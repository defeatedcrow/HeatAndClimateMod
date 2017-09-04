package defeatedcrow.hac.food.item;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.CakeButterEntity;
import defeatedcrow.hac.food.entity.CakeChocolateEntity;
import defeatedcrow.hac.food.entity.CakeCoffeeEntity;
import defeatedcrow.hac.food.entity.CakeLemonEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CakeItem extends FoodItemBase {

	public CakeItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 4;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp_int(0, meta, 2);
		String s = "items/food/cake_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"butter_raw",
				"butter",
				"chocolate",
				"coffeejelly",
				"lemonjelly"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new CakeButterEntity(world, x, y, z, player);
		if (i == 0) {
			ret.setRAW(true);
		}
		if (i == 2) {
			ret = new CakeChocolateEntity(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new CakeCoffeeEntity(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new CakeLemonEntity(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return meta == 0 ? 2 : 10;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Placeable as an Entity");
	}

}
