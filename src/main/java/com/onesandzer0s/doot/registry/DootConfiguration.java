package com.onesandzer0s.doot.registry;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DootConfiguration {
   public static ForgeConfigSpec COMMON_CONFIG;

   public static final String CATEGORY_SETTINGS = "settings";

   public static final String CATEGORY_TRUMPET = "trumpet";
   public static ForgeConfigSpec.DoubleValue TRUMPET_RANGE;
   public static ForgeConfigSpec.IntValue TRUMPET_DURABILITY;


   static {
      ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

      COMMON_BUILDER.comment("Game Settings").push(CATEGORY_SETTINGS);

      COMMON_BUILDER.comment("Trumpet").push(CATEGORY_TRUMPET);
      TRUMPET_RANGE = COMMON_BUILDER.comment("How far can the Trumpet be heard?")
              .defineInRange("trumpetRange", 10.0, 0.0, Double.MAX_VALUE);
      TRUMPET_DURABILITY = COMMON_BUILDER.comment("How many uses does the Trumpet have?")
              .defineInRange("trumpetDurability", 64, 1, Integer.MAX_VALUE);

      COMMON_BUILDER.pop();


      COMMON_BUILDER.pop();

      COMMON_CONFIG = COMMON_BUILDER.build();

   }
}
