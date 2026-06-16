package com.addyberry.blocksabound.mixin;

import com.addyberry.blocksabound.common.blocks.IronPipeBlock;
import com.addyberry.blocksabound.common.blocks.IronPipeJunctionBlock;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class PipeStepSoundMixin {

    @Shadow
    private Level level;

    @WrapOperation(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;vibrationAndSoundEffectsFromBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;ZZLnet/minecraft/world/phys/Vec3;)Z", ordinal = 0))
    private boolean blocksabound$pipeStepSound(Entity instance, BlockPos pos, BlockState state, boolean playStepSound, boolean broadcastGameEvent, Vec3 entityPos, Operation<Boolean> original) {
        if (playStepSound) {
            BlockState inState = instance.getInBlockState();
            boolean inPipe = inState.getBlock() instanceof IronPipeBlock || inState.getBlock() instanceof IronPipeJunctionBlock;
            if (inPipe && !instance.isSwimming() && instance.getPose() == Pose.SWIMMING) {
                SoundType soundType = inState.getSoundType(instance.level(), instance.blockPosition(), instance);
                float volume = soundType.getVolume() * 2;
                float pitch = soundType.getPitch() * 0.7F;
                instance.playSound(Blocks.NETHERITE_BLOCK.defaultBlockState().getSoundType(level, pos, instance).getStepSound(), volume, pitch);
                return true;
            }
        }
        return original.call(instance, pos, state, playStepSound, broadcastGameEvent, entityPos);
    }

    @Inject(method = "nextStep", at = @At("HEAD"), cancellable = true)
    private void blocksabound$pipeStepFrequency(CallbackInfoReturnable<Float> cir) {
        Entity instance = (Entity) (Object) this;
        BlockState inState = instance.getInBlockState();
        boolean inPipe = inState.getBlock() instanceof IronPipeBlock || inState.getBlock() instanceof IronPipeJunctionBlock;
        if (inPipe && instance.getPose() == Pose.SWIMMING) {
            cir.setReturnValue(((int) (instance.moveDist * 2.0F) + 1) / 2.0F);
        }
    }

    @Inject(method = "isStateClimbable", at = @At("HEAD"), cancellable = true)
    private void blocksabound$pipeClimbableForSteps(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.getBlock() instanceof IronPipeBlock || state.getBlock() instanceof IronPipeJunctionBlock) {
            cir.setReturnValue(true);
        }
    }
}
