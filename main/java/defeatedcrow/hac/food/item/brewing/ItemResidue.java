package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.api.item.IAnimalFood;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.ItemStack;

public class ItemResidue extends DCItem implements IAnimalFood {

	private final int maxMeta;

	private static String[] names = {
			"bsg",
			"sake",
			"wine",
			"silage",
			"ex_yeast",
			"ex_peptone",
			"ex_whey"
	};

	public ItemResidue() {
		super();
		maxMeta = 6;
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
		String s = "items/food/brewing/residue_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public boolean isTargetAnimal(EntityAnimal entity, ItemStack item) {
		if (entity == null || DCUtil.isEmpty(item))
			return false;

		if (item.getItemDamage() == 3) {
			return !(entity instanceof EntityTameable);
		} else {
			return false;
		}
	}

}
