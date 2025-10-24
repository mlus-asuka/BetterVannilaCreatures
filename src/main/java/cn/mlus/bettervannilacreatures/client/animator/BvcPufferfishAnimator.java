package cn.mlus.bettervannilacreatures.client.animator;

import cn.mlus.bettervannilacreatures.entity.pufferfish.BvcPufferfishEntity;
import net.minecraft.util.Mth;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

import java.util.List;

public class BvcPufferfishAnimator extends GeneralAnimator<BvcPufferfishEntity>{
    public BvcPufferfishAnimator(BvcPufferfishEntity entity) {
        super(entity);
    }

    @Override
    public void animate(GeoModel<BvcPufferfishEntity> model, AnimationState<BvcPufferfishEntity> animationState) {
        if(animationState.getAnimatable().getPuffState() == 0)
            animTail(model);
    }

    protected void animTail(GeoModel<BvcPufferfishEntity> model){
        String[] tailBoneNames = {"tail_1","tail_2","tail_3"};
        List<GeoBone> tailBones = getBonesByName(tailBoneNames, model);
        for(int i = 0; i < tailBones.size(); i++){
            GeoBone tail = tailBones.get(i);
            float reversedIndex = tailBones.size() - i;
            float logFactor = (float)(Math.log(reversedIndex + 1) / Math.log(tailBones.size() + 1));

            float angleLimit = 160 * logFactor;
            float pitchOfs = Mth.clamp(pitchTrail.get(partialTicks, 0, i + 1) * 0.13f, -angleLimit, angleLimit);
            float yawOfs = Mth.clamp(yawTrail.get(partialTicks, 0, i + 1) * 0.13f, -angleLimit, angleLimit);

            if (i < 3) {
                float extraRotationFactor = 7.5f - (i * 0.3f);
                pitchOfs *= extraRotationFactor;
                yawOfs *= extraRotationFactor;
            }else {
                pitchOfs *= 3.5f;
                yawOfs *=  3.5f;
            }

            tail.setRotX((float) (tail.getRotX() + Math.toRadians(pitchOfs)));
            tail.setRotY((float) (tail.getRotY() + Math.toRadians(yawOfs)));
        }
    }
}
