package defeatedcrow.hac.core.event;

import java.util.List;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WandererTradeEventDC {

	@SubscribeEvent
	public static void onLoadingTrade(WandererTradesEvent event) {
		List<ItemListing> generic = event.getGenericTrades();
		List<ItemListing> rare = event.getRareTrades();
		// 両替
		generic.add(new ForItems(new ItemStack(Items.GOLD_INGOT, 3), 2, 3));
		// 種袋
		generic.add(new ForEmeralds(new ItemStack(CoreInit.SHIRT_LINEN_CYAN.get()), 1, 3));
		generic.add(new ForEmeralds(new ItemStack(CoreInit.COIL_CASE.get()), 5, 12));
		generic.add(new ForEmeralds(new ItemStack(CoreInit.HARPOON_STEEL.get()), 4, 12));
		generic.add(new ForEmeralds(new ItemStack(CoreInit.CALABASH_BUCKET.get()), 1, 3));

		generic.add(new ForEmeralds(new ItemStack(MagicInit.CARD_GB.get()), 2, 4));
		generic.add(new ForEmeralds(new ItemStack(MagicInit.CARD_BR.get()), 5, 12));
		generic.add(new ForEmeralds(new ItemStack(MagicInit.CARD_WU.get()), 3, 8));

		generic.add(new ForEmeralds(new ItemStack(MagicInit.RING_SILVER_WHITE.get()), 4, 8));
		generic.add(new ForEmeralds(new ItemStack(MagicInit.RING_SILVER_BLACK.get()), 4, 8));
		generic.add(new ForEmeralds(new ItemStack(MagicInit.RING_SILVER_RED.get()), 4, 8));

		rare.add(new ForEmeralds(new ItemStack(MagicInit.PENDANT_SILVER_WHITE.get()), 6, 12));
		rare.add(new ForEmeralds(new ItemStack(MagicInit.PENDANT_SILVER_BLUE.get()), 6, 12));
		rare.add(new ForEmeralds(new ItemStack(MagicInit.PENDANT_SILVER_RED.get()), 6, 12));
	}

	static class ForEmeralds implements VillagerTrades.ItemListing {
		private final ItemStack itemStack;
		private final int emeraldCost;
		private final int maxUses;

		public ForEmeralds(ItemStack stack, int cost, int max) {
			this.itemStack = stack;
			this.emeraldCost = cost;
			this.maxUses = max;
		}

		@Override
		public MerchantOffer getOffer(Entity entity, RandomSource random) {
			return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), this.itemStack.copy(), this.maxUses, 1, 0.05F);
		}
	}

	static class ForItems implements VillagerTrades.ItemListing {
		private final ItemStack itemStack;
		private final int emeraldCost;
		private final int maxUses;

		public ForItems(ItemStack stack, int cost, int max) {
			this.itemStack = stack;
			this.emeraldCost = cost;
			this.maxUses = max;
		}

		@Override
		public MerchantOffer getOffer(Entity entity, RandomSource random) {
			return new MerchantOffer(this.itemStack.copy(), new ItemStack(Items.EMERALD, this.emeraldCost), this.maxUses, 1, 0.05F);
		}
	}

}
