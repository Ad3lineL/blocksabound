package com.addyberry.blocksabound.common.registry;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlocksAboundCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlocksAbound.MODID);




    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
