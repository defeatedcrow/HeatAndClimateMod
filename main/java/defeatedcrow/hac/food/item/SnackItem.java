package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.MealCroquettePotatoEntity;
import defeatedcrow.hac.food.entity.MealCroquettePumpkinEntity;
import defeatedcrow.hac.food.entity.MealFalafelSandEntity;
import defeatedcrow.hac.food.entity.MealFishAndChipsEntity;
import defeatedcrow.hac.food.entity.MealFriedPotatoEntity;
import defeatedcrow.hac.food.entity.MealShawarmaEntity;
import defeatedcrow.hac.main.util.EnumFixedName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SnackItem extends FoodItemBase {

	public SnackItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 5;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 5);
		String s = "items/food/meal_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"fried_potato",
				"fish_and_chips",
				"croquette_potato",
				"croquette_pumpkin",
				"shawarma",
				"falafel_sandwich"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new MealFriedPotatoEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new MealFishAndChipsEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new MealCroquettePotatoEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new MealCroquettePumpkinEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new MealShawarmaEntity(world, x, y, z, player);
		}
		if (i == 5) {
			ret = new MealFalafelSandEntity(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 12;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.4F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(EnumFixedName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
