package com.addyberry.blocksabound.common.block;

import com.addyberry.blocksabound.core.registry.BABlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
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

import java.util.Map;

public class IronPipeJunctionBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

    protected static final VoxelShape FRAME;
    protected static final VoxelShape PLATE_NORTH;
    protected static final VoxelShape PLATE_EAST;
    protected static final VoxelShape PLATE_SOUTH;
    protected static final VoxelShape PLATE_WEST;
    protected static final VoxelShape PLATE_UP;
    protected static final VoxelShape PLATE_DOWN;

    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), p_55164_ -> {
        p_55164_.put(Direction.NORTH, NORTH);
        p_55164_.put(Direction.EAST, EAST);
        p_55164_.put(Direction.SOUTH, SOUTH);
        p_55164_.put(Direction.WEST, WEST);
        p_55164_.put(Direction.UP, UP);
        p_55164_.put(Direction.DOWN, DOWN);
    }));


    public IronPipeJunctionBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false)
        );
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return !(context.isHoldingItem(BABlocks.PIPE.asItem()) || context.isHoldingItem(BABlocks.HATCH.asItem()) || context.isHoldingItem(BABlocks.VENT.asItem())) ? getCollisionShape(state, level, pos, context) : Shapes.block();
    }

    protected VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = FRAME;

        if (!state.getValue(NORTH)) shape = Shapes.or(shape, PLATE_NORTH);
        if (!state.getValue(EAST)) shape = Shapes.or(shape, PLATE_EAST);
        if (!state.getValue(SOUTH)) shape = Shapes.or(shape, PLATE_SOUTH);
        if (!state.getValue(WEST)) shape = Shapes.or(shape, PLATE_WEST);
        if (!state.getValue(UP)) shape = Shapes.or(shape, PLATE_UP);
        if (!state.getValue(DOWN)) shape = Shapes.or(shape, PLATE_DOWN);

        return shape;
    }

    public static BlockState setDirection(BlockState state, Direction direction, boolean open) {
        return switch (direction) {
            case NORTH -> state.setValue(NORTH, open);
            case EAST -> state.setValue(EAST, open);
            case SOUTH -> state.setValue(SOUTH, open);
            case WEST -> state.setValue(WEST, open);
            case UP -> state.setValue(UP, open);
            case DOWN -> state.setValue(DOWN, open);
        };
    }

    public static boolean opensToward(BlockState state, Direction direction) {
        Block block = state.getBlock();
        return switch (block) {
            case IronPipeJunctionBlock pipeJunction -> state.getValue(PROPERTY_BY_DIRECTION.get(direction));
            case IronPipeBlock pipe -> direction.getAxis() == state.getValue(IronPipeBlock.AXIS);
            case AbstractPanelBlock panel ->
                    direction == AbstractPanelBlock.getOpenDirection(state).getOpposite();
            default -> false;
        };
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        state = state.setValue(PROPERTY_BY_DIRECTION.get(direction), opensToward(neighborState, direction.getOpposite()));

        boolean x = state.getValue(EAST) || state.getValue(WEST);
        boolean y = state.getValue(UP) || state.getValue(DOWN);
        boolean z = state.getValue(NORTH) || state.getValue(SOUTH);
        int axes = (x ? 1 : 0) + (y ? 1 : 0) + (z ? 1 : 0);
        if (axes <= 1) {
            Direction.Axis axis = x ? Direction.Axis.X : y ? Direction.Axis.Y : z ? Direction.Axis.Z : Direction.Axis.Y;
            return BABlocks.PIPE.get().withPropertiesOf(state).setValue(IronPipeBlock.AXIS, axis);
        }
        return state;
    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return BABlocks.PIPE.toStack();
    }

    static {
        FRAME = Shapes.or(Block.box(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D), Block.box(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D), Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D), Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D), Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D), Block.box(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 14.0D, 0.0D, 2.0D, 16.0D, 16.0D), Block.box(14.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 2.0D), Block.box(0.0D, 14.0D, 14.0D, 16.0D, 16.0D, 16.0D));
        PLATE_NORTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        PLATE_EAST = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        PLATE_SOUTH = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        PLATE_WEST = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
        PLATE_UP = Block.box(2.0D, 14.0D, 2.0D, 14.0D, 16.0D, 14.0D);
        PLATE_DOWN = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D);
    }
}
