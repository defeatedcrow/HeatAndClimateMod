package defeatedcrow.hac.main.item.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.base.DCEntityItem;
import defeatedcrow.hac.main.entity.EntityBigCushion;
import defeatedcrow.hac.main.entity.EntityBigCushionBrown;
import defeatedcrow.hac.main.entity.EntitySmallCushionA;
import defeatedcrow.hac.main.entity.EntitySmallCushionB;
import defeatedcrow.hac.main.entity.EntitySmallCushionC;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCushionGray extends DCEntityItem {

	public ItemCushionGray() {
		super();
	}

	@Override
	public int getMaxMeta() {
		return 4;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 4);
		String s = "items/block/cushion_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"gray",
				"brown",
				"small_red",
				"small_blue",
				"small_straw"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		DCEntityBase ret = new EntityBigCushion(world, x, y, z, player);
		if (i == 1) {
			ret = new EntityBigCushionBrown(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new EntitySmallCushionA(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new EntitySmallCushionB(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new EntitySmallCushionC(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
