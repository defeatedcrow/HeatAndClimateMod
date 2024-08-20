package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
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
public class FluidChamberScreen extends AbstractContainerScreen<FluidChamberMenu> {

	public FluidChamberScreen(FluidChamberMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
		this.imageHeight = 182;
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
		if (this.isHovering(7, 29, 14, 14, mx, my)) {
			DCAirflow air = DCAirflow.getTypeByID(this.menu.getAirID());
			list.add(Component.translatable("dcs.tip.device.airflow", air.localize()));
		}
		if (this.isHovering(80, 24, 38, 32, mx, my)) {
			DCHeatTier temp = DCHeatTier.getTypeByID(this.menu.getTempID());
			list.add(Component.translatable("dcs.tip.device.heat", temp.localize()));
		}
		if (this.isHovering(54, 27, 12, 40, mx, my)) {
			if (!this.menu.getFluid().isEmpty()) {
				list.add(this.menu.getFluidName());
				list.add(this.menu.getFluidAmount());
			} else {
				list.add(this.menu.getFluidName());
			}
		}
		this.renderComponentTooltip(pose, list, mx, my);
	}

	@Override
	protected void renderBg(PoseStack pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_CHAMBER_FLUID.getLocation());
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

		boolean lock = this.getMenu().getContainer().isLocked();
		if (lock) {
			this.blit(pose, i + 156, j + 3, 176, 21, 12, 21);
		} else {
			this.blit(pose, i + 156, j + 3, 176, 0, 12, 21);
		}

		int airID = Math.min(3, this.menu.getAirID());
		this.blit(pose, i + 7, j + 28, 176, 56 + airID * 14, 14, 14);

		int tempID = this.menu.getTempID();
		if (tempID > DCHeatTier.OVEN.getID()) {
			int aj = 0;
			if (tempID > DCHeatTier.SMELTING.getID()) {
				aj = 33;
			}
			this.blit(pose, i + 80, j + 22, 190, aj, 38, 33);
		}

		int l = this.menu.getBurnProgress();
		if (l > 0)
			this.blit(pose, i + 91, j + 58 + l, 176, 42 + l, 14, 14 - l);

		if (!this.menu.getFluid().isEmpty()) {
			if (l > 0) {
				this.blit(pose, i + 58, j + 72, 190, 67, 49, 7);
			}

			int amo = this.menu.getFluidGauge();
			PortableTankScreen.renderFluid(pose, this.menu.getFluid(), i + 54, j + 27, 12, 40, amo);
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
