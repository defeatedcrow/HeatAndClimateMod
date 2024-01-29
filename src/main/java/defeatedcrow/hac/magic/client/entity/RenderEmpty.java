package defeatedcrow.hac.magic.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.magic.material.entity.OwnableMagicEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class RenderEmpty extends EntityRenderer<OwnableMagicEntity> {

	public RenderEmpty(Context ctx) {
		super(ctx);
	}

	@Override
	public ResourceLocation getTextureLocation(OwnableMagicEntity entity) {
		return TEX.getTextureLocation();
	}

	@Override
	public void render(OwnableMagicEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}

	public static final EntityRenderData TEX = new EntityRenderData("magic/light_cauldron", 1.0F, 0F);

}
