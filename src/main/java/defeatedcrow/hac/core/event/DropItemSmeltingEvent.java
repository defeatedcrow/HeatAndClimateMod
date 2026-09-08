package defeatedcrow.hac.core.event;

import java.util.Optional;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.event.DCItemUpdateEvent;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.IHeatTreatment;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DropItemSmeltingEvent {

	@SubscribeEvent
	public static void onItemUpdate(DCItemUpdateEvent event) {
		ItemEntity drop = event.entity;
		if (drop != null && !drop.level().isClientSide() && !DCUtil.isEmpty(drop.getItem())) {
			Level level = drop.level();
			Vec3 p = drop.getEyePosition();
			BlockPos pos = BlockPos.containing(p);
			ItemStack item = drop.getItem();

			// 20tickおき
			if (drop.getAge() % 20 == 0) {
				ClimateSupplier supplier = new ClimateSupplier(level, pos);
				IClimate current = supplier.get();
				Optional<IClimateSmelting> recipe1 = DCRecipes.getSmeltingRecipe(current, item.copy());
				Optional<IHeatTreatment> recipe2 = DCRecipes.getHeatTreatmentRecipe(current, item.copy());
				Optional<ItemStack> recipe3 = DCRecipes.getVanillaSmeltingRecipe(current, item, level);
				if (recipe1.isEmpty() && recipe2.isEmpty() && recipe3.isEmpty()) {
					return;
				}
				CompoundTag tag = item.getTag();
				if (tag != null && tag.contains(TagKeyDC.CURRENT_PROGRESS)) {
					int i = tag.getInt(TagKeyDC.CURRENT_PROGRESS);
					if (i <= 0) {
						if (recipe1.isPresent()) {
							ItemStack output = recipe1.get().getOutput();
							output.setCount(item.getCount());
							drop.setItem(output);
							if (level instanceof ServerLevel serverLevel)
								MsgEffectToC.sendToClient(serverLevel, p, 24);
						} else if (recipe2.isPresent()) {
							ItemStack output = recipe2.get().getCurrentOutput(item, current);
							output.setCount(item.getCount());
							drop.setItem(output);
							if (level instanceof ServerLevel serverLevel)
								MsgEffectToC.sendToClient(serverLevel, p, 24);
						} else if (recipe3.isPresent()) {
							ItemStack output = recipe3.get();
							output.setCount(item.getCount());
							drop.setItem(output);
							if (level instanceof ServerLevel serverLevel)
								MsgEffectToC.sendToClient(serverLevel, p, 24);
						}
					} else {
						i--;
						tag.putInt(TagKeyDC.CURRENT_PROGRESS, i);
						item.setTag(tag);
						if (level instanceof ServerLevel serverLevel)
							MsgEffectToC.sendToClient(serverLevel, p, 6);
					}
				} else {
					tag = item.getOrCreateTag();
					tag.putInt(TagKeyDC.CURRENT_PROGRESS, 3);
					item.setTag(tag);
					if (level instanceof ServerLevel serverLevel)
						MsgEffectToC.sendToClient(serverLevel, p, 6);
				}
			}

		}

	}
}
