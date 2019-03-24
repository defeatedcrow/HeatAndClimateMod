package defeatedcrow.hac.main.villager;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HaCTradeData {
	public final int price;
	public final ItemStack item;
	public final TradeType type;

	public HaCTradeData(TradeType t, ItemStack i, int p) {
		price = p;
		item = i;
		type = t;
	}

	public static enum TradeType {
		BUY,
		SELL;
	}

	public static final List<HaCTradeData> AGRI1 = Lists.newArrayList();
	public static final List<HaCTradeData> AGRI2 = Lists.newArrayList();
	public static final List<HaCTradeData> TRADE1 = Lists.newArrayList();
	public static final List<HaCTradeData> TRADE2 = Lists.newArrayList();
	public static final List<HaCTradeData> TRADE3 = Lists.newArrayList();
	public static final List<HaCTradeData> TRADE4 = Lists.newArrayList();
	public static final List<HaCTradeData> MACHINE1 = Lists.newArrayList();
	public static final List<HaCTradeData> MACHINE2 = Lists.newArrayList();
	public static final List<HaCTradeData> MACHINE3 = Lists.newArrayList();

	public static void init() {
		AGRI1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSeed(1), 1));
		AGRI1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.foodMaterials, 4, 0), 1));
		AGRI1.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomBasket(1), 2));
		AGRI1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.miscDust, 8, 4), 0));
		AGRI1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSeed(1), 0));
		AGRI1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.foodDust, 8, 0), 0));
		AGRI1.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomCrop(4), 0));
		AGRI1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSeed(1), 0));

		AGRI2.add(new HaCTradeData(TradeType.BUY, new ItemStack(FoodInit.paperPack, 4, 4), 3));
		AGRI2.add(new HaCTradeData(TradeType.BUY, new ItemStack(FoodInit.bread, 3, 0), 1));
		AGRI2.add(new HaCTradeData(TradeType.BUY, new ItemStack(FoodInit.bread, 3, 0), 1));
		AGRI2.add(new HaCTradeData(TradeType.BUY, new ItemStack(Items.DYE, 8, 15), 2));
		AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSapling(1), 0));
		AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSapling(1), 0));
		AGRI2.add(new HaCTradeData(TradeType.SELL, new ItemStack(FoodInit.teaLeaves, 4, 2), 1));
		AGRI2.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.foodDust, 4, 2), 0));
		AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSeed(1), 0));

		TRADE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 3), 2));
		TRADE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 3), 2));
		TRADE1.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomGem(1), 2));
		TRADE1.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomGem(1), 2));
		TRADE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.dustBags, 1, 3), 0));
		TRADE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.bakedApple, 4, 3), 2));
		TRADE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.chalLamp, 1, 2), 3));

		TRADE2.add(new HaCTradeData(TradeType.BUY, MainUtil.getIngot(3), 5));
		TRADE2.add(new HaCTradeData(TradeType.SELL, new ItemStack(MagicInit.expGem, 1, 0), 2));
		TRADE2.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.clothes, 1, 2), 1));
		TRADE2.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.metalBlock, 1, 5), 2));
		TRADE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.chairMarble, 1, 0), 3));
		TRADE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.squaretableMarble, 1, 0), 4));
		TRADE2.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomGem2(1), 4));
		TRADE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.silkworm, 9, 0), 8));
		TRADE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 6), 4));

		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.silkworm, 9, 0), 5));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.silkworm, 9, 0), 5));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 6), 4));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 6), 4));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.synthetic, 1, 0), 4));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.cropBasket, 1, 11), 4));
		TRADE3.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.selenite, 1, 0), 2));
		TRADE3.add(new HaCTradeData(TradeType.SELL, new ItemStack(MagicInit.elestial, 1, 0), 4));

		TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MagicInit.lotusCandleBlack, 1, 0), 8));
		TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MagicInit.biomeOrb, 1, 0), 3));
		TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MagicInit.biomeOrb, 1, 2), 6));
		TRADE4.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomRing(1), 12));
		TRADE4.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomRing(1), 10));
		TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.chandelierGypsum, 1, 0), 6));
		TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 6), 2));
		TRADE4.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomRing(1), 2));
		TRADE4.add(new HaCTradeData(TradeType.BUY, MainUtil.getIngot(3), 4));

		MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.shaft_s, 1, 0), 2));
		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.metalBlock, 1, 4), 1));
		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.miscDust, 8, 2), 1));
		MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.fenceLadderSteel, 8, 0), 2));
		MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gears, 1, 0), 1));
		MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gears, 1, 0), 1));
		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.metalBlock, 1, 6), 2));

		MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.shaft3_s, 1, 0), 10));
		MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.IBC, 1, 0), 12));
		MACHINE2.add(new HaCTradeData(TradeType.SELL, new ItemStack(MachineInit.fuelCont, 1, 1), 0));
		MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gun, 1, 0), 30));
		MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.reagent, 16, 8), 4));
		MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.reagent, 16, 8), 4));
		MACHINE2.add(new HaCTradeData(TradeType.SELL, MainUtil.getGem(9), 0));
		MACHINE2.add(new HaCTradeData(TradeType.SELL, MainUtil.getGem(10), 0));

		MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.shaft2_s, 1, 0), 15));
		MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.dynamite, 4, 0), 9));
		MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.scooter, 1, 0), 20));
		MACHINE3.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomCoating(1), 8));
		MACHINE3.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.metalBlock, 1, 10), 1));
		MACHINE3.add(new HaCTradeData(TradeType.SELL, new ItemStack(MachineInit.machimeMaterials, 1, 0), 0));
		MACHINE3.add(new HaCTradeData(TradeType.SELL, new ItemStack(MachineInit.machimeMaterials, 1, 2), 0));
		MACHINE3.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.gears, 4, 2), 1));
	}
}
