package com.addyberry.blocksabound.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HalfTransparentBlock.class)
public class GlassRenderingMixin {

    @ModifyReturnValue(method = "skipRendering", at = @At("RETURN"))
    private boolean blocksabound$mergeGlass(boolean original, BlockState state, BlockState adjacent, Direction direction) {
        return original || state.is(Tags.Blocks.GLASS_BLOCKS) && adjacent.is(Tags.Blocks.GLASS_BLOCKS);
    }
}
