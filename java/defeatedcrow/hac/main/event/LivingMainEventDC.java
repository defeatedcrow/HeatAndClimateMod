package defeatedcrow.hac.main.event;

import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.AdvancedHUDEvent;
import defeatedcrow.hac.main.client.particle.ParticleTempColor;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// 主にclient側のプレイヤー操作にかかわるもの
public class LivingMainEventDC {

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
		if (entity != null && entity instanceof EntityPlayer && !entity.isRiding()) {
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
				if (player.inventory.hasItemStack(new ItemStack(MagicInit.pendant, 1, 10))
						&& player.isPotionActive(MobEffects.SPEED)) {
					player.stepHeight = 1.0F;
				} else {
					player.stepHeight = 0.6F;
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
				if (player.isPotionActive(MainInit.ocean) && player.isInWater()) {
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
						if (d < 1.0D) {
							player.motionX += vec3d.x * 0.1D;
							player.motionZ += vec3d.z * 0.1D;
						}
					}
				} else if (player.isPotionActive(MainInit.bird)) {
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
							if (d < 1.0D) {
								player.motionX += vec3d.x * 0.05D;
								player.motionZ += vec3d.z * 0.05D;
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

			if ((!DCUtil.isEmpty(player.getHeldItemMainhand())
					&& player.getHeldItemMainhand().getItem() == MainInit.scope)
					|| (!DCUtil.isEmpty(player.getHeldItemOffhand())
							&& player.getHeldItemOffhand().getItem() == MainInit.scope)) {

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
										Particle shock = new ParticleTempColor.Factory().createParticle(0, world, px,
												py, pz, 0D, 0D, 0D, h1.getColor());
										FMLClientHandler.instance().getClient().effectRenderer.addEffect(shock);
									}
								}
							} else {
								DCHeatTier h1 = ClimateAPI.registerBlock.getHeatTier(state.getBlock(),
										state.getBlock().getMetaFromState(state));
								if (h1 != DCHeatTier.NORMAL) {
									IBlockState s2 = world.getBlockState(mpos.up());
									if (!s2.getBlock().isSideSolid(s2, world, mpos.up(), EnumFacing.DOWN)) {
										double px = mpos.getX() + 0.5D;
										double py = mpos.getY() + 0.5D;
										double pz = mpos.getZ() + 0.5D;
										Particle shock = new ParticleTempColor.Factory().createParticle(0, world, px,
												py, pz, 0D, 0D, 0D, h1.getColor());
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
