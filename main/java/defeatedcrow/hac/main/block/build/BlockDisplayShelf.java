package defeatedcrow.hac.main.block.build;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.block.BlockExclusiveDC;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDisplayShelf extends BlockExclusiveDC {

	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockDisplayShelf(Material m, String s) {
		super(m, s, 0);
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
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face == null)
			return super.withRotation(state, rot);
		switch (rot) {
		case CLOCKWISE_180:
			return state.withProperty(DCState.FACING, face.getOpposite());
		case COUNTERCLOCKWISE_90:
			return state.withProperty(DCState.FACING, face.rotateY());
		case CLOCKWISE_90:
			return state.withProperty(DCState.FACING, face.rotateYCCW());
		default:
			return state;
		}
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		if (face == null)
			return super.withMirror(state, mirrorIn);
		switch (mirrorIn) {
		case LEFT_RIGHT:
			return state.withProperty(DCState.FACING, face.getOpposite());
		case FRONT_BACK:
			return state.withProperty(DCState.FACING, face.getOpposite());
		default:
			return super.withMirror(state, mirrorIn);
		}
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
			if (tile instanceof TileDisplayShelf) {
				player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDisplayShelf();
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}

	// 破壊時
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileDisplayShelf) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
			world.updateComparatorOutputLevel(pos, state.getBlock());
		}
		world.removeTileEntity(pos);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

}
