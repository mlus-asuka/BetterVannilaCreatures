package cn.mlus.bettervannilacreatures.init;


import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.data.BvcBiomeSpawnCostModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BvcBiomeModifierSerializers {
    private static final DeferredRegister<Codec<? extends BiomeModifier>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, BetterVannilaCreatures.MODID);

    private static boolean isInitialised = false;

    public static final RegistryObject<Codec<BvcBiomeSpawnCostModifier>> ADD_MOB_SPAWN_COST = SERIALIZERS.register(
            "add_mob_spawn_cost", BvcBiomeSpawnCostModifier::makeCodec
    );

    /**
     * Registers the {@link DeferredRegister} instance with the mod event bus.
     * <p>
     * This should be called during mod construction.
     *
     * @param modEventBus The mod event bus
     */
    public static void register(final IEventBus modEventBus) {
        if (isInitialised) {
            throw new IllegalStateException("Already initialised");
        }

        SERIALIZERS.register(modEventBus);

        isInitialised = true;
    }
}
