package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.core.client.entity.model.FlowerPotModel;
import defeatedcrow.hac.core.material.entity.FlowerPotEntity;
import defeatedcrow.hac.core.material.item.tool.FlowerPotItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.block.crops.LeavesCropBlockDC;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.ModelData;

@OnlyIn(Dist.CLIENT)
public class RenderFlowerPot extends EntityRenderer<FlowerPotEntity> {

	protected FlowerPotModel<FlowerPotEntity> model;
	private final BlockRenderDispatcher blockRenderer;

	public RenderFlowerPot(Context ctx) {
		super(ctx);
		this.model = new FlowerPotModel<>(ctx.bakeLayer(FlowerPotItem.WHITE.getLayerLocation()));
		this.blockRenderer = ctx.getBlockRenderDispatcher();
	}

	@Override
	public ResourceLocation getTextureLocation(FlowerPotEntity entity) {
		return FlowerPotItem.WHITE.getTextureLocation();
	}

	@Override
	public void render(FlowerPotEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		if (entity != null) {
			Item item = entity.getItem()
			    .getItem();
			if (item instanceof IEntityItem && ((IEntityItem) item).getRenderData(item) != null) {
				EntityRenderData data = ((IEntityItem) item).getRenderData(item);
				ResourceLocation tex = data.getTextureLocation();
				float f1 = data.getModelScale();
				float f2 = data.getAdjustY();

				poseStack.pushPose();
				poseStack.translate(0F, f2, 0F);
				poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.scale(f1, f1, f1);
				model.setupAnim(entity, 180.0F - yaw, partialTicks, packedLight, f1, f2);
				VertexConsumer vertex = buffer.getBuffer(model.renderType(tex));
				this.model.renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
				poseStack.popPose();
			}

			ItemStack flower = entity.getFlowerItem();
			if (!DCUtil.isEmpty(flower)) {
				BlockState flowerState = FlowerPotEntity.getRenderState(flower);
				poseStack.pushPose();
				poseStack.translate(0.0F, 0.0F, 0.0F);
				poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
				poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
				if (flowerState.getBlock() instanceof LeavesCropBlockDC || flowerState.getBlock() instanceof LeavesBlock) {
					poseStack.scale(0.45F, 0.45F, 0.45F);
					poseStack.translate(-0.5F, 0.85F, -0.5F);
				} else {
					poseStack.scale(0.65F, 0.65F, 0.65F);
					poseStack.translate(-0.5F, 0.5F, -0.5F);
				}

				this.blockRenderer.renderSingleBlock(flowerState, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, null);
				poseStack.popPose();
			}
		}
		super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
	}
}
