package com.onesandzer0s.doot.registry;

import com.onesandzer0s.doot.DootDootMod;
import com.onesandzer0s.doot.item.TrumpetItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DootItems {
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DootDootMod.MODID);


   public static final RegistryObject<Item> TRUMPET = ITEMS.register("trumpet", () -> new TrumpetItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TRUMPET_SKELETON_SPAWN_EGG = ITEMS.register("trumpet_skeleton_egg", () -> new ForgeSpawnEggItem(DootEntities.TRUMPET_SKELETON, 0xCCC198, 0x665D48,new Item.Properties().stacksTo(64)));

}
