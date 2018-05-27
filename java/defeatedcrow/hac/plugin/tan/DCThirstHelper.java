package defeatedcrow.hac.plugin.tan;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.plugin.DrinkPotionType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fluids.Fluid;

public class DCThirstHelper {

	public static boolean enableThirst() {
		return toughasnails.api.config.SyncedConfig.getBooleanValue(
				toughasnails.api.config.GameplayOption.ENABLE_THIRST);
	}

	public static void onDrink(EntityPlayer player, Fluid f) {
		if (f != null) {
			toughasnails.thirst.ThirstHandler handler = (toughasnails.thirst.ThirstHandler) toughasnails.api.thirst.ThirstHelper.getThirstData(
					player);
			toughasnails.api.stat.capability.IThirst thirst = toughasnails.api.thirst.ThirstHelper.getThirstData(
					player);
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
				if (f == FoodInit.blackTea)
					return TEA;
				if (f == FoodInit.greenTea)
					return TEA;
				if (f == FoodInit.coffee)
					return TEA;
				if (f == FoodInit.lemon)
					return TEA;
				if (f == FoodInit.stock)
					return TEA;
				if (f == FoodInit.tomatoJuice)
					return TEA;
				if (f == FoodInit.hotSpring)
					return RAW;
				if (f == FoodInit.cream)
					return RAW;
				if (f == FoodInit.mazai)
					return ETHANOL;
				if (f == FoodInit.blackLiquor)
					return RISK;
				if (f == FoodInit.oil)
					return RAW;
				if (f == MachineInit.ethanol)
					return ETHANOL;
				if (f == MachineInit.ammonia)
					return RISK;
				if (f == MachineInit.nitricAcid)
					return RISK;
				if (f == MachineInit.sulfuricAcid)
					return RISK;
				if (f == MachineInit.nitrogen)
					return RISK;

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
