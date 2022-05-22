package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.block.BlockExclusiveDC;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDisplayShopCase extends BlockExclusiveDC {

	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");

	public BlockDisplayShopCase(Material m, String s) {
		super(m, s, 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH).withProperty(NORTH, false).withProperty(NORTH, false)
				.withProperty(SOUTH, false).withProperty(EAST, false).withProperty(WEST, false).withProperty(UP, false).withProperty(DOWN, false));
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
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		state = state.withProperty(DCState.FACING, placer.getHorizontalFacing().getOpposite());
		return state;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.world.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileDisplayShopCase) {
				if (((TileDisplayShopCase) tile).getOwner() != null) {
					if (((TileDisplayShopCase) tile).isOwnerOrOP(player) && player.isSneaking()) {
						player.openGui(ClimateMain.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
					} else {
						player.openGui(ClimateMain.instance, 2, world, pos.getX(), pos.getY(), pos.getZ());
					}
				} else {
					player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
				}
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDisplayShopCase();
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileDisplayShelf) {
			return ((TileDisplayShelf) tile).calcRedstone();
		}
		return 0;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}

	// 破壊時
	// @Override
	// public void breakBlock(World world, BlockPos pos, IBlockState state) {
	// TileEntity tile = world.getTileEntity(pos);
	// if (tile instanceof TileDisplayShopCase) {
	// InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
	// world.updateComparatorOutputLevel(pos, state.getBlock());
	// }
	// world.removeTileEntity(pos);
	// }
	//
	// @Override
	// public int quantityDropped(Random random) {
	// return 1;
	// }
	//
	// @Override
	// public Item getItemDropped(IBlockState state, Random rand, int fortune) {
	// return Item.getItemFromBlock(this);
	// }

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int f = 5 - (meta >> 2);
		IBlockState state = this.getDefaultState().withProperty(DCState.FACING, EnumFacing.getFront(f));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int f = 0;
		f = 5 - state.getValue(DCState.FACING).getIndex();
		f = f << 2;
		return f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state.withProperty(NORTH, !isSameBlock(world, pos.offset(EnumFacing.NORTH))).withProperty(EAST, !isSameBlock(world, pos.offset(EnumFacing.EAST)))
				.withProperty(SOUTH, !isSameBlock(world, pos.offset(EnumFacing.SOUTH))).withProperty(WEST, !isSameBlock(world, pos.offset(EnumFacing.WEST)))
				.withProperty(UP, !isSameBlock(world, pos.offset(EnumFacing.UP))).withProperty(DOWN, !isSameBlock(world, pos.offset(EnumFacing.DOWN)));
	}

	private boolean isSameBlock(IBlockAccess world, BlockPos pos) {
		IBlockState target = world.getBlockState(pos);
		return target != null && target.getBlock() == this;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FACING,
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
		return new IProperty[] {
				DCState.FACING
		};
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.CUSTOM;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		tooltip.add(I18n.format("dcs.tip.exclusive"));
		tooltip.add(I18n.format("dcs.tip.displaycase"));
	}
}
