package cn.mlus.bettervannilacreatures.block.be;

import cn.mlus.bettervannilacreatures.init.BvcBlockEntities;
import cn.mlus.bettervannilacreatures.init.BvcBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class FishSpecimenBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private float scale = 1f;
    public FishSpecimenBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(getBlockEntityType(pBlockState), pPos, pBlockState);
    }

    private static BlockEntityType<FishSpecimenBlockEntity> getBlockEntityType(BlockState pState) {
        BlockEntityType<FishSpecimenBlockEntity> type;
        if(pState.is(BvcBlocks.ATLANTIC_COD_SPECIMEN.get())){
            type = BvcBlockEntities.ATLANTIC_COD_SPECIMEN.get();
        }else if(pState.is(BvcBlocks.PACIFIC_COD_SPECIMEN.get())) {
            type = BvcBlockEntities.PACIFIC_COD_SPECIMEN.get();
        }else if(pState.is(BvcBlocks.HADDOCK_COD_SPECIMEN.get())){
            type = BvcBlockEntities.HADDOCK_COD_SPECIMEN.get();
        }else if(pState.is(BvcBlocks.PACIFIC_SALMON_SPECIMEN.get())){
            type = BvcBlockEntities.PACIFIC_SALMON_SPECIMEN.get();
        }else if(pState.is(BvcBlocks.MALE_SALMON_SPECIMEN.get())){
            type = BvcBlockEntities.MALE_SALMON_SPECIMEN.get();
        }else{
            type = BvcBlockEntities.FEMALE_SALMON_SPECIMEN.get();
        }
        return type;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float value) {
        this.scale = value;
        markUpdated();
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Scale")) {
            this.scale = tag.getFloat("Scale");
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putFloat("Scale", this.scale);
    }

}
