package com.addyberry.blocksabound.core.registry;

import com.addyberry.blocksabound.BlocksAbound;
import com.addyberry.blocksabound.common.block.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class BABlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BlocksAbound.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(BlocksAbound.MODID);

        //Tawny Plate
    public static final BlockSetType TAWNY_BLOCK_SET_TYPE = BlockSetType.register(new BlockSetType("tawny", true, true, false, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.METAL, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
    public static BlockBehaviour.Properties getTawnyProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                .strength(1F, 4.0F)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops();
    }
    public static final DeferredBlock<Block> TAWNY_PLATE = registerBlock("tawny_plate", () -> new Block(getTawnyProperties()));
    public static final DeferredBlock<Block> CHISELED_TAWNY_PLATE = registerBlock("chiseled_tawny_plate", () -> new Block(getTawnyProperties()));
    public static final DeferredBlock<Block> CUT_TAWNY_PLATE = registerBlock("cut_tawny_plate", () -> new Block(getTawnyProperties()));
    public static final DeferredBlock<Block> CUT_TAWNY_PLATE_STAIRS = registerBlock("cut_tawny_plate_stairs", () -> new StairBlock(CUT_TAWNY_PLATE.get().defaultBlockState(), getTawnyProperties()));
    public static final DeferredBlock<Block> CUT_TAWNY_PLATE_SLAB = registerBlock("cut_tawny_plate_slab", () -> new SlabBlock(getTawnyProperties()));
    public static final DeferredBlock<Block> TAWNY_DOOR = registerBlock("tawny_door", () -> new DoorBlock(TAWNY_BLOCK_SET_TYPE, getTawnyProperties().noOcclusion()));
    public static final DeferredBlock<Block> TAWNY_TRAPDOOR = registerBlock("tawny_trapdoor", () -> new TrapDoorBlock(TAWNY_BLOCK_SET_TYPE, getTawnyProperties().noOcclusion()));
    public static final DeferredBlock<Block> TAWNY_HULL_PLATE = registerBlock("tawny_hull_plate", () -> new HullPlateBlock(getTawnyProperties()));


        //Pyrite Plate
    public static final BlockSetType PYRITE_BLOCK_SET_TYPE = BlockSetType.register(new BlockSetType("pyrite", true, true, false, BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.METAL, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));
    public static BlockBehaviour.Properties getPyriteProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)
                .strength(1F, 4.0F)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops();
    }
    public static final DeferredBlock<Block> PYRITE_PLATE = registerBlock("pyrite_plate", () -> new Block(getPyriteProperties()));
    public static final DeferredBlock<Block> CHISELED_PYRITE_PLATE = registerBlock("chiseled_pyrite_plate", () -> new Block(getPyriteProperties()));
    public static final DeferredBlock<Block> CUT_PYRITE_PLATE = registerBlock("cut_pyrite_plate", () -> new Block(getPyriteProperties()));
    public static final DeferredBlock<Block> CUT_PYRITE_PLATE_STAIRS = registerBlock("cut_pyrite_plate_stairs", () -> new StairBlock(CUT_PYRITE_PLATE.get().defaultBlockState(), getPyriteProperties()));
    public static final DeferredBlock<Block> CUT_PYRITE_PLATE_SLAB = registerBlock("cut_pyrite_plate_slab", () -> new SlabBlock(getPyriteProperties()));
    public static final DeferredBlock<Block> PYRITE_DOOR = registerBlock("pyrite_door", () -> new DoorBlock(PYRITE_BLOCK_SET_TYPE, getPyriteProperties().noOcclusion()));
    public static final DeferredBlock<Block> PYRITE_TRAPDOOR = registerBlock("pyrite_trapdoor", () -> new TrapDoorBlock(PYRITE_BLOCK_SET_TYPE, getPyriteProperties().noOcclusion()));
    public static final DeferredBlock<Block> LARGE_PYRITE_PLATE = registerBlockNoItem("large_pyrite_plate", () -> new LargePlateBlock(getPyriteProperties()));


        //Asphalt
    public static BlockBehaviour.Properties getAsphaltProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)
                .mapColor(MapColor.COLOR_GRAY)
                .sound(SoundType.NETHERRACK)
                .requiresCorrectToolForDrops();
    }
    public static final DeferredBlock<Block> ASPHALT = registerBlock("asphalt", () -> new Block(getAsphaltProperties()));
    public static final DeferredBlock<Block> ASPHALT_STAIRS = registerBlock("asphalt_stairs", () -> new StairBlock(ASPHALT.get().defaultBlockState(), getAsphaltProperties()));
    public static final DeferredBlock<Block> ASPHALT_SLAB = registerBlock("asphalt_slab", () -> new SlabBlock(getAsphaltProperties()));


        //Tarmac
    public static BlockBehaviour.Properties getTarmacProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)
                .mapColor(MapColor.COLOR_BLACK)
                .sound(SoundType.NETHERRACK)
                .requiresCorrectToolForDrops();
    }
    public static final DeferredBlock<Block> TARMAC = registerBlock("tarmac", () -> new Block(getTarmacProperties()));
    public static final DeferredBlock<Block> TARMAC_STAIRS = registerBlock("tarmac_stairs", () -> new StairBlock(TARMAC.get().defaultBlockState(), getTarmacProperties()));
    public static final DeferredBlock<Block> TARMAC_SLAB = registerBlock("tarmac_slab", () -> new SlabBlock(getTarmacProperties()));


        //Tarred Paper
    public static BlockBehaviour.Properties getTarredPaperProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)
                .mapColor(MapColor.COLOR_BLACK);
    }
    public static final DeferredBlock<Block> TARRED_PAPER = registerBlock("tarred_paper", () -> new Block(getTarredPaperProperties()));
    public static final DeferredBlock<Block> TARRED_PAPER_STAIRS = registerBlock("tarred_paper_stairs", () -> new StairBlock(TARRED_PAPER.get().defaultBlockState(), getTarredPaperProperties()));
    public static final DeferredBlock<Block> TARRED_PAPER_SLAB = registerBlock("tarred_paper_slab", () -> new SlabBlock(getTarredPaperProperties()));


        //Light Bulb
    public static final DeferredBlock<Block> LIGHT_BULB = registerBlock("light_bulb", () -> new LightBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 15 : 0))); //15
    public static final DeferredBlock<Block> SOUL_LIGHT_BULB = registerBlock("soul_light_bulb", () -> new LightBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 12 : 0)));
    public static final DeferredBlock<Block> REDSTONE_LIGHT_BULB = registerBlock("redstone_light_bulb", () -> new LightBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 9 : 0)));
    public static final DeferredBlock<Block> COPPER_LIGHT_BULB = registerBlock("copper_light_bulb", () -> new LightBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 13 : 0)));


        //Fluorescent Tube
    public static final DeferredBlock<Block> FLUORESCENT_TUBE = registerBlock("fluorescent_tube", () -> new FluorescentTubeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).noOcclusion().lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 13 : 0)));
    public static final DeferredBlock<Block> COLD_FLUORESCENT_TUBE = registerBlock("cold_fluorescent_tube", () -> new FluorescentTubeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).noOcclusion().lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 13 : 0)));
    public static final DeferredBlock<Block> COOL_FLUORESCENT_TUBE = registerBlock("cool_fluorescent_tube", () -> new FluorescentTubeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).noOcclusion().lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 13 : 0)));
    public static final DeferredBlock<Block> WARM_FLUORESCENT_TUBE = registerBlock("warm_fluorescent_tube", () -> new FluorescentTubeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).noOcclusion().lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 13 : 0)));
    public static final DeferredBlock<Block> HOT_FLUORESCENT_TUBE = registerBlock("hot_fluorescent_tube", () -> new FluorescentTubeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).noOcclusion().lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 13 : 0)));
    public static final DeferredBlock<Block> ABERRANT_FLUORESCENT_TUBE = registerBlock("aberrant_fluorescent_tube", () -> new FluorescentTubeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_LAMP).noOcclusion().lightLevel(state -> state.getValue(LightBulbBlock.LIT) ? 13 : 0)));


        //Mechanical Iron
    public static BlockBehaviour.Properties getReinforcedIronProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GRAY)
                .strength(1F, 6.0F)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops();
    }
    public static final DeferredBlock<Block> REINFORCED_IRON = registerBlock("reinforced_iron", () -> new Block(getReinforcedIronProperties()));
    public static final DeferredBlock<Block> REINFORCED_IRON_STAIRS = registerBlock("reinforced_iron_stairs", () -> new StairBlock(REINFORCED_IRON.get().defaultBlockState(), getReinforcedIronProperties()));
    public static final DeferredBlock<Block> REINFORCED_IRON_SLAB = registerBlock("reinforced_iron_slab", () -> new SlabBlock(getReinforcedIronProperties()));
    public static final DeferredBlock<Block> CHISELED_REINFORCED_IRON = registerBlock("chiseled_reinforced_iron", () -> new Block(getReinforcedIronProperties()));
    public static final DeferredBlock<Block> REINFORCED_IRON_WALL = registerBlock("reinforced_iron_wall", () -> new WallBlock(getReinforcedIronProperties()));

    public static final DeferredBlock<Block> HATCH = registerBlock("hatch", () -> new HatchBlock(getReinforcedIronProperties().noOcclusion()));
    public static final DeferredBlock<Block> VENT = registerBlock("vent", () -> new VentBlock(getReinforcedIronProperties().noOcclusion()));
    public static final DeferredBlock<Block> PIPE = registerBlock("pipe", () -> new IronPipeBlock(getReinforcedIronProperties().noOcclusion()));
    public static final DeferredBlock<Block> PIPE_JUNCTION = registerBlockNoItem("pipe_junction", () -> new IronPipeJunctionBlock(getReinforcedIronProperties().noOcclusion()));


        //Reinforced Glass
    public static BlockBehaviour.Properties getReinforcedGlassProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                .strength(1F, 6.0F)
                .requiresCorrectToolForDrops();
    }
    public static final DeferredBlock<Block> REINFORCED_GLASS = registerBlock("reinforced_glass", () -> new ReinforcedGlassBlock(getReinforcedGlassProperties()));

    public static final Map<DyeColor, DeferredBlock<Block>> DYED_REINFORCED_GLASS = new EnumMap<>(DyeColor.class);
    private static DeferredBlock<Block> registerGlass(DyeColor color) {
        DeferredBlock<Block> block = registerBlock(color.getName() + "_reinforced_glass",
                () -> new ReinforcedGlassBlock(getReinforcedGlassProperties()));
        DYED_REINFORCED_GLASS.put(color, block);
        return block;
    }

    public static final DeferredBlock<Block> REINFORCED_GLASS_SLAB = registerBlock("reinforced_glass_slab", () -> new ReinforcedGlassSlabBlock(getReinforcedGlassProperties()));

    public static final Map<DyeColor, DeferredBlock<Block>> DYED_REINFORCED_GLASS_SLAB = new EnumMap<>(DyeColor.class);
    private static DeferredBlock<Block> registerGlassSlab(DyeColor color) {
        DeferredBlock<Block> block = registerBlock(color.getName() + "_reinforced_glass_slab",
                () -> new ReinforcedGlassSlabBlock(getReinforcedGlassProperties()));
        DYED_REINFORCED_GLASS_SLAB.put(color, block);
        return block;
    }

        //Misc
    public static final DeferredBlock<Block> TAR_BLOCK = registerBlock("tar_block", () -> new TarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).speedFactor(0.6F).jumpFactor(0.8F).mapColor(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<Block> WHEEL = registerBlock("wheel", () -> new WheelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion().jumpFactor(1.1F).sound(SoundType.WOOD).mapColor(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<Block> BUMPER = registerBlock("bumper", () -> new BumperBlock(getReinforcedIronProperties().noOcclusion().jumpFactor(1.1F).mapColor(MapColor.COLOR_BLACK)));


    /* TODO:
     SEALED GLASS
     IRON CABLE
     CLOCK BLOCK
     GUIDING ROD
     NAIL
     SKATES
     LOCKER
     HAZARD STRIPE
     MECHANICAL LEVER
     WIND-UP LEVER
     SHOWERHEAD
     FUME BLOCK

     Sets:
     BLUE SET
     RED SET

     Insane maybes:
     CARGO BAY
     TRAFFIC CONE
     SANDBAG
     MECHANICAL HORN
     APERTURE HATCH
     METAL ENVELOPE BLOCK
     ELECTRIC COIL BLOCK
     ARROW SIGNS
     DETECTOR PIPE
     WINDOWED PIPE
     GIRDER
     GLASS PIPE???
    */




    public static <T extends Block> DeferredBlock<T> registerBlock(String name, final Supplier<T> supplier) {
        DeferredBlock<T> block = BLOCKS.register(name, supplier);
        BABlocks.BLOCK_ITEMS.registerSimpleBlockItem(name, block);
        return block;
    }

    public static <T extends Block> DeferredBlock<T> registerBlockNoItem(String name, final Supplier<T> supplier) {
        return BLOCKS.register(name, supplier);
    }

    public static void register(IEventBus eventBus) {
        for (DyeColor color : DyeColor.values()) {
            registerGlass(color);
            registerGlassSlab(color);
        }
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
