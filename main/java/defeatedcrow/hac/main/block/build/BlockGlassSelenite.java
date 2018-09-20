package defeatedcrow.hac.main.block.build;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ISidedRenderingBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlassSelenite extends DCSimpleBlock implements ITexturePath, ISidedRenderingBlock {

	public BlockGlassSelenite(String s, int max) {
		super(Material.GLASS, s, max, false);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
		this.lightOpacity = 0;
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"normal",
				"light",
				"half",
				"crystal"
		};
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int i = meta & 15;
		switch (i) {
		case 0:
			return "dcs_climate:blocks/build/glass_selenite";
		case 1:
			return "dcs_climate:blocks/build/glass_light";
		case 2:
			return "dcs_climate:blocks/build/glass_half";
		case 3:
			return "dcs_climate:blocks/build/glass_crystal";
		default:
			return "dcs_climate:blocks/build/glass_half";
		}
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
			if (state2.getBlock() instanceof BlockBreakable) {
				return false;
			}

			if (state2.getBlock() instanceof ISidedRenderingBlock) {
				return ((ISidedRenderingBlock) state2.getBlock()).isRendered(side, state2);
			}

			if (!state2.isSideSolid(world, check, side.getOpposite())) {
				return true;
			}
		}

		return super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
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
	public String getTexPath(int meta, boolean isFull) {
		int i = meta & 15;
		switch (i) {
		case 0:
			return "dcs_climate:blocks/build/glass_selenite";
		case 1:
			return "dcs_climate:blocks/build/glass_light";
		case 2:
			return "dcs_climate:blocks/build/glass_half";
		case 3:
			return "dcs_climate:blocks/build/glass_crystal";
		default:
			return "dcs_climate:blocks/build/glass_half";
		}
	}

	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = state.getBlock().getMetaFromState(state);
		return meta == 2 ? 255 : 0;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = state.getBlock().getMetaFromState(state);
		return meta == 1 ? 15 : 0;
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
