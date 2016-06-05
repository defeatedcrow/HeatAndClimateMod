package defeatedcrow.hac.main.block.build;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.placeable.ISidedTexture;
import defeatedcrow.hac.core.ClimateCore;

public class BlockStairsBase extends BlockStairs implements ISidedTexture {

	public final String TEX;
	public final boolean isGlass;

	public BlockStairsBase(IBlockState state, String name, boolean glass) {
		super(state);
		TEX = "dcs_climate:blocks/" + name;
		isGlass = glass;
		this.setCreativeTab(ClimateCore.climate);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
	}

	/** T, B, N, S, W, E */
	@Override
	public String getTexture(int meta, int side, boolean face) {
		return TEX;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 0;
	}

	/* 以下Glass用設定 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		BlockPos check = pos.offset(side.getOpposite());
		IBlockState state2 = world.getBlockState(check);

		if (state2.getMaterial() != Material.GLASS) {
			return true;
		}

		if (state.getBlock() == this) {
			return false;
		}

		return super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		if (isGlass)
			return false;

		return super.doesSideBlockRendering(state, world, pos, face);
	}

}
