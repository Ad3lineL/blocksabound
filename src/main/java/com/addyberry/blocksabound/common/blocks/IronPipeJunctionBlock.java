package com.addyberry.blocksabound.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class IronPipeJunctionBlock extends PipeBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public IronPipeJunctionBlock(float apothem, Properties properties) {
        super(apothem, properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected MapCodec<? extends PipeBlock> codec() {
        return null;
    }
}
