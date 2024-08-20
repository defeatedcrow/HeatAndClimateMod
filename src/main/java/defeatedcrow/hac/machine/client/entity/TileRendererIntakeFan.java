package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.block.machine.IntakeFanTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererIntakeFan implements BlockEntityRenderer<IntakeFanTile> {

	protected IntakeFanModel model;

	public TileRendererIntakeFan(BlockEntityRendererProvider.Context ctx) {
		this.model = new IntakeFanModel(ctx.bakeLayer(IntakeFanTile.NORMAL.getLayerLocation()));
	}

	@Override
	public void render(IntakeFanTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			BlockState state = tile.getBlockState();
			EntityRenderData data = tile.getRenderData(state.getBlock());
			ResourceLocation tex = data.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING).getOpposite();
			float f1 = data.getModelScale();
			float f2 = data.getAdjustY();
			float f3 = tile.rotation - tile.lastRotation;
			if (f3 < 0) {
				f3 += 360F;
			}
			float rot = tile.lastRotation + (f3 * partialTicks);
			boolean b1 = DCState.getBool(state, DCState.FLAG);
			boolean b2 = DCState.getBool(state, DCState.POWERED) || tile.getEnergyHandler().getEnergyStored() == 0;

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F + f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(f1, f1, f1);

			this.model.setupAnim(rot);
			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			this.model.renderButton(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F, b1, !b2);

			poseStack.popPose();

		}
	}

}
