package com.addyberry.blocksabound.mixin;

import com.addyberry.blocksabound.common.block.LargePlateBlock;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PistonStructureResolver.class)
public class LargePlateStickinessMixin {

    @Shadow
    @Final
    private Direction pushDirection;

    @WrapOperation(method = "addBlockLine", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;canStickTo(Lnet/minecraft/world/level/block/state/BlockState;)Z", ordinal = 1))
    private boolean blocksabound$onlyConnectFacingPlatesS(BlockState instance, BlockState state, Operation<Boolean> original) {
        boolean canStickTo = original.call(instance, state);
        if (canStickTo && instance.getBlock() instanceof LargePlateBlock) {
            Direction facing = instance.getValue(LargePlateBlock.FACING);
            if (pushDirection.getOpposite() != facing) return false;
        }

        return canStickTo;
    }

    @WrapOperation(method = "addBranchingBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;canStickTo(Lnet/minecraft/world/level/block/state/BlockState;)Z", ordinal = 1))
    private boolean blocksabound$onlyConnectFacingPlates(BlockState instance, BlockState state, Operation<Boolean> original, @Local(ordinal = 1) BlockPos pos, @Local Direction direction) {
        boolean canStickTo = original.call(instance, state);
        if (canStickTo && instance.getBlock() instanceof LargePlateBlock) {
            Direction facing = instance.getValue(LargePlateBlock.FACING);
            if (direction != facing) return false;
        }

        return canStickTo;
    }
}
