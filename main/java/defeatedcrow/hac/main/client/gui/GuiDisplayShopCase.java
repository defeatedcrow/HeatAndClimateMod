package defeatedcrow.hac.main.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.main.block.build.TileDisplayShopCase;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageDisplayCaseButton;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Owner用GUI
 */
@SideOnly(Side.CLIENT)
public class GuiDisplayShopCase extends GuiBaseDC {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate", "textures/gui/display_shop2_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileDisplayShopCase shelf;

	public GuiDisplayShopCase(TileDisplayShopCase te, EntityPlayer player) {
		super(new ContainerDisplayShopCase(te, player, false));
		this.playerInventory = player.inventory;
		this.shelf = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.shelf.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName()
				.getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

		String p1 = "" + shelf.getField(0);
		String p2 = "" + shelf.getField(1);
		String e1 = "" + shelf.getField(3);

		this.fontRenderer.drawString(p1, 46, 26, 0xFFFFFF);
		this.fontRenderer.drawString(p2, 128, 26, 0xFFFFFF);
		this.fontRenderer.drawString(e1, 105, 62, 0xFFFFFF);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);

		List<String> list = new ArrayList<String>();

		// if (ClimateCore.isDebug) {
		// int i = mouseX - this.guiLeft;
		// int j = mouseY - this.guiTop;
		// list.add("Point:" + i + ", " + j);
		// }

		if (this.isPointInRegion(-20, 4, 20, 20, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.device.exclusive", this.shelf.getOwnerName()));
			}
		}

		if (this.isPointInRegion(72, 21, 8, 5, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.price_add"));
			}
		}
		if (this.isPointInRegion(72, 30, 8, 5, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.price_red"));
			}
		}

		if (this.isPointInRegion(154, 21, 8, 5, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.price_add"));
			}
		}
		if (this.isPointInRegion(154, 30, 8, 5, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.price_red"));
			}
		}

		int p1 = shelf.getField(0);
		if (p1 > 0 && this.isPointInRegion(42, 22, 28, 14, mouseX, mouseY)) {
			String name = MainCoreConfig.currency.localizedname();
			list.add(name + " x" + p1);
		}

		int p2 = shelf.getField(1);
		if (p2 > 0 && this.isPointInRegion(123, 22, 28, 14, mouseX, mouseY)) {
			String name = MainCoreConfig.currency.localizedname();
			list.add(name + " x" + p2);
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

		if (shelf != null && shelf.getOwner() != null) {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 0, 20, 20);
		} else {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 20, 20, 20);
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton) throws IOException {
		super.mouseClicked(x, y, mouseButton);
		boolean flag = this.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(mouseButton - 100);
		if (mouseButton == 0 || mouseButton == 1 || flag) {
			if (isPointInRegion(72, 21, 8, 5, x, y)) {
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 0, (short) 0));

			}
			if (isPointInRegion(72, 30, 8, 5, x, y)) {
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 1, (short) 0));
			}
			if (isPointInRegion(154, 21, 8, 5, x, y)) {
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 2, (short) 0));
			}
			if (isPointInRegion(154, 30, 8, 5, x, y)) {
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 3, (short) 0));
			}
		}
	}
}
