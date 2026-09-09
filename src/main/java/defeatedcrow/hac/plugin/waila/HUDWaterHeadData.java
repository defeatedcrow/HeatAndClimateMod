package defeatedcrow.hac.plugin.waila;

import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.machine.material.block.transport.FluidPipeBlock;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import defeatedcrow.hac.machine.material.fluid.DCHeadTank;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.config.IPluginConfig;

public class HUDWaterHeadData implements IBlockComponentProvider {

	private static final HUDWaterHeadData INSTANCE = new HUDWaterHeadData();

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor level, IPluginConfig config) {
		if (level.getBlock() == Blocks.AIR)
			return;
		if (config.get(FLUID_HEAD)) {
			BlockEntity tile = level.getBlockEntity();
			if (tile == null)
				return;
			tile.getCapability(ForgeCapabilities.FLUID_HANDLER).filter(HUDWaterHeadData::isPipe).ifPresent(handler -> {
				FluidStack fluid = handler.getFluidInTank(0);
				int head = DCFluidUtil.getHead(fluid);
				if (!fluid.isEmpty())
					tooltip.add(Component.translatable(String.format("Head: %d block", head)));
			});
		}
	}

	private static boolean isPipe(IFluidHandler handler) {
		return handler instanceof IFluidPipe || handler instanceof DCHeadTank;
	}

	public static void register(IWailaClientRegistration registrar) {

		try {
			registrar.addConfig(FLUID_HEAD, true);
		} catch (Exception e) {
		}

		registrar.registerBlockComponent(INSTANCE, FluidPipeBlock.class);

	}

	public static final ResourceLocation FLUID_HEAD = ResourceLocation.fromNamespaceAndPath("dcs_climate", "fluid_head");

	@Override
	public ResourceLocation getUid() {
		return FLUID_HEAD;
	}

}
