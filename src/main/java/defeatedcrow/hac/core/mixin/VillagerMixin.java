package defeatedcrow.hac.core.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;

@Mixin(Villager.class)
public class VillagerMixin {

	@Inject(method = "wantsToPickUp", at = @At(value = "HEAD"), cancellable = true, remap = true)
	public void hookPickUp(ItemStack item, @Nonnull CallbackInfoReturnable<Boolean> callback) {
		if (ConfigCommonBuilder.INSTANCE.enVillagerEatToHeal.get())
			if (!item.isEmpty() && Villager.FOOD_POINTS.keySet().contains(item.getItem())) {
				callback.setReturnValue(true);
			}
	}

}
