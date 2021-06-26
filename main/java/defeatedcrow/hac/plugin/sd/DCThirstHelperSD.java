package defeatedcrow.hac.plugin.sd;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.DrinkPotionType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;

public class DCThirstHelperSD {

	public static boolean enableThirst() {
		return com.charles445.simpledifficulty.api.config.QuickConfig.isThirstEnabled();
	}

	public static void drink(EntityPlayer player, Fluid f) {
		DCDrink type = DCDrink.getType(f);
		com.charles445.simpledifficulty.api.thirst.ThirstUtil.takeDrink(player, type.getThirst(), type
				.getHydration(), type.getPoisonChance());
	}

	public enum DCDrink {

		TEA(6, 0.5F, 0.0F),
		JUICE(6, 0.2F, 0.0F),
		RAW(4, 0.2F, 0.25F),
		ETHANOL(2, 0.1F, 0.0F),
		RISK(1, 0.0F, 0.75F);

		final int thirst;
		final float hydration;
		final float chance;

		private DCDrink(int i, float f1, float f2) {
			thirst = i;
			hydration = f1;
			chance = f2;
		}

		public int getThirst() {
			return thirst;
		}

		public float getHydration() {
			return hydration;
		}

		public float getPoisonChance() {
			return chance;
		}

		public static DCDrink getType(Fluid f) {
			if (f == null) {
				return RAW;
			} else {
				if (f == MainInit.blackTea)
					return TEA;
				if (f == MainInit.greenTea)
					return TEA;
				if (f == MainInit.coffee)
					return TEA;
				if (f == MainInit.lemon)
					return JUICE;
				if (f == MainInit.stock)
					return JUICE;
				if (f == MainInit.tomatoJuice)
					return JUICE;
				if (f == MainInit.milk)
					return JUICE;
				if (f == MainInit.soyMilk)
					return JUICE;
				if (f == MainInit.hotSpring)
					return JUICE;
				if (f == MainInit.cream)
					return RAW;
				if (f == MainInit.mazai)
					return ETHANOL;
				if (f == MainInit.blackLiquor)
					return RISK;
				if (f == MainInit.oil)
					return RISK;
				if (f == MainInit.fuelOil)
					return RISK;
				if (f == MainInit.fuelGas)
					return RISK;
				if (f == MainInit.ethanol)
					return ETHANOL;
				if (f == MainInit.ammonia)
					return RISK;
				if (f == MainInit.nitricAcid)
					return RISK;
				if (f == MainInit.sulfuricAcid)
					return RISK;
				if (f == MainInit.nitrogen)
					return RISK;
				if (f == MainInit.ammonia)
					return RISK;
				if (f == MainInit.oxygen)
					return RAW;

				if (ModuleConfig.food_advanced) {
					if (f == FoodInit.roseWater)
						return TEA;
					if (f == FoodInit.tonic)
						return TEA;
					if (f == FoodInit.lemon_squash)
						return JUICE;
					if (f == FoodInit.cola)
						return JUICE;
					if (f == FoodInit.beer)
						return JUICE;
					if (f == FoodInit.wine)
						return JUICE;
					if (f == FoodInit.dateWine)
						return JUICE;
					if (f == FoodInit.sake)
						return JUICE;
					if (f == FoodInit.whisky)
						return ETHANOL;
					if (f == FoodInit.brandy)
						return ETHANOL;
					if (f == FoodInit.pomaceBrandy)
						return ETHANOL;
					if (f == FoodInit.araq)
						return ETHANOL;
					if (f == FoodInit.shotyu)
						return ETHANOL;
					if (f == FoodInit.akvavit)
						return ETHANOL;
					if (f == FoodInit.whiteRum)
						return ETHANOL;
					if (f == FoodInit.darkRum)
						return ETHANOL;
					if (f == FoodInit.vodka)
						return ETHANOL;
					if (f == FoodInit.netherWine)
						return JUICE;
					if (f == FoodInit.chorusLiquor)
						return ETHANOL;
				}

				Potion p = DrinkPotionType.getPotion(f);
				if (p != null && p.isBadEffect()) {
					return RISK;
				} else {
					return ETHANOL;
				}
			}
		}

	}

}
