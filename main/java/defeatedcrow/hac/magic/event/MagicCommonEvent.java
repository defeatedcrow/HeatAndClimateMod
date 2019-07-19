package defeatedcrow.hac.magic.event;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;

public class MagicCommonEvent {

	@SubscribeEvent
	public void onAttackEvent(LivingAttackEvent event) {
		EntityLivingBase target = event.getEntityLiving();
		DamageSource source = event.getSource();
		if (target != null && source != null) {
			if (source.getTrueSource() instanceof EntityTameable) {
				EntityTameable living = (EntityTameable) source.getTrueSource();

				boolean amu = DCUtil.hasCharmItem(living, new ItemStack(MagicInit.colorBadge, 1, 3));
				if (amu && living.getOwner() instanceof EntityPlayer) {
					target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) living.getOwner()), event
							.getAmount());
					event.setCanceled(true);
				}
			} else if (source.getTrueSource() instanceof EntityLivingBase) {
				EntityLivingBase owner = (EntityLivingBase) source.getTrueSource();
				if (owner != null) {
					if (!(target instanceof IMob) && (DCUtil.hasCharmItem(owner, new ItemStack(MagicInit.pendant, 1,
							19)) || DCUtil.hasCharmItem(owner, new ItemStack(MagicInit.colorPendant, 1, 4)))) {
						// white pendant
						event.setCanceled(true);
					} else if (DCUtil.hasCharmItem(owner, new ItemStack(MagicInit.colorBadge, 1, 3))) {
						// black badge
						target.attackEntityFrom(DamageSource.causeMobDamage(target), event.getAmount());
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity != null && entity instanceof EntityArrow) {
			EntityArrow arrow = (EntityArrow) entity;

			if (arrow.shootingEntity instanceof EntityLivingBase) {
				EntityLivingBase liv = (EntityLivingBase) arrow.shootingEntity;
				if (DCUtil.hasCharmItem(liv, new ItemStack(MagicInit.pendant, 1, 18))) {
					arrow.shoot(liv, liv.rotationPitch, liv.rotationYaw, 0.0F, 5.0F, 0.0F);
				}
			}
		}
	}

	@SubscribeEvent
	public void onInteract(PlayerInteractEvent.EntityInteractSpecific event) {
		EntityPlayer player = event.getEntityPlayer();
		Entity target = event.getTarget();
		if (player != null && target != null && !player.world.isRemote) {
			if (DCUtil.hasCharmItem(player, new ItemStack(MagicInit.pendant, 1, 17))) {
				if (target instanceof EntityTameable) {
					EntityTameable animal = (EntityTameable) target;
					if (player.getHeldItem(event.getHand()).getItem() == Items.APPLE && !animal.isTamed()) {
						animal.setTamedBy(player);
						animal.setHealth(animal.getMaxHealth());
						animal.setAttackTarget((EntityLivingBase) null);
						animal.getEntityWorld().setEntityState(animal, (byte) 7);
						playTameEffect(animal);
						if (animal instanceof EntityWolf) {
							((EntityWolf) animal).getAISit().setSitting(true);
						} else if (animal instanceof EntityOcelot) {
							((EntityOcelot) animal).getAISit().setSitting(true);
							((EntityOcelot) animal).setTameSkin(1 + animal.getEntityWorld().rand.nextInt(3));
						}
						event.setCancellationResult(EnumActionResult.SUCCESS);
					}
				} else if (target instanceof AbstractHorse) {
					AbstractHorse horse = (AbstractHorse) target;
					if (player.getHeldItem(event.getHand()).getItem() == Items.APPLE && !horse.isTame()) {
						horse.increaseTemper(100);
						event.setCancellationResult(EnumActionResult.SUCCESS);
					}
				}
			} else if (player.isSneaking() && DCUtil.hasCharmItem(player, new ItemStack(MagicInit.colorRing2, 1, 0))) {
				if (target instanceof EntityLiving) {
					player.openGui(ClimateMain.instance, target.getEntityId(), player.world, target.getPosition()
							.getX(), target.getPosition().getY(), target.getPosition().getZ());
					event.setCancellationResult(EnumActionResult.SUCCESS);
				}
			}
		}
	}

	private void playTameEffect(Entity animal) {
		EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

		for (int i = 0; i < 7; ++i) {
			double d0 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			double d1 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			double d2 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			animal.getEntityWorld().spawnParticle(enumparticletypes, animal.posX + animal.getEntityWorld().rand
					.nextFloat() * animal.width * 2.0F - animal.width, animal.posY + 0.5D + animal.getEntityWorld().rand
							.nextFloat() * animal.height, animal.posZ + animal.getEntityWorld().rand
									.nextFloat() * animal.width * 2.0F - animal.width, d0, d1, d2);
		}
	}

	@SubscribeEvent
	public void afterWarpDimEvent(PlayerChangedDimensionEvent event) {
		EntityPlayer player = event.player;
		if (player != null) {
			ItemStack charm = MainUtil.getCharmItem(player, new ItemStack(MagicInit.colorBadge, 1, 1));
			if (!DCUtil.isEmpty(charm)) {
				int dim = player.world.provider.getDimension();
				String dimName = player.world.provider.getDimensionType().getName();
				int x = MathHelper.floor(player.posX);
				int y = MathHelper.floor(player.posY);
				int z = MathHelper.floor(player.posZ);
				NBTTagCompound tag = charm.getTagCompound();
				if (tag == null) {
					tag = new NBTTagCompound();
				}
				tag.setString("dcs.portal.dimname", dimName);
				tag.setInteger("dcs.portal.dim", dim);
				tag.setInteger("dcs.portal.x", x);
				tag.setInteger("dcs.portal.y", y);
				tag.setInteger("dcs.portal.z", z);
				charm.setTagCompound(tag);
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

		if (!living.world.isRemote && living instanceof IMob) {
			int lu1 = 0;
			int lb2 = 0;
			if (source.getTrueSource() instanceof EntityLivingBase) {
				EntityLivingBase liv2 = (EntityLivingBase) source.getTrueSource();
				lu1 = MainUtil.getCharmLevel(liv2, new ItemStack(MagicInit.colorRing, 1, 0));
				lb2 = MainUtil.getCharmLevel(liv2, new ItemStack(MagicInit.colorRing2, 1, 3));
			}
			int m = 1 + (lu1 + level) * 5;
			if (living.world.rand.nextInt(100) < m) {
				int meta = getDropMeta(living);
				ItemStack item1 = new ItemStack(MagicInit.colorDrop, 1, meta);
				EntityItem drop = new EntityItem(living.world, living.posX, living.posY, living.posZ, item1);
				event.getDrops().add(drop);
			}
			int m2 = lb2 * 10;
			if (living.world.rand.nextInt(100) < m2) {
				if (!event.getDrops().isEmpty()) {
					int i = event.getDrops().size();
					int i2 = living.world.rand.nextInt(i);
					event.getDrops().get(i2).getItem().grow(1);
				}
			}
		}
	}

	static int getDropMeta(EntityLivingBase living) {
		if (living.getCreatureAttribute() == EnumCreatureAttribute.ILLAGER || living instanceof EntityEnderman) {
			return 4;
		} else if (living.isEntityUndead() || living.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
			return 3;
		} else if (living instanceof EntityCreeper || living instanceof EntityGhast || living instanceof EntityBlaze) {
			return 2;
		} else if (living instanceof EntitySlime || living instanceof EntityGuardian) {
			return 0;
		} else {
			return 1;
		}
	}

	// @SubscribeEvent
	public void collisionEvent(GetCollisionBoxesEvent event) {
		if (event.getAabb() != null && !event.getCollisionBoxesList().isEmpty()) {
			List<AxisAlignedBB> list = event.getCollisionBoxesList();
			List<AxisAlignedBB> ret = Lists.newArrayList();
			int lv = 0;
			if (event.getEntity() != null) {
				if (event.getEntity() instanceof EntityPlayer && !event.getEntity().isSneaking()) {
					lv += MainUtil.getCharmLevel((EntityPlayer) event.getEntity(), new ItemStack(MagicInit.colorRing2,
							1, 3));
				}
			}

			List<EntityPlayer> entities = event.getWorld().playerEntities;
			for (EntityPlayer player : entities) {
				Vec3d p = new Vec3d(player.posX, player.posY, player.posZ);
				if (!player.isSneaking() && event.getAabb().contains(p)) {
					lv += MainUtil.getCharmLevel(player, new ItemStack(MagicInit.colorRing2, 1, 3));
				}
			}

			if (lv > 0) {
				event.getCollisionBoxesList().clear();
			}
		}
	}

}
