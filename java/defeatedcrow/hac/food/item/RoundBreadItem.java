package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.PizzaTomatoEntity;
import defeatedcrow.hac.food.entity.RoundBreadCreamEntity;
import defeatedcrow.hac.food.entity.RoundBreadEntity;
import defeatedcrow.hac.food.entity.SquareBreadEntity;
import defeatedcrow.hac.food.entity.ToastBreadEntity;
import net.minecraft.client.util.ITooltipFlag;
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
		return 8;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 1);
		String s = "items/food/" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"roundbread_raw", "roundbread_baked", "squarebread_raw", "squarebread_baked", "butter_toast_raw",
				"butter_toast_baked", "pizza_tomato_raw", "pizza_tomato_baked", "roundbread_cream"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new RoundBreadEntity(world, x, y, z, player);
		if (i == 2 || i == 3) {
			ret = new SquareBreadEntity(world, x, y, z, player);
			if (i == 2) {
				((SquareBreadEntity) ret).setMOLD(true);
			}
		}
		if (i == 4 || i == 5) {
			ret = new ToastBreadEntity(world, x, y, z, player);
		}
		if (i == 6 || i == 7) {
			ret = new PizzaTomatoEntity(world, x, y, z, player);
		}
		if (i == 8) {
			ret = new RoundBreadCreamEntity(world, x, y, z, player);
		}

		if ((i & 1) == 0) {
			ret.setRAW(true);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		if (meta == 7)
			return 10;
		if (meta == 8)
			return 8;
		return (meta & 1) == 0 ? 0 : 6;
	}

	@Override
	public float getSaturation(int meta) {
		if (meta == 8)
			return 0.5F;
		return (meta & 1) == 0 ? 0F : 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add("Placeable as an Entity");
	}

}
