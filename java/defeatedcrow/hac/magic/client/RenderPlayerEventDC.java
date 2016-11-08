package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPlayerEventDC {

	private static final ResourceLocation WING_TEX = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magical_wings.png");
	private static final ResourceLocation TAIL_TEX = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magical_fishtail.png");
	private static final ModelMagicalWing MODEL = new ModelMagicalWing();

	private int lastDurB = 0;
	private int lastDurO = 0;

	@SubscribeEvent
	public void renderWings(RenderPlayerEvent.Post event) {
		EntityPlayer player = event.getEntityPlayer();
		float partial = event.getPartialRenderTick();
		if (player != null && player instanceof EntityPlayerSP && !player.isElytraFlying()) {
			float f = this.interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, partial);
			float f1 = this.interpolateRotation(player.prevRotationYawHead, player.rotationYawHead, partial);
			double x = event.getX();
			double y = event.getY() + player.height * 0.85F;
			double z = event.getZ();
			boolean forward = ClimateMain.proxy.isForwardKeyDown();
			boolean sneak = ClimateMain.proxy.isSneakKeyDown();
			boolean jump = ClimateCore.proxy.isJumpKeyDown();

			if (player.isPotionActive(MainInit.bird) && !player.isInWater()) {
				PotionEffect effB = player.getActivePotionEffect(MainInit.bird);
				if (effB != null) {
					int dur = effB.getDuration() & 7;
					boolean b = (effB.getDuration() & 8) == 0;
					float angle = 8.0F;
					if (jump) {
						angle = lastDurB * 1.0F + (dur - lastDurB) * partial;
						if (b) {
							angle = 8.0F - angle;
						}
					}
					lastDurB = dur;

					GlStateManager.pushMatrix();
					GlStateManager.translate((float) x, (float) y, (float) z);
					GlStateManager.rotate(180F - f, 0.0F, 1.0F, 0.0F);
					GlStateManager.enableBlend();
					GlStateManager.disableLighting();
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
							GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
							GlStateManager.DestFactor.ZERO);

					int i = 15728880;
					int j = i % 65536;
					int k = i / 65536;
					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);

					GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
					event.getRenderer().bindTexture(WING_TEX);

					GlStateManager.scale(-1.2F, -1.2F, 1.2F);
					MODEL.renderWing(player, 0.0625F, sneak, angle);

					GlStateManager.enableLighting();
					GlStateManager.disableBlend();
					GlStateManager.popMatrix();
				}
			}
			if (player.isPotionActive(MainInit.ocean)) {
				PotionEffect effO = player.getActivePotionEffect(MainInit.ocean);
				if (effO != null) {
					int dur = effO.getDuration() & 7;
					boolean b = (effO.getDuration() & 8) == 0;
					float angle = 4.0F;
					if (forward) {
						angle = lastDurO * 1.0F + (dur - lastDurO) * partial;
						if (b) {
							angle = 8.0F - angle;
						}
					}
					lastDurO = dur;

					GlStateManager.pushMatrix();
					GlStateManager.translate((float) x, (float) y, (float) z);
					GlStateManager.rotate(180F - f, 0.0F, 1.0F, 0.0F);
					GlStateManager.enableBlend();
					GlStateManager.disableLighting();
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
							GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
							GlStateManager.DestFactor.ZERO);

					int i = 15728880;
					int j = i % 65536;
					int k = i / 65536;
					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);

					GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
					event.getRenderer().bindTexture(TAIL_TEX);

					GlStateManager.scale(-1.2F, -1.2F, 1.2F);
					MODEL.renderTail(player, 0.0625F, false, angle);

					GlStateManager.enableLighting();
					GlStateManager.disableBlend();
					GlStateManager.popMatrix();
				}
			}
		}
	}

	private float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
		float f = yawOffset - prevYawOffset;

		while (f < -180.0F) {
			f += 360.0F;
		}

		while (f >= 180.0F) {
			f -= 360.0F;
		}

		return prevYawOffset + partialTicks * f;
	}

}
