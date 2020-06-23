package defeatedcrow.hac.main.event;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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
			ItemStack charm = ItemStack.EMPTY;
			if (ModuleConfig.magic) {
				charm = MainUtil.getCharmItem(player, new ItemStack(MagicInit.colorBadge, 1, 1));
			}

			if (!DCUtil.isEmpty(charm)) {
				int dim = player.world.provider.getDimension();
				BlockPos pos = player.getBedLocation(dim);
				if (pos == null) {
					NBTTagCompound tag = charm.getTagCompound();
					if (tag != null && tag.hasKey("dcs.portal.dim")) {
						String warpDim = tag.getString("dcs.portal.dimname");
						int x = tag.getInteger("dcs.portal.x");
						int y = tag.getInteger("dcs.portal.y");
						int z = tag.getInteger("dcs.portal.z");
						if (dim == tag.getInteger("dcs.portal.dim")) {
							pos = new BlockPos(x, y, z);
						}
					}
				}
				if (pos != null) {
					player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D);
				}
				player.fallDistance = 0.0F;
				player.setHealth(6.0F + charm.getCount() * 5.0F);
				event.setCanceled(true);
				flag = true;
			}

			if (!flag) {
				if (player.getDisplayNameString().equals("defeatedcrow")) {
					DCLogger.infoLog("defeatedcrow dies...");
					ItemStack chicken = new ItemStack(FoodInit.sticks, 1, 3);
					EntityItem drop = new EntityItem(player.world, player.posX, player.posY, player.posZ, chicken);
					player.world.spawnEntity(drop);
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

		if (!(living instanceof EntityPlayer) && dam >= living.getHealth()) {
			if (DCUtil.hasCharmItem(living, new ItemStack(MagicInit.colorBadge, 1, 1))) {
				// DCLogger.debugInfoLog("on amulet process");
				living.fallDistance = 0.0F;
				living.setHealth(living.getMaxHealth() * 0.5F);
				living.world.playSound(null, living
						.getPosition(), SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.PLAYERS, 1.0F, 0.75F);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onDrop(LivingDropsEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		DamageSource source = event.getSource();
		int level = event.getLootingLevel();
		if (living == null)
			return;

		if (ModuleConfig.food && !living.world.isRemote) {
			if (living.world.rand.nextInt(3) == 0) {
				if (living instanceof EntitySquid) {
					if (source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer) {
						ItemStack squid = new ItemStack(FoodInit.meat, 1, 2);
						EntityItem drop = new EntityItem(living.world, living.posX, living.posY, living.posZ, squid);
						event.getDrops().add(drop);
					}
				} else if (living instanceof EntityAnimal) {
					if (source.getTrueSource() != null && source.getTrueSource() instanceof EntityPlayer) {
						ItemStack vis = new ItemStack(FoodInit.meat, 1, 0);
						EntityItem drop = new EntityItem(living.world, living.posX, living.posY, living.posZ, vis);
						event.getDrops().add(drop);
					}
				}
			}
		}
	}
}
