package com.gr00ze.training.block;

import com.gr00ze.training.BlockList;
import com.gr00ze.training.block.blockentity.TrainingCustomBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TrainingCustomBlockWithEntity extends BlockWithEntity {
    public TrainingCustomBlockWithEntity(Settings settings) {
        super(settings.nonOpaque());
    }

    public static AbstractBlock.Settings getBlockSettings(){
        return AbstractBlock.Settings.create();

    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(TrainingCustomBlockWithEntity::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TrainingCustomBlockEntity(pos, state);
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, BlockList.CUSTOM_BLOCK_ENTITY, TrainingCustomBlockEntity::tick);
    }


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!(world.getBlockEntity(pos) instanceof TrainingCustomBlockEntity blockEntity))
            return super.onUse(state, world, pos, player, hit);

        blockEntity.changeRotationState();
        player.sendMessage(Text.literal("Rotation is" + (blockEntity.isRotating()?"On":"Off")), true);

        return ActionResult.SUCCESS;

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                Block.createCuboidShape(1, 1, 1, 15, 15, 15) // Corpo del hopper
        );
    }


    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        //TO NOT RENDER STATIC MODEL OF THE BLOCK
        return BlockRenderType.INVISIBLE;
    }




}
