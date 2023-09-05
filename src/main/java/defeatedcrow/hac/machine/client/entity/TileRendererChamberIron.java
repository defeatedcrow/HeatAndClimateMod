package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.block.HeatingChamberTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererChamberIron implements BlockEntityRenderer<HeatingChamberTile> {

	protected BlockChamberIronModel model;
	private BlockRenderDispatcher renderer;

	public TileRendererChamberIron(BlockEntityRendererProvider.Context ctx) {
		this.model = new BlockChamberIronModel(ctx.bakeLayer(DATA.getLayerLocation()));
		renderer = ctx.getBlockRenderDispatcher();
	}

	@Override
	public void render(HeatingChamberTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Block block = tile.getBlockState().getBlock();
			ResourceLocation tex = DATA.getTextureLocation();
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);
			float f1 = DATA.getModelScale();
			float f2 = DATA.getAdjustY();

			poseStack.pushPose();
			poseStack.translate(0.5F, f2, 0.5F);
			poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Vector3f.YP.rotationDegrees(dir.toYRot()));
			poseStack.scale(f1, f1, f1);

			this.model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(tex)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			poseStack.popPose();

			DCHeatTier temp = tile.getHeatTier();
			if (temp.getID() > DCHeatTier.HOT.getID()) {
				BlockState fire = Blocks.FIRE.defaultBlockState();
				float f3 = 0.5F;
				if (temp.getID() < DCHeatTier.KILN.getID()) {
					f3 = 0.25F;
				} else if (temp.getID() > DCHeatTier.KILN.getID()) {
					f3 = 0.8F;
				}
				float f4 = 1F - f3;
				f4 *= 0.5F;

				poseStack.pushPose();
				poseStack.translate(f4, 0D, f4);
				poseStack.scale(f3, f3, f3);
				this.renderer.renderSingleBlock(fire, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
				poseStack.popPose();
			}

		}
	}

	public static final EntityRenderData DATA = new EntityRenderData("tile/heating_chamber", 1F, 0F);

}
