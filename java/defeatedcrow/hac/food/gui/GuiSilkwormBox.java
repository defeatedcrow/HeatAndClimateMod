package defeatedcrow.hac.food.gui;

import java.util.ArrayList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.food.block.TileSilkwormBox;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSilkwormBox extends GuiContainer {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/silkworm_box_gui.png");
	private static final ResourceLocation iconTex = new ResourceLocation("dcs_climate", "textures/gui/gui_icons.png");
	private final InventoryPlayer playerInventory;
	private final TileSilkwormBox processor;

	public GuiSilkwormBox(TileSilkwormBox te, InventoryPlayer playerInv) {
		super(new ContainerSilkwormBox(te, playerInv));
		this.playerInventory = playerInv;
		this.processor = te;
		this.ySize = 186;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.processor.getName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 3, 4210752);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, par3);

		ArrayList<String> list = new ArrayList<String>();
		// String hov = "point " + x + "," + y;
		// list.add(hov);

		if (processor != null && processor.getClimate() != null) {
			IClimate clm = processor.getClimate();

			if (this.isPointInRegion(-20, 4, 20, 20, x, y)) {
				if (clm.getHeat().getTier() < DCHeatTier.NORMAL.getTier()) {
					list.add(I18n.translateToLocal("dcs.gui.message.pottery.toocold"));
				} else if (clm.getHeat().getTier() > DCHeatTier.HOT.getTier()) {
					list.add(I18n.translateToLocal("dcs.gui.message.pottery.toohot"));
				} else {
					list.add(I18n.translateToLocal("dcs.gui.message.suitableclimate"));
				}
			}

			if (this.isPointInRegion(-20, 25, 20, 20, x, y)) {
				if (clm.getAirflow() == DCAirflow.TIGHT) {
					list.add(I18n.translateToLocal("dcs.gui.message.require.airflow"));
				} else {
					list.add(I18n.translateToLocal("dcs.gui.message.suitable"));
				}
			}
		}

		if (!list.isEmpty()) {
			this.drawHoveringText(list, x, y);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (processor != null && processor.getClimate() != null) {
			IClimate clm = processor.getClimate();
			this.mc.getTextureManager().bindTexture(iconTex);

			if (clm.getHeat().getTier() > DCHeatTier.COOL.getTier()
					&& clm.getHeat().getTier() < DCHeatTier.OVEN.getTier()) {
				this.drawTexturedModalRect(i - 20, j + 4, 48, 0, 20, 20);
			} else {
				this.drawTexturedModalRect(i - 20, j + 4, 48, 20, 20, 20);
			}

			if (clm.getAirflow() != DCAirflow.TIGHT) {
				this.drawTexturedModalRect(i - 20, j + 25, 48, 40, 20, 20);
			} else {
				this.drawTexturedModalRect(i - 20, j + 25, 48, 60, 20, 20);
			}

		}
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}

}
