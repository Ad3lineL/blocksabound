package com.addyberry.blocksabound.core.registry;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
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
            event.accept(BABlocks.CUT_TAWNY_PLATE);
            event.accept(BABlocks.CUT_TAWNY_PLATE_STAIRS);
            event.accept(BABlocks.CUT_TAWNY_PLATE_SLAB);
            event.accept(BABlocks.TAWNY_DOOR);
            event.accept(BABlocks.TAWNY_TRAPDOOR);
            event.accept(BABlocks.TAWNY_HULL_PLATE);

            event.accept(BABlocks.PYRITE_PLATE);
            event.accept(BABlocks.CHISELED_PYRITE_PLATE);
            event.accept(BABlocks.CUT_PYRITE_PLATE);
            event.accept(BABlocks.CUT_PYRITE_PLATE_STAIRS);
            event.accept(BABlocks.CUT_PYRITE_PLATE_SLAB);
            event.accept(BABlocks.PYRITE_DOOR);
            event.accept(BABlocks.PYRITE_TRAPDOOR);
            event.accept(BAItems.LARGE_PYRITE_PLATE);

            event.accept(BAItems.PIPE);
            event.accept(BABlocks.REINFORCED_IRON);

            event.accept(BABlocks.ASPHALT);
            event.accept(BABlocks.ASPHALT_STAIRS);
            event.accept(BABlocks.ASPHALT_SLAB);

            event.accept(BABlocks.TARMAC);
            event.accept(BABlocks.TARMAC_STAIRS);
            event.accept(BABlocks.TARMAC_SLAB);


        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.insertAfter(Items.SOUL_LANTERN.getDefaultInstance(), BABlocks.REDSTONE_LIGHT_BULB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.SOUL_LANTERN.getDefaultInstance(), BABlocks.SOUL_LIGHT_BULB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.SOUL_LANTERN.getDefaultInstance(), BABlocks.LIGHT_BULB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(BABlocks.COLD_FLUORESCENT_TUBE);
            event.accept(BABlocks.COOL_FLUORESCENT_TUBE);
            event.accept(BABlocks.FLUORESCENT_TUBE);
            event.accept(BABlocks.WARM_FLUORESCENT_TUBE);
            event.accept(BABlocks.HOT_FLUORESCENT_TUBE);
            event.accept(BABlocks.ABERRANT_FLUORESCENT_TUBE);
            event.accept(BABlocks.HATCH);
            event.accept(BABlocks.VENT);
            event.accept(BAItems.PIPE);
            event.accept(BABlocks.REINFORCED_IRON);
        }
        if(event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.insertAfter(Items.REDSTONE_LAMP.getDefaultInstance(), BABlocks.REDSTONE_LIGHT_BULB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.REDSTONE_LAMP.getDefaultInstance(), BABlocks.SOUL_LIGHT_BULB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.REDSTONE_LAMP.getDefaultInstance(), BABlocks.LIGHT_BULB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(Items.CLAY_BALL.getDefaultInstance(), BAItems.TAR.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
