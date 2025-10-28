package cn.mlus.bettervannilacreatures.entity.nautilus;

import cn.mlus.bettervannilacreatures.client.animator.BvcNautilusAnimator;
import cn.mlus.bettervannilacreatures.client.animator.GeneralAnimator;
import cn.mlus.bettervannilacreatures.entity.BvcEntity;
import cn.mlus.bettervannilacreatures.entity.GeneralBodyControl;
import cn.mlus.bettervannilacreatures.entity.ai.BvcFollowOwnerGoal;
import cn.mlus.bettervannilacreatures.init.BvcItems;
import cn.mlus.bettervannilacreatures.init.BvcMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Predicate;

public class NautilusEntity extends TamableAnimal implements GeoEntity, BvcEntity<NautilusEntity>, Bucketable {
    public NautilusEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        animator = new BvcNautilusAnimator(this);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10,0.02F,0.1F,true);;
        this.lookControl = new SmoothSwimmingLookControl(this,10);
    }

    protected @NotNull PathNavigation createNavigation(@NotNull Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(NautilusEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> FEEDING_TICK = SynchedEntityData.defineId(NautilusEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SHELTER_TICK = SynchedEntityData.defineId(NautilusEntity.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final GeneralAnimator<NautilusEntity> animator;
    private static final Predicate<LivingEntity> SCARY_MOB;
    static final TargetingConditions targetingConditions;

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new GeneralBodyControl(this);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED,0.4)
                .add(ForgeMod.SWIM_SPEED.get(),1);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(FEEDING_TICK, 0);
        this.entityData.define(SHELTER_TICK, 0);
    }
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }

    public int getFeedingTick(){
        return this.entityData.get(FEEDING_TICK);
    }

    public void setFeedingTick(int tick){
        this.entityData.set(FEEDING_TICK, tick);
    }

    public int getShelterTick(){
        return this.entityData.get(SHELTER_TICK);
    }

    public void setShelterTick(int tick){
        this.entityData.set(SHELTER_TICK, tick);
    }

    @Override
    public void saveToBucketTag(ItemStack pStack) {
        this.addAdditionalSaveData(pStack.getOrCreateTag());
    }
    @Override
    public void loadFromBucketTag(@NotNull CompoundTag pTag) {
        this.readAdditionalSaveData(pTag);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
    }

    @Override
    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(BvcItems.NAUTILUS_BUCKET.get());
    }

    @Override
    public @NotNull SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if(level().isClientSide){
            animator.tick();
        }else {
            if(temptGoal != null && temptGoal.isRunning()){
                setFeedingTick(getFeedingTick() + 1);
            }else {
                setFeedingTick(Math.max(0, getFeedingTick() - 1));
            }

            if(getShelterTick() > 0){
                navigation.stop();
                setShelterTick(getShelterTick() -1);
            }

            if(tickCount % 100 == 0){
                List<Entity> entities = level().getEntities(this, this.getBoundingBox().inflate(4.0),
                        entity -> entity instanceof Player);
                entities.forEach(entity -> {
                    if (entity instanceof Player player) {
                       player.addEffect(new MobEffectInstance(BvcMobEffects.NAUTILUS_BLESSING.get(),160,0,false,false,true));
                    }
                });
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        AnimationController<NautilusEntity> main = new AnimationController<>(this, "main", 2, state -> {
            RawAnimation builder = RawAnimation.begin();
            if(getShelterTick() >0){
                builder.thenLoop("animation.shelter");
            }else {
                if(temptGoal != null && temptGoal.isRunning()){
                    if (state.isMoving()) {
                        builder.thenLoop("animation.swimming_feeding");
                    } else {
                        builder.thenLoop("animation.idle_feeding");
                    }
                }else {
                    if (state.isMoving()) {
                        builder.thenLoop("animation.swimming");
                    } else {
                        builder.thenLoop("animation.idle");
                    }
                }
            }

            return state.setAndContinue(builder);
        });

        controllerRegistrar.add(main);
    }

    public TemptGoal temptGoal;

    @Override
    protected void registerGoals() {
        temptGoal = new TemptGoal(this, 1.25, Ingredient.of(Items.TROPICAL_FISH), false);
        this.goalSelector.addGoal(0, new PanicGoal(this, 0){
            @Override
            public void start() {
                super.start();
                setShelterTick(12000);
                NautilusEntity.this.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,1200,5,false,false,true));
            }
        });
        this.goalSelector.addGoal(0, new ShelterGoal(this));
        this.goalSelector.addGoal(1, new BvcFollowOwnerGoal(this, 6.0F, 3.0F, false));
        this.goalSelector.addGoal(3, temptGoal);
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,1,40));
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand pHand) {
        if (!level().isClientSide() && isFood(player.getMainHandItem())) {
            if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, player)){
                this.tame(player);
                this.level().broadcastEntityEvent(this, (byte)7);
            }else{
                this.level().broadcastEntityEvent(this, (byte)6);
            }
        }
        return super.mobInteract(player, pHand);
    }

    @Override
    public boolean isFood(@NotNull ItemStack pStack) {
        return pStack.is(Items.TROPICAL_FISH);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public GeneralAnimator<NautilusEntity> getAnimator() {
        return animator;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }

    static {
        SCARY_MOB = (p_289442_) -> {
            if (p_289442_ instanceof Player player) {
                return !player.isCreative() && !player.getItemInHand(InteractionHand.MAIN_HAND).is(Items.TROPICAL_FISH);
            } else {
                return p_289442_.getMobType() != MobType.WATER;
            }
        };
        targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(SCARY_MOB);
    }

    private class ShelterGoal extends Goal {
        private final NautilusEntity fish;

        public ShelterGoal(NautilusEntity pFish) {
            this.fish = pFish;
        }

        public boolean canUse() {
            List<LivingEntity> $$0 = this.fish.level().getEntitiesOfClass(LivingEntity.class, this.fish.getBoundingBox().inflate(2.0), (p_149015_) -> targetingConditions.test(this.fish, p_149015_));
            if($$0.size() == 1 && $$0.get(0) instanceof Player player){
               return !fish.isOwnedBy(player);
            }
            return !$$0.isEmpty();
        }

        public void start() {
           setShelterTick(12000);
           fish.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,1200,5,false,false,true));
        }
    }
}
