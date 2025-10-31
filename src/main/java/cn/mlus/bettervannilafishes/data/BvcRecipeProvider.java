package cn.mlus.bettervannilafishes.data;

import cn.aurorian.ers.EcologicalReplenishmentStation;
import cn.mlus.bettervannilafishes.BetterVannilaFishes;
import cn.mlus.bettervannilafishes.init.BvcItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BvcRecipeProvider extends RecipeProvider {

    public BvcRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COD,1)
                .requires(BvcItems.ATLANTIC_COD.get())
                .unlockedBy(getHasName(BvcItems.ATLANTIC_COD.get()), has(BvcItems.ATLANTIC_COD.get()))
                .save(pWriter, BetterVannilaFishes.prefix("cod_from_atlantic_cod"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COD,1)
                .requires(BvcItems.HADDOCK_COD.get())
                .unlockedBy(getHasName(BvcItems.HADDOCK_COD.get()), has(BvcItems.HADDOCK_COD.get()))
                .save(pWriter, BetterVannilaFishes.prefix("cod_from_haddock_cod"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COD,1)
                .requires(BvcItems.PACIFIC_COD.get())
                .unlockedBy(getHasName(BvcItems.PACIFIC_COD.get()), has(BvcItems.PACIFIC_COD.get()))
                .save(pWriter, BetterVannilaFishes.prefix("cod_from_pacific_cod"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.SALMON,1)
                .requires(BvcItems.PACIFIC_SALMON.get())
                .unlockedBy(getHasName(BvcItems.PACIFIC_SALMON.get()), has(BvcItems.PACIFIC_SALMON.get()))
                .save(pWriter, BetterVannilaFishes.prefix("salmon_from_pacific_salmon"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.SALMON,1)
                .requires(BvcItems.MALE_SALMON.get())
                .unlockedBy(getHasName(BvcItems.MALE_SALMON.get()), has(BvcItems.MALE_SALMON.get()))
                .save(pWriter, BetterVannilaFishes.prefix("salmon_from_male_salmon"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.SALMON,1)
                .requires(BvcItems.FEMALE_SALMON.get())
                .unlockedBy(getHasName(BvcItems.FEMALE_SALMON.get()), has(BvcItems.FEMALE_SALMON.get()))
                .save(pWriter, BetterVannilaFishes.prefix("salmon_from_female_salmon"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUFFERFISH,1)
                .requires(BvcItems.YELLOW_FIN_PUFFER.get())
                .unlockedBy(getHasName(BvcItems.YELLOW_FIN_PUFFER.get()), has(BvcItems.YELLOW_FIN_PUFFER.get()))
                .save(pWriter, BetterVannilaFishes.prefix("pufferfish_from_yellow_fin_puffer"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUFFERFISH,1)
                .requires(BvcItems.OBSCURE_PUFFER.get())
                .unlockedBy(getHasName(BvcItems.OBSCURE_PUFFER.get()), has(BvcItems.OBSCURE_PUFFER.get()))
                .save(pWriter, BetterVannilaFishes.prefix("pufferfish_from_obscure_puffer"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BvcItems.ATLANTIC_COD_SPECIMEN.get(),1)
                .requires(BvcItems.ATLANTIC_COD.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.ATLANTIC_COD.get()), has(BvcItems.ATLANTIC_COD.get()))
                .save(pWriter, BetterVannilaFishes.prefix("atlantic_cod_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BvcItems.HADDOCK_COD_SPECIMEN.get(),1)
                .requires(BvcItems.HADDOCK_COD.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.HADDOCK_COD.get()), has(BvcItems.HADDOCK_COD.get()))
                .save(pWriter, BetterVannilaFishes.prefix("haddock_cod_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BvcItems.PACIFIC_COD_SPECIMEN.get(),1)
                .requires(BvcItems.PACIFIC_COD.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.PACIFIC_COD.get()), has(BvcItems.PACIFIC_COD.get()))
                .save(pWriter, BetterVannilaFishes.prefix("pacific_cod_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BvcItems.PACIFIC_SALMON_SPECIMEN.get(),1)
                .requires(BvcItems.PACIFIC_SALMON.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.PACIFIC_SALMON.get()), has(BvcItems.PACIFIC_SALMON.get()))
                .save(pWriter, BetterVannilaFishes.prefix("pacific_salmon_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,BvcItems.MALE_SALMON_SPECIMEN.get(),1)
                .requires(BvcItems.MALE_SALMON.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.PACIFIC_SALMON.get()), has(BvcItems.MALE_SALMON.get()))
                .save(pWriter, BetterVannilaFishes.prefix("male_salmon_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,BvcItems.FEMALE_SALMON_SPECIMEN.get(),1)
                .requires(BvcItems.FEMALE_SALMON.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.PACIFIC_SALMON.get()), has(BvcItems.MALE_SALMON.get()))
                .save(pWriter, BetterVannilaFishes.prefix("female_salmon_specimen"));
    }

    public ConditionalRecipe.Builder buildErsRecipe(ShapelessRecipeBuilder recipe, String path) {
        return ConditionalRecipe.builder().addCondition(new ModLoadedCondition(EcologicalReplenishmentStation.MODID))
                .addRecipe(consumer -> recipe.save(consumer, BetterVannilaFishes.prefix(path)));
    }

    private static void buildFoodProcessRecipes(Consumer<FinishedRecipe> recipeOutput, Item input, Item output, float xp) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, output, xp, 200)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput, BetterVannilaFishes.prefix(getItemName(output) + "_smelting"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, xp, 100)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput, BetterVannilaFishes.prefix(getItemName(output) + "_smoking"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, xp, 600)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput, BetterVannilaFishes.prefix(getItemName(output) + "_campfire_cooking"));
    }
}
