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
        this.add(BvcEntities.BVC_COD.get(),"鳕鱼");
        this.add(BvcEntities.BVC_SALMON_MALE.get(),"雄性鲑鱼");
        this.add(BvcEntities.BVC_SALMON_FEMALE.get(),"雌性鲑鱼");
        this.add(BvcEntities.BVC_SALMON_ATLANTIC.get(),"大西洋鲑鱼");
        //Item
        this.add(BvcItems.MALE_SALMON_SPAWN_EGG.get(),"雄性鲑鱼生成蛋");
        this.add(BvcItems.FEMALE_SALMON_SPAWN_EGG.get(),"雌性鲑鱼生成蛋");
        this.add(BvcItems.ATLANTIC_SALMON_SPAWN_EGG.get(),"大西洋鲑鱼生成蛋");
        this.add(BvcItems.MALE_SALMON_BUCKET.get(),"雄性鲑鱼桶");
        this.add(BvcItems.FEMALE_SALMON_BUCKET.get(),"雌性鲑鱼桶");
        this.add(BvcItems.ATLANTIC_SALMON_BUCKET.get(),"大西洋鲑鱼桶");
        this.add(BvcItems.MALE_SALMON.get(), "雄性鲑鱼");
        this.add(BvcItems.FEMALE_SALMON.get(), "雌性鲑鱼");
        this.add(BvcItems.ATLANTIC_SALMON.get(), "大西洋鲑鱼");
    }
}
