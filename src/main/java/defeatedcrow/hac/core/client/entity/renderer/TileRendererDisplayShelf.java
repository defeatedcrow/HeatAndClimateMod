package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.building.ItemDisplayTile;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererDisplayShelf implements BlockEntityRenderer<ItemDisplayTile> {

	private BlockRenderDispatcher renderer;
	private final ItemRenderer itemRenderer;

	public TileRendererDisplayShelf(BlockEntityRendererProvider.Context ctx) {
		renderer = ctx.getBlockRenderDispatcher();
		this.itemRenderer = ctx.getItemRenderer();
	}

	@Override
	public void render(ItemDisplayTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Direction dir = DCState.getFace(tile.getBlockState(), DCState.FACING);
			if (dir == null)
				dir = Direction.NORTH;
			int i = (int) tile.getBlockPos().asLong();
			float[] offsetX = { -0.3F, -0.15F, 0.0F, 0.15F, 0.3F };
			float[] offsetZ = { -0.375F, -0.125F, -0.375F, -0.125F, -0.375F };

			for (int j = 0; j < 5; j++) {
				if (!DCUtil.isEmpty(tile.getDisplay(j))) {
					float offsetY = -0.2F;
					ItemStack disp = tile.getDisplay(j).copy().split(1);
					boolean lit = DCState.getBool(tile.getBlockState(), DCState.LIT);
					if (disp.getItem() instanceof BlockItem blockitem) {
						Block block = blockitem.getBlock();
						if (block.defaultBlockState().getLightEmission(tile.getLevel(), tile.getBlockPos()) > 0) {
							lit = true;
						}
					}

					float f1 = dir.getAxis() == Direction.Axis.X ? dir.getStepX() * offsetZ[j] : dir.getStepZ() * offsetX[j];
					float f2 = dir.getAxis() == Direction.Axis.X ? -dir.getStepX() * offsetX[j] : dir.getStepZ() * offsetZ[j];
					int l = lit ? 15728880 : packedLight;

					poseStack.pushPose();
					poseStack.translate(0.5D, 0.5D, 0.5D);
					float f = 180 - dir.toYRot();
					poseStack.translate(f1, offsetY, f2);
					poseStack.mulPose(Vector3f.YP.rotationDegrees(f));
					poseStack.scale(0.3F, 0.3F, 0.3F);
					this.itemRenderer.renderStatic(disp, ItemTransforms.TransformType.FIXED, l, OverlayTexture.NO_OVERLAY, poseStack, buffer, i);
					poseStack.popPose();
				}
			}

		}
	}

}
