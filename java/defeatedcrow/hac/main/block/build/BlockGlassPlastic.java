package defeatedcrow.hac.main.block.build;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ISidedRenderingBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlassPlastic extends DCSimpleBlock implements ITexturePath, ISidedRenderingBlock {

	public BlockGlassPlastic(String s, int max) {
		super(Material.GLASS, s, max, false);
		this.setHardness(3.0F);
		this.setResistance(30.0F);
		this.fullBlock = false;
		this.lightOpacity = 0;
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"white",
				"orange",
				"magenta",
				"light_blue",
				"yellow",
				"lime",
				"pink",
				"gray",
				"silver",
				"cyan",
				"purple",
				"blue",
				"brown",
				"green",
				"red",
				"black"
		};
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int i = meta & 15;
		String s = "dcs_climate:blocks/build/synthetic_" + getNameSuffix()[i];
		return s;
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		return list;
	}

	/* 以下Glass用設定 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		BlockPos check = pos.offset(side);
		IBlockState state2 = world.getBlockState(check);

		if (state.getBlock() == this) {
			if (state2.getBlock() instanceof BlockBreakable)
				return false;

			if (state2.getBlock() instanceof ISidedRenderingBlock)
				return ((ISidedRenderingBlock) state2.getBlock()).isRendered(side, state2);

			if (!state2.isSideSolid(world, check, side.getOpposite()))
				return true;
		}

		return super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
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

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = meta & 15;
		String s = "blocks/build/synthetic_" + getNameSuffix()[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	// climate
	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		return false;
	}

	@Override
	public boolean isRendered(EnumFacing face, IBlockState state) {
		return false;
	}

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

}
