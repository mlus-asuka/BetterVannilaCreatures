package cn.mlus.bettervannilacreatures.entity;

import cn.mlus.bettervannilacreatures.client.animator.BvcFishAnimator;
import cn.mlus.bettervannilacreatures.client.animator.GeneralAnimator;
import cn.mlus.bettervannilacreatures.client.render.entity.ai.BvcFishMoveControl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class BvcAbstractFish<T extends BvcAbstractFish<?>> extends AbstractFish implements GeoEntity{

    public BvcAbstractFish(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new BvcFishMoveControl(this);
        this.lookControl = new SmoothSwimmingLookControl(this,10);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(BvcAbstractFish.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Float> SCALE = SynchedEntityData.defineId(BvcAbstractFish.class, EntityDataSerializers.FLOAT);

    public @NotNull BvcCodEntity.Variant getVariant() {
        return BvcCodEntity.Variant.byId(this.entityData.get(DATA_VARIANT));
    }

    public void setVariant(BvcCodEntity.Variant pVariant) {
        this.entityData.set(DATA_VARIANT, pVariant.getId());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_VARIANT, 0);
        entityData.define(SCALE, 1.0f);
    }

    public float getScale(){
        return entityData.get(SCALE);
    }

    public void setScale(float scale){
        entityData.set(SCALE, Math.clamp(scale, 0.8f, 1.1f));
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putFloat("Scale", getScale());
        compoundTag.putInt("Variant", this.getVariant().getId());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setScale(compound.getFloat("Scale"));
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pPose) {
        float scale = getScale();
        return this.getType().getDimensions().scale(scale);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        AnimationController<BvcAbstractFish<?>> main = new AnimationController<>(this, "main", 4, state -> {
            RawAnimation builder = RawAnimation.begin();
            if(isInWater()){
                if (isSprinting()) {
                    builder.thenLoop("animation.swim");
                } else {
                    builder.thenLoop("animation.slow_swim");
                }
            }else {
                builder.thenLoop("animation.flop");
            }

            return state.setAndContinue(builder);
        });

        controllerRegistrar.add(main);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Nullable
    public RandomSwimmingGoal randomSwimmingGoal;

    @Override
    protected void registerGoals() {
        this.randomSwimmingGoal = new RandomSwimmingGoal(this,1,40){
            @Nullable
            @Override
            protected Vec3 getPosition() {
                return BehaviorUtils.getRandomSwimmablePos(this.mob, 35, 7);
            }
        };

        this.goalSelector.addGoal(1, this.randomSwimmingGoal);
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.6, 1.4));
        this.goalSelector.addGoal(3, new TryFindWaterGoal(this));
    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new GeneralBodyControl(this);
    }
}
