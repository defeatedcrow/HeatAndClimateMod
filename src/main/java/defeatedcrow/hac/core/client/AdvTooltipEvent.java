package defeatedcrow.hac.core.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.compress.utils.Lists;

import com.mojang.datafixers.util.Either;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class AdvTooltipEvent {

	@SubscribeEvent
	public static void render(RenderTooltipEvent.GatherComponents event) {
		ItemStack target = event.getItemStack();
		if (!target.isEmpty()) {
			List<Component> list = Lists.newArrayList();

			float regH = DCItemUtil.getItemResistantData(target, false);
			float regC = DCItemUtil.getItemResistantData(target, true);
			if (regH != 0 || regC != 0) {
				MutableComponent ret = Component.translatable("dcs.tip.resistance").append(": " + String.format("%.1f", regH) + "/" + String.format("%.1f", regC));
				list.add(ret);
			}

			if (!target.getTags().toList().isEmpty()) {
				list.add(Component.literal("=== Tags ==="));
			}
			target.getTags().forEach(tag -> {
				list.add(Component.literal(tag.location().toString()));
			});

			if (!list.isEmpty()) {
				if (!ClimateCore.proxy.keyShiftPushed()) {
					list.clear();
					list.add(Component.translatable("dcs.tip.shift"));
				}

				List<Either<FormattedText, TooltipComponent>> elements = list.stream()
					.map((Function<FormattedText, Either<FormattedText, TooltipComponent>>) Either::left)
					.collect(Collectors.toCollection(ArrayList::new));
				event.getTooltipElements().addAll(elements);
			}
		}

	}

}
