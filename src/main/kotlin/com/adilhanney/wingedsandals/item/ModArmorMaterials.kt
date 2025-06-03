package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.IEventBus

//? if >=1.21.4 {
/*import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorMaterials
import net.minecraft.world.item.equipment.EquipmentAssets
*///?} else if >=1.21.2 {
/*import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorMaterials
*///?} else {
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ArmorMaterials
import net.neoforged.neoforge.registries.DeferredRegister
//?}

object ModArmorMaterials {
  //? if >=1.20.5 && <=1.21.1 {
  private val ARMOR_MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, WingedSandals.MODID)
  private fun registerMaterial(name: String, material: ArmorMaterial) = ARMOR_MATERIALS.register(name) { -> material }
  fun register(modEventBus: IEventBus) {
    ARMOR_MATERIALS.register(modEventBus)
  }
  //?} else {
  /*private fun registerMaterial(name: String, material: ArmorMaterial) = material
  fun register(modEventBus: IEventBus) {}
  *///?}

  val wingedSandalsMaterial = registerMaterial(WingedSandalsArmorMaterial.NAME, WingedSandalsArmorMaterial.instance)
}

//? if >=1.21.2 {
/*private class WingedSandalsArmorMaterial {
  companion object {
    private val gold = ArmorMaterials.GOLD
    private val netherite = ArmorMaterials.NETHERITE

    const val NAME = "winged_sandals"
    private val resourceLocation = ResourceLocation.fromNamespaceAndPath(WingedSandals.MODID, NAME)
    val instance = ArmorMaterial(
      gold.durability,
      gold.defense,
      gold.enchantmentValue,
      gold.equipSound,
      gold.toughness,
      netherite.knockbackResistance,
      gold.repairIngredient,
      //? if >=1.21.4 {
      /^ResourceKey.create(EquipmentAssets.ROOT_ID, resourceLocation),
      ^///?} else {
      resourceLocation,
      //?}
    )
  }
}
*///?} else {
private class WingedSandalsArmorMaterial {
  companion object {
    private val gold = ArmorMaterials.GOLD.value()
    private val netherite = ArmorMaterials.NETHERITE.value()

    const val NAME = "winged_sandals"
    val instance = ArmorMaterial(
      gold.defense(),
      gold.enchantmentValue,
      gold.equipSound,
      gold.repairIngredient,
      listOf(
        ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(WingedSandals.MODID, NAME))
      ),
      gold.toughness,
      netherite.knockbackResistance,
    )
  }
}
//?}