package defeatedcrow.hac.magic.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLivingDC extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate",
			"textures/gui/inv_living_gui.png");
	private final EntityLiving living;
	private final InventoryPlayer playerInventory;

	public GuiLivingDC(EntityLiving livingIn, EntityPlayer player) {
		super(new ContainerLivingDC(livingIn, player));
		this.living = livingIn;
		this.playerInventory = player.inventory;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		GuiInventory
				.drawEntityOnScreen(i + 90, j + 80, 30, (float) (i + 90) - mouseX, (float) (j + 95 - 50) - mouseY, this.living);
	}
}
