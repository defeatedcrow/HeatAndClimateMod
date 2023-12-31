package defeatedcrow.hac.magic.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.client.DCTexturePath;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoringScreen extends AbstractContainerScreen<BoringMenu> {

	public BoringScreen(BoringMenu menu, Inventory inv, Component comp) {
		super(menu, inv, comp);
		this.imageWidth = 135;
		this.imageHeight = 198;
		this.titleLabelX = 8;
		this.titleLabelY = 12;
	}

	@Override
	protected void renderLabels(PoseStack pose, int mx, int my) {
		ItemStack cont = this.menu.getContainer();
		CompoundTag tag = cont.getTag();
		if (tag != null) {
			if (tag.contains("dcs.boring.date")) {
				String date = tag.getString("dcs.boring.date");
				this.font.draw(pose, date, 27F, 4F, 4210752);
			}
			if (tag.contains("dcs.boring.x")) {
				int x = tag.getInt("dcs.boring.x");
				int y = tag.getInt("dcs.boring.y");
				int z = tag.getInt("dcs.boring.z");
				String pos = x + ", " + y + ", " + z;
				this.font.draw(pose, pos, 27F, 17F, 4210752);
			}
		}
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
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_BORING.getLocation());
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

		ItemStack cont = this.menu.getContainer();
		CompoundTag tag = cont.getTag();
		if (tag != null && tag.contains("dcs.boring.int_array")) {
			int[] blocks = tag.getIntArray("dcs.boring.int_array");

			for (int h = 0; h < blocks.length; h++) {
				this.blit(pose, i + 31, j + 33 + (h * 5), 72, 36 + (8 * blocks[h]), 4, 4);
			}
		}

	}

}
