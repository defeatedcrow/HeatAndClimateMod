package defeatedcrow.hac.core.event;

import defeatedcrow.hac.api.material.ITierItem;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
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

	// scythe
	@SubscribeEvent
	public static void onBreakBlock(BlockEvent.BreakEvent event) {
		Player player = event.getPlayer();
		BlockState state = event.getState();
		BlockPos pos = event.getPos();
		Level level = player.level;
		ItemStack held = player.getMainHandItem();
		if (level.isClientSide || player.isCrouching() || player.isSpectator())
			return;

		if (!DCUtil.isEmpty(held) && held.is(TagDC.ItemTag.SCYTHES)) {
			int range = 2;
			if (held.getItem() instanceof ITierItem tool) {
				range = tool.getTier().getLevel() + 1;
			}
			boolean b = false;
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			// 上から削る
			for (int y = range; y > -range - 1; y--) {
				for (int x = -range; x < range + 1; x++) {
					for (int z = -range; z < range + 1; z++) {
						if (x == 0 && y == 0 && z == 0)
							continue;
						mpos.set(pos.offset(x, y, z));
						BlockState target = level.getBlockState(mpos);
						ItemStack copy = held.copy();
						if (level.getBlockEntity(mpos) != null || !target.canHarvestBlock(level, mpos, player))
							continue;

						if (target.is(TagDC.BlockTag.SCYTHE_BREAKABLE) || target.is(BlockTags.FLOWERS) || target.is(TagDC.BlockTag.WEED)) {
							if (target.onDestroyedByPlayer(level, mpos, player, true, level.getFluidState(mpos))) {
								target.getBlock().destroy(level, mpos, state);
								target.getBlock().playerDestroy(level, player, mpos, target, null, copy);
								b = true;
							}
						}
					}
				}
			}
		}
	}

}
