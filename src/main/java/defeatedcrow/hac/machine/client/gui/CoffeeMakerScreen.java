package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;

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
public class CoffeeMakerScreen extends AbstractContainerScreen<CoffeeMakerMenu> {

	private int w1 = 41;
	private int h1 = 57;
	private int w2 = 145;
	private int h2 = 33;

	public CoffeeMakerScreen(CoffeeMakerMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
		this.imageHeight = 203;
		this.titleLabelX = 8;
		this.titleLabelY = 11;
		this.inventoryLabelX = 8;
		this.inventoryLabelY = this.imageHeight - 92;
	}

	@Override
	public void render(GuiGraphics pose, int mx, int my, float f) {
		this.renderBackground(pose);
		super.render(pose, mx, my, f);
		this.renderTooltip(pose, mx, my);

		List<Component> list = Lists.newArrayList();

		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		//list.add(Component.literal("x" + (mx - i) + ", y" + (my - j)));
		boolean lock = this.getMenu()
		    .getContainer()
		    .isLocked();
		if (this.isHovering(156, 3, 12, 20, mx, my)) {
			if (lock) {
				list.add(Component.translatable("dcs.tip.container.ownable_locked", this.getMenu()
				    .getContainer()
				    .getOwnerName()));
			} else if (ClimateCore.proxy.keyShiftPushed())
				list.add(Component.translatable("dcs.tip.container.ownable"));
			else
				list.add(Component.translatable("dcs.tip.container.ownable_short"));
		}
		if (this.isHovering(w1, h1, 12, 40, mx, my)) {
			if (!this.menu.getInputFluid()
			    .isEmpty()) {
				list.add(this.menu.getInputFluidName());
				list.add(this.menu.getInputFluidAmount());
			} else {
				list.add(this.menu.getInputFluidName());
			}
		}

		if (this.isHovering(98, 89, 12, 12, mx, my)) {
			DCHeatTier temp = DCHeatTier.getTypeByID(this.menu.getTempID());
			list.add(Component.translatable("dcs.tip.device.heat", temp.localize()));
		}

		pose.renderComponentTooltip(this.font, list, mx, my);
	}

	@Override
	protected void renderBg(GuiGraphics pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_COFFEE_MAKER.getLocation());

		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		pose.blit(DCTexturePath.GUI_COFFEE_MAKER.getLocation(), i, j, 0, 0, this.imageWidth, this.imageHeight);

		boolean lock = this.getMenu()
		    .getContainer()
		    .isLocked();
		if (lock) {
			pose.blit(DCTexturePath.GUI_COFFEE_MAKER.getLocation(), i + 156, j + 3, 176, 21, 12, 21);
		} else {
			pose.blit(DCTexturePath.GUI_COFFEE_MAKER.getLocation(), i + 156, j + 3, 176, 0, 12, 21);
		}

		int tempID = this.menu.getTempID();
		pose.blit(DCTexturePath.GUI_COFFEE_MAKER.getLocation(), i + 98, j + 89, 190, tempID * 14, 14, 14);

		int l = this.menu.getBurnProgress();
		if (l > 0) {
			if (tempID > DCHeatTier.COOL.getID()) {
				pose.blit(DCTexturePath.GUI_COFFEE_MAKER.getLocation(), i + 80, j + 89 + 14 - l, 176, 42 + 14 - l, 14, l);
			} else {
				pose.blit(DCTexturePath.GUI_COFFEE_MAKER.getLocation(), i + 80, j + 89 + 14 - l, 176, 56 + 14 - l, 14, l);
			}
		}

		if (!this.menu.getInputFluid()
		    .isEmpty()) {
			int amo = this.menu.getInputFluidGauge();
			PortableTankScreen.renderFluid(pose, this.menu.getInputFluid(), i + w1, j + h1, 12, 40, amo);
		}

	}

	@Override
	public boolean mouseClicked(double x, double y, int i0) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;

		double dx = x - (i + 156);
		double dy = y - (j + 3);
		if (dx >= 0.0D && dy >= 0.0D && dx < 112.0D && dy < 21.0D) {
			if (this.getMenu()
			    .getContainer() != null && this.getMenu().isOwner) {
				boolean b = this.getMenu()
				    .getContainer()
				    .toggleLock();
				MsgTileOwnerKeyToS.sendToServer(this.minecraft.player, this.getMenu()
				    .getContainer()
				    .getBlockPos(), b);
				Minecraft.getInstance()
				    .getSoundManager()
				    .play(SimpleSoundInstance.forUI(SoundEvents.IRON_DOOR_OPEN, 1.0F));
				return true;
			}
		}

		return super.mouseClicked(x, y, i0);
	}
}
