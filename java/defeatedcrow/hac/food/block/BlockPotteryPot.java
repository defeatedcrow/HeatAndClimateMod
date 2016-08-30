package defeatedcrow.hac.food.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.block.fluid.FluidUtil;

public class BlockPotteryPot extends DCTileBlock implements IAirflowTile {

	public BlockPotteryPot(String s) {
		super(Material.CLAY, s, 0);
		this.setCreativeTab(ClimateMain.food);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.worldObj.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TilePotteryPot) {
				if (FluidUtil.onActivateDCTank(tile, heldItem, world, state, side, player)) {
					return true;
				}
				player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePotteryPot();
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
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
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == FoodInit.potteryPot) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 0), 3);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != FoodInit.potteryPot)
			return false;
		int meta = state.getBlock().getMetaFromState(state) & 3;
		return meta == 1;
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
	public DCAirflow getAirflow(World world, BlockPos to, BlockPos from) {
		return DCAirflow.NORMAL;
	}

}
