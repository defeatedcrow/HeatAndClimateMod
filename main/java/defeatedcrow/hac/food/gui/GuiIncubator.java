package defeatedcrow.hac.food.gui;

import java.io.IOException;
import java.util.ArrayList;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.food.block.TileIncubator;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIncubator extends GuiContainer {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/incubator_gui.png");
	private static final ResourceLocation iconTex = new ResourceLocation("dcs_climate", "textures/gui/gui_icons.png");
	private final InventoryPlayer playerInventory;
	private final TileIncubator tile;

	public GuiIncubator(TileIncubator te, InventoryPlayer playerInv) {
		super(new ContainerIncubator(te, playerInv));
		this.playerInventory = playerInv;
		this.tile = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.tile.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 3, 4210752);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, par3);

		ArrayList<String> list = new ArrayList<String>();
		// String hov = "point " + (x - this.guiLeft) + "," + (y - this.guiTop);
		// list.add(hov);

		if (tile != null && tile.getClimate() != null) {
			IClimate clm = tile.getClimate();
			if (isPointInRegion(5, 25, 50, 40, x, y)) {
				list.add(I18n.format("dcs.tip.incubator_gui"));
			}

			if (isPointInRegion(12, 28, 40, 6, x, y)) {
				if (clm != null) {
					DCHeatTier h = clm.getHeat();
					list.add(h.toString());
				}
			}

			if (isPointInRegion(12, 42, 40, 6, x, y)) {
				if (clm != null) {
					DCHumidity h = clm.getHumidity();
					list.add(h.toString());
				}
			}

			if (isPointInRegion(12, 56, 40, 6, x, y)) {
				if (clm != null) {
					DCAirflow h = clm.getAirflow();
					list.add(h.toString());
				}
			}
		}

		if (!list.isEmpty())
			this.drawHoveringText(list, x, y);
		this.renderHoveredToolTip(x, y);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (tile != null && tile.getClimate() != null) {
			IClimate clm = tile.getClimate();
			this.mc.getTextureManager().bindTexture(iconTex);

			if (clm != null) {
				int cl = clm.getHeat().getID() * 3;
				this.drawTexturedModalRect(i + 10 + cl, j + 25, 1, 25, 5, 10);

				int ch = clm.getHumidity().getID() * 11;
				this.drawTexturedModalRect(i + 13 + ch, j + 39, 1, 25, 5, 10);

				int ca = clm.getAirflow().getID() * 11;
				this.drawTexturedModalRect(i + 13 + ca, j + 53, 1, 25, 5, 10);
			}

		}
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton) throws IOException {
		super.mouseClicked(x, y, mouseButton);
		boolean flag = this.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(mouseButton - 100);
		if (mouseButton == 0 || mouseButton == 1 || flag) {
			if (isPointInRegion(10, 20, 40, 60, x, y)) {
				DCHeatTier heat = tile.getClimate().getHeat();
				DCHumidity hum = tile.getClimate().getHumidity();
				DCAirflow air = tile.getClimate().getAirflow();

				if (isPointInRegion(10, 28, 40, 6, x, y)) {
					int c = (x - 10 - this.guiLeft) / 3;
					if (c > 13)
						c = 13;
					heat = DCHeatTier.getTypeByID(c);
				}

				if (isPointInRegion(10, 42, 40, 6, x, y)) {
					int c = (x - 10 - this.guiLeft) / 10;
					if (c > 3)
						c = 3;
					hum = DCHumidity.getTypeByID(c);
				}

				if (isPointInRegion(10, 56, 40, 6, x, y)) {
					int c = (x - 10 - this.guiLeft) / 10;
					if (c > 3)
						c = 3;
					air = DCAirflow.getTypeByID(c);
				}

				IClimate ret = ClimateAPI.register.getClimateFromParam(heat, hum, air);
				if (ret != tile.getClimate()) {
					mc.getSoundHandler().playSound(PositionedSoundRecord
							.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
					tile.setClimateAndPacket(ret);
				}
			}
		}
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}

}
