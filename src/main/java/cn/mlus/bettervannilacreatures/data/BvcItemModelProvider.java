package cn.mlus.bettervannilacreatures.data;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.init.BvcItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
public class BvcItemModelProvider extends ItemModelProvider {
    public BvcItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, BetterVannilaCreatures.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.simpleItem(BvcItems.HADDOCK_COD_SPAWN_EGG.get());
        this.simpleItem(BvcItems.ATLANTIC_COD_SPAWN_EGG.get());
        this.simpleItem(BvcItems.PACIFIC_COD_SPAWN_EGG.get());
        this.simpleItem(BvcItems.MALE_SALMON_SPAWN_EGG.get());
        this.simpleItem(BvcItems.FEMALE_SALMON_SPAWN_EGG.get());
        this.simpleItem(BvcItems.PACIFIC_SALMON_SPAWN_EGG.get());
        this.simpleItem(BvcItems.YELLOW_FIN_PUFFER_SPAWN_EGG.get());
        this.simpleItem(BvcItems.OBSCURE_PUFFER_SPAWN_EGG.get());
        this.simpleItem(BvcItems.NAUTILUS_SPAWN_EGG.get());

        this.simpleItem(BvcItems.HADDOCK_COD_BUCKET.get());
        this.simpleItem(BvcItems.ATLANTIC_COD_BUCKET.get());
        this.simpleItem(BvcItems.PACIFIC_COD_BUCKET.get());
        this.simpleItem(BvcItems.MALE_SALMON_BUCKET.get());
        this.simpleItem(BvcItems.FEMALE_SALMON_BUCKET.get());
        this.simpleItem(BvcItems.PACIFIC_SALMON_BUCKET.get());
        this.simpleItem(BvcItems.YELLOW_FIN_PUFFER_BUCKET.get());
        this.simpleItem(BvcItems.OBSCURE_PUFFER_BUCKET.get());
        this.simpleItem(BvcItems.NAUTILUS_BUCKET.get());

        this.simpleItem(BvcItems.HADDOCK_COD.get());
        this.simpleItem(BvcItems.ATLANTIC_COD.get());
        this.simpleItem(BvcItems.PACIFIC_COD.get());
        this.simpleItem(BvcItems.MALE_SALMON.get());
        this.simpleItem(BvcItems.FEMALE_SALMON.get());
        this.simpleItem(BvcItems.PACIFIC_SALMON.get());
        this.simpleItem(BvcItems.YELLOW_FIN_PUFFER.get());
        this.simpleItem(BvcItems.OBSCURE_PUFFER.get());

        this.simpleItem(BvcItems.HADDOCK_COD_SPECIMEN.get());
        this.simpleItem(BvcItems.ATLANTIC_COD_SPECIMEN.get());
        this.simpleItem(BvcItems.PACIFIC_COD_SPECIMEN.get());
        this.simpleItem(BvcItems.PACIFIC_SALMON_SPECIMEN.get());
        this.simpleItem(BvcItems.MALE_SALMON_SPECIMEN.get());
        this.simpleItem(BvcItems.FEMALE_SALMON_SPECIMEN.get());
    }

    private void simpleItem(Item item) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        this.withExistingParent(path, this.mcLoc("item/generated"))
                .texture("layer0", this.modLoc("item/" + path));
    }
}
