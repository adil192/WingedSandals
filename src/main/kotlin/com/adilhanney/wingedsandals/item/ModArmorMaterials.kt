package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.minecraft.item.ArmorItem
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import java.util.*

//? if >=1.21.2 {
/*import net.minecraft.item.equipment.ArmorMaterial
import net.minecraft.item.equipment.ArmorMaterials
//? if >=1.21.4
/^import net.minecraft.item.equipment.EquipmentAssetKeys.REGISTRY_KEY^/
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.ItemTags
*///?} else {
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials
//?}

object ModArmorMaterials {
  val wingedSandalsMaterial = register(WingedSandalsArmorMaterial.instance)

  //? if =1.21.1 {
  /*private fun register(armorMaterial: ArmorMaterial) : RegistryEntry<ArmorMaterial> {
    return Registry.registerReference(Registries.ARMOR_MATERIAL, WingedSandalsArmorMaterial.id, WingedSandalsArmorMaterial.instance)
  }
  *///?} else {
  private fun register(armorMaterial: ArmorMaterial) : ArmorMaterial = armorMaterial
  //?}
}

//? if >=1.21.2 {
/*class WingedSandalsArmorMaterial {
  companion object {
    private val gold = ArmorMaterials.GOLD
    private val netherite = ArmorMaterials.NETHERITE

    val id = Identifier.of(WingedSandals.MOD_ID, "winged_sandals")!!
    val instance = ArmorMaterial(
      7,
      gold.defense,
      gold.enchantmentValue,
      gold.equipSound,
      gold.toughness,
      netherite.knockbackResistance,
      ItemTags.REPAIRS_GOLD_ARMOR,
      //? if >=1.21.4 {
      /^RegistryKey.of(REGISTRY_KEY, id),
      ^///?} else {
      id,
      //?}
    )
  }
}
*///?} else if >=1.21 {
/*class WingedSandalsArmorMaterial {
  companion object {
    private val gold = ArmorMaterials.GOLD.value()
    private val netherite = ArmorMaterials.NETHERITE.value()

    val id = Identifier.of(WingedSandals.MOD_ID, "winged_sandals")!!
    val instance = ArmorMaterial(
      gold.defense,
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
