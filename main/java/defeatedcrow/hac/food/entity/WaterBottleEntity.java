package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.food.item.brewing.ItemRoseWaterBottle;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WaterBottleEntity extends LiquorBottleEntity {

	public WaterBottleEntity(World worldIn) {
		super(worldIn);
	}

	public WaterBottleEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public WaterBottleEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected ItemStack[] drops() {
		ItemStack ret = new ItemStack(FoodInit.roseWaterBottle, 1, meta);
		IDrinkCustomize drink = ret.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
		if (drink != null) {
			drink.setMilk(DrinkMilk.getFromId(getMilk()));
			drink.setSugar(DrinkSugar.getFromId(getSugar()));
			drink.setAging(getAging());
		}
		return new ItemStack[] { ret, ret.copy() };
	}

	@Override
	public String getName() {
		if (this.hasCustomName()) {
			return this.getCustomNameTag();
		} else {
			String s = EntityList.getEntityString(this);
			int i = this.getMeta();
			String type = ItemRoseWaterBottle.getTypeName(i);
			return I18n.format("entity." + s + "_" + type + ".name");
		}
	}
}
