package defeatedcrow.hac.main.block.plant;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHedge extends BlockDC {

	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	protected static final AxisAlignedBB[] BOUNDING_BOXES_DC = new AxisAlignedBB[] {
			new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D),
			new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D),
			new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.875D, 1.0D, 1.0D),
			new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 0.875D),
			new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.875D, 1.0D, 0.875D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D),
			new AxisAlignedBB(0.125D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D),
			new AxisAlignedBB(0.125D, 0.0D, 0.125D, 1.0D, 1.0D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D),
			new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 1.0D),
			new AxisAlignedBB(0.125D, 0.0D, 0.0D, 1.0D, 1.0D, 0.875D),
			new AxisAlignedBB(0.125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.875D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)
	};
	public static final AxisAlignedBB PILLAR_AABB2 = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.5D, 0.875D);
	public static final AxisAlignedBB SOUTH_AABB2 = new AxisAlignedBB(0.125D, 0.0D, 0.875D, 0.875D, 1.5D, 1.0D);
	public static final AxisAlignedBB WEST_AABB2 = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.125D, 1.5D, 0.875D);
	public static final AxisAlignedBB NORTH_AABB2 = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.5D, 0.125D);
	public static final AxisAlignedBB EAST_AABB2 = new AxisAlignedBB(0.875D, 0.0D, 0.125D, 1.0D, 1.5D, 0.875D);

	public final EnumSeason season;

	public BlockHedge(String s, EnumSeason seasonIn) {
		super(Material.LEAVES, s);
		season = seasonIn;
		this.setHardness(0.2F);
		this.setResistance(10.0F);
		this.setSoundType(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(SOUTH, false)
				.withProperty(WEST, false).withProperty(EAST, false).withProperty(DCState.FLAG, false));
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = this.getActualState(state, source, pos);
		return BOUNDING_BOXES_DC[getBoundingBoxIdx(state)];
	}

	private static int getBoundingBoxIdx(IBlockState state) {
		int i = 0;

		if (state.getValue(NORTH).booleanValue()) {
			i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
		}
		if (state.getValue(EAST).booleanValue()) {
			i |= 1 << EnumFacing.EAST.getHorizontalIndex();
		}
		if (state.getValue(SOUTH).booleanValue()) {
			i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
		}
		if (state.getValue(WEST).booleanValue()) {
			i |= 1 << EnumFacing.WEST.getHorizontalIndex();
		}

		return i;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean b) {
		if (!b) {
			state = state.getActualState(worldIn, pos);
		}
		addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_AABB2);

		if (state.getValue(NORTH).booleanValue()) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB2);
		}

		if (state.getValue(EAST).booleanValue()) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB2);
		}

		if (state.getValue(SOUTH).booleanValue()) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB2);
		}

		if (state.getValue(WEST).booleanValue()) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB2);
		}
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		// client
		if (ClimateCore.proxy.getClientWorld() != null) {
			World cw = ClimateCore.proxy.getClientWorld();
			EnumSeason s = DCTimeHelper.getSeasonEnum(cw);
			if (s == season) {
				state = state.withProperty(DCState.FLAG, true);
			} else {
				state = state.withProperty(DCState.FLAG, false);
			}
		} else if (ClimateCore.proxy.getWorld() != null) {
			World sw = ClimateCore.proxy.getWorld();
			EnumSeason s = DCTimeHelper.getSeasonEnum(sw);
			if (s == season) {
				state = state.withProperty(DCState.FLAG, true);
			} else {
				state = state.withProperty(DCState.FLAG, false);
			}
		}

		return state.withProperty(NORTH, canConnectTo(world, pos.north()))
				.withProperty(EAST, canConnectTo(world, pos.east()))
				.withProperty(SOUTH, canConnectTo(world, pos.south()))
				.withProperty(WEST, canConnectTo(world, pos.west()));
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
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
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (DCUtil.machCreativeTab(tab, getCreativeTabToDisplayOn()))
			list.add(new ItemStack(this, 1, 0));
	}

	// 設置・破壊処理
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
		IBlockState state = this.getDefaultState();
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				NORTH,
				EAST,
				WEST,
				SOUTH,
				DCState.FLAG
		});
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	public boolean canConnectTo(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		return block == this;
	}

	@Override
	public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		return canConnectTo(world, pos.offset(facing));
	}

	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
	}
}
