package defeatedcrow.hac.plugin.waila;

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
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.config.IPluginConfig;

public class HUDHandlerClimateData implements IBlockComponentProvider {

	private static final HUDHandlerClimateData INSTANCE = new HUDHandlerClimateData();

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor level, IPluginConfig config) {
		if (level.getBlock() == Blocks.AIR)
			return;

		if (config.get(CLIMATE_SMELTING) && DCRecipes.hasAnySmeltingRecipe(level.getBlock()).isPresent()) {

			ClimateSupplier clm = new ClimateSupplier(level.getLevel(), level.getPosition());
			if (DCRecipes.getSmeltingRecipe(clm, new ItemStack(level.getBlock())).isPresent()) {
				tooltip.add(Component.translatable("dcs.tip.waila.smelting_suitable").withStyle(ChatFormatting.AQUA));
				if (level.getBlock().isRandomlyTicking(level.getBlockState())) {
					tooltip.add(Component.translatable("dcs.tip.waila.smelting_tick_scheduled"));
				} else {
					tooltip.add(Component.translatable("dcs.tip.waila.smelting_tick_not_scheduled"));
				}
			}
			return;
		}

		if (config.get(CLIMATE_SMELTING) && DCRecipes.hasAnyHeatTreatmentRecipe(level.getBlock()).isPresent()) {

			ClimateSupplier clm = new ClimateSupplier(level.getLevel(), level.getPosition());
			if (DCRecipes.getHeatTreatmentRecipe(clm, new ItemStack(level.getBlock())).isPresent()) {
				tooltip.add(Component.translatable("dcs.tip.waila.smelting_suitable").withStyle(ChatFormatting.AQUA));
				if (level.getBlock().isRandomlyTicking(level.getBlockState())) {
					tooltip.add(Component.translatable("dcs.tip.waila.smelting_tick_scheduled"));
				} else {
					tooltip.add(Component.translatable("dcs.tip.waila.smelting_tick_not_scheduled"));
				}
			}
			return;
		}

		if (config.get(CLIMATE) && ClimateAPI.registerBlock.isRegisteredBlock(level.getBlockState())) {

			ClimateAPI.registerBlock.getHeatTier(level.getBlockState()).ifPresent(heat -> {
				if (heat != DCHeatTier.NORMAL)
					tooltip.add(DCHeatTier.basename2().withStyle(heat.getChatColor()).append(Component.literal(" ")).append(heat.localize()));
			});
			ClimateAPI.registerBlock.getHumidity(level.getBlockState()).ifPresent(hum -> {
				if (hum != DCHumidity.NORMAL)
					tooltip.add(DCHumidity.basename2().withStyle(hum.getChatColor()).append(Component.literal(" ")).append(hum.localize()));
			});
			ClimateAPI.registerBlock.getAirflow(level.getBlockState()).ifPresent(air -> {
				if (air != DCAirflow.TIGHT)
					tooltip.add(DCAirflow.basename2().withStyle(air.getChatColor()).append(Component.literal(" ")).append(air.localize()));
			});
			return;
		}

		if (config.get(CLIMATE) && level.getBlock() instanceof IHeatTile tile) {
			DCHeatTier heat = tile.getHeatTier(level.getLevel(), level.getPosition(), level.getPosition());
			if (heat != DCHeatTier.NORMAL)
				tooltip.add(DCHeatTier.basename2().withStyle(heat.getChatColor()).append(Component.literal(" ")).append(heat.localize()));
			return;
		}

		if (config.get(CLIMATE) && level.getBlock() instanceof IHumidityTile tile) {
			DCHumidity hum = tile.getHumidity(level.getLevel(), level.getPosition(), level.getPosition());
			if (hum != DCHumidity.NORMAL)
				tooltip.add(DCHumidity.basename2().withStyle(hum.getChatColor()).append(Component.literal(" ")).append(hum.localize()));
			return;
		}

		if (config.get(CLIMATE) && level.getBlock() instanceof IAirflowTile tile) {
			DCAirflow air = tile.getAirflow(level.getLevel(), level.getPosition(), level.getPosition());
			if (air != DCAirflow.TIGHT)
				tooltip.add(DCAirflow.basename2().withStyle(air.getChatColor()).append(Component.literal(" ")).append(air.localize()));
		}

	}

	public static void register(IWailaClientRegistration registrar) {

		try {
			registrar.addConfig(CLIMATE, true);
			registrar.addConfig(CLIMATE_SMELTING, true);
		} catch (Exception e) {
			DCLogger.warnLog("Since the old Waila API is not supported, it will not be added to the config.");
		}

		registrar.registerBlockComponent(INSTANCE, Block.class);

	}

	public static final ResourceLocation CLIMATE = ResourceLocation.fromNamespaceAndPath("dcs_climate", "show_climate");
	public static final ResourceLocation CLIMATE_SMELTING = ResourceLocation.fromNamespaceAndPath("dcs_climate", "show_climate_smelting_target");

	@Override
	public ResourceLocation getUid() {
		return CLIMATE;
	}

}
