package defeatedcrow.hac.plugin.waila;

import java.util.List;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import mcp.mobius.waila.addons.core.HUDHandlerBlocks;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HUDHandlerTorque extends HUDHandlerBlocks {

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if (!config.getConfig("dcs_climate.showtorque") || accessor.getBlock() == null)
			return currenttip;

		Block block = accessor.getBlock();
		int meta = accessor.getMetadata();

		if (BlockTorqueBase.class.isInstance(accessor.getBlock())) {
			float torque = accessor.getNBTData().getFloat("dcs.pretoq");
			int s = meta & 7;
			EnumSide side = EnumSide.fromIndex(s);
			boolean power = meta > 7;
			currenttip.add(String.format("Torque Energy : %.2f", torque));
			currenttip.add(String.format("Facing : %s", side.getFacing()));
		}

		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world,
			BlockPos pos) {
		return te.writeToNBT(tag);
	}

	public static void register(IWailaRegistrar registrar) {
		registrar.addConfig("HeatAndClimate", "dcs_climate.showtorque", true);

		HUDHandlerTorque provider = new HUDHandlerTorque();

		registrar.registerBodyProvider(provider, TileTorqueBase.class);
		registrar.registerNBTProvider(provider, TileTorqueBase.class);
	}
}
