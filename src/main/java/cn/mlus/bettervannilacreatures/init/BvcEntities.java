package cn.mlus.bettervannilacreatures.init;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.BvcCodEntity;
import cn.mlus.bettervannilacreatures.entity.BvcSalmonEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BvcEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            BetterVannilaCreatures.MODID);

    public static final RegistryObject<EntityType<BvcCodEntity>> BVC_COD = ENTITIES.register("cod",
            () -> EntityType.Builder.of(BvcCodEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "cod").toString()));

    public static final RegistryObject<EntityType<BvcSalmonEntity>> BVC_SALMON_MALE = ENTITIES.register("male_salmon",
            () -> EntityType.Builder.of(BvcSalmonEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "male_salmon").toString()));
    public static final RegistryObject<EntityType<BvcSalmonEntity>> BVC_SALMON_FEMALE = ENTITIES.register("female_salmon",
            () -> EntityType.Builder.of(BvcSalmonEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "female_salmon").toString()));

    public static final RegistryObject<EntityType<BvcSalmonEntity>> BVC_SALMON_ATLANTIC = ENTITIES.register("atlantic_salmon",
            () -> EntityType.Builder.of(BvcSalmonEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "atlantic_salmon").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
