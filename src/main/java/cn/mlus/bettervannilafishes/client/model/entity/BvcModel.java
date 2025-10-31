package cn.mlus.bettervannilafishes.client.model.entity;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.entity.BvcEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class BvcModel<T extends Mob & GeoAnimatable & BvcEntity<T>> extends GeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"geo/entity/" + BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"textures/entity/" + BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getPath() +".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"animations/entity/" + BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getPath() + ".animation.json");
    }

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);
        animatable.getAnimator().setPartialTick(animationState.getPartialTick());
        animatable.getAnimator().animate(this, animationState);
    }
}
