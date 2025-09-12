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
        this.add(BvcEntities.BVC_COD.get(),"Cod");
        this.add(BvcEntities.BVC_SALMON_MALE.get(),"Male Salmon");
        this.add(BvcEntities.BVC_SALMON_FEMALE.get(),"Female Salmon");
        this.add(BvcEntities.BVC_SALMON_ATLANTIC.get(),"Atlantic Salmon");
        //Item
        this.add(BvcItems.MALE_SALMON_SPAWN_EGG.get(),"Male Salmon Spawn Egg");
        this.add(BvcItems.FEMALE_SALMON_SPAWN_EGG.get(),"Female Salmon Spawn Egg");
        this.add(BvcItems.ATLANTIC_SALMON_SPAWN_EGG.get(),"Atlantic Salmon Spawn Egg");
        this.add(BvcItems.MALE_SALMON_BUCKET.get(),"Male Salmon Bucket");
        this.add(BvcItems.FEMALE_SALMON_BUCKET.get(),"Female Salmon Bucket");
        this.add(BvcItems.ATLANTIC_SALMON_BUCKET.get(),"Atlantic Salmon Bucket");
        this.add(BvcItems.MALE_SALMON.get(), "Male Salmon");
        this.add(BvcItems.FEMALE_SALMON.get(), "Female Salmon");
        this.add(BvcItems.ATLANTIC_SALMON.get(), "Atlantic Salmon");
    }
    
}
