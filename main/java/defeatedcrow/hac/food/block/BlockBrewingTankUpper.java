package defeatedcrow.hac.food.block;

import java.util.Random;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBrewingTankUpper extends DCTileBlock {

	public BlockBrewingTankUpper(String s) {
		super(Material.CLAY, s, 0);
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null && !world.isRemote && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos.down());
			ItemStack held = player.getHeldItem(hand);
			if (tile != null && tile instanceof TileBrewingTankUnder) {
				if (!DCUtil.isEmpty(held) && DCFluidUtil.onActivateDCTank(tile, held, world, state, side, player))
					return true;
				player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY() - 1, pos.getZ());
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBrewingTankUpper();
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		// block側の気候処理は無し
	}

	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		world.updateComparatorOutputLevel(pos, state.getBlock());
		world.removeTileEntity(pos);
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
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return MainUtil.calcTankRedstone(worldIn.getTileEntity(pos.down()));
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

	// 2ブロック合体管理

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		IBlockState under = world.getBlockState(pos.down());
		if (under == null || under.getBlock() != FoodInit.brewingTank) {
			world.destroyBlock(pos, true);
		}
	}

}
