package defeatedcrow.hac.main.client;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AdvancedHUDEvent {

	public static final AdvancedHUDEvent INSTANCE = new AdvancedHUDEvent();
	public static final ResourceLocation TEX = new ResourceLocation(ClimateCore.PACKAGE_ID,
			"textures/gui/hud_climate_adv.png");

	private int climate = 0;
	private int count = 20;

	public static boolean enable = MainCoreConfig.enableAdvHUD;

	public static boolean hasAcv = ClimateCore.isDebug;

	public static boolean active = true;

	@SubscribeEvent
	public void doRender(RenderGameOverlayEvent.Post event) {
		if (event.getType() != null && event.getType() == ElementType.ALL) {
			EntityPlayer player = ClimateCore.proxy.getPlayer();
			World world = ClimateCore.proxy.getClientWorld();
			GuiScreen gui = Minecraft.getMinecraft().currentScreen;
			if (player != null && world != null && gui == null) {
				if (enable) {
					if (count == 0) {
						active = true;
						count = 10;

						// if (!hasAcv) {
						// if (player instanceof EntityPlayerSP) {
						// if (((EntityPlayerSP) player).getStatFileWriter()
						// .hasAchievementUnlocked(AchievementClimate.CLIMATE_MASTER)) {
						// hasAcv = true;
						// }
						// }
						// }

						/* 10Fごとに使用データを更新 */

						if (hasAcv) {
							int px = MathHelper.floor_double(player.posX);
							int py = MathHelper.floor_double(player.posY);
							int pz = MathHelper.floor_double(player.posZ);
							BlockPos pos = new BlockPos(px, py, pz);
							if (pos != null && world.isAreaLoaded(pos.add(-2, -2, -2), pos.add(2, 2, 2))) {
								IClimate id = ClimateAPI.calculator.getClimate(world, pos);
								if (id != null) {
									climate = id.getClimateInt();
								}
							}
						}

					} else {
						active = false;
						count--;
					}

					if (hasAcv) {
						IClimate clm = ClimateAPI.register.getClimateFromInt(climate);
						int we = WeatherChecker.getTempOffset(world.provider.getDimension(), false);

						Minecraft.getMinecraft().getTextureManager().bindTexture(TEX);
						FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						GlStateManager.enableBlend();

						int iw = 0;
						if (we > 0) {
							iw = 32;
						} else if (we < 0) {
							iw = 64;
						}
						int offsetX = MainCoreConfig.iconX;
						int offsetY = MainCoreConfig.iconY;
						int x = offsetX;
						int y = event.getResolution().getScaledHeight() + offsetY;
						drawTexturedModalRect(x, y, iw, 0, iw + 16, 64);

						String temp = clm.getHeat().toString();
						String hum = clm.getHumidity().toString();
						String air = clm.getAirflow().toString();

						fr.drawString(temp, x + 10, y + 15, clm.getHeat().getColorInt(), true);
						fr.drawString(hum, x + 10, y + 25, clm.getHumidity().getColorInt(), true);
						fr.drawString(air, x + 10, y + 35, clm.getAirflow().getColorInt(), true);

						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					}
				}
			}
		}
	}

	public void drawTexturedModalRect(int x, int y, int tX, int tY, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos(x + 0, y + height, -90.0F).tex((tX + 0) * f, (tY + height) * f1).endVertex();
		vertexbuffer.pos(x + width, y + height, -90.0F).tex((tX + width) * f, (tY + height) * f1).endVertex();
		vertexbuffer.pos(x + width, y + 0, -90.0F).tex((tX + width) * f, (tY + 0) * f1).endVertex();
		vertexbuffer.pos(x + 0, y + 0, -90.0F).tex((tX + 0) * f, (tY + 0) * f1).endVertex();
		tessellator.draw();
	}

}
