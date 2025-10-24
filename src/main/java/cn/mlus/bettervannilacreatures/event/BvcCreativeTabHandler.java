package cn.mlus.bettervannilacreatures.event;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.init.BvcItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterVannilaCreatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BvcCreativeTabHandler {
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(BvcItems.HADDOCK_COD_SPAWN_EGG.get());
            event.accept(BvcItems.ATLANTIC_COD_SPAWN_EGG.get());
            event.accept(BvcItems.PACIFIC_COD_SPAWN_EGG.get());
            event.accept(BvcItems.MALE_SALMON_SPAWN_EGG.get());
            event.accept(BvcItems.FEMALE_SALMON_SPAWN_EGG.get());
            event.accept(BvcItems.PACIFIC_SALMON_SPAWN_EGG.get());
            event.accept(BvcItems.YELLOW_FIN_PUFFER_SPAWN_EGG.get());
            event.accept(BvcItems.OBSCURE_PUFFER_SPAWN_EGG.get());
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(BvcItems.HADDOCK_COD.get());
            event.accept(BvcItems.ATLANTIC_COD.get());
            event.accept(BvcItems.PACIFIC_COD.get());
            event.accept(BvcItems.MALE_SALMON.get());
            event.accept(BvcItems.FEMALE_SALMON.get());
            event.accept(BvcItems.PACIFIC_SALMON.get());
            event.accept(BvcItems.YELLOW_FIN_PUFFER.get());
            event.accept(BvcItems.OBSCURE_PUFFER.get());
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(BvcItems.ENCHANTED_PUFFERFISH.get());
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.getEntries().putAfter(Items.AXOLOTL_BUCKET.getDefaultInstance(),BvcItems.HADDOCK_COD_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.AXOLOTL_BUCKET.getDefaultInstance(),BvcItems.ATLANTIC_COD_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.AXOLOTL_BUCKET.getDefaultInstance(),BvcItems.PACIFIC_COD_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.AXOLOTL_BUCKET.getDefaultInstance(),BvcItems.MALE_SALMON_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.AXOLOTL_BUCKET.getDefaultInstance(),BvcItems.FEMALE_SALMON_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.AXOLOTL_BUCKET.getDefaultInstance(),BvcItems.PACIFIC_SALMON_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.AXOLOTL_BUCKET.getDefaultInstance(),BvcItems.YELLOW_FIN_PUFFER_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.AXOLOTL_BUCKET.getDefaultInstance(),BvcItems.OBSCURE_PUFFER_BUCKET.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(BvcItems.HADDOCK_COD_SPECIMEN.get());
            event.accept(BvcItems.ATLANTIC_COD_SPECIMEN.get());
            event.accept(BvcItems.PACIFIC_COD_SPECIMEN.get());
            event.accept(BvcItems.PACIFIC_SALMON_SPECIMEN.get());
            event.accept(BvcItems.MALE_SALMON_SPECIMEN.get());
            event.accept(BvcItems.FEMALE_SALMON_SPECIMEN.get());
        }
    }
}
