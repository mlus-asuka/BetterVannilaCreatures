package cn.mlus.bettervannilafishes.client.model.entity;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.entity.pufferfish.BvcPufferfishEntity;
import net.minecraft.resources.ResourceLocation;

public class PufferfishModel extends BvcModel<BvcPufferfishEntity>{
    @Override
    public ResourceLocation getModelResource(BvcPufferfishEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"geo/entity/pufferfish.geo.json");
    }

    @Override
    public ResourceLocation getAnimationResource(BvcPufferfishEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"animations/entity/pufferfish.animation.json");
    }
}
