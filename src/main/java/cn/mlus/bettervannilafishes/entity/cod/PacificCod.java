package cn.mlus.bettervannilafishes.entity.cod;

import cn.mlus.bettervannilafishes.init.BvcItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PacificCod extends BvcCodEntity {
    public PacificCod(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(BvcItems.PACIFIC_COD_BUCKET.get());
    }
}
