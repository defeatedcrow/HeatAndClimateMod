package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.DCTexturePath;
import defeatedcrow.hac.core.network.packet.message.MsgTileOwnerKeyToS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

@OnlyIn(Dist.CLIENT)
public class TeaPotScreen extends AbstractContainerScreen<TeaPotMenu> {

	private int w1 = 47;
	private int h1 = 41;
	private int w2 = 145;
	private int h2 = 33;

	public TeaPotScreen(TeaPotMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
		this.imageHeight = 203;
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
		if (this.isHovering(156, 3, 168, 23, mx, my)) {
			if (lock) {
				list.add(Component.translatable("dcs.tip.container.ownable_locked", this.getMenu().getContainer().getOwnerName()));
			} else {
				if (ClimateCore.proxy.keyShiftPushed())
					list.add(Component.translatable("dcs.tip.container.ownable"));
				else
					list.add(Component.translatable("dcs.tip.container.ownable_short"));
			}
		}
		if (this.isHovering(w1, h1, 12, 42, mx, my)) {
			if (!this.menu.getInputFluid().isEmpty()) {
				list.add(this.menu.getInputFluidName());
				list.add(this.menu.getInputFluidAmount());
			} else {
				list.add(this.menu.getInputFluidName());
			}
		}

		if (this.isHovering(63, 89, 12, 12, mx, my)) {
			DCHeatTier temp = DCHeatTier.getTypeByID(this.menu.getTempID());
			list.add(Component.translatable("dcs.tip.device.heat", temp.localize()));
		}

		this.renderComponentTooltip(pose, list, mx, my);
	}

	@Override
	protected void renderBg(PoseStack pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_TEA_POT.getLocation());

		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

		boolean lock = this.getMenu().getContainer().isLocked();
		if (lock) {
			this.blit(pose, i + 156, j + 3, 176, 21, 12, 21);
		} else {
			this.blit(pose, i + 156, j + 3, 176, 0, 12, 21);
		}

		int tempID = this.menu.getTempID();
		this.blit(pose, i + 63, j + 89, 190, tempID * 14, 14, 14);

		int l = this.menu.getBurnProgress();
		if (l > 0) {
			if (tempID > DCHeatTier.COOL.getID()) {
				this.blit(pose, i + 45, j + 89 + 14 - l, 176, 42 + 14 - l, 14, l);
			} else {
				this.blit(pose, i + 45, j + 89 + 14 - l, 176, 56 + 14 - l, 14, l);
			}
		}

		if (!this.menu.getInputFluid().isEmpty()) {
			int amo = this.menu.getInputFluidGauge();
			renderFluid(pose, this.menu.getInputFluid(), i + w1, j + h1, 12, 40, amo);
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

	public static void renderFluid(PoseStack pose, FluidStack fluid, int x, int y, int width, int height, int amo) {
		IClientFluidTypeExtensions ext = IClientFluidTypeExtensions.of(fluid.getFluid().getFluidType());
		ResourceLocation res = ext.getStillTexture(fluid);
		TextureAtlasSprite tex = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(res);
		int color = ext.getTintColor(fluid);
		int y1 = y + (height - amo);

		RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
		Matrix4f matrix = pose.last().pose();
		setColor(color);

		int hc = amo / 16;
		int hr = amo - (hc * 16);
		if (hc > 0)
			for (int h = 0; h < hc; h++) {
				int y2 = y1 + hr + (h * 16);
				draw(matrix, x, y2, width, 16, tex, 0);
			}
		if (hr > 0) {
			draw(matrix, x, y1, width, hr, tex, 16 - hr);
		}
	}

	public static void draw(Matrix4f matrix, float x, float y, int w, int h, TextureAtlasSprite sprite, int top) {
		float uMin = sprite.getU0();
		float uMax = sprite.getU1();
		float vMin = sprite.getV0();
		float vMax = sprite.getV1();
		vMax = vMax - (top / 16F * (vMax - vMin));

		RenderSystem.setShader(GameRenderer::getPositionTexShader);

		Tesselator tessellator = Tesselator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuilder();
		bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferBuilder.vertex(matrix, x, y + h, 100F).uv(uMin, vMax).endVertex();
		bufferBuilder.vertex(matrix, x + w, y + h, 100F).uv(uMax, vMax).endVertex();
		bufferBuilder.vertex(matrix, x + w, y, 100F).uv(uMax, vMin).endVertex();
		bufferBuilder.vertex(matrix, x, y, 100F).uv(uMin, vMin).endVertex();
		tessellator.end();
	}

	public static void setColor(int color) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;
		float alpha = ((color >> 24) & 0xFF) / 255F;
		RenderSystem.setShaderColor(red, green, blue, alpha);
	}
}
