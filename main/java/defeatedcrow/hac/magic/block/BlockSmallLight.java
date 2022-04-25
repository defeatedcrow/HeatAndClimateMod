package defeatedcrow.hac.magic.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.base.EnumStateType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSmallLight extends BlockDC {
	protected static final AxisAlignedBB AABB_D = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.0675D, 0.75D);
	protected static final AxisAlignedBB AABB_U = new AxisAlignedBB(0.25D, 0.9325D, 0.25D, 0.75D, 1.0D, 0.75D);
	protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.0675D);
	protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.25D, 0.25D, 0.9325D, 0.75D, 0.75D, 1.0D);
	protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.9325D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
	protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.0675D, 0.75D, 0.75D);
	// Type上限
	public final int maxMeta;

	public BlockSmallLight(String s) {
		super(Material.GLASS, s);
		this.setHardness(0.1F);
		this.setResistance(1.0F);
		this.setLightLevel(1.0F);
		this.maxMeta = 0;
		this.fullBlock = false;
		this.lightOpacity = 0;
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.SIDE, EnumSide.DOWN).withProperty(DCState.POWERED, false));
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
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing.getOpposite()));
		return state;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumSide face = DCState.getSide(state, DCState.SIDE);
		switch (face) {
		case EAST:
			return AABB_E;
		case NORTH:
			return AABB_N;
		case SOUTH:
			return AABB_S;
		case WEST:
			return AABB_W;
		case DOWN:
			return AABB_D;
		case UP:
			return AABB_U;
		default:
			return AABB_N;
		}
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int m = meta & 7;
		IBlockState state = this.getDefaultState().withProperty(DCState.SIDE, EnumSide.fromIndex(m)).withProperty(DCState.POWERED, Boolean
				.valueOf((meta & 8) > 0));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int f = 0;
		int i = 0;
		f = state.getValue(DCState.SIDE).index;
		i = state.getValue(DCState.POWERED) ? 8 : 0;
		return i + f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.SIDE,
				DCState.POWERED
		});
	}

	@Override
	public IProperty[] ignoreTarget() {
		return new IProperty[] {
				DCState.POWERED
		};
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.SIDE;
	}
}
