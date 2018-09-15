package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.WagashiButterEntity;
import defeatedcrow.hac.food.entity.WagashiIsobeEntity;
import defeatedcrow.hac.food.entity.WagashiKinakoEntity;
import defeatedcrow.hac.food.entity.WagashiKurimanjuEntity;
import defeatedcrow.hac.food.entity.WagashiKurumiEntity;
import defeatedcrow.hac.food.entity.WagashiNerikiriEntity;
import defeatedcrow.hac.food.entity.WagashiStrawberryEntity;
import defeatedcrow.hac.food.entity.WagashiZundaEntity;
import defeatedcrow.hac.main.util.EnumFixedName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WagashiItem extends FoodItemBase {

	public WagashiItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 7;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 7);
		String s = "items/food/wagashi_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/wagashi_" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"kinako",
				"isobe",
				"zunda",
				"butter",
				"strawberry",
				"kurumi",
				"kurimanju",
				"nerikiri"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new WagashiKinakoEntity(world, x, y, z, player);
		switch (i) {
		case 1:
			ret = new WagashiIsobeEntity(world, x, y, z, player);
			break;
		case 2:
			ret = new WagashiZundaEntity(world, x, y, z, player);
			break;
		case 3:
			ret = new WagashiButterEntity(world, x, y, z, player);
			break;
		case 4:
			ret = new WagashiStrawberryEntity(world, x, y, z, player);
			break;
		case 5:
			ret = new WagashiKurumiEntity(world, x, y, z, player);
			break;
		case 6:
			ret = new WagashiKurimanjuEntity(world, x, y, z, player);
			break;
		case 7:
			ret = new WagashiNerikiriEntity(world, x, y, z, player);
			break;
		}

		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 12;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.6F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(EnumFixedName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
