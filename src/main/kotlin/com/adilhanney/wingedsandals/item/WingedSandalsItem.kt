package com.adilhanney.wingedsandals.item

import com.adilhanney.wingedsandals.WingedSandals
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.StackReference
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

  override fun onClicked(stack: ItemStack?, otherStack: ItemStack?, slot: Slot, clickType: ClickType?, player: PlayerEntity, cursorStackReference: StackReference?): Boolean {
    if (slot.id == 8) {
      // The player picked up the item from their boots slot
      setAllowFlying(player, false)
    }
    return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference)
  }


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
    WingedSandals.logger.info("Setting allowFlying to $allowFlying")
    player.abilities.allowFlying = allowFlying
    if (!allowFlying) player.abilities.flying = false
    player.sendAbilitiesUpdate()
  }

  /**
   * @return Whether the 10 blocks below the player are all air
   */
  private fun isHighUp(player: PlayerEntity): Boolean {
    val world = player.world
    if (world == null) return false

    val playerPos = player.blockPos
    for (i in 0..10) {
      val blockPos = playerPos.down(i)
      val blockState = world.getBlockState(blockPos)
      if (!blockState.isAir) return false
    }
    return true
  }
}
