package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.TeaPotTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererTeaPot implements BlockEntityRenderer<TeaPotTile> {

	protected TeaPotModel_A model_A;
	protected TeaPotModel_B model_B;
	protected TeaPotModel_C model_C;
	private BlockRenderDispatcher renderer;

	public TileRendererTeaPot(BlockEntityRendererProvider.Context ctx) {
		this.model_A = new TeaPotModel_A(ctx.bakeLayer(TeaPotTile.NORMAL.getLayerLocation()));
		this.model_B = new TeaPotModel_B(ctx.bakeLayer(TeaPotTile.BLUE.getLayerLocation()));
		this.model_C = new TeaPotModel_C(ctx.bakeLayer(TeaPotTile.WHITE.getLayerLocation()));
		renderer = ctx.getBlockRenderDispatcher();
	}

	@Override
	public void render(TeaPotTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			EntityRenderData data = tile.getRenderData(block);
			ResourceLocation tex = data.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();
			boolean isB = block == MachineInit.TEA_POT_BLUE.get() || block == MachineInit.TEA_POT_GREEN.get() || block == MachineInit.TEA_POT_RED.get();
			boolean isC = block == MachineInit.TEA_POT_WHITE.get();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot() + 90F));
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
