package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCFoodItem;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNoEntityFoods extends DCFoodItem {

	private final int maxMeta;

	private static String[] names = { "marshmallow", "date_and_nut", "toffee", "tofu", "smoked_salmon", "raisin" };

	public ItemNoEntityFoods() {
		super(false);
		maxMeta = 5;
	}

	@Override
	public int getMaxMeta() {
		return 5;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/food_" + names[meta];
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
		return 0.1F;
	}

	@Override
	public boolean addEffects(ItemStack stack, World worldIn, EntityLivingBase living) {
		if (!worldIn.isRemote && stack != null) {
			MainUtil.removeBadPotion(living);
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.AQUA.toString() + DCName.REMOVE_BAD_POTION.getLocalizedName());
	}

}
