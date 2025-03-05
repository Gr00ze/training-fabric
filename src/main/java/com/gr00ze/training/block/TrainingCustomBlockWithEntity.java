package com.gr00ze.training.block;

import com.gr00ze.training.BlockList;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TrainingCustomBlockWithEntity extends BlockWithEntity {
    public TrainingCustomBlockWithEntity(Settings settings) {
        super(settings);
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
        return validateTicker(type, BlockList.TRAINING_BLOCK_ENTITY, TrainingCustomBlockEntity::tick);
    }


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!(world.getBlockEntity(pos) instanceof TrainingCustomBlockEntity blockEntity))
            return super.onUse(state, world, pos, player, hit);

        blockEntity.doSomething();
        player.sendMessage(Text.literal("Light is" + (blockEntity.isLightOn()?"On":"Off")), true);

        return ActionResult.SUCCESS;

    }
}
