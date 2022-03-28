package defeatedcrow.hac.food.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPotteryPot extends DCTileBlock implements IAirflowTile {

	protected static final AxisAlignedBB AABB_MIDDLE = new AxisAlignedBB(0.0625D, 0D, 0.0625D, 0.9375D, 0.875D,
			0.9375D);

	public BlockPotteryPot(String s) {
		super(Material.CLAY, s, 0);
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null && !world.isRemote && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			ItemStack held = player.getHeldItem(hand);
			if (tile != null && tile instanceof TilePotteryPot) {
				if (player.isSneaking() && DCUtil.isEmpty(held)) {
					int type = DCState.getInt(state, DCState.TYPE4);
					boolean f = ((TilePotteryPot) tile).hasCap();
					boolean next = !f;
					DCLogger.debugLog("pottery type " + f + "->" + next);
					((TilePotteryPot) tile).setCap(next);
				} else {
					if (!DCUtil.isEmpty(held) && DCFluidUtil.onActivateDCTank(tile, held, world, state, side, player))
						return true;
					player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
				}
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePotteryPot();
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

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == FoodInit.potteryPot) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1), 2);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 0), 2);
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.HEAT.getLocalizedName() + ": " + TextFormatting.BLUE
					.toString() + "COLD" + TextFormatting.GRAY.toString() + " ~ " + TextFormatting.RED
							.toString() + "KILN");
			tooltip.add(DCName.REQUIRE_CLIMATE.getLocalizedName());
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(DCName.OUTPUT_ITEM.getLocalizedName());
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 1");
		tooltip.add(TextFormatting.AQUA.toString() + DCName.COLOR_CHANGE_TARGET
				.getLocalizedName());
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
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN;
	}

}
