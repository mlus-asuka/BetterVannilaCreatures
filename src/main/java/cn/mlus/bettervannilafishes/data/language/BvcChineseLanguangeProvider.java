package cn.mlus.bettervannilafishes.data.language;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.init.BvcEntities;
import cn.mlus.bettervannilafishes.init.BvcItems;
import cn.mlus.bettervannilafishes.init.BvcMobEffects;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class BvcChineseLanguangeProvider extends LanguageProvider {
    public BvcChineseLanguangeProvider(PackOutput output) {
        super(output, BetterVannilaFishes.MODID, "zh_cn");
    }
    @Override
    protected void addTranslations() {
        this.add(BvcEntities.BVC_HADDOCK_COD.get(),"黑线鳕鱼");
        this.add(BvcEntities.BVC_PACIFIC_COD.get(),"太平洋鳕鱼");
        this.add(BvcEntities.BVC_ATLANTIC_COD.get(),"大西洋鳕鱼");
        this.add(BvcEntities.BVC_SALMON_MALE.get(),"雄性鲑鱼");
        this.add(BvcEntities.BVC_SALMON_FEMALE.get(),"雌性鲑鱼");
        this.add(BvcEntities.BVC_SALMON_PACIFIC.get(),"太平洋鲑鱼");
        this.add(BvcEntities.YELLOW_FIN_PUFFER.get(),"黄鳍东方鲀");
        this.add(BvcEntities.OBSCURE_PUFFER.get(),"暗纹东方鲀");
        this.add(BvcEntities.NAUTILUS.get(),"鹦鹉螺");
        //Item
        this.add(BvcItems.HADDOCK_COD_SPAWN_EGG.get(), "黑线鳕鱼生成蛋");
        this.add(BvcItems.ATLANTIC_COD_SPAWN_EGG.get(), "大西洋鳕鱼生成蛋");
        this.add(BvcItems.PACIFIC_COD_SPAWN_EGG.get(), "太平洋鳕鱼生成蛋");
        this.add(BvcItems.MALE_SALMON_SPAWN_EGG.get(),"雄性鲑鱼生成蛋");
        this.add(BvcItems.FEMALE_SALMON_SPAWN_EGG.get(),"雌性鲑鱼生成蛋");
        this.add(BvcItems.PACIFIC_SALMON_SPAWN_EGG.get(),"太平洋鲑鱼生成蛋");
        this.add(BvcItems.YELLOW_FIN_PUFFER_SPAWN_EGG.get(),"黄鳍东方鲀生成蛋");
        this.add(BvcItems.OBSCURE_PUFFER_SPAWN_EGG.get(),"暗纹东方鲀生成蛋");
        this.add(BvcItems.NAUTILUS_SPAWN_EGG.get(),"鹦鹉螺生成蛋");
        this.add(BvcItems.HADDOCK_COD_BUCKET.get(), "黑线鳕鱼桶");
        this.add(BvcItems.ATLANTIC_COD_BUCKET.get(), "大西洋鳕鱼桶");
        this.add(BvcItems.PACIFIC_COD_BUCKET.get(), "太平洋鳕鱼桶");
        this.add(BvcItems.MALE_SALMON_BUCKET.get(),"雄性鲑鱼桶");
        this.add(BvcItems.FEMALE_SALMON_BUCKET.get(),"雌性鲑鱼桶");
        this.add(BvcItems.PACIFIC_SALMON_BUCKET.get(),"太平洋鲑鱼桶");
        this.add(BvcItems.YELLOW_FIN_PUFFER_BUCKET.get(),"黄鳍东方鲀桶");
        this.add(BvcItems.OBSCURE_PUFFER_BUCKET.get(),"暗纹东方鲀桶");
        this.add(BvcItems.NAUTILUS_BUCKET.get(),"鹦鹉螺桶");
        this.add(BvcItems.HADDOCK_COD.get(), "黑线鳕鱼");
        this.add(BvcItems.ATLANTIC_COD.get(), "大西洋鳕鱼");
        this.add(BvcItems.PACIFIC_COD.get(), "太平洋鳕鱼");
        this.add(BvcItems.MALE_SALMON.get(), "雄性鲑鱼");
        this.add(BvcItems.FEMALE_SALMON.get(), "雌性鲑鱼");
        this.add(BvcItems.PACIFIC_SALMON.get(), "太平洋鲑鱼");
        this.add(BvcItems.YELLOW_FIN_PUFFER.get(), "黄鳍东方鲀");
        this.add(BvcItems.OBSCURE_PUFFER.get(), "暗纹东方鲀");
        this.add(BvcItems.ATLANTIC_COD_SPECIMEN.get(), "大西洋鳕鱼标本");
        this.add(BvcItems.PACIFIC_COD_SPECIMEN.get(), "太平洋鳕鱼标本");
        this.add(BvcItems.HADDOCK_COD_SPECIMEN.get(), "黑线鳕鱼标本");
        this.add(BvcItems.PACIFIC_SALMON_SPECIMEN.get(), "太平洋鲑鱼标本");
        this.add(BvcItems.MALE_SALMON_SPECIMEN.get(), "雄性鲑鱼标本");
        this.add(BvcItems.FEMALE_SALMON_SPECIMEN.get(), "雌性鲑鱼标本");
        this.add(BvcItems.ENCHANTED_PUFFERFISH.get(), "附魔河豚");

        this.add(BvcMobEffects.NAUTILUS_BLESSING.get(), "鹦鹉螺祝福");

        this.add("bvc.command.hanging", "闲逛");
        this.add("bvc.command.follow", "跟随");

        this.add("advancements.bettervannilafishes.monster_go_away.title","妖魔鬼怪快离开");
        this.add("advancements.bettervannilafishes.monster_go_away.desc","制作一个附魔河豚来对抗怪物。");
    }
}
