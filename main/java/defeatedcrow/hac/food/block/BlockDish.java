package defeatedcrow.hac.food.block;

import defeatedcrow.hac.core.base.DCSimpleBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDish extends DCSimpleBlock {

	protected static final AxisAlignedBB AABB_PLATE = new AxisAlignedBB(0D, 0D, 0D, 1D, 0.125D, 1D);

	public BlockDish(String s, int max) {
		super(Material.CLAY, s, max, false);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
		this.setSoundType(SoundType.METAL);
		this.setTickRandomly(false);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"white",
				"silver" };
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

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

}
