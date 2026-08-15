package com.addyberry.blocksabound.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WheelBlock extends FaceAttachedHorizontalDirectionalBlock {
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
                .setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.WALL));
    }

    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    private VoxelShape getVoxelShape(BlockState state) {
        Direction direction = state.getValue(FACING);
        switch (state.getValue(FACE)) {
            case FLOOR:
                if (direction != Direction.NORTH && direction != Direction.SOUTH) {
                    return FLOOR_EAST_WEST_WHEEL;
                }

                return FLOOR_NORTH_SOUTH_WHEEL;
            case WALL:
                if (direction == Direction.NORTH) {
                    return WALL_NORTH_WHEEL;
                } else if (direction == Direction.SOUTH) {
                    return WALL_SOUTH_WHEEL;
                } else {
                    if (direction == Direction.EAST) {
                        return WALL_EAST_WHEEL;
                    }

                    return WALL_WEST_WHEEL;
                }
            case CEILING:
                if (direction != Direction.NORTH && direction != Direction.SOUTH) {
                    return CEILING_EAST_WEST_WHEEL;
                }

                return CEILING_NORTH_SOUTH_WHEEL;
            default:
                return FLOOR_EAST_WEST_WHEEL;
        }
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
        if (vec3.y < (double)0.0F) {
            double d0 = entity instanceof LivingEntity ? (double)0.7F : 0.55;
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FACE);
    }

    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }


    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return null;
    }

    static {
        FLOOR_NORTH_SOUTH_LEFT_LEG = Shapes.or(FLOOR_NORTH_SOUTH_LEFT_POST, FLOOR_NORTH_SOUTH_LEFT_PIVOT);
        FLOOR_NORTH_SOUTH_RIGHT_LEG = Shapes.or(FLOOR_NORTH_SOUTH_RIGHT_POST, FLOOR_NORTH_SOUTH_RIGHT_PIVOT);
        FLOOR_NORTH_SOUTH_ALL_LEGS = Shapes.or(FLOOR_NORTH_SOUTH_LEFT_LEG, FLOOR_NORTH_SOUTH_RIGHT_LEG);
        FLOOR_NORTH_SOUTH_WHEEL = Shapes.or(FLOOR_NORTH_SOUTH_ALL_LEGS, Block.box((double)4.0F, (double)4.0F, (double)2.0F, (double)12.0F, (double)16.0F, (double)14.0F));
        FLOOR_EAST_WEST_LEFT_POST = Block.box((double)6.0F, (double)0.0F, (double)2.0F, (double)10.0F, (double)7.0F, (double)4.0F);
        FLOOR_EAST_WEST_RIGHT_POST = Block.box((double)6.0F, (double)0.0F, (double)12.0F, (double)10.0F, (double)7.0F, (double)14.0F);
        FLOOR_EAST_WEST_LEFT_PIVOT = Block.box((double)5.0F, (double)7.0F, (double)2.0F, (double)11.0F, (double)13.0F, (double)4.0F);
        FLOOR_EAST_WEST_RIGHT_PIVOT = Block.box((double)5.0F, (double)7.0F, (double)12.0F, (double)11.0F, (double)13.0F, (double)14.0F);
        FLOOR_EAST_WEST_LEFT_LEG = Shapes.or(FLOOR_EAST_WEST_LEFT_POST, FLOOR_EAST_WEST_LEFT_PIVOT);
        FLOOR_EAST_WEST_RIGHT_LEG = Shapes.or(FLOOR_EAST_WEST_RIGHT_POST, FLOOR_EAST_WEST_RIGHT_PIVOT);
        FLOOR_EAST_WEST_ALL_LEGS = Shapes.or(FLOOR_EAST_WEST_LEFT_LEG, FLOOR_EAST_WEST_RIGHT_LEG);
        FLOOR_EAST_WEST_WHEEL = Shapes.or(FLOOR_EAST_WEST_ALL_LEGS, Block.box((double)2.0F, (double)4.0F, (double)4.0F, (double)14.0F, (double)16.0F, (double)12.0F));
        WALL_SOUTH_LEFT_POST = Block.box((double)2.0F, (double)6.0F, (double)0.0F, (double)4.0F, (double)10.0F, (double)7.0F);
        WALL_SOUTH_RIGHT_POST = Block.box((double)12.0F, (double)6.0F, (double)0.0F, (double)14.0F, (double)10.0F, (double)7.0F);
        WALL_SOUTH_LEFT_PIVOT = Block.box((double)2.0F, (double)5.0F, (double)7.0F, (double)4.0F, (double)11.0F, (double)13.0F);
        WALL_SOUTH_RIGHT_PIVOT = Block.box((double)12.0F, (double)5.0F, (double)7.0F, (double)14.0F, (double)11.0F, (double)13.0F);
        WALL_SOUTH_LEFT_LEG = Shapes.or(WALL_SOUTH_LEFT_POST, WALL_SOUTH_LEFT_PIVOT);
        WALL_SOUTH_RIGHT_LEG = Shapes.or(WALL_SOUTH_RIGHT_POST, WALL_SOUTH_RIGHT_PIVOT);
        WALL_SOUTH_ALL_LEGS = Shapes.or(WALL_SOUTH_LEFT_LEG, WALL_SOUTH_RIGHT_LEG);
        WALL_SOUTH_WHEEL = Shapes.or(WALL_SOUTH_ALL_LEGS, Block.box((double)4.0F, (double)2.0F, (double)4.0F, (double)12.0F, (double)14.0F, (double)16.0F));
        WALL_NORTH_LEFT_POST = Block.box((double)2.0F, (double)6.0F, (double)7.0F, (double)4.0F, (double)10.0F, (double)16.0F);
        WALL_NORTH_RIGHT_POST = Block.box((double)12.0F, (double)6.0F, (double)7.0F, (double)14.0F, (double)10.0F, (double)16.0F);
        WALL_NORTH_LEFT_PIVOT = Block.box((double)2.0F, (double)5.0F, (double)3.0F, (double)4.0F, (double)11.0F, (double)9.0F);
        WALL_NORTH_RIGHT_PIVOT = Block.box((double)12.0F, (double)5.0F, (double)3.0F, (double)14.0F, (double)11.0F, (double)9.0F);
        WALL_NORTH_LEFT_LEG = Shapes.or(WALL_NORTH_LEFT_POST, WALL_NORTH_LEFT_PIVOT);
        WALL_NORTH_RIGHT_LEG = Shapes.or(WALL_NORTH_RIGHT_POST, WALL_NORTH_RIGHT_PIVOT);
        WALL_NORTH_ALL_LEGS = Shapes.or(WALL_NORTH_LEFT_LEG, WALL_NORTH_RIGHT_LEG);
        WALL_NORTH_WHEEL = Shapes.or(WALL_NORTH_ALL_LEGS, Block.box((double)4.0F, (double)2.0F, (double)0.0F, (double)12.0F, (double)14.0F, (double)12.0F));
        WALL_WEST_LEFT_POST = Block.box((double)7.0F, (double)6.0F, (double)2.0F, (double)16.0F, (double)10.0F, (double)4.0F);
        WALL_WEST_RIGHT_POST = Block.box((double)7.0F, (double)6.0F, (double)12.0F, (double)16.0F, (double)10.0F, (double)14.0F);
        WALL_WEST_LEFT_PIVOT = Block.box((double)3.0F, (double)5.0F, (double)2.0F, (double)9.0F, (double)11.0F, (double)4.0F);
        WALL_WEST_RIGHT_PIVOT = Block.box((double)3.0F, (double)5.0F, (double)12.0F, (double)9.0F, (double)11.0F, (double)14.0F);
        WALL_WEST_LEFT_LEG = Shapes.or(WALL_WEST_LEFT_POST, WALL_WEST_LEFT_PIVOT);
        WALL_WEST_RIGHT_LEG = Shapes.or(WALL_WEST_RIGHT_POST, WALL_WEST_RIGHT_PIVOT);
        WALL_WEST_ALL_LEGS = Shapes.or(WALL_WEST_LEFT_LEG, WALL_WEST_RIGHT_LEG);
        WALL_WEST_WHEEL = Shapes.or(WALL_WEST_ALL_LEGS, Block.box((double)0.0F, (double)2.0F, (double)4.0F, (double)12.0F, (double)14.0F, (double)12.0F));
        WALL_EAST_LEFT_POST = Block.box((double)0.0F, (double)6.0F, (double)2.0F, (double)9.0F, (double)10.0F, (double)4.0F);
        WALL_EAST_RIGHT_POST = Block.box((double)0.0F, (double)6.0F, (double)12.0F, (double)9.0F, (double)10.0F, (double)14.0F);
        WALL_EAST_LEFT_PIVOT = Block.box((double)7.0F, (double)5.0F, (double)2.0F, (double)13.0F, (double)11.0F, (double)4.0F);
        WALL_EAST_RIGHT_PIVOT = Block.box((double)7.0F, (double)5.0F, (double)12.0F, (double)13.0F, (double)11.0F, (double)14.0F);
        WALL_EAST_LEFT_LEG = Shapes.or(WALL_EAST_LEFT_POST, WALL_EAST_LEFT_PIVOT);
        WALL_EAST_RIGHT_LEG = Shapes.or(WALL_EAST_RIGHT_POST, WALL_EAST_RIGHT_PIVOT);
        WALL_EAST_ALL_LEGS = Shapes.or(WALL_EAST_LEFT_LEG, WALL_EAST_RIGHT_LEG);
        WALL_EAST_WHEEL = Shapes.or(WALL_EAST_ALL_LEGS, Block.box((double)4.0F, (double)2.0F, (double)4.0F, (double)16.0F, (double)14.0F, (double)12.0F));
        CEILING_NORTH_SOUTH_LEFT_POST = Block.box((double)2.0F, (double)9.0F, (double)6.0F, (double)4.0F, (double)16.0F, (double)10.0F);
        CEILING_NORTH_SOUTH_RIGHT_POST = Block.box((double)12.0F, (double)9.0F, (double)6.0F, (double)14.0F, (double)16.0F, (double)10.0F);
        CEILING_NORTH_SOUTH_LEFT_PIVOT = Block.box((double)2.0F, (double)3.0F, (double)5.0F, (double)4.0F, (double)9.0F, (double)11.0F);
        CEILING_NORTH_SOUTH_RIGHT_PIVOT = Block.box((double)12.0F, (double)3.0F, (double)5.0F, (double)14.0F, (double)9.0F, (double)11.0F);
        CEILING_NORTH_SOUTH_LEFT_LEG = Shapes.or(CEILING_NORTH_SOUTH_LEFT_POST, CEILING_NORTH_SOUTH_LEFT_PIVOT);
        CEILING_NORTH_SOUTH_RIGHT_LEG = Shapes.or(CEILING_NORTH_SOUTH_RIGHT_POST, CEILING_NORTH_SOUTH_RIGHT_PIVOT);
        CEILING_NORTH_SOUTH_ALL_LEGS = Shapes.or(CEILING_NORTH_SOUTH_LEFT_LEG, CEILING_NORTH_SOUTH_RIGHT_LEG);
        CEILING_NORTH_SOUTH_WHEEL = Shapes.or(CEILING_NORTH_SOUTH_ALL_LEGS, Block.box((double)4.0F, (double)0.0F, (double)2.0F, (double)12.0F, (double)12.0F, (double)14.0F));
        CEILING_EAST_WEST_LEFT_POST = Block.box((double)6.0F, (double)9.0F, (double)2.0F, (double)10.0F, (double)16.0F, (double)4.0F);
        CEILING_EAST_WEST_RIGHT_POST = Block.box((double)6.0F, (double)9.0F, (double)12.0F, (double)10.0F, (double)16.0F, (double)14.0F);
        CEILING_EAST_WEST_LEFT_PIVOT = Block.box((double)5.0F, (double)3.0F, (double)2.0F, (double)11.0F, (double)9.0F, (double)4.0F);
        CEILING_EAST_WEST_RIGHT_PIVOT = Block.box((double)5.0F, (double)3.0F, (double)12.0F, (double)11.0F, (double)9.0F, (double)14.0F);
        CEILING_EAST_WEST_LEFT_LEG = Shapes.or(CEILING_EAST_WEST_LEFT_POST, CEILING_EAST_WEST_LEFT_PIVOT);
        CEILING_EAST_WEST_RIGHT_LEG = Shapes.or(CEILING_EAST_WEST_RIGHT_POST, CEILING_EAST_WEST_RIGHT_PIVOT);
        CEILING_EAST_WEST_ALL_LEGS = Shapes.or(CEILING_EAST_WEST_LEFT_LEG, CEILING_EAST_WEST_RIGHT_LEG);
        CEILING_EAST_WEST_WHEEL = Shapes.or(CEILING_EAST_WEST_ALL_LEGS, Block.box((double)2.0F, (double)0.0F, (double)4.0F, (double)14.0F, (double)12.0F, (double)12.0F));
    }
}
