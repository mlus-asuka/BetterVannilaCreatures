package cn.mlus.bettervannilafishes.client.model.entity;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.entity.salmon.BvcSalmonEntity;
import net.minecraft.resources.ResourceLocation;

public class SalmonModel extends BvcModel<BvcSalmonEntity>{
    @Override
    public ResourceLocation getAnimationResource(BvcSalmonEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"animations/entity/salmon.animation.json");
    }
}
