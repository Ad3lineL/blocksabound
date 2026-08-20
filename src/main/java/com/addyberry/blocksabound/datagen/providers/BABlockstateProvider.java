package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
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


            //Reinforced Glass
        this.simpleBlockWithItem(REINFORCED_GLASS.get(), this.models().cubeAll("reinforced_glass", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_glass")).renderType("translucent"));

        for (DyeColor color : DyeColor.values()) {
            Block block = DYED_REINFORCED_GLASS.get(color).get();
            String name = color.getName() + "_reinforced_glass";

            ModelFile full = this.models().cubeAll(name, this.modLoc(ModelProvider.BLOCK_FOLDER + "/colored_reinforced_glass/" + name)).renderType("translucent");

            this.simpleBlockWithItem(block, full);
        }
        this.slabBlock((SlabBlock) REINFORCED_GLASS_SLAB.get(),
                this.models().withExistingParent("reinforced_glass_slab", this.modLoc( ModelProvider.BLOCK_FOLDER + "/parents/polished_slab"))
                        .texture("end", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_glass")).texture("side", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_glass_slab")).renderType("translucent"),
                this.models().withExistingParent("reinforced_glass_slab_top", this.modLoc(ModelProvider.BLOCK_FOLDER + "/parents/polished_slab_top"))
                        .texture("end", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_glass")).texture("side", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_glass_slab")).renderType("translucent"),
                this.models().withExistingParent("reinforced_glass_slab_double", this.modLoc(ModelProvider.BLOCK_FOLDER + "/parents/polished_slab_double"))
                        .texture("end", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_glass")).texture("side", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_glass_slab")).renderType("translucent")
        );
        this.simpleBlockItem(REINFORCED_GLASS_SLAB.get(), this.models().withExistingParent("reinforced_glass_slab", this.modLoc( ModelProvider.BLOCK_FOLDER + "/parents/polished_slab")));


        for (DyeColor color : DyeColor.values()) {
            Block block = DYED_REINFORCED_GLASS_SLAB.get(color).get();
            String name = color.getName() + "_reinforced_glass_slab";
            ResourceLocation end = this.modLoc(ModelProvider.BLOCK_FOLDER + "/colored_reinforced_glass/" + color.getName() + "_reinforced_glass");
            ResourceLocation side = this.modLoc( ModelProvider.BLOCK_FOLDER + "/colored_reinforced_glass/" + color.getName() + "_reinforced_glass_slab");

            ModelFile bottom = this.models().withExistingParent(name, this.modLoc( ModelProvider.BLOCK_FOLDER + "/parents/polished_slab"))
                    .texture("end", end).texture("side", side).renderType("translucent");
            ModelFile top = this.models().withExistingParent(name + "_top", this.modLoc(ModelProvider.BLOCK_FOLDER + "/parents/polished_slab_top"))
                    .texture("end", end).texture("side", side).renderType("translucent");
            ModelFile doubleSlab = this.models().withExistingParent(name + "_double", this.modLoc(ModelProvider.BLOCK_FOLDER + "/parents/polished_slab_double"))
                    .texture("end", end).texture("side", side).renderType("translucent");

            this.slabBlock((SlabBlock) block, bottom, top, doubleSlab);
            this.simpleBlockItem(block, bottom);
        }

        /*
        for (DyeColor color : DyeColor.values()) {
            Block block = DYED_VERTICAL_REINFORCED_GLASS_SLAB.get(color).get();
            String name = "vertical_" + color.getName() + "_reinforced_glass_slab";
            ResourceLocation full = this.modLoc(ModelProvider.BLOCK_FOLDER + "/colored_reinforced_glass/" + color.getName() + "_reinforced_glass");
            ResourceLocation horizontal = this.modLoc( ModelProvider.BLOCK_FOLDER + "/colored_reinforced_glass/" + color.getName() + "_reinforced_glass_slab");
            ResourceLocation vertical = this.modLoc( ModelProvider.BLOCK_FOLDER + "/colored_reinforced_glass/vertical_" + color.getName() + "_reinforced_glass_slab");

            ModelFile east = this.models().withExistingParent(name, this.modLoc( ModelProvider.BLOCK_FOLDER + "/compat/parents/polished_vertical_slab_east"))
                    .texture("full", full).texture("horizontal", horizontal).texture("vertical", vertical).renderType("translucent");
            ModelFile north = this.models().withExistingParent(name, this.modLoc( ModelProvider.BLOCK_FOLDER + "/compat/parents/polished_vertical_slab_north"))
                    .texture("full", full).texture("horizontal", horizontal).texture("vertical", vertical).renderType("translucent");
            ModelFile west = this.models().withExistingParent(name, this.modLoc( ModelProvider.BLOCK_FOLDER + "/compat/parents/polished_vertical_slab_west"))
                    .texture("full", full).texture("horizontal", horizontal).texture("vertical", vertical).renderType("translucent");
            ModelFile south = this.models().withExistingParent(name, this.modLoc( ModelProvider.BLOCK_FOLDER + "/compat/parents/polished_vertical_slab_south"))
                    .texture("full", full).texture("horizontal", horizontal).texture("vertical", vertical).renderType("translucent");
            ModelFile doubleX = this.models().withExistingParent(name, this.modLoc( ModelProvider.BLOCK_FOLDER + "/compat/parents/polished_vertical_slab_double_x"))
                    .texture("full", full).texture("horizontal", horizontal).texture("vertical", vertical).renderType("translucent");
            ModelFile doubleZ = this.models().withExistingParent(name, this.modLoc( ModelProvider.BLOCK_FOLDER + "/compat/parents/polished_vertical_slab_double_z"))
                    .texture("full", full).texture("horizontal", horizontal).texture("vertical", vertical).renderType("translucent");

            this.simpleBlock(block, east);
        }
        */

            //Misc
        this.simpleBlockItem(PIPE.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/pipe")));
        this.simpleBlockWithItem(REINFORCED_IRON.get(), this.models().cubeAll("reinforced_iron", this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_iron")));
        this.simpleBlockItem(REINFORCED_IRON_STAIRS.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_iron_stairs_west")));
        this.simpleBlockItem(REINFORCED_IRON_SLAB.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_iron_slab")));
        this.simpleBlockWithItem(CHISELED_REINFORCED_IRON.get(), this.models().cubeColumn("chiseled_reinforced_iron", this.modLoc(ModelProvider.BLOCK_FOLDER + "/chiseled_reinforced_iron"), this.modLoc(ModelProvider.BLOCK_FOLDER + "/chiseled_reinforced_iron_end")));
        this.simpleBlockItem(REINFORCED_IRON_WALL.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/reinforced_iron_wall_inventory")));

        this.flatBlockItem(LIGHT_BULB.get());
        this.flatBlockItem(SOUL_LIGHT_BULB.get());
        this.flatBlockItem(REDSTONE_LIGHT_BULB.get());
        this.flatBlockItem(COPPER_LIGHT_BULB.get());

        this.flatBlockItem(COLD_FLUORESCENT_TUBE.get());
        this.flatBlockItem(COOL_FLUORESCENT_TUBE.get());
        this.flatBlockItem(FLUORESCENT_TUBE.get());
        this.flatBlockItem(WARM_FLUORESCENT_TUBE.get());
        this.flatBlockItem(HOT_FLUORESCENT_TUBE.get());
        this.flatBlockItem(ABERRANT_FLUORESCENT_TUBE.get());

        this.simpleBlockItem(WHEEL.get(), this.models().getExistingFile(this.modLoc(ModelProvider.BLOCK_FOLDER + "/wheel")));
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
