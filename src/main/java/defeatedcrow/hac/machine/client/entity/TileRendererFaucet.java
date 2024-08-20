package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.transport.FaucetTile;
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
public class TileRendererFaucet implements BlockEntityRenderer<FaucetTile> {

	protected FaucetModel_A model_A;
	protected FaucetModel_B model_B;
	protected FaucetModel_C model_C;
	protected FaucetModel_D model_D;

	public TileRendererFaucet(BlockEntityRendererProvider.Context ctx) {
		this.model_A = new FaucetModel_A(ctx.bakeLayer(FaucetTile.TYPE_A.getLayerLocation()));
		this.model_B = new FaucetModel_B(ctx.bakeLayer(FaucetTile.TYPE_B.getLayerLocation()));
		this.model_C = new FaucetModel_C(ctx.bakeLayer(FaucetTile.TYPE_C.getLayerLocation()));
		this.model_D = new FaucetModel_D(ctx.bakeLayer(FaucetTile.TYPE_D.getLayerLocation()));
	}

	@Override
	public void render(FaucetTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			EntityRenderData data = tile.getRenderData(block);
			ResourceLocation tex = data.getTextureLocation();
			ResourceLocation tex2 = data.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING).getOpposite();
			boolean flag = DCState.getBool(tile.getBlockState(), DCState.POWERED);
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(f1, f1, f1);

			if (block == MachineInit.FAUCET_A.get()) {
				this.model_A.setupAnim(flag);
				this.model_A.renderToBuffer(poseStack, buffer.getBuffer(model_A.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}
			if (block == MachineInit.FAUCET_B.get()) {
				this.model_B.setupAnim(flag);
				this.model_B.renderToBuffer(poseStack, buffer.getBuffer(model_B.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}
			if (block == MachineInit.FAUCET_C.get()) {
				this.model_C.setupAnim(flag);
				this.model_C.renderToBuffer(poseStack, buffer.getBuffer(model_C.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}
			if (block == MachineInit.FAUCET_D.get()) {
				this.model_D.renderToBuffer(poseStack, buffer.getBuffer(model_D.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			poseStack.popPose();

		}
	}

}
