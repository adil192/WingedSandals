package com.adilhanney.wingedsandals.datagen

import com.adilhanney.wingedsandals.item.ModItems
//? if >=1.21.4 {
/*import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.minecraft.client.data.BlockStateModelGenerator
import net.minecraft.client.data.ItemModelGenerator
*///?} else {
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
//?}
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
  override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
  }

  override fun generateItemModels(generator: ItemModelGenerator) {
    //? if >=1.21.4 {
    /*generator.register(ModItems.wingedSandals)
    *///?} else {
    generator.register(ModItems.wingedSandals, Models.GENERATED)
    //?}
  }
}
