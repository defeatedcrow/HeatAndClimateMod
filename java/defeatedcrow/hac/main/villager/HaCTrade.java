package defeatedcrow.hac.main.villager;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.main.villager.HaCTradeData.TradeType;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class HaCTrade {

	public static final HaCTrade INSTANCE = new HaCTrade();

	public class Get implements ITradeList {
		@Nullable
		private final PriceInfo info;
		private final List<HaCTradeData> data;

		public Get(List<HaCTradeData> list, @Nullable PriceInfo priceInfo) {
			info = priceInfo;
			data = list;
		}

		@Override
		public void modifyMerchantRecipeList(MerchantRecipeList recipes, Random random) {
			if (data != null && !data.isEmpty()) {
				int p1 = 1;
				if (info != null) {
					p1 = info.getPrice(random);
				}
				int l = data.size();
				HaCTradeData trade = data.get(random.nextInt(l));
				ItemStack item = trade.item;
				p1 += trade.price;
				ItemStack emerald = new ItemStack(Items.EMERALD, p1);
				if (trade.type == TradeType.BUY) {
					recipes.add(new MerchantRecipe(emerald, item));
				} else {
					recipes.add(new MerchantRecipe(item, emerald));
				}
			}
		}
	}

}
