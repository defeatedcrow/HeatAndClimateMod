package defeatedcrow.hac.main.item.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.base.DCEntityItem;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_A;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_B;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_C;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_D;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_E;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_F;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_G;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_H;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_I;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDesktopAccessories extends DCEntityItem {

	public ItemDesktopAccessories() {
		super();
	}

	@Override
	public int getMaxMeta() {
		return 8;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 8);
		String s = "items/block/desktop_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"books",
				"books2",
				"note",
				"pen",
				"organizer",
				"keyboard",
				"laptop",
				"phone",
				"pen_tablet"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		DCEntityBase ret = new EntityDesktopAccessories_A(world, x, y, z, player);
		if (i == 1) {
			ret = new EntityDesktopAccessories_B(world, x, y, z, player);
		}
		if (i == 2) {
			ret = new EntityDesktopAccessories_C(world, x, y, z, player);
		}
		if (i == 3) {
			ret = new EntityDesktopAccessories_D(world, x, y, z, player);
		}
		if (i == 4) {
			ret = new EntityDesktopAccessories_E(world, x, y, z, player);
		}
		if (i == 5) {
			ret = new EntityDesktopAccessories_F(world, x, y, z, player);
		}
		if (i == 6) {
			ret = new EntityDesktopAccessories_G(world, x, y, z, player);
		}
		if (i == 7) {
			ret = new EntityDesktopAccessories_H(world, x, y, z, player);
		}
		if (i == 8) {
			ret = new EntityDesktopAccessories_I(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
		tooltip.add(TextFormatting.AQUA.toString() + DCName.COLOR_CHANGE_TARGET
				.getLocalizedName());
	}

}
