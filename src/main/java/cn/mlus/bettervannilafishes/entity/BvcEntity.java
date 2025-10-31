package cn.mlus.bettervannilafishes.entity;

import cn.mlus.bettervannilafishes.client.animator.GeneralAnimator;
import net.minecraft.world.entity.Mob;
import software.bernie.geckolib.core.animatable.GeoAnimatable;

public interface BvcEntity<T extends Mob & GeoAnimatable> {
    GeneralAnimator<T> getAnimator();
    float getScale();
}
