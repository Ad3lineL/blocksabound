package com.addyberry.blocksabound.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
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

    protected static final VoxelShape OPEN_PLACEHOLDER = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

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
        switch (state.getValue(FACE)) {
            case FLOOR:
                if (direction.getAxis() == Direction.Axis.X) {
                    return flag ? UP_OPEN_X : UP_AABB;
                }
                return flag ? UP_OPEN_Z : UP_AABB;
            case WALL:
                return switch (state.getValue(FACING)) {
                    case NORTH -> flag ? NORTH_OPEN : NORTH_AABB;
                    case SOUTH -> flag ? SOUTH_OPEN : SOUTH_AABB;
                    case WEST -> flag ? WEST_OPEN : WEST_AABB;
                    case EAST -> flag ? EAST_OPEN : EAST_AABB;
                    default -> throw new MatchException((String) null, (Throwable) null);
                };
            case CEILING:
            default:
                if (direction.getAxis() == Direction.Axis.X) {
                    return flag ? DOWN_OPEN_X : DOWN_AABB;
                }
                return flag ? DOWN_OPEN_Z : DOWN_AABB;
        }

    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        Direction facing = Direction.UP;
        Vec3 entityPosition = entity.position();

        double offset = entityPosition.get(facing.getAxis()) - pos.get(facing.getAxis()) + 0.875;
        if (Math.abs(offset) <= 0.125) {
            if (!state.getValue(OPEN)) {
                BlockState blockstate = state.setValue(OPEN, true);
                level.setBlockAndUpdate(pos, blockstate);
                this.playSound((Player) null, level, pos, true);
            }
        }
    }

    protected void playSound(@Nullable Player player, LevelAccessor level, BlockPos pos, boolean isOpen) {
        level.playSound(player, pos, isOpen ? SoundEvents.IRON_TRAPDOOR_OPEN : SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
        level.gameEvent(player, isOpen ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
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
