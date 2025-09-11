package cn.mlus.bettervannilacreatures.client.model.entity;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.BvcCodEntity;
import net.minecraft.resources.ResourceLocation;

public class CodModel extends BvcModel<BvcCodEntity>{
    @Override
    public ResourceLocation getTextureResource(BvcCodEntity entity) {
        return switch (entity.getVariant().getId()) {
            case 1 -> ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "textures/entity/cod_atlantic.png");
            case 2 -> ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "textures/entity/cod_pacific.png");
            default -> ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "textures/entity/cod_haddock.png");
        };
    }
}
