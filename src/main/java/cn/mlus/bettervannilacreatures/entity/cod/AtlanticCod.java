package cn.mlus.bettervannilacreatures.entity.cod;

import cn.mlus.bettervannilacreatures.init.BvcItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AtlanticCod extends BvcCodEntity {
    public AtlanticCod(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public @NotNull ItemStack getBucketItemStack() {
        return new ItemStack(BvcItems.ATLANTIC_COD_BUCKET.get());
    }
}
