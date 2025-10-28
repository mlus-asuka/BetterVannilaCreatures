package cn.mlus.bettervannilacreatures.data;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.init.BvcEntities;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BvcBiomeSpawnCostModifier implements BiomeModifier {

    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(BetterVannilaCreatures.prefix("add_spawn_cost"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, BetterVannilaCreatures.MODID);
    @Override
    public void modify(final Holder<Biome> biome, final Phase phase, final ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD) {
            if(biome.is(BiomeTags.IS_OCEAN)){
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.BVC_HADDOCK_COD.get(),0.7,0.1);
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.BVC_PACIFIC_COD.get(),0.7,0.1);
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.BVC_ATLANTIC_COD.get(),0.7,0.1);
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.BVC_SALMON_PACIFIC.get(),0.8,0.1);
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.YELLOW_FIN_PUFFER.get(), 0.8,0.1);
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.OBSCURE_PUFFER.get(), 0.8,0.1);
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.NAUTILUS.get(),0.5,0.2);
            }
            if(biome.is(BiomeTags.IS_RIVER)){
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.BVC_SALMON_MALE.get(),0.7,0.1);
                builder.getMobSpawnSettings().addMobCharge(BvcEntities.BVC_SALMON_FEMALE.get(),0.7,0.1);
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<BvcBiomeSpawnCostModifier> makeCodec() {
        return Codec.unit(BvcBiomeSpawnCostModifier::new);
    }
}
