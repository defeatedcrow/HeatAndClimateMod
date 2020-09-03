package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.EnumMicrobeType;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemUnidentified extends DCItem {

	private final int maxMeta;

	private static String[] names = { "bacilli", "yeast", "mold" };

	public ItemUnidentified() {
		super();
		maxMeta = 2;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/brewing/unidentified_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	public static ItemStack setSpecies(IMicrobe sp) {
		ItemStack ret = new ItemStack(FoodInit.unidentified, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		if (sp != null) {
			if (sp.getType() == EnumMicrobeType.YEAST) {
				ret = new ItemStack(FoodInit.unidentified, 1, 1);
			} else if (sp.getType() == EnumMicrobeType.MOLD) {
				ret = new ItemStack(FoodInit.unidentified, 1, 2);
			}
			tag.setString("species", sp.getName());
		} else {
			tag.setString("species", "bacillus");
		}
		ret.setTagCompound(tag);
		return ret;
	}

	public static IMicrobe getSpecies(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("species")) {
				String name = tag.getString("species");
				IMicrobe sp = MainAPIManager.microbeRegister.getRecipe(name);
				if (sp != null) {
					return sp;
				}
			}
		}
		return null;
	}

}
