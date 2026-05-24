package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.common.registry.BlocksAboundBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class BlocksAboundLootTableProvider extends BlockLootSubProvider {
    public BlocksAboundLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlocksAboundBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    @Override
    protected void generate() {
        for (Block block : this.getKnownBlocks()) {
            dropSelf(block);
        }
    }

}
