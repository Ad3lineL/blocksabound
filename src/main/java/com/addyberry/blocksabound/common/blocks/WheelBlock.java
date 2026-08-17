package com.addyberry.blocksabound.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WheelBlock extends AbstractRotatedDirectionalBlock {
    public static final VoxelShape FLOOR_NORTH_SOUTH_LEFT_POST = Block.box(2.0F, 0.0F, 6.0F, 4.0F, 7.0F, 10.0F);
    public static final VoxelShape FLOOR_NORTH_SOUTH_RIGHT_POST = Block.box(12.0F, 0.0F, 6.0F, 14.0F, 7.0F, 10.0F);
    public static final VoxelShape FLOOR_NORTH_SOUTH_LEFT_PIVOT = Block.box(2.0F, 7.0F, 5.0F, 4.0F, 13.0F, 11.0F);
    public static final VoxelShape FLOOR_NORTH_SOUTH_RIGHT_PIVOT = Block.box(12.0F, 7.0F, 5.0F, 14.0F, 13.0F, 11.0F);
    public static final VoxelShape FLOOR_NORTH_SOUTH_LEFT_LEG;
    public static final VoxelShape FLOOR_NORTH_SOUTH_RIGHT_LEG;
    public static final VoxelShape FLOOR_NORTH_SOUTH_ALL_LEGS;
    public static final VoxelShape FLOOR_NORTH_SOUTH_WHEEL;
    public static final VoxelShape FLOOR_EAST_WEST_LEFT_POST;
    public static final VoxelShape FLOOR_EAST_WEST_RIGHT_POST;
    public static final VoxelShape FLOOR_EAST_WEST_LEFT_PIVOT;
    public static final VoxelShape FLOOR_EAST_WEST_RIGHT_PIVOT;
    public static final VoxelShape FLOOR_EAST_WEST_LEFT_LEG;
    public static final VoxelShape FLOOR_EAST_WEST_RIGHT_LEG;
    public static final VoxelShape FLOOR_EAST_WEST_ALL_LEGS;
    public static final VoxelShape FLOOR_EAST_WEST_WHEEL;
    public static final VoxelShape WALL_SOUTH_LEFT_POST;
    public static final VoxelShape WALL_SOUTH_RIGHT_POST;
    public static final VoxelShape WALL_SOUTH_LEFT_PIVOT;
    public static final VoxelShape WALL_SOUTH_RIGHT_PIVOT;
    public static final VoxelShape WALL_SOUTH_LEFT_LEG;
    public static final VoxelShape WALL_SOUTH_RIGHT_LEG;
    public static final VoxelShape WALL_SOUTH_ALL_LEGS;
    public static final VoxelShape WALL_SOUTH_WHEEL;
    public static final VoxelShape WALL_NORTH_LEFT_POST;
    public static final VoxelShape WALL_NORTH_RIGHT_POST;
    public static final VoxelShape WALL_NORTH_LEFT_PIVOT;
    public static final VoxelShape WALL_NORTH_RIGHT_PIVOT;
    public static final VoxelShape WALL_NORTH_LEFT_LEG;
    public static final VoxelShape WALL_NORTH_RIGHT_LEG;
    public static final VoxelShape WALL_NORTH_ALL_LEGS;
    public static final VoxelShape WALL_NORTH_WHEEL;
    public static final VoxelShape WALL_WEST_LEFT_POST;
    public static final VoxelShape WALL_WEST_RIGHT_POST;
    public static final VoxelShape WALL_WEST_LEFT_PIVOT;
    public static final VoxelShape WALL_WEST_RIGHT_PIVOT;
    public static final VoxelShape WALL_WEST_LEFT_LEG;
    public static final VoxelShape WALL_WEST_RIGHT_LEG;
    public static final VoxelShape WALL_WEST_ALL_LEGS;
    public static final VoxelShape WALL_WEST_WHEEL;
    public static final VoxelShape WALL_EAST_LEFT_POST;
    public static final VoxelShape WALL_EAST_RIGHT_POST;
    public static final VoxelShape WALL_EAST_LEFT_PIVOT;
    public static final VoxelShape WALL_EAST_RIGHT_PIVOT;
    public static final VoxelShape WALL_EAST_LEFT_LEG;
    public static final VoxelShape WALL_EAST_RIGHT_LEG;
    public static final VoxelShape WALL_EAST_ALL_LEGS;
    public static final VoxelShape WALL_EAST_WHEEL;
    public static final VoxelShape WALL_SOUTH_TOP_POST;
    public static final VoxelShape WALL_SOUTH_BOTTOM_POST;
    public static final VoxelShape WALL_SOUTH_TOP_PIVOT;
    public static final VoxelShape WALL_SOUTH_BOTTOM_PIVOT;
    public static final VoxelShape WALL_SOUTH_TOP_LEG;
    public static final VoxelShape WALL_SOUTH_BOTTOM_LEG;
    public static final VoxelShape WALL_SOUTH_VERTICAL_LEGS;
    public static final VoxelShape WALL_SOUTH_WHEEL_ROTATED;
    public static final VoxelShape WALL_NORTH_TOP_POST;
    public static final VoxelShape WALL_NORTH_BOTTOM_POST;
    public static final VoxelShape WALL_NORTH_TOP_PIVOT;
    public static final VoxelShape WALL_NORTH_BOTTOM_PIVOT;
    public static final VoxelShape WALL_NORTH_TOP_LEG;
    public static final VoxelShape WALL_NORTH_BOTTOM_LEG;
    public static final VoxelShape WALL_NORTH_VERTICAL_LEGS;
    public static final VoxelShape WALL_NORTH_WHEEL_ROTATED;
    public static final VoxelShape WALL_WEST_TOP_POST;
    public static final VoxelShape WALL_WEST_BOTTOM_POST;
    public static final VoxelShape WALL_WEST_TOP_PIVOT;
    public static final VoxelShape WALL_WEST_BOTTOM_PIVOT;
    public static final VoxelShape WALL_WEST_TOP_LEG;
    public static final VoxelShape WALL_WEST_BOTTOM_LEG;
    public static final VoxelShape WALL_WEST_VERTICAL_LEGS;
    public static final VoxelShape WALL_WEST_WHEEL_ROTATED;
    public static final VoxelShape WALL_EAST_TOP_POST;
    public static final VoxelShape WALL_EAST_BOTTOM_POST;
    public static final VoxelShape WALL_EAST_TOP_PIVOT;
    public static final VoxelShape WALL_EAST_BOTTOM_PIVOT;
    public static final VoxelShape WALL_EAST_TOP_LEG;
    public static final VoxelShape WALL_EAST_BOTTOM_LEG;
    public static final VoxelShape WALL_EAST_VERTICAL_LEGS;
    public static final VoxelShape WALL_EAST_WHEEL_ROTATED;
    public static final VoxelShape CEILING_NORTH_SOUTH_LEFT_POST;
    public static final VoxelShape CEILING_NORTH_SOUTH_RIGHT_POST;
    public static final VoxelShape CEILING_NORTH_SOUTH_LEFT_PIVOT;
    public static final VoxelShape CEILING_NORTH_SOUTH_RIGHT_PIVOT;
    public static final VoxelShape CEILING_NORTH_SOUTH_LEFT_LEG;
    public static final VoxelShape CEILING_NORTH_SOUTH_RIGHT_LEG;
    public static final VoxelShape CEILING_NORTH_SOUTH_ALL_LEGS;
    public static final VoxelShape CEILING_NORTH_SOUTH_WHEEL;
    public static final VoxelShape CEILING_EAST_WEST_LEFT_POST;
    public static final VoxelShape CEILING_EAST_WEST_RIGHT_POST;
    public static final VoxelShape CEILING_EAST_WEST_LEFT_PIVOT;
    public static final VoxelShape CEILING_EAST_WEST_RIGHT_PIVOT;
    public static final VoxelShape CEILING_EAST_WEST_LEFT_LEG;
    public static final VoxelShape CEILING_EAST_WEST_RIGHT_LEG;
    public static final VoxelShape CEILING_EAST_WEST_ALL_LEGS;
    public static final VoxelShape CEILING_EAST_WEST_WHEEL;

    public WheelBlock(Properties properties) {
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
            case UP -> rotated ? FLOOR_EAST_WEST_WHEEL : FLOOR_NORTH_SOUTH_WHEEL;
            case DOWN -> rotated ? CEILING_EAST_WEST_WHEEL : CEILING_NORTH_SOUTH_WHEEL;
            case EAST -> rotated ? WALL_EAST_WHEEL_ROTATED : WALL_EAST_WHEEL;
            case WEST -> rotated ? WALL_WEST_WHEEL_ROTATED : WALL_WEST_WHEEL;
            case NORTH -> rotated ? WALL_NORTH_WHEEL_ROTATED : WALL_NORTH_WHEEL;
            default -> rotated ? WALL_SOUTH_WHEEL_ROTATED : WALL_SOUTH_WHEEL;
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
            double d0 = entity instanceof LivingEntity ? 0.7D : 0.55D;
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

    static {
        FLOOR_NORTH_SOUTH_LEFT_LEG = Shapes.or(FLOOR_NORTH_SOUTH_LEFT_POST, FLOOR_NORTH_SOUTH_LEFT_PIVOT);
        FLOOR_NORTH_SOUTH_RIGHT_LEG = Shapes.or(FLOOR_NORTH_SOUTH_RIGHT_POST, FLOOR_NORTH_SOUTH_RIGHT_PIVOT);
        FLOOR_NORTH_SOUTH_ALL_LEGS = Shapes.or(FLOOR_NORTH_SOUTH_LEFT_LEG, FLOOR_NORTH_SOUTH_RIGHT_LEG);
        FLOOR_NORTH_SOUTH_WHEEL = Shapes.or(FLOOR_NORTH_SOUTH_ALL_LEGS, Block.box(4.0F, 4.0F, 2.0F, 12.0F, 16.0F, 14.0F));
        FLOOR_EAST_WEST_LEFT_POST = Block.box(6.0F, 0.0F, 2.0F, 10.0F, 7.0F, 4.0F);
        FLOOR_EAST_WEST_RIGHT_POST = Block.box(6.0F, 0.0F, 12.0F, 10.0F, 7.0F, 14.0F);
        FLOOR_EAST_WEST_LEFT_PIVOT = Block.box(5.0F, 7.0F, 2.0F, 11.0F, 13.0F, 4.0F);
        FLOOR_EAST_WEST_RIGHT_PIVOT = Block.box(5.0F, 7.0F, 12.0F, 11.0F, 13.0F, 14.0F);
        FLOOR_EAST_WEST_LEFT_LEG = Shapes.or(FLOOR_EAST_WEST_LEFT_POST, FLOOR_EAST_WEST_LEFT_PIVOT);
        FLOOR_EAST_WEST_RIGHT_LEG = Shapes.or(FLOOR_EAST_WEST_RIGHT_POST, FLOOR_EAST_WEST_RIGHT_PIVOT);
        FLOOR_EAST_WEST_ALL_LEGS = Shapes.or(FLOOR_EAST_WEST_LEFT_LEG, FLOOR_EAST_WEST_RIGHT_LEG);
        FLOOR_EAST_WEST_WHEEL = Shapes.or(FLOOR_EAST_WEST_ALL_LEGS, Block.box(2.0F, 4.0F, 4.0F, 14.0F, 16.0F, 12.0F));
        WALL_SOUTH_LEFT_POST = Block.box(2.0F, 6.0F, 0.0F, 4.0F, 10.0F, 7.0F);
        WALL_SOUTH_RIGHT_POST = Block.box(12.0F, 6.0F, 0.0F, 14.0F, 10.0F, 7.0F);
        WALL_SOUTH_LEFT_PIVOT = Block.box(2.0F, 5.0F, 7.0F, 4.0F, 11.0F, 13.0F);
        WALL_SOUTH_RIGHT_PIVOT = Block.box(12.0F, 5.0F, 7.0F, 14.0F, 11.0F, 13.0F);
        WALL_SOUTH_LEFT_LEG = Shapes.or(WALL_SOUTH_LEFT_POST, WALL_SOUTH_LEFT_PIVOT);
        WALL_SOUTH_RIGHT_LEG = Shapes.or(WALL_SOUTH_RIGHT_POST, WALL_SOUTH_RIGHT_PIVOT);
        WALL_SOUTH_ALL_LEGS = Shapes.or(WALL_SOUTH_LEFT_LEG, WALL_SOUTH_RIGHT_LEG);
        WALL_SOUTH_WHEEL = Shapes.or(WALL_SOUTH_ALL_LEGS, Block.box(4.0F, 2.0F, 4.0F, 12.0F, 14.0F, 16.0F));
        WALL_NORTH_LEFT_POST = Block.box(2.0F, 6.0F, 7.0F, 4.0F, 10.0F, 16.0F);
        WALL_NORTH_RIGHT_POST = Block.box(12.0F, 6.0F, 7.0F, 14.0F, 10.0F, 16.0F);
        WALL_NORTH_LEFT_PIVOT = Block.box(2.0F, 5.0F, 3.0F, 4.0F, 11.0F, 9.0F);
        WALL_NORTH_RIGHT_PIVOT = Block.box(12.0F, 5.0F, 3.0F, 14.0F, 11.0F, 9.0F);
        WALL_NORTH_LEFT_LEG = Shapes.or(WALL_NORTH_LEFT_POST, WALL_NORTH_LEFT_PIVOT);
        WALL_NORTH_RIGHT_LEG = Shapes.or(WALL_NORTH_RIGHT_POST, WALL_NORTH_RIGHT_PIVOT);
        WALL_NORTH_ALL_LEGS = Shapes.or(WALL_NORTH_LEFT_LEG, WALL_NORTH_RIGHT_LEG);
        WALL_NORTH_WHEEL = Shapes.or(WALL_NORTH_ALL_LEGS, Block.box(4.0F, 2.0F, 0.0F, 12.0F, 14.0F, 12.0F));
        WALL_WEST_LEFT_POST = Block.box(7.0F, 6.0F, 2.0F, 16.0F, 10.0F, 4.0F);
        WALL_WEST_RIGHT_POST = Block.box(7.0F, 6.0F, 12.0F, 16.0F, 10.0F, 14.0F);
        WALL_WEST_LEFT_PIVOT = Block.box(3.0F, 5.0F, 2.0F, 9.0F, 11.0F, 4.0F);
        WALL_WEST_RIGHT_PIVOT = Block.box(3.0F, 5.0F, 12.0F, 9.0F, 11.0F, 14.0F);
        WALL_WEST_LEFT_LEG = Shapes.or(WALL_WEST_LEFT_POST, WALL_WEST_LEFT_PIVOT);
        WALL_WEST_RIGHT_LEG = Shapes.or(WALL_WEST_RIGHT_POST, WALL_WEST_RIGHT_PIVOT);
        WALL_WEST_ALL_LEGS = Shapes.or(WALL_WEST_LEFT_LEG, WALL_WEST_RIGHT_LEG);
        WALL_WEST_WHEEL = Shapes.or(WALL_WEST_ALL_LEGS, Block.box(0.0F, 2.0F, 4.0F, 12.0F, 14.0F, 12.0F));
        WALL_EAST_LEFT_POST = Block.box(0.0F, 6.0F, 2.0F, 9.0F, 10.0F, 4.0F);
        WALL_EAST_RIGHT_POST = Block.box(0.0F, 6.0F, 12.0F, 9.0F, 10.0F, 14.0F);
        WALL_EAST_LEFT_PIVOT = Block.box(7.0F, 5.0F, 2.0F, 13.0F, 11.0F, 4.0F);
        WALL_EAST_RIGHT_PIVOT = Block.box(7.0F, 5.0F, 12.0F, 13.0F, 11.0F, 14.0F);
        WALL_EAST_LEFT_LEG = Shapes.or(WALL_EAST_LEFT_POST, WALL_EAST_LEFT_PIVOT);
        WALL_EAST_RIGHT_LEG = Shapes.or(WALL_EAST_RIGHT_POST, WALL_EAST_RIGHT_PIVOT);
        WALL_EAST_ALL_LEGS = Shapes.or(WALL_EAST_LEFT_LEG, WALL_EAST_RIGHT_LEG);
        WALL_EAST_WHEEL = Shapes.or(WALL_EAST_ALL_LEGS, Block.box(4.0F, 2.0F, 4.0F, 16.0F, 14.0F, 12.0F));
        WALL_SOUTH_TOP_POST = Block.box(6.0F, 12.0F, 0.0F, 10.0F, 14.0F, 7.0F);
        WALL_SOUTH_BOTTOM_POST = Block.box(6.0F, 2.0F, 0.0F, 10.0F, 4.0F, 7.0F);
        WALL_SOUTH_TOP_PIVOT = Block.box(5.0F, 12.0F, 7.0F, 11.0F, 14.0F, 13.0F);
        WALL_SOUTH_BOTTOM_PIVOT = Block.box(5.0F, 2.0F, 7.0F, 11.0F, 4.0F, 13.0F);
        WALL_SOUTH_TOP_LEG = Shapes.or(WALL_SOUTH_TOP_POST, WALL_SOUTH_TOP_PIVOT);
        WALL_SOUTH_BOTTOM_LEG = Shapes.or(WALL_SOUTH_BOTTOM_POST, WALL_SOUTH_BOTTOM_PIVOT);
        WALL_SOUTH_VERTICAL_LEGS = Shapes.or(WALL_SOUTH_TOP_LEG, WALL_SOUTH_BOTTOM_LEG);
        WALL_SOUTH_WHEEL_ROTATED = Shapes.or(WALL_SOUTH_VERTICAL_LEGS, Block.box(2.0F, 4.0F, 4.0F, 14.0F, 12.0F, 16.0F));
        WALL_NORTH_TOP_POST = Block.box(6.0F, 12.0F, 9.0F, 10.0F, 14.0F, 16.0F);
        WALL_NORTH_BOTTOM_POST = Block.box(6.0F, 2.0F, 9.0F, 10.0F, 4.0F, 16.0F);
        WALL_NORTH_TOP_PIVOT = Block.box(5.0F, 12.0F, 3.0F, 11.0F, 14.0F, 9.0F);
        WALL_NORTH_BOTTOM_PIVOT = Block.box(5.0F, 2.0F, 3.0F, 11.0F, 4.0F, 9.0F);
        WALL_NORTH_TOP_LEG = Shapes.or(WALL_NORTH_TOP_POST, WALL_NORTH_TOP_PIVOT);
        WALL_NORTH_BOTTOM_LEG = Shapes.or(WALL_NORTH_BOTTOM_POST, WALL_NORTH_BOTTOM_PIVOT);
        WALL_NORTH_VERTICAL_LEGS = Shapes.or(WALL_NORTH_TOP_LEG, WALL_NORTH_BOTTOM_LEG);
        WALL_NORTH_WHEEL_ROTATED = Shapes.or(WALL_NORTH_VERTICAL_LEGS, Block.box(2.0F, 4.0F, 0.0F, 14.0F, 12.0F, 12.0F));
        WALL_WEST_TOP_POST = Block.box(9.0F, 12.0F, 6.0F, 16.0F, 14.0F, 10.0F);
        WALL_WEST_BOTTOM_POST = Block.box(9.0F, 2.0F, 6.0F, 16.0F, 4.0F, 10.0F);
        WALL_WEST_TOP_PIVOT = Block.box(3.0F, 12.0F, 5.0F, 9.0F, 14.0F, 11.0F);
        WALL_WEST_BOTTOM_PIVOT = Block.box(3.0F, 2.0F, 5.0F, 9.0F, 4.0F, 11.0F);
        WALL_WEST_TOP_LEG = Shapes.or(WALL_WEST_TOP_POST, WALL_WEST_TOP_PIVOT);
        WALL_WEST_BOTTOM_LEG = Shapes.or(WALL_WEST_BOTTOM_POST, WALL_WEST_BOTTOM_PIVOT);
        WALL_WEST_VERTICAL_LEGS = Shapes.or(WALL_WEST_TOP_LEG, WALL_WEST_BOTTOM_LEG);
        WALL_WEST_WHEEL_ROTATED = Shapes.or(WALL_WEST_VERTICAL_LEGS, Block.box(0.0F, 4.0F, 2.0F, 12.0F, 12.0F, 14.0F));
        WALL_EAST_TOP_POST = Block.box(0.0F, 12.0F, 6.0F, 7.0F, 14.0F, 10.0F);
        WALL_EAST_BOTTOM_POST = Block.box(0.0F, 2.0F, 6.0F, 7.0F, 4.0F, 10.0F);
        WALL_EAST_TOP_PIVOT = Block.box(7.0F, 12.0F, 5.0F, 13.0F, 14.0F, 11.0F);
        WALL_EAST_BOTTOM_PIVOT = Block.box(7.0F, 2.0F, 5.0F, 13.0F, 4.0F, 11.0F);
        WALL_EAST_TOP_LEG = Shapes.or(WALL_EAST_TOP_POST, WALL_EAST_TOP_PIVOT);
        WALL_EAST_BOTTOM_LEG = Shapes.or(WALL_EAST_BOTTOM_POST, WALL_EAST_BOTTOM_PIVOT);
        WALL_EAST_VERTICAL_LEGS = Shapes.or(WALL_EAST_TOP_LEG, WALL_EAST_BOTTOM_LEG);
        WALL_EAST_WHEEL_ROTATED = Shapes.or(WALL_EAST_VERTICAL_LEGS, Block.box(4.0F, 4.0F, 2.0F, 16.0F, 12.0F, 14.0F));
        CEILING_NORTH_SOUTH_LEFT_POST = Block.box(2.0F, 9.0F, 6.0F, 4.0F, 16.0F, 10.0F);
        CEILING_NORTH_SOUTH_RIGHT_POST = Block.box(12.0F, 9.0F, 6.0F, 14.0F, 16.0F, 10.0F);
        CEILING_NORTH_SOUTH_LEFT_PIVOT = Block.box(2.0F, 3.0F, 5.0F, 4.0F, 9.0F, 11.0F);
        CEILING_NORTH_SOUTH_RIGHT_PIVOT = Block.box(12.0F, 3.0F, 5.0F, 14.0F, 9.0F, 11.0F);
        CEILING_NORTH_SOUTH_LEFT_LEG = Shapes.or(CEILING_NORTH_SOUTH_LEFT_POST, CEILING_NORTH_SOUTH_LEFT_PIVOT);
        CEILING_NORTH_SOUTH_RIGHT_LEG = Shapes.or(CEILING_NORTH_SOUTH_RIGHT_POST, CEILING_NORTH_SOUTH_RIGHT_PIVOT);
        CEILING_NORTH_SOUTH_ALL_LEGS = Shapes.or(CEILING_NORTH_SOUTH_LEFT_LEG, CEILING_NORTH_SOUTH_RIGHT_LEG);
        CEILING_NORTH_SOUTH_WHEEL = Shapes.or(CEILING_NORTH_SOUTH_ALL_LEGS, Block.box(4.0F, 0.0F, 2.0F, 12.0F, 12.0F, 14.0F));
        CEILING_EAST_WEST_LEFT_POST = Block.box(6.0F, 9.0F, 2.0F, 10.0F, 16.0F, 4.0F);
        CEILING_EAST_WEST_RIGHT_POST = Block.box(6.0F, 9.0F, 12.0F, 10.0F, 16.0F, 14.0F);
        CEILING_EAST_WEST_LEFT_PIVOT = Block.box(5.0F, 3.0F, 2.0F, 11.0F, 9.0F, 4.0F);
        CEILING_EAST_WEST_RIGHT_PIVOT = Block.box(5.0F, 3.0F, 12.0F, 11.0F, 9.0F, 14.0F);
        CEILING_EAST_WEST_LEFT_LEG = Shapes.or(CEILING_EAST_WEST_LEFT_POST, CEILING_EAST_WEST_LEFT_PIVOT);
        CEILING_EAST_WEST_RIGHT_LEG = Shapes.or(CEILING_EAST_WEST_RIGHT_POST, CEILING_EAST_WEST_RIGHT_PIVOT);
        CEILING_EAST_WEST_ALL_LEGS = Shapes.or(CEILING_EAST_WEST_LEFT_LEG, CEILING_EAST_WEST_RIGHT_LEG);
        CEILING_EAST_WEST_WHEEL = Shapes.or(CEILING_EAST_WEST_ALL_LEGS, Block.box(2.0F, 0.0F, 4.0F, 14.0F, 12.0F, 12.0F));
    }
}
