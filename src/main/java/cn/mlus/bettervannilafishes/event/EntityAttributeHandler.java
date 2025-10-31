package cn.mlus.bettervannilafishes.event;

import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.entity.BvcAbstractFish;
import cn.mlus.bettervannilafishes.entity.nautilus.NautilusEntity;
import cn.mlus.bettervannilafishes.entity.pufferfish.BvcPufferfishEntity;
import cn.mlus.bettervannilafishes.init.BvcEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterVannilaFishes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeHandler {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(BvcEntities.BVC_HADDOCK_COD.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.BVC_ATLANTIC_COD.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.BVC_PACIFIC_COD.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.BVC_SALMON_MALE.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.BVC_SALMON_FEMALE.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.BVC_SALMON_PACIFIC.get(), BvcAbstractFish.createAttributes().build());
        event.put(BvcEntities.YELLOW_FIN_PUFFER.get(), BvcPufferfishEntity.createAttributes().build());
        event.put(BvcEntities.OBSCURE_PUFFER.get(), BvcPufferfishEntity.createAttributes().build());
        event.put(BvcEntities.NAUTILUS.get(), NautilusEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(BvcEntities.BVC_HADDOCK_COD.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCodSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.BVC_ATLANTIC_COD.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCodSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.BVC_PACIFIC_COD.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCodSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.BVC_SALMON_MALE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCustomWaterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.BVC_SALMON_FEMALE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCustomWaterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.BVC_SALMON_PACIFIC.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkCustomWaterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.YELLOW_FIN_PUFFER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkPufferSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.OBSCURE_PUFFER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkPufferSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BvcEntities.NAUTILUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityAttributeHandler::checkPufferSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
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

    public static boolean checkPufferSpawnRules(
            EntityType<? extends Mob> type,
            LevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if(!level.getBiome(pos).is(Biomes.WARM_OCEAN) && !level.getBiome(pos).is(Biomes.LUKEWARM_OCEAN) && !level.getBiome(pos).is(Biomes.DEEP_LUKEWARM_OCEAN) && !level.getBiome(pos).is(Biomes.RIVER)){
            return false;
        }
        return checkSurfaceWaterAnimalSpawnRules(level,pos);
    }

    public static boolean checkCustomWaterSpawnRules(
            EntityType<? extends WaterAnimal> type,
            LevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if(rollSpawn(3, random, spawnType)) {
            return checkSurfaceWaterAnimalSpawnRules(level,pos);
        }else {
            return false;
        }
    }

    public static boolean checkSurfaceWaterAnimalSpawnRules(LevelAccessor pLevel, BlockPos pPos) {
        int $$5 = pLevel.getSeaLevel();
        int $$6 = $$5 - 13;
        return pPos.getY() >= $$6 && pPos.getY() <= $$5 && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER);
    }

    public static boolean rollSpawn(int rolls, RandomSource random, MobSpawnType reason) {
        if (reason == MobSpawnType.SPAWNER) {
            return true;
        } else {
            return rolls <= 0 || random.nextInt(rolls) == 0;
        }
    }
}
