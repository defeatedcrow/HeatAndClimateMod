package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.block.monitor.MonitorEnergyTile;
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
public class TileRendererMeterEnergy implements BlockEntityRenderer<MonitorEnergyTile> {

	protected MonitorPanelModel model;

	public TileRendererMeterEnergy(BlockEntityRendererProvider.Context ctx) {
		this.model = new MonitorPanelModel(ctx.bakeLayer(TileRendererMeterTemp.NORMAL.getLayerLocation()));
	}

	@Override
	public void render(MonitorEnergyTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			ResourceLocation tex = TileRendererMeterTemp.NORMAL.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING).getOpposite();
			float rot = tile.last + (tile.amount - tile.last) * partialTicks;
			rot = rot * 2F;
			rot -= 90F;

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(1F, 1F, 1F);

			this.model.setupAnim(rot);
			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			poseStack.popPose();

		}
	}

}
