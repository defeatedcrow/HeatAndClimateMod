package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import defeatedcrow.hac.core.base.DCSidedBlock;
import defeatedcrow.hac.main.MainInit;

public class BlockLogCont extends DCSidedBlock implements IFuelHandler {

	public BlockLogCont(Material m, String s, int max) {
		super(m, s, max, true);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"oak",
				"spruce",
				"birch",
				"jungle",
				"acacia",
				"dark",
				"charcoal" };
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 7;
		if (m > 6)
			m = 6;
		String b = "dcs_climate:blocks/cont/";
		switch (side) {
		case 0:
		case 1:
			return b + "logbox_s_" + getNameSuffix()[m];
		case 2:
		case 3:
			return face ? b + "logbox_f_" + getNameSuffix()[m] : b + "logbox_s_" + getNameSuffix()[m];
		case 4:
		case 5:
			return face ? b + "logbox_s_" + getNameSuffix()[m] : b + "logbox_f_" + getNameSuffix()[m];
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/";
		list.add(b + "logbox_f_oak");
		list.add(b + "logbox_f_birch");
		list.add(b + "logbox_f_spruce");
		list.add(b + "logbox_f_jungle");
		list.add(b + "logbox_f_dark");
		list.add(b + "logbox_f_acacia");
		list.add(b + "logbox_s_oak");
		list.add(b + "logbox_s_birch");
		list.add(b + "logbox_s_spruce");
		list.add(b + "logbox_s_jungle");
		list.add(b + "logbox_s_dark");
		list.add(b + "logbox_s_acacia");
		return list;
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel != null && fuel.getItem() == Item.getItemFromBlock(MainInit.logCont)) {
			int i = fuel.getItemDamage();
			if (i == 6)
				return 14400;
			else
				return 2700;
		}
		return 0;
	}

}
