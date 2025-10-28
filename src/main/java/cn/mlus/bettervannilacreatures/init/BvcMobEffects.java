package cn.mlus.bettervannilacreatures.init;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.effect.NautilusBlessingEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BvcMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BetterVannilaCreatures.MODID);
    public static final RegistryObject<MobEffect> NAUTILUS_BLESSING = MOB_EFFECTS.register("nautilus_blessing", () -> new NautilusBlessingEffect(MobEffectCategory.HARMFUL, 0x808080)
            .addAttributeModifier(Attributes.ATTACK_SPEED, "C1433E6F-6644-2C5A-AA46-7BA5BBADBEF3", 0.10000000149011612, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
