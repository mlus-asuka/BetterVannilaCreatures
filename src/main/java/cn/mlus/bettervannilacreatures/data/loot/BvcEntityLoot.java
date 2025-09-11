package cn.mlus.bettervannilacreatures.data.loot;

import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import net.minecraft.data.loot.packs.VanillaEntityLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Stream;

public class BvcEntityLoot extends VanillaEntityLoot {
    @Override
    public void generate() {

    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return ForgeRegistries.ENTITY_TYPES.getValues().stream().filter(entities -> Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entities)).getNamespace().equals(BetterVannilaCreatures.MODID));
    }
}
