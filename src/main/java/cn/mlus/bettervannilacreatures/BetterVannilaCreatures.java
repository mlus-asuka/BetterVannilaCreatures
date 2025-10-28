package cn.mlus.bettervannilacreatures;

import cn.mlus.bettervannilacreatures.init.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

import java.util.Locale;

@Mod(BetterVannilaCreatures.MODID)
public class BetterVannilaCreatures {
    public static final String MODID = "bettervannilacreatures";

    public BetterVannilaCreatures(FMLJavaModLoadingContext context) {
        GeckoLib.initialize();

        IEventBus eventBus = context.getModEventBus();
        BvcEntities.register(eventBus);
        BvcBlocks.register(eventBus);
        BvcBlockEntities.register(eventBus);
        BvcItems.register(eventBus);
        BvcCreativeTab.register(eventBus);
        BvcBiomeModifierSerializers.register(eventBus);
        BvcMobEffects.register(eventBus);
    }

    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
    }
}
