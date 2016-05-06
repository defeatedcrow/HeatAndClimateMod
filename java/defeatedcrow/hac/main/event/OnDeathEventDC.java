package defeatedcrow.hac.main.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;

public class OnDeathEventDC {

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		EntityLivingBase living = event.entityLiving;
		DamageSource source = event.source;
		if (living != null && living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if (check != null && check.getItem() != null && check.getItem() instanceof ItemMagicalPendant) {
					int m = check.getItemDamage();
					if (m == 7) {
						hasCharm = true;
					}
				}
			}

			if (hasCharm) {
				int dim = player.worldObj.provider.getDimensionId();
				boolean over = false;
				BlockPos pos = player.getBedLocation(dim);
				if (pos != null) {
					player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 1.25D, pos.getZ() + 0.5D);
					player.fallDistance = 0.0F;
					player.setHealth(10.0F);
					player.worldObj.playSoundAtEntity(player, Blocks.glass.stepSound.getBreakSound(), 1.0F, 0.75F);
					event.setCanceled(true);
				}

			}
		}
	}
}
