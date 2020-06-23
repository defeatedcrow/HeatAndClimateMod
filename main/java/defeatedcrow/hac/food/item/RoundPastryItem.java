package defeatedcrow.hac.food.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.QuichePotatoEntity;
import defeatedcrow.hac.food.entity.QuicheSpinachEntity;
import defeatedcrow.hac.food.entity.TartAppleEntity;
import defeatedcrow.hac.food.entity.TartChocolateEntity;
import defeatedcrow.hac.food.entity.TartCrostataEntity;
import defeatedcrow.hac.food.entity.TartCustardEntity;
import defeatedcrow.hac.food.entity.TartLemonEntity;
import defeatedcrow.hac.food.entity.TartLiverEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RoundPastryItem extends FoodItemBase {

	public RoundPastryItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 15;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(meta, 0, getMaxMeta());
		String s = "items/food/tart_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
			"apple_raw",
			"apple_baked",
			"lemon_raw",
			"lemon_baked",
			"spinach_raw",
			"spinach_baked",
			"potato_raw",
			"potato_baked",
			"crostata_raw",
			"crostata_baked",
			"chocolate_raw",
			"chocolate_baked",
			"custard_raw",
			"custard_baked",
			"liver_raw",
			"liver_baked" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = null;
		switch (i) {
		case 0:
		case 1:
			ret = new TartAppleEntity(world, x, y, z, player);
			break;
		case 2:
		case 3:
			ret = new TartLemonEntity(world, x, y, z, player);
			break;
		case 4:
		case 5:
			ret = new QuicheSpinachEntity(world, x, y, z, player);
			break;
		case 6:
		case 7:
			ret = new QuichePotatoEntity(world, x, y, z, player);
			break;
		case 8:
		case 9:
			ret = new TartCrostataEntity(world, x, y, z, player);
			break;
		case 10:
		case 11:
			ret = new TartChocolateEntity(world, x, y, z, player);
			break;
		case 12:
		case 13:
			ret = new TartCustardEntity(world, x, y, z, player);
			break;
		case 14:
		case 15:
			ret = new TartLiverEntity(world, x, y, z, player);
			break;
		default:
			ret = new TartAppleEntity(world, x, y, z, player);
		}

		if (ret != null && (i & 1) == 0) {
			ret.setRAW(true);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		switch (meta) {
		case 1:
			return 10;
		case 3:
			return 10;
		case 5:
			return 12;
		case 7:
			return 12;
		case 9:
			return 10;
		case 11:
		case 13:
			return 12;
		case 15:
			return 14;
		default:
			return 0;
		}
	}

	@Override
	public float getSaturation(int meta) {
		return (meta & 1) == 0 ? 0F : 0.8F;
	}

	@Override
	public List<PotionEffect> getPotionEffect(int meta) {
		List<PotionEffect> ret = new ArrayList<PotionEffect>();
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
