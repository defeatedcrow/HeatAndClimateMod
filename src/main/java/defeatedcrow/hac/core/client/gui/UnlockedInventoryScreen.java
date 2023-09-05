package defeatedcrow.hac.core.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.client.DCTexturePath;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UnlockedInventoryScreen extends AbstractContainerScreen<SimpleInventoryMenu> {

	public UnlockedInventoryScreen(SimpleInventoryMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
		this.imageHeight = 230;
		this.titleLabelX = 8;
		this.titleLabelY = 12;
		this.inventoryLabelX = 8;
		this.inventoryLabelY = this.imageHeight - 92;
	}

	@Override
	public void render(PoseStack pose, int mx, int my, float f) {
		this.renderBackground(pose);
		super.render(pose, mx, my, f);
		this.renderTooltip(pose, mx, my);
	}

	@Override
	protected void renderBg(PoseStack pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_INV_DOUBLE.getLocation());
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);
	}
}
