package cn.mlus.bettervannilacreatures.entity.salmon;

import cn.mlus.bettervannilacreatures.init.BvcItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class FemaleSalmon extends BvcSalmonEntity {
    public FemaleSalmon(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(BvcItems.FEMALE_SALMON_BUCKET.get());
    }
}
