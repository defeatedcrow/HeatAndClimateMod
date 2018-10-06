package defeatedcrow.hac.main.api;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

public interface IColorableArmor {

	EnumDyeColor[] getColorableList();

	void setColorableList(EnumDyeColor... colors);

	public EnumDyeColor getCurrentColor(ItemStack armor);

}
