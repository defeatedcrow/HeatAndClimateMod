package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.base.BlockDC;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMetalPillar extends BlockDC {

	/* 左右のチェック */
	// public static final PropertyBool NORTH = PropertyBool.create("north");
	// public static final PropertyBool EAST = PropertyBool.create("east");
	// public static final PropertyBool SOUTH = PropertyBool.create("south");
	// public static final PropertyBool WEST = PropertyBool.create("west");

	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis",
			EnumFacing.Axis.class);

	public BlockMetalPillar(String s) {
		super(Material.CLAY, s);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y)
		/*
		 * .withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false))
		 * .withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false))
		 */);
	}

	// additional state
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing face) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (block == this)
			return true;
		else if (block.isSideSolid(state, worldIn, pos, face))
			return true;
		return false;
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
		EnumFacing.Axis axis = state2.getValue(AXIS);
		if (axis != null) {
			if (axis == EnumFacing.Axis.X || axis == EnumFacing.Axis.Z)
				return side == EnumFacing.UP || side == EnumFacing.DOWN;
			else
				return true;
		}
		return true;
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
		return list;
	}

	// 設置・破壊処理
	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
			EnumFacing face = placer.getHorizontalFacing();
			state = state.withProperty(AXIS, EnumFacing.Axis.Y);
		} else if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			state = state.withProperty(AXIS, EnumFacing.Axis.Z);
		} else {
			state = state.withProperty(AXIS, EnumFacing.Axis.X);
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

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		// EnumFacing.Axis axis = state.getValue(AXIS);
		// if (axis != null) {
		// if (axis == EnumFacing.Axis.X) {
		// BlockPos n = pos.north();
		// BlockPos s = pos.south();
		// return state.withProperty(NORTH, this.canConnectTo(worldIn, n, EnumFacing.SOUTH)).withProperty(SOUTH,
		// this.canConnectTo(worldIn, s, EnumFacing.NORTH));
		// } else if (axis == EnumFacing.Axis.Z) {
		// BlockPos w = pos.west();
		// BlockPos e = pos.east();
		// return state.withProperty(WEST, this.canConnectTo(worldIn, w, EnumFacing.EAST)).withProperty(EAST,
		// this.canConnectTo(worldIn, e, EnumFacing.WEST));
		// }
		// }
		return state/*
					 * .withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false))
					 * .withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false))
					 */;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:

			switch (state.getValue(AXIS)) {
			case X:
				return state.withProperty(AXIS, EnumFacing.Axis.Z);
			case Z:
				return state.withProperty(AXIS, EnumFacing.Axis.X);
			default:
				return state;
			}

		default:
			return state;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				AXIS
				// NORTH,
				// EAST,
				// WEST,
				// SOUTH
		});
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing.Axis axis = EnumFacing.Axis.Y;
		int i = meta & 12;

		if (i == 4) {
			axis = EnumFacing.Axis.X;
		} else if (i == 8) {
			axis = EnumFacing.Axis.Z;
		}

		return this.getDefaultState().withProperty(AXIS, axis);
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		EnumFacing.Axis axis = state.getValue(AXIS);

		if (axis == EnumFacing.Axis.X) {
			i |= 4;
		} else if (axis == EnumFacing.Axis.Z) {
			i |= 8;
		}

		return i;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

}
