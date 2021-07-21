package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.CurryBeansEntity;
import defeatedcrow.hac.food.entity.CurryButterChickenEntity;
import defeatedcrow.hac.food.entity.CurryFishEntity;
import defeatedcrow.hac.food.entity.CurryGreenEntity;
import defeatedcrow.hac.food.entity.CurryRiceEntity;
import defeatedcrow.hac.food.entity.CurrySpinachEntity;
import defeatedcrow.hac.food.entity.CurryVegiEntity;
import defeatedcrow.hac.food.entity.CurryVindalooEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CurryItem extends FoodItemBase {

	public CurryItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 7;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 0);
		String s = "items/food/curry_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"vegi",
				"beans",
				"fish",
				"spinach",
				"butter_chicken",
				"vindaloo",
				"green",
				"rice"

		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new CurryVegiEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new CurryBeansEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new CurryFishEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new CurrySpinachEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new CurryButterChickenEntity(world, x, y, z, player);
		}
		if (i == 5) {
			ret = new CurryVindalooEntity(world, x, y, z, player);
		}
		if (i == 6) {
			ret = new CurryGreenEntity(world, x, y, z, player);
		}
		if (i == 7) {
			ret = new CurryRiceEntity(world, x, y, z, player);
		}
		ret.setIndividual(world.rand.nextInt(32));
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 12 + meta;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
