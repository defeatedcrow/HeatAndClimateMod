package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.DCTexturePath;
import defeatedcrow.hac.core.network.packet.message.MsgTileOwnerKeyToS;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToS;
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
public class MonitorAndonScreen extends AbstractContainerScreen<MonitorAndonMenu> {

	public MonitorAndonScreen(MonitorAndonMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
		this.imageHeight = 176;
		this.titleLabelX = 8;
		this.titleLabelY = 11;
		this.inventoryLabelX = 8;
		this.inventoryLabelY = this.imageHeight - 92;
	}

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

		if (this.isHovering(44, 23, 39, 8, mx, my) && this.menu.isRSMode()) {
			list.add(Component.translatable("dcs.tip.andon.rs_mode"));
		}

		if (this.isHovering(93, 23, 39, 8, mx, my) && !this.menu.isRSMode()) {
			list.add(Component.translatable("dcs.tip.andon.inv_mode"));
		}

		if (this.isHovering(138, 40, 10, 12, mx, my)) {
			if (this.menu.isAlartMode())
				list.add(Component.translatable("dcs.tip.andon.alart_on"));
			else
				list.add(Component.translatable("dcs.tip.andon.alart_off"));
		}

		if (this.isHovering(25, 38, 16, 16, mx, my)) {
			if (this.menu.getPairPos() != null) {
				list.add(Component.translatable("dcs.tip.andon.pos"));
				list.add(Component.literal("X " + this.menu.getPairPos().getX() + ", Y " + this.menu.getPairPos().getY() + ", Z " + this.menu.getPairPos().getZ()));
			} else {
				list.add(Component.translatable("dcs.tip.andon.pos_null"));
			}
		}

		if (this.isHovering(56, 39, 16, 16, mx, my)) {
			list.add(Component.translatable("dcs.tip.andon.red"));
		}

		if (this.isHovering(56, 57, 16, 16, mx, my)) {
			list.add(Component.translatable("dcs.tip.andon.yellow"));
		}

		// int i = (this.width - this.imageWidth) / 2;
		// int j = (this.height - this.imageHeight) / 2;
		// list.add(Component.literal("X" + (mx - i) + ", Y" + (my - j)));

		this.renderComponentTooltip(pose, list, mx, my);
	}

	@Override
	protected void renderBg(PoseStack pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_MONITOR_ANDON.getLocation());
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

		boolean lock = this.getMenu().getContainer().isLocked();
		if (lock) {
			this.blit(pose, i + 156, j + 3, 176, 21, 12, 21);
		} else {
			this.blit(pose, i + 156, j + 3, 176, 0, 12, 21);
		}

		if (this.menu.isRSMode()) {
			this.blit(pose, i + 44, j + 23, 208, 16, 39, 8);
		} else {
			this.blit(pose, i + 93, j + 23, 208, 24, 39, 8);
		}

		if (this.menu.isAlartMode()) {
			this.blit(pose, i + 138, j + 46, 208, 38, 10, 6);
		} else {
			this.blit(pose, i + 138, j + 40, 208, 32, 10, 6);
		}

		if (this.menu.getPairPos() != null) {
			this.blit(pose, i + 25, j + 38, 208, 0, 16, 16);
		}

		if (this.menu.getRedThreshold() > 0) {
			this.blit(pose, i + 84, j + 40, 189, 13 * this.menu.getRedThreshold() - 13, 19, 13);
		}

		if (this.menu.getYellowThreshold() > 0) {
			this.blit(pose, i + 84, j + 58, 189, 13 * this.menu.getYellowThreshold() - 13, 19, 13);
		}
	}

	@Override
	public boolean mouseClicked(double x, double y, int i0) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;

		double dx1 = x - (i + 156);
		double dy1 = y - (j + 3);
		if (dx1 >= 0.0D && dy1 >= 0.0D && dx1 < 14.0D && dy1 < 21.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				boolean b = this.getMenu().getContainer().toggleLock();
				MsgTileOwnerKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), b);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.IRON_DOOR_OPEN, 1.0F));
				return true;
			}
		}

		double dx2 = x - (i + 44);
		double dy2 = y - (j + 22);
		if (dx2 >= 0.0D && dy2 >= 0.0D && dx2 < 39.0D && dy2 < 8.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				this.getMenu().getContainer().receiveIntegerFromClient(4);
				MsgTileSimpleIntegerToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 4);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				return true;
			}
		}
		double dx3 = x - (i + 93);
		if (dx3 >= 0.0D && dy2 >= 0.0D && dx3 < 39.0D && dy2 < 8.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				this.getMenu().getContainer().receiveIntegerFromClient(5);
				MsgTileSimpleIntegerToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 5);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				return true;
			}
		}

		double dx4 = x - (i + 110);
		double dy4 = y - (j + 40);
		if (dx4 >= 0.0D && dy4 >= 0.0D && dx4 < 10.0D && dy4 < 7.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				this.getMenu().getContainer().receiveIntegerFromClient(0);
				MsgTileSimpleIntegerToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 0);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				return true;
			}
		}
		if (dx4 >= 0.0D && dy4 >= 7.0D && dx4 < 10.0D && dy4 < 14.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				this.getMenu().getContainer().receiveIntegerFromClient(1);
				MsgTileSimpleIntegerToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 1);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				return true;
			}
		}
		if (dx4 >= 0.0D && dy4 >= 17.0D && dx4 < 10.0D && dy4 < 24.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				this.getMenu().getContainer().receiveIntegerFromClient(2);
				MsgTileSimpleIntegerToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 2);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				return true;
			}
		}
		if (dx4 >= 0.0D && dy4 >= 24.0D && dx4 < 10.0D && dy4 < 31.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				this.getMenu().getContainer().receiveIntegerFromClient(3);
				MsgTileSimpleIntegerToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 3);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				return true;
			}
		}

		double dx5 = x - (i + 138);
		double dy5 = y - (j + 40);
		if (dx5 >= 0.0D && dy5 >= 0.0D && dx5 < 10.0D && dy5 < 12.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				int alart = this.menu.isAlartMode() ? 6 : 7;
				this.getMenu().getContainer().receiveIntegerFromClient(alart);
				MsgTileSimpleIntegerToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), alart);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				return true;
			}
		}

		return super.mouseClicked(x, y, i0);
	}
}
