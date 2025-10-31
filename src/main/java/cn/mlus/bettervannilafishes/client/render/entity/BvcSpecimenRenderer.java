package cn.mlus.bettervannilafishes.client.render.entity;

import cn.mlus.bettervannilafishes.block.be.FishSpecimenBlockEntity;
import cn.mlus.bettervannilafishes.client.model.entity.SpecimenModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BvcSpecimenRenderer extends GeoBlockRenderer<FishSpecimenBlockEntity> {
    public BvcSpecimenRenderer() {
        super(new SpecimenModel<>());
    }

    @Override
    public void preRender(PoseStack poseStack, FishSpecimenBlockEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        float scale = animatable.getScale();
        poseStack.scale(scale, scale, scale);
    }
}
