package defeatedcrow.hac.plugin.waila;

import java.util.List;

import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.machine.block.TileIBC;
import mcp.mobius.waila.addons.HUDHandlerBase;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HUDHandlerFluidDC extends HUDHandlerBase {

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if (!config.getConfig("dcs_climate.showfluid") || accessor.getBlock() == null
				|| !DCTileBlock.class.isInstance(accessor.getBlock()))
			return currenttip;

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
