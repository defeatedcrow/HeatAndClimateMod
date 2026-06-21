package defeatedcrow.hac.food.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class SausageModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;

	public SausageModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(0, 10)
		    .addBox(-5.0F, 1.0F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(0, 10)
		    .addBox(-5.0F, 1.0F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(0, 10)
		    .addBox(-5.0F, 1.0F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(0, 10)
		    .addBox(-5.0F, 1.0F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition meal = bowl.addOrReplaceChild("meal", CubeListBuilder.create()
		    .texOffs(0, 13)
		    .addBox(-2.3F, -1.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r5 = meal.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(11, 21)
		    .addBox(1.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, -0.4363F, 0.0F));

		PartDefinition cube_r6 = meal.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(11, 21)
		    .addBox(2.5F, -2.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r7 = meal.addOrReplaceChild("cube_r7", CubeListBuilder.create()
		    .texOffs(8, 18)
		    .addBox(0.0F, -1.0F, 1.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.2618F, 0.0F));

		PartDefinition cube_r8 = meal.addOrReplaceChild("cube_r8", CubeListBuilder.create()
		    .texOffs(16, 18)
		    .addBox(-2.0F, -4.0F, 1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.9599F, 0.0F));

		PartDefinition cube_r9 = meal.addOrReplaceChild("cube_r9", CubeListBuilder.create()
		    .texOffs(8, 18)
		    .addBox(0.0F, -1.0F, -3.0F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, -1.3963F, 0.0F));

		PartDefinition cube_r10 = meal.addOrReplaceChild("cube_r10", CubeListBuilder.create()
		    .texOffs(11, 13)
		    .addBox(2.0F, -2.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition cube_r11 = meal.addOrReplaceChild("cube_r11", CubeListBuilder.create()
		    .texOffs(0, 17)
		    .addBox(-0.8F, -2.4F, 2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, -1.0472F, 0.0F));

		PartDefinition cube_r12 = meal.addOrReplaceChild("cube_r12", CubeListBuilder.create()
		    .texOffs(0, 13)
		    .addBox(-1.7F, -2.5F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, -0.6981F, 0.0F));

		PartDefinition cube_r13 = meal.addOrReplaceChild("cube_r13", CubeListBuilder.create()
		    .texOffs(0, 21)
		    .addBox(-1.5F, -2.5F, -3.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, -0.4363F, 0.0F));

		PartDefinition cube_r14 = meal.addOrReplaceChild("cube_r14", CubeListBuilder.create()
		    .texOffs(0, 17)
		    .addBox(0.0F, -2.8F, 1.8F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r15 = meal.addOrReplaceChild("cube_r15", CubeListBuilder.create()
		    .texOffs(0, 21)
		    .addBox(-0.5F, -2.5F, -3.2F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, -0.5236F, 0.0F));

		PartDefinition cube_r16 = meal.addOrReplaceChild("cube_r16", CubeListBuilder.create()
		    .texOffs(0, 13)
		    .addBox(-0.5F, -2.5F, -0.7F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, -0.5236F, 0.0F));

		PartDefinition cube_r17 = meal.addOrReplaceChild("cube_r17", CubeListBuilder.create()
		    .texOffs(0, 17)
		    .addBox(-2.7F, -1.0F, 0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.4363F, 0.0F));

		PartDefinition cube_r18 = meal.addOrReplaceChild("cube_r18", CubeListBuilder.create()
		    .texOffs(0, 21)
		    .addBox(-2.5F, -1.5F, -4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.1745F, 0.0F));

		PartDefinition cube_r19 = meal.addOrReplaceChild("cube_r19", CubeListBuilder.create()
		    .texOffs(0, 21)
		    .addBox(-3.8F, -1.7F, -3.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.2618F, 0.0F));

		PartDefinition cube_r20 = meal.addOrReplaceChild("cube_r20", CubeListBuilder.create()
		    .texOffs(0, 17)
		    .addBox(-3.7F, -2.0F, 0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r21 = meal.addOrReplaceChild("cube_r21", CubeListBuilder.create()
		    .texOffs(0, 13)
		    .addBox(-3.5F, -1.8F, -1.7F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bowl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
