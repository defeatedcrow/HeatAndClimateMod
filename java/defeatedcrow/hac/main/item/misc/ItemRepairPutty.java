package defeatedcrow.hac.main.item.misc;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRepairPutty extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"putty",
			"abrasive"
	};

	public ItemRepairPutty() {
		super();
		maxMeta = 1;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (stack != null) {
			int m = stack.getItemDamage();
			if (m == 0) {
				tooltip.add(I18n.translateToLocal("dcs.tip.repair_putty.description"));
			} else if (m == 1) {
				tooltip.add(I18n.translateToLocal("dcs.tip.abrasive.description"));
			}
		}
	}

}
