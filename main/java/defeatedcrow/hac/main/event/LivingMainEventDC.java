package defeatedcrow.hac.main.event;

import java.lang.reflect.Field;
import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.block.build.BlockBedDC;
import defeatedcrow.hac.main.client.particle.ParticleTempColor;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.util.DCAdvancementUtil;
import defeatedcrow.hac.main.util.DCArmorMaterial;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.util.portal.DCDimChangeHelper;
import defeatedcrow.hac.main.worldgen.CaravanGenEvent.CaravanType;
import defeatedcrow.hac.main.worldgen.CaravanGenPos;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// 主にclient側のプレイヤー操作にかかわるもの
public class LivingMainEventDC {

	@SubscribeEvent
	public void onSpawn(LivingSpawnEvent.CheckSpawn event) {
		EntityLivingBase living = event.getEntityLiving();
		if (living instanceof IMob) {
			int cx = (int) event.getX() >> 4;
			int cz = (int) event.getZ() >> 4;
			if (event.getY() > 65 && event.getY() < 120) {
				int num = CaravanGenPos.getCaravanPartNum(cx, cz, living.getEntityWorld());
				if (num > -1) {
					int cx2 = (num % 3) + cx - 1;
					int cz2 = (num / 3) + cz - 1;
					if (living.world.isBlockLoaded(new BlockPos(7 + cx2 * 16, 61, 7 + cz2 * 16)) && CaravanGenPos
							.getType(event.getWorld(), cx2, cz2) != CaravanType.BROKEN) {
						event.setResult(Result.DENY);
					}
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
		this.onLivingUpdate(event);
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

				if (!player.world.isRemote && player instanceof EntityPlayerMP) {
					// advancement
					boolean b1 = false;
					boolean b2 = false;
					for (ItemStack armor : player.inventory.armorInventory) {
						if (!DCUtil.isEmpty(armor) && armor.getItem() instanceof ItemArmor) {
							if (((ItemArmor) armor.getItem())
									.getArmorMaterial() == DCArmorMaterial.DC_LINEN || ((ItemArmor) armor.getItem())
											.getArmorMaterial() == ArmorMaterial.LEATHER)
								b1 = true;
							else if (((ItemArmor) armor.getItem())
									.getArmorMaterial() == DCArmorMaterial.DC_CLOTH || ((ItemArmor) armor.getItem())
											.getArmorMaterial() == DCArmorMaterial.DC_SYNTHETIC)
								b2 = true;
						}
					}
					if (b1) {
						DCAdvancementUtil.unlock(player, "climate_wear");
					}
					if (b2) {
						DCAdvancementUtil.unlock(player, "climate_wear2");
					}

					// warp process
					if (DCDimChangeHelper.INSTANCE.inWarpProcess(player)) {
						DCDimChangeHelper.INSTANCE.warp(player);
					}
				}
			}
		}
	}

	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (entity != null && !entity.world.isRemote) {
			if (entity.isPotionActive(MainInit.gravity) && entity.isInWater()) {
				entity.motionY -= 0.1D * entity.getEntityAttribute(EntityLivingBase.SWIM_SPEED).getAttributeValue();
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
					if (dist < 32.0D && MainUtil.isHeldItem(MainInit.entityScope, p)) {
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
				// else if (ModuleConfig.magic && MainUtil.getCharmLevel(entity, new ItemStack(MagicInit.colorRing2, 1,
				// 3)) > 0) {
				// if (!ClimateMain.proxy.isSneakKeyDown()) {
				// int x = MathHelper.floor(player.posX);
				// int y = MathHelper.floor(player.posY - 0.5D);
				// int z = MathHelper.floor(player.posZ);
				// boolean f = false;
				// for (int i = 0; i < 2; i++) {
				// for (int j = 0; j < 2; j++) {
				// BlockPos p = new BlockPos(x + i, y, z + j);
				// if (player.world.getBlockState(p).getCollisionBoundingBox(player.world, p) != null) {
				// f = true;
				// }
				// }
				// }
				//
				// if (f) {
				// player.motionY = 0.0D;
				// }
				// }
				// }
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void onPlayerToolClientUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (entity != null && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			World world = player.world;

			if ((!DCUtil.isEmpty(player.getHeldItemMainhand()) && player.getHeldItemMainhand()
					.getItem() == MainInit.scope) || (!DCUtil.isEmpty(player.getHeldItemOffhand()) && player
							.getHeldItemOffhand().getItem() == MainInit.scope)) {

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
										Particle shock = new ParticleTempColor.Factory()
												.createParticle(0, world, px, py, pz, 0D, 0D, 0D, h1.getColor());
										FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);
									}
								}
							} else {
								DCHeatTier h1 = ClimateAPI.registerBlock.getHeatTier(state.getBlock(), state.getBlock()
										.getMetaFromState(state));
								if (h1 != DCHeatTier.NORMAL) {
									IBlockState s2 = world.getBlockState(mpos.up());
									if (!s2.getBlock().isSideSolid(s2, world, mpos.up(), EnumFacing.DOWN)) {
										double px = mpos.getX() + 0.5D;
										double py = mpos.getY() + 0.5D;
										double pz = mpos.getZ() + 0.5D;
										Particle shock = new ParticleTempColor.Factory()
												.createParticle(0, world, px, py, pz, 0D, 0D, 0D, h1.getColor());
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

	/**
	 * 睡眠イベント
	 *
	 * @Author flammpfell 氏のgistを参考にしたもの
	 * @date 2019.11.16
	 */

	// @SubscribeEvent
	public void trySleep(PlayerSleepInBedEvent event) {

		if (event.getEntityPlayer() != null && event.getEntityPlayer().getEntityWorld().isBlockLoaded(event.getPos())) {
			IBlockState state = event.getEntityPlayer().getEntityWorld().getBlockState(event.getPos());

			if (state.getBlock() instanceof BlockBedDC) {

				EnumFacing face = state.getValue(BlockHorizontal.FACING);

				if (!event.getEntityPlayer().getEntityWorld().isRemote) {
					if (event.getEntityPlayer().isPlayerSleeping() || !event.getEntityPlayer().isEntityAlive()) {
						event.setResult(EntityPlayer.SleepResult.OTHER_PROBLEM);
						return;
					}

					double d2 = 8.0D;
					double d1 = 5.0D;
					List<EntityMob> list = event.getEntityPlayer().getEntityWorld()
							.<EntityMob>getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(event.getPos()
									.getX() - d1, event.getPos().getY() - d2, event.getPos().getZ() - d1, event.getPos()
											.getX() + d1, event.getPos().getY() + d2, event.getPos()
													.getZ() + d1), null);
					if (!list.isEmpty()) {
						event.setResult(EntityPlayer.SleepResult.NOT_SAFE);
					}
				}

				if (event.getEntityPlayer().isRiding()) {
					event.getEntityPlayer().dismountRidingEntity();
				}

				if (face != null) {
					float f1 = 0.5F + face.getFrontOffsetX() * 0.4F;
					float f = 0.5F + face.getFrontOffsetZ() * 0.4F;
					event.getEntityPlayer().renderOffsetX = -1.8F * face.getFrontOffsetX();
					event.getEntityPlayer().renderOffsetZ = -1.8F * face.getFrontOffsetZ();
					event.getEntityPlayer().setPosition(event.getPos().getX() + f1, event.getPos()
							.getY() + 0.6875F, event.getPos().getZ() + f);
				} else {
					event.getEntityPlayer().setPosition(event.getPos().getX() + 0.5F, event.getPos()
							.getY() + 0.6875F, event.getPos().getZ() + 0.5F);
				}

				try {
					Field f1 = ReflectionHelper.findField(EntityPlayer.class, "sleeping");
					f1.setAccessible(true);
					// Field f2 = ReflectionHelper.findField(EntityPlayer.class, "sleepTimer");
					// f2.setAccessible(true);
					f1.set(event.getEntityPlayer(), true);
					// f2.set(event.getEntityPlayer(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}

				event.getEntityPlayer().bedLocation = event.getPos();
				event.getEntityPlayer().motionX = 0.0D;
				event.getEntityPlayer().motionY = 0.0D;
				event.getEntityPlayer().motionZ = 0.0D;

				if (!event.getEntityPlayer().world.isRemote) {
					event.getEntityPlayer().world.updateAllPlayersSleepingFlag();
				}

				event.setResult(EntityPlayer.SleepResult.OK);
				event.getEntityPlayer().getEntityData().setBoolean("doSleep", true);
			}
		}
	}

	// @SubscribeEvent
	public void inBed(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.START && !event.player.world.isRemote && event.player.getEntityData()
				.getBoolean("doSleep")) {
			ReflectionHelper.setPrivateValue(EntityPlayer.class, event.player, false, "sleeping");
		}
	}

	// @SubscribeEvent
	public void inBed2(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END && !event.player.world.isRemote && event.player.getEntityData()
				.getBoolean("doSleep")) {
			ReflectionHelper.setPrivateValue(EntityPlayer.class, event.player, true, "sleeping");
		}
	}

	@SubscribeEvent
	public void onWakeUp(PlayerSetSpawnEvent event) {
		if (event.getEntityPlayer() != null && event.getNewSpawn() != null) {
			IBlockState state = event.getEntityPlayer().getEntityWorld().getBlockState(event.getNewSpawn());
			if (state != null && state.getBlock() instanceof BlockBedDC) {
				event.setCanceled(true);
			}
		}
	}

	// @SubscribeEvent
	public void onWakeUp2(PlayerWakeUpEvent event) {
		event.getEntityLiving().getEntityData().removeTag("doSleep");
	}
}
