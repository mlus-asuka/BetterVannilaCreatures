package cn.mlus.bettervannilacreatures.data;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.data.language.BvcChineseLanguangeProvider;
import cn.mlus.bettervannilacreatures.data.language.BvcEnglishLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = BetterVannilaCreatures.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new BvcItemModelProvider(output,helper));
        generator.addProvider(event.includeClient(), new BvcEnglishLanguageProvider(output));
        generator.addProvider(event.includeClient(), new BvcChineseLanguangeProvider(output));
        generator.addProvider(event.includeServer(), new BvcRecipeProvider(output));
        generator.addProvider(event.includeServer(), new ModDatapackEntries(output, lookupProvider));
        generator.addProvider(event.includeServer(), new BvcLootTableProvider(output));

        BvcBlockTagProvider blockTagsProvider = new BvcBlockTagProvider(output, lookupProvider, helper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new BvcItemTagProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), helper));
    }
}