package com.onesandzer0s.doot.registry;

import com.onesandzer0s.doot.DootDootMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DootSounds {

   public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DootDootMod.MODID);

   public static final RegistryObject<SoundEvent> TRUMPET_SOUND = SOUNDS.register("item.trumpet.use",
           () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DootDootMod.MODID, "item.trumpet.use")));
}
