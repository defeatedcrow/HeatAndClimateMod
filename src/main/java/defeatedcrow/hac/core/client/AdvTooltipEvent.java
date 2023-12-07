package defeatedcrow.hac.core.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.config.ConfigClientBuilder;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class AdvTooltipEvent {

	@SubscribeEvent
	public static void render(RenderTooltipEvent.GatherComponents event) {
		ItemStack target = event.getItemStack();
		if (!target.isEmpty() && ConfigClientBuilder.INSTANCE.showAltTip.get()) {
			List<Component> list = Lists.newArrayList();
			List<Component> list2 = Lists.newArrayList();

			float regH = DCItemUtil.getItemResistantData(target, false);
			float regC = DCItemUtil.getItemResistantData(target, true);
			if (regH != 0 || regC != 0) {
				MutableComponent ret = Component.translatable("dcs.tip.resistance").append(": " + String.format("%.1f", regH) + "/" + String.format("%.1f", regC));
				list.add(ret);
			}

			if (target.getItem() instanceof BlockItem blockitem) {
				Block block = blockitem.getBlock();
				ClimateAPI.registerBlock.getHeatTier(block.defaultBlockState()).ifPresent(heat -> {
					if (heat != DCHeatTier.NORMAL)
						list.add(DCHeatTier.basename2().append(": ").append(heat.localize()).withStyle(heat.getChatColor()));
				});
				ClimateAPI.registerBlock.getHumidity(block.defaultBlockState()).ifPresent(hum -> {
					if (hum != DCHumidity.NORMAL)
						list.add(DCHumidity.basename2().append(": ").append(hum.localize()).withStyle(hum.getChatColor()));
				});
				ClimateAPI.registerBlock.getAirflow(block.defaultBlockState()).ifPresent(air -> {
					if (air != DCAirflow.TIGHT)
						list.add(DCAirflow.basename2().append(": ").append(air.localize()).withStyle(air.getChatColor()));
				});
			}

			if (!list.isEmpty()) {
				List<Either<FormattedText, TooltipComponent>> elements = list.stream()
					.map((Function<FormattedText, Either<FormattedText, TooltipComponent>>) Either::left)
					.collect(Collectors.toCollection(ArrayList::new));
				event.getTooltipElements().addAll(elements);
			}

			if (target.getItem() instanceof ItemDC item) {
				item.advTooltipText(target, Minecraft.getInstance().level, list2);
			} else if (target.getItem() instanceof BlockItemDC blockitem) {
				if (blockitem.getBlock() instanceof BlockDC block) {
					block.advTooltipText(target, Minecraft.getInstance().level, list2);
				}
			}

			if (!target.getTags().toList().isEmpty()) {
				list2.add(Component.literal("=== Tags ==="));
			}
			target.getTags().forEach(tag -> {
				list2.add(Component.literal(tag.location().toString()));
			});

			if (!list2.isEmpty()) {
				if (!ClimateCore.proxy.keyShiftPushed()) {
					list2.clear();
					list2.add(Component.translatable("dcs.tip.shift"));
				}

				List<Either<FormattedText, TooltipComponent>> elements = list2.stream()
					.map((Function<FormattedText, Either<FormattedText, TooltipComponent>>) Either::left)
					.collect(Collectors.toCollection(ArrayList::new));
				event.getTooltipElements().addAll(elements);
			}
		}

	}

}
