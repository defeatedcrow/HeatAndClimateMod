package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWallLamp extends Block {

	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.3125D, 0.25D, 0.5D, 0.6875D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.3125D, 0.25D, 0.0D, 0.6875D, 1.0D, 0.5D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.5D, 0.25D, 0.3125D, 1.0D, 1.0D, 0.6875D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.0D, 0.25D, 0.3125D, 0.5D, 1.0D, 0.6875D);

	// Type上限
	public final int maxMeta;

	public BlockWallLamp(String s) {
		super(Material.GLASS);
		this.setUnlocalizedName(s);
		this.setHardness(0.2F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.GLASS);
		this.setLightLevel(1.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH)
				.withProperty(DCState.TYPE4, 0));
		this.maxMeta = 3;
		this.fullBlock = false;
		this.lightOpacity = 0;
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (int i = 0; i < maxMeta + 1; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
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

	@Override
	public int damageDropped(IBlockState state) {
		int i = state.getValue(DCState.TYPE4);
		if (i > maxMeta) {
			i = maxMeta;
		}
		return i;
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		state = state.withProperty(DCState.FACING, placer.getHorizontalFacing().getOpposite());
		return state;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		if (i > maxMeta) {
			i = maxMeta;
		}
		int f = 5 - (meta >> 2);
		IBlockState state = this.getDefaultState().withProperty(DCState.TYPE4, i);
		state = state.withProperty(DCState.FACING, EnumFacing.getFront(f));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		int f = 0;

		i = state.getValue(DCState.TYPE4);
		if (i > maxMeta) {
			i = maxMeta;
		}

		f = 5 - state.getValue(DCState.FACING).getIndex();
		f = f << 2;
		return i + f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FACING,
				DCState.TYPE4
		});
	}

}
