package com.onesandzer0s.doot.event;

import com.onesandzer0s.doot.DootDootMod;
import com.onesandzer0s.doot.entity.TrumpetSkeletonRenderer;
import com.onesandzer0s.doot.registry.DootEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DootDootMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvents {

   @SubscribeEvent
   public static void onEntityRendererRegister( EntityRenderersEvent.RegisterRenderers event) {
      event.registerEntityRenderer(DootEntities.TRUMPET_SKELETON.get(), TrumpetSkeletonRenderer::new);
   }
}
