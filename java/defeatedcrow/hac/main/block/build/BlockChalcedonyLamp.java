package defeatedcrow.hac.main.block.build;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.DCSimpleBlock;

public class BlockChalcedonyLamp extends DCSimpleBlock {

	protected static final AxisAlignedBB AABB_FULL = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_SMALL = new AxisAlignedBB(0.125D, 0.125D, 0.125D, 0.875D, 0.875D, 0.875D);

	public BlockChalcedonyLamp(String s, int max) {
		super(Material.GLASS, s, max, false);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
		this.setLightLevel(1.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"cube_blue",
				"cube_red",
				"cube_white",
				"cube_wood",
				"glass_blue",
				"glass_red",
				"glass_white",
				"glass_wood",
				"desk_blue",
				"desk_red",
				"desk_white",
				"desk_wood",
				"pendant_blue",
				"pendant_red",
				"pendant_white",
				"pendant_wood" };
		return name;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return state.getValue(DCState.TYPE16) > 7 ? AABB_SMALL : AABB_FULL;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
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
