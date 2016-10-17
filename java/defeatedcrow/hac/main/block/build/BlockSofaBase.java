package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSofaBase extends Block {

	/* 左右のチェック */
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");

	protected static final AxisAlignedBB AABB_SMALL = new AxisAlignedBB(0.125D, 0.125D, 0.125D, 0.875D, 0.75D, 0.875D);
	protected static final AxisAlignedBB AABB_FULL = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
	protected static final AxisAlignedBB AABB_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.5D, 0.75D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 0.25D, 1.0D, 1.0D);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 0.25D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.75D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

	private boolean isSmallAABB = false;

	public BlockSofaBase(String s) {
		super(Material.CLAY);
		this.setCreativeTab(ClimateCore.climate);
		this.setUnlocalizedName(s);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH)
				.withProperty(LEFT, false).withProperty(RIGHT, false));
	}

	public BlockSofaBase setSmallAABB() {
		isSmallAABB = true;
		return this;
	}

	// additional state
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
		return block == this;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face != null) {
			BlockPos left = pos.offset(face.rotateYCCW());
			BlockPos right = pos.offset(face.rotateY());
			return state.withProperty(LEFT, Boolean.valueOf(this.canConnectTo(worldIn, left))).withProperty(RIGHT,
					Boolean.valueOf(this.canConnectTo(worldIn, right)));
		}
		return state.withProperty(LEFT, false).withProperty(RIGHT, false);
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face == null) {
			return super.withRotation(state, rot);
		}
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
		if (face == null) {
			return super.withMirror(state, mirrorIn);
		}
		switch (mirrorIn) {
		case LEFT_RIGHT:
			return state.withProperty(DCState.FACING, face.getOpposite()).withProperty(LEFT, state.getValue(RIGHT))
					.withProperty(RIGHT, state.getValue(LEFT));
		case FRONT_BACK:
			return state.withProperty(DCState.FACING, face.getOpposite()).withProperty(RIGHT, state.getValue(LEFT))
					.withProperty(LEFT, state.getValue(RIGHT));
		default:
			return super.withMirror(state, mirrorIn);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FACING,
				LEFT,
				RIGHT });
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return isSmallAABB ? AABB_SMALL : AABB_FULL;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
		state = state.getActualState(worldIn, pos);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_HALF);
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face != null) {
			switch (face) {
			case EAST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
				break;
			case NORTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
				break;
			case SOUTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
				break;
			case WEST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
				break;
			default:
				break;
			}
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
	public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
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
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
	}

	// 設置・破壊処理
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		state = state.withProperty(DCState.FACING, placer.getHorizontalFacing());
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

}
