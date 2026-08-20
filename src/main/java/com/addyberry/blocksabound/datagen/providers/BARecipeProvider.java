package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.core.registry.BABlocks;
import com.addyberry.blocksabound.core.registry.BAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

import static com.addyberry.blocksabound.core.registry.BABlocks.*;

public class BARecipeProvider extends RecipeProvider {
    public BARecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {

            //Tawny Plate
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TAWNY_PLATE, 4)
                .define('#', Items.COPPER_BLOCK).define('N', Items.GOLD_NUGGET)
                .pattern(" N ")
                .pattern("N#N")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.COPPER_BLOCK), has(Items.COPPER_BLOCK)).save(output);

        standardSet(output, TAWNY_PLATE, CHISELED_TAWNY_PLATE, CUT_TAWNY_PLATE, CUT_TAWNY_PLATE_STAIRS, CUT_TAWNY_PLATE_SLAB, TAWNY_TRAPDOOR, TAWNY_DOOR);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TAWNY_HULL_PLATE, 3)
                .define('#', TAWNY_PLATE)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(TAWNY_PLATE), has(TAWNY_PLATE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, TAWNY_HULL_PLATE, TAWNY_PLATE);

            //Pyrite Plate
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, PYRITE_PLATE, 4)
                .define('#', Items.COPPER_BLOCK).define('N', Items.IRON_NUGGET)
                .pattern(" N ")
                .pattern("N#N")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.COPPER_BLOCK), has(Items.COPPER_BLOCK)).save(output);

        standardSet(output, PYRITE_PLATE, CHISELED_PYRITE_PLATE, CUT_PYRITE_PLATE, CUT_PYRITE_PLATE_STAIRS, CUT_PYRITE_PLATE_SLAB, PYRITE_TRAPDOOR, PYRITE_DOOR);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LARGE_PYRITE_PLATE, 2)
                .define('#', PYRITE_PLATE)
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(PYRITE_PLATE), has(PYRITE_PLATE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, LARGE_PYRITE_PLATE, PYRITE_PLATE);

            //Reinforced Glass
        reinforcedGlassSet(output, REINFORCED_GLASS, REINFORCED_GLASS_SLAB, Items.GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.WHITE), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.WHITE), Items.WHITE_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.LIGHT_GRAY), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.LIGHT_GRAY), Items.LIGHT_GRAY_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.GRAY), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.GRAY), Items.GRAY_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.BLACK), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.BLACK), Items.BLACK_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.BROWN), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.BROWN), Items.BROWN_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.RED), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.RED), Items.RED_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.ORANGE), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.ORANGE), Items.ORANGE_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.YELLOW), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.YELLOW), Items.YELLOW_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.LIME), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.LIME), Items.LIME_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.GREEN), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.GREEN), Items.GREEN_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.CYAN), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.CYAN), Items.CYAN_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.LIGHT_BLUE), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.LIGHT_BLUE), Items.LIGHT_BLUE_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.BLUE), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.BLUE), Items.BLUE_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.PURPLE), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.PURPLE), Items.PURPLE_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.MAGENTA), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.MAGENTA), Items.MAGENTA_STAINED_GLASS);
        reinforcedGlassSet(output, DYED_REINFORCED_GLASS.get(DyeColor.PINK), DYED_REINFORCED_GLASS_SLAB.get(DyeColor.PINK), Items.PINK_STAINED_GLASS);


        //Asphalt
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ASPHALT, 8)
                .requires(BAItems.TAR).requires(Items.COBBLESTONE, 8)
                .unlockedBy(getHasName(BAItems.TAR), has(BAItems.TAR)).save(output);
        stoneSlab(output, ASPHALT_SLAB, ASPHALT);
        stoneStair(output, ASPHALT_STAIRS, ASPHALT);


            //Tarmac
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, TARMAC, 8)
                .requires(BAItems.TAR).requires(Items.COBBLED_DEEPSLATE, 8)
                .unlockedBy(getHasName(BAItems.TAR), has(BAItems.TAR)).save(output);
        stoneSlab(output, TARMAC_SLAB, TARMAC);
        stoneStair(output, TARMAC_STAIRS, TARMAC);


            //Tarred Paper
        twoAndTwo(output, TARRED_PAPER, BAItems.TAR, Items.PAPER);
        slab(output, TARRED_PAPER_SLAB, TARRED_PAPER);
        stair(output, TARRED_PAPER_STAIRS, TARRED_PAPER);


        //Reinforced Iron
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, REINFORCED_IRON, 4)
                .define('I', Items.IRON_INGOT).define('N', Items.IRON_NUGGET)
                .pattern("NNN")
                .pattern("NIN")
                .pattern("NNN")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(output);
        stoneSlab(output, REINFORCED_IRON_SLAB, REINFORCED_IRON);
        stoneStair(output, REINFORCED_IRON_STAIRS, REINFORCED_IRON);
        chiseledStoneAlt(output, CHISELED_REINFORCED_IRON, REINFORCED_IRON_SLAB, REINFORCED_IRON);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, PIPE, 4)
                .define('#', REINFORCED_IRON)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy(getHasName(REINFORCED_IRON), has(REINFORCED_IRON)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, VENT)
                .define('#', REINFORCED_IRON)
                .pattern("##")
                .unlockedBy(getHasName(REINFORCED_IRON), has(REINFORCED_IRON)).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, HATCH)
                .requires(REINFORCED_IRON).requires(Items.IRON_TRAPDOOR)
                .unlockedBy(getHasName(REINFORCED_IRON), has(REINFORCED_IRON)).save(output);


            //Lights
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LIGHT_BULB)
                .define('T', Items.TORCH).define('N', Items.IRON_NUGGET).define('G', Items.GLASS)
                .pattern(" G ")
                .pattern("NTN")
                .unlockedBy(getHasName(Items.TORCH), has(Items.TORCH)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SOUL_LIGHT_BULB)
                .define('T', Items.SOUL_TORCH).define('N', Items.IRON_NUGGET).define('G', Items.GLASS)
                .pattern(" G ")
                .pattern("NTN")
                .unlockedBy(getHasName(Items.SOUL_TORCH), has(Items.SOUL_TORCH)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, REDSTONE_LIGHT_BULB)
                .define('T', Items.REDSTONE_TORCH).define('N', Items.IRON_NUGGET).define('G', Items.GLASS)
                .pattern(" G ")
                .pattern("NTN")
                .unlockedBy(getHasName(Items.REDSTONE_TORCH), has(Items.REDSTONE_TORCH)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, COLD_FLUORESCENT_TUBE, 4)
                .define('N', Items.IRON_NUGGET).define('G', Items.GLASS).define('C', Items.AMETHYST_SHARD)
                .pattern(" N ")
                .pattern("GCG")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, COOL_FLUORESCENT_TUBE, 4)
                .define('N', Items.IRON_NUGGET).define('G', Items.GLASS).define('C', Items.LAPIS_LAZULI)
                .pattern(" N ")
                .pattern("GCG")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FLUORESCENT_TUBE, 4)
                .define('N', Items.IRON_NUGGET).define('G', Items.GLASS).define('C', Items.QUARTZ)
                .pattern(" N ")
                .pattern("GCG")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, WARM_FLUORESCENT_TUBE, 4)
                .define('N', Items.IRON_NUGGET).define('G', Items.GLASS).define('C', Items.GLOWSTONE_DUST)
                .pattern(" N ")
                .pattern("GCG")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HOT_FLUORESCENT_TUBE, 4)
                .define('N', Items.IRON_NUGGET).define('G', Items.GLASS).define('C', Items.REDSTONE)
                .pattern(" N ")
                .pattern("GCG")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ABERRANT_FLUORESCENT_TUBE, 4)
                .define('N', Items.IRON_NUGGET).define('G', Items.GLASS).define('C', Items.EMERALD)
                .pattern(" N ")
                .pattern("GCG")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).save(output);

            //Misc
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BAItems.TAR, 8)
                .requires(Ingredient.of(Items.HONEYCOMB, Items.SLIME_BALL))
                .requires(Ingredient.of(Items.COAL, Items.CHARCOAL), 2)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BAItems.TAR, 4)
                .requires(TAR_BLOCK)
                .unlockedBy(getHasName(TAR_BLOCK), has(TAR_BLOCK))
                .save(output, "tar_from_tar_block");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TAR_BLOCK)
                .define('#', BAItems.TAR)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(BAItems.TAR), has(BAItems.TAR)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, WHEEL)
                .define('#', Items.DRIED_KELP_BLOCK).define('S', Items.STICK).define('P', ItemTags.PLANKS)
                .pattern("S#S")
                .pattern("P P")
                .unlockedBy(getHasName(Items.DRIED_KELP_BLOCK), has(Items.DRIED_KELP_BLOCK)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BUMPER, 4)
                .define('#', Items.DRIED_KELP_BLOCK).define('X', REINFORCED_IRON)
                .pattern("##")
                .pattern("XX")
                .unlockedBy(getHasName(Items.DRIED_KELP_BLOCK), has(Items.DRIED_KELP_BLOCK)).save(output);
    }

    protected static void standardSet(RecipeOutput recipeOutput, ItemLike plate, ItemLike chiseled, ItemLike cut, ItemLike cutStair, ItemLike cutSlab, ItemLike trapdoor, ItemLike door) {
        chiseledStone(recipeOutput, chiseled, cutSlab, cut, plate);
        cutStone(recipeOutput, cut, plate);
        cutStoneSlab(recipeOutput, cutSlab, cut, plate);
        cutStoneStair(recipeOutput, cutStair, cut, plate);
        door(recipeOutput, door, plate);
        trapdoor(recipeOutput, trapdoor, plate);
    }

    protected static void reinforcedGlassSet (RecipeOutput recipeOutput, ItemLike reinforcedGlass, ItemLike reinforcedGlassSlab, ItemLike glassMaterial) {
        twoAndTwo(recipeOutput, reinforcedGlass, glassMaterial, REINFORCED_IRON);
        stoneSlab(recipeOutput, reinforcedGlassSlab, reinforcedGlass);
    }

    protected static void cutStoneSlab(RecipeOutput recipeOutput, ItemLike slab, ItemLike cutMaterial, ItemLike baseMaterial) {
        stoneSlab(recipeOutput, slab, cutMaterial);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, baseMaterial, 2);
    }
    protected static void cutStoneStair(RecipeOutput recipeOutput, ItemLike stair, ItemLike cutMaterial, ItemLike baseMaterial) {
        stoneStair(recipeOutput, stair, cutMaterial);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, stair, baseMaterial);
    }
    protected static void chiseledStone(RecipeOutput recipeOutput, ItemLike chiseled, ItemLike slabMaterial, ItemLike cutMaterial, ItemLike baseMaterial) {
        chiseled(recipeOutput, chiseled, slabMaterial);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, chiseled, cutMaterial);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, chiseled, baseMaterial);
    }

    protected static void chiseledStoneAlt(RecipeOutput recipeOutput, ItemLike chiseled, ItemLike slabMaterial, ItemLike baseMaterial) {
        chiseled(recipeOutput, chiseled, slabMaterial);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, chiseled, baseMaterial);
    }

    protected static void stoneSlab(RecipeOutput recipeOutput, ItemLike slab, ItemLike material) {
        slab(recipeOutput, slab, material);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, material, 2);
    }
    protected static void stoneStair(RecipeOutput recipeOutput, ItemLike stair, ItemLike material) {
        stair(recipeOutput, stair, material);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, stair, material);
    }
    protected static void cutStone(RecipeOutput recipeOutput, ItemLike cut, ItemLike material) {
        cut(recipeOutput, cut, material);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, cut, material);
    }

    protected static void slab(RecipeOutput recipeOutput, ItemLike slab, ItemLike material) {
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, material);
    }
    protected static void stair(RecipeOutput recipeOutput, ItemLike stair, ItemLike material) {
        stairBuilder(stair, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(recipeOutput);
    }
    protected static void door(RecipeOutput recipeOutput, ItemLike door, ItemLike material) {
        doorBuilder(door, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(recipeOutput);
    }
    protected static void trapdoor(RecipeOutput recipeOutput, ItemLike trapdoor, ItemLike material) {
        trapdoorBuilder(trapdoor, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(recipeOutput);
    }
    protected static void chiseled(RecipeOutput recipeOutput, ItemLike chiseled, ItemLike material) {
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, chiseled, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(recipeOutput);
    }
    protected static void cut(RecipeOutput recipeOutput, ItemLike cut, ItemLike material) {
        cutBuilder(RecipeCategory.BUILDING_BLOCKS, cut, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(recipeOutput);
    }
    protected static void twoAndTwo(RecipeOutput recipeOutput, ItemLike twoAndTwo, ItemLike material, ItemLike material2) {
        twoAndTwoBuilder(twoAndTwo, Ingredient.of(material), Ingredient.of(material2)).unlockedBy(getHasName(material), has(material)).save(recipeOutput);
    }


    protected static RecipeBuilder twoAndTwoBuilder(ItemLike twoAndTwo, Ingredient material, Ingredient material2) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, twoAndTwo, 4).define('#', material).define('X', material2).pattern("X#").pattern("#X");
    }

}
