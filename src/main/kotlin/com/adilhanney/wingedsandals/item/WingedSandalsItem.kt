package com.adilhanney.wingedsandals.item

import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class WingedSandalsItem(settings: Settings) : ArmorItem(ModArmorMaterials.wingedSandalsMaterial, Type.BOOTS, settings) {

  @Override
  override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
    if (entity is PlayerEntity) {
      setAllowFlying(entity)
    }
    super.inventoryTick(stack, world, entity, slot, selected)
  }

  @Override
  override fun onStoppedUsing(stack: ItemStack?, world: World?, user: LivingEntity?, remainingUseTicks: Int) {
    if (user is PlayerEntity) {
      setAllowFlying(user)
    }
    super.onStoppedUsing(stack, world, user, remainingUseTicks)
  }

  @Override
  override fun finishUsing(stack: ItemStack?, world: World?, user: LivingEntity?): ItemStack? {
    if (user is PlayerEntity) {
      setAllowFlying(user)
    }
    return super.finishUsing(stack, world, user)
  }


  /** @return Whether the player would be able to fly in vanilla minecraft */
  private fun canNormallyFly(player: PlayerEntity): Boolean {
    return player.isInCreativeMode || player.isSpectator
  }

  /**
   * Sets the player's ability to fly based on whether they have the winged sandals equipped.
   *
   * If the player is flying and becomes incapable of flying, they are dropped out of the air.
   */
  fun setAllowFlying(player: PlayerEntity) {
    val isEquipped = player.getEquippedStack(EquipmentSlot.FEET).item == this
    val allowFlying = isEquipped || canNormallyFly(player)

    if (player.abilities.allowFlying == allowFlying) return
    player.abilities.allowFlying = allowFlying
    if (!allowFlying) player.abilities.flying = false
    player.sendAbilitiesUpdate()
  }
}
