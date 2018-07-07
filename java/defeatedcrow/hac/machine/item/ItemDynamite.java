package defeatedcrow.hac.machine.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCEntityItem;
import defeatedcrow.hac.main.entity.EntityDynamite;
import defeatedcrow.hac.main.entity.EntityDynamiteBlue;
import defeatedcrow.hac.main.util.EnumFixedName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDynamite extends DCEntityItem {

	public ItemDynamite() {
		super();
	}

	@Override
	public int getMaxMeta() {
		return 1;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 1);
		String s = "items/misc/dynamite_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"red",
				"blue"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		EntityDynamite ret = new EntityDynamite(world, x, y, z, player);
		if (i == 1) {
			ret = new EntityDynamiteBlue(world, x, y, z, player);
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(EnumFixedName.PLACEABLE_ENTITY.getLocalizedName());
	}

}
