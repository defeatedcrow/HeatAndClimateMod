package defeatedcrow.hac.main.event;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent;
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
			if (living.worldObj.isRemote) {
				this.onPlayerKeyUpdate(event);
			}
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
		}
	}

	private boolean x_key = false;

	@SideOnly(Side.CLIENT)
	public void onPlayerKeyUpdate(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (entity != null && entity instanceof EntityPlayer && !entity.isRiding()) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if (player.isPotionActive(MainInit.ocean)) {
				// ocean potion
				if (player.isInWater()) {
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
						double d1 = Math.sqrt(vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord);
						if (d < 1.0D) {
							player.motionX += vec3d.xCoord * 0.1D;
							player.motionZ += vec3d.zCoord * 0.1D;
						}
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
						double d1 = Math.sqrt(vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord);
						if (d < 1.0D) {
							player.motionX += vec3d.xCoord * 0.05D;
							player.motionZ += vec3d.zCoord * 0.05D;
						}
					}
				}
			}
		}
	}

}
