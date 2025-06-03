package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
//? if <1.21.2
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
//? if >=1.21.2
/*import net.minecraft.world.item.equipment.ArmorType*/
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {
  private val ITEMS = DeferredRegister.createItems(WingedSandals.MODID)

  val wingedSandals: DeferredItem<WingedSandalsItem> = ITEMS.registerItem("winged_sandals", ::WingedSandalsItem,
    Item.Properties()
      .rarity(Rarity.COMMON)
      //? if >=1.21.2 {
      /*.durability(ArmorType.BOOTS.getDurability(7))
      *///?} else if >=1.21 {
      .durability(ArmorItem.Type.BOOTS.getDurability(7))
      //?}
  )

  fun register(modEventBus: IEventBus) {
    ITEMS.register(modEventBus)

    modEventBus.addListener<BuildCreativeModeTabContentsEvent> { event -> this.addCreative(event) }

    NeoForge.EVENT_BUS.addListener(WingedSandalsItem::onBootsChanged)
  }

  private fun addCreative(event: BuildCreativeModeTabContentsEvent) {
    if (event.tabKey == CreativeModeTabs.COMBAT)
        event.accept(wingedSandals)
  }
}
