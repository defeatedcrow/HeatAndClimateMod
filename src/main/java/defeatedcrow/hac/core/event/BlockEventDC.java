package defeatedcrow.hac.core.event;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent.FarmlandTrampleEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockEventDC {

	@SubscribeEvent
	public static void onFarmLand(FarmlandTrampleEvent event) {
		Entity entity = event.getEntity();
		if (entity == null || !entity.isAlive())
			return;

		if (entity instanceof LivingEntity living) {
			if (living.hasEffect(CoreInit.BIRD.get())) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onDig(PlayerEvent.BreakSpeed event) {
		Player player = event.getEntity();
		float f = event.getOriginalSpeed();
		if (player == null || !player.isAlive())
			return;

		int count = DCItemUtil.hasCharmItem(player, new ItemStack(MagicInit.PENDANT_SILVER_RED.get()));
		if (count > 0) {
			f += 0.5F * count;
		}

		if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get()) && player.hasEffect(CoreInit.FISH.get()) && !EnchantmentHelper.hasAquaAffinity(player)) {
			f *= 2.5F;
		}

		if (!player.isOnGround() && !player.isInWaterOrRain() && player.hasEffect(CoreInit.BIRD.get())) {
			f *= 2.5F;
		}

		event.setNewSpeed(f);
	}

}
