package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.machine.KichenBenchTile;
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
public class TileRendererKichenBench implements BlockEntityRenderer<KichenBenchTile> {

	protected KichenBenchLabModel_A model_A;
	protected KichenBenchLabModel_B model_B;
	protected KichenBenchSUSModel model_C;

	public TileRendererKichenBench(BlockEntityRendererProvider.Context ctx) {
		this.model_A = new KichenBenchLabModel_A(ctx.bakeLayer(KichenBenchTile.LAB_A.getLayerLocation()));
		this.model_B = new KichenBenchLabModel_B(ctx.bakeLayer(KichenBenchTile.LAB_B.getLayerLocation()));
		this.model_C = new KichenBenchSUSModel(ctx.bakeLayer(KichenBenchTile.SUS.getLayerLocation()));
	}

	@Override
	public void render(KichenBenchTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			if (block == MachineInit.KICHEN_BENCH_BRICK.get()) {
				return;
			}
			EntityRenderData data = tile.getRenderData(block);
			ResourceLocation tex = data.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();
			boolean isB = block == MachineInit.KICHEN_BENCH_WOOD_B.get() || block == MachineInit.KICHEN_BENCH_BLACK_B.get() || block == MachineInit.KICHEN_BENCH_LAB_B.get();
			boolean isC = block == MachineInit.KICHEN_BENCH_SUS.get();

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

			poseStack.popPose();
		}
	}
}
