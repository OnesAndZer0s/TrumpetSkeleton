package com.onesandzer0s.doot;

import com.mojang.logging.LogUtils;
import com.onesandzer0s.doot.entity.TrumpetSkeletonEntity;
import com.onesandzer0s.doot.item.TrumpetItem;
import com.onesandzer0s.doot.registry.DootConfiguration;
import com.onesandzer0s.doot.registry.DootEntities;
import com.onesandzer0s.doot.registry.DootItems;
import com.onesandzer0s.doot.registry.DootSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DootDootMod.MODID)
public class DootDootMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "doot";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public DootDootMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DootConfiguration.COMMON_CONFIG);


        modEventBus.addListener(this::onSpawnPlacement);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::newEntityAttributes);





        // Register the Deferred Register to the mod event bus so blocks get registered
        // Register the Deferred Register to the mod event bus so items get registered
        DootEntities.ENTITIES.register(modEventBus);
        DootItems.ITEMS.register(modEventBus);
        DootSounds.SOUNDS.register(modEventBus);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void onSpawnPlacement(final SpawnPlacementRegisterEvent event)
    {
        // Register the spawn placement for the trumpet skeleton
        event.register(DootEntities.TRUMPET_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TrumpetSkeletonEntity::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
            event.accept(DootItems.TRUMPET_SKELETON_SPAWN_EGG.get());

        if (event.getTabKey() == CreativeModeTabs.COMBAT)
            event.accept(DootItems.TRUMPET.get());
    }


    public void newEntityAttributes( EntityAttributeCreationEvent event) {
        event.put(DootEntities.TRUMPET_SKELETON.get(), TrumpetSkeletonEntity.createAttributes().build());

    }
}
