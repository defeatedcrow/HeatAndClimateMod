package defeatedcrow.hac.magic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.magic.material.entity.CrowTurretEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CrowTurretModel extends EntityModel<CrowTurretEntity> {

	private final ModelPart base;
	private final ModelPart gun;

	public CrowTurretModel(ModelPart root) {
		this.base = root.getChild("base");
		this.gun = root.getChild("gun");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(41, 2).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition gun = partdefinition.addOrReplaceChild("gun", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 6.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(0, 22).addBox(-3.0F, 1.0F, -10.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 26).addBox(-3.0F, -4.0F, -10.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(7, 15).addBox(2.0F, -3.0F, -10.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 15).addBox(-3.0F, -3.0F, -10.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 37).addBox(-3.5F, -7.0F, -1.0F, 7.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(31, 41).addBox(-3.0F, -9.0F, -1.5F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(11, 50).addBox(3.0F, -6.0F, 0.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(0, 50).addBox(-4.0F, -6.0F, 0.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(23, 41).addBox(-1.0F, -6.0F, 7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(23, 38).addBox(-1.0F, -7.5F, -2.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(CrowTurretEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		this.gun.yRot = headYaw * ((float) Math.PI / 180F);
		this.gun.xRot = headPitch * ((float) Math.PI / 180F);
	}

	public void renderBody(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		gun.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
