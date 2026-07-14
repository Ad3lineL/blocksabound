package com.addyberry.blocksabound.mixin;

import com.addyberry.blocksabound.common.blocks.IronPipeBlock;
import com.addyberry.blocksabound.common.blocks.IronPipeJunctionBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.checkerframework.common.aliasing.qual.Unique;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Player.class)
public abstract class PipeCrawlingMixin extends Entity {

    private PipeCrawlingMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "updatePlayerPose", at = @At("HEAD"), cancellable = true)
    private void updatePlayerPose(CallbackInfo ci) {
        BlockState state = this.getInBlockState();
        if (!this.isSpectator() && (state.getBlock() instanceof IronPipeBlock || state.getBlock() instanceof IronPipeJunctionBlock)) {
            ci.cancel();
            this.setPose(Pose.SWIMMING);
        }
    }
}