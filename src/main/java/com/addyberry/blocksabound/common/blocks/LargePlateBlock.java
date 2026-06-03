package com.addyberry.blocksabound.common.blocks;

import com.addyberry.blocksabound.core.registry.BABlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class LargePlateBlock extends DirectionalBlock {
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");;

    public LargePlateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(CONNECTED, false)
                .setValue(FACING, Direction.UP)
        );
    }

    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        state = state.setValue(FACING, context.getClickedFace().getOpposite());

        BlockPos clickedPos = context.getClickedPos().relative(context.getClickedFace().getOpposite());
        BlockState blockState = context.getLevel().getBlockState(clickedPos);
        if (blockState.is(this) && !blockState.getValue(CONNECTED)) {
            state = state.setValue(CONNECTED, true);
        }
        return state;
    }

    public static void connectBlock(BlockState state, Direction direction, Level level, BlockPos pos) {
        if (state.is(BABlocks.LARGE_PYRITE_PLATE)) {
            if (!state.getValue(CONNECTED)) {
                BlockState blockstate = state
                        .setValue(FACING, direction)
                        .setValue(CONNECTED, true);
                level.setBlockAndUpdate(pos, blockstate);
            }
        }
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockPos blockpos = pos.relative(state.getValue(FACING), 1);
        BlockState blockstate = level.getBlockState(blockpos);
        return state.setValue(CONNECTED, blockstate.is(this));
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return null;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CONNECTED, FACING);
    }
}
