package defeatedcrow.hac.main.event;

import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.plugin.baubles.DCPluginBaubles;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.AdvancedHUDEvent;
import defeatedcrow.hac.main.client.particle.ParticleTempColor;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.worldgen.CaravanGenPos;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// 主にclient側のプレイヤー操作にかかわるもの
public class LivingMainEventDC {

	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if (entity != null && entity instanceof EntityArrow) {
			EntityArrow arrow = (EntityArrow) entity;
			if (arrow.shootingEntity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) arrow.shootingEntity;
				boolean hasCharm = false;
				for (int i = 9; i < 18; i++) {
					ItemStack check = player.inventory.getStackInSlot(i);
					if (!DCUtil.isEmpty(check) && check.getItem() == MagicInit.pendant) {
						int m = check.getMetadata();
						if (m == 18) {
							hasCharm = true;
						}
					}
				}
				if (Loader.isModLoaded("baubles") && !hasCharm) {
					if (DCPluginBaubles.hasBaublesCharm(player, new ItemStack(MagicInit.pendant, 1, 18))) {
						hasCharm = true;
					}
				}

				if (hasCharm) {
					arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 5.0F, 0.0F);
				}
			}
		}
	}

	@SubscribeEvent
	public void onInteract(PlayerInteractEvent.EntityInteractSpecific event) {
		EntityPlayer player = event.getEntityPlayer();
		Entity target = event.getTarget();
		if (player != null && target != null && !player.world.isRemote) {
			boolean hasCharm = false;
			for (int i = 9; i < 18; i++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if (!DCUtil.isEmpty(check) && check.getItem() == MagicInit.pendant) {
					int m = check.getMetadata();
					if (m == 17) {
						hasCharm = true;
					}
				}
			}

			if (hasCharm) {
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
			}
		}
	}

	private void playTameEffect(Entity animal) {
		EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

		for (int i = 0; i < 7; ++i) {
			double d0 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			double d1 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			double d2 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			animal.getEntityWorld().spawnParticle(enumparticletypes, animal.posX +
					animal.getEntityWorld().rand.nextFloat() * animal.width * 2.0F - animal.width, animal.posY + 0.5D +
							animal.getEntityWorld().rand.nextFloat() * animal.height, animal.posZ +
									animal.getEntityWorld().rand.nextFloat() * animal.width * 2.0F -
									animal.width, d0, d1, d2);
		}
	}

	@SubscribeEvent
	public void onSpawn(LivingSpawnEvent.CheckSpawn event) {
		EntityLivingBase living = event.getEntityLiving();
		if (living instanceof IMob && event.getY() > 65 && event.getY() < 85) {
			int cx = (int) event.getX() >> 4;
			int cz = (int) event.getZ() >> 4;
			int num = CaravanGenPos.getCaravanPartNum(cx, cz, living.getEntityWorld());
			if (num > -1) {
				int cx2 = (num % 3) + cx - 1;
				int cz2 = (num / 3) + cz - 1;
				if (CaravanGenPos.canGenerateBiome(cx2, cz2, living.getEntityWorld()) &&
						!CaravanGenPos.isDupe(cx2, cz2, living.getEntityWorld())) {
					event.setResult(Result.DENY);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEvent(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		if (living != null && living instanceof EntityPlayer) {
			this.onPlayerUpdate(event);
			if (living.world.isRemote) {
				this.onPlayerKeyUpdate(event);
				this.onPlayerToolClientUpdate(event);
			}
		} else if (living instanceof IMob) {
			this.onEnemyUpdate(event);
		}
	}

	public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (entity != null && !entity.isRiding()) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntity();
				if (player.isPotionActive(MainInit.ocean) && player.isInWater()) {
					// bird potion
					player.setAir(300);
				}
				if (player.isPotionActive(MainInit.bird)) {
					// bird potion
					player.fallDistance = 0.0F;
				}
				if (MainCoreConfig.pendant_schorl) {
					if (player.inventory.hasItemStack(new ItemStack(MagicInit.pendant, 1, 10)) &&
							player.isPotionActive(MobEffects.SPEED)) {
						player.stepHeight = 1.0F;
					} else {
						player.stepHeight = 0.6F;
					}
				}
			}
		}
	}

	public void onEnemyUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (entity != null && entity.isEntityAlive() && !entity.world.isRemote) {
			List<EntityPlayer> players = entity.world.playerEntities;
			boolean flag = false;
			for (EntityPlayer p : players) {
				if (p.isEntityAlive()) {
					double d1 = p.getPosition().distanceSq(entity.getPosition());
					double dist = Math.sqrt(d1);
					if (dist < 32.0D && MainUtil.isPlayerHeldItem(MainInit.entityScope, p)) {
						flag = true;
					}
				}
			}
			if (flag) {
				int i = 0;
				if (entity.isPotionActive(MobEffects.GLOWING)) {
					PotionEffect eff = entity.getActivePotionEffect(MobEffects.GLOWING);
					i = eff.getDuration();
				}
				if (i < 10) {
					entity.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 60, 0));
				}
			}
		}
	}

	private boolean x_key = false;

	@SideOnly(Side.CLIENT)
	public void onPlayerKeyUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (entity != null && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if (!player.isRiding()) {
				if (MainCoreConfig.ocean_effect && player.isPotionActive(MainInit.ocean) && player.isInWater()) {
					// ocean potion
					if (ClimateCore.proxy.isJumpKeyDown()) {
						player.motionY += 0.15D;
						if (player.motionY > 2.0D) {
							player.motionY = 2.0D;
						}
					} else if (ClimateMain.proxy.isSneakKeyDown() && !player.onGround) {
						player.motionY -= 0.15D;
						if (player.motionY < -2.0D) {
							player.motionY = -2.0D;
						}
					}
					if (ClimateMain.proxy.isForwardKeyDown()) {
						Vec3d vec3d = player.getLookVec();
						double d = Math.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
						double d1 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
						double df = 1.0D;
						if (player.isPotionActive(MobEffects.SPEED)) {
							df += player.getActivePotionEffect(MobEffects.SPEED).getAmplifier() * 0.5D;
						}
						if (d < df) {
							player.motionX += vec3d.x * 0.1D;
							player.motionZ += vec3d.z * 0.1D;
						}
					}
				} else if (MainCoreConfig.bird_effect && player.isPotionActive(MainInit.bird)) {
					// bird potion
					if (!player.isInWater() && !player.isElytraFlying()) {
						if (ClimateCore.proxy.isJumpKeyDown()) {
							player.motionY += 0.15D;
							if (player.motionY > 2.0D) {
								player.motionY = 2.0D;
							}
						} else if (ClimateMain.proxy.isSneakKeyDown() && !player.onGround) {
							player.motionY = 0.0D;
						}
						if (ClimateMain.proxy.isForwardKeyDown() && !player.onGround) {
							Vec3d vec3d = player.getLookVec();
							double d = Math.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
							double d1 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
							double df = 1.0D;
							if (player.isPotionActive(MobEffects.SPEED)) {
								df += player.getActivePotionEffect(MobEffects.SPEED).getAmplifier() * 0.5D;
							}
							if (d < df) {
								player.motionX += vec3d.x * 0.05D * df;
								player.motionZ += vec3d.z * 0.05D * df;
							}
						}
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void onPlayerToolClientUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (entity != null && entity instanceof EntityPlayer && AdvancedHUDEvent.INSTANCE.active) {
			EntityPlayer player = (EntityPlayer) entity;
			World world = player.world;

			if ((!DCUtil.isEmpty(player.getHeldItemMainhand()) &&
					player.getHeldItemMainhand().getItem() == MainInit.scope) ||
					(!DCUtil.isEmpty(player.getHeldItemOffhand()) &&
							player.getHeldItemOffhand().getItem() == MainInit.scope)) {

				EnumFacing face = player.getHorizontalFacing();
				BlockPos pos = player.getPosition().offset(face, 2);

				BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();

				for (int x1 = -8; x1 < 8; x1++) {
					for (int z1 = -8; z1 < 8; z1++) {
						for (int y1 = -3; y1 < 3; y1++) {
							mpos.setPos(pos.getX() + x1, pos.getY() + y1, pos.getZ() + z1);
							IBlockState state = world.getBlockState(mpos);

							if (state.getBlock() instanceof IHeatTile) {
								IBlockState s2 = world.getBlockState(mpos.up());
								if (!s2.getBlock().isSideSolid(s2, world, mpos.up(), EnumFacing.DOWN)) {
									DCHeatTier h1 = ((IHeatTile) state.getBlock()).getHeatTier(world, mpos.up(), mpos);
									if (h1 != DCHeatTier.NORMAL) {
										double px = mpos.getX() + 0.5D;
										double py = mpos.getY() + 0.5D;
										double pz = mpos.getZ() + 0.5D;
										Particle shock = new ParticleTempColor.Factory().createParticle(0, world, px, py, pz, 0D, 0D, 0D, h1.getColor());
										FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);
									}
								}
							} else {
								DCHeatTier h1 = ClimateAPI.registerBlock.getHeatTier(state.getBlock(), state.getBlock().getMetaFromState(state));
								if (h1 != DCHeatTier.NORMAL) {
									IBlockState s2 = world.getBlockState(mpos.up());
									if (!s2.getBlock().isSideSolid(s2, world, mpos.up(), EnumFacing.DOWN)) {
										double px = mpos.getX() + 0.5D;
										double py = mpos.getY() + 0.5D;
										double pz = mpos.getZ() + 0.5D;
										Particle shock = new ParticleTempColor.Factory().createParticle(0, world, px, py, pz, 0D, 0D, 0D, h1.getColor());
										FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
