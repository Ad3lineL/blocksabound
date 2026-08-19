package com.addyberry.blocksabound.datagen.providers;

import com.addyberry.blocksabound.common.block.IronPipeJunctionBlock;
import com.addyberry.blocksabound.common.block.ReinforcedGlassBlock;
import com.addyberry.blocksabound.common.block.ReinforcedGlassSlabBlock;
import com.addyberry.blocksabound.core.registry.BABlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class BALootTableProvider extends BlockLootSubProvider {
    public BALootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BABlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    @Override
    protected void generate() {
        for (Block block : this.getKnownBlocks()) {
            if (block instanceof ReinforcedGlassBlock) {
                dropWhenSilkTouch(block);
            } else if (block instanceof ReinforcedGlassSlabBlock) {
                add(block, createSilkTouchOnlySlabItemTable(block));
            } else if (block instanceof DoorBlock) {
                add(block, createDoorTable(block));
            } else if (block instanceof IronPipeJunctionBlock) {
                dropOther(block, BABlocks.PIPE);
            } else if (block instanceof SlabBlock) {
                add(block, createSlabItemTable(block));
            } else dropSelf(block);
        }
    }

    protected LootTable.Builder createSilkTouchOnlySlabItemTable(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().when(this.hasSilkTouch()).setRolls(ConstantValue.exactly(1.0F)).add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))));
    }

}
