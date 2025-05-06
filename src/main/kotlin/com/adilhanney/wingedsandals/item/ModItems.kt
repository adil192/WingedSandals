package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.Items
//? if >=1.21.4
/*import net.minecraft.item.equipment.EquipmentType*/
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object ModItems {
  val wingedSandals = register("winged_sandals", ::WingedSandalsItem,
    Item.Settings()
      //? if >=1.21.4 {
      /*.maxDamage(EquipmentType.BOOTS.getMaxDamage(7))
      *///?} else if >=1.21 {
      /*.maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(7))
      *///?}
  )

  private fun register(name: String?, itemFactory: (Item.Settings) -> Item, settings: Item.Settings): Item {
    val registryKey =
      RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WingedSandals.MOD_ID, name))

    //? if >=1.21.4 {
    /*settings.registryKey(registryKey)
    return Items.register(registryKey, itemFactory, settings)
    *///?} else {
    return Items.register(registryKey, itemFactory(settings))
    //?}
  }

  fun registerItems() {
    WingedSandals.logger.info("Registering Items for " + WingedSandals.MOD_ID)

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModifyEntries { entries ->
      entries.add(wingedSandals)
    })

  }
}
