package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockContainerDC;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMetalFenceBase extends BlockContainerDC {

	/* 左右のチェック */
	public static final PropertyBool UPPER = PropertyBool.create("upper");
	public static final PropertyBool UNDER = PropertyBool.create("under");

	public static final PropertyDirection BACK_FACING = PropertyDirection
			.create("back_facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool BACK_UPPER = PropertyBool.create("back_upper");
	public static final PropertyBool BACK_UNDER = PropertyBool.create("back_under");

	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.875D, 0.875D, 1.0D, 1.0D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.125D, 1.0D, 0.875D);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 0.125D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.875D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D);

	public static final AxisAlignedBB SOUTH_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB WEST_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
	public static final AxisAlignedBB NORTH_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
	public static final AxisAlignedBB EAST_BOX = new AxisAlignedBB(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockMetalFenceBase(String s, boolean b) {
		super(b ? Material.GLASS : Material.CLAY, s);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH)
				.withProperty(BACK_FACING, EnumFacing.SOUTH).withProperty(UPPER, false).withProperty(UNDER, false)
				.withProperty(BACK_UPPER, false).withProperty(BACK_UNDER, false));
	}

	// additional state
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing face) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (block == this)
			return DCState.getFace(state, DCState.FACING) == face;
		return false;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face != null) {
			boolean under = this.canConnectTo(worldIn, pos.down(), face);
			boolean upper = this.canConnectTo(worldIn, pos.up(), face);
			state = state.withProperty(UPPER, upper).withProperty(UNDER, under);

			BlockPos back = pos.offset(face.getOpposite());
			IBlockState b = worldIn.getBlockState(back);
			if (b != null && b.getBlock() == this) {
				EnumFacing b_face = DCState.getFace(b, DCState.FACING);
				boolean b_under = this.canConnectTo(worldIn, back.down(), b_face);
				boolean b_upper = this.canConnectTo(worldIn, back.up(), b_face);
				state = state.withProperty(BACK_FACING, b_face).withProperty(BACK_UPPER, b_upper)
						.withProperty(BACK_UNDER, b_under);
			} else {
				state = state.withProperty(BACK_FACING, face).withProperty(BACK_UPPER, false)
						.withProperty(BACK_UNDER, false);
			}

			return state;
		}
		return this.getDefaultState();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face == null)
			return super.withRotation(state, rot);
		switch (rot) {
		case CLOCKWISE_180:
			return state.withProperty(DCState.FACING, face.getOpposite()).withProperty(BACK_FACING, face.getOpposite());
		case COUNTERCLOCKWISE_90:
			return state.withProperty(DCState.FACING, face.rotateY()).withProperty(BACK_FACING, face.rotateY());
		case CLOCKWISE_90:
			return state.withProperty(DCState.FACING, face.rotateYCCW()).withProperty(BACK_FACING, face.rotateYCCW());
		default:
			return state;
		}
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face == null)
			return super.withMirror(state, mirrorIn);
		switch (mirrorIn) {
		case LEFT_RIGHT:
			return state.withProperty(DCState.FACING, face.getOpposite()).withProperty(BACK_FACING, face.getOpposite());
		case FRONT_BACK:
			return state.withProperty(DCState.FACING, face.getOpposite()).withProperty(BACK_FACING, face.getOpposite());
		default:
			return super.withMirror(state, mirrorIn);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
			DCState.FACING,
			BACK_FACING,
			UPPER,
			UNDER,
			BACK_UNDER,
			BACK_UPPER });
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		switch (face) {
		case NORTH:
			return NORTH_BOX;
		case SOUTH:
			return SOUTH_BOX;
		case WEST:
			return WEST_BOX;
		case EAST:
			return EAST_BOX;
		default:
			return SOUTH_BOX;
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		if (!isActualState) {
			state = state.getActualState(world, pos);
		}

		EnumFacing main = DCState.getFace(state, DCState.FACING);
		EnumFacing back = DCState.getFace(state, BACK_FACING);

		if (main == EnumFacing.EAST || back == EnumFacing.EAST) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
		}

		if (main == EnumFacing.WEST || back == EnumFacing.WEST) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
		}

		if (main == EnumFacing.SOUTH || back == EnumFacing.SOUTH) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
		}

		if (main == EnumFacing.NORTH || back == EnumFacing.NORTH) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
		}
	}

	@Override
	protected boolean canSilkHarvest() {
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

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		IBlockState state2 = worldIn.getBlockState(pos);
		EnumFacing face = DCState.getFace(state2, DCState.FACING);
		return face == side;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.UP) {
			IBlockState up = world.getBlockState(pos.up());
			return up == null || up.getBlock() != this;
		}
		if (side == EnumFacing.DOWN) {
			IBlockState ud = world.getBlockState(pos.down());
			return ud == null || ud.getBlock() != this;
		}
		return true;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	// 設置・破壊処理
	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		if (placer != null) {
			EnumFacing face = placer.getHorizontalFacing();
			state = state.withProperty(DCState.FACING, face);
		} else {
			if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
				facing = EnumFacing.NORTH;
			}
			state = state.withProperty(DCState.FACING, facing.getOpposite());
		}
		return state;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(state.getBlock());
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		IBlockState state = this.getDefaultState().withProperty(DCState.FACING, EnumFacing.getFront(5 - i));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 5 - state.getValue(DCState.FACING).getIndex();
		return i;
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
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

}
