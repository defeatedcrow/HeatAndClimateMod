package defeatedcrow.hac.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import defeatedcrow.hac.api.event.DCItemUpdateEvent;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import net.minecraft.world.entity.item.ItemEntity;

@Mixin(ItemEntity.class)
public class ItemUpdateMixin {

	@Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
	public void onTick(CallbackInfo callback) {
		ItemEntity entity = ItemEntity.class.cast(this);
		if (entity != null && ConfigCommonBuilder.INSTANCE.enDropSmelting.get()) {
			DCItemUpdateEvent event = new DCItemUpdateEvent(entity);
			if (event.post()) {
				callback.cancel();
			}
		}
	}

}
