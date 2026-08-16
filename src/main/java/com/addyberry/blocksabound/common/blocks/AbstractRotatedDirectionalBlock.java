package com.addyberry.blocksabound.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class AbstractRotatedDirectionalBlock extends DirectionalBlock {
    public static final BooleanProperty ROTATED = BooleanProperty.create("rotated");;

    protected AbstractRotatedDirectionalBlock(Properties properties) {
        super(properties);
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING).getOpposite();
        BlockPos blockpos = pos.relative(direction);
        return level.getBlockState(blockpos).isFaceSturdy(level, blockpos, direction.getOpposite());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
            BlockState blockstate;
            switch (direction.getAxis()) {
                case Y -> blockstate = this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(ROTATED, context.getHorizontalDirection().getAxis() == Direction.Axis.X);
                case X -> blockstate = this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(ROTATED, context.getNearestLookingDirection().getAxis() == Direction.Axis.Z);
                default -> blockstate = this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(ROTATED, context.getNearestLookingDirection().getAxis() == Direction.Axis.X);
            }

            if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockstate;
            }
        }

        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROTATED);
    }

    protected MapCodec<? extends DirectionalBlock> codec() {
        return null;
    }
}
