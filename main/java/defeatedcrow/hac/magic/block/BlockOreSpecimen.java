package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOreSpecimen extends DCSimpleBlock implements ITexturePath {

	public BlockOreSpecimen(Material m, String s) {
		super(m, s, 15, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(30.0F);
	}

	@Override
	public int getMaxMeta() {
		return 15;
	}

	private static String[] names = {
			"diamond",
			"emerald",
			"redstone",
			"lapis",
			"chalcedony",
			"crystal",
			"sapphire",
			"malachite",
			"celestite",
			"elestial",
			"serpentine",
			"peridot",
			"bismuth",
			"schorl",
			"almandine",
			"rutile"
	};

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta > getMaxMeta()) {
			meta = getMaxMeta();
		}
		String s = "blocks/build/specimen_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
