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

public class SauteModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart dish;
	private final ModelPart meal;

	public SauteModel(ModelPart root) {
		this.dish = root.getChild("dish");
		this.meal = root.getChild("meal");
	}

	public static LayerDefinition createTofuLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dish = partdefinition.addOrReplaceChild("dish", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r1 = dish.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 1.5708F, 0.0F));
		PartDefinition cube_r2 = dish.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 3.1416F, 0.0F));
		PartDefinition cube_r3 = dish.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -1.5708F, 0.0F));
		PartDefinition cube_r4 = dish.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(-8, 12).addBox(-4.0F, -1.3F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(17, 14).addBox(-1.0F, -1.8F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 16).addBox(2.0F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 14).addBox(-2.5F, -1.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 14).addBox(-2.0F, -1.7F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r5 = meal.addOrReplaceChild("cube_r5", CubeListBuilder.create()
				.texOffs(17, 16).addBox(-3.0F, -1.7F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1345F, 0.0F));
		PartDefinition cube_r6 = meal.addOrReplaceChild("cube_r6", CubeListBuilder.create()
				.texOffs(17, 14).addBox(-3.0F, -1.7F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.0944F, 0.0F));
		PartDefinition cube_r7 = meal.addOrReplaceChild("cube_r7", CubeListBuilder.create()
				.texOffs(17, 16).addBox(-3.5F, -1.8F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition cube_r8 = meal.addOrReplaceChild("cube_r8", CubeListBuilder.create()
				.texOffs(17, 18).addBox(-2.5F, -1.8F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r9 = meal.addOrReplaceChild("cube_r9", CubeListBuilder.create()
				.texOffs(17, 18).addBox(-3.0F, -1.8F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r10 = meal.addOrReplaceChild("cube_r10", CubeListBuilder.create()
				.texOffs(17, 18).addBox(-3.0F, -1.8F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, 0.0F));
		PartDefinition cube_r11 = meal.addOrReplaceChild("cube_r11", CubeListBuilder.create()
				.texOffs(17, 16).addBox(1.0F, -1.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition cube_r12 = meal.addOrReplaceChild("cube_r12", CubeListBuilder.create()
				.texOffs(17, 16).addBox(2.0F, -1.8F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.7453F, 0.0F));
		PartDefinition cube_r13 = meal.addOrReplaceChild("cube_r13", CubeListBuilder.create()
				.texOffs(17, 18).addBox(1.0F, -1.8F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));
		PartDefinition cube_r14 = meal.addOrReplaceChild("cube_r14", CubeListBuilder.create()
				.texOffs(17, 12).addBox(-1.0F, -1.7F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));
		PartDefinition cube_r15 = meal.addOrReplaceChild("cube_r15", CubeListBuilder.create()
				.texOffs(17, 12).addBox(-0.5F, -1.8F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public static LayerDefinition createEggLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dish = partdefinition.addOrReplaceChild("dish", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r1 = dish.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 1.5708F, 0.0F));
		PartDefinition cube_r2 = dish.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 3.1416F, 0.0F));
		PartDefinition cube_r3 = dish.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -1.5708F, 0.0F));
		PartDefinition cube_r4 = dish.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(-8, 12).addBox(-4.0F, -1.3F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public static LayerDefinition createChickenLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dish = partdefinition.addOrReplaceChild("dish", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r1 = dish.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 1.5708F, 0.0F));
		PartDefinition cube_r2 = dish.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 3.1416F, 0.0F));
		PartDefinition cube_r3 = dish.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -1.5708F, 0.0F));
		PartDefinition cube_r4 = dish.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(-8, 12).addBox(-4.0F, -1.3F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r16 = meal.addOrReplaceChild("cube_r16", CubeListBuilder.create()
				.texOffs(14, 20).addBox(-2.0F, -2.2F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(10, 22).addBox(-1.5F, -1.8F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(10, 24).addBox(-1.5F, -1.8F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 22).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(-2.0F, -2.0F, 1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-2.5F, -2.1F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-2.5F, -2.1F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition cube_r17 = meal.addOrReplaceChild("cube_r17", CubeListBuilder.create()
				.texOffs(0, 29).addBox(-1.2F, -1.5F, -4.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.7854F, 0.0F));
		PartDefinition cube_r18 = meal.addOrReplaceChild("cube_r18", CubeListBuilder.create()
				.texOffs(7, 26).addBox(-1.5F, 0.0F, 5.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.7854F, 0.0F));
		PartDefinition cube_r19 = meal.addOrReplaceChild("cube_r19", CubeListBuilder.create()
				.texOffs(6, 26).addBox(-0.5F, -1.0F, 4.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.7854F, 0.0F));
		PartDefinition cube_r20 = meal.addOrReplaceChild("cube_r20", CubeListBuilder.create()
				.texOffs(0, 26).addBox(-1.0F, -1.3F, 3.2F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public static LayerDefinition createNasuLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dish = partdefinition.addOrReplaceChild("dish", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r1 = dish.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 1.5708F, 0.0F));
		PartDefinition cube_r2 = dish.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 3.1416F, 0.0F));
		PartDefinition cube_r3 = dish.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -1.5708F, 0.0F));
		PartDefinition cube_r4 = dish.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 9).addBox(-5.0F, 0.5F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(-8, 12).addBox(-4.0F, -1.3F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(8, 26).addBox(0.0F, -1.5F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r17 = meal.addOrReplaceChild("cube_r17", CubeListBuilder.create()
				.texOffs(8, 29).addBox(0.0F, -2.0F, 2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7925F, -0.1745F));
		PartDefinition cube_r18 = meal.addOrReplaceChild("cube_r18", CubeListBuilder.create()
				.texOffs(8, 26).addBox(0.5F, -1.5F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r19 = meal.addOrReplaceChild("cube_r19", CubeListBuilder.create()
				.texOffs(8, 29).addBox(-2.0F, -2.5F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));
		PartDefinition cube_r20 = meal.addOrReplaceChild("cube_r20", CubeListBuilder.create()
				.texOffs(8, 29).addBox(-1.0F, -2.5F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition cube_r21 = meal.addOrReplaceChild("cube_r21", CubeListBuilder.create()
				.texOffs(0, 26).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r22 = meal.addOrReplaceChild("cube_r22", CubeListBuilder.create()
				.texOffs(0, 29).addBox(-3.5F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.9599F, -0.0873F));
		PartDefinition cube_r23 = meal.addOrReplaceChild("cube_r23", CubeListBuilder.create()
				.texOffs(0, 29).addBox(1.0F, -2.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4363F, 0.1745F));
		PartDefinition cube_r24 = meal.addOrReplaceChild("cube_r24", CubeListBuilder.create()
				.texOffs(0, 26).addBox(1.0F, -2.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.2618F, 0.0F));
		PartDefinition cube_r25 = meal.addOrReplaceChild("cube_r25", CubeListBuilder.create()
				.texOffs(0, 29).addBox(0.0F, -1.6F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));
		PartDefinition cube_r26 = meal.addOrReplaceChild("cube_r26", CubeListBuilder.create()
				.texOffs(0, 26).addBox(2.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6109F, 0.0873F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		dish.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		meal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
