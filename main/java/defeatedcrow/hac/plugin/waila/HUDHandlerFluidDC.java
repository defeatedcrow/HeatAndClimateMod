package defeatedcrow.hac.plugin.waila;

import java.util.List;

import defeatedcrow.hac.core.fluid.DCTank;
import defeatedcrow.hac.food.block.TileFluidProcessorBase;
import defeatedcrow.hac.machine.block.TileDieselEngine;
import defeatedcrow.hac.machine.block.TileHopperFluid;
import defeatedcrow.hac.machine.block.TileIBC;
import defeatedcrow.hac.machine.block.TileReactor;
import defeatedcrow.hac.machine.block.tankyard.TileTankYard;
import defeatedcrow.hac.machine.block.tankyard.TileYardPart;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import defeatedcrow.hac.main.block.device.TilePail;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.DCName;
import mcp.mobius.waila.addons.core.HUDHandlerBlocks;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class HUDHandlerFluidDC extends HUDHandlerBlocks {

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if (!config.getConfig("dcs_climate.showfluid") || accessor.getBlock() == null)
			return currenttip;

		if (accessor.getNBTData() != null && accessor.getNBTData().hasKey("DCTank")) {
			NBTTagList list = accessor.getNBTData().getTagList("DCTank", 10);
			NBTTagCompound nbt2 = list.getCompoundTagAt(0);
			int lim = accessor.getNBTData().getInteger("Limit");
			if (!nbt2.hasKey("Empty")) {
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt2);
				if (fluid != null && fluid.getFluid() != null) {
					currenttip.add(String.format(DCName.FLUID.getLocalizedName() + " : %s", fluid.getLocalizedName()));
					currenttip.add(String.format(DCName.AMOUNT
							.getLocalizedName() + " : %d / %d mB", fluid.amount, lim));
				}
			}

		}

		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		if (te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN)) {
			IFluidHandler tank = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN);
			if (tank != null && tank instanceof DCTank) {
				tag.setInteger("Limit", ((DCTank) tank).getCapacity());
				return ((DCTank) tank).writeToNBT(tag, "DCTank");
			}
		}
		return te.writeToNBT(tag);
	}

	public static void register(IWailaRegistrar registrar) {
		registrar.addConfig("HeatAndClimate", "dcs_climate.showfluid", true);

		HUDHandlerFluidDC provider = new HUDHandlerFluidDC();

		registrar.registerBodyProvider(provider, TilePail.class);
		registrar.registerNBTProvider(provider, TilePail.class);

		registrar.registerBodyProvider(provider, TileCookingStove.class);
		registrar.registerNBTProvider(provider, TileCookingStove.class);

		if (ModuleConfig.machine) {
			registrar.registerBodyProvider(provider, TileIBC.class);
			registrar.registerNBTProvider(provider, TileIBC.class);

			registrar.registerBodyProvider(provider, TileHopperFluid.class);
			registrar.registerNBTProvider(provider, TileHopperFluid.class);

			if (ModuleConfig.machine_advanced) {
				registrar.registerBodyProvider(provider, TileTankYard.class);
				registrar.registerNBTProvider(provider, TileTankYard.class);

				registrar.registerBodyProvider(provider, TileYardPart.class);
				registrar.registerNBTProvider(provider, TileYardPart.class);

				registrar.registerBodyProvider(provider, TileReactor.class);
				registrar.registerNBTProvider(provider, TileReactor.class);

				registrar.registerBodyProvider(provider, TileDieselEngine.class);
				registrar.registerNBTProvider(provider, TileDieselEngine.class);
			}

		}

		registrar.registerBodyProvider(provider, TileFluidProcessorBase.class);
		registrar.registerNBTProvider(provider, TileFluidProcessorBase.class);

	}
}
