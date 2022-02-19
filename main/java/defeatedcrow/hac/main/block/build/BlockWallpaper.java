package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.base.INameSuffix;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWallpaper extends BlockDC implements INameSuffix {

	/* 左右のチェック */
	public static final PropertyEnum<BlockWallpaper.Type> TYPE = PropertyEnum.<BlockWallpaper.Type>create("connect", BlockWallpaper.Type.class);

	public final int maxMeta;

	public BlockWallpaper(String s) {
		super(Material.WOOD, s);
		this.setHardness(0.5F);
		this.setResistance(3.0F);
		maxMeta = 7;
		this.setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FLAG, false)
				.withProperty(DCState.TYPE8, 0).withProperty(TYPE, Type.MIDDLE));
	}

	private static String[] names = {
			"plant",
			"slate",
			"ash",
			"presscake",
			"draff",
			"wood",
			"bran",
			"borax"
	};

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	public int getMaxMeta() {
		return maxMeta;
	}

	private boolean isSameBlock(IBlockState state, IBlockAccess world, BlockPos pos) {
		if (pos.getY() < 0 || pos.getY() > 255)
			return false;
		IBlockState check = world.getBlockState(pos);
		if (check == null)
			return false;
		return state.getBlock() == check.getBlock() && DCState.getInt(state, DCState.TYPE8) == DCState
				.getInt(check, DCState.TYPE8);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean bot2 = isSameBlock(state, world, pos.down(2));
		boolean bot1 = isSameBlock(state, world, pos.down());
		boolean up1 = isSameBlock(state, world, pos.up());
		boolean up2 = isSameBlock(state, world, pos.up(2));
		boolean flag = ((pos.getX() + pos.getZ() + pos.getY()) & 1) == 0;

		Type type = Type.MIDDLE;
		if (!bot2) {
			if (bot1)
				type = up1 ? Type.LOWER : Type.TOP;
			else
				type = Type.BOTTOM;
		} else if (!up2) {
			type = up1 ? Type.UPPER : Type.TOP;
		}

		return state.withProperty(DCState.FLAG, flag).withProperty(TYPE, type);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FLAG,
				DCState.TYPE8,
				TYPE
		});
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = super.getSubItemList();
		for (int i = 0; i < maxMeta + 1; i++) {
			list.add(new ItemStack(this, 1, i));
		}
		return list;
	}

	@Override
	public int damageDropped(IBlockState state) {
		int i = DCState.getInt(state, DCState.TYPE8);
		if (i < 0)
			i = 0;
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
		IBlockState state = this.getDefaultState().withProperty(DCState.FLAG, f).withProperty(DCState.TYPE8, i);
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = DCState.getInt(state, DCState.TYPE8);
		if (i < 0)
			i = 0;
		if (i > maxMeta) {
			i = maxMeta;
		}
		boolean f = DCState.getBool(state, DCState.FLAG);

		return f ? i : i | 8;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.SOLID;
	}

	public static enum Type implements IStringSerializable {
		BOTTOM,
		LOWER,
		MIDDLE,
		UPPER,
		TOP;

		@Override
		public String getName() {
			return this.toString().toLowerCase();
		}

	}

}
