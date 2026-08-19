package com.addyberry.blocksabound.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ReinforcedGlassBlock extends Block {
    public ReinforcedGlassBlock(Properties properties) {
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
                case TOP -> side == Direction.DOWN;
                case BOTTOM -> side == Direction.UP;
                default -> true;
            };
        }
        return super.skipRendering(state, adjacentBlockState, side);
    }
}
