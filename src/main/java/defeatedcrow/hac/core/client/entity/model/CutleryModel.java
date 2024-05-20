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

public class CutleryModel extends EntityModel<ObjectEntityBaseDC> {

	private final ModelPart chopsticks;
	private final ModelPart cutlery;
	private final ModelPart spoon;
	private final ModelPart fork;

	public CutleryModel(ModelPart root) {
		this.chopsticks = root.getChild("chopsticks");
		this.cutlery = root.getChild("cutlery");
		this.spoon = root.getChild("spoon");
		this.fork = root.getChild("fork");
	}

	public static LayerDefinition createLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition chopsticks = partdefinition.addOrReplaceChild("chopsticks", CubeListBuilder.create()
				.texOffs(0, 3).addBox(-6.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition c3_r1 = chopsticks.addOrReplaceChild("c3_r1", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-7.0F, -1.5F, 0.25F, 15.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -1.5F, -1.25F, 15.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cutlery = partdefinition.addOrReplaceChild("cutlery", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -1.0F, 1.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition c6_r1 = cutlery.addOrReplaceChild("c6_r1", CubeListBuilder.create()
				.texOffs(0, 3).addBox(-1.0F, -0.5F, -6.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition c5_r1 = cutlery.addOrReplaceChild("c5_r1", CubeListBuilder.create()
				.texOffs(2, 8).addBox(-1.0F, -1.5F, 2.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition spoon = partdefinition.addOrReplaceChild("spoon", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition c7_r1 = spoon.addOrReplaceChild("c7_r1", CubeListBuilder.create()
				.texOffs(18, 3).addBox(-2.0F, 0.0F, 4.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition fork = partdefinition.addOrReplaceChild("fork", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition c9_r1 = fork.addOrReplaceChild("c9_r1", CubeListBuilder.create()
				.texOffs(21, 13).addBox(-2.0F, 0.0F, 4.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 9).addBox(-2.0F, 0.5F, 6.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(ObjectEntityBaseDC entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		chopsticks.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderChopsticks(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		chopsticks.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSpoon(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cutlery.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spoon.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderFork(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cutlery.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fork.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
