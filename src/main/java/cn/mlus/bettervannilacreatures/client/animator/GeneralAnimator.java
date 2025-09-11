package cn.mlus.bettervannilacreatures.client.animator;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneralAnimator<T extends Mob & GeoAnimatable> {
    protected final CircularBuffer yawTrail = new CircularBuffer(64);
    protected final CircularBuffer pitchTrail = new CircularBuffer(16);
    private final CircularBuffer yTrail = new CircularBuffer(8);

    protected T entity;
    protected float partialTicks;
    private boolean init;

    public GeneralAnimator(T entity){
        this.entity = entity;
        init = false;
    }

    public void tick(){
        if(!init){
            init = true;
            yawTrail.fill(-entity.yBodyRot);
            yTrail.fill((float) entity.getY());
            pitchTrail.fill(getModelPitch(partialTicks));
        }
        yawTrail.update(-entity.yBodyRot);
        yTrail.update((float) entity.getY());
        pitchTrail.update(getModelPitch(partialTicks));
    }
    public void animate(GeoModel<T> model, AnimationState<T> animationState) {}

    public float getModelPitch(float pt, float amp) {
        float pitchMovingMax = 90;
        return Mth.clamp(yTrail.get(pt, 5, 0) * amp, -pitchMovingMax, pitchMovingMax);
    }

    public float getModelPitch(float pt) {
        return getModelPitch(pt,10);
    }

    public List<GeoBone> getBonesByName(String[] boneNames, GeoModel<T> model){
        List<GeoBone> bones = new ArrayList<>();
        for(String boneName : boneNames){
            Optional<GeoBone> bone = model.getBone(boneName);
            bone.ifPresent(bones::add);
        }
        return bones;
    }

    public void setPartialTick(float partialTicks){
        this.partialTicks = partialTicks;
    }
}
       
