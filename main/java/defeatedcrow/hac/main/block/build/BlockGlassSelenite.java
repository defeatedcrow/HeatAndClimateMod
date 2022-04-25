package defeatedcrow.hac.main.block.build;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.placeable.ISidedTexture;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.core.base.INameSuffix;
import defeatedcrow.hac.core.base.ISidedRenderingBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlassSelenite extends BlockDC implements ITexturePath, ISidedTexture, INameSuffix,
		ISidedRenderingBlock {
	// Type上限
	public final int maxMeta;
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");

	public BlockGlassSelenite(String s, int max) {
		super(Material.GLASS, s);
		this.setHardness(0.5F);
		this.setResistance(15.0F);
		this.fullBlock = false;
		this.lightOpacity = 0;
		this.maxMeta = max;
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.TYPE16, 0).withProperty(NORTH, false).withProperty(NORTH, false).withProperty(SOUTH, false).withProperty(EAST, false).withProperty(WEST, false).withProperty(UP, false).withProperty(DOWN, false));
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"normal",
				"light",
				"half",
				"crystal"
		};
		return name;
	}

	/* Json登録とかクリエタブ登録とか */
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = super.getSubItemList();
		for (int i = 0; i < maxMeta + 1; i++) {
			list.add(new ItemStack(this, 1, i));
		}
		return list;
	}

	// 設置・破壊処理
	@Override
	public int damageDropped(IBlockState state) {
		int i = state.getValue(DCState.TYPE16);
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

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int i = meta & 15;
		switch (i) {
		case 0:
			return "dcs_climate:blocks/build/glass_selenite";
		case 1:
			return "dcs_climate:blocks/build/glass_light";
		case 2:
			return "dcs_climate:blocks/build/glass_half";
		case 3:
			return "dcs_climate:blocks/build/glass_crystal";
		default:
			return "dcs_climate:blocks/build/glass_half";
		}
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		return list;
	}

	/* 以下Glass用設定 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		BlockPos check = pos.offset(side);
		IBlockState state2 = world.getBlockState(check);
		if (state.getBlock() == this) {
			if (state2.getBlock() instanceof BlockBreakable || state2.getBlock() == this) {
				return false;
			}
			if (state2.getBlock() instanceof ISidedRenderingBlock) {
				return ((ISidedRenderingBlock) state2.getBlock()).isRendered(side, state2);
			}
			if (!state2.isSideSolid(world, check, side.getOpposite())) {
				return true;
			}
		}
		return super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
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
	public String getTexPath(int meta, boolean isFull) {
		int i = meta & 15;
		switch (i) {
		case 0:
			return "dcs_climate:blocks/build/glass_selenite";
		case 1:
			return "dcs_climate:blocks/build/glass_light";
		case 2:
			return "dcs_climate:blocks/build/glass_half";
		case 3:
			return "dcs_climate:blocks/build/glass_crystal";
		default:
			return "dcs_climate:blocks/build/glass_half";
		}
	}

	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = state.getBlock().getMetaFromState(state);
		return meta == 2 ? 255 : 0;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = state.getBlock().getMetaFromState(state);
		return meta == 1 ? 15 : 0;
	}

	@Override
	public boolean isRendered(EnumFacing face, IBlockState state) {
		return false;
	}

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

	/* state */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 15;
		IBlockState state = this.getDefaultState().withProperty(DCState.TYPE16, i);
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = state.getValue(DCState.TYPE16);
		if (i > maxMeta)
			i = maxMeta;
		return i;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = DCState.getInt(state, DCState.TYPE16);
		return state.withProperty(NORTH, !isSameBlock(world, pos.offset(EnumFacing.NORTH), meta)).withProperty(EAST, !isSameBlock(world, pos.offset(EnumFacing.EAST), meta)).withProperty(SOUTH, !isSameBlock(world, pos.offset(EnumFacing.SOUTH), meta)).withProperty(WEST, !isSameBlock(world, pos.offset(EnumFacing.WEST), meta)).withProperty(UP, !isSameBlock(world, pos.offset(EnumFacing.UP), meta)).withProperty(DOWN, !isSameBlock(world, pos.offset(EnumFacing.DOWN), meta));
	}

	private boolean isSameBlock(IBlockAccess world, BlockPos pos, int type) {
		IBlockState target = world.getBlockState(pos);
		return target != null && target.getBlock() == this && DCState.getInt(target, DCState.TYPE16) == type;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.TYPE16,
				NORTH,
				EAST,
				WEST,
				SOUTH,
				UP,
				DOWN
		});
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.CUSTOM;
	}
}
