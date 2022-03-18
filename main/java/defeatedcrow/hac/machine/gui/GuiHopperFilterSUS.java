package defeatedcrow.hac.machine.gui;

import java.io.IOException;
import java.util.ArrayList;

import defeatedcrow.hac.machine.block.TileHopperFilterSUS;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageUniHopperButton;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHopperFilterSUS extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate",
			"textures/gui/hoppersus_gui.png");
	private final TileHopperFilterSUS chest;
	private final InventoryPlayer playerInventory;

	private final int buttonX = 147;
	private final int buttonY = 5;

	public GuiHopperFilterSUS(TileHopperFilterSUS inv, EntityPlayer player) {
		super(new ContainerHopperFilterSUS(inv, player));
		this.chest = inv;
		this.playerInventory = player.inventory;
		this.allowUserInput = false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.chest.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName()
				.getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		ArrayList<String> list = new ArrayList<String>();
		// if (ClimateCore.isDebug) {
		// int i = mouseX - this.guiLeft;
		// int j = mouseY - this.guiTop;
		// list.add("Point:" + i + ", " + j);
		// }
		if (isPointInRegion(buttonX, buttonY, 24, 13, mouseX, mouseY)) {
			String s1 = chest.getField(0) == 0 ? "NORMAL MODE" : "UNIFIER MODE";
			list.add(s1);
		}
		this.drawHoveringText(list, mouseX, mouseY);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (this.chest.getField(0) > 0) {
			this.drawTexturedModalRect(i + buttonX, j + buttonY, 176, 13, 24, 13);
		} else {
			this.drawTexturedModalRect(i + buttonX, j + buttonY, 176, 0, 24, 13);
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton) throws IOException {
		super.mouseClicked(x, y, mouseButton);
		boolean flag = this.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(mouseButton - 100);
		if (mouseButton == 0 || mouseButton == 1 || flag) {
			int i = x - this.guiLeft;
			int j = y - this.guiTop;
			int n = 0;
			if (isPointInRegion(buttonX, buttonY, 24, 13, x, y)) {
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageUniHopperButton(chest.getPos(), true));
			}
		}
	}

}
