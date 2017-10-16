package defeatedcrow.hac.machine.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCEntityItem;
import defeatedcrow.hac.machine.entity.EntityScooter;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemScooter extends DCEntityItem {

	public ItemScooter() {
		super();
	}

	@Override
	public int getMaxMeta() {
		return 3;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 1);
		String s = "items/tool/scooter_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"orange", "blue", "white", "black"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getItemDamage() & 3;
		EntityScooter ret = new EntityScooter(world, x, y, z, player);
		ret.setColor(i);

		NBTTagCompound tag = item.getTagCompound();
		if (tag != null) {
			ret.setNBTToEntity(tag);
		}

		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add("Placeable as an Entity");
	}

}
