package defeatedcrow.hac.food.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCFoodItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemLotusPetal extends DCFoodItem {

	private final int maxMeta;

	private static String[] names = {
			"lotus", "black"
	};

	public ItemLotusPetal() {
		super(false);
		maxMeta = 1;
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
		String s = "items/food/petal_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 2;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.25F;
	}

	@Override
	public boolean addEffects(ItemStack stack, World worldIn, EntityLivingBase living) {
		if (!worldIn.isRemote && stack != null && living != null) {
			int meta = stack.getMetadata();
			if (meta == 1) {
				EntityXPOrb orb = new EntityXPOrb(worldIn, living.posX, living.posY + 0.25D, living.posZ, 100);
				worldIn.spawnEntity(orb);
			} else {
				Potion por = MobEffects.REGENERATION;
				int amp = 0;
				int dur = 600;
				if (living.isPotionActive(MobEffects.REGENERATION)) {
					PotionEffect check = living.getActivePotionEffect(por);
					dur += check.getDuration();
				}
				living.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, dur, amp));
			}
			return true;
		}
		return false;
	}

}
