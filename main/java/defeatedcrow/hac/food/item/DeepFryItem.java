package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.FriedChickenEntity;
import defeatedcrow.hac.food.entity.FriedFalafelEntity;
import defeatedcrow.hac.food.entity.FriedFishEntity;
import defeatedcrow.hac.food.entity.FriedFishcakeEntity;
import defeatedcrow.hac.food.entity.FriedPorkEntity;
import defeatedcrow.hac.food.entity.FriedPorkGingerEntity;
import defeatedcrow.hac.food.entity.FriedPrawnEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DeepFryItem extends FoodItemBase {

	public DeepFryItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 6;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 6);
		String s = "items/food/frying_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = { "pork", "chicken", "fish", "falafel", "fishcake", "pork_ginger", "prawn" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new FriedPorkEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new FriedChickenEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new FriedFishEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new FriedFalafelEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new FriedFishcakeEntity(world, x, y, z, player);
		}
		if (i == 5) {
			ret = new FriedPorkGingerEntity(world, x, y, z, player);
		}
		if (i == 6) {
			ret = new FriedPrawnEntity(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 8;
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
