package defeatedcrow.hac.plugin.waila;

import java.util.List;

import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.machine.block.BlockIBC;
import defeatedcrow.hac.machine.block.TileIBC;
import mcp.mobius.waila.addons.core.HUDHandlerBlocks;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class HUDHandlerFluidDC extends HUDHandlerBlocks {

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if (!config.getConfig("dcs_climate.showfluid") || accessor.getBlock() == null
				|| !DCTileBlock.class.isInstance(accessor.getBlock()))
			return currenttip;

		if (BlockIBC.class.isInstance(accessor.getBlock())) {
			NBTTagList list = accessor.getNBTData().getTagList("Tank", 10);
			NBTTagCompound nbt2 = list.getCompoundTagAt(0);
			if (!nbt2.hasKey("Empty")) {
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt2);
				if (fluid != null && fluid.getFluid() != null) {
					currenttip.add(String.format("Fluid : %s", fluid.getLocalizedName()));
					currenttip.add(String.format("Amount : %d mB", fluid.amount));

				}
			}

		}

		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		return te.writeToNBT(tag);
	}

	public static void register(IWailaRegistrar registrar) {
		registrar.addConfig("HeatAndClimate", "dcs_climate.showfluid", true);

		HUDHandlerFluidDC provider = new HUDHandlerFluidDC();

		registrar.registerBodyProvider(provider, TileIBC.class);
		registrar.registerNBTProvider(provider, TileIBC.class);
	}
}
