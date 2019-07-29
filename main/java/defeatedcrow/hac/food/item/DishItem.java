package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.DishBruschettaEntity;
import defeatedcrow.hac.food.entity.DishCapreseEntity;
import defeatedcrow.hac.food.entity.DishMisoniEntity;
import defeatedcrow.hac.food.entity.DishSalmonEntity;
import defeatedcrow.hac.food.entity.DishSashimiEntity;
import defeatedcrow.hac.food.entity.DishSushiEntity;
import defeatedcrow.hac.food.entity.DishTamagoEntity;
import defeatedcrow.hac.food.entity.DishYakkoEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DishItem extends FoodItemBase {

	public DishItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 7;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 7);
		String s = "items/food/dish_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
			"caprese",
			"bruschetta",
			"salmon_cheese",
			"sushi",
			"sashimi",
			"misoni",
			"tamagoyaki",
			"hiyayakko" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new DishCapreseEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new DishBruschettaEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new DishSalmonEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new DishSushiEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new DishSashimiEntity(world, x, y, z, player);
			ret.setIndividual(world.rand.nextInt(32));
		}
		if (i == 5) {
			ret = new DishMisoniEntity(world, x, y, z, player);
		}
		if (i == 6) {
			ret = new DishTamagoEntity(world, x, y, z, player);
		}
		if (i == 7) {
			ret = new DishYakkoEntity(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		if (meta == 3 || meta == 4 | meta == 5) {
			return 10;
		}
		return 6;
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
