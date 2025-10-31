package cn.mlus.bettervannilafishes.init;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class BvcCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BetterVannilaFishes.MODID);
//    public static RegistryObject<CreativeModeTab> ERS_TAB = TABS.register("main", () -> CreativeModeTab.builder()
//            .title(Component.translatable("itemGroup.bvc_group"))
//            .icon(() -> BvcItems.SWAMP_DRAGON_SADDLE.get().getDefaultInstance())
//            .displayItems((par, output) -> {
//
//            }).build());
    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
