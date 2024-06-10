package defeatedcrow.hac.plugin.waila;

import static mcp.mobius.waila.api.TooltipPosition.*;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.climate.IHumidityTile;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.recipe.DCRecipes;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.component.PairComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class HUDHandlerClimateData implements IBlockComponentProvider {

	private static final HUDHandlerClimateData INSTANCE = new HUDHandlerClimateData();

	@Override
	public void appendTail(ITooltip tooltip, IBlockAccessor level, IPluginConfig config) {
		if (level.getBlock() == Blocks.AIR)
			return;

		if (config.getBoolean(CLIMATE_SMELTING) && DCRecipes.INSTANCE.hasAnySmeltingRecipe(level.getBlock()).isPresent()) {

			ClimateSupplier clm = new ClimateSupplier(level.getWorld(), level.getPosition());
			if (DCRecipes.INSTANCE.getSmeltingRecipe(clm, new ItemStack(level.getBlock())).isPresent()) {
				tooltip.addLine(Component.translatable("dcs.tip.waila.smelting_suitable").withStyle(ChatFormatting.AQUA));
				if (level.getBlock().isRandomlyTicking(level.getBlockState())) {
					tooltip.addLine(Component.translatable("dcs.tip.waila.smelting_tick_scheduled"));
				} else {
					tooltip.addLine(Component.translatable("dcs.tip.waila.smelting_tick_not_scheduled"));
				}
			}
			return;
		}

		if (config.getBoolean(CLIMATE_SMELTING) && DCRecipes.INSTANCE.hasAnyHeatTreatmentRecipe(level.getBlock()).isPresent()) {

			ClimateSupplier clm = new ClimateSupplier(level.getWorld(), level.getPosition());
			if (DCRecipes.INSTANCE.getHeatTreatmentRecipe(clm, new ItemStack(level.getBlock())).isPresent()) {
				tooltip.addLine(Component.translatable("dcs.tip.waila.smelting_suitable").withStyle(ChatFormatting.AQUA));
				if (level.getBlock().isRandomlyTicking(level.getBlockState())) {
					tooltip.addLine(Component.translatable("dcs.tip.waila.smelting_tick_scheduled"));
				} else {
					tooltip.addLine(Component.translatable("dcs.tip.waila.smelting_tick_not_scheduled"));
				}
			}
			return;
		}

		if (config.getBoolean(CLIMATE) && ClimateAPI.registerBlock.isRegisteredBlock(level.getBlockState())) {

			ClimateAPI.registerBlock.getHeatTier(level.getBlockState()).ifPresent(heat -> {
				if (heat != DCHeatTier.NORMAL)
					tooltip.addLine(new PairComponent(DCHeatTier.basename2().withStyle(heat.getChatColor()), heat.localize()));
			});
			ClimateAPI.registerBlock.getHumidity(level.getBlockState()).ifPresent(hum -> {
				if (hum != DCHumidity.NORMAL)
					tooltip.addLine(new PairComponent(DCHumidity.basename2().withStyle(hum.getChatColor()), hum.localize()));
			});
			ClimateAPI.registerBlock.getAirflow(level.getBlockState()).ifPresent(air -> {
				if (air != DCAirflow.TIGHT)
					tooltip.addLine(new PairComponent(DCAirflow.basename2().withStyle(air.getChatColor()), air.localize()));
			});
			return;
		}

		if (config.getBoolean(CLIMATE) && level.getBlock() instanceof IHeatTile tile) {
			DCHeatTier heat = tile.getHeatTier(level.getWorld(), level.getPosition(), level.getPosition());
			if (heat != DCHeatTier.NORMAL)
				tooltip.addLine(new PairComponent(DCHeatTier.basename2().withStyle(heat.getChatColor()), heat.localize()));
			return;
		}

		if (config.getBoolean(CLIMATE) && level.getBlock() instanceof IHumidityTile tile) {
			DCHumidity hum = tile.getHumidity(level.getWorld(), level.getPosition(), level.getPosition());
			if (hum != DCHumidity.NORMAL)
				tooltip.addLine(new PairComponent(DCHumidity.basename2().withStyle(hum.getChatColor()), hum.localize()));
			return;
		}

		if (config.getBoolean(CLIMATE) && level.getBlock() instanceof IAirflowTile tile) {
			DCAirflow air = tile.getAirflow(level.getWorld(), level.getPosition(), level.getPosition());
			if (air != DCAirflow.TIGHT)
				tooltip.addLine(new PairComponent(DCAirflow.basename2().withStyle(air.getChatColor()), air.localize()));
			return;
		}

	}

	public static void register(IRegistrar registrar) {

		try {
			registrar.addFeatureConfig(CLIMATE, true);
			registrar.addFeatureConfig(CLIMATE_SMELTING, true);
		} catch (Exception e) {
			DCLogger.warnLog("Since the old Waila API is not supported, it will not be added to the config.");
		}

		registrar.addComponent(INSTANCE, TAIL, Block.class);

	}

	public static final ResourceLocation CLIMATE = new ResourceLocation("dcs_climate", "show_climate");
	public static final ResourceLocation CLIMATE_SMELTING = new ResourceLocation("dcs_climate", "show_climate_smelting_target");

}
