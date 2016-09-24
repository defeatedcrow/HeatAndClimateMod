package defeatedcrow.hac.food.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTeaPot extends DCTileBlock implements IAirflowTile {

	public BlockTeaPot(String s) {
		super(Material.CLAY, s, 0);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.worldObj.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileTeaPot) {
				TileTeaPot pot = (TileTeaPot) tile;
				if (onActivateDCTank(pot, heldItem, world, state, side, player)) {
					return true;
				} else if (DrinkMilk.isMilkItem(heldItem) != DrinkMilk.NONE) {
					DrinkMilk milk = DrinkMilk.isMilkItem(heldItem);
					if (pot.setMilk(milk)) {
						if (!player.capabilities.isCreativeMode && heldItem.stackSize-- <= 0) {
							heldItem = null;
						}
						DCLogger.debugLog("Success to put milk: " + milk);
						DCLogger.debugLog("Milk Count: " + pot.getMilkCount());
						world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
					}
					return true;
				} else if (DrinkSugar.isSugarItem(heldItem) != DrinkSugar.NONE) {
					DrinkSugar sugar = DrinkSugar.isSugarItem(heldItem);
					if (pot.setSugar(sugar)) {
						if (!player.capabilities.isCreativeMode && heldItem.stackSize-- <= 0) {
							heldItem = null;
						}
						DCLogger.debugLog("Success to put sugar: " + sugar);
						DCLogger.debugLog("Sugar Count: " + pot.getSugarCount());
						world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
					}
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
		if (state.getBlock() == FoodInit.teaPot) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 0), 3);
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
		if (item != null && tile != null && item.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)
				&& tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)) {
			ItemStack copy = new ItemStack(item.getItem(), 1, item.getItemDamage());
			if (item.getTagCompound() != null)
				copy.setTagCompound(item.getTagCompound());
			IFluidHandler cont = item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
			IFluidHandler dummy = copy.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
			IFluidHandler intank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.UP);
			IFluidHandler outtank = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
					EnumFacing.DOWN);

			// dummyを使った検証
			if (dummy != null && dummy.getTankProperties() != null && intank instanceof DCTank
					&& outtank instanceof DCTank) {
				int max = dummy.getTankProperties()[0].getCapacity();
				FluidStack f1 = dummy.drain(max, false);
				DCTank dc_in = (DCTank) intank;
				DCTank dc_out = (DCTank) outtank;

				ItemStack ret = null;
				boolean success = false;
				// input
				if (f1 != null && dc_in.fill(f1, false) == max) {
					FluidStack fill = dummy.drain(max, true);
					ret = copy;
					if (ret.stackSize <= 0) {
						ret = null;
					}
					if (fill != null && fill.amount == max) {
						dc_in.fill(fill, true);
						success = true;
					}
				}
				// output
				else if (f1 == null && dc_out.drain(max, false) != null) {
					int drain = dummy.fill(dc_out.drain(max, false), true);
					ret = copy;
					if (ret.stackSize <= 0) {
						ret = null;
					}
					if (ret != null) {
						DCLogger.debugLog("check1");
						if (ret.hasCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null)) {
							DCLogger.debugLog("check2");
							IDrinkCustomize drink = ret.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY,
									null);
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
					if (drain == max) {
						dc_out.drain(drain, true);
						success = true;
					}
				}

				if (success) {
					if (item.stackSize-- <= 0) {
						item = null;
					}
					tile.markDirty();
					player.inventory.markDirty();
					if (ret != null) {
						EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ, ret);
						world.spawnEntityInWorld(drop);
					}
					return true;
				}
			}
		}
		return false;
	}

}
