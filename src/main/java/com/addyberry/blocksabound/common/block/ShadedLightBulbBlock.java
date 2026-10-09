package com.addyberry.blocksabound.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShadedLightBulbBlock extends LightBulbBlock{
    protected static final VoxelShape FLOOR_SHAPE = Shapes.or(Block.box(5.0, 2.0, 5.0, 11.0, 10.0, 11.0), Block.box(6.0, 0.0, 6.0, 10.0, 2.0, 10.0), Block.box(6.0, 10.0, 6.0, 10.0, 12.0, 10.0));
    protected static final VoxelShape CEILING_SHAPE = Shapes.or(Block.box(5.0, 7.0, 5.0, 11.0, 11.0, 11.0), Block.box(6.0, 14.0, 6.0, 10.0, 16.0, 10.0), Block.box(2.0, 11.0, 2.0, 14.0, 14.0, 14.0));
    protected static final VoxelShape NORTH_WALL_SHAPE = Shapes.or(Block.box(5.0, 5.0, 7.0, 11.0, 11.0, 14.0), Block.box(6.0, 6.0, 14.0, 10.0, 10.0, 16.0));
    protected static final VoxelShape WEST_WALL_SHAPE = Shapes.or(Block.box(7.0, 5.0, 5.0, 14.0, 11.0, 11.0), Block.box(14.0, 6.0, 6.0, 16.0, 10.0, 10.0));
    protected static final VoxelShape EAST_WALL_SHAPE = Shapes.or(Block.box(2.0, 5.0, 5.0, 9.0, 11.0, 11.0), Block.box(0.0, 6.0, 6.0, 2.0, 10.0, 10.0));
    protected static final VoxelShape SOUTH_WALL_SHAPE = Shapes.or(Block.box(5.0, 5.0, 2.0, 11.0, 11.0, 9.0), Block.box(6.0, 6.0, 0.0, 10.0, 10.0, 2.0));

    public ShadedLightBulbBlock(Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(FACE) == AttachFace.FLOOR)  {
            return FLOOR_SHAPE;
        } else if (state.getValue(FACE) == AttachFace.CEILING) {
            return CEILING_SHAPE;
        }
        if (state.getValue(FACING) == Direction.WEST) {
            return WEST_WALL_SHAPE;
        } else if (state.getValue(FACING) == Direction.EAST) {
            return EAST_WALL_SHAPE;
        } else if (state.getValue(FACING) == Direction.SOUTH) {
            return SOUTH_WALL_SHAPE;
        }
        return NORTH_WALL_SHAPE;
    };
}
