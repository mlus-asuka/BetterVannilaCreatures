package cn.mlus.bettervannilacreatures.data;

import cn.aurorian.ers.EcologicalReplenishmentStation;
import cn.aurorian.ers.init.ErsItems;
import cn.mlus.bettervannilacreatures.BetterVannilaCreatures;
import cn.mlus.bettervannilacreatures.init.BvcItems;
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
        buildErsRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ErsItems.FISH_FILLET.get(), 2)
                .requires(BvcItems.ATLANTIC_COD.get())
                .unlockedBy(getHasName(BvcItems.ATLANTIC_COD.get()), has(BvcItems.ATLANTIC_COD.get())),"fish_fillet_from_atlantic_cod")
                .build(pWriter,BetterVannilaCreatures.prefix("fish_fillet_from_atlantic_cod"));
        buildErsRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ErsItems.FISH_FILLET.get(), 2)
                .requires(BvcItems.HADDOCK_COD.get())
                .unlockedBy(getHasName(BvcItems.HADDOCK_COD.get()), has(BvcItems.HADDOCK_COD.get())),"fish_fillet_from_haddock_cod")
                .build(pWriter,BetterVannilaCreatures.prefix("fish_fillet_from_haddock_cod"));
        buildErsRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ErsItems.FISH_FILLET.get(), 2)
                .requires(BvcItems.PACIFIC_COD.get())
                .unlockedBy(getHasName(BvcItems.PACIFIC_COD.get()), has(BvcItems.PACIFIC_COD.get())),"fish_fillet_from_pacific_cod")
                .build(pWriter,BetterVannilaCreatures.prefix("fish_fillet_from_pacific_cod"));
        buildErsRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ErsItems.FISH_FILLET.get(), 3)
                .requires(BvcItems.PACIFIC_SALMON.get())
                .unlockedBy(getHasName(BvcItems.PACIFIC_SALMON.get()), has(BvcItems.PACIFIC_SALMON.get())),"fish_fillet_from_atlantic_salmon")
                .build(pWriter,BetterVannilaCreatures.prefix("fish_fillet_from_atlantic_salmon"));
        buildErsRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ErsItems.FISH_FILLET.get(), 3)
                .requires(BvcItems.MALE_SALMON.get())
                .unlockedBy(getHasName(BvcItems.MALE_SALMON.get()),has(BvcItems.MALE_SALMON.get())),"fish_fillet_from_male_salmon")
                .build(pWriter,BetterVannilaCreatures.prefix("fish_fillet_from_male_salmon"));
        buildErsRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ErsItems.FISH_FILLET.get(), 3)
                .requires(BvcItems.FEMALE_SALMON.get())
                .unlockedBy(getHasName(BvcItems.FEMALE_SALMON.get()),has(BvcItems.FEMALE_SALMON.get())),"fish_fillet_from_female_salmon")
                .build(pWriter,BetterVannilaCreatures.prefix("fish_fillet_from_female_salmon"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BvcItems.ATLANTIC_COD_SPECIMEN.get(),1)
                .requires(BvcItems.ATLANTIC_COD.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.ATLANTIC_COD.get()), has(BvcItems.ATLANTIC_COD.get()))
                .save(pWriter,BetterVannilaCreatures.prefix("atlantic_cod_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BvcItems.HADDOCK_COD_SPECIMEN.get(),1)
                .requires(BvcItems.HADDOCK_COD.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.HADDOCK_COD.get()), has(BvcItems.HADDOCK_COD.get()))
                .save(pWriter,BetterVannilaCreatures.prefix("haddock_cod_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BvcItems.PACIFIC_COD_SPECIMEN.get(),1)
                .requires(BvcItems.PACIFIC_COD.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.PACIFIC_COD.get()), has(BvcItems.PACIFIC_COD.get()))
                .save(pWriter,BetterVannilaCreatures.prefix("pacific_cod_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BvcItems.PACIFIC_SALMON_SPECIMEN.get(),1)
                .requires(BvcItems.PACIFIC_SALMON.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.PACIFIC_SALMON.get()), has(BvcItems.PACIFIC_SALMON.get()))
                .save(pWriter,BetterVannilaCreatures.prefix("pacific_salmon_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,BvcItems.MALE_SALMON_SPECIMEN.get(),1)
                .requires(BvcItems.MALE_SALMON.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.PACIFIC_SALMON.get()), has(BvcItems.MALE_SALMON.get()))
                .save(pWriter,BetterVannilaCreatures.prefix("male_salmon_specimen"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,BvcItems.FEMALE_SALMON_SPECIMEN.get(),1)
                .requires(BvcItems.FEMALE_SALMON.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(BvcItems.PACIFIC_SALMON.get()), has(BvcItems.MALE_SALMON.get()))
                .save(pWriter,BetterVannilaCreatures.prefix("female_salmon_specimen"));
    }

    public ConditionalRecipe.Builder buildErsRecipe(ShapelessRecipeBuilder recipe, String path) {
        return ConditionalRecipe.builder().addCondition(new ModLoadedCondition(EcologicalReplenishmentStation.MODID))
                .addRecipe(consumer -> recipe.save(consumer, BetterVannilaCreatures.prefix(path)));
    }

    private static void buildFoodProcessRecipes(Consumer<FinishedRecipe> recipeOutput, Item input, Item output, float xp) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, output, xp, 200)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput, BetterVannilaCreatures.prefix(getItemName(output) + "_smelting"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, xp, 100)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput, BetterVannilaCreatures.prefix(getItemName(output) + "_smoking"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, xp, 600)
                .unlockedBy(getHasName(input), has(input)).save(recipeOutput, BetterVannilaCreatures.prefix(getItemName(output) + "_campfire_cooking"));
    }
}
