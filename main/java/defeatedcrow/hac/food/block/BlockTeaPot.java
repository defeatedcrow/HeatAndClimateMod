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
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.EnumFixedName;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTeaPot extends DCTileBlock implements IAirflowTile {

	protected static final AxisAlignedBB AABB_MIDDLE = new AxisAlignedBB(0.125D, 0.125D, 0.125D, 0.875D, 0.875D,
			0.875D);

	public BlockTeaPot(String s) {
		super(Material.CLAY, s, 0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_MIDDLE;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.world.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			ItemStack held = player.getHeldItem(hand);
			if (tile instanceof TileTeaPot) {
				TileTeaPot pot = (TileTeaPot) tile;
				if (DrinkMilk.isMilkItem(held) != DrinkMilk.NONE) {
					DrinkMilk milk = DrinkMilk.isMilkItem(held);
					if (pot.setMilk(milk)) {
						if (!player.capabilities.isCreativeMode) {
							DCUtil.reduceStackSize(held, 1);
						}
						DCLogger.debugLog("Success to put milk: " + milk);
						DCLogger.debugLog("Milk Count: " + pot.getMilkCount());
						world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
					}
					return true;
				} else if (DrinkSugar.isSugarItem(held) != DrinkSugar.NONE) {
					DrinkSugar sugar = DrinkSugar.isSugarItem(held);
					if (pot.setSugar(sugar)) {
						if (!player.capabilities.isCreativeMode) {
							DCUtil.reduceStackSize(held, 1);
						}
						DCLogger.debugLog("Success to put sugar: " + sugar);
						DCLogger.debugLog("Sugar Count: " + pot.getSugarCount());
						world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
					}
					return true;
				} else if (!DCUtil.isEmpty(held) && onActivateDCTank(pot, held, world, state, side, player)) {
					return true;
				}
				player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileTeaPot();
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
		if (state.getBlock() == FoodInit.teaPot) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1), 2);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 0), 2);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != FoodInit.teaPot)
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

	/**
	 * DCTankをFluidContItemで右クリックする処理
	 */
	public static boolean onActivateDCTank(TileTeaPot tile, ItemStack item, World world, IBlockState state,
			EnumFacing side, EntityPlayer player) {
		if (!DCUtil.isEmpty(item) && tile != null && item
				.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, side) && tile
						.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)) {
			ItemStack copy = item.copy();
			if (item.getCount() > 1)
				copy.setCount(1);
			IFluidHandlerItem dummy = copy.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			IFluidHandler intank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
			IFluidHandler outtank = tile
					.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN);

			// dummyを使った検証
			if (dummy != null && dummy.getTankProperties() != null && dummy
					.getTankProperties().length > 0 && intank instanceof DCTank && outtank instanceof DCTank) {
				int max = dummy.getTankProperties()[0].getCapacity();
				FluidStack f1 = dummy.drain(max, false);
				DCTank dc_in = (DCTank) intank;
				DCTank dc_out = (DCTank) outtank;

				ItemStack ret = ItemStack.EMPTY;
				boolean success = false;
				// input
				if (f1 != null && dc_in.fill(f1, false) > 0) {
					int f2 = dc_in.fill(f1, false);
					FluidStack fill = dummy.drain(f2, true);
					ret = dummy.getContainer();
					if (fill != null && fill.amount > 0) {
						dc_in.fill(fill, true);
						success = true;
					}
				}
				// output
				else if (f1 == null && dc_out.drain(max, false) != null) {
					int drain = dummy.fill(dc_out.drain(max, false), true);
					ret = dummy.getContainer();
					if (!DCUtil.isEmpty(ret)) {
						// DCLogger.debugInfoLog("check1");
						if (ret.hasCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null)) {
							// DCLogger.debugInfoLog("check2");
							IDrinkCustomize drink = ret
									.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
							DrinkMilk milk = tile.cap.getMilk();
							DrinkSugar sugar = tile.cap.getSugar();
							if (drink.setMilk(milk)) {
								tile.milkCount--;
								if (tile.milkCount <= 0) {
									tile.cap.setMilk(DrinkMilk.NONE);
									tile.milkCount = 0;
								}
							}
							if (drink.setSugar(sugar)) {
								tile.sugarCount--;
								if (tile.sugarCount <= 0) {
									tile.cap.setSugar(DrinkSugar.NONE);
									tile.sugarCount = 0;
								}
							}
						}
					}
					if (drain > 0) {
						dc_out.drain(drain, true);
						success = true;
					}
				}

				if (success) {
					if (!player.capabilities.isCreativeMode) {
						DCUtil.reduceStackSize(item, 1);
					}
					tile.markDirty();
					player.inventory.markDirty();
					if (!DCUtil.isEmpty(ret)) {
						EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ, ret);
						world.spawnEntity(drop);
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(EnumFixedName.REQUIRE_CLIMATE.getLocalizedName());
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(EnumFixedName.OUTPUT_FLUID.getLocalizedName());
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

}
