package defeatedcrow.hac.food.capability;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Entity用のカスタム
 */
public class DrinkCustomizer implements IDrinkCustomize {

	public byte milkID = 0;
	public byte sugarID = 0;

	public DrinkCustomizer() {
	}

	@Override
	public DrinkMilk getMilk() {
		return DrinkMilk.getFromId(milkID);
	}

	@Override
	public DrinkSugar getSugar() {
		return DrinkSugar.getFromId(sugarID);
	}

	@Override
	public boolean setMilk(DrinkMilk milk) {
		milkID = (byte) milk.id;
		return true;
	}

	@Override
	public boolean setSugar(DrinkSugar sugar) {
		sugarID = (byte) sugar.id;
		return true;
	}

	public DrinkCustomizer readFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey(DrinkMilk.getTagKey())) {
			byte milk = nbt.getByte(DrinkMilk.getTagKey());
			milkID = milk;
		}
		if (nbt.hasKey(DrinkSugar.getTagKey())) {
			byte sugar = nbt.getByte(DrinkSugar.getTagKey());
			sugarID = sugar;
		}
		return this;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setByte(DrinkMilk.getTagKey(), milkID);
		nbt.setByte(DrinkSugar.getTagKey(), sugarID);
		return nbt;
	}

}
