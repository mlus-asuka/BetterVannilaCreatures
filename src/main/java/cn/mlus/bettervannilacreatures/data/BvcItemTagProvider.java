package cn.mlus.bettervannilacreatures.data;

import cn.aurorian.ers.init.ErsTagKeys;
import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.init.BvcItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BvcItemTagProvider extends ItemTagsProvider{

    public BvcItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, BetterVannilaCreatures.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ItemTags.FISHES)
                .add(BvcItems.HADDOCK_COD.get())
                .add(BvcItems.ATLANTIC_COD.get())
                .add(BvcItems.PACIFIC_COD.get())
                .add(BvcItems.MALE_SALMON.get())
                .add(BvcItems.FEMALE_SALMON.get())
                .add(BvcItems.PACIFIC_SALMON.get());

        this.tag(ErsTagKeys.KNOWN_FISH)
                .add(BvcItems.HADDOCK_COD.get())
                .add(BvcItems.ATLANTIC_COD.get())
                .add(BvcItems.PACIFIC_COD.get())
                .add(BvcItems.MALE_SALMON.get())
                .add(BvcItems.FEMALE_SALMON.get())
                .add(BvcItems.PACIFIC_SALMON.get());
    }
}
