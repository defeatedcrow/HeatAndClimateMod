package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.core.client.entity.model.CutleryModel;
import defeatedcrow.hac.core.material.entity.ObjectEntityBaseDC;
import defeatedcrow.hac.core.material.item.tool.CutleryForkItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderFork extends EntityRenderer<ObjectEntityBaseDC> {

	protected CutleryModel model;

	public RenderFork(Context ctx) {
		super(ctx);
		this.model = new CutleryModel(ctx.bakeLayer(CutleryForkItem.FORK.getLayerLocation()));
	}

	@Override
	public ResourceLocation getTextureLocation(ObjectEntityBaseDC entity) {
		return CutleryForkItem.FORK.getTextureLocation();
	}

	@Override
	public void render(ObjectEntityBaseDC entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			Item item = entity.getItem().getItem();
			if (item instanceof IEntityItem && ((IEntityItem) item).getRenderData(item) != null) {
				EntityRenderData data = ((IEntityItem) item).getRenderData(item);
				ResourceLocation tex = data.getTextureLocation();
				float f1 = data.getModelScale();
				float f2 = data.getAdjustY();

				poseStack.pushPose();
				poseStack.translate(0F, f2, 0F);
				poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - yaw));
				poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
				poseStack.scale(f1, f1, f1);
				model.setupAnim(entity, 180.0F - yaw, partialTicks, packedLight, f1, f2);
				VertexConsumer vertex = buffer.getBuffer(model.renderType(tex));
				this.model.renderFork(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				poseStack.popPose();
			}
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}
}
