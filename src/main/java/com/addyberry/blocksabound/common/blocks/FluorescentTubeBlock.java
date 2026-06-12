package com.addyberry.blocksabound.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class FluorescentTubeBlock extends RotatedPillarBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty INVERTED = BlockStateProperties.INVERTED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty EXTENDED_UP = BooleanProperty.create("extended_up");;
    public static final BooleanProperty EXTENDED_DOWN = BooleanProperty.create("extended_down");;

    protected static final VoxelShape AXIS_X = Block.box(0.0, 6.0, 6.0, 16.0, 10.0, 10.0);
    protected static final VoxelShape AXIS_Y = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    protected static final VoxelShape AXIS_Z = Block.box(6.0, 6.0, 0.0, 10.0, 10.0, 16.0);

    public FluorescentTubeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(LIT, false)
                .setValue(INVERTED, false)
                .setValue(POWERED, false)
                .setValue(EXTENDED_UP, false)
                .setValue(EXTENDED_DOWN, false)
                .setValue(AXIS, Direction.Axis.Y)
        );
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> AXIS_X;
            case Y -> AXIS_Y;
            case Z -> AXIS_Z;
        };
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {

        BlockState clickedState = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()));
        BlockState oppositeClickedState = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace()));

        BlockState state = this.defaultBlockState();
        boolean poweredflag = false;
        if ((clickedState.is(this) && clickedState.getValue(POWERED)) || (oppositeClickedState.is(this) && oppositeClickedState.getValue(POWERED))) {
            poweredflag = true;
        }

        if ((clickedState.is(this) && clickedState.getValue(INVERTED)) || (oppositeClickedState.is(this) && oppositeClickedState.getValue(INVERTED))) {
            state = state.setValue(INVERTED, true);
        }

        state = state
                .setValue(AXIS, context.getClickedFace().getAxis())
                .setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()) || poweredflag);

        state = state.setValue(LIT, state.getValue(POWERED) != state.getValue(INVERTED));

        return state;
    }

    //

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (neighborState.is(this)) {
            boolean neighborIsSameAxis = (direction.getAxis() == state.getValue(AXIS)) && (state.getValue(AXIS) == neighborState.getValue(AXIS));
            if (neighborIsSameAxis && !state.equals(neighborState)) {
                state = state
                        .setValue(LIT, neighborState.getValue(LIT))
                        .setValue(INVERTED, neighborState.getValue(INVERTED))
                        .setValue(POWERED, neighborState.getValue(POWERED))
                ;
            }
        }
        for (int i = 0; i <= 1; i++) {
            BlockState axisEndState = level.getBlockState(pos.relative(Direction.get(i == 0 ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE, state.getValue(AXIS)), 1));
            state = state.setValue(i == 0 ? EXTENDED_UP : EXTENDED_DOWN, false);
            if (axisEndState.is(this)) {
                state = state.setValue(i == 0 ? EXTENDED_UP : EXTENDED_DOWN, axisEndState.getValue(AXIS) == state.getValue(AXIS));
            }
        }


        BlockState axisEndStateNegative = level.getBlockState(pos.relative(Direction.get(Direction.AxisDirection.NEGATIVE, state.getValue(AXIS)), 1));
        state = state.setValue(EXTENDED_DOWN, false);
        if (axisEndStateNegative.is(this)) {
            state = state.setValue(EXTENDED_DOWN, axisEndStateNegative.getValue(AXIS) == state.getValue(AXIS));
        }

        return state;
    }

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.invert(state, level, pos, null);
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
                this.setLit(blockstate, level, pos, (Player)null);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, EXTENDED_DOWN, EXTENDED_UP, LIT, POWERED, INVERTED);
    }
}
