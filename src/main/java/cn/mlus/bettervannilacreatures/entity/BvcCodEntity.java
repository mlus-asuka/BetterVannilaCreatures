package cn.mlus.bettervannilacreatures.entity;

import cn.mlus.bettervannilacreatures.client.animator.BvcFishAnimator;
import cn.mlus.bettervannilacreatures.client.animator.GeneralAnimator;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.IntFunction;

public class BvcCodEntity extends BvcAbstractFish<BvcCodEntity> implements VariantHolder<BvcCodEntity.Variant>, BvcEntity<BvcCodEntity>{
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
            RandomSource $$6 = pLevel.getRandom();
            if (pSpawnData == null) {
                pSpawnData = new FishGroupData(Variant.getCommonSpawnVariant($$6));
            }

            this.setVariant(((FishGroupData)pSpawnData).getVariant($$6));
            this.setScale(Mth.randomBetween(this.random, 0.8f, 1f));

            return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        }
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(Variant.byId(compound.getInt("Variant")));
    }

    public enum Variant implements StringRepresentable {
        HADDOCK(0, "haddock", true),
        ATLANTIC(1, "atlantic", true),
        PACIFIC(2, "pacific", true);

        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        private final int id;
        private final String name;
        private final boolean common;

        Variant(int pId, String pName, boolean pCommon) {
            this.id = pId;
            this.name = pName;
            this.common = pCommon;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public @NotNull String getSerializedName() {
            return this.name;
        }

        public static Variant byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static Variant getCommonSpawnVariant(RandomSource pRandom) {
            return getSpawnVariant(pRandom);
        }

        private static Variant getSpawnVariant(RandomSource pRandom) {
            Variant[] $$2 = Arrays.stream(values()).filter((p_149252_) -> p_149252_.common).toArray(Variant[]::new);
            return Util.getRandom($$2, pRandom);
        }
    }
    public static class FishGroupData extends AgeableMob.AgeableMobGroupData {
        public final Variant[] types;

        public FishGroupData(Variant... pTypes) {
            super(false);
            this.types = pTypes;
        }

        public Variant getVariant(RandomSource pRandom) {
            return this.types[pRandom.nextInt(this.types.length)];
        }
    }
}
