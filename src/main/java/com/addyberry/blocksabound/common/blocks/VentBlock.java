package com.addyberry.blocksabound.common.blocks;

import com.addyberry.blocksabound.core.registry.BABlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VentBlock extends AbstractPanelBlock{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<AttachFace> FACE = BlockStateProperties.ATTACH_FACE;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");

    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_AABB = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape UP_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape DOWN_AABB = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_CONNECTED_AABB = Block.box(-2.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_CONNECTED_AABB = Block.box(14.0D, 0.0D, 0.0D, 18.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_CONNECTED_AABB = Block.box(0.0D, 0.0D, -2.0D, 16.0D, 16.0D, 2.0D);
    protected static final VoxelShape NORTH_CONNECTED_AABB = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 18.0D);
    protected static final VoxelShape UP_CONNECTED_AABB = Block.box(0.0D, -2.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape DOWN_CONNECTED_AABB = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 18.0D, 16.0D);

    public VentBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
                .setValue(CONNECTED, false)
        );
    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return null;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean flag = state.getValue(CONNECTED);
        return switch (state.getValue(FACE)) {
            case FLOOR -> flag ? UP_CONNECTED_AABB : UP_AABB;
            case WALL -> switch (state.getValue(FACING)) {
                case NORTH -> flag ? NORTH_CONNECTED_AABB : NORTH_AABB;
                case SOUTH -> flag ? SOUTH_CONNECTED_AABB : SOUTH_AABB;
                case WEST -> flag ? WEST_CONNECTED_AABB : WEST_AABB;
                case EAST -> flag ? EAST_CONNECTED_AABB : EAST_AABB;
                default -> throw new MatchException(null, null);
            };
            default -> flag ? DOWN_CONNECTED_AABB : DOWN_AABB;
        };
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction connectionSide = AbstractPanelBlock.getOpenDirection(state);
        boolean connectedFlag = false;
        if (neighborState.is(BABlocks.PIPE_JUNCTION)) {
            connectedFlag = neighborState.getValue(IronPipeJunctionBlock.PROPERTY_BY_DIRECTION.get(connectionSide));

        } else if (neighborState.is(BABlocks.PIPE)) {
            //connectedFlag = neighborState.getValue(IronPipeBlock.AXIS) == Direction.Axis.Y;
        }
        state = state.setValue(CONNECTED, connectedFlag);
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACE, FACING, CONNECTED);
    }
}
