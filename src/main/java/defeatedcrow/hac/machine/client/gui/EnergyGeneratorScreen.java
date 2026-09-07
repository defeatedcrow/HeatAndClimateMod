package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;

import defeatedcrow.hac.core.client.DCTexturePath;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnergyGeneratorScreen extends EnergyBatteryScreen {

	public EnergyGeneratorScreen(EnergyBatteryMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
	}

	@Override
	public void render(GuiGraphics pose, int mx, int my, float f) {
		this.renderBackground(pose);
		super.render(pose, mx, my, f);
		this.renderTooltip(pose, mx, my);

		List<Component> list = Lists.newArrayList();

		if (this.isHovering(66, 70, 25, 15, mx, my)) {
			int g = this.menu.getFlow();
			list.add(Component.translatable("dcs.tip.device.energy.ammeter").append(g + " FE/t"));
		}

		pose.renderComponentTooltip(this.font, list, mx, my);
	}

	@Override
	protected void renderBg(GuiGraphics pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_GENERATOR.getLocation());

		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i, j, 0, 0, this.imageWidth, this.imageHeight);

		// lock
		boolean lock = this.getMenu().getContainer().isLocked();
		if (lock) {
			pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 156, j + 3, 176, 21, 12, 21);
		} else {
			pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 156, j + 3, 176, 0, 12, 21);
		}

		if (this.menu.isPowerd()) {
			pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 31, j + 34, 176, 82, 14, 9);
		} else {
			pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 31, j + 42, 176, 91, 14, 9);
		}

		if (this.menu.isActive()) {
			pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 60, j + 27, 176, 62, 10, 10);
		}

		if (this.menu.isRS()) {
			pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 60, j + 44, 176, 72, 10, 10);
		}

		int m = this.menu.getBatteryCount();
		if (m > 0) {
			for (int k = 0; k < m; k++) {
				pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 33, j + 100 - (k * 5), 176, 100, 10, 4);
			}
		}

		pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 120, j + 32, 189, this.menu.getFaceIO(0) * 8, 27, 8);
		pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 120, j + 45, 189, this.menu.getFaceIO(1) * 8, 27, 8);
		pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 120, j + 58, 189, this.menu.getFaceIO(2) * 8, 27, 8);
		pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 120, j + 71, 189, this.menu.getFaceIO(3) * 8, 27, 8);
		pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 120, j + 84, 189, this.menu.getFaceIO(4) * 8, 27, 8);
		pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 120, j + 97, 189, this.menu.getFaceIO(5) * 8, 27, 8);

		int g = this.menu.getBurnProgress();
		pose.blit(DCTexturePath.GUI_GENERATOR.getLocation(), i + 66, j + 70, 216, g * 14, 25, 14);

	}
}
