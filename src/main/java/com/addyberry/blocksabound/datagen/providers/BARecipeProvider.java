package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.core.registry.BAItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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


            //Pyrite Plate
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, PYRITE_PLATE, 4)
                .define('#', Items.COPPER_BLOCK).define('N', Items.IRON_NUGGET)
                .pattern(" N ")
                .pattern("N#N")
                .pattern(" N ")
                .unlockedBy(getHasName(Items.COPPER_BLOCK), has(Items.COPPER_BLOCK)).save(output);
        standardSet(output, PYRITE_PLATE, CHISELED_PYRITE_PLATE, CUT_PYRITE_PLATE, CUT_PYRITE_PLATE_STAIRS, CUT_PYRITE_PLATE_SLAB, PYRITE_TRAPDOOR, PYRITE_DOOR);


            //Asphalt
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ASPHALT)
                .requires(BAItems.TAR).requires(Items.COBBLESTONE, 8)
                .unlockedBy(getHasName(BAItems.TAR), has(BAItems.TAR)).save(output);
        stoneSlab(output, ASPHALT_SLAB, ASPHALT);
        stoneStair(output, ASPHALT_STAIRS, ASPHALT);


            //Tarmac
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, TARMAC)
                .requires(BAItems.TAR).requires(Items.COBBLED_DEEPSLATE, 8)
                .unlockedBy(getHasName(BAItems.TAR), has(BAItems.TAR)).save(output);
        stoneSlab(output, TARMAC_SLAB, TARMAC);
        stoneStair(output, TARMAC_STAIRS, TARMAC);


        //Reinforced Iron
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, REINFORCED_IRON)
                .define('I', Items.IRON_INGOT).define('N', Items.IRON_NUGGET)
                .pattern("NNN")
                .pattern("NIN")
                .pattern("NNN")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT)).save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, PIPE)
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
    }

    protected static void standardSet(RecipeOutput recipeOutput, ItemLike plate, ItemLike chiseled, ItemLike cut, ItemLike cutStair, ItemLike cutSlab, ItemLike trapdoor, ItemLike door) {
        chiseledStone(recipeOutput, chiseled, cutSlab, cut, plate);
        cutStone(recipeOutput, cut, plate);
        cutStoneSlab(recipeOutput, cutSlab, cut, plate);
        cutStoneStair(recipeOutput, cutStair, cut, plate);
        door(recipeOutput, door, plate);
        trapdoor(recipeOutput, trapdoor, plate);
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
}
