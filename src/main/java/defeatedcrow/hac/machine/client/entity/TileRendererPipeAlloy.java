package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.machine.material.block.FluidPipeAlloyTile;
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
public class TileRendererPipeAlloy implements BlockEntityRenderer<FluidPipeAlloyTile> {

	protected CableModel model;

	public TileRendererPipeAlloy(BlockEntityRendererProvider.Context ctx) {
		this.model = new CableModel(ctx.bakeLayer(BRASS.getLayerLocation()));
	}

	@Override
	public void render(FluidPipeAlloyTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			ResourceLocation tex = getCableTexture(FaceIO.PIPE, block);

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.scale(1F, 1F, 1F);

			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			if (tile.getFluidHandler().getFace(Direction.NORTH) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getFluidHandler().getFace(Direction.NORTH), block);
				this.model.renderSideN(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getFluidHandler().getFace(Direction.SOUTH) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getFluidHandler().getFace(Direction.SOUTH), block);
				this.model.renderSideS(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getFluidHandler().getFace(Direction.WEST) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getFluidHandler().getFace(Direction.WEST), block);
				this.model.renderSideW(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getFluidHandler().getFace(Direction.EAST) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getFluidHandler().getFace(Direction.EAST), block);
				this.model.renderSideE(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getFluidHandler().getFace(Direction.DOWN) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getFluidHandler().getFace(Direction.DOWN), block);
				this.model.renderSideD(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getFluidHandler().getFace(Direction.UP) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getFluidHandler().getFace(Direction.UP), block);
				this.model.renderSideU(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			poseStack.popPose();

		}
	}

	protected ResourceLocation getCableTexture(FaceIO io, Block block) {
		if (io == FaceIO.INPUT) {
			return BRASS_INPUT.getTextureLocation();
		} else if (io == FaceIO.OUTPUT) {
			return BRASS_OUTLET.getTextureLocation();
		}
		return BRASS.getTextureLocation();
	}

	public static final EntityRenderData BRASS = new EntityRenderData("tile/pipe_brass", 1F, 0F);
	public static final EntityRenderData BRASS_INPUT = new EntityRenderData("tile/pipe_brass_input", 1F, 0F);
	public static final EntityRenderData BRASS_OUTLET = new EntityRenderData("tile/pipe_brass_outlet", 1F, 0F);

}
