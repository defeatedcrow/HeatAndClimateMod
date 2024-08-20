package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.client.entity.model.BlockChandelier2Model;
import defeatedcrow.hac.core.client.entity.model.BlockChandelier3Model;
import defeatedcrow.hac.core.client.entity.model.BlockChandelierModel;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.block.building.ChandelierTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererChandelier implements BlockEntityRenderer<ChandelierTile> {

	protected BlockChandelierModel model;
	protected BlockChandelier2Model model2;
	protected BlockChandelier3Model model3;

	public TileRendererChandelier(BlockEntityRendererProvider.Context ctx) {
		this.model = new BlockChandelierModel(ctx.bakeLayer(ChandelierTile.CHALCEDONY.getLayerLocation()));
		this.model2 = new BlockChandelier2Model(ctx.bakeLayer(ChandelierTile.IRON.getLayerLocation()));
		this.model3 = new BlockChandelier3Model(ctx.bakeLayer(ChandelierTile.CANDLE.getLayerLocation()));
	}

	@Override
	public void render(ChandelierTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			EntityRenderData data = tile.getRenderData(block);
			ResourceLocation tex = data.getTextureLocation();
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5D, 0.5F);
			poseStack.mulPose(Direction.DOWN.getRotation());
			poseStack.scale(f1, f1, f1);
			poseStack.translate(0F, f2, 0F);

			int i = 15728880;
			int j = i % 65536;
			int k = i / 65536;
			int l = j | k << 16;

			if (block == BuildInit.CHANDELIER_IRON.get()) {
				this.model2.renderToBuffer(poseStack, buffer.getBuffer(model2.renderType(tex)), l, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				this.model2.renderFlowers(poseStack, buffer.getBuffer(RenderType.beaconBeam(tex, false)).uv2(240), l, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			} else if (block == BuildInit.CHANDELIER_CANDLE.get()) {
				this.model3.renderToBuffer(poseStack, buffer.getBuffer(model3.renderType(tex)), l, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			} else {
				this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), l, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				this.model.renderFlowers(poseStack, buffer.getBuffer(RenderType.beaconBeam(tex, false)).uv2(240), l, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			poseStack.popPose();
		}
	}

}
