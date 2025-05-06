package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import java.util.*

object ModArmorMaterials {
  val wingedSandalsMaterial = register(WingedSandalsArmorMaterial.instance)

  //? if >=1.21 {
  /*private fun register(armorMaterial: ArmorMaterial) : RegistryEntry<ArmorMaterial> {
    val id = Identifier.of(WingedSandals.MOD_ID, armorMaterial.name)
    return Registry.registerReference(Registries.ARMOR_MATERIAL, id, WingedSandalsArmorMaterial.instance)
  }
  *///? } else {
  private fun register(armorMaterial: ArmorMaterial) : ArmorMaterial = armorMaterial
  //? }
}

class WingedSandalsArmorMaterial : ArmorMaterial {
  companion object {
    val instance = WingedSandalsArmorMaterial()
  }

  override fun getDurability(type: ArmorItem.Type?): Int = ArmorMaterials.GOLD.getDurability(type)
  override fun getProtection(type: ArmorItem.Type?): Int = ArmorMaterials.GOLD.getProtection(type)
  override fun getEnchantability(): Int = ArmorMaterials.GOLD.enchantability
  override fun getEquipSound(): SoundEvent? = ArmorMaterials.GOLD.equipSound
  override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(Items.GOLD_INGOT, ModItems.wingedSandals)
  override fun getName(): String? = "winged_sandals"
  override fun getToughness(): Float = ArmorMaterials.GOLD.toughness
  override fun getKnockbackResistance(): Float = ArmorMaterials.NETHERITE.knockbackResistance
}
