package cn.mlus.bettervannilacreatures.init;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.entity.BvcAbstractFish;
import cn.mlus.bettervannilacreatures.entity.BvcCodEntity;
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

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
