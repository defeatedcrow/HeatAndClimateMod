package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.block.transport.ConveyorSortingTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererConveyorSorter implements BlockEntityRenderer<ConveyorSortingTile> {

	private final ItemRenderer itemRenderer;

	public TileRendererConveyorSorter(BlockEntityRendererProvider.Context ctx) {
		this.itemRenderer = ctx.getItemRenderer();
	}

	@Override
	public void render(ConveyorSortingTile tile, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i2) {
		if (tile != null && tile.getBlockState() != null) {
			Direction dir = tile.getBlockDir();
			Direction dir2 = tile.getOutputDirection();
			if (dir == null)
				dir = Direction.NORTH;
			if (dir2 == null)
				dir2 = Direction.NORTH;
			int i = (int) tile.getBlockPos().asLong();

			if (!DCUtil.isEmpty(tile.disp)) {
				float offset = tile.prevMove - 4F + partialTicks;
				if (tile.move == tile.prevMove) {
					offset = tile.move - 4F;
				} else if (tile.move > 0 && tile.prevMove == 0) {
					offset = tile.move - 5F + partialTicks;
				}
				offset /= 8F;
				float offsetY = -0.2F;
				ItemStack disp = tile.disp.copy();

				float f1 = dir.getAxis() == Direction.Axis.X ? dir.getStepX() * offset : 0F;
				float f2 = dir.getAxis() == Direction.Axis.X ? 0F : dir.getStepZ() * offset;
				if (tile.prevMove > 4) {
					f1 = dir2.getAxis() == Direction.Axis.X ? dir2.getStepX() * offset : 0F;
					f2 = dir2.getAxis() == Direction.Axis.X ? 0F : dir2.getStepZ() * offset;
				}

				poseStack.pushPose();
				poseStack.translate(0.5D, 0.5D, 0.5D);
				float f = 90 - dir.toYRot();
				poseStack.translate(f1, offsetY, f2);
				poseStack.mulPose(Vector3f.YP.rotationDegrees(f));
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.itemRenderer.renderStatic(disp, ItemTransforms.TransformType.FIXED, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, i);
				poseStack.popPose();
			} else if (!DCUtil.isEmpty(tile.lastDisp) && tile.prevMove > 0) {
				float offset = tile.prevMove - 4F + partialTicks;
				offset /= 8F;
				float offsetY = -0.2F;
				ItemStack disp = tile.lastDisp.copy();

				float f1 = dir.getAxis() == Direction.Axis.X ? dir.getStepX() * offset : 0F;
				float f2 = dir.getAxis() == Direction.Axis.X ? 0F : dir.getStepZ() * offset;
				if (tile.prevMove > 4) {
					f1 = dir2.getAxis() == Direction.Axis.X ? dir2.getStepX() * offset : 0F;
					f2 = dir2.getAxis() == Direction.Axis.X ? 0F : dir2.getStepZ() * offset;
				}

				poseStack.pushPose();
				poseStack.translate(0.5D, 0.5D, 0.5D);
				float f = 90 - dir.toYRot();
				poseStack.translate(f1, offsetY, f2);
				poseStack.mulPose(Vector3f.YP.rotationDegrees(f));
				poseStack.scale(0.5F, 0.5F, 0.5F);
				this.itemRenderer.renderStatic(disp, ItemTransforms.TransformType.FIXED, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, i);
				poseStack.popPose();
			}

		}
	}
}
