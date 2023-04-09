package defeatedcrow.hac.core.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;

import defeatedcrow.hac.core.client.entity.EntityModelLoader;
import defeatedcrow.hac.core.client.entity.model.ModelMagicFin;
import defeatedcrow.hac.core.client.entity.model.ModelMagicWing;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderPlayerEventDC {

	private static final ResourceLocation WINGS_LOCATION = new ResourceLocation("dcs_climate:textures/entity/magic/magic_wing.png");
	private static final ResourceLocation FIN_LOCATION = new ResourceLocation("dcs_climate:textures/entity/magic/magic_fin.png");

	@SubscribeEvent
	public static void renderWings(RenderPlayerEvent.Post event) {
		Player player = event.getEntity();
		if (player != null && player instanceof AbstractClientPlayer) {
			ItemStack item = player.getItemBySlot(EquipmentSlot.CHEST);
			if (!hasElytra(item) && !player.hasPose(Pose.SLEEPING) && !player.isInFluidType()) {
				float f = -Mth.lerp(event.getPartialTick(), player.yBodyRotO, player.yBodyRot);
				float f2 = player.isCrouching() ? 5F : 0.0F;
				double y = 2.75D;
				boolean flag = player.hasEffect(CoreInit.BIRD.get());
				if (flag) {
					PoseStack pose = event.getPoseStack();
					pose.pushPose();
					pose.translate(0D, y, 0D);
					pose.mulPose(Vector3f.YP.rotationDegrees(f));
					pose.mulPose(Vector3f.XP.rotationDegrees(180.0F + f2));
					pose.scale(1.5F, 1.5F, 1.5F);
					ModelMagicWing model = EntityModelLoader.INSTANCE.MODEL_WING;
					model.setupAnim(player, 0F, 0F, event.getPartialTick(), 0F, 0F);
					VertexConsumer vertex = event.getMultiBufferSource().getBuffer(model.renderType(WINGS_LOCATION));
					model.renderToBuffer(pose, vertex, event.getPackedLight(), OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
					pose.popPose();
				}
			}
			if (!hasElytra(item) && !player.hasPose(Pose.SLEEPING)) {
				float f = -Mth.lerp(event.getPartialTick(), player.yBodyRotO, player.yBodyRot);
				float f2 = player.isCrouching() ? 5F : 0.0F;
				double y = 3.0F;
				boolean flag = player.hasEffect(CoreInit.FISH.get());
				if (flag) {
					PoseStack pose = event.getPoseStack();
					pose.pushPose();
					pose.translate(0D, y, 0D);
					pose.mulPose(Vector3f.YP.rotationDegrees(f));
					pose.mulPose(Vector3f.XP.rotationDegrees(180.0F + f2));
					pose.scale(2F, 2F, 2F);
					ModelMagicFin model = EntityModelLoader.INSTANCE.MODEL_FIN;
					model.setupAnim(player, 0F, 0F, event.getPartialTick(), 0F, 0F);
					VertexConsumer vertex = event.getMultiBufferSource().getBuffer(model.renderType(FIN_LOCATION));
					model.renderToBuffer(pose, vertex, event.getPackedLight(), OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
					pose.popPose();
				}
			}
		}
	}

	private static boolean hasElytra(ItemStack stack) {
		return stack.getItem() == Items.ELYTRA;
	}

}
