using TrumpetSkeleton.Content.Tiles;
using Terraria;
using Terraria.ModLoader;
using Terraria.ID;
using Terraria.GameContent.Creative;

namespace TrumpetSkeleton.Content.Items.Placeable
{
	public class TrumpetSkeletonBannerItem : ModItem
	{

		public override void SetStaticDefaults() {
			DisplayName.SetDefault("Trumpet Skeleton Banner");

		}
		public override void SetDefaults() {
			CreativeItemSacrificesCatalog.Instance.SacrificeCountNeededByItemId[Type] = 1;

			Item.width = 10;
			Item.height = 24;
			Item.maxStack = 99;
			Item.useTurn = true;
			Item.autoReuse = true;
			Item.useAnimation = 15;
			Item.useTime = 10;
			Item.useStyle = ItemUseStyleID.Swing;
			Item.consumable = true;
			Item.rare = ItemRarityID.Blue;
			Item.value = Item.buyPrice(0, 0, 10, 0);
			Item.createTile = ModContent.TileType<TrumpetSkeletonBanner>();
			Item.placeStyle = 0;        //Place style means which frame(Horizontally, starting from 0) of the tile should be placed
		}
	}
}