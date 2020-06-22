package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.PastaBasilEntity;
import defeatedcrow.hac.food.entity.PastaBeefEntity;
import defeatedcrow.hac.food.entity.PastaCodEntity;
import defeatedcrow.hac.food.entity.PastaOilEntity;
import defeatedcrow.hac.food.entity.PastaPrawnEntity;
import defeatedcrow.hac.food.entity.PastaSquidEntity;
import defeatedcrow.hac.food.entity.PastaTomatoEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PastaItem extends FoodItemBase {

	public PastaItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 6;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 0);
		String s = "items/food/pasta_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = { "oil", "basil", "tomato", "prawn", "cod", "squid", "beef" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new PastaOilEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new PastaBasilEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new PastaTomatoEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new PastaPrawnEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new PastaCodEntity(world, x, y, z, player);
		}
		if (i == 5) {
			ret = new PastaSquidEntity(world, x, y, z, player);
		}
		if (i == 6) {
			ret = new PastaBeefEntity(world, x, y, z, player);
		}
		ret.setIndividual(world.rand.nextInt(32));
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		if (meta == 0) {
			return 11;
		}
		if (meta == 1) {
			return 12;
		}
		return 14;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.4F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
