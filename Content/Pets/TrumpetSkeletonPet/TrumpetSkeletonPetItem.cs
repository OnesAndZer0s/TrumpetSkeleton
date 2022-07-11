// using TrumpetSkeleton.Content.Items;
// using Microsoft.Xna.Framework;
// using Terraria;
// using Terraria.ID;
// using Terraria.GameContent.Creative;
// using Terraria.ModLoader;

// namespace TrumpetSkeleton.Content.Pets.TrumpetSkeletonPet
// {
// 	public class TrumpetSkeletonPetItem : ModItem
// 	{
// 		public override void SetStaticDefaults() {
// 			DisplayName.SetDefault("Toy Trumpet");
// 			Tooltip.SetDefault("Summons a Trumpet Skeleton");

// 			CreativeItemSacrificesCatalog.Instance.SacrificeCountNeededByItemId[Type] = 1;
// 		}

// 		public override void SetDefaults() {
// 			Item.CloneDefaults(ItemID.ZephyrFish); // Copy the Defaults of the Zephyr Fish Item.

// 			Item.shoot = ModContent.ProjectileType<TrumpetSkeletonPetProjectile>(); // "Shoot" your pet projectile.
// 			Item.buffType = ModContent.BuffType<TrumpetSkeletonPetBuff>(); // Apply buff upon usage of the Item.
// 		}

// 		public override void UseStyle(Player player, Rectangle heldItemFrame) {
// 			if (player.whoAmI == Main.myPlayer && player.itemTime == 0) {
// 				player.AddBuff(Item.buffType, 3600);
// 			}
// 		}

// 		// Please see Content/ExampleRecipes.cs for a detailed explanation of recipe creation.
// 		public override void AddRecipes() {
// 			// CreateRecipe()
// 			// 	.AddIngredient<ExampleItem>()
// 			// 	.AddTile<>()
// 			// 	.Register();
// 		}
// 	}
// }

