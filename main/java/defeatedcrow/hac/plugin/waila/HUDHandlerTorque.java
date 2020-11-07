package defeatedcrow.hac.plugin.waila;

import java.util.List;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.block.BlockEntityPanel;
import defeatedcrow.hac.machine.block.BlockMonitorPanel;
import defeatedcrow.hac.machine.block.BlockMonitorTemp;
import defeatedcrow.hac.machine.block.TileEntityPanel;
import defeatedcrow.hac.machine.block.TileMonitorBase;
import defeatedcrow.hac.main.util.DCName;
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

		if (BlockMonitorTemp.class.isInstance(accessor.getBlock())) {
			float amount = accessor.getNBTData().getFloat("gauge");
			int s = meta & 7;
			EnumSide side = EnumSide.fromIndex(s);
			boolean power = meta > 7;
			int id = (int) amount - 1;
			if (id < 0) {
				currenttip.add(DCName.CLIMATE.getLocalizedName() + " : NO DATA");
				currenttip.add(String.format(DCName.FACING.getLocalizedName() + " : %s", side.getFacing()));
			} else {
				currenttip.add(String.format(DCName.CLIMATE.getLocalizedName() + " : %s", DCHeatTier.getTypeByID(id)
						.toString()));
				currenttip.add(String.format(DCName.FACING.getLocalizedName() + " : %s", side.getFacing()));
			}
		} else if (BlockMonitorPanel.class.isInstance(accessor.getBlock())) {
			float amount = accessor.getNBTData().getFloat("gauge");
			int s = meta & 7;
			EnumSide side = EnumSide.fromIndex(s);
			boolean power = meta > 7;
			currenttip.add(String.format(DCName.AMOUNT.getLocalizedName() + " : %.1f", amount));
			currenttip.add(String.format(DCName.FACING.getLocalizedName() + " : %s", side.getFacing()));
		} else if (BlockTorqueBase.class.isInstance(accessor.getBlock())) {
			float torque = accessor.getNBTData().getFloat("dcs.pretoq");
			int s = meta & 7;
			EnumSide side = EnumSide.fromIndex(s);
			boolean power = meta > 7;
			currenttip.add(String.format(DCName.TORQUE.getLocalizedName() + " : %.2f", torque));
			currenttip.add(String.format(DCName.FACING.getLocalizedName() + " : %s", side.getFacing()));
		} else if (BlockEntityPanel.class.isInstance(accessor.getBlock())) {
			int s = meta & 7;
			boolean power = meta > 7;
			String type = "Item";
			if (s == 0) {
				type = "Player";
			} else if (s == 1) {
				type = "Monster";
			} else if (s == 2) {
				type = "Animal";
			}
			currenttip.add(String.format(DCName.POWER.getLocalizedName() + " : %b", power));
			currenttip.add(String.format(DCName.TARGET.getLocalizedName() + " : %s", type));
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
		registrar.registerBodyProvider(provider, TileMonitorBase.class);
		registrar.registerNBTProvider(provider, TileMonitorBase.class);
		registrar.registerBodyProvider(provider, TileEntityPanel.class);
	}
}
