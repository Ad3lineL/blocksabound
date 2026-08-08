package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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

        this.simpleBlockWithItem(CUT_TAWNY_PLATE.get(), this.models().cubeAll("cut_tawny_plate", this.modLoc(tawnyPlateLoc + "/cut_tawny_plate")));

        this.stairsBlock((StairBlock) CUT_TAWNY_PLATE_STAIRS.get(), this.modLoc(tawnyPlateLoc + "/cut_tawny_plate"));
        this.simpleBlockItem(CUT_TAWNY_PLATE_STAIRS.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_tawny_plate_stairs")));

        this.slabBlock((SlabBlock) CUT_TAWNY_PLATE_SLAB.get(), this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_tawny_plate"), this.modLoc(tawnyPlateLoc + "/cut_tawny_plate"));
        this.simpleBlockItem(CUT_TAWNY_PLATE_SLAB.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_tawny_plate_slab")));

        this.flatBlockItem(TAWNY_DOOR.get());
        this.simpleBlockItem(TAWNY_TRAPDOOR.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tawny_trapdoor_bottom")));

        this.simpleBlockItem(TAWNY_HULL_PLATE.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tawny_hull_plate")));


            //Pyrite Plate Set
        String pyritePlateLoc = ModelProvider.BLOCK_FOLDER + "/pyrite_plate";

        this.simpleBlockWithItem(PYRITE_PLATE.get(), this.models().cubeAll("pyrite_plate", this.modLoc(pyritePlateLoc + "/pyrite_plate")));
        this.simpleBlockWithItem(CHISELED_PYRITE_PLATE.get(), this.models().cubeAll("chiseled_pyrite_plate", this.modLoc(pyritePlateLoc + "/chiseled_pyrite_plate")));

        this.simpleBlockWithItem(CUT_PYRITE_PLATE.get(), this.models().cubeAll("cut_pyrite_plate", this.modLoc(pyritePlateLoc + "/cut_pyrite_plate")));

        this.stairsBlock((StairBlock) CUT_PYRITE_PLATE_STAIRS.get(), this.modLoc(pyritePlateLoc + "/cut_pyrite_plate"));
        this.simpleBlockItem(CUT_PYRITE_PLATE_STAIRS.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_pyrite_plate_stairs")));

        this.slabBlock((SlabBlock) CUT_PYRITE_PLATE_SLAB.get(), this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_pyrite_plate"), this.modLoc(pyritePlateLoc + "/cut_pyrite_plate"));
        this.simpleBlockItem(CUT_PYRITE_PLATE_SLAB.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/cut_pyrite_plate_slab")));

        this.flatBlockItem(PYRITE_DOOR.get());
        this.simpleBlockItem(PYRITE_TRAPDOOR.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/pyrite_trapdoor_bottom")));

        this.simpleBlockItem(LARGE_PYRITE_PLATE.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/large_pyrite_plate_south")));


            //Asphalt
        this.simpleBlockItem(ASPHALT.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/asphalt")));

        this.simpleBlockItem(ASPHALT_STAIRS.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/asphalt_stairs")));

        this.simpleBlockItem(ASPHALT_SLAB.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/asphalt_slab")));


            //Tarmac
        this.simpleBlockItem(TARMAC.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarmac")));

        this.simpleBlockItem(TARMAC_STAIRS.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarmac_stairs")));

        this.simpleBlockItem(TARMAC_SLAB.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarmac_slab")));


            //Tarred Paper
        this.simpleBlockWithItem(TARRED_PAPER.get(), this.models().cubeAll("tarred_paper", this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarred_paper")));

        this.stairsBlock((StairBlock) TARRED_PAPER_STAIRS.get(), this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarred_paper"));
        this.simpleBlockItem(TARRED_PAPER_STAIRS.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarred_paper_stairs")));

        this.slabBlock((SlabBlock) TARRED_PAPER_SLAB.get(), this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarred_paper"), this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarred_paper"));
        this.simpleBlockItem(TARRED_PAPER_SLAB.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tarred_paper_slab")));


            //Misc
        this.simpleBlockItem(PIPE.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/pipe")));
        this.simpleBlockWithItem(REINFORCED_IRON.get(), this.models().cubeAll("reinforced_iron", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_iron")));
        this.simpleBlockItem(REINFORCED_IRON_STAIRS.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_iron_stairs_west")));
        this.simpleBlockItem(REINFORCED_IRON_SLAB.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_iron_slab")));
        this.simpleBlockWithItem(CHISELED_REINFORCED_IRON.get(), this.models().cubeColumn("chiseled_reinforced_iron", this.modLoc(ModelProvider.BLOCK_FOLDER + "/chiseled_reinforced_iron"), this.modLoc(ModelProvider.BLOCK_FOLDER + "/chiseled_reinforced_iron_end")));


        this.flatBlockItem(LIGHT_BULB.get());
        this.flatBlockItem(SOUL_LIGHT_BULB.get());
        this.flatBlockItem(REDSTONE_LIGHT_BULB.get());

        this.flatBlockItem(COLD_FLUORESCENT_TUBE.get());
        this.flatBlockItem(COOL_FLUORESCENT_TUBE.get());
        this.flatBlockItem(FLUORESCENT_TUBE.get());
        this.flatBlockItem(WARM_FLUORESCENT_TUBE.get());
        this.flatBlockItem(HOT_FLUORESCENT_TUBE.get());
        this.flatBlockItem(ABERRANT_FLUORESCENT_TUBE.get());

        this.simpleBlockItem(TAR_BLOCK.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/tar_block")));
    }

    //shamelessly stolen from cappin im not sory
    public void flatBlockItem(Block block) {
        this.flatBlockItem(block, this.modLoc( "item/" + name(block)));
    }

    public void flatBlockItem(Block block, ResourceLocation texture) {
        this.itemModels().getBuilder(key(block).getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
