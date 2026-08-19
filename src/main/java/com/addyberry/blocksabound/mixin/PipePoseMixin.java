package com.addyberry.blocksabound.mixin;

import com.addyberry.blocksabound.common.block.IronPipeBlock;
import com.addyberry.blocksabound.common.block.IronPipeJunctionBlock;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerRenderer.class)
public abstract class PipePoseMixin {

    @ModifyExpressionValue(method = "setupRotations", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isInWater()Z"))
    private boolean blocksabound$pipePitchTilt(boolean original, AbstractClientPlayer entity) {
        if (original) {
            return true;
        }
        BlockState state = entity.getInBlockState();
        return state.getBlock() instanceof IronPipeBlock || state.getBlock() instanceof IronPipeJunctionBlock;
    }
}
