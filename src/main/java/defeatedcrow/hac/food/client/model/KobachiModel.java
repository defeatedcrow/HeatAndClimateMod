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

public class KobachiModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart meal;

	public KobachiModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.meal = root.getChild("meal");
	}

	public static LayerDefinition createMainLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
				.texOffs(6, 0).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition bowl_r1 = bowl.addOrReplaceChild("bowl_r1", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 2.3562F, 0.0F));
		PartDefinition bowl_r2 = bowl.addOrReplaceChild("bowl_r2", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -2.3562F, 0.0F));
		PartDefinition bowl_r3 = bowl.addOrReplaceChild("bowl_r3", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.7854F, 0.0F));
		PartDefinition bowl_r4 = bowl.addOrReplaceChild("bowl_r4", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.7854F, 0.0F));
		PartDefinition bowl_r5 = bowl.addOrReplaceChild("bowl_r5", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));
		PartDefinition bowl_r6 = bowl.addOrReplaceChild("bowl_r6", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 3.1416F, 0.0F));
		PartDefinition bowl_r7 = bowl.addOrReplaceChild("bowl_r7", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -1.5708F, 0.0F));
		PartDefinition bowl_r8 = bowl.addOrReplaceChild("bowl_r8", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(0, 22).addBox(-2.0F, -5.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition meal_r9 = meal.addOrReplaceChild("meal_r1", CubeListBuilder.create()
				.texOffs(26, 11).addBox(2.0F, -3.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, -1.9199F, 0.0F));
		PartDefinition meal_r10 = meal.addOrReplaceChild("meal_r2", CubeListBuilder.create()
				.texOffs(22, 11).addBox(-3.0F, -3.0F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.8727F, 0.0F));
		PartDefinition meal_r11 = meal.addOrReplaceChild("meal_r3", CubeListBuilder.create()
				.texOffs(26, 7).addBox(1.0F, -2.0F, -6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.309F, 0.9599F, 0.0F));
		PartDefinition meal_r12 = meal.addOrReplaceChild("meal_r4", CubeListBuilder.create()
				.texOffs(22, 7).addBox(1.0F, -5.0F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, -0.8727F, 0.0F));
		PartDefinition meal_r13 = meal.addOrReplaceChild("meal_r5", CubeListBuilder.create()
				.texOffs(12, 7).addBox(-2.5F, -5.0F, -4.8F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.4363F, 0.0F));
		PartDefinition meal_r14 = meal.addOrReplaceChild("meal_r6", CubeListBuilder.create()
				.texOffs(12, 17).addBox(-1.0F, -6.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, -0.5236F, 0.0F));
		PartDefinition meal_r15 = meal.addOrReplaceChild("meal_r7", CubeListBuilder.create()
				.texOffs(12, 12).addBox(0.0F, -6.0F, -2.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.1745F, 0.0F));
		PartDefinition meal_r16 = meal.addOrReplaceChild("meal_r8", CubeListBuilder.create()
				.texOffs(0, 17).addBox(-3.0F, -4.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.0F));
		PartDefinition meal_r17 = meal.addOrReplaceChild("meal_r9", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-1.0F, -3.5F, -0.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));
		PartDefinition meal_r18 = meal.addOrReplaceChild("meal_r10", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-2.0F, -5.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, -0.3491F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public static LayerDefinition createChickenLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
				.texOffs(6, 0).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition bowl_r1 = bowl.addOrReplaceChild("bowl_r1", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 2.3562F, 0.0F));
		PartDefinition bowl_r2 = bowl.addOrReplaceChild("bowl_r2", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -2.3562F, 0.0F));
		PartDefinition bowl_r3 = bowl.addOrReplaceChild("bowl_r3", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.7854F, 0.0F));
		PartDefinition bowl_r4 = bowl.addOrReplaceChild("bowl_r4", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.7854F, 0.0F));
		PartDefinition bowl_r5 = bowl.addOrReplaceChild("bowl_r5", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));
		PartDefinition bowl_r6 = bowl.addOrReplaceChild("bowl_r6", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 3.1416F, 0.0F));
		PartDefinition bowl_r7 = bowl.addOrReplaceChild("bowl_r7", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -1.5708F, 0.0F));
		PartDefinition bowl_r8 = bowl.addOrReplaceChild("bowl_r8", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(0, 22).addBox(-2.0F, -5.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition meal_r9 = meal.addOrReplaceChild("meal_r9", CubeListBuilder.create()
				.texOffs(26, 11).addBox(2.0F, -3.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, -1.9199F, 0.0F));
		PartDefinition meal_r10 = meal.addOrReplaceChild("meal_r10", CubeListBuilder.create()
				.texOffs(22, 11).addBox(-3.0F, -3.0F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.8727F, 0.0F));
		PartDefinition meal_r11 = meal.addOrReplaceChild("meal_r11", CubeListBuilder.create()
				.texOffs(26, 7).addBox(1.0F, -2.0F, -6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.309F, 0.9599F, 0.0F));
		PartDefinition meal_r12 = meal.addOrReplaceChild("meal_r12", CubeListBuilder.create()
				.texOffs(22, 7).addBox(1.0F, -5.0F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, -0.8727F, 0.0F));
		PartDefinition meal_r13 = meal.addOrReplaceChild("meal_r13", CubeListBuilder.create()
				.texOffs(12, 7).addBox(-2.5F, -5.0F, -4.8F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.4363F, 0.0F));
		PartDefinition meal_r14 = meal.addOrReplaceChild("meal_r14", CubeListBuilder.create()
				.texOffs(12, 17).addBox(-1.0F, -6.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, -0.5236F, 0.0F));
		PartDefinition meal_r15 = meal.addOrReplaceChild("meal_r15", CubeListBuilder.create()
				.texOffs(12, 12).addBox(0.0F, -6.0F, -2.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.1745F, 0.0F));
		PartDefinition meal_r16 = meal.addOrReplaceChild("meal_r16", CubeListBuilder.create()
				.texOffs(0, 17).addBox(-3.0F, -4.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.0F));
		PartDefinition meal_r17 = meal.addOrReplaceChild("meal_r17", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-1.0F, -3.5F, -0.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));
		PartDefinition meal_r18 = meal.addOrReplaceChild("meal_r18", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-2.0F, -5.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, -0.3491F, 0.0F));

		PartDefinition meal_r23 = meal.addOrReplaceChild("meal_r23", CubeListBuilder.create()
				.texOffs(26, 15).addBox(-2.5F, -6.0F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, -0.5236F, -0.0873F));
		PartDefinition meal_r24 = meal.addOrReplaceChild("meal_r24", CubeListBuilder.create()
				.texOffs(22, 15).addBox(2.0F, -8.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.3491F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public static LayerDefinition createTunaLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
				.texOffs(6, 0).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition bowl_r1 = bowl.addOrReplaceChild("bowl_r1", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 2.3562F, 0.0F));
		PartDefinition bowl_r2 = bowl.addOrReplaceChild("bowl_r2", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -2.3562F, 0.0F));
		PartDefinition bowl_r3 = bowl.addOrReplaceChild("bowl_r3", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.7854F, 0.0F));
		PartDefinition bowl_r4 = bowl.addOrReplaceChild("bowl_r4", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.7854F, 0.0F));
		PartDefinition bowl_r5 = bowl.addOrReplaceChild("bowl_r5", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));
		PartDefinition bowl_r6 = bowl.addOrReplaceChild("bowl_r6", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 3.1416F, 0.0F));
		PartDefinition bowl_r7 = bowl.addOrReplaceChild("bowl_r7", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -1.5708F, 0.0F));
		PartDefinition bowl_r8 = bowl.addOrReplaceChild("bowl_r8", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(0, 22).addBox(-2.0F, -5.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition meal_r9 = meal.addOrReplaceChild("meal_r9", CubeListBuilder.create()
				.texOffs(26, 11).addBox(2.0F, -3.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, -1.9199F, 0.0F));
		PartDefinition meal_r10 = meal.addOrReplaceChild("meal_r10", CubeListBuilder.create()
				.texOffs(22, 11).addBox(-3.0F, -3.0F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.8727F, 0.0F));
		PartDefinition meal_r11 = meal.addOrReplaceChild("meal_r11", CubeListBuilder.create()
				.texOffs(26, 7).addBox(1.0F, -2.0F, -6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.309F, 0.9599F, 0.0F));
		PartDefinition meal_r12 = meal.addOrReplaceChild("meal_r12", CubeListBuilder.create()
				.texOffs(22, 7).addBox(1.0F, -5.0F, -4.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, -0.8727F, 0.0F));
		PartDefinition meal_r13 = meal.addOrReplaceChild("meal_r13", CubeListBuilder.create()
				.texOffs(12, 7).addBox(-2.5F, -5.0F, -4.8F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.4363F, 0.0F));
		PartDefinition meal_r14 = meal.addOrReplaceChild("meal_r14", CubeListBuilder.create()
				.texOffs(12, 17).addBox(-1.0F, -6.0F, -0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, -0.5236F, 0.0F));
		PartDefinition meal_r15 = meal.addOrReplaceChild("meal_r15", CubeListBuilder.create()
				.texOffs(12, 12).addBox(0.0F, -6.0F, -2.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.1745F, 0.0F));
		PartDefinition meal_r16 = meal.addOrReplaceChild("meal_r16", CubeListBuilder.create()
				.texOffs(0, 17).addBox(-3.0F, -4.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.0F));
		PartDefinition meal_r17 = meal.addOrReplaceChild("meal_r17", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-1.0F, -3.5F, -0.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));
		PartDefinition meal_r18 = meal.addOrReplaceChild("meal_r18", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-2.0F, -5.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, -0.3491F, 0.0F));
		PartDefinition meal_r19 = meal.addOrReplaceChild("meal_r19", CubeListBuilder.create()
				.texOffs(22, 19).addBox(-2.0F, -7.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, -0.4363F, 0.0F));
		PartDefinition meal_r20 = meal.addOrReplaceChild("meal_r20", CubeListBuilder.create()
				.texOffs(22, 19).addBox(0.0F, -5.0F, -5.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, -0.6981F, 0.0F));
		PartDefinition meal_r21 = meal.addOrReplaceChild("meal_r21", CubeListBuilder.create()
				.texOffs(22, 19).addBox(0.0F, -1.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1345F, 0.8727F, 0.0F));
		PartDefinition meal_r22 = meal.addOrReplaceChild("meal_r22", CubeListBuilder.create()
				.texOffs(22, 19).addBox(1.0F, -3.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.2217F, -1.9199F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public static LayerDefinition createPasteLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
				.texOffs(6, 0).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition bowl_r1 = bowl.addOrReplaceChild("bowl_r1", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 2.3562F, 0.0F));
		PartDefinition bowl_r2 = bowl.addOrReplaceChild("bowl_r2", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -2.3562F, 0.0F));
		PartDefinition bowl_r3 = bowl.addOrReplaceChild("bowl_r3", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.7854F, 0.0F));
		PartDefinition bowl_r4 = bowl.addOrReplaceChild("bowl_r4", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -7.0F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.7854F, 0.0F));
		PartDefinition bowl_r5 = bowl.addOrReplaceChild("bowl_r5", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));
		PartDefinition bowl_r6 = bowl.addOrReplaceChild("bowl_r6", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 3.1416F, 0.0F));
		PartDefinition bowl_r7 = bowl.addOrReplaceChild("bowl_r7", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -1.5708F, 0.0F));
		PartDefinition bowl_r8 = bowl.addOrReplaceChild("bowl_r8", CubeListBuilder.create()
				.texOffs(22, 0).addBox(-2.0F, -6.5F, 1.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(0, 22).addBox(-2.0F, -5.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(12, 25).addBox(-2.5F, -4.5F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
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
