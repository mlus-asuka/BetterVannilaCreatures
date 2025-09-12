package cn.mlus.bettervannilacreatures.event;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.BvcAbstractFish;
import cn.mlus.bettervannilacreatures.init.BvcEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterVannilaCreatures.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeHandler {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(BvcEntities.BVC_COD.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.BVC_SALMON_MALE.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.BVC_SALMON_FEMALE.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.BVC_SALMON_ATLANTIC.get(), BvcAbstractFish.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(BvcEntities.BVC_COD.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCodSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.BVC_SALMON_MALE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCustomWaterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.BVC_SALMON_FEMALE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCustomWaterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.BVC_SALMON_ATLANTIC.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCustomWaterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

    public static boolean checkCodSpawnRules(
            EntityType<? extends WaterAnimal> type,
            LevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if(level.getBiome(pos).is(Biomes.WARM_OCEAN) || level.getBiome(pos).is(Biomes.LUKEWARM_OCEAN) || level.getBiome(pos).is(Biomes.DEEP_LUKEWARM_OCEAN)){
            return false;
        }
            return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(type, level, spawnType, pos, random);
    }

    public static boolean checkCustomWaterSpawnRules(
            EntityType<? extends WaterAnimal> type,
            LevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if(rollSpawn(3, random, spawnType)) {
            return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(type, level, spawnType, pos, random);
        }else {
            return false;
        }
    }

    public static boolean rollSpawn(int rolls, RandomSource random, MobSpawnType reason) {
        if (reason == MobSpawnType.SPAWNER) {
            return true;
        } else {
            return rolls <= 0 || random.nextInt(rolls) == 0;
        }
    }
}
