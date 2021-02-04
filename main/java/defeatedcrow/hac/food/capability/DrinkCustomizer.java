package defeatedcrow.hac.food.capability;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Entity用のカスタム
 */
public class DrinkCustomizer implements IDrinkCustomize {

	public byte milkID = 0;
	public byte sugarID = 0;
	public byte agingLevel = 0;

	public DrinkCustomizer() {}

	@Override
	public DrinkMilk getMilk() {
		return DrinkMilk.getFromId(milkID);
	}

	@Override
	public DrinkSugar getSugar() {
		return DrinkSugar.getFromId(sugarID);
	}

	@Override
	public int getAgingLevel() {
		return agingLevel;
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

	@Override
	public boolean setAging(int l) {
		if (l > 100)
			l = 100;
		agingLevel = (byte) l;
		return true;
	}

	public DrinkCustomizer readFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey(DrinkMilk.getTagKey())) {
			byte milk = nbt.getByte(DrinkMilk.getTagKey());
			milkID = milk;
		} else {
			milkID = 0;
		}
		if (nbt.hasKey(DrinkSugar.getTagKey())) {
			byte sugar = nbt.getByte(DrinkSugar.getTagKey());
			sugarID = sugar;
		} else {
			sugarID = 0;
		}
		if (nbt.hasKey(DrinkItemCustomizer.AGING_KEY)) {
			byte sugar = nbt.getByte(DrinkItemCustomizer.AGING_KEY);
			agingLevel = sugar;
		} else {
			agingLevel = 0;
		}
		return this;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		if (milkID == 0) {
			nbt.setByte(DrinkMilk.getTagKey(), milkID);
		} else {
			nbt.removeTag(DrinkMilk.getTagKey());
		}
		if (sugarID == 0) {
			nbt.setByte(DrinkSugar.getTagKey(), sugarID);
		} else {
			nbt.removeTag(DrinkSugar.getTagKey());
		}
		if (agingLevel == 0) {
			nbt.setByte(DrinkItemCustomizer.AGING_KEY, agingLevel);
		} else {
			nbt.removeTag(DrinkItemCustomizer.AGING_KEY);
		}

		return nbt;
	}
}
