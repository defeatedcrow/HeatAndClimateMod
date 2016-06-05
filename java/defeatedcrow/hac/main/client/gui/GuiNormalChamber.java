package defeatedcrow.hac.main.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.main.block.device.TileNormalChamber;

@SideOnly(Side.CLIENT)
public class GuiNormalChamber extends GuiContainer {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate", "textures/gui/chamber.normal.gui.png");
	/** The player inventory bound to this GUI. */
	private final InventoryPlayer playerInventory;
	private final TileNormalChamber chamber;

	public GuiNormalChamber(TileNormalChamber cham, InventoryPlayer playerInv) {
		super(new ContainerNormalChamber(cham, playerInv));
		this.playerInventory = playerInv;
		this.chamber = cham;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.chamber.getName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (this.chamber.isActive()) {
			if (this.chamber.getCurrentHeatID() > 5) {
				this.drawTexturedModalRect(i + 75, j + 23, 176, 0, 26, 26);
			} else {
				this.drawTexturedModalRect(i + 75, j + 23, 176, 26, 26, 26);
			}
		}

		int l = this.getCookProgressScaled(32);
		this.drawTexturedModalRect(i + 72, j + 51, 202, 0, 32 - l, 3);
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.chamber.getField(0);
		int j = this.chamber.getField(1);
		return j != 0 && i != 0 ? pixels - (i * pixels / j) : pixels;
	}
}
