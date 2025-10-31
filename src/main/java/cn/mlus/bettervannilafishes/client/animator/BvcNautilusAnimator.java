package cn.mlus.bettervannilafishes.client.animator;

import cn.mlus.bettervannilafishes.entity.nautilus.NautilusEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class BvcNautilusAnimator extends GeneralAnimator<NautilusEntity>{
    private final NautilusEntity entity;
    public BvcNautilusAnimator(NautilusEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void animate(GeoModel<NautilusEntity> model, AnimationState<NautilusEntity> animationState) {
        animBody(model);
    }

    protected void animBody(GeoModel<NautilusEntity> model){
        GeoBone body = model.getBone("root").get();
        if(entity.isInWater() && entity.getFeedingTick() > 0){
            body.setRotY((float) Math.min(Math.PI, Math.PI / 40 * entity.getFeedingTick()));
        }else {
            body.setRotY((float) Math.max(0, Math.PI / 40 * entity.getFeedingTick()));
        }
    }
}
