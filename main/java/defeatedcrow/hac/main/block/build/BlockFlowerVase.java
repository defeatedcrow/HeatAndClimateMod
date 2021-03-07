package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IHumidityTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFlowerVase extends DCSimpleBlock implements ITexturePath, IHumidityTile {

	protected static final AxisAlignedBB AABB_FULL = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_MIDDLE = new AxisAlignedBB(0.125D, 0D, 0.125D, 0.875D, 1D, 0.875D);

	public BlockFlowerVase(Material m, String s) {
		super(m, s, 4, false);
		this.setTickRandomly(false);
		this.setHardness(1.0F);
		this.setResistance(30.0F);
	}

	@Override
	public boolean canClimateUpdate(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(DCState.TYPE16) == 0) {
			return AABB_FULL;
		} else {
			return AABB_MIDDLE;
		}
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
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

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return DCState.getInt(blockState, DCState.TYPE16) != 0;
	}

	// 偽装耕地

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		IBlockState plant = plantable.getPlant(world, pos.offset(direction));
		EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

		if (plantable instanceof BlockBush || plantType == EnumPlantType.Plains) {
			return true;
		}

		if (DCState.getInt(state, DCState.TYPE16) == 0) {
			return plantType != EnumPlantType.Water && plantType != EnumPlantType.Nether;
		}

		return false;
	}

	@Override
	public boolean isFertile(World world, BlockPos pos) {
		IBlockState plant = world.getBlockState(pos);
		if (DCState.getInt(plant, DCState.TYPE16) == 0) {
			return true;
		}

		return false;
	}

	@Override
	public int getMaxMeta() {
		return 4;
	}

	private static String[] names = { "planter", "white", "blue", "pink", "gray", "orange", "green" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta > getMaxMeta()) {
			meta = getMaxMeta();
		}
		String s = "blocks/build/vase_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public DCHumidity getHumidity(World world, BlockPos targrt, BlockPos source) {
		return DCHumidity.WET;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.MIDDLE_POLE;
	}
}
