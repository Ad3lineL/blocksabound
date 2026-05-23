package com.addyberry.blocksabound.common.registry;

import com.addyberry.blocksabound.BlocksAbound;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlocksAboundItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlocksAbound.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
