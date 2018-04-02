package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.material.Material;

public class BlockLinoleum extends DCSimpleBlock implements ITexturePath {

	public BlockLinoleum(Material m, String s) {
		super(m, s, 15, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(30.0F);
	}

	@Override
	public int getMaxMeta() {
		return 15;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta > getMaxMeta()) {
			meta = getMaxMeta();
		}
		String s = "blocks/build/build_linoleum_" + meta;
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}
}
