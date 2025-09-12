package cn.mlus.bettervannilacreatures.entity;

import cn.mlus.bettervannilacreatures.client.animator.BvcFishAnimator;
import cn.mlus.bettervannilacreatures.client.animator.GeneralAnimator;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BvcSalmonEntity extends BvcAbstractFish implements BvcEntity<BvcSalmonEntity> {
    public BvcSalmonEntity(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        animator = new BvcFishAnimator<>(this);
    }

    @Override
    public void tick() {
        super.tick();
        animator.tick();
    }

    private final GeneralAnimator<BvcSalmonEntity> animator;

    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(Items.SALMON_BUCKET);
    }

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
