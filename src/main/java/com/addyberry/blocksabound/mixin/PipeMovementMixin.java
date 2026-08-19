package com.addyberry.blocksabound.mixin;

import com.addyberry.blocksabound.common.block.IronPipeBlock;
import com.addyberry.blocksabound.common.block.IronPipeJunctionBlock;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class PipeMovementMixin {

    @Shadow
    protected boolean jumping;

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void blocksabound$pipeTravel(Vec3 travelVector, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (!(self instanceof Player player) || !player.isLocalPlayer() || player.isSpectator()) {
            return;
        }
        BlockState inState = self.getInBlockState();
        if (!blocksabound$isPipe(inState)) {
            return;
        }

        float yawRad = self.getYRot() * ((float) Math.PI / 180.0F);
        Vec3 look = self.getLookAngle();
        Vec3 lateral = new Vec3(Mth.cos(yawRad), 0.0D, Mth.sin(yawRad));
        double forward = travelVector.z;
        double strafe = travelVector.x;
        double vertical = (this.jumping ? 1.0D : 0.0D) - (self.isShiftKeyDown() ? 1.0D : 0.0D);

        double wishX = look.x * forward + lateral.x * strafe;
        double wishZ = look.z * forward + lateral.z * strafe;
        double wishY = look.y * forward + vertical;

        Vec3 wish = new Vec3(wishX, wishY, wishZ);
        if (wish.lengthSqr() > 1.0E-6D) {
            wish = wish.normalize().scale(0.06D);
        } else {
            wish = Vec3.ZERO;
        }

        Vec3 delta = self.getDeltaMovement().add(wish);
        if (!blocksabound$hasVerticalOpening(inState)) {
            delta = delta.add(0.0D, -0.08D, 0.0D);
        }

        self.resetFallDistance();
        self.setDeltaMovement(delta);
        self.move(MoverType.SELF, self.getDeltaMovement());
        self.setDeltaMovement(self.getDeltaMovement().scale(0.4D));
        self.calculateEntityAnimation(false);
        ci.cancel();
    }

    private static boolean blocksabound$isPipe(BlockState state) {
        return state.getBlock() instanceof IronPipeBlock || state.getBlock() instanceof IronPipeJunctionBlock;
    }

    private static boolean blocksabound$hasVerticalOpening(BlockState state) {
        if (state.getBlock() instanceof IronPipeBlock) {
            return state.getValue(IronPipeBlock.AXIS) == Direction.Axis.Y;
        }
        if (state.getBlock() instanceof IronPipeJunctionBlock) {
            return state.getValue(IronPipeJunctionBlock.UP) || state.getValue(IronPipeJunctionBlock.DOWN);
        }
        return false;
    }
}
