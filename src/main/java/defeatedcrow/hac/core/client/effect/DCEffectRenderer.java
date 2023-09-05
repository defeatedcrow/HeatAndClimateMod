package defeatedcrow.hac.core.client.effect;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;

import defeatedcrow.hac.core.client.DCTexturePath;
import defeatedcrow.hac.core.material.effects.MobEffectDC;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

public class DCEffectRenderer implements IClientMobEffectExtensions {

	@Override
	public boolean renderInventoryIcon(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, PoseStack poseStack, int x, int y, int blit) {
		MobEffect effect = instance.getEffect();
		if (effect instanceof MobEffectDC) {
			MobEffectDC dc = (MobEffectDC) effect;
			RenderSystem.setShaderTexture(0, DCTexturePath.POTION.getLocation());
			blit(poseStack.last().pose(), x, y + 8, blit, dc.getIconX(), dc.getIconY());
			return true;
		}

		return false;
	}

	@Override
	public boolean renderInventoryText(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, PoseStack poseStack, int x, int y, int blit) {
		// TODO +10レベル以上の場合
		return false;
	}

	@Override
	public boolean renderGuiIcon(MobEffectInstance instance, Gui gui, PoseStack poseStack, int x, int y, float z, float alpha) {
		MobEffect effect = instance.getEffect();
		if (effect instanceof MobEffectDC) {
			MobEffectDC dc = (MobEffectDC) effect;
			RenderSystem.setShaderTexture(0, DCTexturePath.POTION.getLocation());
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);
			blit(poseStack.last().pose(), x + 3, y + 3, z, dc.getIconX(), dc.getIconY());
			return true;
		}

		return false;
	}

	private static void blit(Matrix4f poseStack, int x, int y, float blit, int u, int v) {
		float f = 1F / 256F;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(poseStack, x, y + 18F, blit).uv(u * f, (v + 18) * f).endVertex();
		bufferbuilder.vertex(poseStack, x + 18F, y + 18F, blit).uv((u + 18) * f, (v + 18) * f).endVertex();
		bufferbuilder.vertex(poseStack, x + 18F, y, blit).uv((u + 18) * f, v * f).endVertex();
		bufferbuilder.vertex(poseStack, x, y, blit).uv(u * f, v * f).endVertex();
		BufferUploader.drawWithShader(bufferbuilder.end());
	}

}
