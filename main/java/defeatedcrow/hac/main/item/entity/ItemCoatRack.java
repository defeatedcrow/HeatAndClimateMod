package defeatedcrow.hac.main.item.entity;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCEntityItem;
import defeatedcrow.hac.main.entity.EntityCoatRack;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCoatRack extends DCEntityItem {

	public ItemCoatRack() {
		super();
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = MathHelper.clamp(0, meta, 0);
		String s = "items/block/coat_rack_" + this.getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"black"
		};
		return s;
	}

	@Override
	public Entity getPlacementEntity(World world, EntityPlayer player, double x, double y, double z, ItemStack item) {
		int i = item.getMetadata();
		EntityCoatRack ret = new EntityCoatRack(world, x, y, z);
		float f = (float) MathHelper.floor((MathHelper
				.wrapDegrees(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
		ret.setLocationAndAngles(x, y, z, f, 0F);
		ret.setHeadRotation(new Rotations(30F, 0F, 0F));
		ItemMonsterPlacer.applyItemEntityDataToEntity(world, player, item, ret);
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(DCName.PLACEABLE_ENTITY.getLocalizedName());
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		tooltip.add(I18n.format("dcs.tip.coat_rack"));
	}

}
