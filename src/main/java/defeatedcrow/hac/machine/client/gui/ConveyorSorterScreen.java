package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.DCTexturePath;
import defeatedcrow.hac.core.network.packet.message.MsgTileOwnerKeyToS;
import defeatedcrow.hac.core.network.packet.message.MsgTileSorterGuiKeyToS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConveyorSorterScreen extends AbstractContainerScreen<ConveyorSorterMenu> {

	public ConveyorSorterScreen(ConveyorSorterMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
		this.imageHeight = 182;
		this.titleLabelX = 8;
		this.titleLabelY = 11;
		this.inventoryLabelX = 8;
		this.inventoryLabelY = this.imageHeight - 92;
	}

	private int x1 = 72;
	private int x2 = 36;
	private int x3 = 109;
	private int y1 = 23;
	private int y2 = 53;
	private int y3 = 53;

	@Override
	public void render(PoseStack pose, int mx, int my, float f) {
		this.renderBackground(pose);
		super.render(pose, mx, my, f);
		this.renderTooltip(pose, mx, my);

		List<Component> list = Lists.newArrayList();
		boolean lock = this.getMenu().getContainer().isLocked();
		if (this.isHovering(156, 3, 12, 20, mx, my)) {
			if (lock) {
				list.add(Component.translatable("dcs.tip.container.ownable_locked", this.getMenu().getContainer().getOwnerName()));
			} else {
				if (ClimateCore.proxy.keyShiftPushed())
					list.add(Component.translatable("dcs.tip.container.ownable"));
				else
					list.add(Component.translatable("dcs.tip.container.ownable_short"));
			}
		}

		if (this.isHovering(x1, y1, 31, 20, mx, my)) {
			String s = "off";
			switch (this.menu.getFrontSortingType()) {
			case OFF:
				s = "off";
				break;
			case COMMON_PHRASE:
				s = "phrase";
				break;
			case COMMON_TAG:
				s = "tag";
				break;
			case EXACT_MATCH:
				s = "exact";
				break;
			}
			list.add(Component.translatable("dcs.tip.device.sorter.type." + s));
		}

		if (this.isHovering(x2, y2, 31, 20, mx, my)) {
			String s = "off";
			switch (this.menu.getLeftSortingType()) {
			case OFF:
				s = "off";
				break;
			case COMMON_PHRASE:
				s = "phrase";
				break;
			case COMMON_TAG:
				s = "tag";
				break;
			case EXACT_MATCH:
				s = "exact";
				break;
			}
			list.add(Component.translatable("dcs.tip.device.sorter.type." + s));
		}

		if (this.isHovering(x3, y3, 31, 20, mx, my)) {
			String s = "off";
			switch (this.menu.getRightSortingType()) {
			case OFF:
				s = "off";
				break;
			case COMMON_PHRASE:
				s = "phrase";
				break;
			case COMMON_TAG:
				s = "tag";
				break;
			case EXACT_MATCH:
				s = "exact";
				break;
			}
			list.add(Component.translatable("dcs.tip.device.sorter.type." + s));
		}

		this.renderComponentTooltip(pose, list, mx, my);
	}

	@Override
	protected void renderBg(PoseStack pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_CONVEYOR_SORTER.getLocation());

		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

		// lock
		boolean lock = this.getMenu().getContainer().isLocked();
		if (lock) {
			this.blit(pose, i + 156, j + 3, 176, 21, 12, 21);
		} else {
			this.blit(pose, i + 156, j + 3, 176, 0, 12, 21);
		}

		this.blit(pose, i + x1 + 1, j + y1 + 11, 189, this.menu.getFrontSortingType().priority * 8, 29, 8);
		this.blit(pose, i + x2 + 1, j + y2 + 11, 189, this.menu.getLeftSortingType().priority * 8, 29, 8);
		this.blit(pose, i + x3 + 1, j + y3 + 11, 189, this.menu.getRightSortingType().priority * 8, 29, 8);

	}

	@Override
	public boolean mouseClicked(double x, double y, int i0) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;

		if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {

			double dx = x - i;
			double dy = y - j;
			if (dx >= 156.0D && dy >= 3.0D && dx < 168.0D && dy < 24.0D) {
				boolean b = this.getMenu().getContainer().toggleLock();
				MsgTileOwnerKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), b);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.IRON_DOOR_OPEN, 1.0F));
				return true;
			}

			if (dx >= x1 + 1 && dy >= y1 + 11 && dx < x1 + 30D && dy < y1 + 19D) {
				MsgTileSorterGuiKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 0);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.LEVER_CLICK, 1.0F));
				this.menu.getContainer().switchSide(0);
				return true;
			}

			if (dx >= x2 + 1 && dy >= y2 + 11 && dx < x2 + 30D && dy < y2 + 19D) {
				MsgTileSorterGuiKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 1);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.LEVER_CLICK, 1.0F));
				this.menu.getContainer().switchSide(1);
				return true;
			}

			if (dx >= x3 + 1 && dy >= y3 + 11 && dx < x3 + 30D && dy < y3 + 19D) {
				MsgTileSorterGuiKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 2);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.LEVER_CLICK, 1.0F));
				this.menu.getContainer().switchSide(2);
				return true;
			}
		}

		return super.mouseClicked(x, y, i0);
	}
}
