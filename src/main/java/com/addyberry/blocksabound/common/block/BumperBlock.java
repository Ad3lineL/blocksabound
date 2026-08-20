package com.addyberry.blocksabound.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BumperBlock extends AbstractRotatedDirectionalBlock {
    public static final VoxelShape UP_X = Block.box(0.0F, 0.0F, 4.0F, 16.0F, 8.0F, 12.0F);
    public static final VoxelShape UP_Z = Block.box(4.0F, 0.0F, 0.0F, 12.0F, 8.0F, 16.0F);
    public static final VoxelShape DOWN_X = Block.box(0.0F, 8.0F, 4.0F, 16.0F, 16.0F, 12.0F);
    public static final VoxelShape DOWN_Z = Block.box(4.0F, 8.0F, 0.0F, 12.0F, 16.0F, 16.0F);
    public static final VoxelShape EAST = Block.box(0.0F, 4.0F, 0.0F, 8.0F, 12.0F, 16.0F);
    public static final VoxelShape EAST_UP = Block.box(0.0F, 0.0F, 4.0F, 8.0F, 16.0F, 12.0F);
    public static final VoxelShape WEST = Block.box(8.0F, 4.0F, 0.0F, 16.0F, 12.0F, 16.0F);
    public static final VoxelShape WEST_UP = Block.box(8.0F, 0.0F, 4.0F, 16.0F, 16.0F, 12.0F);
    public static final VoxelShape NORTH = Block.box(0.0F, 4.0F, 8.0F, 16.0F, 12.0F, 16.0F);
    public static final VoxelShape NORTH_UP = Block.box(4.0F, 0.0F, 8.0F, 12.0F, 16.0F, 16.0F);
    public static final VoxelShape SOUTH = Block.box(0.0F, 4.0F, 0.0F, 16.0F, 12.0F, 8.0F);
    public static final VoxelShape SOUTH_UP = Block.box(4.0F, 0.0F, 0.0F, 12.0F, 16.0F, 8.0F);

    public BumperBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.UP)
                .setValue(ROTATED, false));
    }

    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    private VoxelShape getVoxelShape(BlockState state) {
        Direction direction = state.getValue(FACING);
        Boolean rotated = state.getValue(ROTATED);
        return switch (direction) {
            case UP -> rotated ? UP_Z : UP_X;
            case DOWN -> rotated ? DOWN_Z : DOWN_X;
            case EAST -> rotated ? EAST_UP : EAST;
            case WEST -> rotated ? WEST_UP : WEST;
            case NORTH -> rotated ? NORTH_UP : NORTH;
            default -> rotated ? SOUTH_UP : SOUTH;
        };
    }

    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getVoxelShape(state);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getVoxelShape(state);
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(level, state, pos, entity, fallDistance);
        } else {
            entity.causeFallDamage(fallDistance, 0.2F, level.damageSources().fall());
        }
    }

    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(level, entity);
        } else {
            this.bounceUp(entity);
        }
    }

    private void bounceUp(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        if (vec3.y < 0.0D) {
            double d0 = entity instanceof LivingEntity ? 0.9D : 0.8D;
            entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
        }
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

}
