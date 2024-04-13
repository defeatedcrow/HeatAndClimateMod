package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.food.material.entity.potfoods.IPotFoods;
import defeatedcrow.hac.food.material.entity.potfoods.RiceBowlItem;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.CookingPotTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

@OnlyIn(Dist.CLIENT)
public class TileRendererCookingPot implements BlockEntityRenderer<CookingPotTile> {

	protected CookingPotModel_A model_A;
	protected CookingPotModel_B model_B;
	protected CookingPotModel_C model_C;
	protected CookingPotModel_Layer model_layer;
	private BlockRenderDispatcher renderer;

	public TileRendererCookingPot(BlockEntityRendererProvider.Context ctx) {
		this.model_A = new CookingPotModel_A(ctx.bakeLayer(CookingPotTile.NORMAL.getLayerLocation()));
		this.model_B = new CookingPotModel_B(ctx.bakeLayer(CookingPotTile.BLUE.getLayerLocation()));
		this.model_C = new CookingPotModel_C(ctx.bakeLayer(CookingPotTile.BLACK.getLayerLocation()));
		this.model_layer = new CookingPotModel_Layer(ctx.bakeLayer(RiceBowlItem.NORMAL_LAYER.getLayerLocation()));
		renderer = ctx.getBlockRenderDispatcher();
	}

	@Override
	public void render(CookingPotTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			EntityRenderData data = tile.getRenderData(block);
			ResourceLocation tex = data.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);
			boolean lit = DCState.getBool(tile.getBlockState(), DCState.LIT);
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();
			boolean isB = block == MachineInit.COOKING_POT_BLUE.get() || block == MachineInit.COOKING_POT_GREEN.get();
			boolean isC = block == MachineInit.COOKING_POT_BLACK.get();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(f1, f1, f1);

			if (isC)
				this.model_C.renderToBuffer(poseStack, buffer.getBuffer(model_C.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			else if (isB)
				this.model_B.renderToBuffer(poseStack, buffer.getBuffer(model_B.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			else
				this.model_A.renderToBuffer(poseStack, buffer.getBuffer(model_A.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			if (lit) {
				if (isC)
					this.model_C.renderTop(poseStack, buffer.getBuffer(model_C.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				else if (isB)
					this.model_B.renderTop(poseStack, buffer.getBuffer(model_B.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				else
					this.model_A.renderTop(poseStack, buffer.getBuffer(model_A.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			poseStack.popPose();

			if (!lit) {
				ItemStack output = tile.getItem(6);
				if (output.getItem() instanceof IPotFoods) {
					renderOutputItem(poseStack, buffer, output.copy(), packedLight);
				} else {
					FluidStack fluid = tile.outputTank.getFluid();
					if (fluid.isEmpty()) {
						fluid = tile.inputTank.getFluid();
					}
					if (!fluid.isEmpty()) {
						FluidStack copy = fluid.copy();
						if (isC) {
							float h = copy.getAmount() * 0.25F / tile.TANK_CAP;
							renderFluid(poseStack, buffer, copy, packedLight, 0.5F, 0.125F, 0.5F, 0.305F, h);
						} else {
							float h = copy.getAmount() * 0.35F / tile.TANK_CAP;
							renderFluid(poseStack, buffer, copy, packedLight, 0.5F, 0.075F, 0.5F, isB ? 0.375F : 0.305F, h);
						}
					}
				}
			}
		}
	}

	public static void renderFluid(PoseStack pose, MultiBufferSource buffer, FluidStack fluid, int light, float x, float y, float z, float w, float h) {
		IClientFluidTypeExtensions ext = IClientFluidTypeExtensions.of(fluid.getFluid().getFluidType());
		ResourceLocation res = ext.getStillTexture(fluid);
		TextureAtlasSprite tex = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(res);
		int color = ext.getTintColor(fluid);
		float[] colors = getColor(color);

		pose.pushPose();
		pose.translate(x, y, z);
		Matrix4f m4f = pose.last().pose();
		Matrix3f m3f = pose.last().normal();
		VertexConsumer vertex = buffer.getBuffer(RenderType.translucent());
		RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);

		pose.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		drawQuad(m4f, m3f, vertex, light, colors, 0, -w, -w, h, w, w, tex);

		pose.popPose();
	}

	public void renderOutputItem(PoseStack pose, MultiBufferSource buffer, ItemStack item, int light) {
		if (item.getItem() instanceof IPotFoods food) {
			EntityRenderData data = food.getPotTexture(item.getItem());
			ResourceLocation tex = data.getTextureLocation();

			pose.pushPose();
			pose.translate(0.5F, 0F, 0.5F);
			pose.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			pose.scale(0.75F, 1F, 0.75F);

			this.model_layer.renderToBuffer(pose, buffer.getBuffer(model_layer.renderType(tex)), light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			pose.popPose();
		}
	}

	public static void drawQuad(Matrix4f m4f, Matrix3f m3f, VertexConsumer vertex, int light, float[] colors, float x1, float y1, float z1, float x2, float y2, float z2, TextureAtlasSprite sprite) {
		float uMin = sprite.getU0();
		float uMax = sprite.getU1();
		float vMin = sprite.getV0();
		float vMax = sprite.getV1();

		// e
		vertex.vertex(m4f, x2, y2, z1).color(colors[0], colors[1], colors[2], colors[3]).uv(uMin, vMax).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y2, z2).color(colors[0], colors[1], colors[2], colors[3]).uv(uMax, vMax).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y1, z2).color(colors[0], colors[1], colors[2], colors[3]).uv(uMax, vMin).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y1, z1).color(colors[0], colors[1], colors[2], colors[3]).uv(uMin, vMin).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
	}

	public static void drawQuad(Matrix4f m4f, Matrix3f m3f, VertexConsumer vertex, int light, float x1, float y1, float z1, float x2, float y2, float z2) {
		float uMin = 0F;
		float uMax = 1F;
		float vMin = 0F;
		float vMax = 1F;
		float[] colors = getColor(0xFFFFFFFF);

		// e
		vertex.vertex(m4f, x2, y2, z1).color(colors[0], colors[1], colors[2], colors[3]).uv(uMin, vMax).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y2, z2).color(colors[0], colors[1], colors[2], colors[3]).uv(uMax, vMax).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y1, z2).color(colors[0], colors[1], colors[2], colors[3]).uv(uMax, vMin).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
		vertex.vertex(m4f, x2, y1, z1).color(colors[0], colors[1], colors[2], colors[3]).uv(uMin, vMin).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(m3f, 0F, 1F, 0F).endVertex();
	}

	public static float[] getColor(int color) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;
		float alpha = ((color >> 24) & 0xFF) / 255F;
		return new float[] { red, green, blue, alpha };
	}

}
