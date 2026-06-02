package com.addyberry.blocksabound.datagen;

import com.addyberry.blocksabound.BlocksAbound;
import com.addyberry.blocksabound.datagen.providers.BABlockstateProvider;
import com.addyberry.blocksabound.datagen.providers.BAItemModelProvider;
import com.addyberry.blocksabound.datagen.providers.BALootTableProvider;
import com.addyberry.blocksabound.datagen.providers.BABlockTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BlocksAbound.MODID)
public class BADatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(BALootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        BABlockTagProvider blockTags = new BABlockTagProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(true, blockTags);
        generator.addProvider(event.includeClient(), new BABlockstateProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new BAItemModelProvider(output, existingFileHelper));
    }
}
