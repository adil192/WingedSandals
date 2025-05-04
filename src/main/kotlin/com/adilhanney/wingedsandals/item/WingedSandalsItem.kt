package com.adilhanney.wingedsandals.item

import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class WingedSandalsItem(settings: Settings) : ArmorItem(ModArmorMaterials.wingedSandalsMaterial, Type.BOOTS, settings) {

  @Override
  override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
    if (entity is PlayerEntity) {
      val isEquipped = entity.getEquippedStack(EquipmentSlot.FEET).item == this
      setAllowFlying(entity, isEquipped || canNormallyFly(entity))
    }
    super.inventoryTick(stack, world, entity, slot, selected)
  }

  @Override
  override fun onStoppedUsing(stack: ItemStack?, world: World?, user: LivingEntity?, remainingUseTicks: Int) {
    if (user is PlayerEntity) {
      setAllowFlying(user, canNormallyFly(user))
    }
    super.onStoppedUsing(stack, world, user, remainingUseTicks)
  }

  @Override
  override fun finishUsing(stack: ItemStack?, world: World?, user: LivingEntity?): ItemStack? {
    if (user is PlayerEntity) {
      setAllowFlying(user, canNormallyFly(user))
    }
    return super.finishUsing(stack, world, user)
  }


  /** @return Whether the player would be able to fly in vanilla minecraft */
  private fun canNormallyFly(player: PlayerEntity): Boolean {
    return player.isInCreativeMode || player.isSpectator
  }

  /**
   * Sets the player's ability to fly.
   *
   * If [allowFlying] is false, the player will drop out of the air.
   */
  fun setAllowFlying(player: PlayerEntity, allowFlying: Boolean) {
    player.abilities.allowFlying = allowFlying

    if (!allowFlying) {
      dropOutOfAir(player)
    }
  }

  private fun dropOutOfAir(player: PlayerEntity) {
    if (player.isOnGround) return
    if (player.isFallFlying) return // i.e. if they're using an elytra
    if (player.isTouchingWater) return
    if (player.hasStatusEffect(StatusEffects.LEVITATION)) return

    // TODO: This doesn't work. Find out how to drop out of the air.
    player.stopFallFlying()
  }

}
