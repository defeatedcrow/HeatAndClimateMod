package defeatedcrow.hac.main.item.food;

import defeatedcrow.hac.api.item.IAnimalFood;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.ItemStack;

public class ItemAnimalFood extends DCItem implements IAnimalFood {

	private final int maxMeta;

	private static String[] names = {
			"compound",
			"hay",
			"straw"
	};

	public ItemAnimalFood(int max) {
		super();
		maxMeta = max;
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
		String s = "items/food/animalfeed_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public boolean isTargetAnimal(EntityAnimal entity, ItemStack item) {
		if (entity == null || DCUtil.isEmpty(item))
			return false;

		if (item.getItemDamage() == 0) {
			return !(entity instanceof EntityTameable);
		} else {
			return entity instanceof AbstractHorse || entity instanceof EntityCow || entity instanceof EntitySheep;
		}
	}

}
