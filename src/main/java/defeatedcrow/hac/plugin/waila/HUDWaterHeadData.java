package defeatedcrow.hac.plugin.waila;

import static mcp.mobius.waila.api.TooltipPosition.*;

import defeatedcrow.hac.api.machine.IFluidPipe;
import defeatedcrow.hac.machine.material.block.FluidPipeBlock;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class HUDWaterHeadData implements IBlockComponentProvider {

	private static final HUDWaterHeadData INSTANCE = new HUDWaterHeadData();

	@Override
	public void appendTail(ITooltip tooltip, IBlockAccessor level, IPluginConfig config) {
		if (level.getBlock() == Blocks.AIR)
			return;
		if (config.getBoolean(FLUID_HEAD)) {
			BlockEntity tile = level.getBlockEntity();
			tile.getCapability(ForgeCapabilities.FLUID_HANDLER).filter(handler -> isPipe(handler)).ifPresent(handler -> {
				FluidStack fluid = handler.getFluidInTank(0);
				int head = DCFluidUtil.getHead(fluid);
				if (!fluid.isEmpty())
					tooltip.addLine(Component.translatable(String.format("Head: %d block", head)));
			});
		}
	}

	private static boolean isPipe(IFluidHandler handler) {
		return handler instanceof IFluidPipe;
	}

	public static void register(IRegistrar registrar) {

		registrar.addFeatureConfig(FLUID_HEAD, true);

		registrar.addComponent(INSTANCE, TAIL, FluidPipeBlock.class);

	}

	public static final ResourceLocation FLUID_HEAD = new ResourceLocation("dcs_climate", "fluid_head");

}
