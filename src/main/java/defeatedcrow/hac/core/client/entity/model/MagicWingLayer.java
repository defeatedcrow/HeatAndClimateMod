package defeatedcrow.hac.core.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.core.client.entity.EntityModelLoader;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicWingLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
	private static final ResourceLocation WINGS_LOCATION = new ResourceLocation("dcs_climate:textures/entity/magic/magic_wing.png");
	private final ModelMagicWing wingModel;

	public MagicWingLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> render, EntityModelSet modelSet) {
		super(render);
		this.wingModel = new ModelMagicWing<>(modelSet.bakeLayer(EntityModelLoader.WING.getLayerLocation()));
	}

	@Override
	public void render(PoseStack pose, MultiBufferSource buffer, int light, AbstractClientPlayer player, float f1, float f2, float f3, float f4, float f5, float f6) {
		boolean flag = player.hasEffect(MobEffects.REGENERATION);
		if (flag) {
			pose.pushPose();
			pose.translate(0.0D, 0.0D, 0.0D);
			this.wingModel.crouching = this.getParentModel().crouching;
			this.wingModel.setupAnim(player, f1, f2, f3, f5, f6);
			VertexConsumer vertex = buffer.getBuffer(wingModel.renderType(WINGS_LOCATION));
			this.wingModel.renderToBuffer(pose, vertex, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			pose.popPose();
		}
	}
}
