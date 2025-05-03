package com.adilhanney.wingedsandals

import com.adilhanney.wingedsandals.item.ModItems
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object WingedSandals : ModInitializer {
  const val MOD_ID = "wingedsandals"
  val logger = LoggerFactory.getLogger(MOD_ID)!!

  override fun onInitialize() {
    logger.info("Hello Fabric world!")
    ModItems.registerItems()
  }
}
