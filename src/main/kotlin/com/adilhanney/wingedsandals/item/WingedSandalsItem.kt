package com.adilhanney.wingedsandals.item

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class WingedSandalsItem(settings: Settings) : ArmorItem(ModArmorMaterials.wingedSandalsMaterial, Type.BOOTS, settings) {

  @Override
  override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
    if (entity is PlayerEntity) {
      entity.abilities.allowFlying = true
    }
    super.inventoryTick(stack, world, entity, slot, selected)
  }
}
