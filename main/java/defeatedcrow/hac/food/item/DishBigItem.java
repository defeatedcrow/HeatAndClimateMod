package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.DishIkameshiEntity;
import defeatedcrow.hac.food.entity.DishMaboEntity;
import defeatedcrow.hac.food.entity.DishNachosEntity;
import defeatedcrow.hac.food.entity.DishOmericeEntity;
import defeatedcrow.hac.food.entity.DishTacoEntity;
import defeatedcrow.hac.food.entity.DishTacoriceEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DishBigItem extends FoodItemBase {

	public DishBigItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 5;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 0);
		String s = "items/food/dish_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = { "mabo", "omerice", "taco", "tacorice", "nachos", "ikameshi" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new DishMaboEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new DishOmericeEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new DishTacoEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new DishTacoriceEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new DishNachosEntity(world, x, y, z, player);
		}
		if (i == 5) {
			ret = new DishIkameshiEntity(world, x, y, z, player);
		}
		ret.setIndividual(world.rand.nextInt(32));
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		if (meta == 1 || meta == 4) {
			return 8;
		}
		return 14;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.25F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
