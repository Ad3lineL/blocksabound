package com.addyberry.blocksabound.core.registry;

import com.addyberry.blocksabound.BlocksAbound;
import com.addyberry.blocksabound.common.blocks.HullPlateBlock;
import com.addyberry.blocksabound.common.blocks.LightBulbBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BABlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BlocksAbound.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(BlocksAbound.MODID);

    //Tawny Plate
    public static BlockBehaviour.Properties getTawnyProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                .strength(1F, 4.0F)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops();
    }

    public static final BlockSetType TAWNY_BLOCK_SET_TYPE = BlockSetType.register(new BlockSetType("tawny", true, true, false, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.METAL, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));

    public static final DeferredBlock<Block> TAWNY_PLATE = registerBlock("tawny_plate", () -> new Block(getTawnyProperties()));
    public static final DeferredBlock<Block> CHISELED_TAWNY_PLATE = registerBlock("chiseled_tawny_plate", () -> new Block(getTawnyProperties()));
    /*public static final DeferredBlock<Block> TAWNY_GRATE = registerBlock("tawny_grate", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_GRATE)
            .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
            .strength(1F, 4.0F)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
            .noOcclusion()
    ));*/
    public static final DeferredBlock<Block> CUT_TAWNY_PLATE = registerBlock("cut_tawny_plate", () -> new Block(getTawnyProperties()));
    public static final DeferredBlock<Block> CUT_TAWNY_PLATE_STAIRS = registerBlock("cut_tawny_plate_stairs", () -> new StairBlock(CUT_TAWNY_PLATE.get().defaultBlockState(), getTawnyProperties()));
    public static final DeferredBlock<Block> CUT_TAWNY_PLATE_SLAB = registerBlock("cut_tawny_plate_slab", () -> new SlabBlock(getTawnyProperties()));
    public static final DeferredBlock<Block> TAWNY_TRAPDOOR = registerBlock("tawny_trapdoor", () -> new TrapDoorBlock(TAWNY_BLOCK_SET_TYPE, getTawnyProperties().noOcclusion()));
    public static final DeferredBlock<Block> TAWNY_HULL_PLATE = registerBlock("tawny_hull_plate", () -> new HullPlateBlock(getTawnyProperties()));

    //light bulbs
    public static final DeferredBlock<Block> LIGHT_BULB = registerBlock("light_bulb", () -> new LightBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP)));
    public static final DeferredBlock<Block> SOUL_LIGHT_BULB = registerBlock("soul_light_bulb", () -> new LightBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP)));
    public static final DeferredBlock<Block> REDSTONE_LIGHT_BULB = registerBlock("redstone_light_bulb", () -> new LightBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP)));

    //asphalt
    public static BlockBehaviour.Properties getAsphaltProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)
                .mapColor(MapColor.COLOR_GRAY)
                .sound(SoundType.NETHERRACK)
                .requiresCorrectToolForDrops();
    }
    public static final DeferredBlock<Block> ASPHALT = registerBlock("asphalt", () -> new Block(getAsphaltProperties()));
    public static <T extends Block> DeferredBlock<T> registerBlock(String name, final Supplier<T> supplier) {
        DeferredBlock<T> block = BLOCKS.register(name, supplier);
        BABlocks.BLOCK_ITEMS.registerSimpleBlockItem(name, block);
        return block;
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
