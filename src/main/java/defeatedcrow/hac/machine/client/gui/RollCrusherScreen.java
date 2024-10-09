package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.DCTexturePath;
import defeatedcrow.hac.core.network.packet.message.MsgTileOwnerKeyToS;
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
public class RollCrusherScreen extends AbstractContainerScreen<RollCrusherMenu> {

	public RollCrusherScreen(RollCrusherMenu menu, Inventory playerInv, Component comp) {
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

		if (this.isHovering(12, 92, 12, 16, mx, my)) {
			String s = this.menu.getEnergy() + "/" + this.menu.getContainer().getEnergyHandler().getMaxEnergyStored() + " FE";
			list.add(Component.literal(s));
		}

		if (this.menu.errorPrimary() && this.isHovering(62, 61, 24, 17, mx, my)) {
			list.add(Component.translatable("dcs.tip.process_error.no_1"));
		}

		if (this.menu.errorSecondary() && this.isHovering(88, 61, 24, 17, mx, my)) {
			list.add(Component.translatable("dcs.tip.process_error.no_2"));
		}

		if (this.menu.errorTertiary() && this.isHovering(88, 61, 24, 17, mx, my)) {
			list.add(Component.translatable("dcs.tip.process_error.no_3"));
		}

		if (this.menu.errorContainer() && this.isHovering(9, 24, 26, 25, mx, my)) {
			list.add(Component.translatable("dcs.tip.process_error.no_0"));
		}

		if (this.menu.errorFluid() && this.isHovering(118, 23, 48, 61, mx, my)) {
			list.add(Component.translatable("dcs.tip.process_error.no_4"));
		}

		this.renderComponentTooltip(pose, list, mx, my);
	}

	@Override
	protected void renderBg(PoseStack pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_CRUSHER.getLocation());
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

		boolean lock = this.getMenu().getContainer().isLocked();
		if (lock) {
			this.blit(pose, i + 156, j + 3, 176, 21, 12, 21);
		} else {
			this.blit(pose, i + 156, j + 3, 176, 0, 12, 21);
		}

		int l = this.menu.getBurnProgress();
		if (l > 0)
			this.blit(pose, i + 42, j + 49, 189, l * 12, 12, 12);

		int m = this.menu.getBatteryCount();
		if (m > 0)
			this.blit(pose, i + 12, j + 108 - m * 2, 176, 42, 12, m * 2);

		if (this.menu.isRS()) {
			this.blit(pose, i + 13, j + 73, 176, 58, 10, 10);
		}

		if (this.menu.errorPrimary()) {
			this.blit(pose, i + 62, j + 61, 201, 0, 24, 17);
		}

		if (this.menu.errorSecondary() || this.menu.errorTertiary()) {
			this.blit(pose, i + 88, j + 61, 201, 0, 24, 17);
		}

		if (this.menu.errorFluid()) {
			this.blit(pose, i + 118, j + 23, 201, 95, 48, 61);
		}

		if (this.menu.errorContainer()) {
			this.blit(pose, i + 9, j + 24, 201, 18, 26, 25);
		}
	}

	@Override
	public boolean mouseClicked(double x, double y, int i0) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;

		double dx = x - (i + 156);
		double dy = y - (j + 3);
		if (dx >= 0.0D && dy >= 0.0D && dx < 112.0D && dy < 21.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				boolean b = this.getMenu().getContainer().toggleLock();
				MsgTileOwnerKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), b);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.IRON_DOOR_OPEN, 1.0F));
				return true;
			}
		}

		return super.mouseClicked(x, y, i0);
	}
}
