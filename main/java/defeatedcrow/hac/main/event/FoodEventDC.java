package defeatedcrow.hac.main.event;

import defeatedcrow.hac.api.hook.DCItemEatEvent;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FoodEventDC {

	@SubscribeEvent
	public void onEvent(DCItemEatEvent event) {
		World world = event.world;
		EntityLivingBase living = event.living;
		ItemStack item = event.item;
		if (!world.isRemote && living instanceof EntityPlayer && !DCUtil.isEmpty(item)) {
			EntityPlayer player = (EntityPlayer) living;
			boolean b = false;
			boolean c = false;
			float s = 0.0F;
			int h = 0;

			if (player.isPotionActive(MainInit.digestive) && item.getItem() instanceof ItemFood) {
				s += 0.1F;
				h += player.getActivePotionEffect(MainInit.digestive).getAmplifier() + 1;
				player.getFoodStats().addStats(h, s);
			}
			if (player.isPotionActive(MainInit.immunity) && event.potion != null && event.potion.getPotion()
					.isBadEffect()) {
				if (item.getItem() instanceof ItemFood) {
					s += ((ItemFood) item.getItem()).getSaturationModifier(item);
					h += ((ItemFood) item.getItem()).getHealAmount(item);
				}
				player.getFoodStats().addStats(h, s);
				world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand
						.nextFloat() * 0.1F + 0.9F);
				player.addStat(StatList.getObjectUseStats(item.getItem()));

				if (player instanceof EntityPlayerMP) {
					CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) player, item);
				}

				event.setResult(Result.ALLOW);
			}

		}
	}

}
