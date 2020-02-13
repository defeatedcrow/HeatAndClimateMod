package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
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

public class BlockMetalLadder extends BlockDC {

	/* 左右のチェック */
	public static final PropertyBool CLAMP = PropertyBool.create("clamp");
	public static final PropertyBool UPPER = PropertyBool.create("upper");

	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockMetalLadder(String s) {
		super(Material.CLAY, s);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH)
				.withProperty(CLAMP, false).withProperty(UPPER, false));
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.world.isRemote && player != null) {
			ItemStack held = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(held) && held.getItem() == Item.getItemFromBlock(this)) {
				BlockPos target = null;
				for (int i = 1; i < 16; i++) {
					BlockPos check = pos.up(i);
					if (hitY < 0.5F) {
						check = pos.down(i);
					}
					if (world.getBlockState(check).getBlock().isReplaceable(world, check)) {
						target = check;
						break;
					}
				}
				if (target != null) {
					EnumFacing face = DCState.getFace(state, DCState.FACING);
					IBlockState set = this.getDefaultState().withProperty(DCState.FACING, face);
					world.setBlockState(target, set, 3);
					DCUtil.reduceStackSize(held, 1);
					return true;

				}
			}
		}
		return true;
	}

	// additional state
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing face) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (block.isSideSolid(state, worldIn, pos, face.getOpposite()))
			return true;
		return false;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face != null) {
			BlockPos up = pos.offset(face);
			return state.withProperty(CLAMP, Boolean.valueOf(this.canConnectTo(worldIn, up, face)))
					.withProperty(UPPER, worldIn.isAirBlock(pos.up()));
		}
		return state.withProperty(CLAMP, false).withProperty(UPPER, false);
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face == null)
			return super.withRotation(state, rot);
		switch (rot) {
		case CLOCKWISE_180:
			return state.withProperty(DCState.FACING, face.getOpposite());
		case COUNTERCLOCKWISE_90:
			return state.withProperty(DCState.FACING, face.rotateY());
		case CLOCKWISE_90:
			return state.withProperty(DCState.FACING, face.rotateYCCW());
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
			return state.withProperty(DCState.FACING, face.getOpposite());
		case FRONT_BACK:
			return state.withProperty(DCState.FACING, face.getOpposite());
		default:
			return super.withMirror(state, mirrorIn);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DCState.FACING, CLAMP, UPPER });
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		switch (face) {
		case NORTH:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		case EAST:
			return EAST_AABB;
		default:
			return NORTH_AABB;
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
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		return face == side;
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

	@Override
	public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
		return true;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
