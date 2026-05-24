package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.addyberry.blocksabound.common.registry.BABlocks.*;

public class BABlockstateProvider extends BlockStateProvider {
        public BABlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, BlocksAbound.MODID, exFileHelper);
        }

    @Override
    protected void registerStatesAndModels() {

        ResourceLocation tawnyPlateLoc = this.modLoc(ModelProvider.BLOCK_FOLDER + "/tawny_plate/tawny_plate");
        this.simpleBlockWithItem(TAWNY_PLATE.get(), this.models().cubeAll("tawny_plate", tawnyPlateLoc));
    }
}
