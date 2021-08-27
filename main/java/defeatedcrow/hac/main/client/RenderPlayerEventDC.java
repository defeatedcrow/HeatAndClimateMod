package defeatedcrow.hac.main.client;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.magic.client.ModelMagicalWing;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.model.ModelPanel;
import net.minecraft.client.entity.AbstractClientPlayer;
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
	private static final ResourceLocation WING_TEX2 = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magical_wings_r.png");
	private static final ResourceLocation TAIL_TEX = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/entity/magic/magical_fishtail.png");
	private static final ModelMagicalWing MODEL = new ModelMagicalWing();
	private static final ModelPanel PANEL_MODEL = new ModelPanel();

	private float durB = 0;
	private float lastDurB = 0;
	private float durO;
	private float lastDurO = 0;

	@SubscribeEvent
	public void renderWings(RenderPlayerEvent.Post event) {
		EntityPlayer player = event.getEntityPlayer();
		float partial = event.getPartialRenderTick();
		if (player != null && player instanceof AbstractClientPlayer && !player.isElytraFlying()) {
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
					if (effB.getDuration() < 0) {
						player.removeActivePotionEffect(MainInit.bird);
					} else {
						float angle = 8.0F;

						if (jump || forward) {
							lastDurB = durB;
							durB = player.world.getWorldTime() % 16;
							durB /= 8F;
							float fb = this.interpolateRotation(lastDurB, durB, partial);
							double sin = Math.sin(fb * Math.PI);
							angle = (float) (sin * 8.0F);
						}

						GlStateManager.pushMatrix();
						GlStateManager.translate((float) x, (float) y, (float) z);
						GlStateManager.rotate(180F - f, 0.0F, 1.0F, 0.0F);
						GlStateManager.enableBlend();
						GlStateManager.disableLighting();
						GlStateManager
								.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

						int i = 15728880;
						int j = i % 65536;
						int k = i / 65536;
						OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j, k);

						GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
						if (ClimateCore.isDebug)
							event.getRenderer().bindTexture(WING_TEX2);
						else
							event.getRenderer().bindTexture(WING_TEX);

						GlStateManager.scale(-1.2F, -1.2F, 1.2F);
						MODEL.renderWing(player, 0.0625F, sneak, angle);

						GlStateManager.enableLighting();
						GlStateManager.disableBlend();
						GlStateManager.popMatrix();
					}
				}
			}
			if (player.isPotionActive(MainInit.ocean)) {
				PotionEffect effO = player.getActivePotionEffect(MainInit.ocean);
				if (effO != null) {
					if (effO.getDuration() < 0) {
						player.removeActivePotionEffect(MainInit.ocean);
					} else {
						float angle = 4.0F;
						if (forward) {
							lastDurO = durO;
							durO = player.world.getWorldTime() % 16;
							durO /= 8F;
							float fb = this.interpolateRotation(lastDurB, durB, partial);
							double sin = Math.sin(fb * Math.PI);
							angle = (float) (sin * 4.0F);
						}

						GlStateManager.pushMatrix();
						GlStateManager.translate((float) x, (float) y, (float) z);
						GlStateManager.rotate(180F - f, 0.0F, 1.0F, 0.0F);
						GlStateManager.enableBlend();
						GlStateManager.disableLighting();
						GlStateManager
								.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

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
	}

	private static String[] name = {
			"absolute",
			"cryogenic",
			"frostbite",
			"cold",
			"cool",
			"normal",
			"warm",
			"hot",
			"boil",
			"oven",
			"kiln",
			"smelting",
			"uht",
			"inferno"
	};

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
