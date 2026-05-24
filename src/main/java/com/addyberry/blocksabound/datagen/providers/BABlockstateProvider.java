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

        String tawnyPlateLoc = ModelProvider.BLOCK_FOLDER + "/tawny_plate/";
        this.simpleBlockWithItem(TAWNY_PLATE.get(), this.models().cubeAll("tawny_plate", this.modLoc(tawnyPlateLoc + "tawny_plate")));
        this.simpleBlockWithItem(CHISELED_TAWNY_PLATE.get(), this.models().cubeAll("chiseled_tawny_plate", this.modLoc(tawnyPlateLoc + "chiseled_tawny_plate")));
        this.simpleBlockWithItem(TAWNY_GRATE.get(), this.models().cubeAll("tawny_grate", this.modLoc(tawnyPlateLoc + "tawny_grate")).renderType("cutout"));
        this.simpleBlockWithItem(CUT_TAWNY_PLATE.get(), this.models().cubeAll("cut_tawny_plate", this.modLoc(tawnyPlateLoc + "cut_tawny_plate")));


    }
}
