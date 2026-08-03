package defeatedcrow.hac.core.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.core.material.entity.ObjectEntityBaseDC;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class HammockModel extends EntityModel<ObjectEntityBaseDC> {

	private final ModelPart legs;
	private final ModelPart cloth;
	private final ModelPart rope;

	public HammockModel(ModelPart root) {
		this.legs = root.getChild("legs");
		this.cloth = root.getChild("cloth");
		this.rope = root.getChild("rope");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition legs = partdefinition.addOrReplaceChild("legs", CubeListBuilder.create()
		    .texOffs(0, 19)
		    .addBox(7.0F, -1.0F, -8.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 19)
		    .addBox(-8.0F, -1.0F, -8.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = legs.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(0, 14)
		    .addBox(19.0F, -21.0F, 4.5F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 2.2689F, 0.0F));

		PartDefinition cube_r2 = legs.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(0, 14)
		    .addBox(-20.0F, -21.0F, 4.5F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, -2.2689F, 0.0F));

		PartDefinition cube_r3 = legs.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(0, 14)
		    .addBox(7.0F, -14.0F, -3.5F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, -0.8727F, 0.0F));

		PartDefinition cube_r4 = legs.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(0, 14)
		    .addBox(-8.0F, -14.0F, -3.5F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.8727F, 0.0F));

		PartDefinition cube_r5 = legs.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(0, 19)
		    .addBox(-8.0F, -1.0F, 8.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 19)
		    .addBox(7.0F, -1.0F, 8.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cloth = partdefinition.addOrReplaceChild("cloth", CubeListBuilder.create()
		    .texOffs(-11, 1)
		    .addBox(-6.0F, -8.0F, -8.0F, 12.0F, 0.0F, 11.0F, new CubeDeformation(0.0F))
		    .texOffs(-11, 0)
		    .addBox(-6.0F, -8.0F, -19.0F, 12.0F, 0.0F, 11.0F, new CubeDeformation(0.0F))
		    .texOffs(28, 30)
		    .addBox(-6.0F, -8.5F, -19.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(28, 30)
		    .addBox(-6.0F, -8.5F, 2.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r6 = cloth.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(16, 0)
		    .addBox(-6.0F, 2.5F, 20.5F, 12.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 3.1416F, 0.0F));

		PartDefinition cube_r7 = cloth.addOrReplaceChild("cube_r7", CubeListBuilder.create()
		    .texOffs(16, 0)
		    .addBox(-6.0F, -5.5F, 6.5F, 12.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition rope = partdefinition.addOrReplaceChild("rope", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r8 = rope.addOrReplaceChild("cube_r8", CubeListBuilder.create()
		    .texOffs(18, 8)
		    .addBox(-1.0F, 2.5F, 28.5F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 3.1416F, 0.0F));

		PartDefinition cube_r9 = rope.addOrReplaceChild("cube_r9", CubeListBuilder.create()
		    .texOffs(18, 8)
		    .addBox(-1.0F, -5.5F, 14.5F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(ObjectEntityBaseDC entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cloth.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderLegs(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		legs.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderRope(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rope.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
