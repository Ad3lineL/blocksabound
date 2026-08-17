package com.addyberry.blocksabound.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IronBarsBlock.class)
public class GlassPaneRenderingMixin {

    @ModifyReturnValue(method = "skipRendering", at = @At("RETURN"))
    private boolean blocksabound$mergePanes(boolean original, BlockState state, BlockState adjacent, Direction direction) {
        if (original || !state.is(Tags.Blocks.GLASS_PANES) || !adjacent.is(Tags.Blocks.GLASS_PANES)) {
            return original;
        }
        if (!direction.getAxis().isHorizontal()) {
            return true;
        }
        final BooleanProperty near = blocksabound$side(direction);
        final BooleanProperty far = blocksabound$side(direction.getOpposite());
        return state.hasProperty(near) && adjacent.hasProperty(far) && state.getValue(near) && adjacent.getValue(far);
    }

    @Unique
    private static BooleanProperty blocksabound$side(final Direction direction) {
        return switch (direction) {
            case NORTH -> BlockStateProperties.NORTH;
            case SOUTH -> BlockStateProperties.SOUTH;
            case EAST -> BlockStateProperties.EAST;
            default -> BlockStateProperties.WEST;
        };
    }
}

