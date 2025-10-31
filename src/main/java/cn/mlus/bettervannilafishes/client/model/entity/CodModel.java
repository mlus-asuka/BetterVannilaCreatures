package cn.mlus.bettervannilafishes.client.model.entity;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.entity.cod.BvcCodEntity;
import net.minecraft.resources.ResourceLocation;

public class CodModel extends BvcModel<BvcCodEntity>{
    @Override
    public ResourceLocation getModelResource(BvcCodEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"geo/entity/cod.geo.json");
    }

    @Override
    public ResourceLocation getAnimationResource(BvcCodEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"animations/entity/cod.animation.json");
    }
}
