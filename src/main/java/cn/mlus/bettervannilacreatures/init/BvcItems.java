package cn.mlus.bettervannilacreatures.init;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BvcItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            BetterVannilaCreatures.MODID);

    public static final RegistryObject<Item> MALE_SALMON_BUCKET = ITEMS.register("male_salmon_bucket",
            () -> new MobBucketItem(
                    BvcEntities.BVC_SALMON_MALE,
                    () -> Fluids.WATER,
                    () -> SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1)
            ));
    public static final RegistryObject<Item> FEMALE_SALMON_BUCKET = ITEMS.register("female_salmon_bucket",
            () -> new MobBucketItem(
                    BvcEntities.BVC_SALMON_FEMALE,
                    () -> Fluids.WATER,
                    () -> SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1)
            ));
    public static final RegistryObject<Item> ATLANTIC_SALMON_BUCKET = ITEMS.register("atlantic_salmon_bucket",
            () -> new MobBucketItem(
                    BvcEntities.BVC_SALMON_ATLANTIC,
                    () -> Fluids.WATER,
                    () -> SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1)
            ));
    //Spawn Egg
    public static final RegistryObject<Item> MALE_SALMON_SPAWN_EGG = ITEMS.register("male_salmon_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    BvcEntities.BVC_SALMON_MALE,
                    0xFFFFFF,
                    0x000000,
                    new Item.Properties()
            ));
    public static final RegistryObject<Item> FEMALE_SALMON_SPAWN_EGG = ITEMS.register("female_salmon_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    BvcEntities.BVC_SALMON_FEMALE,
                    0xFFFFFF,
                    0x000000,
                    new Item.Properties()
            ));
    public static final RegistryObject<Item> ATLANTIC_SALMON_SPAWN_EGG = ITEMS.register("atlantic_salmon_spawn_egg",
            () -> new ForgeSpawnEggItem(
                    BvcEntities.BVC_SALMON_ATLANTIC,
                    0xFFFFFF,
                    0x000000,
                    new Item.Properties()
            ));
    //Food
    public static final RegistryObject<Item> MALE_SALMON = ITEMS.register("male_salmon",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).build())));
    public static final RegistryObject<Item> FEMALE_SALMON = ITEMS.register("female_salmon",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).build())));
    public static final RegistryObject<Item> ATLANTIC_SALMON = ITEMS.register("atlantic_salmon",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).build())));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
