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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FluorescentTubeBlock extends RotatedPillarBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty INVERTED = BlockStateProperties.INVERTED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty EXTENDED_UP = BooleanProperty.create("extended_up");
    public static final BooleanProperty EXTENDED_DOWN = BooleanProperty.create("extended_down");

    protected static final VoxelShape AXIS_X = Block.box(0.0, 6.0, 6.0, 16.0, 10.0, 10.0);
    protected static final VoxelShape AXIS_Y = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    protected static final VoxelShape AXIS_Z = Block.box(6.0, 6.0, 0.0, 10.0, 10.0, 16.0);

    public FluorescentTubeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
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
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction.Axis axis = context.getClickedFace().getAxis();
        BlockState state = this.defaultBlockState().setValue(AXIS, axis);

        Direction positive = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        boolean powered = level.hasNeighborSignal(pos);
        boolean inverted = false;

        BlockState positiveNeighbor = level.getBlockState(pos.relative(positive));
        if (positiveNeighbor.is(this) && positiveNeighbor.getValue(AXIS) == axis) {
            state = state.setValue(EXTENDED_UP, true);
            powered |= positiveNeighbor.getValue(POWERED);
            inverted |= positiveNeighbor.getValue(INVERTED);
        }
        BlockState negativeNeighbor = level.getBlockState(pos.relative(positive.getOpposite()));
        if (negativeNeighbor.is(this) && negativeNeighbor.getValue(AXIS) == axis) {
            state = state.setValue(EXTENDED_DOWN, true);
            powered |= negativeNeighbor.getValue(POWERED);
            inverted |= negativeNeighbor.getValue(INVERTED);
        }

        return state.setValue(POWERED, powered).setValue(INVERTED, inverted).setValue(LIT, powered != inverted);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() == state.getValue(AXIS)) {
            boolean connected = neighborState.is(this) && neighborState.getValue(AXIS) == state.getValue(AXIS);
            BooleanProperty end = direction.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EXTENDED_UP : EXTENDED_DOWN;
            state = state.setValue(end, connected);
        }
        return state;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (level.isClientSide) return;
        BlockPos origin = pos;
        BlockState neighborState = level.getBlockState(fromPos);
        if (neighborState.is(this) && neighborState.getValue(AXIS) == state.getValue(AXIS)) origin = fromPos;
        updateLine(level, origin, state.getValue(AXIS));
    }

    private void updateLine(Level level, BlockPos origin, Direction.Axis axis) {
        List<BlockPos> line = new ArrayList<>();
        line.add(origin);
        Direction positive = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        for (Direction dir : new Direction[]{positive, positive.getOpposite()}) {
            BlockPos.MutableBlockPos cursor = origin.mutable().move(dir);
            BlockState cursorState = level.getBlockState(cursor);
            while (cursorState.is(this) && cursorState.getValue(AXIS) == axis) {
                line.add(cursor.immutable());
                cursor.move(dir);
                cursorState = level.getBlockState(cursor);
            }
        }

        boolean powered = false;
        for (BlockPos pos : line) {
            if (level.hasNeighborSignal(pos)) {
                powered = true;
                break;
            }
        }

        boolean inverted = level.getBlockState(origin).getValue(INVERTED);
        for (BlockPos pos : line) {
            BlockState state = level.getBlockState(pos);
            boolean lit = powered != inverted;
            if (state.getValue(POWERED) != powered || state.getValue(LIT) != lit || state.getValue(INVERTED) != inverted) {
                level.setBlock(pos, state.setValue(POWERED, powered).setValue(LIT, lit).setValue(INVERTED, inverted), Block.UPDATE_CLIENTS);
            }
        }
    }

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        boolean inverted = !state.getValue(INVERTED);
        boolean lit = state.getValue(POWERED) != inverted;
        level.setBlockAndUpdate(pos, state.setValue(INVERTED, inverted).setValue(LIT, lit));
        this.playSound(player, level, pos, lit);
        return InteractionResult.SUCCESS;
    }

    protected void playSound(@Nullable Player player, LevelAccessor level, BlockPos pos, boolean isLit) {
        float f = isLit ? 0.6F : 0.9F;
        level.playSound(player, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, f);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, EXTENDED_DOWN, EXTENDED_UP, LIT, POWERED, INVERTED);
    }
}
