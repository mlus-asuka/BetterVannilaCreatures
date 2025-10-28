package cn.mlus.bettervannilacreatures.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class NautilusBlessingEffect extends MobEffect {
    public NautilusBlessingEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setAirSupply(pLivingEntity.getMaxAirSupply());
        if(pLivingEntity.hasEffect(MobEffects.DIG_SLOWDOWN))
            pLivingEntity.removeEffect(MobEffects.DIG_SLOWDOWN);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
