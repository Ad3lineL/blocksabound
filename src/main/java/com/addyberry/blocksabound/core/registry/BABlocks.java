package com.addyberry.blocksabound.common.registry;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
