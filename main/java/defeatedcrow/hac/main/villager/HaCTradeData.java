package defeatedcrow.hac.main.villager;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.item.equip.ItemArmorDC;
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
	public static final List<HaCTradeData> TAILOR1 = Lists.newArrayList();
	public static final List<HaCTradeData> TAILOR2 = Lists.newArrayList();
	public static final List<HaCTradeData> TAILOR3 = Lists.newArrayList();

	public static void init() {

		AGRI1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomCont(1), 1));
		AGRI1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomLogCont(8), 2));

		AGRI2.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.foodDust, 4, 2), 0));
		AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomCont(1), 0));
		AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomCardboard(1), 2));
		AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomLogCont(16), 4));

		if (ModuleConfig.food) {
			AGRI1.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomBasket(1), 2));
			AGRI1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSeed(1), 1));
			AGRI1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.foodMaterials, 4, 0), 1));
			AGRI1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.miscDust, 8, 4), 0));
			AGRI1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSeed(1), 0));
			AGRI1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.foodDust, 8, 0), 0));
			AGRI1.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomCrop(4), 0));

			AGRI2.add(new HaCTradeData(TradeType.BUY, new ItemStack(FoodInit.paperPack, 4, 4), 3));
			AGRI2.add(new HaCTradeData(TradeType.BUY, new ItemStack(FoodInit.bread, 3, 0), 1));
			AGRI2.add(new HaCTradeData(TradeType.BUY, new ItemStack(FoodInit.bread, 3, 0), 1));
			AGRI2.add(new HaCTradeData(TradeType.BUY, new ItemStack(Items.DYE, 8, 15), 2));
			AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSapling(1), 0));
			AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSapling(1), 0));
			AGRI2.add(new HaCTradeData(TradeType.SELL, new ItemStack(FoodInit.teaLeaves, 4, 2), 1));
			AGRI2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomSeed(1), 0));
		}

		TRADE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 3), 2));
		TRADE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 3), 2));
		TRADE1.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomGem(1), 2));
		TRADE1.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomGem(1), 2));
		TRADE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.dustBags, 1, 1), 1));
		TRADE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.dustBags, 1, 2), 1));
		TRADE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.chalLamp, 1, 2), 3));
		TRADE1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomBuildingBlock(32), 2));
		TRADE1.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomBuildingBlock(64), 4));
		TRADE2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomGem(1), 5));
		TRADE2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomGem(1), 7));
		TRADE2.add(new HaCTradeData(TradeType.BUY, MainUtil.getIngot(3), 5));

		TRADE2.add(new HaCTradeData(TradeType.SELL, MainUtil.getRandomGem2(1), 4));
		TRADE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.silkworm, 9, 0), 8));
		TRADE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 6), 4));
		TRADE2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomBuildingBlock(32), 2));
		TRADE2.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomBuildingBlock(64), 4));
		TRADE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.selenite, 1, 0), 2));

		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.silkworm, 9, 0), 5));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.silkworm, 9, 0), 5));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 6), 4));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 4, 6), 14));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.cropBasket, 1, 11), 4));
		TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.miscDust, 8, 12), 8));

		TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 6), 2));
		TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 6), 2));
		TRADE4.add(new HaCTradeData(TradeType.BUY, MainUtil.getIngot(3), 4));

		if (ModuleConfig.build_advanced) {
			TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.chandelierGypsum, 1, 0), 6));
		}

		if (ModuleConfig.magic) {
			TRADE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MagicInit.expGem, 1, 0), 2));
			TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MagicInit.elestial, 1, 0), 12));
			TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MagicInit.lotusCandleBlack, 1, 0), 8));
			TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MagicInit.biomeOrb, 1, 0), 3));
			TRADE4.add(new HaCTradeData(TradeType.BUY, new ItemStack(MagicInit.biomeOrb, 1, 2), 6));
			TRADE4.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomRing(1), 12));
			TRADE4.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomRing(1), 10));
		}

		if (ModuleConfig.machine_advanced) {
			TRADE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.synthetic, 1, 0), 4));
		}

		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.oreItem, 8, 0), 2));
		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.oreItem, 8, 1), 2));
		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.oreItem, 8, 2), 3));
		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.oreItem, 8, 3), 3));
		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.oreItem, 8, 4), 2));
		MACHINE1.add(new HaCTradeData(TradeType.SELL, new ItemStack(MainInit.miscDust, 8, 2), 1));
		MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gears, 1, 1), 1));
		MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gears, 1, 1), 1));
		MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gears, 1, 2), 3));

		MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gears, 1, 2), 5));
		MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gears, 1, 3), 4));

		if (ModuleConfig.build_advanced) {
			MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.fenceLadderSteel, 8, 0), 2));
		}

		if (ModuleConfig.weapon_advanced) {
			MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.gun, 1, 0), 30));
		}

		if (ModuleConfig.tool) {
			MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.dcPickaxe[8], 1, 0), 16));
			MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.dcSpade[8], 1, 0), 16));
		}

		if (ModuleConfig.machine) {
			MACHINE1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.shaft_s, 1, 0), 2));
			MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.shaft3_s, 1, 0), 10));
			MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.IBC, 1, 0), 12));
			MACHINE2.add(new HaCTradeData(TradeType.SELL, new ItemStack(MachineInit.fuelCont, 1, 1), 2));
			MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.reagent, 16, 8), 4));
			MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.reagent, 16, 8), 4));
			MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.shaft2_s, 1, 0), 15));
			MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.conveyor, 4, 0), 6));
			MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.hopperFluid, 1, 0), 20));
		}

		if (ModuleConfig.machine_advanced) {
			MACHINE2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.dynamite, 4, 0), 9));
			MACHINE3.add(new HaCTradeData(TradeType.BUY, MainUtil.getRandomCoating(1), 8));
			MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.rotaryBlade, 1, 0), 12));
			MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.rotaryBlade, 1, 1), 24));
			MACHINE3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.scooter, 1, 0), 32));
		}

		TAILOR1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.leatherHat, 1, 0), 3));
		TAILOR1.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.linenUnder, 1,
				0)), 3));
		TAILOR1.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 3), 1));

		TAILOR2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 4), 1));
		TAILOR2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothCoat, 1, 0), 5));
		TAILOR2.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.clothUnder, 1,
				0)), 5));

		TAILOR3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.cottonHat, 1, 0), 8));
		TAILOR3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.clothes, 1, 7), 4));

		if (ModuleConfig.clothes_advanced) {
			TAILOR1.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.linenShirt, 1,
					0)), 3));
			TAILOR1.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.linenBottom,
					1, 0)), 3));

			TAILOR2.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.clothShirt, 1,
					0)), 5));
			TAILOR2.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.clothBottom,
					1, 0)), 5));
			TAILOR2.add(new HaCTradeData(TradeType.BUY, new ItemStack(MainInit.modsCoat, 1, 0), 5));
			TAILOR2.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.woolJacket, 1,
					0)), 4));
			TAILOR2.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.hoodie, 1,
					0)), 5));

			TAILOR3.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.trackSuit, 1,
					0)), 7));
			TAILOR3.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.silkCape, 1,
					0)), 8));
			TAILOR3.add(new HaCTradeData(TradeType.BUY, ItemArmorDC.setRondomColor(new ItemStack(MainInit.workerWear, 1,
					0)), 6));

			if (ModuleConfig.machine) {
				TAILOR3.add(new HaCTradeData(TradeType.BUY, new ItemStack(MachineInit.synthetic, 1, 1), 5));
			}
		}

	}
}
