package cn.mlus.bettervannilafishes.client.render.entity;

import cn.mlus.bettervannilafishes.client.animator.GeneralAnimator;
import cn.mlus.bettervannilafishes.client.model.entity.BvcModel;
import cn.mlus.bettervannilafishes.entity.BvcEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Mob;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BvcRenderer<T extends Mob & BvcEntity<T> & GeoAnimatable> extends GeoEntityRenderer<T> {
    public BvcRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BvcModel<>());
        this.XRotDegree = 50;
    }

    public BvcRenderer(EntityRendererProvider.Context renderManager, float XRotDegree) {
        super(renderManager, new BvcModel<>());
        this.XRotDegree = XRotDegree;
    }

    public BvcRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model) {
        super(renderManager, model);
        this.XRotDegree = 50;
    }

    public BvcRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model, float XRotDegree) {
        super(renderManager, model);
        this.XRotDegree = XRotDegree;
    }

    float XRotDegree;

    @Override
    protected void applyRotations(T animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
        GeneralAnimator<? extends T> animator = animatable.getAnimator();
        float scale = animatable.getScale();
        poseStack.scale(scale, scale, scale);

        if(animator != null)
            poseStack.mulPose(Axis.XP.rotationDegrees(animator.getModelPitch(partialTick, XRotDegree)));
    }
}
