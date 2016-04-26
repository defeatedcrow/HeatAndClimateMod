package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.placeable.ISidedTexture;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.INameSuffix;

// doubleなし
public abstract class BlockSlabBase extends Block implements ISidedTexture, INameSuffix {

	public static final PropertyBool SIDE = PropertyBool.create("side");
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 7);

	// Type上限
	public final int maxMeta;
	public final boolean isGlass;

	protected Random rand = new Random();
	public static final String CL_TEX = "dcs_climate:blocks/clear";

	public BlockSlabBase(Material m, String s, int max, boolean glass) {
		super(m);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		this.setUnlocalizedName(s);
		this.setCreativeTab(ClimateCore.climate);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SIDE, false).withProperty(TYPE, 0));
		if (max < 0 || max > 7)
			max = 7;
		this.maxMeta = max;
		this.isGlass = glass;
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);

		if (state.getBlock() == this) {
			if (state.getValue(SIDE)) {
				this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
			} else {
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
			}
		}
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list,
			Entity collidingEntity) {
		this.setBlockBoundsBasedOnState(worldIn, pos);
		super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockAccess world, BlockPos pos, EnumFacing face) {
		if (isGlass)
			return false;
		boolean top = world.getBlockState(pos).getValue(SIDE);
		return (top && face == EnumFacing.DOWN) || (!top && face == EnumFacing.UP);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(SIDE, false);
		if (facing != EnumFacing.DOWN && (facing == EnumFacing.UP || hitY <= 0.5D)) {
			return state;
		} else {
			return state.withProperty(SIDE, true);
		}
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		BlockPos check = pos.offset(side.getOpposite());
		IBlockState state = worldIn.getBlockState(pos);
		IBlockState state2 = worldIn.getBlockState(check);

		if (isGlass) {
			if (state2.getBlock().getMaterial() != Material.glass) {
				return true;
			}

			if (state2.getBlock() == this) {
				if (state.getBlock() == this && state.getValue(SIDE) == state2.getValue(SIDE))
					return false;
			}

			if (state2.getBlock().isFullBlock())
				return false;
		}

		if (side != EnumFacing.UP && side != EnumFacing.DOWN && !super.shouldSideBeRendered(worldIn, pos, side)) {
			return false;
		}
		// additional logic breaks doesSideBlockRendering and is no longer useful.
		return super.shouldSideBeRendered(worldIn, pos, side);
	}

	@Override
	public int getDamageValue(World worldIn, BlockPos pos) {
		return super.getDamageValue(worldIn, pos) & 7;
	}

	@Override
	public int damageDropped(IBlockState state) {
		int i = state.getValue(TYPE);
		if (i > maxMeta)
			i = maxMeta;
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
		if (i > maxMeta)
			i = maxMeta;
		boolean f = state.getValue(SIDE);

		return f ? i : i | 8;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {
				SIDE,
				TYPE });
	}

	/** T, B, N, S, W, E */
	@Override
	public String getTexture(int meta, int side, boolean face) {
		return CL_TEX;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	/* Json登録とかクリエタブ登録とか */
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (int i = 0; i < maxMeta + 1; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
	}
}
