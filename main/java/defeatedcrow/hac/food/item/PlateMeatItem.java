package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.entity.PlateBeefEntity;
import defeatedcrow.hac.food.entity.PlateBigGarlicEntity;
import defeatedcrow.hac.food.entity.PlateChickenEntity;
import defeatedcrow.hac.food.entity.PlateFishEntity;
import defeatedcrow.hac.food.entity.PlatePorkEntity;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlateMeatItem extends FoodItemBase {

	public PlateMeatItem(boolean isWolfFood) {
		super(isWolfFood);
		this.setContainerItem(FoodInit.steakplate);
	}

	@Override
	public int getMaxMeta() {
		return 9;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 1);
		String s = "items/food/plate_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"beef_raw",
				"beef_baked",
				"pork_raw",
				"pork_baked",
				"chicken_raw",
				"chicken_baked",
				"fish_raw",
				"fish_baked",
				"big_garlic_raw",
				"big_garlic_baked"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new PlateBeefEntity(world, x, y, z, player);
		if (i == 2 || i == 3) {
			ret = new PlatePorkEntity(world, x, y, z, player);
		}
		if (i == 4 || i == 5) {
			ret = new PlateChickenEntity(world, x, y, z, player);
		}
		if (i == 6 || i == 7) {
			ret = new PlateFishEntity(world, x, y, z, player);
		}
		if (i == 8 || i == 9) {
			ret = new PlateBigGarlicEntity(world, x, y, z, player);
			ret.setIndividual(world.rand.nextInt(32));
		}

		if ((i & 1) == 0) {
			ret.setRAW(true);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		switch (meta) {
		case 0:
		case 2:
		case 4:
		case 6:
			return 0;
		case 1:
			return 10;
		case 3:
			return 10;
		case 5:
			return 10;
		case 7:
			return 12;
		case 9:
			return 16;
		}
		return 0;
	}

	@Override
	public float getSaturation(int meta) {
		return meta == 9 ? 1.0F : (meta & 1) == 0 ? 0F : 0.6F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
