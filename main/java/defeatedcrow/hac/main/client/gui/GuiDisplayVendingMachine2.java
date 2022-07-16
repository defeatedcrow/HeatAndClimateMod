package defeatedcrow.hac.main.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.main.block.build.TileDisplayVendingMachine;
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
public class GuiDisplayVendingMachine2 extends GuiBaseDC {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate", "textures/gui/display_vender_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileDisplayVendingMachine shelf;

	public GuiDisplayVendingMachine2(TileDisplayVendingMachine te, EntityPlayer player) {
		super(new ContainerDisplayVendingMachine(te, player, true));
		this.playerInventory = player.inventory;
		this.shelf = te;
		this.ySize = 192;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.shelf.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 4, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName()
				.getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

		String p1 = "" + shelf.getField(0);
		String p2 = "" + shelf.getField(1);
		String p3 = "" + shelf.getField(2);
		String p4 = "" + shelf.getField(3);

		if (shelf.getField(0) <= 0) {
			p1 = "---";
		}
		if (shelf.getField(1) <= 0) {
			p2 = "---";
		}
		if (shelf.getField(2) <= 0) {
			p3 = "---";
		}
		if (shelf.getField(3) <= 0) {
			p4 = "---";
		}

		this.fontRenderer.drawString(p1, 55, 23, 0xFFFFFF);
		this.fontRenderer.drawString(p2, 136, 23, 0xFFFFFF);
		this.fontRenderer.drawString(p3, 55, 61, 0xFFFFFF);
		this.fontRenderer.drawString(p4, 136, 61, 0xFFFFFF);
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
		int p3 = shelf.getField(2);
		int p4 = shelf.getField(3);

		if (emeraldCount >= p1 && this.isPointInRegion(35, 37, 25, 11, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.buy"));
			}
		}
		if (emeraldCount >= p2 && this.isPointInRegion(116, 37, 25, 11, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.buy"));
			}
		}
		if (emeraldCount >= p3 && this.isPointInRegion(35, 76, 25, 11, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.buy"));
			}
		}
		if (emeraldCount >= p4 && this.isPointInRegion(116, 76, 25, 11, mouseX, mouseY)) {
			if (shelf != null) {
				list.add(I18n.format("dcs.gui.message.displaycase.buy"));
			}
		}

		if (p1 > 0 && this.isPointInRegion(53, 23, 20, 10, mouseX, mouseY)) {
			String name = MainCoreConfig.currency.localizedname();
			list.add(name + " x" + p1);
		}
		if (p2 > 0 && this.isPointInRegion(134, 23, 20, 10, mouseX, mouseY)) {
			String name = MainCoreConfig.currency.localizedname();
			list.add(name + " x" + p2);
		}
		if (p3 > 0 && this.isPointInRegion(53, 60, 20, 10, mouseX, mouseY)) {
			String name = MainCoreConfig.currency.localizedname();
			list.add(name + " x" + p3);
		}
		if (p4 > 0 && this.isPointInRegion(134, 60, 20, 10, mouseX, mouseY)) {
			String name = MainCoreConfig.currency.localizedname();
			list.add(name + " x" + p4);
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
		if (button3 > 0) {
			button3--;
		}
		if (button4 > 0) {
			button4--;
		}
	}

	private int button1 = 0;
	private int button2 = 0;
	private int button3 = 0;
	private int button4 = 0;

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
		int p3 = shelf.getField(2);
		int p4 = shelf.getField(3);
		int eme = shelf.getField(5);
		if (button1 > 0) {
			this.drawTexturedModalRect(i + 35, j + 37, 176, 51, 25, 11);
		} else if (p1 > 0 && emeraldCount >= p1 && (p1 + eme) <= shelf.EMERALD_MAX) {
			this.drawTexturedModalRect(i + 35, j + 37, 176, 40, 25, 11);
		}
		if (button2 > 0) {
			this.drawTexturedModalRect(i + 116, j + 37, 176, 51, 25, 11);
		} else if (p2 > 0 && emeraldCount >= p2 && (p2 + eme) <= shelf.EMERALD_MAX) {
			this.drawTexturedModalRect(i + 116, j + 37, 176, 40, 25, 11);
		}
		if (button3 > 0) {
			this.drawTexturedModalRect(i + 35, j + 76, 176, 51, 25, 11);
		} else if (p3 > 0 && emeraldCount >= p3 && (p3 + eme) <= shelf.EMERALD_MAX) {
			this.drawTexturedModalRect(i + 35, j + 76, 176, 40, 25, 11);
		}
		if (button4 > 0) {
			this.drawTexturedModalRect(i + 116, j + 76, 176, 51, 25, 11);
		} else if (p4 > 0 && emeraldCount >= p4 && (p4 + eme) <= shelf.EMERALD_MAX) {
			this.drawTexturedModalRect(i + 116, j + 76, 176, 40, 25, 11);
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
		int eme = shelf.getField(5);
		if (mouseButton == 0 || mouseButton == 1 || flag) {
			if (isPointInRegion(35, 37, 25, 11, x, y)) {
				int p1 = shelf.getField(0);
				if ((p1 + eme) > shelf.EMERALD_MAX) {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.emerald_max")));
					return;
				}
				emeraldCount = MainUtil.inventoryItemCheck(playerInventory, MainCoreConfig.currency.getSingleStack());
				if (p1 > 0 && emeraldCount >= p1) {
					mc.getSoundHandler().playSound(PositionedSoundRecord
							.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
					button1 = 5;
					DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 8, (short) p1));
				} else {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.failed")));
				}
			}
			if (isPointInRegion(116, 37, 25, 11, x, y)) {
				int p2 = shelf.getField(1);
				if ((p2 + eme) > shelf.EMERALD_MAX) {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.emerald_max")));
					return;
				}
				emeraldCount = MainUtil.inventoryItemCheck(playerInventory, MainCoreConfig.currency.getSingleStack());
				if (p2 > 0 && emeraldCount >= p2) {
					mc.getSoundHandler().playSound(PositionedSoundRecord
							.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
					button2 = 5;
					DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 9, (short) p2));
				} else {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.failed")));
				}
			}
			if (isPointInRegion(35, 76, 25, 11, x, y)) {
				int p3 = shelf.getField(2);
				if ((p3 + eme) > shelf.EMERALD_MAX) {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.emerald_max")));
					return;
				}
				emeraldCount = MainUtil.inventoryItemCheck(playerInventory, MainCoreConfig.currency.getSingleStack());
				if (p3 > 0 && emeraldCount >= p3) {
					mc.getSoundHandler().playSound(PositionedSoundRecord
							.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
					button3 = 5;
					DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 10, (short) p3));
				} else {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.failed")));
				}
			}
			if (isPointInRegion(116, 76, 25, 11, x, y)) {
				int p4 = shelf.getField(3);
				if ((p4 + eme) > shelf.EMERALD_MAX) {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.emerald_max")));
					return;
				}
				emeraldCount = MainUtil.inventoryItemCheck(playerInventory, MainCoreConfig.currency.getSingleStack());
				if (p4 > 0 && emeraldCount >= p4) {
					mc.getSoundHandler().playSound(PositionedSoundRecord
							.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
					button4 = 5;
					DCMainPacket.INSTANCE.sendToServer(new MessageDisplayCaseButton(shelf.getPos(), (byte) 11, (short) p4));
				} else {
					mc.player.sendMessage(new TextComponentString(I18n.format("dcs.gui.message.displaycase.failed")));
				}
			}
		}
	}
}
