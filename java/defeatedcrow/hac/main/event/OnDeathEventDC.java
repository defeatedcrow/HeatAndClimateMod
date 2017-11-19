package defeatedcrow.hac.main.event;

import java.util.Map;

import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.magic.MagicInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnDeathEventDC {

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		DamageSource source = event.getSource();
		if (living == null)
			return;

		boolean flag = false;

		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if (check != null && check.getItem() != null && check.getItem() == MagicInit.pendant) {
					int m = check.getMetadata();
					if (m == 7) {
						hasCharm = true;
					}
				}
			}

			if (hasCharm) {
				int dim = player.worldObj.provider.getDimension();
				boolean over = false;
				BlockPos pos = player.getBedLocation(dim);
				if (pos != null) {
					player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 1.25D, pos.getZ() + 0.5D);
					player.fallDistance = 0.0F;
					player.setHealth(10.0F);
					player.worldObj.playSound(player, pos, Blocks.GLASS.getSoundType().getBreakSound(),
							SoundCategory.PLAYERS, 1.0F, 0.75F);
					event.setCanceled(true);
					flag = true;
				}
			}

			if (!flag) {
				if (player.getDisplayNameString().equals("defeatedcrow") || ClimateCore.isDebug) {
					if (source.isFireDamage() || source == DamageSourceClimate.climateHeatDamage) {
						ItemStack chicken = new ItemStack(FoodInit.sticks, 1, 3);
						EntityItem drop = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ,
								chicken);
						player.worldObj.spawnEntityInWorld(drop);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onDrop(LivingHurtEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		DamageSource source = event.getSource();
		float dam = event.getAmount();
		if (living == null)
			return;

		/* Projectileでの一撃必殺 */
		if (dam > living.getMaxHealth()) {
			if (!living.worldObj.isRemote && living.worldObj.rand.nextBoolean()) {
				if (living instanceof EntitySquid) {
					if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer) {

						ItemStack squid = new ItemStack(FoodInit.meat, 1, 2);
						EntityItem drop = new EntityItem(living.worldObj, living.posX, living.posY, living.posZ, squid);
						living.worldObj.spawnEntityInWorld(drop);
					}
				} else if (living instanceof EntityAnimal) {
					if (source.getEntity() != null && source.getEntity() instanceof EntityPlayer) {
						ItemStack vis = new ItemStack(FoodInit.meat, 1, 0);
						EntityItem drop = new EntityItem(living.worldObj, living.posX, living.posY, living.posZ, vis);
						living.worldObj.spawnEntityInWorld(drop);
					}
				}
			}
		}

		if (dam >= living.getHealth()) {
			Map<Integer, ItemStack> map = DCUtil.getAmulets(living);
			boolean amu = false;
			if (!map.isEmpty()) {
				for (ItemStack item : map.values()) {
					if (item.getItem() == MagicInit.amulet && item.getItemDamage() == 3) {
						amu = true;
					}
				}
			}

			if (amu) {
				living.fallDistance = 0.0F;
				living.setHealth(living.getMaxHealth() * 0.5F);
				living.worldObj.playSound(null, living.getPosition(), Blocks.GLASS.getSoundType().getBreakSound(),
						SoundCategory.PLAYERS, 1.0F, 0.75F);
				event.setCanceled(true);
			}
		}
	}
}
