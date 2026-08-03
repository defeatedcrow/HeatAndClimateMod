package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.client.entity.model.HammockModel;
import defeatedcrow.hac.core.material.block.building.NoSaveHammockTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererHammock implements BlockEntityRenderer<NoSaveHammockTile> {

	protected HammockModel model;
	public static final EntityRenderData TEX = new EntityRenderData("tile/hammock_rope", 1F, 0.0F);

	public TileRendererHammock(BlockEntityRendererProvider.Context ctx) {
		this.model = new HammockModel(ctx.bakeLayer(TEX.getLayerLocation()));
	}

	@Override
	public void render(NoSaveHammockTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null && tile.hasLevel()) {
			BlockState block = tile.getBlockState();
			if (DCState.getBool(block, DCState.FLAG)) {
				ResourceLocation tex = TEX.getTextureLocation();
				float f1 = TEX.getModelScale();
				float f2 = TEX.getAdjustY();
				Direction dir = DCState.getFace(block, DCState.FACING);

				poseStack.pushPose();
				poseStack.translate(0.5F, f2, 0.5F);
				poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
				poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
				poseStack.scale(f1, f1, f1);
				this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				if (hasSolidWall(tile, dir)) {
					this.model.renderRope(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				} else {
					this.model.renderLegs(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				}
				poseStack.popPose();
			}
		}
	}

	private boolean hasSolidWall(NoSaveHammockTile tile, Direction dir) {
		Level level = tile.getLevel();
		BlockPos pos = tile.getBlockPos();
		return level.getBlockState(pos.relative(dir.getOpposite()))
		    .getMaterial()
		    .isSolid()
		    && level.getBlockState(pos.relative(dir, 2))
		        .getMaterial()
		        .isSolid();
	}

}
