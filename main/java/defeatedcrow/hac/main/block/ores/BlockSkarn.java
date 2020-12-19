package defeatedcrow.hac.main.block.ores;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockSkarn extends DCSimpleBlock implements ITexturePath {

	public BlockSkarn(String s) {
		super(Material.ROCK, s, 2, false);
		this.setTickRandomly(false);
		this.setHardness(2.0F);
		this.setResistance(15.0F);
	}

	@Override
	public boolean canClimateUpdate(IBlockState state) {
		return false;
	}

	private static String[] names = { "skarn", "hornfels", "greisen" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length) {
			meta = names.length - 1;
		}
		String s = "blocks/ores/gemblock_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}
}
