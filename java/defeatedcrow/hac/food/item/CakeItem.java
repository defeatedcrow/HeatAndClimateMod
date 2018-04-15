package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.CakeBerryEntity;
import defeatedcrow.hac.food.entity.CakeButterEntity;
import defeatedcrow.hac.food.entity.CakeChocolateEntity;
import defeatedcrow.hac.food.entity.CakeCocotteEntity;
import defeatedcrow.hac.food.entity.CakeCoffeeEntity;
import defeatedcrow.hac.food.entity.CakeCreamEntity;
import defeatedcrow.hac.food.entity.CakeKuzuEntity;
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
		return 9;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(meta, 0, 9);
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
				"lemonjelly",
				"bakedcream",
				"berryjelly",
				"kuzujelly",
				"egg_cocotte_raw",
				"egg_cocotte_baked"
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
		if (i == 5) {
			ret = new CakeCreamEntity(world, x, y, z, player);
		}
		if (i == 6) {
			ret = new CakeBerryEntity(world, x, y, z, player);
		}
		if (i == 7) {
			ret = new CakeKuzuEntity(world, x, y, z, player);
		}
		if (i == 8) {
			ret = new CakeCocotteEntity(world, x, y, z, player);
			ret.setRAW(true);
		}
		if (i == 9) {
			ret = new CakeCocotteEntity(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return meta == 0 || meta == 8 ? 2 : 10;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add("Placeable as an Entity");
	}

}
