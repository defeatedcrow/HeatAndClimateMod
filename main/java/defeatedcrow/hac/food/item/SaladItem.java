package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.SaladGreenEntity;
import defeatedcrow.hac.food.entity.SaladLotusrootEntity;
import defeatedcrow.hac.food.entity.SaladPotatoEntity;
import defeatedcrow.hac.food.entity.SimmeredGomokuEntity;
import defeatedcrow.hac.food.entity.SimmeredSoyEntity;
import defeatedcrow.hac.food.entity.SimmeredSpinachEntity;
import defeatedcrow.hac.main.util.EnumFixedName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SaladItem extends FoodItemBase {

	public SaladItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 7;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 7);
		String s = "items/food/";
		if (i < 2)
			s += "salad_" + this.getNameSuffix()[i];
		else
			s += "simmered_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"green",
				"potato",
				"lotusroot",
				"soy",
				"gomoku",
				"spinach",
				"beans",
				"natto"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new SaladGreenEntity(world, x, y, z, player);
		if (i == 1) {
			ret = new SaladPotatoEntity(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new SaladLotusrootEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new SimmeredSoyEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new SimmeredGomokuEntity(world, x, y, z, player);
		}
		if (i == 5) {
			ret = new SimmeredSpinachEntity(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return meta == 1 || meta == 4 ? 12 : 8;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.25F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(EnumFixedName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
