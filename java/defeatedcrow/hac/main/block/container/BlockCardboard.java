package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import defeatedcrow.hac.core.base.DCSidedBlock;

public class BlockCardboard extends DCSidedBlock {

	public BlockCardboard(Material m, String s, int max) {
		super(m, s, max, true);
		this.setStepSound(soundTypeWood);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"beef",
				"pork",
				"chicken",
				"sheep",
				"egg",
				"wool" };
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 7;
		if (m > 5)
			m = 5;
		String b = "dcs_climate:blocks/cont/";
		switch (side) {
		case 0:
			return b + "cardboard_t";
		case 1:
			return b + "cardboard_b";
		case 2:
		case 3:
			return face ? b + "cardboard_f_" + getNameSuffix()[m] : b + "cardboard_s";
		case 4:
		case 5:
			return face ? b + "cardboard_s" : b + "cardboard_f_" + getNameSuffix()[m];
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/";
		list.add(b + "cardboard_f_beef");
		list.add(b + "cardboard_f_pork");
		list.add(b + "cardboard_f_chicken");
		list.add(b + "cardboard_f_sheep");
		list.add(b + "cardboard_f_egg");
		list.add(b + "cardboard_f_wool");
		list.add(b + "cardboard_s");
		list.add(b + "cardboard_t");
		list.add(b + "cardboard_b");
		return list;
	}

}
