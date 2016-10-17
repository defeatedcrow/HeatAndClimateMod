package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.core.base.DCSimpleBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockIronPlate extends DCSimpleBlock {

	protected static final AxisAlignedBB AABB_PLATE = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.0625D, 1D);

	public BlockIronPlate(String s, int max) {
		super(Material.CLAY, s, max, false);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"iron",
				"mesh" };
		return name;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_PLATE;
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
