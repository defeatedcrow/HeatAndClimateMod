package defeatedcrow.hac.main.item.misc;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSilkworm extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"silk_eggcard",
			"silk_worm",
			"silk_cocoon",
			"silk_moth"
	};

	public ItemSilkworm(int max) {
		super();
		maxMeta = max;
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
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		if (!DCUtil.isEmpty(stack) && stack.getItemDamage() == 1) {
			String s = "1st";
			if (stack.hasTagCompound()) {
				int stage = stack.getTagCompound().getInteger("RearingStage") / 10;
				if (stage == 0) {
					s = "1st";
				} else if (stage == 1) {
					s = "2nd";
				} else if (stage == 2) {
					s = "3rd";
				} else {
					s = (stage + 1) + "th";
				}
			}
			tooltip.add(s + " stage");
		}
	}

}
