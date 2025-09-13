package cn.mlus.bettervannilacreatures.entity.cod;

import cn.mlus.bettervannilacreatures.client.animator.BvcFishAnimator;
import cn.mlus.bettervannilacreatures.client.animator.GeneralAnimator;
import cn.mlus.bettervannilacreatures.entity.BvcAbstractFish;
import cn.mlus.bettervannilacreatures.entity.BvcEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

public class BvcCodEntity extends BvcAbstractFish implements BvcEntity<BvcCodEntity> {
    public BvcCodEntity(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        animator = new BvcFishAnimator<>(this);
    }

    private final GeneralAnimator<BvcCodEntity> animator;
    public GeneralAnimator<BvcCodEntity> getAnimator() {
        return animator;
    }

    @Override
    public void tick() {
        super.tick();
        animator.tick();
    }

    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(Items.COD_BUCKET);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    protected @NotNull SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull MobSpawnType pReason, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnData, @org.jetbrains.annotations.Nullable CompoundTag pDataTag) {
        if (pReason == MobSpawnType.BUCKET) {
            return pSpawnData;
        } else {
            if (pSpawnData == null) {
                pSpawnData = new AgeableMob.AgeableMobGroupData(false);
            }

            this.setScale(Mth.randomBetween(this.random, 0.8f, 1f));

            return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        }
    }
}
