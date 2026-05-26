package com.addyberry.blocksabound.core.registry;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = BlocksAbound.MODID)
public class BACreativeModeTabs {
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(BABlocks.TAWNY_PLATE);
            event.accept(BABlocks.CHISELED_TAWNY_PLATE);
            //event.accept(BABlocks.TAWNY_GRATE);
            event.accept(BABlocks.CUT_TAWNY_PLATE);
            event.accept(BABlocks.CUT_TAWNY_PLATE_STAIRS);
            event.accept(BABlocks.CUT_TAWNY_PLATE_SLAB);
            event.accept(BABlocks.TAWNY_TRAPDOOR);
            event.accept(BABlocks.TAWNY_HULL_PLATE);

            event.accept(BABlocks.LIGHT_BULB);
            event.accept(BABlocks.SOUL_LIGHT_BULB);
            event.accept(BABlocks.REDSTONE_LIGHT_BULB);

        }
    }
}
