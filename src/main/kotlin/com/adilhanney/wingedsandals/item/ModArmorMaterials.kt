package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.Util
import java.util.*
import java.util.function.Consumer

object ModArmorMaterials {
  val wingedSandalsMaterial = registerWingedSandalsMaterial()

  private fun registerWingedSandalsMaterial() : RegistryEntry<ArmorMaterial> {
    val id = Identifier.of(WingedSandals.MOD_ID, "winged_sandals")

    val defense = Util.make(EnumMap<ArmorItem.Type, Int>(ArmorItem.Type::class.java), Consumer { map ->
      map.put(ArmorItem.Type.BOOTS, 1)
      map.put(ArmorItem.Type.LEGGINGS, 3)
      map.put(ArmorItem.Type.CHESTPLATE, 5)
      map.put(ArmorItem.Type.HELMET, 2)
      map.put(ArmorItem.Type.BODY, 7)
    })

    val enchantability = 25
    val toughness = 0.0F
    val knockbackResistance = 0.1F

    val soundEvent = SoundEvents.ITEM_ARMOR_EQUIP_GOLD

    val repairIngredient = { Ingredient.ofItems(Items.GOLD_INGOT, ModItems.wingedSandals) }

    val layers = listOf(ArmorMaterial.Layer(id))

    return Registry.registerReference(
      Registries.ARMOR_MATERIAL,
      id,
      ArmorMaterial(defense, enchantability, soundEvent, repairIngredient, layers, toughness, knockbackResistance)
    )
  }
}