package defeatedcrow.hac.main.block.build;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockDC;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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

public class BlockWindow extends BlockDC {

	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0.875D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.125D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.875D, 0D, 0D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0D, 0D, 0.125D, 1D, 1D);

	// Type上限
	public final int maxMeta;

	public BlockWindow(String s) {
		super(Material.WOOD, s);
		this.setHardness(0.2F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH)
				.withProperty(DCState.POWERED, false)
				.withProperty(DCState.FLAG, false));
		this.maxMeta = 1;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		switch (face) {
		case EAST:
			return AABB_EAST;
		case NORTH:
			return AABB_NORTH;
		case SOUTH:
			return AABB_SOUTH;
		case WEST:
			return AABB_WEST;
		default:
			return AABB_NORTH;

		}
	}

	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		if (!isActualState) {
			state = state.getActualState(worldIn, pos);
		}

		EnumFacing enumfacing = DCState.getFace(state, DCState.FACING);
		boolean pow = DCState.getBool(state, DCState.POWERED);

		if (!pow) {
			switch (enumfacing) {
			case EAST:
			default:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EAST);
				break;
			case SOUTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SOUTH);
				break;
			case WEST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WEST);
				break;
			case NORTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NORTH);
				break;
			}
		}
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		IBlockState target = world.getBlockState(pos);
		if (target != null && target.getBlock() == this) {
			boolean p = DCState.getBool(state, DCState.POWERED);
			target = target.withProperty(DCState.POWERED, !p);
			world.setBlockState(pos, target);
			world.playSound((EntityPlayer) null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos
					.getZ() + 0.5D, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundCategory.BLOCKS, 0.5F, world.rand
							.nextFloat() * 0.1F + 0.9F);
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
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean flag = DCState.getBool(state, DCState.POWERED);
		return flag ? 0 : 1;
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
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		return list;
	}

	@Override
	public int damageDropped(IBlockState state) {
		boolean b = DCState.getBool(state, DCState.FLAG);
		return b ? 1 : 0;
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
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FACING,
				DCState.FLAG,
				DCState.POWERED
		});
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
