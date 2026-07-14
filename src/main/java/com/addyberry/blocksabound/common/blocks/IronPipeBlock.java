package com.addyberry.blocksabound.common.blocks;

import com.addyberry.blocksabound.core.registry.BABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.HitResult;
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
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(AXIS, Direction.Axis.Y)
            .setValue(WATERLOGGED, false)
        );
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return !(context.isHoldingItem(BABlocks.PIPE.asItem()) || context.isHoldingItem(BABlocks.HATCH.asItem()) || context.isHoldingItem(BABlocks.VENT.asItem())) ? getCollisionShape(state, level, pos, context) : Shapes.block();
    }

    protected VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> AXIS_X;
            case Y -> AXIS_Y;
            case Z -> AXIS_Z;
        };
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return connectedState(context.getLevel(), context.getClickedPos(), context.getClickedFace().getAxis(), waterlogged);
    }

    public static BlockState connectedState(BlockGetter level, BlockPos pos, Direction.Axis fallbackAxis, boolean waterlogged) {
        boolean x = connectsFrom(level, pos, Direction.EAST) || connectsFrom(level, pos, Direction.WEST);
        boolean y = connectsFrom(level, pos, Direction.UP) || connectsFrom(level, pos, Direction.DOWN);
        boolean z = connectsFrom(level, pos, Direction.NORTH) || connectsFrom(level, pos, Direction.SOUTH);
        int axes = (x ? 1 : 0) + (y ? 1 : 0) + (z ? 1 : 0);

        if (axes >= 2) {
            BlockState junction = BABlocks.PIPE_JUNCTION.get().defaultBlockState().setValue(IronPipeJunctionBlock.WATERLOGGED, waterlogged);
            for (Direction side : Direction.values()) {
                junction = junction.setValue(IronPipeJunctionBlock.PROPERTY_BY_DIRECTION.get(side), connectsFrom(level, pos, side));
            }
            return junction;
        }

        Direction.Axis axis = x ? Direction.Axis.X : y ? Direction.Axis.Y : z ? Direction.Axis.Z : fallbackAxis;
        return BABlocks.PIPE.get().defaultBlockState().setValue(AXIS, axis).setValue(WATERLOGGED, waterlogged);
    }

    private static boolean connectsFrom(BlockGetter level, BlockPos pos, Direction side) {
        return IronPipeJunctionBlock.opensToward(level.getBlockState(pos.relative(side)), side.getOpposite());
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return connectedState(level, pos, state.getValue(AXIS), state.getValue(WATERLOGGED));
    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, AXIS);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return BABlocks.PIPE.toStack();
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
