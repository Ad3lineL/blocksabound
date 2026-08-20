package com.addyberry.blocksabound.common.block;

import com.addyberry.blocksabound.core.registry.BABlocks;
import com.addyberry.blocksabound.core.registry.BAParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.checkerframework.common.returnsreceiver.qual.This;

public class TarBlock extends Block {
    public static final BooleanProperty BOILING = BooleanProperty.create("boiling");
    protected static final VoxelShape OUTLINE_SHAPE;
    protected static final VoxelShape COLLISION_SHAPE;
    protected static final VoxelShape BOILING_SHAPE;
    protected final RandomSource random = RandomSource.create();

    public TarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BOILING, false)
        );
    }

    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(BOILING) ? BOILING_SHAPE : COLLISION_SHAPE;
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return OUTLINE_SHAPE;
    }


    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity && state.getValue(BOILING)) {
            entity.hurt(level.damageSources().hotFloor(), 1.0F);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        BlockState neighborState = context.getLevel().getBlockState(context.getClickedPos().below());

        if (neighborState.is(Blocks.MAGMA_BLOCK) || neighborState.is(Blocks.LAVA)) {
            blockstate = blockstate.setValue(BOILING, true);
        } else if (neighborState.is(BABlocks.TAR_BLOCK)) {
            blockstate = blockstate.setValue(BOILING, neighborState.getValue(BOILING));
        }

        return blockstate;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if (direction == Direction.DOWN) {
            if (neighborState.is(BABlocks.TAR_BLOCK)) {
                state = state.setValue(BOILING, neighborState.getValue(BOILING));
            } else if (neighborState.is(Blocks.MAGMA_BLOCK) || neighborState.is(Blocks.LAVA)) {
                state = state.setValue(BOILING, true);
            } else {
                state = state.setValue(BOILING, false);
            }
        }

        return state;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double d0 = (double)pos.getX() + 0.05 + 0.9*random.nextDouble();
        double d1 = (double)pos.getY() + 1.05;
        double d2 = (double)pos.getZ() + 0.05 + 0.9*random.nextDouble();
        BlockState aboveState = level.getBlockState(pos.relative(Direction.UP));
        if (state.getValue(BOILING) && !state.isCollisionShapeFullBlock(level, pos.relative(Direction.UP)) && !aboveState.is(this)) {
            if (this.random.nextInt(6) == 0) {
                level.addParticle(BAParticles.TAR_BUBBLE.get(), d0, d1, d2, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BOILING);
    }

    static {
        OUTLINE_SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F);
        COLLISION_SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 15.0F, 16.0F);
        BOILING_SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 12.0F, 16.0F);
    }
}
