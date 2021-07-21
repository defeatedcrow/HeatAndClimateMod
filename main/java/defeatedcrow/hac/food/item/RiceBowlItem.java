package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.EntityRiceBall;
import defeatedcrow.hac.food.entity.EntityRiceBallMiso;
import defeatedcrow.hac.food.entity.EntityRiceBallRoe;
import defeatedcrow.hac.food.entity.EntityRiceBallSeaweed;
import defeatedcrow.hac.food.entity.EntityRiceBowl;
import defeatedcrow.hac.food.entity.EntityRiceMushroom;
import defeatedcrow.hac.food.entity.EntityRiceSekihan;
import defeatedcrow.hac.food.entity.EntityRiceShiruko;
import defeatedcrow.hac.food.entity.EntityRiceTyadukeRoe;
import defeatedcrow.hac.food.entity.EntityRiceTyadukeSalmon;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RiceBowlItem extends FoodItemBase {

	public RiceBowlItem(boolean isWolfFood) {
		super(isWolfFood);
	}

	@Override
	public int getMaxMeta() {
		return 9;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 5);
		String s = "items/food/rice_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/rice_" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"boiled",
				"mushroom",
				"ball",
				"ball_seaweed",
				"ball_miso",
				"ball_roe",
				"sekihan",
				"shiruko",
				"tyaduke_salmon",
				"tyaduke_roe"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new EntityRiceBowl(world, x, y, z, player);
		switch (i) {
		case 1:
			ret = new EntityRiceMushroom(world, x, y, z, player);
			break;
		case 2:
			ret = new EntityRiceBall(world, x, y, z, player);
			break;
		case 3:
			ret = new EntityRiceBallSeaweed(world, x, y, z, player);
			break;
		case 4:
			ret = new EntityRiceBallMiso(world, x, y, z, player);
			break;
		case 5:
			ret = new EntityRiceBallRoe(world, x, y, z, player);
			break;
		case 6:
			ret = new EntityRiceSekihan(world, x, y, z, player);
			break;
		case 7:
			ret = new EntityRiceShiruko(world, x, y, z, player);
			ret.setIndividual(world.rand.nextInt(32));
			break;
		case 8:
			ret = new EntityRiceTyadukeSalmon(world, x, y, z, player);
			break;
		case 9:
			ret = new EntityRiceTyadukeRoe(world, x, y, z, player);
			break;
		}

		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		if (meta > 6)
			return 8;
		return meta == 0 ? 5 : 6;
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
