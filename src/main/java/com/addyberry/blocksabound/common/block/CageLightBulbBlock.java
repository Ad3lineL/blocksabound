package com.addyberry.blocksabound.common.block;

import com.addyberry.blocksabound.core.registry.BABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CageLightBulbBlock extends LightBulbBlock {
    protected static final VoxelShape FLOOR_SHAPE = Shapes.or(Block.box(4.0, 2.0, 4.0, 12.0, 10.0, 12.0), Block.box(6.0, 0.0, 6.0, 10.0, 2.0, 10.0));
    protected static final VoxelShape CEILING_SHAPE = Shapes.or(Block.box(4.0, 6.0, 4.0, 12.0, 14.0, 12.0), Block.box(6.0, 14.0, 6.0, 10.0, 16.0, 10.0));
    protected static final VoxelShape NORTH_WALL_SHAPE = Shapes.or(Block.box(4.0, 4.0, 6.0, 12.0, 12.0, 14.0), Block.box(6.0, 6.0, 14.0, 10.0, 10.0, 16.0));
    protected static final VoxelShape WEST_WALL_SHAPE = Shapes.or(Block.box(6.0, 4.0, 4.0, 14.0, 12.0, 12.0), Block.box(14.0, 6.0, 6.0, 16.0, 10.0, 10.0));
    protected static final VoxelShape EAST_WALL_SHAPE = Shapes.or(Block.box(2.0, 4.0, 4.0, 10.0, 12.0, 12.0), Block.box(0.0, 6.0, 6.0, 2.0, 10.0, 10.0));
    protected static final VoxelShape SOUTH_WALL_SHAPE = Shapes.or(Block.box(4.0, 4.0, 2.0, 12.0, 12.0, 10.0), Block.box(6.0, 6.0, 0.0, 10.0, 10.0, 2.0));
    
    public CageLightBulbBlock(Properties properties) {
        super(properties, null);
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
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {}

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return Blocks.IRON_BARS.asItem().getDefaultInstance();
    }

}
