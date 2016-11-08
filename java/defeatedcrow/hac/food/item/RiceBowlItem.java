package defeatedcrow.hac.food.item;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.base.FoodItemBase;
import defeatedcrow.hac.food.entity.EntityRiceBowl;
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
		return 0;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp_int(0, meta, 1);
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
				"ball",
				"ball_baked" };
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		FoodEntityBase ret = new EntityRiceBowl(world, x, y, z, player);
		return ret;
	}

	@Override
	public int getFoodAmo(int meta) {
		return 8;
	}

	@Override
	public float getSaturation(int meta) {
		return 0.25F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add("Placeable as an Entity");
	}

}
