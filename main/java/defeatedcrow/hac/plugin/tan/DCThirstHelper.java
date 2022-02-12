package defeatedcrow.hac.plugin.tan;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.DrinkPotionType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fluids.Fluid;

public class DCThirstHelper {

	public static boolean enableThirst() {
		return toughasnails.api.config.SyncedConfig
				.getBooleanValue(toughasnails.api.config.GameplayOption.ENABLE_THIRST);
	}

	public static void onDrink(EntityPlayer player, Fluid f) {
		if (f != null) {
			toughasnails.thirst.ThirstHandler handler = (toughasnails.thirst.ThirstHandler) toughasnails.api.thirst.ThirstHelper
					.getThirstData(player);
			toughasnails.api.stat.capability.IThirst thirst = toughasnails.api.thirst.ThirstHelper
					.getThirstData(player);
			toughasnails.api.thirst.IDrink type = DCDrink.getType(f);
			DCLogger.infoLog("on thirst effect: " + type);
			thirst.addStats(type.getThirst(), type.getHydration());
			if (enableThirst() && player.world.rand.nextFloat() < type.getPoisonChance()) {
				player.addPotionEffect(new PotionEffect(toughasnails.api.TANPotions.thirst, 600));
			}
		}
	}

	public enum DCDrink implements toughasnails.api.thirst.IDrink {

		TEA(10, 0.5F, 0.0F),
		RAW(4, 0.1F, 0.5F),
		ETHANOL(6, 0.2F, 0.25F),
		RISK(2, 0.1F, 0.75F);

		final int thirst;
		final float hydration;
		final float chance;

		private DCDrink(int i, float f1, float f2) {
			thirst = i;
			hydration = f1;
			chance = f2;
		}

		@Override
		public int getThirst() {
			return thirst;
		}

		@Override
		public float getHydration() {
			return hydration;
		}

		@Override
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
					return TEA;
				if (f == MainInit.stock)
					return TEA;
				if (f == MainInit.tomatoJuice)
					return TEA;
				if (f == MainInit.milk)
					return TEA;
				if (f == MainInit.soyMilk)
					return TEA;
				if (f == MainInit.hotSpring)
					return RAW;
				if (f == MainInit.cream)
					return RAW;
				if (f == MainInit.mazai)
					return ETHANOL;
				if (f == MainInit.blackLiquor)
					return RISK;
				if (f == MainInit.oil)
					return RAW;
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

				if (ModuleConfig.food_advanced) {
					if (f == FoodInit.roseWater)
						return TEA;
					if (f == FoodInit.tonic)
						return TEA;
					if (f == FoodInit.lemon_squash)
						return TEA;
					if (f == FoodInit.cola)
						return TEA;
					if (f == FoodInit.beer)
						return TEA;
					if (f == FoodInit.wine)
						return TEA;
					if (f == FoodInit.dateWine)
						return TEA;
					if (f == FoodInit.sake)
						return TEA;
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
						return TEA;
					if (f == FoodInit.chorusLiquor)
						return ETHANOL;
				}

				Potion p = DrinkPotionType.getPotion(f);
				if (p != null && p.isBadEffect()) {
					return RISK;
				} else {
					return RAW;
				}
			}
		}

	}

}
