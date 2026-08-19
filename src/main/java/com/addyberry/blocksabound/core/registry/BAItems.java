package com.addyberry.blocksabound.core.registry;

import com.addyberry.blocksabound.BlocksAbound;
import com.addyberry.blocksabound.common.item.LargePlateBlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BAItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlocksAbound.MODID);


    public static final DeferredItem<Item> TAR = registerItem("tar", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LARGE_PYRITE_PLATE = registerItem("large_pyrite_plate", () -> new LargePlateBlockItem(BABlocks.LARGE_PYRITE_PLATE.get(), new Item.Properties()));


    public static <T extends Item> DeferredItem<T> registerItem(String name, final Supplier<T> supplier) {
        DeferredItem<T> item = ITEMS.register(name, supplier);
        return item;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
