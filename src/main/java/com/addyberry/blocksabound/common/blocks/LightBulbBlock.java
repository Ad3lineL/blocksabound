package com.addyberry.blocksabound.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;


public class LightBulbBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty INVERTED = BlockStateProperties.INVERTED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape FLOOR_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 9.0, 11.0);
    protected static final VoxelShape CEILING_SHAPE = Block.box(5.0, 7.0, 5.0, 11.0, 16.0, 11.0);
    protected static final VoxelShape NORTH_WALL_SHAPE = Block.box(5.0, 5.0, 7.0, 11.0, 11.0, 16.0);
    protected static final VoxelShape WEST_WALL_SHAPE = Block.box(7.0, 5.0, 5.0, 16.0, 11.0, 11.0);
    protected static final VoxelShape EAST_WALL_SHAPE = Block.box(0.0, 5.0, 5.0, 9.0, 11.0, 11.0);
    protected static final VoxelShape SOUTH_WALL_SHAPE = Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 9.0);


    public LightBulbBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT, false)
                .setValue(INVERTED, false)
                .setValue(POWERED, false)
                .setValue(FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
        );
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(FACE) == AttachFace.FLOOR)  {
            return FLOOR_SHAPE;
        } else if (state.getValue(FACE) == AttachFace.CEILING) {
            return CEILING_SHAPE;
        }
        if (state.getValue(FACING) == Direction.WEST) {
            return WEST_WALL_SHAPE;
        } else if (state.getValue(FACING) == Direction.EAST) {
            return EAST_WALL_SHAPE;
        } else if (state.getValue(FACING) == Direction.SOUTH) {
            return SOUTH_WALL_SHAPE;
        }
        return NORTH_WALL_SHAPE;
    };

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
            BlockState blockstate;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = this.defaultBlockState().setValue(FACE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR).setValue(FACING, context.getHorizontalDirection());
            } else {
                blockstate = this.defaultBlockState().setValue(FACE, AttachFace.WALL).setValue(FACING, direction.getOpposite());
            }

            if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockstate.setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos())).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
            }
        }

        return null;
    }

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.invert(state, level, pos, (Player)null);
            return InteractionResult.CONSUME;
        }
    }

    private void invert (BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        BlockState blockstate = state.cycle(INVERTED);
        this.setLit(blockstate, level, pos, null);
        this.playSound(player, level, pos, blockstate.getValue(LIT));
    }

    private void setLit (BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        BlockState blockstate = state.cycle(LIT);
        level.setBlockAndUpdate(pos, blockstate);
    }

    protected void playSound(@Nullable Player player, LevelAccessor level, BlockPos pos, boolean isLit) {
        float f = isLit ? 0.6F : 0.9F;
        level.playSound(player, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, f);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }

    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean flag = level.hasNeighborSignal(pos);
            if (state.getValue(POWERED) != flag) {
                BlockState blockstate = state.cycle(POWERED);
                this.setLit(blockstate, level, pos, null);
            }
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        BlockPos blockpos = hit.getBlockPos();
        if (!level.isClientSide && projectile.mayInteract(level, blockpos) && projectile.mayBreak(level)) {
            level.destroyBlock(blockpos, true, projectile);
        }

    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, INVERTED, POWERED, FACE, FACING);
    }

}
