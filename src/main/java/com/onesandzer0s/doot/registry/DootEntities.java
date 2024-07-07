package com.onesandzer0s.doot.registry;

import com.onesandzer0s.doot.DootDootMod;
import com.onesandzer0s.doot.entity.TrumpetSkeletonEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.html.parser.Entity;

public class DootEntities {
   public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DootDootMod.MODID);

   public static final RegistryObject<EntityType<TrumpetSkeletonEntity>> TRUMPET_SKELETON = ENTITIES.register(
           "trumpet_skeleton",
           () -> EntityType.Builder.of(TrumpetSkeletonEntity::new, MobCategory.MONSTER)
                   .sized(0.6f, 1.99f)
                   .clientTrackingRange(8)
                   .build(new ResourceLocation(DootDootMod.MODID, "trumpet_skeleton").toString()));

}
