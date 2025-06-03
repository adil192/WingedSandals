package com.adilhanney.wingedsandals.datagen

import com.adilhanney.wingedsandals.item.ModItems
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.world.item.Items
import java.util.concurrent.CompletableFuture

//? if >=1.21.2 {
/*class ModRecipeProvider(registries: HolderLookup.Provider, output: RecipeOutput) :
  RecipeProvider(registries, output) {
  override fun buildRecipes() {
    shapeless(RecipeCategory.COMBAT, ModItems.wingedSandals.get())
      .requires(Items.GOLDEN_BOOTS)
      .requires(Items.ELYTRA)
      .unlockedBy("has_elytra", has(Items.ELYTRA))
      .save(output)
  }

  class Runner(packOutput: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
    RecipeProvider.Runner(packOutput, registries) {
    override fun createRecipeProvider(registries: HolderLookup.Provider, output: RecipeOutput) =
      ModRecipeProvider(registries, output)

    override fun getName() = "WingedSandalsModRecipeProvider"
  }
}
*///?} else {
class ModRecipeProvider(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) :
  RecipeProvider(output, registries) {
  override fun buildRecipes(recipeOutput: RecipeOutput) {
    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.wingedSandals.get())
      .requires(Items.GOLDEN_BOOTS)
      .requires(Items.ELYTRA)
      .unlockedBy("has_elytra", has(Items.ELYTRA))
      .save(recipeOutput)
  }
}
//?}
