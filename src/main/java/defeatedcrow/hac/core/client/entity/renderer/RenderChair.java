package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.material.entity.ChairEntity;
import defeatedcrow.hac.magic.client.entity.RenderBindPlant;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderChair extends EntityRenderer<ChairEntity> {

	public RenderChair(Context ctx) {
		super(ctx);
	}

	@Override
	public ResourceLocation getTextureLocation(ChairEntity entity) {
		return RenderBindPlant.PLANT.getTextureLocation();
	}

	@Override
	public void render(ChairEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

	}
}
