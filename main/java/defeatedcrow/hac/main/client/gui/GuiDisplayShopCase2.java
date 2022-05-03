package defeatedcrow.hac.main.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.main.block.build.TileDisplayShopCase;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageDisplayCaseButton;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 購入プレイヤーのためのGUI
 */
@SideOnly(Side.CLIENT)
public class GuiDisplayShopCase2 extends GuiBaseDC {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate", "textures/gui/display_shop_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileDisplayShopCase shelf;

	public GuiDisplayShopCase2(TileDisplayShopCase te, EntityPlayer player) {
		super(new ContainerDisplayShopCase(te, player, true));
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

		if (shelf.getField(0) <= 0) {
			p1 = "---";
		}
		if (shelf.getField(1) <= 0) {
			p2 = "---";
		}

		this.fontRenderer.drawString(p1, 55, 29, 0xFFFFFF);
		this.fontRenderer.drawString(p2, 136, 29, 0xFFFFFF);
	}

	private int emeraldCount = 0;
	private int limit = 0;

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

		int p1 = shelf.getField(0);
		int p2 = shelf.getField(1);

		if (emeraldCount >= p1 && this.isPointInRegion(47, 46, 25, 11, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.buy"));
			}
		}
		if (emeraldCount >= p2 && this.isPointInRegion(128, 46, 25, 11, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.buy"));
			}
		}

		if (p1 > 0 && this.isPointInRegion(46, 27, 27, 10, mouseX, mouseY)) {
			String name = MainCoreConfig.currency.localizedname();
			list.add(name + " x" + p1);
		}

		if (p2 > 0 && this.isPointInRegion(127, 27, 27, 10, mouseX, mouseY)) {
			String name = MainCoreConfig.currency.localizedname();
			list.add(name + " x" + p2);
		}

		if (!list.isEmpty()) {
			this.drawHoveringText(list, mouseX, mouseY);
		}

		if (button1 > 0) {
			button1--;
		}
		if (button2 > 0) {
			button2--;
		}
	}

	private int button1 = 0;
	private int button2 = 0;

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (limit <= 0) {
			emeraldCount = MainUtil.inventoryItemCheck(playerInventory, MainCoreConfig.currency.getSingleStack());
			limit = 20;
		} else {
			limit--;
		}
		int p1 = shelf.getField(0);
		int p2 = shelf.getField(1);
		int eme = shelf.getField(3);
		if (button1 > 0) {
			this.drawTexturedModalRect(i + 47, j + 46, 176, 51, 25, 11);
		} else if (p1 > 0 && emeraldCount >= p1 && (p1 + eme) <= shelf.EMERALD_MAX) {
			this.drawTexturedModalRect(i + 47, j + 46, 176, 40, 25, 11);
		}

		if (button2 > 0) {
			this.drawTexturedModalRect(i + 128, j + 46, 176, 51, 25, 11);
		} else if (p2 > 0 && emeraldCount >= p2 && (p2 + eme) <= shelf.EMERALD_MAX) {
			this.drawTexturedModalRect(i + 128, j + 46, 176, 40, 25, 11);
		}

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
			if (isPointInRegion(47, 46, 25, 11, x, y)) {
				int p1 = shelf.getField(0);
				int eme = shelf.getField(3);
				if ((p1 + eme) > shelf.EMERALD_MAX) {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.emerald_max")));
					return;
				}
				emeraldCount = MainUtil.inventoryItemCheck(playerInventory, MainCoreConfig.currency.getSingleStack());
				if (p1 > 0 && emeraldCount >= p1) {
					mc.getSoundHandler().playSound(PositionedSoundRecord
							.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
					button1 = 5;
					DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 4, (short) p1));
				} else {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.failed")));
				}
			}
			if (isPointInRegion(128, 46, 25, 11, x, y)) {
				int p2 = shelf.getField(0);
				int eme = shelf.getField(3);
				if ((p2 + eme) > shelf.EMERALD_MAX) {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.emerald_max")));
					return;
				}
				emeraldCount = MainUtil.inventoryItemCheck(playerInventory, MainCoreConfig.currency.getSingleStack());
				if (p2 > 0 && emeraldCount >= p2) {
					mc.getSoundHandler().playSound(PositionedSoundRecord
							.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
					button2 = 5;
					DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 5, (short) p2));
				} else {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.failed")));
				}
			}
		}
	}
}
