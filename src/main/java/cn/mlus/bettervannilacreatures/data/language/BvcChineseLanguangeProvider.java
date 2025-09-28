package cn.mlus.bettervannilacreatures.data.language;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.init.BvcEntities;
import cn.mlus.bettervannilacreatures.init.BvcItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class BvcChineseLanguangeProvider extends LanguageProvider {
    public BvcChineseLanguangeProvider(PackOutput output) {
        super(output, BetterVannilaCreatures.MODID, "zh_cn");
    }
    @Override
    protected void addTranslations() {
        this.add(BvcEntities.BVC_HADDOCK_COD.get(),"黑线鳕鱼");
        this.add(BvcEntities.BVC_PACIFIC_COD.get(),"太平洋鳕鱼");
        this.add(BvcEntities.BVC_ATLANTIC_COD.get(),"大西洋鳕鱼");
        this.add(BvcEntities.BVC_SALMON_MALE.get(),"雄性鲑鱼");
        this.add(BvcEntities.BVC_SALMON_FEMALE.get(),"雌性鲑鱼");
        this.add(BvcEntities.BVC_SALMON_PACIFIC.get(),"太平洋鲑鱼");
        //Item
        this.add(BvcItems.HADDOCK_COD_SPAWN_EGG.get(), "黑线鳕鱼生成蛋");
        this.add(BvcItems.ATLANTIC_COD_SPAWN_EGG.get(), "大西洋鳕鱼生成蛋");
        this.add(BvcItems.PACIFIC_COD_SPAWN_EGG.get(), "太平洋鳕鱼生成蛋");
        this.add(BvcItems.MALE_SALMON_SPAWN_EGG.get(),"雄性鲑鱼生成蛋");
        this.add(BvcItems.FEMALE_SALMON_SPAWN_EGG.get(),"雌性鲑鱼生成蛋");
        this.add(BvcItems.PACIFIC_SALMON_SPAWN_EGG.get(),"太平洋鲑鱼生成蛋");
        this.add(BvcItems.HADDOCK_COD_BUCKET.get(), "黑线鳕鱼桶");
        this.add(BvcItems.ATLANTIC_COD_BUCKET.get(), "大西洋鳕鱼桶");
        this.add(BvcItems.PACIFIC_COD_BUCKET.get(), "太平洋鳕鱼桶");
        this.add(BvcItems.MALE_SALMON_BUCKET.get(),"雄性鲑鱼桶");
        this.add(BvcItems.FEMALE_SALMON_BUCKET.get(),"雌性鲑鱼桶");
        this.add(BvcItems.PACIFIC_SALMON_BUCKET.get(),"太平洋鲑鱼桶");
        this.add(BvcItems.HADDOCK_COD.get(), "黑线鳕鱼");
        this.add(BvcItems.ATLANTIC_COD.get(), "大西洋鳕鱼");
        this.add(BvcItems.PACIFIC_COD.get(), "太平洋鳕鱼");
        this.add(BvcItems.MALE_SALMON.get(), "雄性鲑鱼");
        this.add(BvcItems.FEMALE_SALMON.get(), "雌性鲑鱼");
        this.add(BvcItems.PACIFIC_SALMON.get(), "太平洋鲑鱼");
        this.add(BvcItems.ATLANTIC_COD_SPECIMEN.get(), "大西洋鳕鱼标本");
        this.add(BvcItems.PACIFIC_COD_SPECIMEN.get(), "太平洋鳕鱼标本");
        this.add(BvcItems.HADDOCK_COD_SPECIMEN.get(), "黑线鳕鱼标本");
        this.add(BvcItems.PACIFIC_SALMON_SPECIMEN.get(), "太平洋鲑鱼标本");
        this.add(BvcItems.MALE_SALMON_SPECIMEN.get(), "雄性鲑鱼标本");
        this.add(BvcItems.FEMALE_SALMON_SPECIMEN.get(), "雌性鲑鱼标本");
    }
}
