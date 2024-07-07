package com.onesandzer0s.doot.entity;

import com.onesandzer0s.doot.item.TrumpetItem;
import com.onesandzer0s.doot.registry.DootItems;
import com.onesandzer0s.doot.registry.DootSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TrumpetSkeletonEntity extends Skeleton {

   private final TrumpetAttackGoal<AbstractSkeleton> trumpetGoal = new TrumpetAttackGoal<>(this, 1.0D, 60, 6.0F);
   private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false);

   public TrumpetSkeletonEntity( EntityType<? extends Skeleton> type, Level worldIn) {
      super(type, worldIn);
      this.reassessWeaponGoal();
   }

   @Override
   protected void registerGoals() {
      // this.goalSelector.addGoal(0, new AvoidEntityGoalNoCombat<>(this, Player.class, 6.0F, 1.2, 1.2));
      this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
      this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

      this.goalSelector.addGoal(2, new RestrictSunGoal(this));
      this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
      this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true)); // chnge player
      // this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
   }

   protected void populateDefaultEquipmentSlots( RandomSource random, DifficultyInstance difficultyInstance) {
      super.populateDefaultEquipmentSlots(random, difficultyInstance);
      this.startUsingItem(InteractionHand.MAIN_HAND);
      this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(DootItems.TRUMPET.get()));

   }

   @Nullable
   public SpawnGroupData finalizeSpawn( ServerLevelAccessor pLevel, DifficultyInstance p_32147_,
                                        MobSpawnType p_32148_, @Nullable SpawnGroupData p_32149_, @Nullable CompoundTag p_32150_) {
      p_32149_ = super.finalizeSpawn(pLevel, p_32147_, p_32148_, p_32149_, p_32150_);
      RandomSource randomsource = pLevel.getRandom();

      this.populateDefaultEquipmentSlots(randomsource, p_32147_);
      this.populateDefaultEquipmentEnchantments(randomsource, p_32147_);
      //    this.reassessWeaponGoal();
      this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * p_32147_.getSpecialMultiplier());
      if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
         LocalDate localdate = LocalDate.now();
         int i = localdate.get(ChronoField.DAY_OF_MONTH);
         int j = localdate.get(ChronoField.MONTH_OF_YEAR);
         if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
            this.setItemSlot(EquipmentSlot.HEAD,
                    new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
            this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
         }
      }

      return p_32149_;
   }

   protected SoundEvent getAmbientSound() {
      return (this.random.nextInt(5) == 0) ? DootSounds.TRUMPET_SOUND.get() : SoundEvents.SKELETON_AMBIENT;
   }

   // @Override
   // public Packet<?> getAddEntityPacket() {
   //     return NetworkHooks.getEntitySpawningPacket(this);
   // }

   public static AttributeSupplier.Builder createAttributes() {
      return LivingEntity.createLivingAttributes()
              .add(Attributes.ATTACK_DAMAGE, 3.0)
              .add(Attributes.ATTACK_KNOCKBACK, 0.5)
              .add(Attributes.MAX_HEALTH, 20.0)
              .add(Attributes.FOLLOW_RANGE, 40.0)
              .add(Attributes.MOVEMENT_SPEED, 0.3);
   }

   /**
    * sets this entity's combat AI.
    */
   public void reassessWeaponGoal() {
      if (this.level() != null && !this.level().isClientSide) {
         this.goalSelector.removeGoal(this.meleeGoal);
         this.goalSelector.removeGoal(this.trumpetGoal);
         ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this,
                 item -> item instanceof TrumpetItem));
         if (itemstack.is(DootItems.TRUMPET.get())) {
            int i = 30;
            if (this.level().getDifficulty() != Difficulty.HARD) {
               i = 60;
            }
            this.trumpetGoal.setMinAttackInterval(i);
            this.goalSelector.addGoal(4, this.trumpetGoal);
         } else {
            // this.goalSelector.addGoal(4, this.meleeGoal);
         }

      }
   }


}
