package cn.mlus.bettervannilacreatures.data;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.init.BvcEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BvcBiomeModifier
{
    public static void register(BootstapContext<net.minecraftforge.common.world.BiomeModifier> context) {
        removeSpawn(context, "cod_ocean", BiomeTags.IS_OCEAN, EntityType.COD);
        removeSpawn(context,"salmon_ocean", BiomeTags.IS_OCEAN, EntityType.SALMON);
        removeSpawn(context,"pufferfish_ocean", BiomeTags.IS_OCEAN, EntityType.PUFFERFISH);
        removeSpawn(context,"tropical_fish_ocean", BiomeTags.IS_OCEAN, EntityType.TROPICAL_FISH);
        removeSpawn(context,"cod_river", BiomeTags.IS_RIVER, EntityType.COD);
        removeSpawn(context,"salmon_river", BiomeTags.IS_RIVER, EntityType.SALMON);
        removeSpawn(context,"pufferfish_river", BiomeTags.IS_RIVER, EntityType.PUFFERFISH);

        addSpawn(context,"cod_ocean", BiomeTags.IS_OCEAN, new MobSpawnSettings.SpawnerData(BvcEntities.BVC_COD.get(),12,3,9));
        addSpawn(context, "salmon_male", BiomeTags.IS_RIVER, new MobSpawnSettings.SpawnerData(BvcEntities.BVC_SALMON_MALE.get(), 12, 2, 6));
        addSpawn(context, "salmon_female", BiomeTags.IS_RIVER, new MobSpawnSettings.SpawnerData(BvcEntities.BVC_SALMON_FEMALE.get(), 12, 2, 6));
        addSpawn(context, "salmon_atlantic", BiomeTags.IS_OCEAN, new MobSpawnSettings.SpawnerData(BvcEntities.BVC_SALMON_ATLANTIC.get(), 12, 3, 9));
    }

    private static void addSpawn(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, MobSpawnSettings.SpawnerData... spawns) {
        register(context, "add_spawn/" + name, () -> new ForgeBiomeModifiers.AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), List.of(spawns)));
    }

    private static void addSpawn(BootstapContext<BiomeModifier> context, String name, ResourceKey<Biome> biome, MobSpawnSettings.SpawnerData... spawns){
        register(context, "add_spawn/" + name, () -> new ForgeBiomeModifiers.AddSpawnsBiomeModifier(HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(biome)), List.of(spawns)));
    }

    private static void removeSpawn(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, EntityType<?>... types) {
        register(context, "remove_spawn/" + name, () -> new ForgeBiomeModifiers.RemoveSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), HolderSet.direct(Stream.of(types).map(type -> ForgeRegistries.ENTITY_TYPES.getHolder(type).get()).collect(Collectors.toList()))));
    }

    private static void register(BootstapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, name)), modifier.get());
    }

    @SafeVarargs
    private static void addFeature(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "add_feature/" + name, () -> new ForgeBiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
    }

    @SafeVarargs
    private static HolderSet<PlacedFeature> featureSet(BootstapContext<?> context, ResourceKey<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
    }
}
