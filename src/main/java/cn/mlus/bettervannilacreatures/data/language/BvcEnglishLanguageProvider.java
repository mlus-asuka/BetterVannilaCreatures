package cn.mlus.bettervannilacreatures.data.language;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.init.BvcEntities;
import cn.mlus.bettervannilacreatures.init.BvcItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class BvcEnglishLanguageProvider extends LanguageProvider {

    public BvcEnglishLanguageProvider(PackOutput output) {
        super(output, BetterVannilaCreatures.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(BvcEntities.BVC_HADDOCK_COD.get(),"Haddock Cod");
        this.add(BvcEntities.BVC_ATLANTIC_COD.get(),"Atlantic Cod");
        this.add(BvcEntities.BVC_PACIFIC_COD.get(),"Pacific Cod");
        this.add(BvcEntities.BVC_SALMON_MALE.get(),"Male Salmon");
        this.add(BvcEntities.BVC_SALMON_FEMALE.get(),"Female Salmon");
        this.add(BvcEntities.BVC_SALMON_PACIFIC.get(),"Pacific Salmon");
        //Item
        this.add(BvcItems.HADDOCK_COD_SPAWN_EGG.get(), "Haddock Cod Spawn Egg");
        this.add(BvcItems.ATLANTIC_COD_SPAWN_EGG.get(), "Atlantic Cod Spawn Egg");
        this.add(BvcItems.PACIFIC_COD_SPAWN_EGG.get(), "Pacific Cod Spawn Egg");
        this.add(BvcItems.MALE_SALMON_SPAWN_EGG.get(),"Male Salmon Spawn Egg");
        this.add(BvcItems.FEMALE_SALMON_SPAWN_EGG.get(),"Female Salmon Spawn Egg");
        this.add(BvcItems.PACIFIC_SALMON_SPAWN_EGG.get(),"Atlantic Salmon Spawn Egg");
        this.add(BvcItems.HADDOCK_COD_BUCKET.get(), "Haddock Cod Bucket");
        this.add(BvcItems.ATLANTIC_COD_BUCKET.get(), "Atlantic Cod Bucket");
        this.add(BvcItems.PACIFIC_COD_BUCKET.get(), "Pacific Cod Bucket");
        this.add(BvcItems.MALE_SALMON_BUCKET.get(),"Male Salmon Bucket");
        this.add(BvcItems.FEMALE_SALMON_BUCKET.get(),"Female Salmon Bucket");
        this.add(BvcItems.PACIFIC_SALMON_BUCKET.get(),"Pacific Salmon Bucket");
        this.add(BvcItems.HADDOCK_COD.get(), "Haddock Cod");
        this.add(BvcItems.ATLANTIC_COD.get(), "Atlantic Cod");
        this.add(BvcItems.PACIFIC_COD.get(), "Pacific Cod");
        this.add(BvcItems.MALE_SALMON.get(), "Male Salmon");
        this.add(BvcItems.FEMALE_SALMON.get(), "Female Salmon");
        this.add(BvcItems.PACIFIC_SALMON.get(), "Pacific Salmon");
    }
    
}
