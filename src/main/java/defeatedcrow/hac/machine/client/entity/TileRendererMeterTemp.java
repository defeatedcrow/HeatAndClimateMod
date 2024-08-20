package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.block.monitor.MonitorTempTile;
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
public class TileRendererMeterTemp implements BlockEntityRenderer<MonitorTempTile> {

	protected MonitorPanelModel model;

	public TileRendererMeterTemp(BlockEntityRendererProvider.Context ctx) {
		this.model = new MonitorPanelModel(ctx.bakeLayer(NORMAL.getLayerLocation()));
	}

	@Override
	public void render(MonitorTempTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			ResourceLocation tex = NORMAL.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING).getOpposite();
			float rot = -90F;
			switch (tile.amount) {
			case 0:
				rot = -90.0F;
				break;
			case 1:
				rot = -77.5F;
				break;
			case 2:
				rot = -65.0F;
				break;
			case 3:
				rot = -50.0F;
				break;
			case 4:
				rot = -35.0F;
				break;
			case 5:
				rot = -20.0F;
				break;
			case 6:
				rot = -7.5F;
				break;
			case 7:
				rot = 7.5F;
				break;
			case 8:
				rot = 20.0F;
				break;
			case 9:
				rot = 35.0F;
				break;
			case 10:
				rot = 50.0F;
				break;
			case 11:
				rot = 65.0F;
				break;
			case 12:
				rot = 77.5F;
				break;
			case 13:
				rot = 90.0F;
				break;
			default:
				rot = -90F;
			}

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

	public static final EntityRenderData NORMAL = new EntityRenderData("tile/meterpanel", 1F, 0F);

}
