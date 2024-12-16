package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.client.entity.model.BlockRoundChairModel;
import defeatedcrow.hac.core.material.block.building.ChairRoundTile;
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
public class TileRendererChairRound implements BlockEntityRenderer<ChairRoundTile> {

	protected BlockRoundChairModel model;

	public TileRendererChairRound(BlockEntityRendererProvider.Context ctx) {
		this.model = new BlockRoundChairModel(ctx.bakeLayer(ChairRoundTile.WHITE.getLayerLocation()));
	}

	@Override
	public void render(ChairRoundTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			EntityRenderData data = tile.getRenderData(block);
			ResourceLocation tex = data.getTextureLocation();
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5D + f2, 0.5F);
			poseStack.mulPose(Direction.DOWN.getRotation());
			poseStack.scale(f1, f1, f1);

			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			poseStack.popPose();
		}
	}

}
