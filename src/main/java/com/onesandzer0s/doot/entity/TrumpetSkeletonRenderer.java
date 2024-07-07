package com.onesandzer0s.doot.entity;

import com.onesandzer0s.doot.DootDootMod;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class TrumpetSkeletonRenderer extends HumanoidMobRenderer<TrumpetSkeletonEntity, SkeletonModel<TrumpetSkeletonEntity>> {

   private static final ResourceLocation TEXTURE = new ResourceLocation(DootDootMod.MODID,
           "textures/entity/trumpet_skeleton.png");

   public TrumpetSkeletonRenderer( EntityRendererProvider.Context context) {
      super(context, new SkeletonModel<>(SkeletonModel.createBodyLayer().bakeRoot()), 0.3f);
   }

   @Override
   public @NotNull ResourceLocation getTextureLocation( TrumpetSkeletonEntity entity) {
      return TEXTURE;
   }
}