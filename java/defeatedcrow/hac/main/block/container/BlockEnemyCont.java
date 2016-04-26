package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import defeatedcrow.hac.core.base.DCSimpleBlock;

public class BlockEnemyCont extends DCSimpleBlock {

	public BlockEnemyCont(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeStone);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"rotten",
				"bone",
				"spider",
				"ender",
				"powder" };
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 4)
			m = 4;
		String b = "dcs_climate:blocks/cont/";
		switch (side) {
		case 0:
			return b + "metalbox_t_" + getNameSuffix()[m];
		case 1:
			return b + "metalbox_b";
		case 2:
		case 3:
		case 4:
		case 5:
			return b + "metalbox_s";
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/metalbox_";
		list.add(b + "t_rotten");
		list.add(b + "t_bone");
		list.add(b + "t_spider");
		list.add(b + "t_ender");
		list.add(b + "t_powder");
		list.add(b + "s");
		list.add(b + "b");
		return list;
	}

}
