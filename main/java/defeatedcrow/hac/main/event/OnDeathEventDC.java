package defeatedcrow.hac.main.event;

import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.api.DimCoord;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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
			float magicHealthCost = 0F;
			if (ModuleConfig.magic && DCUtil.playerCanUseCharm(player, new ItemStack(MagicInit.colorBadge, 1, 1))) {
				// healthをコストにしている場合のみ後払いになる
				if (!CoreConfigDC.harderMagic || CoreConfigDC.harderMagicCost == 2) {
					if (CoreConfigDC.harderMagicCost == 2) {
						magicHealthCost = (float) CoreConfigDC.harderMagicCostAmount;
						if (magicHealthCost > 6.0F + charm.getCount() * 5.0F) {
							magicHealthCost = 4.0F + charm.getCount() * 5.0F;
						}
					}
					charm = MainUtil.getCharmItem(player, new ItemStack(MagicInit.colorBadge, 1, 1));
				} else if (DCUtil.playerCanUseCharm(player, new ItemStack(MagicInit.colorBadge, 1, 1))) {
					charm = MainUtil.getCharmItem(player, new ItemStack(MagicInit.colorBadge, 1, 1));
					DCUtil.playerConsumeCharm(player, new ItemStack(MagicInit.colorBadge, 1, 1));
				}
			}

			if (!DCUtil.isEmpty(charm)) {
				int dim = player.world.provider.getDimension();
				BlockPos pos = player.getBedLocation(dim);
				if (pos == null) {
					NBTTagCompound tag = charm.getTagCompound();
					if (tag != null && tag.hasKey("dcs.portal.dimname")) {
						DimCoord coord = DimCoord.getCoordFromNBT(tag);
						if (coord != null) {
							int x = coord.x;
							int y = coord.y;
							int z = coord.z;
							if (dim == coord.dimID) {
								pos = new BlockPos(x, y, z);
							}
						}
					}
				}
				if (pos != null) {
					player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D);
				}
				player.fallDistance = 0.0F;
				float health = 6.0F + charm.getCount() * 5.0F;
				health -= magicHealthCost;
				player.setHealth(health);
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
	public void onDropLoot(LivingDropsEvent event) {
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
