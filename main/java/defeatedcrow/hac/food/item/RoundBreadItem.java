package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.BreadGingermanEntity;
import defeatedcrow.hac.food.entity.BreadGrahamCrackerEntity;
import defeatedcrow.hac.food.entity.BreadPancakeEntity;
import defeatedcrow.hac.food.entity.BreadPitaEntity;
import defeatedcrow.hac.food.entity.PizzaTomatoEntity;
import defeatedcrow.hac.food.entity.BreadRaisinEntity;
import defeatedcrow.hac.food.entity.BreadRoundCreamEntity;
import defeatedcrow.hac.food.entity.BreadRoundEntity;
import defeatedcrow.hac.food.entity.BreadSausageEntity;
import defeatedcrow.hac.food.entity.BreadSquareEntity;
import defeatedcrow.hac.food.entity.BreadToastEntity;
import defeatedcrow.hac.food.entity.BreadToastFrenchEntity;
import defeatedcrow.hac.food.entity.BreadToastGarlicEntity;
import defeatedcrow.hac.food.entity.BreadTortillaEntity;
import defeatedcrow.hac.food.entity.BreadWalnutEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RoundBreadItem extends FoodItemBase {

	public RoundBreadItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 27;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 27);
		String s = "items/food/" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
			"roundbread_raw",
			"roundbread_baked",
			"squarebread_raw",
			"squarebread_baked",
			"butter_toast_raw",
			"butter_toast_baked",
			"pizza_tomato_raw",
			"pizza_tomato_baked",
			"roundbread_cream",
			"french_toast",
			"garlic_toast_raw",
			"garlic_toast_baked",
			"pita_bread_raw",
			"pita_bread_baked",
			"pancake_raw",
			"pancake_baked",
			"walnut_bread_raw",
			"walnut_bread_baked",
			"gingerbreadman_raw",
			"gingerbreadman_baked",
			"tortilla_raw",
			"tortilla_baked",
			"raisin_bread_raw",
			"raisin_bread_baked",
			"sausage_bread_raw",
			"sausage_bread_baked",
			"graham_crackers_raw",
			"graham_crackers_baked" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new BreadRoundEntity(world, x, y, z, player);
		if (i == 2 || i == 3) {
			ret = new BreadSquareEntity(world, x, y, z, player);
			if (i == 2) {
				((BreadSquareEntity) ret).setMOLD(true);
			}
		}
		if (i == 4 || i == 5) {
			ret = new BreadToastEntity(world, x, y, z, player);
		}
		if (i == 6 || i == 7) {
			ret = new PizzaTomatoEntity(world, x, y, z, player);
		}
		if (i == 8) {
			ret = new BreadRoundCreamEntity(world, x, y, z, player);
		}
		if (i == 9) {
			ret = new BreadToastFrenchEntity(world, x, y, z, player);
		}
		if (i == 10 || i == 11) {
			ret = new BreadToastGarlicEntity(world, x, y, z, player);
		}
		if (i == 12 || i == 13) {
			ret = new BreadPitaEntity(world, x, y, z, player);
		}
		if (i == 14 || i == 15) {
			ret = new BreadPancakeEntity(world, x, y, z, player);
		}
		if (i == 16 || i == 17) {
			ret = new BreadWalnutEntity(world, x, y, z, player);
		}
		if (i == 18 || i == 19) {
			ret = new BreadGingermanEntity(world, x, y, z, player);
		}
		if (i == 20 || i == 21) {
			ret = new BreadTortillaEntity(world, x, y, z, player);
		}
		if (i == 22 || i == 23) {
			ret = new BreadRaisinEntity(world, x, y, z, player);
		}
		if (i == 24 || i == 25) {
			ret = new BreadSausageEntity(world, x, y, z, player);
		}
		if (i == 26 || i == 27) {
			ret = new BreadGrahamCrackerEntity(world, x, y, z, player);
		}

		if ((i & 1) == 0) {
			ret.setRAW(true);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		switch (meta) {
		case 1:
		case 3:
		case 13:
		case 21:
		case 27:
			return 6;
		case 11:
		case 15:
		case 19:
			return 7;
		case 5:
		case 10:
			return 6;
		case 7:
		case 25:
			return 10;
		case 8:
		case 9:
		case 17:
		case 23:
			return 8;
		}
		return 0;
	}

	@Override
	public float getSaturation(int meta) {
		if (meta == 8)
			return 0.5F;
		return (meta & 1) == 0 ? 0F : 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
