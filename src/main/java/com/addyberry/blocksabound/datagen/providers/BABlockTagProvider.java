package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.BlocksAbound;
import com.addyberry.blocksabound.core.registry.BABlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BABlockTagProvider extends BlockTagsProvider {
    public BABlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BlocksAbound.MODID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {

        IntrinsicTagAppender<Block> pickaxeMinable = this.tag(BlockTags.MINEABLE_WITH_PICKAXE).replace(false);
        /*IntrinsicTagAppender<Block> axeMinable = this.tag(BlockTags.MINEABLE_WITH_AXE).replace(false);
        IntrinsicTagAppender<Block> shovelMinable = this.tag(BlockTags.MINEABLE_WITH_SHOVEL).replace(false);
        IntrinsicTagAppender<Block> hoeMinable = this.tag(BlockTags.MINEABLE_WITH_HOE).replace(false);
        IntrinsicTagAppender<Block> swordMinable = this.tag(BlockTags.SWORD_EFFICIENT).replace(false);*/
        
        for (DeferredHolder<Block, ? extends Block> block : BABlocks.BLOCKS.getEntries()) {
            String name = block.getRegisteredName().toLowerCase();
            if (name.contains("tawny") ||
                name.contains("pyrite")) {
                pickaxeMinable.add(block.get());
            }
        }

        //todo: slabs
    }
}
