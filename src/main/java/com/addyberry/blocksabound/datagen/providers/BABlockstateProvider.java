package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.addyberry.blocksabound.common.registry.BABlocks.*;

public class BABlockstateProvider extends BlockStateProvider {
        public BABlockstateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
            super(output, BlocksAbound.MODID, exFileHelper);
        }

    @Override
    protected void registerStatesAndModels() {

        this.simpleBlockWithItem(TAWNY_PLATE.get(), cubeAll(TAWNY_PLATE.get())
        );
    }
}
