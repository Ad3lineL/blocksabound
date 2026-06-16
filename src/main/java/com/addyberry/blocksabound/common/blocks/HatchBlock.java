package com.addyberry.blocksabound.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class HatchBlock extends FaceAttachedHorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape UP_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_OPEN;
    protected static final VoxelShape EAST_JAW_UP;
    protected static final VoxelShape EAST_JAW_DOWN;
    protected static final VoxelShape WEST_OPEN;
    protected static final VoxelShape WEST_JAW_UP;
    protected static final VoxelShape WEST_JAW_DOWN;
    protected static final VoxelShape SOUTH_OPEN;
    protected static final VoxelShape SOUTH_JAW_UP;
    protected static final VoxelShape SOUTH_JAW_DOWN;
    protected static final VoxelShape NORTH_OPEN;
    protected static final VoxelShape NORTH_JAW_UP;
    protected static final VoxelShape NORTH_JAW_DOWN;
    protected static final VoxelShape UP_OPEN_X;
    protected static final VoxelShape UP_OPEN_Z;
    protected static final VoxelShape UP_JAW_EAST;
    protected static final VoxelShape UP_JAW_WEST;
    protected static final VoxelShape UP_JAW_SOUTH;
    protected static final VoxelShape UP_JAW_NORTH;
    protected static final VoxelShape DOWN_OPEN_X;
    protected static final VoxelShape DOWN_OPEN_Z;
    protected static final VoxelShape DOWN_JAW_EAST;
    protected static final VoxelShape DOWN_JAW_WEST;
    protected static final VoxelShape DOWN_JAW_SOUTH;
    protected static final VoxelShape DOWN_JAW_NORTH;

    public HatchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(OPEN, false)
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false)
                .setValue(FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return null;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean flag = state.getValue(OPEN);
        return switch (state.getValue(FACE)) {
            case FLOOR -> {
                if (direction.getAxis() == Direction.Axis.X) {
                    yield flag ? UP_OPEN_X : UP_AABB;
                }
                yield flag ? UP_OPEN_Z : UP_AABB;
            }
            case WALL -> switch (state.getValue(FACING)) {
                case NORTH -> flag ? NORTH_OPEN : NORTH_AABB;
                case SOUTH -> flag ? SOUTH_OPEN : SOUTH_AABB;
                case WEST -> flag ? WEST_OPEN : WEST_AABB;
                case EAST -> flag ? EAST_OPEN : EAST_AABB;
                default -> throw new MatchException(null, null);
            };
            default -> {
                if (direction.getAxis() == Direction.Axis.X) {
                    yield flag ? DOWN_OPEN_X : DOWN_AABB;
                }
                yield flag ? DOWN_OPEN_Z : DOWN_AABB;
            }
        };

    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (!state.getValue(OPEN)) {
            VoxelShape shape = getShape(state, level, pos, context);
            Direction facingOpposite = getOpenDirection(state).getOpposite();
            Direction.Axis axis = facingOpposite.getAxis();
            if (facingOpposite != Direction.UP) {
                int step = facingOpposite.getAxisDirection().getStep();
                return Shapes.create(shape.bounds().contract(
                        axis == Direction.Axis.X ? step * 0.01 : 0,
                        axis == Direction.Axis.Y ? step *0.01 : 0,
                        axis == Direction.Axis.Z ? step * 0.01 : 0
                ));
            }
        }

        return super.getCollisionShape(state, level, pos, context);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (state.getValue(FACE) != AttachFace.WALL && changeState(state, level, pos, entity instanceof Player player ? player : null, true)) {
            Direction.Axis axis = state.getValue(FACING).getAxis();
            Vec3 towardsCenter = entity.position().vectorTo(Vec3.atCenterOf(pos)).multiply(axis == Direction.Axis.X ? 0.5 : 0, 0, axis == Direction.Axis.Z ? 0.5 : 0);
            entity.addDeltaMovement(towardsCenter);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        for(Direction direction : context.getNearestLookingDirections()) {
            BlockState blockstate;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = this.defaultBlockState().setValue(FACE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR).setValue(FACING, context.getHorizontalDirection());
            } else {
                blockstate = this.defaultBlockState().setValue(FACE, AttachFace.WALL).setValue(FACING, direction.getOpposite());
            }

            return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }

        return null;
    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (state.getValue(POWERED) != level.hasNeighborSignal(currentPos)) {
            state = state.cycle(POWERED);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        Direction facing = getOpenDirection(state);
        Direction.Axis axis = facing.getAxis();
        int step = facing.getAxisDirection().getStep();
        double s0 = pos.get(axis) + (step == 1 ? 0.0   : 0.775);
        double s1 = pos.get(axis) + (step == 1 ? 0.225 : 1.0);

        AABB box = entity.getBoundingBox();
        if (box.max(axis) >= s0 && box.min(axis) <= s1) {
            if (changeState(state, level, pos, entity instanceof Player player ? player : null, true)) {
                if (axis == Direction.Axis.Y && entity.getY() > pos.getY()) {
                    Direction.Axis axis1 = state.getValue(FACING).getAxis();
                    Vec3 towardsCenter = entity.position().vectorTo(Vec3.atCenterOf(pos)).multiply(axis1 == Direction.Axis.X ? 0.5 : 0, 0, axis1 == Direction.Axis.Z ? 0.5 : 0);
                    entity.addDeltaMovement(towardsCenter);
                } else if (entity.blockPosition().equals(pos)) {
                    entity.addDeltaMovement(new Vec3(0, 0.25, 0));
                }
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Direction facing = getOpenDirection(state);
        AABB box = new AABB(pos).expandTowards(facing.getStepX() * 0.6, facing.getStepY() * 0.6, facing.getStepZ() * 0.6);
        if (facing.getAxis() != Direction.Axis.Y) {
            box = box.inflate(0.0, 0.1, 0.0);
        }
        if (
                !level.getEntitiesOfClass(Entity.class, box).isEmpty()
                || !changeState(state, level, pos, null, false)
        ) level.scheduleTick(pos, this, 20);

        super.tick(state, level, pos, random);
    }

    protected static Direction getOpenDirection(BlockState state) {
        return switch (state.getValue(FACE)) {
            case CEILING -> Direction.DOWN;
            case FLOOR -> Direction.UP;
            case WALL -> state.getValue(FACING);
        };
    }

    protected static boolean changeState(BlockState state, Level level, BlockPos pos, @Nullable Player player, boolean open) {
        if (!state.getValue(POWERED) && state.getValue(OPEN) != open) {
            level.setBlockAndUpdate(pos, state.setValue(OPEN, open));
            level.playSound(player, pos, open ? SoundEvents.IRON_TRAPDOOR_OPEN : SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.6F);
            level.gameEvent(player, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            if (open) level.scheduleTick(pos, state.getBlock(), 20);
            return true;
        }

        return false;
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPEN, POWERED, WATERLOGGED, FACE, FACING);
    }

    static {
        EAST_JAW_UP = Block.box(0.0D, 0.0D, 0.0D, 9.0D, 2.0D, 16.0D);
        EAST_JAW_DOWN = Block.box(0.0D, 14.0D, 0.0D, 9.0D, 16.0D, 16.0D);
        EAST_OPEN = Shapes.or(EAST_JAW_UP, EAST_JAW_DOWN);
        WEST_JAW_UP = Block.box(7.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        WEST_JAW_DOWN = Block.box(7.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        WEST_OPEN = Shapes.or(WEST_JAW_UP, WEST_JAW_DOWN);
        SOUTH_JAW_UP = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 9.0D);
        SOUTH_JAW_DOWN = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 9.0D);
        SOUTH_OPEN = Shapes.or(SOUTH_JAW_UP, SOUTH_JAW_DOWN);
        NORTH_JAW_UP = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 2.0D, 16.0D);
        NORTH_JAW_DOWN = Block.box(0.0D, 14.0D, 7.0D, 16.0D, 16.0D, 16.0D);
        NORTH_OPEN = Shapes.or(NORTH_JAW_UP, NORTH_JAW_DOWN);

        UP_JAW_EAST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 9.0D, 16.0D);
        UP_JAW_WEST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
        UP_JAW_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 2.0D);
        UP_JAW_NORTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 9.0D, 16.0D);
        UP_OPEN_X = Shapes.or(UP_JAW_EAST, UP_JAW_WEST);
        UP_OPEN_Z = Shapes.or(UP_JAW_SOUTH, UP_JAW_NORTH);

        DOWN_JAW_EAST = Block.box(0.0D, 7.0D, 0.0D, 2.0D, 16.0D, 16.0D);
        DOWN_JAW_WEST = Block.box(14.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        DOWN_JAW_SOUTH = Block.box(0.0D, 7.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        DOWN_JAW_NORTH = Block.box(0.0D, 7.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        DOWN_OPEN_X = Shapes.or(DOWN_JAW_EAST, DOWN_JAW_WEST);
        DOWN_OPEN_Z = Shapes.or(DOWN_JAW_SOUTH, DOWN_JAW_NORTH);
    }
}
