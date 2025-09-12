package cn.mlus.bettervannilacreatures.client.model.entity;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.BvcSalmonEntity;
import net.minecraft.resources.ResourceLocation;

public class SalmonModel extends BvcModel<BvcSalmonEntity>{
    @Override
    public ResourceLocation getAnimationResource(BvcSalmonEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID,"animations/entity/salmon.animation.json");
    }
}
