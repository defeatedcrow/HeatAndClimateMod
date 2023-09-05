package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.client.entity.model.BlockLuggageModel;
import defeatedcrow.hac.core.material.block.building.LuggageTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererLuggage implements BlockEntityRenderer<LuggageTile> {

	protected BlockLuggageModel model;

	public TileRendererLuggage(BlockEntityRendererProvider.Context ctx) {
		this.model = new BlockLuggageModel(ctx.bakeLayer(LuggageTile.NORMAL.getLayerLocation()));
	}

	@Override
	public void render(LuggageTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			EntityRenderData data = tile.getRenderData(block);
			ResourceLocation tex = data.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5D + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(f1, f1, f1);

			float f = tile.getOpenNess(partialTicks) * -60F;

			this.model.setupAnim(tile, f);
			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			poseStack.popPose();
		}
	}

}
