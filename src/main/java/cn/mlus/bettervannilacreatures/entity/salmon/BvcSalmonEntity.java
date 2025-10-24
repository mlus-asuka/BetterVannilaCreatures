package cn.mlus.bettervannilacreatures.entity.salmon;

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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

public abstract class BvcSalmonEntity extends BvcAbstractFish implements BvcEntity<BvcSalmonEntity> {
    public BvcSalmonEntity(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        animator = new BvcFishAnimator<>(this);
    }

    @Override
    public void tick() {
        super.tick();
        if(level().isClientSide)
            animator.tick();
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull MobSpawnType pReason, @org.jetbrains.annotations.Nullable SpawnGroupData pSpawnData, @org.jetbrains.annotations.Nullable CompoundTag pDataTag) {
        if (pReason == MobSpawnType.BUCKET) {
            return pSpawnData;
        } else {
            if (pSpawnData == null) {
                pSpawnData = new AgeableMob.AgeableMobGroupData(false);
            }

            this.setScale(Mth.randomBetween(this.random, 0.9f, 1.1f));

            return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        }
    }

    private final GeneralAnimator<BvcSalmonEntity> animator;

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.SALMON_HURT;
    }

    protected @NotNull SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    @Override
    public GeneralAnimator<BvcSalmonEntity> getAnimator() {
        return animator;
    }
}
