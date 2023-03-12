package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.core.material.entity.proj.ThrownHarpoon;
import defeatedcrow.hac.core.material.item.tool.HarpoonItem;
import defeatedcrow.hac.core.util.TierDC;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderHarpoon extends EntityRenderer<ThrownHarpoon> {
	private final TridentModel model;

	public RenderHarpoon(EntityRendererProvider.Context cont) {
		super(cont);
		this.model = new TridentModel(cont.bakeLayer(HarpoonItem.FLINT.getLayerLocation()));
	}

	@Override
	public void render(ThrownHarpoon harpoon, float yaw, float part, PoseStack pose, MultiBufferSource buffer, int light) {
		pose.pushPose();
		pose.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(part, harpoon.yRotO, harpoon.getYRot()) - 90.0F));
		pose.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(part, harpoon.xRotO, harpoon.getXRot()) + 90.0F));
		VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(buffer, this.model.renderType(this.getTextureLocation(harpoon)), false, false);
		this.model.renderToBuffer(pose, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		pose.popPose();
		super.render(harpoon, yaw, part, pose, buffer, light);
	}

	@Override
	public ResourceLocation getTextureLocation(ThrownHarpoon entity) {
		if (!entity.getItem().isEmpty() && entity.getItem().getItem() instanceof HarpoonItem harpoon) {
			if (harpoon.tier == TierDC.STEEL) {
				return HarpoonItem.STEEL.getTextureLocation();
			}
		}
		return HarpoonItem.FLINT.getTextureLocation();
	}
}
