package com.adilhanney.wingedsandals.datagen

import com.adilhanney.wingedsandals.item.ModItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.DataOutput
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ModRecipeProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
  FabricRecipeProvider(output, registriesFuture) {
  override fun generate(exporter: RecipeExporter?) {
    ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.wingedSandals)
      .input(Items.GOLDEN_BOOTS)
      .input(Items.ELYTRA)
      .criterion(hasItem(Items.ELYTRA), conditionsFromItem(Items.ELYTRA))
      .offerTo(exporter)
  }
}
