package com.addyberry.blocksabound.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ReinforcedGlassSlabBlock extends SlabBlock {
    public ReinforcedGlassSlabBlock(Properties properties) {
        super(properties);
    }

    protected VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    protected float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 1.0F;
    }

    protected boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

    protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {

        if (adjacentBlockState.getBlock() instanceof ReinforcedGlassBlock) {return true;}

        if (adjacentBlockState.getBlock() instanceof ReinforcedGlassSlabBlock) {
            return switch (adjacentBlockState.getValue(BlockStateProperties.SLAB_TYPE)) {
                case TOP -> (side.getAxis() != Direction.Axis.Y && state.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.TOP) || (side == Direction.DOWN && state.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.TOP);
                case BOTTOM -> (side.getAxis() != Direction.Axis.Y && state.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) || (side == Direction.UP && state.getValue(BlockStateProperties.SLAB_TYPE) != SlabType.BOTTOM);
                default -> true;

            };
        }

        //state.getValue(BlockStateProperties.SLAB_TYPE) ==

        return super.skipRendering(state, adjacentBlockState, side);
    }
}
