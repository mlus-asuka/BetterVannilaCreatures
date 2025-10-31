package cn.mlus.bettervannilafishes.client.model.entity;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.block.FishSpecimen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class SpecimenModel<T extends BlockEntity & GeoAnimatable> extends GeoModel<T> {
    @Override
    public ResourceLocation getModelResource(BlockEntity entity) {
        String path = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(entity.getType()).getPath();
        int i = entity.getBlockState().getValue(FishSpecimen.HANGING);
        if(i == 1){
            path += "_wall";
        } else if(i == 2){
            path += "_hanging";
        }
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"geo/block/" + path + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlockEntity entity) {
        String path = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(entity.getType()).getPath();
        int i = entity.getBlockState().getValue(FishSpecimen.HANGING);
        if(i == 1){
            path += "_wall";
        } else if(i == 2){
            path += "_hanging";
        }
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"textures/block/" + path +".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlockEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(BetterVannilaFishes.MODID,"animations/entity/empty.animation.json");
    }
}
