package defeatedcrow.hac.main.client.gui;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.main.block.device.TileCraftingCounter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCraftingCounter extends GuiBaseDC {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate",
			"textures/gui/crafting_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileCraftingCounter shelf;

	public GuiCraftingCounter(TileCraftingCounter te, EntityPlayer player, World world, BlockPos pos) {
		super(new ContainerCraftingCounter(te, player, world, pos));
		this.playerInventory = player.inventory;
		this.shelf = te;
		this.xSize = 182;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(I18n.format("container.crafting"), 28, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
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
	}
}
