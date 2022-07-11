using Terraria;
using Terraria.ID;
using Terraria.ModLoader;

namespace TrumpetSkeleton.Content.Pets.TrumpetSkeletonPet
{
	public class TrumpetSkeletonPetProjectile : ModProjectile
	{
		public override void SetStaticDefaults() {
			DisplayName.SetDefault("Trumpet Skeleton");

			Main.projFrames[Projectile.type] = 5;
			Main.projPet[Projectile.type] = true;
		}

		public override void SetDefaults() {
			// Projectile.CloneDefaults(ProjectileID.BabyImp); // Copy the stats of the Zephyr Fish
			// AIType = ProjectileID.BabyImp; // Copy the AI of the Zephyr Fish.
		}

		public override bool PreAI() {
			Player player = Main.player[Projectile.owner];

			player.petFlagBabyImp = false; // Relic from aiType

			return true;
		}

		public override void AI() {
			Player player = Main.player[Projectile.owner];

			// Keep the projectile from disappearing as long as the player isn't dead and has the pet buff.
			// if (!player.dead && player.HasBuff(ModContent.BuffType<TrumpetSkeletonPetBuff>())) {
			// 	Projectile.timeLeft = 2;
			// }
		}
	}
}
