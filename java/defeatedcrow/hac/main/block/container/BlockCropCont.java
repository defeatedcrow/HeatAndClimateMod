package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import defeatedcrow.hac.core.base.DCSimpleBlock;

public class BlockCropCont extends DCSimpleBlock {

	public BlockCropCont(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"apple",
				"potato",
				"carrot",
				"pumpkin",
				"melon",
				"cactus",
				"reed",
				"wart",
				"cocoa",
				"baked_apple",
				"baked_potato" };
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 10)
			m = 10;
		boolean f = m > 8;
		String b = "dcs_climate:blocks/cont/";
		if (f) {
			switch (side) {
			case 0:
				return b + "burntbox_t_" + getNameSuffix()[m];
			case 1:
				return b + "burntbox_b";
			case 2:
			case 3:
			case 4:
			case 5:
				return b + "burntbox_s";
			}
		} else {
			switch (side) {
			case 0:
				return b + "woodbox_t_" + getNameSuffix()[m];
			case 1:
				return b + "woodbox_b";
			case 2:
			case 3:
			case 4:
			case 5:
				return b + "woodbox_s";
			}
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/woodbox_";
		String b2 = "dcs_climate:blocks/cont/burntbox_";
		list.add(b + "t_apple");
		list.add(b + "t_potato");
		list.add(b + "t_carrot");
		list.add(b + "t_pumpkin");
		list.add(b + "t_melon");
		list.add(b + "t_cactus");
		list.add(b + "t_reed");
		list.add(b + "t_wart");
		list.add(b + "t_cocoa");
		list.add(b + "s");
		list.add(b + "b");
		list.add(b2 + "t_baked_apple");
		list.add(b2 + "t_baked_potato");
		list.add(b2 + "s");
		list.add(b2 + "b");
		return list;
	}

}
