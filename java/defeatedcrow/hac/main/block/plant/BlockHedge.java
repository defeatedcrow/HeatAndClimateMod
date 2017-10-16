package defeatedcrow.hac.main.block.plant;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
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

public class BlockHedge extends BlockFence {

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

	public BlockHedge(String s) {
		super(Material.LEAVES, MapColor.FOLIAGE);
		this.setUnlocalizedName(s);
		this.setHardness(0.2F);
		this.setResistance(10.0F);
		this.setSoundType(SoundType.PLANT);
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

	@Override
	protected boolean canSilkHarvest() {
		return false;
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

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
}
