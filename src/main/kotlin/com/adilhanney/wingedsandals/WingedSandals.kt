package com.adilhanney.wingedsandals

import com.adilhanney.wingedsandals.item.ModArmorMaterials
import com.adilhanney.wingedsandals.item.ModItems
import com.mojang.logging.LogUtils
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.server.ServerStartingEvent
import org.slf4j.Logger

@Mod(WingedSandals.Companion.MODID)
class WingedSandals(modEventBus: IEventBus, modContainer: ModContainer) {
  init {
    modEventBus.addListener<FMLCommonSetupEvent> { event -> this.commonSetup(event) }

    ModArmorMaterials.register(modEventBus)
    ModItems.register(modEventBus)

    // Register ourselves for server and other game events we are interested in.
    // Note that this is necessary if and only if we want *this* class (WingedSandals) to respond directly to events.
    // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
    NeoForge.EVENT_BUS.register(this)
  }

  private fun commonSetup(event: FMLCommonSetupEvent?) {
    // Some common setup code
    LOGGER.info("HELLO FROM COMMON SETUP")
  }

  @SubscribeEvent
  fun onServerStarting(event: ServerStartingEvent?) {
  }

  @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
  object ClientModEvents {
    @SubscribeEvent
    fun onClientSetup(event: FMLClientSetupEvent?) {
    }
  }

  companion object {
    const val MODID = "wingedsandals"
    val LOGGER: Logger = LogUtils.getLogger()
  }
}
