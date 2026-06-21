package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.block.machine.CoffeeMakerTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererCoffeeMacchinetta implements BlockEntityRenderer<CoffeeMakerTile> {

	protected CoffeeMacchinettaModel model;
	private BlockRenderDispatcher renderer;
	public static final EntityRenderData NORMAL = new EntityRenderData("tile/coffee_macchinetta", 1F, 0F);

	public TileRendererCoffeeMacchinetta(BlockEntityRendererProvider.Context ctx) {
		this.model = new CoffeeMacchinettaModel(ctx.bakeLayer(NORMAL.getLayerLocation()));
		renderer = ctx.getBlockRenderDispatcher();
	}

	@Override
	public void render(CoffeeMakerTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState()
			    .getBlock();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);

			poseStack.pushPose();
			poseStack.translate(0.5F, 0.0F, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(0.8F, 0.8F, 0.8F);
			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(NORMAL.getTextureLocation())), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			poseStack.popPose();

		}
	}

}
