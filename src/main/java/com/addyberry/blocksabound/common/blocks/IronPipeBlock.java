package com.addyberry.blocksabound.common.blocks;

import com.addyberry.blocksabound.core.registry.BABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;


public class IronPipeBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape EAST_SIDE;
    protected static final VoxelShape WEST_SIDE;
    protected static final VoxelShape SOUTH_SIDE;
    protected static final VoxelShape NORTH_SIDE;
    protected static final VoxelShape UP_SIDE;
    protected static final VoxelShape DOWN_SIDE;
    protected static final VoxelShape AXIS_X;
    protected static final VoxelShape AXIS_Y;
    protected static final VoxelShape AXIS_Z;


    public IronPipeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
            .setValue(AXIS, Direction.Axis.Y)
            .setValue(WATERLOGGED, false)
        );
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> AXIS_X;
            case Y -> AXIS_Y;
            case Z -> AXIS_Z;
        };
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        return super.getStateForPlacement(context).setValue(WATERLOGGED, flag);
    }

    public static void connectBlock(BlockState state, Direction direction, Level level, BlockPos pos, Direction.Axis axis) {

        if (state.is(BABlocks.PIPE)) {
            if (state.getValue(AXIS) != axis) {
                boolean flag = false;
                for (Direction neighbourDirection : Direction.values()) {
                    if (neighbourDirection.getAxis() != axis) continue;

                    BlockPos blockpos = pos.relative(neighbourDirection, 1);
                    BlockState blockstate1 = level.getBlockState(blockpos);

                    if (blockstate1.is(BABlocks.PIPE)) {
                        flag |= blockstate1.getValue(AXIS) == axis;
                    }
                    //why is this always true??????
                }

                if (flag) {
                    BlockState blockstate = state
                            .setValue(AXIS, axis);
                    level.setBlockAndUpdate(pos, blockstate);
                }
            }
        }
    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, AXIS);
    }

    static {
        EAST_SIDE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        WEST_SIDE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
        SOUTH_SIDE = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        NORTH_SIDE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        UP_SIDE = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        DOWN_SIDE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        AXIS_X = Shapes.or(SOUTH_SIDE, NORTH_SIDE, UP_SIDE, DOWN_SIDE);
        AXIS_Y = Shapes.or(EAST_SIDE, WEST_SIDE, SOUTH_SIDE, NORTH_SIDE);
        AXIS_Z = Shapes.or(EAST_SIDE, WEST_SIDE, UP_SIDE, DOWN_SIDE);
    }
}
