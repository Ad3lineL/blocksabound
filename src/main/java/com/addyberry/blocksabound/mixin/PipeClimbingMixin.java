package com.addyberry.blocksabound.mixin;

import com.addyberry.blocksabound.common.block.IronPipeBlock;
import com.addyberry.blocksabound.common.block.IronPipeJunctionBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class PipeClimbingMixin extends Entity {

    @Shadow
    private Optional<BlockPos> lastClimbablePos;

    public PipeClimbingMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "onClimbable", at = @At("TAIL"), cancellable = true)
    public void onClimbable(CallbackInfoReturnable<Boolean> cir) {
        BlockPos pos = this.blockPosition();
        BlockState state = this.getInBlockState();

        if (state.getBlock() instanceof IronPipeBlock || state.getBlock() instanceof IronPipeJunctionBlock) {
            this.lastClimbablePos = Optional.of(pos);
            cir.setReturnValue(true);
        }
    }
}
