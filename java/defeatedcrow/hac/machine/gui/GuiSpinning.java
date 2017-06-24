package defeatedcrow.hac.machine.gui;

import defeatedcrow.hac.machine.block.TileSpinningMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpinning extends GuiContainer {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/stonemill_gui.png");
	/** The player inventory bound to this GUI. */
	private final InventoryPlayer playerInventory;
	private final TileSpinningMachine mill;

	public GuiSpinning(TileSpinningMachine te, InventoryPlayer playerInv) {
		super(new ContainerSpinning(te, playerInv));
		this.playerInventory = playerInv;
		this.mill = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.mill.getName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8,
				this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		int l = this.getCookProgressScaled(20);
		if (l > 20) {
			l = 20;
		}
		this.drawTexturedModalRect(i + 78, j + 34, 176, 0, l, 12);
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.mill.getField(0);
		int j = this.mill.getField(1);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}
}
