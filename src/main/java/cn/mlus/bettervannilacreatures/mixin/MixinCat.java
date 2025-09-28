package cn.mlus.bettervannilacreatures.mixin;

import cn.mlus.bettervannilacreatures.init.BvcItems;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cat.class)
public class MixinCat {
    private static final Ingredient food = Ingredient.of(BvcItems.HADDOCK_COD.get(), BvcItems.PACIFIC_COD.get(),BvcItems.ATLANTIC_COD.get(),
            BvcItems.MALE_SALMON.get(),BvcItems.FEMALE_SALMON.get(),BvcItems.PACIFIC_SALMON.get());
    @Inject(method = "isFood",at = @At("TAIL"), cancellable = true)
    public void isFood(ItemStack pStack, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(cir.getReturnValue() || food.test(pStack));
    }
}
