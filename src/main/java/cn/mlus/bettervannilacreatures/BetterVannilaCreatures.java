package cn.mlus.bettervannilacreatures;

import cn.mlus.bettervannilacreatures.init.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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

        context.getModEventBus().addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(BvcItems.MALE_SALMON_SPAWN_EGG.get());
            event.accept(BvcItems.FEMALE_SALMON_SPAWN_EGG.get());
            event.accept(BvcItems.ATLANTIC_SALMON_SPAWN_EGG.get());
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(BvcItems.MALE_SALMON.get());
            event.accept(BvcItems.FEMALE_SALMON.get());
            event.accept(BvcItems.ATLANTIC_SALMON.get());
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(BvcItems.MALE_SALMON_BUCKET.get());
            event.accept(BvcItems.FEMALE_SALMON_BUCKET.get());
            event.accept(BvcItems.ATLANTIC_SALMON_BUCKET.get());
        }
    }

    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
    }
}
