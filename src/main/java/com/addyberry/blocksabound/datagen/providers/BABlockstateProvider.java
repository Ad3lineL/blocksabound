package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.addyberry.blocksabound.core.registry.BABlocks.*;

public class BABlockstateProvider extends BlockStateProvider {
        public BABlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, BlocksAbound.MODID, exFileHelper);
        }

    @Override
    protected void registerStatesAndModels() {

            //Tawny Plate Set
        String tawnyPlateLoc = ModelProvider.BLOCK_FOLDER + "/tawny_plate";

        this.simpleBlockWithItem(TAWNY_PLATE.get(), this.models().cubeAll("tawny_plate", this.modLoc(tawnyPlateLoc + "/tawny_plate")));
        this.simpleBlockWithItem(CHISELED_TAWNY_PLATE.get(), this.models().cubeAll("chiseled_tawny_plate", this.modLoc(tawnyPlateLoc + "/chiseled_tawny_plate")));
        //this.simpleBlockWithItem(TAWNY_GRATE.get(), this.models().cubeAll("tawny_grate", this.modLoc(tawnyPlateLoc + "tawny_grate")).renderType("cutout"));

        this.simpleBlockWithItem(CUT_TAWNY_PLATE.get(), this.models().cubeAll("cut_tawny_plate", this.modLoc(tawnyPlateLoc + "/cut_tawny_plate")));

        this.stairsBlock((StairBlock) CUT_TAWNY_PLATE_STAIRS.get(), this.modLoc(tawnyPlateLoc + "/cut_tawny_plate"));
        this.simpleBlockItem(CUT_TAWNY_PLATE_STAIRS.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_tawny_plate_stairs")));

        this.slabBlock((SlabBlock) CUT_TAWNY_PLATE_SLAB.get(), this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_tawny_plate"), this.modLoc(tawnyPlateLoc + "/cut_tawny_plate"));
        this.simpleBlockItem(CUT_TAWNY_PLATE_SLAB.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_tawny_plate_slab")));

        this.simpleBlockItem(TAWNY_TRAPDOOR.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tawny_trapdoor_bottom")));

        this.simpleBlockItem(TAWNY_HULL_PLATE.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tawny_hull_plate")));

    }
}
