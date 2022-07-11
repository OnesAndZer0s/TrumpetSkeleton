using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using rail;
using ReLogic.Utilities;
using System;
using Terraria;
using Terraria.Audio;
using Terraria.GameContent.Creative;
using Terraria.ID;
using Terraria.ModLoader;
using Terraria.ModLoader.Config;
// using TrumpetSkeleton.Content.Projectiles;

namespace TrumpetSkeleton.Content.Items
{
	/// <summary>
	/// This item lets you test the existing ItemUseStyleID values for Item.useStyle. Note that the sword texture might not fit each of the useStyle animations.
	/// </summary>
	public class Trumpet : ModItem
	{
		private static Rectangle invItem = new Rectangle(0, 0, 40, 40);
		private static Rectangle worldItem = new Rectangle(0, 32, 32, 32);
		public static readonly SoundStyle DootSound = new($"{nameof(TrumpetSkeleton)}/Content/Items/doot") {
			Volume = 1.00f
		};

		public override void SetStaticDefaults() {
			Tooltip.SetDefault(
				"Playable Instrument\n" +
				"'doot'"
						);
		}

		public override void SetDefaults() {

			CreativeItemSacrificesCatalog.Instance.SacrificeCountNeededByItemId[Type] = 1;
			Item.width = 40;
			Item.height = 40;
			Item.useStyle = ItemUseStyleID.EatFood;
			Item.useTime = 30;
			Item.useAnimation = 30;
			Item.holdStyle = ItemHoldStyleID.HoldFront;
			Item.UseSound = DootSound;
			Item.DamageType = DamageClass.Magic;
			Item.autoReuse = false;
			Item.damage = 16;
			Item.knockBack = 100f;
			// Item.noUseGraphic = true;
			Item.mana = 4;
			Item.noMelee = true;
			Item.rare = ItemRarityID.Orange;
			// Item.shoot = ModContent.ProjectileType<TrumpetProjectile>();

		}
		public override bool? UseItem(Player player) {
			for (int i = 0; i < Main.maxProjectiles; i++) {
				Projectile proj = Main.projectile[i];
				float distance = (player.position.DistanceSQ(proj.position) / 128);
				if (!proj.minion && proj.active && proj.position.WithinRange(player.position, 256) && Math.Sign(proj.position.DirectionTo(player.position).X) == Math.Sign(-player.direction)) {
					proj.velocity.X += (proj.velocity.X / Main.rand.NextFloat(10, 50)) + (128 / distance) * player.direction;
					proj.friendly = true;
				}
			}

			for (int i = 0; i < Main.npc.Length; i++) {
				NPC npc = Main.npc[i];
				float distance = (player.position.DistanceSQ(npc.position) / 128);
				if (!npc.townNPC && !(npc.CountsAsACritter && player.dontHurtCritters) && npc.active && npc.position.WithinRange(player.position, 256) && Math.Sign(npc.position.DirectionTo(player.position).X) == Math.Sign(-player.direction)) {
					npc.StrikeNPC((int)(Item.damage - (distance / 16)), (npc.knockBackResist + (Item.knockBack / Main.rand.NextFloat(60, 150))) * (512 / distance), player.direction, false, true, false);
				}
			}

			// SoundEngine.PlaySound(DootSound);
			return true;
		}

		public override void HoldItemFrame(Player player) {

			player.itemRotation = (MathHelper.PiOver2 - MathHelper.PiOver4 + 0.8f) * player.direction;
			player.itemLocation.Y = player.Center.Y - 11;
			player.itemLocation.X = player.Center.X - 6 * player.direction;
		}

		public override void UseItemFrame(Player player) {
			player.itemRotation = (MathHelper.PiOver2 - MathHelper.PiOver4) * player.direction;
			player.itemLocation.Y = player.Center.Y - 3;
		}
	}
}
