package cn.mlus.bettervannilacreatures.init;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.cod.AtlanticCod;
import cn.mlus.bettervannilacreatures.entity.cod.HaddockCod;
import cn.mlus.bettervannilacreatures.entity.cod.PacificCod;
import cn.mlus.bettervannilacreatures.entity.salmon.AtlanticSalmon;
import cn.mlus.bettervannilacreatures.entity.salmon.FemaleSalmon;
import cn.mlus.bettervannilacreatures.entity.salmon.MaleSalmon;
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

    public static final RegistryObject<EntityType<HaddockCod>> BVC_HADDOCK_COD = ENTITIES.register("haddock_cod",
            () -> EntityType.Builder.of(HaddockCod::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "haddock_cod").toString()));
    public static final RegistryObject<EntityType<AtlanticCod>> BVC_ATLANTIC_COD = ENTITIES.register("atlantic_cod",
            () -> EntityType.Builder.of(AtlanticCod::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "atlantic_cod").toString()));
    public static final RegistryObject<EntityType<PacificCod>> BVC_PACIFIC_COD = ENTITIES.register("pacific_cod",
            () -> EntityType.Builder.of(PacificCod::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "pacific_cod").toString()));

    public static final RegistryObject<EntityType<MaleSalmon>> BVC_SALMON_MALE = ENTITIES.register("male_salmon",
            () -> EntityType.Builder.of(MaleSalmon::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "male_salmon").toString()));
    public static final RegistryObject<EntityType<FemaleSalmon>> BVC_SALMON_FEMALE = ENTITIES.register("female_salmon",
            () -> EntityType.Builder.of(FemaleSalmon::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "female_salmon").toString()));

    public static final RegistryObject<EntityType<AtlanticSalmon>> BVC_SALMON_ATLANTIC = ENTITIES.register("atlantic_salmon",
            () -> EntityType.Builder.of(AtlanticSalmon::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(ResourceLocation.fromNamespaceAndPath(BetterVannilaCreatures.MODID, "atlantic_salmon").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
