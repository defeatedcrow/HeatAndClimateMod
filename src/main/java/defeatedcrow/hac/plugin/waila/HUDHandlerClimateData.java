package defeatedcrow.hac.plugin.waila;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.climate.IHumidityTile;
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
	public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
		if (accessor.getBlock() == Blocks.AIR)
			return;

		if (config.get(CLIMATE_SMELTING) && DCRecipes.hasAnySmeltingRecipe(accessor.getBlock())
		    .isPresent()) {

			ClimateSupplier clm = new ClimateSupplier(accessor.getLevel(), accessor.getPosition());
			if (DCRecipes.getSmeltingRecipe(clm, new ItemStack(accessor.getBlock()))
			    .isPresent()) {
				tooltip.add(Component.translatable("dcs.tip.waila.smelting_suitable")
				    .withStyle(ChatFormatting.AQUA));
				if (accessor.getBlock()
				    .isRandomlyTicking(accessor.getBlockState())) {
					tooltip.add(Component.translatable("dcs.tip.waila.smelting_tick_scheduled"));
				} else {
					tooltip.add(Component.translatable("dcs.tip.waila.smelting_tick_not_scheduled"));
				}
			}
			return;
		}

		if (config.get(CLIMATE_SMELTING) && DCRecipes.hasAnyHeatTreatmentRecipe(accessor.getBlock())
		    .isPresent()) {

			ClimateSupplier clm = new ClimateSupplier(accessor.getLevel(), accessor.getPosition());
			if (DCRecipes.getHeatTreatmentRecipe(clm, new ItemStack(accessor.getBlock()))
			    .isPresent()) {
				tooltip.add(Component.translatable("dcs.tip.waila.smelting_suitable")
				    .withStyle(ChatFormatting.AQUA));
				if (accessor.getBlock()
				    .isRandomlyTicking(accessor.getBlockState())) {
					tooltip.add(Component.translatable("dcs.tip.waila.smelting_tick_scheduled"));
				} else {
					tooltip.add(Component.translatable("dcs.tip.waila.smelting_tick_not_scheduled"));
				}
			}
			return;
		}

		if (config.get(CLIMATE) && ClimateAPI.registerBlock.isRegisteredBlock(accessor.getBlockState())) {

			ClimateAPI.registerBlock.getHeatTier(accessor.getBlockState())
			    .ifPresent(heat -> {
				    if (heat != DCHeatTier.NORMAL) {
					    tooltip.add(DCHeatTier.basename2()
					        .withStyle(heat.getChatColor()));
					    tooltip.append(heat.localize());
				    }
			    });
			ClimateAPI.registerBlock.getHumidity(accessor.getBlockState())
			    .ifPresent(hum -> {
				    if (hum != DCHumidity.NORMAL) {
					    tooltip.add(DCHumidity.basename2()
					        .withStyle(hum.getChatColor()));
					    tooltip.append(hum.localize());
				    }
			    });
			ClimateAPI.registerBlock.getAirflow(accessor.getBlockState())
			    .ifPresent(air -> {
				    if (air != DCAirflow.TIGHT) {
					    tooltip.add(DCAirflow.basename2()
					        .withStyle(air.getChatColor()));
					    tooltip.append(air.localize());
				    }
			    });
			return;
		}

		if (config.get(CLIMATE) && accessor.getBlock() instanceof IHeatTile tile) {
			DCHeatTier heat = tile.getHeatTier(accessor.getLevel(), accessor.getPosition(), accessor.getPosition());
			if (heat != DCHeatTier.NORMAL) {
				tooltip.add(DCHeatTier.basename2()
				    .withStyle(heat.getChatColor()));
				tooltip.append(heat.localize());
			}
			return;
		}

		if (config.get(CLIMATE) && accessor.getBlock() instanceof IHumidityTile tile) {
			DCHumidity hum = tile.getHumidity(accessor.getLevel(), accessor.getPosition(), accessor.getPosition());
			if (hum != DCHumidity.NORMAL) {
				tooltip.add(DCHumidity.basename2()
				    .withStyle(hum.getChatColor()));
				tooltip.append(hum.localize());
			}
			return;
		}

		if (config.get(CLIMATE) && accessor.getBlock() instanceof IAirflowTile tile) {
			DCAirflow air = tile.getAirflow(accessor.getLevel(), accessor.getPosition(), accessor.getPosition());
			if (air != DCAirflow.TIGHT) {
				tooltip.add(DCAirflow.basename2()
				    .withStyle(air.getChatColor()));
				tooltip.append(air.localize());
			}
		}

	}

	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	public static void register(IWailaClientRegistration registration) {
		registration.addConfig(CLIMATE, true);
		registration.addConfig(CLIMATE_SMELTING, true);
		registration.registerBlockComponent(INSTANCE, Block.class);
	}

	public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath("dcs_climate", "climate_data");
	public static final ResourceLocation CLIMATE = ResourceLocation.fromNamespaceAndPath("dcs_climate", "show_climate");
	public static final ResourceLocation CLIMATE_SMELTING = ResourceLocation.fromNamespaceAndPath("dcs_climate", "show_climate_smelting_target");

}
