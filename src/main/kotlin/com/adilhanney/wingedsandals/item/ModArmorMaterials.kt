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
    return Registry.registerReference(Registries.ARMOR_MATERIAL, WingedSandalsArmorMaterial.id, WingedSandalsArmorMaterial.instance)
  }
  *///?} else {
  private fun register(armorMaterial: ArmorMaterial) : ArmorMaterial = armorMaterial
  //?}
}

//? if >=1.21 {
/*class WingedSandalsArmorMaterial {
  companion object {
    private val gold = ArmorMaterials.GOLD.value()
    private val netherite = ArmorMaterials.NETHERITE.value()

    val id = Identifier.of(WingedSandals.MOD_ID, "winged_sandals")!!
    val instance = ArmorMaterial(
      EnumMap(ArmorItem.Type::class.java),
      gold.enchantability,
      gold.equipSound,
      { Ingredient.ofItems(Items.GOLD_INGOT, ModItems.wingedSandals) },
      listOf(ArmorMaterial.Layer(id)),
      gold.toughness,
      netherite.knockbackResistance,
    )
  }
}
*///?} else {
class WingedSandalsArmorMaterial : ArmorMaterial {
  companion object {
    val id = Identifier.of(WingedSandals.MOD_ID, "winged_sandals")!!
    val instance = WingedSandalsArmorMaterial()
  }

  override fun getDurability(type: ArmorItem.Type?): Int = ArmorMaterials.GOLD.getDurability(type)
  override fun getProtection(type: ArmorItem.Type?): Int = ArmorMaterials.GOLD.getProtection(type)
  override fun getEnchantability(): Int = ArmorMaterials.GOLD.enchantability
  override fun getEquipSound(): SoundEvent? = ArmorMaterials.GOLD.equipSound
  override fun getRepairIngredient(): Ingredient? = Ingredient.ofItems(Items.GOLD_INGOT, ModItems.wingedSandals)
  override fun getName(): String? = id.path
  override fun getToughness(): Float = ArmorMaterials.GOLD.toughness
  override fun getKnockbackResistance(): Float = ArmorMaterials.NETHERITE.knockbackResistance
}
//?}
