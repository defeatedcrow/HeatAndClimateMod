package defeatedcrow.hac.food.capability;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * ItemStack用のカスタム
 */
public class DrinkItemCustomizer implements IDrinkCustomize {

	public final ItemStack cont;

	public DrinkItemCustomizer(ItemStack item) {
		cont = item;
	}

	@Override
	public DrinkMilk getMilk() {
		if (DCUtil.isEmpty(cont)) {
			return DrinkMilk.NONE;
		}
		NBTTagCompound tag = cont.getTagCompound();
		if (tag != null && tag.hasKey(DrinkMilk.getTagKey())) {
			byte id = tag.getByte(DrinkMilk.getTagKey());
			DrinkMilk ret = DrinkMilk.getFromId(id);
			return ret;
		}
		return DrinkMilk.NONE;
	}

	@Override
	public DrinkSugar getSugar() {
		if (DCUtil.isEmpty(cont)) {
			return DrinkSugar.NONE;
		}
		NBTTagCompound tag = cont.getTagCompound();
		if (tag != null && tag.hasKey(DrinkSugar.getTagKey())) {
			byte id = tag.getByte(DrinkSugar.getTagKey());
			DrinkSugar ret = DrinkSugar.getFromId(id);
			return ret;
		}
		return DrinkSugar.NONE;
	}

	@Override
	public boolean setMilk(DrinkMilk milk) {
		NBTTagCompound tag = cont.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		tag.setByte(DrinkMilk.getTagKey(), (byte) milk.id);
		cont.setTagCompound(tag);
		return true;
	}

	@Override
	public boolean setSugar(DrinkSugar sugar) {
		NBTTagCompound tag = cont.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		tag.setByte(DrinkSugar.getTagKey(), (byte) sugar.id);
		cont.setTagCompound(tag);
		return true;
	}

}
