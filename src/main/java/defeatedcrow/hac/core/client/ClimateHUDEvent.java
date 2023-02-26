package defeatedcrow.hac.core.client;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.climate.ClientClimateData;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.core.config.ConfigClientBuilder;
import defeatedcrow.hac.core.util.DCTexturePath;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@OnlyIn(Dist.CLIENT)
public class ClimateHUDEvent {

	private static int count = 20;
	private static Holder<Biome> biome = null;
	private static ResourceKey<Biome> biomeName = Biomes.PLAINS;
	private static IForgeRegistry<Biome> reg = ForgeRegistries.BIOMES;

	public static boolean hasAcv = true;
	public static boolean active = true;

	private static boolean type = false;
	private static int offsetX = 0;
	private static int offsetY = 0;
	private static boolean useBiomeName = true;

	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void render(RenderGuiOverlayEvent.Post event) {
		if (event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())) {
			LocalPlayer player = Minecraft.getInstance().player;
			Level world = Minecraft.getInstance().level;
			PoseStack pose = event.getPoseStack();
			if (player != null && world != null && !Minecraft.getInstance().options.hideGui) {
				if (active) {
					if (count == 0) {
						count = 10;

						if (!hasAcv) {
							hasAcv = true;
						}

						/* 10Fごとに使用データを更新 */

						if (hasAcv) {
							BlockPos pos = player.blockPosition();
							if (pos != null) {
								biome = world.getBiome(pos);
							}
						}

					} else {
						count--;
					}

					if (ConfigClientBuilder.INSTANCE.showHUD.get() && hasAcv && biome != null && biome.get() != null && ClientClimateData.INSTANCE.getClimate() != null) {
						IClimate clm = ClientClimateData.INSTANCE.getClimate();
						float we = WeatherChecker.getTempOffsetFloat(world.dimension().location(), false);

						Font fr = Minecraft.getInstance().font;

						RenderSystem.setShader(GameRenderer::getPositionTexShader);
						RenderSystem.setShaderTexture(0, DCTexturePath.HUD.getLocation());
						RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
						RenderSystem.enableBlend();
						RenderSystem.defaultBlendFunc();

						// 画面サイズに合わせる
						int offsetX = ConfigClientBuilder.INSTANCE.offset_x.get();
						int offsetY = ConfigClientBuilder.INSTANCE.offset_y.get();
						int x = offsetX;
						int y = offsetY;

						int fontX = 0;
						int fontY = 0;

						/*
						 * 時計ベース -> 温度 -> 湿度・通気 -> 天候 -> 時刻 -> ダメージハート の順
						 */

						// flag
						int icon = ConfigClientBuilder.INSTANCE.HUD_type;
						boolean showIcon = icon != 4;
						boolean showClimate = icon == 0 || icon == 2;
						boolean showDate = icon == 0 || icon == 1;

						// ベース部分
						if (showIcon) {
							int i1 = ConfigClientBuilder.INSTANCE.HUD_c.get();
							drawTexturedModalRect(pose.last().pose(), x, y, i1 * 32, 96, 32, 32);

							// heat
							int i2 = clm.getHeat().getID();
							if (i2 < DCHeatTier.KILN.getID()) {
								drawTexturedModalRect(pose.last().pose(), x + 6, y + 3, i2 * 23, 35, 23, 26);
							} else {
								i2 -= 10;
								drawTexturedModalRect(pose.last().pose(), x + 6, y + 3, i1 * 23, 61, 23, 26);
							}

							// hum
							int i3 = clm.getHumidity().getID();
							drawTexturedModalRect(pose.last().pose(), x + 7, y + 10, 112 + i3 * 3, 0, 3, 3);

							// air
							int i4 = clm.getAirflow().getID();
							drawTexturedModalRect(pose.last().pose(), x + 15, y + 10, 112 + i4 * 3, 3, 3, 3);

							// weather
							int i5 = 0;
							if (!DCTimeHelper.isDayTime(world)) {
								i5 += 7;
							}
							if (we < 0) {
								i5 += 14;
							}
							drawTexturedModalRect(pose.last().pose(), x + 9, y + 16, 32 + i5, 0, 7, 7);

							// hour
							int i6 = DCTimeHelper.currentTime(world);
							if (i6 >= 12) {
								i6 -= 12;
							}
							drawTexturedModalRect(pose.last().pose(), x + 4, y + 6, 32 + i6 * 17, 10, 17, 17);

							// damage icon
							int i7 = ClientClimateData.INSTANCE.getIconTier();
							drawTexturedModalRect(pose.last().pose(), x + 24, y + 26, 64 + i7 * 9, 0, 9, 9);

							if (ConfigClientBuilder.INSTANCE.showDamEffect.get()) {
								if (i7 > 2) {
									renderOverlay(DCTexturePath.HOT_DISP.getLocation(), 0.5F, event.getWindow().getGuiScaledWidth(), event.getWindow().getGuiScaledHeight());
								} else if (i7 < 2) {
									renderOverlay(DCTexturePath.COLD_DISP.getLocation(), 0.5F, event.getWindow().getGuiScaledWidth(), event.getWindow().getGuiScaledHeight());
								}
							}
						}

						RenderSystem.disableBlend();

						// date
						if (showDate) {
							String biomeName = printBiome(biome);
							if (biomeName != null) {
								float f = biome.get().getBaseTemperature();
								if (ClimateCore.isDebug)
									biomeName += " " + String.format("%.2f", f);
								fr.width(biomeName);
								fr.drawShadow(pose, biomeName, x, y - 30, 0xffffff);
							}

							int c2 = 0xFFFFFF;
							EnumSeason season = DCTimeHelper.getClientSeason();
							MutableComponent sN = season.getFullname();
							c2 = season.color.getTextColor();
							fr.width(sN);
							fr.drawShadow(pose, sN, x, y - 20, c2);

							String dN = DCTimeHelper.getDateDisp();
							fr.width(dN);
							fr.drawShadow(pose, dN, x, y - 10, 0xFFFFFF);
						}

						// climate
						if (showClimate) {
							MutableComponent tN = clm.getHeat().localize();
							int tC = clm.getHeat().getColorInt();
							MutableComponent hN = clm.getHumidity().localize();
							int hC = clm.getHumidity().getColorInt();
							MutableComponent aN = clm.getAirflow().localize();
							int aC = clm.getAirflow().getColorInt();
							fr.width(tN);
							fr.drawShadow(pose, tN, x + 34, y + 2, tC);
							fr.width(hN);
							fr.drawShadow(pose, hN, x + 34, y + 12, hC);
							fr.width(aN);
							fr.drawShadow(pose, aN, x + 34, y + 22, aC);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void renderScreen(ScreenEvent.Render.Post event) {
		if (event.getScreen() != null && event.getScreen() instanceof InventoryScreen) {
			InventoryScreen gui = (InventoryScreen) event.getScreen();
			int x = event.getMouseX() - gui.getGuiLeft();
			int y = event.getMouseY() - gui.getGuiTop();

			if (gui.getSlotUnderMouse() != null && gui.getSlotUnderMouse().hasItem()) {
				return;
			}

			if (ConfigClientBuilder.INSTANCE.showHUD.get()) {
				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				RenderSystem.setShaderTexture(0, DCTexturePath.HUD.getLocation());
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
				RenderSystem.enableBlend();
				RenderSystem.defaultBlendFunc();

				int i7 = ClientClimateData.INSTANCE.getIconTier();
				drawTexturedModalRect(event.getPoseStack().last().pose(), gui.getGuiLeft() + 26, gui.getGuiTop() + 8, 64 + i7 * 9, 0, 9, 9);

				if (x > 26 && x < 32 && y > 8 && y < 14) {
					List<Component> list = Lists.newArrayList();
					list.add(Component.literal("Armor Heat Resistant"));
					list.add(Component.literal("Heat: " + String.format("%.1fF", ClientClimateData.INSTANCE.getArmorHeatPrev())));
					list.add(Component.literal("Cold: " + String.format("%.1fF", ClientClimateData.INSTANCE.getArmorColdPrev())));
					gui.renderComponentTooltip(event.getPoseStack(), list, event.getMouseX(), event.getMouseY());
				}
			}
		}
	}

	private static void drawTexturedModalRect(Matrix4f mat, int x, int y, int tX, int tY, int wid, int hei) {
		float f = 1F / 256F;
		float f1 = 1F / 256F;
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(mat, x + 0, y + hei, -90.0F).uv((tX + 0) * f, (tY + hei) * f1).endVertex();
		bufferbuilder.vertex(mat, x + wid, y + hei, -90.0F).uv((tX + wid) * f, (tY + hei) * f1).endVertex();
		bufferbuilder.vertex(mat, x + wid, y + 0, -90.0F).uv((tX + wid) * f, (tY + 0) * f1).endVertex();
		bufferbuilder.vertex(mat, x + 0, y + 0, -90.0F).uv((tX + 0) * f, (tY + 0) * f1).endVertex();
		Tesselator.getInstance().end();
	}

	protected static void renderOverlay(ResourceLocation tex, float f, int wid, int hei) {
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, f);
		RenderSystem.setShaderTexture(0, tex);
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(0.0D, hei, -90.0D).uv(0.0F, 1.0F).endVertex();
		bufferbuilder.vertex(wid, hei, -90.0D).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(wid, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
		tesselator.end();
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}

	private static String printBiome(Holder<Biome> biome) {
		return biome.unwrap().map((res) -> {
			return res.location().getPath();
		}, (b) -> {
			return "[unregistered " + b + "]";
		});
	}

}
