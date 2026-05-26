package com.addyberry.blocksabound.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class LightBulbBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty INVERTED = BlockStateProperties.INVERTED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape FLOOR_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 9.0, 11.0);
    protected static final VoxelShape CEILING_SHAPE = Block.box(5.0, 7.0, 5.0, 11.0, 16.0, 11.0);
    protected static final VoxelShape NORTH_WALL_SHAPE = Block.box(5.0, 5.0, 7.0, 11.0, 11.0, 16.0);
    protected static final VoxelShape WEST_WALL_SHAPE = Block.box(7.0, 5.0, 5.0, 16.0, 11.0, 11.0);
    protected static final VoxelShape EAST_WALL_SHAPE = Block.box(0.0, 5.0, 5.0, 9.0, 11.0, 11.0);
    protected static final VoxelShape SOUTH_WALL_SHAPE = Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 9.0);


    public LightBulbBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT, false)
                .setValue(INVERTED, false)
                .setValue(POWERED, false)
                .setValue(FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
        );
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


    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, INVERTED, POWERED, FACE, FACING);
    }

}
