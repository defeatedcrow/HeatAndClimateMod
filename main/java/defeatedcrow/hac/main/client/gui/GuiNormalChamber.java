package defeatedcrow.hac.main.client.gui;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.main.block.device.TileChamberBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiNormalChamber extends GuiContainer {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/chamber_main_gui.png");
	private static final ResourceLocation iconTex = new ResourceLocation("dcs_climate", "textures/gui/gui_icons.png");
	/** The player inventory bound to this GUI. */
	private final InventoryPlayer playerInventory;
	private final TileChamberBase chamber;

	public GuiNormalChamber(TileChamberBase cham, InventoryPlayer playerInv) {
		super(new ContainerNormalChamber(cham, playerInv));
		this.playerInventory = playerInv;
		this.chamber = cham;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.chamber.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName()
				.getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);

		List<String> list = new ArrayList<String>();
		if (this.isPointInRegion(-20, 4, 20, 20, mouseX, mouseY)) {
			if (chamber != null) {
				list.addAll(chamber.climateSuitableMassage());
			}
		}
		if (this.isPointInRegion(75, 23, 26, 26, mouseX, mouseY)) {
			if (chamber != null && chamber.isActive()) {
				DCHeatTier h = DCHeatTier.getTypeByID(chamber.getCurrentHeatID());
				list.add(h.toString());
			}
		}

		if (!list.isEmpty()) {
			this.drawHoveringText(list, mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (this.chamber.isActive()) {
			if (this.chamber.getCurrentHeatID() > DCHeatTier.KILN.getID()) {
				this.drawTexturedModalRect(i + 75, j + 23, 176, 0, 26, 26);
			} else {
				this.drawTexturedModalRect(i + 75, j + 23, 176, 26, 26, 26);
			}
		}

		int l = this.getCookProgressScaled(32);
		this.drawTexturedModalRect(i + 72, j + 51, 202, 0, 32 - l, 3);

		// airflow data
		this.mc.getTextureManager().bindTexture(iconTex);
		if (chamber.isSuitableClimate()) {
			this.drawTexturedModalRect(i - 20, j + 4, 48, 40, 20, 20);
		} else {
			this.drawTexturedModalRect(i - 20, j + 4, 48, 60, 20, 20);
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.chamber.getField(0);
		int j = this.chamber.getField(1);
		return j != 0 && i != 0 ? pixels - (i * pixels / j) : pixels;
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}
}
