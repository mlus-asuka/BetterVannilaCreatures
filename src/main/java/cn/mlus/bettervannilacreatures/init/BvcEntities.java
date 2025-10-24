package cn.mlus.bettervannilacreatures.init;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.cod.AtlanticCod;
import cn.mlus.bettervannilacreatures.entity.cod.HaddockCod;
import cn.mlus.bettervannilacreatures.entity.cod.PacificCod;
import cn.mlus.bettervannilacreatures.entity.pufferfish.ObscurePuffer;
import cn.mlus.bettervannilacreatures.entity.pufferfish.YellowFinPuffer;
import cn.mlus.bettervannilacreatures.entity.salmon.FemaleSalmon;
import cn.mlus.bettervannilacreatures.entity.salmon.MaleSalmon;
import cn.mlus.bettervannilacreatures.entity.salmon.PacificSalmon;
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
                    .build(BetterVannilaCreatures.prefix("haddock_cod").toString()));
    public static final RegistryObject<EntityType<AtlanticCod>> BVC_ATLANTIC_COD = ENTITIES.register("atlantic_cod",
            () -> EntityType.Builder.of(AtlanticCod::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(BetterVannilaCreatures.prefix( "atlantic_cod").toString()));
    public static final RegistryObject<EntityType<PacificCod>> BVC_PACIFIC_COD = ENTITIES.register("pacific_cod",
            () -> EntityType.Builder.of(PacificCod::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(BetterVannilaCreatures.prefix("pacific_cod").toString()));

    public static final RegistryObject<EntityType<MaleSalmon>> BVC_SALMON_MALE = ENTITIES.register("male_salmon",
            () -> EntityType.Builder.of(MaleSalmon::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(BetterVannilaCreatures.prefix("male_salmon").toString()));
    public static final RegistryObject<EntityType<FemaleSalmon>> BVC_SALMON_FEMALE = ENTITIES.register("female_salmon",
            () -> EntityType.Builder.of(FemaleSalmon::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(BetterVannilaCreatures.prefix( "female_salmon").toString()));

    public static final RegistryObject<EntityType<PacificSalmon>> BVC_SALMON_PACIFIC = ENTITIES.register("pacific_salmon",
            () -> EntityType.Builder.of(PacificSalmon::new, MobCategory.WATER_AMBIENT)
                    .sized(1F, 1F)
                    .build(BetterVannilaCreatures.prefix( "pacific_salmon").toString()));

    public static final RegistryObject<EntityType<YellowFinPuffer>> YELLOW_FIN_PUFFER = ENTITIES.register("yellow_fin_puffer",
            () -> EntityType.Builder.of(YellowFinPuffer::new, MobCategory.WATER_AMBIENT)
                    .sized(1F,1F)
                    .build(BetterVannilaCreatures.prefix("yellow_fin_puffer").toString()));
    public static final RegistryObject<EntityType<ObscurePuffer>> OBSCURE_PUFFER = ENTITIES.register("obscure_fin_puffer",
            () -> EntityType.Builder.of(ObscurePuffer::new, MobCategory.WATER_AMBIENT)
                    .sized(1F,1F)
                    .build(BetterVannilaCreatures.prefix("obscure_puffer").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
