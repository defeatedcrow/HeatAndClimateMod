package defeatedcrow.hac.main.block.fluid;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class FluidUtil {

	private FluidUtil() {

	}

	/**
	 * DCTankをFluidContItemで右クリックする処理
	 */
	public static boolean onActivateDCTank(TileEntity tile, ItemStack item, World world, IBlockState state,
			EnumFacing side, EntityPlayer player) {
		if (!DCUtil.isEmpty(item) && tile != null && item
				.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, side) && tile
						.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)) {
			ItemStack copy = new ItemStack(item.getItem(), 1, item.getItemDamage());
			if (item.getTagCompound() != null) {
				copy.setTagCompound(item.getTagCompound());
			}
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
				if (f1 != null && dc_in.fill(f1, false) == max) {
					FluidStack fill = dummy.drain(max, true);
					ret = dummy.getContainer();
					if (fill != null && fill.amount == max) {
						dc_in.fill(fill, true);
						success = true;
					}
				}
				// output
				else if (f1 == null && dc_out.drain(max, false) != null) {
					int drain = dummy.fill(dc_out.drain(max, false), true);
					ret = dummy.getContainer();
					if (drain == max) {
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

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/fluid/";
		list.add(b + "blacktea_still");
		list.add(b + "greentea_still");
		list.add(b + "coffee_still");
		list.add(b + "seedoil_still");
		list.add(b + "cream_still");
		list.add(b + "black_liquor_still");
		list.add(b + "hotspring_still");
		list.add(b + "vegetable_still");
		list.add(b + "stock_still");
		list.add(b + "hydrogen_still");
		list.add(b + "ammonia_still");
		list.add(b + "fuel_gas_still");
		list.add(b + "fuel_oil_still");
		list.add(b + "nitric_acid_still");
		list.add(b + "sulfuric_acid_still");
		list.add(b + "oxygen_still");

		return list;
	}

}
