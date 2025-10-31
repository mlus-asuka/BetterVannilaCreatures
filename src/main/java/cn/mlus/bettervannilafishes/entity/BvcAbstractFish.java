package cn.mlus.bettervannilafishes.entity;

import cn.mlus.bettervannilafishes.entity.ai.BvcFishMoveControl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Predicate;

public abstract class BvcAbstractFish extends AbstractFish implements GeoEntity{

    public BvcAbstractFish(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new BvcFishMoveControl(this);
        this.lookControl = new SmoothSwimmingLookControl(this,10);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(BvcAbstractFish.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Float> SCALE = SynchedEntityData.defineId(BvcAbstractFish.class, EntityDataSerializers.FLOAT);

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

    @Nullable
    @Override
    public ItemEntity spawnAtLocation(@NotNull ItemStack pStack) {
        if(pStack.is(ItemTags.FISHES))
            pStack.getOrCreateTag().putFloat("Scale", getScale());
        return super.spawnAtLocation(pStack);
    }

    public void travel(@NotNull Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(pTravelVector);
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putFloat("Scale", getScale());
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
    public int getMaxSpawnClusterSize() {
        return 10;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        AnimationController<BvcAbstractFish> main = new AnimationController<>(this, "main", 0, state -> {
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
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED,0.6)
                .add(ForgeMod.SWIM_SPEED.get(),1);
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

        Predicate<Entity> var = EntitySelector.NO_SPECTATORS;
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1f, 2f, var::test){
            @Override
            public boolean canUse() {
                super.canUse();
                if (this.toAvoid == null) {
                    return false;
                } else {
                    Vec3 $$0 = DefaultRandomPos.getPosAway(this.mob, 32, 7, this.toAvoid.position());
                    if ($$0 == null) {
                        return false;
                    } else if (this.toAvoid.distanceToSqr($$0.x, $$0.y, $$0.z) < this.toAvoid.distanceToSqr(this.mob)) {
                        return false;
                    } else {
                        this.path = this.pathNav.createPath($$0.x, $$0.y, $$0.z, 0);
                        return this.path != null;
                    }
                }
            }

            @Override
            public void start() {
                super.start();
                this.mob.setSprinting(true);
            }

            @Override
            public void stop() {
                super.stop();
                this.mob.setSprinting(false);
            }
        });
        this.goalSelector.addGoal(2, this.randomSwimmingGoal);
        this.goalSelector.addGoal(3, new TryFindWaterGoal(this));
    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new GeneralBodyControl(this);
    }
}
