package com.adilhanney.wingedsandals

import com.adilhanney.wingedsandals.datagen.ModModelProvider
import com.adilhanney.wingedsandals.datagen.ModRecipeProvider
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

@Suppress("unused")
@EventBusSubscriber(modid = WingedSandals.MODID, bus = EventBusSubscriber.Bus.MOD)
object WingedSandalsDataGenerator {
  //? if >=1.21.4 {
  /*@SubscribeEvent
  fun gatherDataClient(event: GatherDataEvent.Client) {
    event.createProvider(::ModModelProvider)
  }

  @SubscribeEvent
  fun gatherDataServer(event: GatherDataEvent.Server) {
    event.createProvider(ModRecipeProvider::Runner)
  }
  *///?} else {
  @SubscribeEvent
  fun gatherData(event: GatherDataEvent) {
    val generator = event.generator
    val output = generator.packOutput
    val existingFileHelper = event.existingFileHelper
    val lookupProvider = event.lookupProvider

    generator.addProvider(
      event.includeClient(),
      ModModelProvider(output, existingFileHelper)
    )
    generator.addProvider(
      event.includeServer(),
      //? if >=1.21.2 {
      /*ModRecipeProvider.Runner(output, lookupProvider)
      *///?} else {
      ModRecipeProvider(output, lookupProvider)
      //?}
    )
  }
  //?}
}
