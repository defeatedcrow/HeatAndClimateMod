package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.DCTexturePath;
import defeatedcrow.hac.core.network.packet.message.MsgTileBatteryGuiKeyToS;
import defeatedcrow.hac.core.network.packet.message.MsgTileOwnerKeyToS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnergyBatteryScreen extends AbstractContainerScreen<EnergyBatteryMenu> {

	public EnergyBatteryScreen(EnergyBatteryMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
		this.imageHeight = 212;
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

		if (this.isHovering(31, 34, 14, 16, mx, my)) {
			String s = this.menu.isPowerd() ? "OFF" : "ON";
			list.add(Component.translatable("dcs.tip.device.energy.power").append(s));
		}

		if (this.isHovering(60, 44, 10, 10, mx, my)) {
			String s = this.menu.isRS() ? "ON" : "OFF";
			list.add(Component.translatable("dcs.tip.device.energy.rs").append(s));
		}

		if (this.isHovering(60, 27, 10, 10, mx, my)) {
			String s = this.menu.isActive() ? "ON" : "OFF";
			list.add(Component.translatable("dcs.tip.device.energy.active." + s));
		}

		if (this.isHovering(30, 60, 16, 45, mx, my)) {
			String s = this.menu.getEnergy() + "/" + this.menu.getContainer().getEnergyHandler().getMaxEnergyStored() + " FE";
			list.add(Component.literal(s));
		}

		for (int k = 0; k < 6; k++)
			if (this.isHovering(120, 32 + (13 * k), 27, 8, mx, my)) {
				String s = "none";
				switch (this.menu.getFaceIO(0)) {
				case 1:
					s = "outlet";
					break;
				case 2:
					s = "input";
					break;
				case 3:
					s = "pass";
					break;
				}
				list.add(Component.translatable("dcs.tip.device.energy.io." + s));
			}

		this.renderComponentTooltip(pose, list, mx, my);
	}

	@Override
	protected void renderBg(PoseStack pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_BATTERY.getLocation());

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

		if (this.menu.isPowerd()) {
			this.blit(pose, i + 31, j + 34, 176, 82, 14, 9);
		} else {
			this.blit(pose, i + 31, j + 42, 176, 91, 14, 9);
		}

		if (this.menu.isActive()) {
			this.blit(pose, i + 60, j + 27, 176, 62, 10, 10);
		}

		if (this.menu.isRS()) {
			this.blit(pose, i + 60, j + 44, 176, 72, 10, 10);
		}

		int m = this.menu.getBatteryCount();
		if (m > 0) {
			for (int k = 0; k < m; k++) {
				this.blit(pose, i + 33, j + 100 - (k * 5), 176, 100, 10, 4);
			}
		}

		this.blit(pose, i + 120, j + 32, 189, this.menu.getFaceIO(0) * 8, 27, 8);
		this.blit(pose, i + 120, j + 45, 189, this.menu.getFaceIO(1) * 8, 27, 8);
		this.blit(pose, i + 120, j + 58, 189, this.menu.getFaceIO(2) * 8, 27, 8);
		this.blit(pose, i + 120, j + 71, 189, this.menu.getFaceIO(3) * 8, 27, 8);
		this.blit(pose, i + 120, j + 84, 189, this.menu.getFaceIO(4) * 8, 27, 8);
		this.blit(pose, i + 120, j + 97, 189, this.menu.getFaceIO(5) * 8, 27, 8);

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

			if (dx >= 30.0D && dy >= 35.0D && dx < 46.0D && dy < 50.0D) {
				MsgTileBatteryGuiKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), 7);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.LEVER_CLICK, 1.0F));
				return true;
			}

			for (int k = 0; k < 6; k++)
				if (dx >= 120.0D && dy >= (32.0D + 13.0D * k) && dx < 147.0D && dy < (40.0D + 13.0D * k)) {
					MsgTileBatteryGuiKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), getDir(k).get3DDataValue());
					Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.LEVER_CLICK, 1.0F));
					this.menu.getContainer().switchFace(getDir(k));
					return true;
				}
		}

		return super.mouseClicked(x, y, i0);
	}

	private Direction getDir(int k) {
		switch (k) {
		case 0:
			return Direction.UP;
		case 1:
			return this.getMenu().getContainer().getFront();
		case 2:
			return this.getMenu().getContainer().getBack();
		case 3:
			return this.getMenu().getContainer().getRight();
		case 4:
			return this.getMenu().getContainer().getLeft();
		case 5:
			return Direction.DOWN;

		default:
			return Direction.DOWN;
		}
	}
}
