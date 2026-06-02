package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.BlocksAbound;
import com.addyberry.blocksabound.core.registry.BAItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class BAItemModelProvider extends ItemModelProvider {
    public BAItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BlocksAbound.MODID, exFileHelper);
    }
    @Override
    protected void registerModels() {

        this.basicItem(BAItems.TAR);

    }

    public ItemModelBuilder basicItem(DeferredItem<? extends Item> item) {
        return super.basicItem(item.get());
    }
}
