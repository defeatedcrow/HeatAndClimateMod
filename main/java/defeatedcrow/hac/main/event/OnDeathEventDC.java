package defeatedcrow.hac.main.event;

import java.util.Map;

import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.plugin.baubles.DCPluginBaubles;
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
import net.minecraftforge.fml.common.Loader;
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
				if (!DCUtil.isEmpty(check) && check.getItem() == MagicInit.pendant) {
					int m = check.getMetadata();
					if (m == 7) {
						hasCharm = true;
					}
				}
			}

			if (Loader.isModLoaded("baubles") && !hasCharm) {
				if (DCPluginBaubles.hasBaublesCharm(player, new ItemStack(MagicInit.pendant, 1, 7))) {
					hasCharm = true;
				} else if (DCPluginBaubles.hasBaublesAmulet(player, new ItemStack(MagicInit.amulet, 1, 3))) {
					hasCharm = true;
				}
			}

			if (hasCharm) {
				int dim = player.world.provider.getDimension();
				boolean over = false;
				BlockPos pos = player.getBedLocation(dim);
				if (pos != null) {
					player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 1.25D, pos.getZ() + 0.5D);
					player.fallDistance = 0.0F;
					player.setHealth(10.0F);
					player.world.playSound(player, pos, Blocks.GLASS.getSoundType().getBreakSound(), SoundCategory.PLAYERS, 1.0F, 0.75F);
					event.setCanceled(true);
					flag = true;
				}
			}

			if (!flag) {
				if (player.getDisplayNameString().equals("defeatedcrow") || ClimateCore.isDebug) {
					if (source.isFireDamage() || source == DamageSourceClimate.climateHeatDamage) {
						DCLogger.infoLog("defeatedcrow dies...");
						ItemStack chicken = new ItemStack(FoodInit.sticks, 1, 3);
						EntityItem drop = new EntityItem(player.world, player.posX, player.posY, player.posZ, chicken);
						player.world.spawnEntity(drop);
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

		if (dam >= living.getMaxHealth()) {
			/* Projectileでの一撃必殺 */
			if (!living.world.isRemote && living.world.rand.nextBoolean()) {
				if (living instanceof EntitySquid) {
					if (source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer) {
						ItemStack squid = new ItemStack(FoodInit.meat, 1, 2);
						EntityItem drop = new EntityItem(living.world, living.posX, living.posY, living.posZ, squid);
						living.world.spawnEntity(drop);
					}
				} else if (living instanceof EntityAnimal) {
					if (source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer) {
						ItemStack vis = new ItemStack(FoodInit.meat, 1, 0);
						EntityItem drop = new EntityItem(living.world, living.posX, living.posY, living.posZ, vis);
						living.world.spawnEntity(drop);
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
				DCLogger.infoLog("on amulet process");
				living.fallDistance = 0.0F;
				living.setHealth(living.getMaxHealth() * 0.5F);
				living.world.playSound(null, living.getPosition(), Blocks.GLASS.getSoundType().getBreakSound(), SoundCategory.PLAYERS, 1.0F, 0.75F);
				event.setCanceled(true);
			}
		}
	}
}
