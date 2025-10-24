package cn.mlus.bettervannilacreatures.client.model.entity;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.pufferfish.BvcPufferfishEntity;
import net.minecraft.resources.ResourceLocation;

public class PufferfishModel extends BvcModel<BvcPufferfishEntity>{
    @Override
    public ResourceLocation getModelResource(BvcPufferfishEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID,"geo/entity/pufferfish.geo.json");
    }

    @Override
    public ResourceLocation getAnimationResource(BvcPufferfishEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID,"animations/entity/pufferfish.animation.json");
    }
}
