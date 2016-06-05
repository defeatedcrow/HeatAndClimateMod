package defeatedcrow.hac.main.block.build;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;

public class BlockGlassSelenite extends DCSimpleBlock implements ITexturePath {

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
				"half" };
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
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess world, BlockPos pos, EnumFacing side) {
		BlockPos check = pos.offset(side.getOpposite());
		IBlockState state = world.getBlockState(pos);
		IBlockState state2 = world.getBlockState(check);

		if (state2.getMaterial() != Material.GLASS) {
			return true;
		}

		if (state.getBlock() == this) {
			return false;
		}

		return super.shouldSideBeRendered(blockState, world, pos, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
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
	public String getTexPath(int meta, boolean isFull) {
		int i = meta & 15;
		switch (i) {
		case 0:
			return "dcs_climate:blocks/build/glass_selenite";
		case 1:
			return "dcs_climate:blocks/build/glass_light";
		case 2:
			return "dcs_climate:blocks/build/glass_half";
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

}
