package com.onesandzer0s.doot.item;


import com.onesandzer0s.doot.registry.DootConfiguration;
import com.onesandzer0s.doot.registry.DootSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

public class TrumpetItem extends Item {

   public TrumpetItem(Properties properties) {
      super(properties);
   }


   public int getMaxDamage(ItemStack stack) {
      return DootConfiguration.TRUMPET_DURABILITY.get();
   }

   public int getEnchantmentValue() {
      return 1;
   }


   public static void scare( Level world, LivingEntity user) {
      if (!world.isClientSide) {
         List<LivingEntity> spooked = world.getEntitiesOfClass(
                 LivingEntity.class,
                 user.getBoundingBox().inflate(
                         DootConfiguration.TRUMPET_RANGE.get()
                                 + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, user.getMainHandItem()) * 2));

         for (LivingEntity entity : spooked) {
            if (entity == user)
               continue;

            double punch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, user.getMainHandItem()) * 2;

            double deltaX = entity.getX() - user.getX() + world.random.nextDouble() - world.random.nextDouble();
            double deltaZ = entity.getZ() - user.getZ() + world.random.nextDouble() - world.random.nextDouble();

            double distance = Math.sqrt((deltaX * deltaX) + (deltaZ * deltaZ));

            entity.hurtMarked = true;
            entity.setLastHurtByMob(user);

            entity.push(
                    (0.5 * deltaX + punch) / distance,
                    (5 + punch) / (10 + distance),
                    (0.5 + deltaZ + punch) / distance);
            if (entity instanceof PathfinderMob mob){
               // add panic goal

               Stream<WrappedGoal> goals = mob.goalSelector.getRunningGoals();
               if (goals.anyMatch(goal -> goal.getGoal() instanceof PanicGoal)){
                  return;
               }
                mob.goalSelector.addGoal(0, new PanicGoal((PathfinderMob) entity, 1.5D)
                {
                   @Override
                   public boolean canUse() {
                      return this.findRandomPosition();
                   }

                   @Override
                   public void stop() {
                      super.stop();
                      mob.goalSelector.removeGoal(this);
                   }
                });

//               mob.goalSelector.getAvailableGoals()
            }
         }
      }
   }

   @Override
   public UseAnim getUseAnimation(ItemStack stack) {
      return UseAnim.DRINK;
   }

   @Override
   public SoundEvent getDrinkingSound() {
      return DootSounds.TRUMPET_SOUND.get();
   }

   @Override
   public int getUseDuration( ItemStack stack) {
      return 55 - Math.min(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack) * 7, 35);
   }

   @Override
   public void onUseTick(Level level, LivingEntity player, ItemStack stack, int count) {
      super.onUseTick(level, player, stack, count);

      int useTime = getUseDuration(stack) - count;
      if (useTime == 10 - Math.min(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack) * 2, 10)) {
         player.playSound(DootSounds.TRUMPET_SOUND.get(), 1f, 0.95F + level.random.nextFloat() * 0.1F);
         TrumpetItem.scare(level, player);
         stack.getItem().damageItem(stack, 1, player, (entity) -> entity.broadcastBreakEvent(player.getUsedItemHand()));
      } else if (useTime >= 15
              - Math.min(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack) * 2, 10)) {
         player.stopUsingItem();
      }
   }

   @Override
   public @NotNull InteractionResultHolder<ItemStack> use( Level world, Player playerIn, InteractionHand handIn) {
      playerIn.startUsingItem(handIn);
      return super.use(world, playerIn, handIn);
   }

}
