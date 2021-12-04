package defeatedcrow.hac.main.block.plant;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCSimpleBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFlowerGarden extends DCSimpleBlock implements IPlantable {

	public BlockFlowerGarden(String s) {
		super(Material.PLANTS, s, 3, false);
		this.setTickRandomly(false);
		this.setSoundType(SoundType.PLANT);
		this.lightOpacity = 0;
	}

	@Override
	public boolean canClimateUpdate(IBlockState state) {
		return false;
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

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public int getMaxMeta() {
		return 3;
	}

	/**
	 * rose: バラ、忘れな草、ペチュニア、アイビー
	 * lily: 山百合、フウロソウ、キンポウゲ、イカリソウ
	 * orchid: コチョウラン、セッコク、ヤドリギ、シダ
	 * snapdragon: キンギョソウ、ネモフィラ、パンジー、ワイヤープランツ
	 */
	private static String[] names = {
			"rose",
			"lily",
			"orchid",
			"snapdragon"
	};

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return world.getBlockState(pos);
	}

}
