package com.addyberry.blocksabound.common.registry;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlocksAboundBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BlocksAbound.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(BlocksAbound.MODID);

    //Tawny Plate
    public static BlockBehaviour.Properties getTawnyProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)
                .strength(1.5F, 3.0F)
                .sound(SoundType.METAL);
    }

    public static final DeferredBlock<Block> TAWNY_PLATE = registerBlock("tawny_plate", () -> new Block(getTawnyProperties()));


    public static <T extends Block> DeferredBlock<T> registerBlock(String name, final Supplier<T> supplier) {
        DeferredBlock<T> block = BLOCKS.register(name, supplier);
        BlocksAboundBlocks.BLOCK_ITEMS.registerSimpleBlockItem(name, block);
        return block;
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
