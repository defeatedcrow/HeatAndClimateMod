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

public class MinidishModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart meal;

	public MinidishModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.meal = root.getChild("meal");
	}

	public static LayerDefinition createMainLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -0.8F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 2.3562F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -2.3562F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.7854F, 0.0F));

		PartDefinition cube_r5 = bowl.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 1.5708F, 0.0F));

		PartDefinition cube_r6 = bowl.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 3.1416F, 0.0F));

		PartDefinition cube_r7 = bowl.addOrReplaceChild("cube_r7", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -1.5708F, 0.0F));

		PartDefinition cube_r8 = bowl.addOrReplaceChild("cube_r8", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition tsukemono = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r9 = tsukemono.addOrReplaceChild("cube_r9", CubeListBuilder.create()
		    .texOffs(0, 10)
		    .addBox(-2.5F, -2.5F, -2.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 10)
		    .addBox(-2.0F, -2.0F, -1.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.0543F, 0.0F));

		PartDefinition cube_r10 = tsukemono.addOrReplaceChild("cube_r10", CubeListBuilder.create()
		    .texOffs(22, 12)
		    .addBox(0.5F, -2.5F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 2.8798F, 0.0F));

		PartDefinition cube_r11 = tsukemono.addOrReplaceChild("cube_r11", CubeListBuilder.create()
		    .texOffs(22, 12)
		    .addBox(0.8F, -2.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 2.9671F, 0.0F));

		PartDefinition cube_r12 = tsukemono.addOrReplaceChild("cube_r12", CubeListBuilder.create()
		    .texOffs(12, 12)
		    .addBox(0.5F, -1.5F, -3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 3.0543F, 0.0F));

		PartDefinition cube_r13 = tsukemono.addOrReplaceChild("cube_r13", CubeListBuilder.create()
		    .texOffs(12, 12)
		    .addBox(0.5F, -1.0F, -3.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, -2.9671F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	public static LayerDefinition createLeavesLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -0.8F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 2.3562F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -2.3562F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.7854F, 0.0F));

		PartDefinition cube_r5 = bowl.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 1.5708F, 0.0F));

		PartDefinition cube_r6 = bowl.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 3.1416F, 0.0F));

		PartDefinition cube_r7 = bowl.addOrReplaceChild("cube_r7", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -1.5708F, 0.0F));

		PartDefinition cube_r8 = bowl.addOrReplaceChild("cube_r8", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition leaves = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r14 = leaves.addOrReplaceChild("cube_r14", CubeListBuilder.create()
		    .texOffs(14, 11)
		    .addBox(-2.0F, -1.5F, -2.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 10)
		    .addBox(-1.0F, -2.5F, -2.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.8727F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	public static LayerDefinition createUmeLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -0.8F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 2.3562F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -2.3562F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.7854F, 0.0F));

		PartDefinition cube_r5 = bowl.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 1.5708F, 0.0F));

		PartDefinition cube_r6 = bowl.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 3.1416F, 0.0F));

		PartDefinition cube_r7 = bowl.addOrReplaceChild("cube_r7", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -1.5708F, 0.0F));

		PartDefinition cube_r8 = bowl.addOrReplaceChild("cube_r8", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-2.0F, -4.5F, 0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition ume = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
		    .texOffs(16, 12)
		    .addBox(-1.5F, -2.5F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r15 = ume.addOrReplaceChild("cube_r15", CubeListBuilder.create()
		    .texOffs(8, 12)
		    .addBox(0.5F, -2.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r16 = ume.addOrReplaceChild("cube_r16", CubeListBuilder.create()
		    .texOffs(0, 12)
		    .addBox(-2.0F, -2.5F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bowl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		meal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
