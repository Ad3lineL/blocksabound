package com.addyberry.blocksabound.common.items;

import com.addyberry.blocksabound.common.blocks.IronPipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class IronPipeBlockItem extends BlockItem {
    public IronPipeBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        if (result.consumesAction()) {
            BlockPos clickedPos = context.getClickedPos().relative(context.getClickedFace().getOpposite());
            BlockState clickedState = context.getLevel().getBlockState(clickedPos);
            IronPipeBlock.connectBlock(clickedState, context.getClickedFace(), context.getLevel(), clickedPos);
        }
        return result;
    }
}