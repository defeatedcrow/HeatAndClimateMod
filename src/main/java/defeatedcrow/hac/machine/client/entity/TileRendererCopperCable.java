package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.machine.FaceIO;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.transport.CableCopperTile;
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
public class TileRendererCopperCable implements BlockEntityRenderer<CableCopperTile> {

	protected CableModel model;

	public TileRendererCopperCable(BlockEntityRendererProvider.Context ctx) {
		this.model = new CableModel(ctx.bakeLayer(COPPER.getLayerLocation()));
	}

	@Override
	public void render(CableCopperTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			ResourceLocation tex = getCableTexture(FaceIO.PIPE, block);
			if (DCState.getBool(tile.getBlockState(), DCState.POWERED)) {
				tex = getPoweredTexture(block);
			}

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.scale(1F, 1F, 1F);

			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			if (tile.getEnergyHandler().getFace(Direction.NORTH) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getEnergyHandler().getFace(Direction.NORTH), block);
				this.model.renderSideN(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getEnergyHandler().getFace(Direction.SOUTH) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getEnergyHandler().getFace(Direction.SOUTH), block);
				this.model.renderSideS(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getEnergyHandler().getFace(Direction.WEST) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getEnergyHandler().getFace(Direction.WEST), block);
				this.model.renderSideW(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getEnergyHandler().getFace(Direction.EAST) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getEnergyHandler().getFace(Direction.EAST), block);
				this.model.renderSideE(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getEnergyHandler().getFace(Direction.DOWN) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getEnergyHandler().getFace(Direction.DOWN), block);
				this.model.renderSideD(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			if (tile.getEnergyHandler().getFace(Direction.UP) != FaceIO.NONE) {
				ResourceLocation tex2 = getCableTexture(tile.getEnergyHandler().getFace(Direction.UP), block);
				this.model.renderSideU(poseStack, buffer.getBuffer(model.renderType(tex2)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			}

			poseStack.popPose();

		}
	}

	protected ResourceLocation getPoweredTexture(Block block) {
		if (block == MachineInit.CABLE_COPPER.get()) {
			return COPPER_POWERED.getTextureLocation();
		} else if (block == MachineInit.CABLE_COPPER_COATED.get()) {
			return COPPER_COATED_POWERED.getTextureLocation();
		}
		return COPPER_POWERED.getTextureLocation();
	}

	protected ResourceLocation getCableTexture(FaceIO io, Block block) {
		if (block == MachineInit.CABLE_COPPER.get()) {
			if (io == FaceIO.INPUT) {
				return COPPER_INPUT.getTextureLocation();
			} else if (io == FaceIO.OUTPUT) {
				return COPPER_OUTLET.getTextureLocation();
			}
			return COPPER.getTextureLocation();
		} else if (block == MachineInit.CABLE_COPPER_COATED.get()) {
			if (io == FaceIO.INPUT) {
				return COPPER_COATED_INPUT.getTextureLocation();
			} else if (io == FaceIO.OUTPUT) {
				return COPPER_COATED_OUTLET.getTextureLocation();
			}
			return COPPER_COATED.getTextureLocation();
		}
		return COPPER.getTextureLocation();
	}

	public static final EntityRenderData COPPER = new EntityRenderData("tile/cable_copper", 1F, 0F);
	public static final EntityRenderData COPPER_INPUT = new EntityRenderData("tile/cable_copper_input", 1F, 0F);
	public static final EntityRenderData COPPER_OUTLET = new EntityRenderData("tile/cable_copper_outlet", 1F, 0F);
	public static final EntityRenderData COPPER_POWERED = new EntityRenderData("tile/cable_copper_powered", 1F, 0F);

	public static final EntityRenderData COPPER_COATED = new EntityRenderData("tile/cable_copper_coated", 1F, 0F);
	public static final EntityRenderData COPPER_COATED_INPUT = new EntityRenderData("tile/cable_copper_coated_input", 1F, 0F);
	public static final EntityRenderData COPPER_COATED_OUTLET = new EntityRenderData("tile/cable_copper_coated_outlet", 1F, 0F);
	public static final EntityRenderData COPPER_COATED_POWERED = new EntityRenderData("tile/cable_copper_coated_powered", 1F, 0F);

}
