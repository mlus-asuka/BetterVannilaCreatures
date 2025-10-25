package cn.mlus.bettervannilacreatures.entity.pufferfish;

import cn.mlus.bettervannilacreatures.client.animator.BvcPufferfishAnimator;
import cn.mlus.bettervannilacreatures.client.animator.GeneralAnimator;
import cn.mlus.bettervannilacreatures.entity.BvcEntity;
import cn.mlus.bettervannilacreatures.entity.GeneralBodyControl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Predicate;

public abstract class BvcPufferfishEntity extends AbstractFish implements GeoEntity, BvcEntity<BvcPufferfishEntity> {
    public BvcPufferfishEntity(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.refreshDimensions();
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10,0.02F,0.1F,true);
        this.lookControl = new SmoothSwimmingLookControl(this,10);
        animator = new BvcPufferfishAnimator(this);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        AnimationController<BvcPufferfishEntity> main = new AnimationController<>(this, "main", 2, state -> {
            RawAnimation builder = RawAnimation.begin();
            if(isInWater()){
                if (state.isMoving()) {
                    if(getPuffState() == 0)
                        builder.thenLoop("animation.swim");
                    else
                        builder.thenLoop("animation.swell_swim");
                } else {
                    if(getPuffState() == 0)
                        builder.thenLoop("animation.idle");
                    else
                        builder.thenLoop("animation.swell_idle");
                }
            }else {
                builder.thenLoop("animation.flop");
            }

            return state.setAndContinue(builder);
        });

        AnimationController<BvcPufferfishEntity> control = new AnimationController<>(this, "control", 0, state -> {
            RawAnimation builder = RawAnimation.begin();

            if(getPuffState() != 0){
                builder.thenPlay("animation.swell");
            }else {
                builder.thenPlay("animation.deflate");
            }
            return state.setAndContinue(builder);
        });

        controllerRegistrar.add(main,control);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new GeneralBodyControl(this);
    }

    private static final EntityDataAccessor<Integer> PUFF_STATE;
    int inflateCounter;
    int deflateTimer;
    private static final Predicate<LivingEntity> SCARY_MOB;
    static final TargetingConditions targetingConditions;

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PUFF_STATE, 0);
    }

    public int getPuffState() {
        return this.entityData.get(PUFF_STATE);
    }

    public void setPuffState(int pPuffState) {
        this.entityData.set(PUFF_STATE, pPuffState);
    }

    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> pKey) {
        if (PUFF_STATE.equals(pKey)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(pKey);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED,0.45)
                .add(ForgeMod.SWIM_SPEED.get(),1);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("PuffState", this.getPuffState());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setPuffState(Math.min(pCompound.getInt("PuffState"), 2));
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PufferfishPuffGoal(this));
    }

    public void tick() {
        if (!this.level().isClientSide && this.isAlive() && this.isEffectiveAi()) {
            if (this.inflateCounter > 0) {
                if (this.getPuffState() == 0) {
                    this.playSound(SoundEvents.PUFFER_FISH_BLOW_UP, this.getSoundVolume(), this.getVoicePitch());
                    this.setPuffState(1);
                } else if (this.inflateCounter > 40 && this.getPuffState() == 1) {
                    this.playSound(SoundEvents.PUFFER_FISH_BLOW_UP, this.getSoundVolume(), this.getVoicePitch());
                    this.setPuffState(2);
                }

                ++this.inflateCounter;
            } else if (this.getPuffState() != 0) {
                if (this.deflateTimer > 60 && this.getPuffState() == 2) {
                    this.playSound(SoundEvents.PUFFER_FISH_BLOW_OUT, this.getSoundVolume(), this.getVoicePitch());
                    this.setPuffState(1);
                } else if (this.deflateTimer > 100 && this.getPuffState() == 1) {
                    this.playSound(SoundEvents.PUFFER_FISH_BLOW_OUT, this.getSoundVolume(), this.getVoicePitch());
                    this.setPuffState(0);
                }

                ++this.deflateTimer;
            }
        }

        super.tick();

        if(level().isClientSide){
            getAnimator().tick();
        }
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

    public void playerTouch(@NotNull Player pEntity) {
        int $$1 = this.getPuffState();
        if (pEntity instanceof ServerPlayer && $$1 > 0) {
            if (!this.isSilent()) {
                ((ServerPlayer)pEntity).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0F));
            }
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PUFFER_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PUFFER_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.PUFFER_FISH_HURT;
    }

    protected @NotNull SoundEvent getFlopSound() {
        return SoundEvents.PUFFER_FISH_FLOP;
    }

    public @NotNull EntityDimensions getDimensions(@NotNull Pose pPose) {
        return super.getDimensions(pPose).scale(getScale(this.getPuffState()));
    }

    private static float getScale(int pPuffState) {
        return switch (pPuffState) {
            case 0 -> 0.5F;
            case 1 -> 0.7F;
            default -> 1.0F;
        };
    }

    static {
        PUFF_STATE = SynchedEntityData.defineId(BvcPufferfishEntity.class, EntityDataSerializers.INT);
        SCARY_MOB = (p_289442_) -> {
            if (p_289442_ instanceof Player && ((Player)p_289442_).isCreative()) {
                return false;
            } else {
                return p_289442_.getType() == EntityType.AXOLOTL || p_289442_.getMobType() != MobType.WATER;
            }
        };
        targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(SCARY_MOB);
    }

    GeneralAnimator<BvcPufferfishEntity> animator;

    @Override
    public GeneralAnimator<BvcPufferfishEntity> getAnimator() {
        return animator;
    }

    private static class PufferfishPuffGoal extends Goal {
        private final BvcPufferfishEntity fish;

        public PufferfishPuffGoal(BvcPufferfishEntity pFish) {
            this.fish = pFish;
        }

        public boolean canUse() {
            List<LivingEntity> $$0 = this.fish.level().getEntitiesOfClass(LivingEntity.class, this.fish.getBoundingBox().inflate(2.0), (p_149015_) -> targetingConditions.test(this.fish, p_149015_));
            return !$$0.isEmpty();
        }

        public void start() {
            this.fish.inflateCounter = 1;
            this.fish.deflateTimer = 0;
        }

        public void stop() {
            this.fish.inflateCounter = 0;
        }
    }
}
