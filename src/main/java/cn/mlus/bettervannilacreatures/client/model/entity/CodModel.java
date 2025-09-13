package cn.mlus.bettervannilacreatures.client.model.entity;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.cod.BvcCodEntity;
import net.minecraft.resources.ResourceLocation;

public class CodModel extends BvcModel<BvcCodEntity>{
    @Override
    public ResourceLocation getModelResource(BvcCodEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID,"geo/entity/cod.geo.json");
    }

    @Override
    public ResourceLocation getAnimationResource(BvcCodEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID,"animations/entity/cod.animation.json");
    }
}
