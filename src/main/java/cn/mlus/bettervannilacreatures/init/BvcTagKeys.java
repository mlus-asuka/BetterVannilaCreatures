package cn.mlus.bettervannilacreatures.init;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BvcTagKeys {
    public static final TagKey<Item> PUFFER = create("puffer");

    public BvcTagKeys() {
    }

    private static TagKey<Item> create(String name) {
        return ItemTags.create(BetterVannilaCreatures.prefix(name));
    }
}
