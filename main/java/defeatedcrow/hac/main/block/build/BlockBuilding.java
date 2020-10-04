package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBuilding extends DCSimpleBlock implements ITexturePath {

	public BlockBuilding(Material m, String s) {
		super(m, s, 11, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(30.0F);
	}

	@Override
	public boolean canClimateUpdate(IBlockState state) {
		return false;
	}

	@Override
	public void setHarvestLevel(String toolClass, int level) {
		for (int i = 0; i < 16; i++) {
			super.setHarvestLevel("pickaxe", 0, this.getStateFromMeta(i));
		}
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		return 0;
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		int meta = DCState.getInt(state, DCState.TYPE16);
		if (meta == 5)
			return side != EnumFacing.UP;
		return true;
	}

	@Override
	public int getMaxMeta() {
		return 11;
	}

	private static String[] names = {
		"concrete",
		"mosaic_red",
		"mosaic_blue",
		"mosaic_yellow",
		"mosaic_black",
		"road",
		"plaster",
		"dirtbrick",
		"flintbrick",
		"flintbrick_gray",
		"flintbrick_red",
		"rattan" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta > getMaxMeta()) {
			meta = getMaxMeta();
		}
		String s = "blocks/build/build_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}
}
