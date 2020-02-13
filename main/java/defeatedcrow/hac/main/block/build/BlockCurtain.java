package defeatedcrow.hac.main.block.build;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockDC;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCurtain extends BlockDC {

	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0.875D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.125D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.875D, 0D, 0D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0D, 0D, 0.125D, 1D, 1D);

	protected static final AxisAlignedBB AABB_NORTH_L = new AxisAlignedBB(0.75D, 0D, 0.75D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SOUTH_L = new AxisAlignedBB(0D, 0D, 0D, 0.25D, 1D, 0.25D);
	protected static final AxisAlignedBB AABB_WEST_L = new AxisAlignedBB(0.75D, 0D, 0.75D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_EAST_L = new AxisAlignedBB(0D, 0D, 0D, 0.25D, 1D, 0.25D);

	protected static final AxisAlignedBB AABB_NORTH_R = new AxisAlignedBB(0D, 0D, 0.75D, 0.25D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SOUTH_R = new AxisAlignedBB(0.75D, 0D, 0D, 1D, 1D, 0.25D);
	protected static final AxisAlignedBB AABB_WEST_R = new AxisAlignedBB(0.75D, 0D, 0D, 1D, 1D, 0.25D);
	protected static final AxisAlignedBB AABB_EAST_R = new AxisAlignedBB(0D, 0D, 0.75D, 0.25D, 1D, 1D);

	// Type上限
	public final int maxMeta;

	public BlockCurtain(String s) {
		super(Material.CLOTH, s);
		this.setHardness(0.2F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.CLOTH);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH)
				.withProperty(DCState.POWERED, false).withProperty(DCState.FLAG, false)
				.withProperty(DCState.DOUBLE, false));
		this.maxMeta = 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		boolean b1 = DCState.getBool(state, DCState.FLAG);
		boolean b2 = DCState.getBool(state, DCState.POWERED);
		switch (face) {
		case EAST:
			return b2 ? (!b1 ? AABB_EAST_R : AABB_EAST_L) : AABB_EAST;
		case NORTH:
			return b2 ? (b1 ? AABB_NORTH_R : AABB_NORTH_L) : AABB_NORTH;
		case SOUTH:
			return b2 ? (b1 ? AABB_SOUTH_R : AABB_SOUTH_L) : AABB_SOUTH;
		case WEST:
			return b2 ? (!b1 ? AABB_WEST_R : AABB_WEST_L) : AABB_WEST;
		default:
			return b2 ? (!b1 ? AABB_NORTH_R : AABB_NORTH_L) : AABB_NORTH;

		}
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		for (int i = 2; i > -3; i--) {
			IBlockState st = world.getBlockState(pos.up(i));
			if (st.getBlock() == this) {
				if (player != null && player.isSneaking()) {
					boolean p = DCState.getBool(state, DCState.FLAG);
					st = st.withProperty(DCState.FLAG, !p);
					world.setBlockState(pos.up(i), st);
					world.playSound((EntityPlayer) null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos
							.getZ() + 0.5D, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.BLOCKS, 0.5F, world.rand
									.nextFloat() * 0.1F + 0.9F);
				} else {
					boolean p = DCState.getBool(state, DCState.POWERED);
					st = st.withProperty(DCState.POWERED, !p);
					world.setBlockState(pos.up(i), st);
					world.playSound((EntityPlayer) null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos
							.getZ() + 0.5D, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.BLOCKS, 0.5F, world.rand
									.nextFloat() * 0.1F + 0.9F);
				}
			}
		}
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
	public int getLightOpacity(IBlockState state) {
		boolean flag = DCState.getBool(state, DCState.POWERED);
		return flag ? 0 : 255;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
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
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		if (placer == null)
			return state;
		state = state.withProperty(DCState.FACING, placer.getHorizontalFacing().getOpposite());
		return state;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		boolean b2 = i > 1;
		boolean b1 = ((i & 1) != 0);
		int f = 5 - (meta >> 2);
		IBlockState state = this.getDefaultState().withProperty(DCState.FLAG, b1).withProperty(DCState.POWERED, b2);
		state = state.withProperty(DCState.FACING, EnumFacing.getFront(f));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		boolean b1 = DCState.getBool(state, DCState.FLAG);
		boolean b2 = DCState.getBool(state, DCState.POWERED);
		int i = b1 ? 1 : 0;
		int f = 0;

		if (b2) {
			i += 2;
		}

		f = 5 - state.getValue(DCState.FACING).getIndex();
		f = f << 2;
		return i + f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean b = world.getBlockState(pos.up()).getBlock() == this;
		state = state.withProperty(DCState.DOUBLE, b);
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
			DCState.FACING,
			DCState.DOUBLE,
			DCState.FLAG,
			DCState.POWERED });
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
