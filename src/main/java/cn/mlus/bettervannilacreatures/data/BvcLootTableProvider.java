package cn.mlus.bettervannilacreatures.data;

import cn.mlus.bettervannilacreatures.data.loot.BvcEntityLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class BvcLootTableProvider extends LootTableProvider {
    public BvcLootTableProvider(PackOutput pOutput) {
        super(pOutput, Set.of(), List.of(
                new SubProviderEntry(BvcEntityLoot::new, LootContextParamSets.ENTITY)));
    }

}
