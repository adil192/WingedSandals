package com.adilhanney.wingedsandals.datagen

import com.adilhanney.wingedsandals.WingedSandals
import com.adilhanney.wingedsandals.item.ModItems
import net.minecraft.data.PackOutput

//? if >=1.21.4 {
/*import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.ModelProvider
import net.minecraft.client.data.models.model.ModelTemplates

class ModModelProvider(output: PackOutput) : ModelProvider(output, WingedSandals.MODID) {
  override fun registerModels(blockModels: BlockModelGenerators, itemModels: ItemModelGenerators) {
    itemModels.generateFlatItem(ModItems.wingedSandals.get(), ModelTemplates.FLAT_ITEM)
  }
}
*///?} else {
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper

class ModModelProvider(output: PackOutput, existingFileHelper: ExistingFileHelper)
  : ItemModelProvider(output, WingedSandals.MODID, existingFileHelper) {
  override fun registerModels() {
    basicItem(ModItems.wingedSandals.get())
  }
}
//?}

