package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.api.material.ITierItem;
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
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileRendererToolHook implements BlockEntityRenderer<ItemDisplayTile> {

	private BlockRenderDispatcher renderer;
	private final ItemRenderer itemRenderer;

	public TileRendererToolHook(BlockEntityRendererProvider.Context ctx) {
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

			if (!DCUtil.isEmpty(tile.getDisplay(0))) {
				ItemStack disp = tile.getDisplay(0).copy();
				boolean lit = DCState.getBool(tile.getBlockState(), DCState.LIT);

				float f1 = dir.getStepX() * 0.35F;
				float f2 = dir.getStepZ() * 0.35F;
				int l = lit ? 15728880 : packedLight;

				poseStack.pushPose();
				poseStack.translate(0.5D, 0.5D, 0.5D);
				float f = -dir.toYRot();
				poseStack.translate(f1, 0D, f2);
				poseStack.mulPose(Vector3f.YP.rotationDegrees(f));
				if ((!(disp.getItem() instanceof ArmorItem) && disp.isDamageableItem()) || disp.getItem() instanceof ITierItem) {
					poseStack.mulPose(Vector3f.ZP.rotationDegrees(135.0F));
				}
				poseStack.scale(0.75F, 0.75F, 0.75F);
				this.itemRenderer.renderStatic(disp, ItemTransforms.TransformType.FIXED, l, OverlayTexture.NO_OVERLAY, poseStack, buffer, i);
				poseStack.popPose();
			}
		}
	}

}
