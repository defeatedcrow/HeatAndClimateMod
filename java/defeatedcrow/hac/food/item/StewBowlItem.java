package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.StewBorschtEntity;
import defeatedcrow.hac.food.entity.StewCongeeEntity;
import defeatedcrow.hac.food.entity.StewEggEntity;
import defeatedcrow.hac.food.entity.StewLotusrootEntity;
import defeatedcrow.hac.food.entity.StewMushroomEntity;
import defeatedcrow.hac.food.entity.StewPumpukinEntity;
import defeatedcrow.hac.food.entity.StewPurpleEntity;
import defeatedcrow.hac.food.entity.StewSeaweedEntity;
import defeatedcrow.hac.food.entity.StewSquidEntity;
import defeatedcrow.hac.food.entity.StewTomatoEntity;
import defeatedcrow.hac.food.entity.StewVegiEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StewBowlItem extends FoodItemBase {

	public StewBowlItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 10;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp_int(meta, 0, getMaxMeta());
		String s = "items/food/bowl_stew_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/bowl_stew_" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"vegi",
				"egg",
				"congee",
				"tomato",
				"pumpkin",
				"borscht",
				"mushroom",
				"purple",
				"lotusroot",
				"squid",
				"seaweed"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new StewVegiEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new StewEggEntity(world, x, y, z, player);
		} else if (i == 2) {
			ret = new StewCongeeEntity(world, x, y, z, player);
		} else if (i == 3) {
			ret = new StewTomatoEntity(world, x, y, z, player);
		} else if (i == 4) {
			ret = new StewPumpukinEntity(world, x, y, z, player);
		} else if (i == 5) {
			ret = new StewBorschtEntity(world, x, y, z, player);
		} else if (i == 6) {
			ret = new StewMushroomEntity(world, x, y, z, player);
		} else if (i == 7) {
			ret = new StewPurpleEntity(world, x, y, z, player);
		} else if (i == 8) {
			ret = new StewLotusrootEntity(world, x, y, z, player);
		} else if (i == 9) {
			ret = new StewSquidEntity(world, x, y, z, player);
		} else if (i == 10) {
			ret = new StewSeaweedEntity(world, x, y, z, player);
		}
		ret.setIndividual(world.rand.nextInt(32));
		DCLogger.debugLog("individual " + ret.getIndividual());
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 8;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.75F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add("Placeable as an Entity");
	}

}
