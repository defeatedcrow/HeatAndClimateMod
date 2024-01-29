package defeatedcrow.hac.food.recipe;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class VillagerTradeDC {

	public static final void init() {
		VillagerTrades.TRADES.get(VillagerProfession.FARMER).replace(5, new VillagerTrades.ItemListing[] {
			new ItemsForEmeralds(Items.GOLDEN_CARROT, 3, 3, 30),
			new ItemsForEmeralds(Items.GLISTERING_MELON_SLICE, 4, 3, 30) });
	}

	static class EmeraldForItems implements VillagerTrades.ItemListing {
		private final Item item;
		private final int cost;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public EmeraldForItems(ItemLike i, int co, int max, int xp) {
			this.item = i.asItem();
			this.cost = co;
			this.maxUses = max;
			this.villagerXp = xp;
			this.priceMultiplier = 0.05F;
		}

		@Override
		public MerchantOffer getOffer(Entity entity, RandomSource random) {
			ItemStack itemstack = new ItemStack(this.item, this.cost);
			return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

	static class ItemsForEmeralds implements VillagerTrades.ItemListing {
		private final ItemStack itemStack;
		private final int emeraldCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public ItemsForEmeralds(Block block, int cost, int count, int max, int xp) {
			this(new ItemStack(block), cost, count, max, xp);
		}

		public ItemsForEmeralds(Item item, int cost, int count, int xp) {
			this(new ItemStack(item), cost, count, 12, xp);
		}

		public ItemsForEmeralds(Item item, int cost, int count, int max, int xp) {
			this(new ItemStack(item), cost, count, max, xp);
		}

		public ItemsForEmeralds(ItemStack item, int cost, int count, int max, int xp) {
			this(item, cost, count, max, xp, 0.05F);
		}

		public ItemsForEmeralds(ItemStack stack, int cost, int count, int max, int xp, float f) {
			this.itemStack = stack;
			this.emeraldCost = cost;
			this.numberOfItems = count;
			this.maxUses = max;
			this.villagerXp = xp;
			this.priceMultiplier = f;
		}

		@Override
		public MerchantOffer getOffer(Entity entity, RandomSource random) {
			return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

}
