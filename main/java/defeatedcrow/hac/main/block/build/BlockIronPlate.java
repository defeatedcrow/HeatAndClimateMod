package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.base.INameSuffix;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
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

public class BlockIronPlate extends BlockDC implements INameSuffix {

	public static final PropertyBool SIDE = PropertyBool.create("side");
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 7);

	protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
	protected static final AxisAlignedBB AABB_TOP_HALF = new AxisAlignedBB(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);

	public final int maxMeta;

	public BlockIronPlate(String s, int max) {
		super(Material.CLAY, s);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SIDE, false).withProperty(TYPE, 0));
		this.fullBlock = false;
		this.setSoundType(SoundType.METAL);
		maxMeta = 1;
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = { "iron", "mesh" };
		return name;
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return state.getValue(SIDE) ? AABB_TOP_HALF : AABB_BOTTOM_HALF;
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
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand)
				.withProperty(SIDE, false);
		if (facing != EnumFacing.DOWN && (facing == EnumFacing.UP || hitY <= 0.5D))
			return state;
		else
			return state.withProperty(SIDE, true);
	}

	@Override
	public int damageDropped(IBlockState state) {
		int i = state.getValue(TYPE);
		if (i > maxMeta) {
			i = maxMeta;
		}
		return i;
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
		int i = meta & 7;
		boolean f = (meta & 8) != 0;
		IBlockState state = this.getDefaultState().withProperty(SIDE, f).withProperty(TYPE, i);
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = state.getValue(TYPE);
		if (i > maxMeta) {
			i = maxMeta;
		}
		boolean f = state.getValue(SIDE);

		return f ? i : i | 8;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { SIDE, TYPE });
	}

	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		for (int i = 0; i < maxMeta + 1; i++) {
			list.add(new ItemStack(this, 1, i));
		}
		return list;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

}
