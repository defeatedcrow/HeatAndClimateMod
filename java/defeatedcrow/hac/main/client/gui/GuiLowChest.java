package defeatedcrow.hac.main.client.gui;

import defeatedcrow.hac.main.block.build.TileLowChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLowChest extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
	private final IInventory chest;
	private final InventoryPlayer playerInventory;

	public GuiLowChest(TileLowChest inv, EntityPlayer player) {
		super(new ContainerLowChest(inv, player));
		this.chest = inv;
		this.playerInventory = player.inventory;
		int i = 222;
		int j = 114;
		this.ySize = 114 + 6 * 18;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.chest.getName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8,
				this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		int c = 6;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, c * 18 + 17);
		this.drawTexturedModalRect(i, j + c * 18 + 17, 0, 126, this.xSize, 96);
	}
}
