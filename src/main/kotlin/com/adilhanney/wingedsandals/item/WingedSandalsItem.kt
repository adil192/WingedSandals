package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
//? if <1.21.5
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
//? if >=1.21.2
/*import net.minecraft.item.equipment.EquipmentType*/
import net.minecraft.screen.slot.Slot
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ClickType
import net.minecraft.world.World


//? if >=1.21.5 {
/*class WingedSandalsItem(settings: Settings): Item(settings.armor(
  ModArmorMaterials.wingedSandalsMaterial,
  EquipmentType.BOOTS,
)) {
*///?} else {
class WingedSandalsItem(settings: Settings) : ArmorItem (
  ModArmorMaterials.wingedSandalsMaterial,
  /*? if >=1.21.2 {*/ /*EquipmentType.BOOTS *//*?} else {*/ Type.BOOTS /*?}*/,
  settings,
) {
//?}

  //? if >=1.21.5 {
  /*override fun inventoryTick(stack: ItemStack?, world: ServerWorld?, entity: Entity?, slot: EquipmentSlot?) {
    if (entity is PlayerEntity) setAllowFlying(entity)
    super.inventoryTick(stack, world, entity, slot)
  }
  *///?} else {
  override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
    if (entity is PlayerEntity) setAllowFlying(entity)
    super.inventoryTick(stack, world, entity, slot, selected)
  }
  //?}

  companion object {
    /** @return Whether the player would be able to fly in vanilla minecraft */
    private fun canNormallyFly(player: PlayerEntity): Boolean {
      return player.isCreative || player.isSpectator
    }

    /** Sets the player's ability to fly based on whether they have the winged sandals equipped. */
    fun setAllowFlying(player: PlayerEntity) {
      val itemStack = player.getEquippedStack(EquipmentSlot.FEET)
      val isEquipped = itemStack.item is WingedSandalsItem
      setAllowFlying(player, isEquipped)
    }

    /** Sets the player's ability to fly based on whether they have the winged sandals equipped. */
    fun setAllowFlying(player: PlayerEntity, isEquipped: Boolean) {
      val allowFlying = isEquipped || canNormallyFly(player)
      if (player.abilities.allowFlying == allowFlying) return

      if (allowFlying) {
        WingedSandals.logger.info("${player.name.string} can now fly with their winged sandals!")
      } else {
        WingedSandals.logger.info("${player.name.string} can no longer fly!")
      }

      player.abilities.allowFlying = allowFlying
      if (!allowFlying) player.abilities.flying = false
      player.sendAbilitiesUpdate()
    }
  }
}
