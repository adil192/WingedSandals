package com.adilhanney.wingedsandals.datagen

import com.adilhanney.wingedsandals.item.ModItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory

//? if >=1.21.4 {
/*import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture
import net.minecraft.data.recipe.RecipeExporter
import net.minecraft.data.recipe.RecipeGenerator
*///?} else if >=1.21.2 {
/*import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeGenerator
*///?} else if >=1.20.5 {
/*import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture
*///?} else {
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import java.util.function.Consumer
//?}

//? if >=1.20.5 {
/*class ModRecipeProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) :
  FabricRecipeProvider(output, registriesFuture) {
*///?} else {
class ModRecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
//?}

  //? if >=1.21.2 {
  /*override fun getRecipeGenerator(registries: RegistryWrapper.WrapperLookup, exporter: RecipeExporter): RecipeGenerator {
    return object : RecipeGenerator(registries, exporter) {
      override fun generate() {
        createShapeless(RecipeCategory.COMBAT, ModItems.wingedSandals)
          .input(Items.GOLDEN_BOOTS)
          .input(Items.ELYTRA)
          .criterion(hasItem(Items.ELYTRA), conditionsFromItem(Items.ELYTRA))
          .offerTo(exporter)
      }
    }
  }
  *///?} else {
  override fun generate(
    exporter: /*? if >=1.20.5 {*/ /*RecipeExporter *//*?} else {*/ Consumer<RecipeJsonProvider> /*?}*/
  ) {
    ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.wingedSandals)
      .input(Items.GOLDEN_BOOTS)
      .input(Items.ELYTRA)
      .criterion(hasItem(Items.ELYTRA), conditionsFromItem(Items.ELYTRA))
      .offerTo(exporter)
  }
  //?}

  //? if >=1.21.2
  /*override fun getName(): String = "WingedSandalsRecipeProvider"*/
}
