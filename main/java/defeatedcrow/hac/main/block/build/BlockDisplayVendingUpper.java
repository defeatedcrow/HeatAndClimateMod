package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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

/*
 * 不可視ダミーブロック
 */
public class BlockDisplayVendingUpper extends BlockDC {
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockDisplayVendingUpper(String s) {
		super(Material.CLAY, s);
		this.setHardness(-1F);
		this.setResistance(6000001.0F);
		this.setLightLevel(0.375F);
		this.fullBlock = false;
		this.lightOpacity = 0;
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH));
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		switch (face) {
		case NORTH:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		case EAST:
			return EAST_AABB;
		default:
			return NORTH_AABB;
		}
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.world.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			BlockPos d = pos.down();
			TileEntity tile = world.getTileEntity(d);
			if (tile instanceof TileDisplayVendingMachine) {
				if (((TileDisplayVendingMachine) tile).getOwner() != null) {
					if (((TileDisplayVendingMachine) tile).isOwnerOrOP(player) && player.isSneaking()) {
						player.openGui(ClimateMain.instance, 1, world, d.getX(), d.getY(), d.getZ());
					} else {
						player.openGui(ClimateMain.instance, 2, world, d.getX(), d.getY(), d.getZ());
					}
				}
			}
		}
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
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
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
		return BlockRenderLayer.SOLID;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		return list;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	// 2ブロック合体管理
	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		IBlockState under = world.getBlockState(pos.down());
		if (under == null || under.getBlock() != MainInit.displayVender) {
			world.destroyBlock(pos, true);
		} else {
			EnumFacing u = DCState.getFace(state, DCState.FACING);
			EnumFacing d = DCState.getFace(under, DCState.FACING);
			if (d != u) {
				IBlockState next = state.withProperty(DCState.FACING, d);
				world.setBlockState(pos, next, 3);
			}
		}
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int f = 5 - (meta >> 2);
		IBlockState state = this.getDefaultState();
		state = state.withProperty(DCState.FACING, EnumFacing.getFront(f));
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int f = 0;
		f = 5 - state.getValue(DCState.FACING).getIndex();
		f = f << 2;
		return f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FACING
		});
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.FACING;
	}
}
