package cn.mlus.bettervannilacreatures.event;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.block.FishSpecimen;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterVannilaCreatures.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpecimenCraftHandler {
    @SubscribeEvent
    public static void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        Player player = event.getEntity();
        if (player == null) return;

        CraftingContainer inv = (CraftingContainer) event.getInventory();
        ItemStack result = event.getCrafting();

        if (result.getItem() instanceof BlockItem item && item.getBlock() instanceof FishSpecimen) {
            for (int i = 0; i < inv.getContainerSize(); i++) {
                ItemStack stack = inv.getItem(i);
                if(!stack.is(ItemTags.FISHES))
                    continue;
                if (stack.hasTag() && stack.getTag().contains("Scale")) {
                    result.getOrCreateTag().put("Scale", stack.getTag().get("Scale"));
                    break;
                }
            }
        }
    }
}
