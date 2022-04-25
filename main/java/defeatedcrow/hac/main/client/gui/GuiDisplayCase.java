package defeatedcrow.hac.main.client.gui;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.main.block.build.TileDisplayCase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDisplayCase extends GuiBaseDC {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate", "textures/gui/display_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileDisplayCase shelf;

	public GuiDisplayCase(TileDisplayCase te, EntityPlayer player) {
		super(new ContainerDisplayCase(te, player));
		this.playerInventory = player.inventory;
		this.shelf = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.shelf.getName());
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
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.device.exclusive", this.shelf.getOwnerName()));
			}
		}
		if (!list.isEmpty()) {
			this.drawHoveringText(list, mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		this.drawTexturedModalRect(i + 79, j + 31, 5, 5, 18, 18);

		if (shelf != null && shelf.getOwner() != null) {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 0, 20, 20);
		} else {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 20, 20, 20);
		}
	}
}
