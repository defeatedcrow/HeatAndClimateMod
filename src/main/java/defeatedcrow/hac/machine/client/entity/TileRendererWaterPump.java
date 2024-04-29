package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.block.machine.WaterPumpTile;
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
public class TileRendererWaterPump implements BlockEntityRenderer<WaterPumpTile> {

	protected WaterPumpModel model;
	protected CableModel cable;

	public TileRendererWaterPump(BlockEntityRendererProvider.Context ctx) {
		this.model = new WaterPumpModel(ctx.bakeLayer(TEX.getLayerLocation()));
		this.cable = new CableModel(ctx.bakeLayer(TileRendererCopperCable.COPPER_COATED.getLayerLocation()));
	}

	@Override
	public void render(WaterPumpTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);
			ResourceLocation tex2 = TileRendererCopperCable.COPPER_COATED.getTextureLocation();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0F, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(1F, 1F, 1F);

			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(TEX.getTextureLocation())), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			poseStack.popPose();

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.scale(1F, 1F, 1F);

			if (DCState.getBool(tile.getLevel().getBlockState(tile.getBlockPos().relative(Direction.NORTH)), DCState.SOUTH)) {
				this.cable.renderSideN(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (DCState.getBool(tile.getLevel().getBlockState(tile.getBlockPos().relative(Direction.SOUTH)), DCState.NORTH)) {
				this.cable.renderSideS(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (DCState.getBool(tile.getLevel().getBlockState(tile.getBlockPos().relative(Direction.WEST)), DCState.EAST)) {
				this.cable.renderSideW(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (DCState.getBool(tile.getLevel().getBlockState(tile.getBlockPos().relative(Direction.EAST)), DCState.WEST)) {
				this.cable.renderSideE(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			poseStack.popPose();

		}
	}

	public static final EntityRenderData TEX = new EntityRenderData("tile/water_pump", 1F, 0F);

}
