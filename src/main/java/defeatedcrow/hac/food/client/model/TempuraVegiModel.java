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

public class TempuraVegiModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart meal;

	public TempuraVegiModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.meal = root.getChild("meal");
	}

	public static LayerDefinition createBodyLayer() {

		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-5.0F, -1.0F, -3.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, 0.0F, 4.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.75F, 0.0F, 0.4363F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 10).addBox(-6.0F, 0.0F, 4.0F, 12.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, 1.0F, 0.4363F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, 0.0F, 4.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.75F, 0.0F, 0.4363F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 10).addBox(-6.0F, 0.0F, 4.0F, 12.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, -1.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r5 = meal.addOrReplaceChild("cube_r5", CubeListBuilder.create()
				.texOffs(1, 27).addBox(-3.0F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.1745F, 0.0F));

		PartDefinition cube_r6 = meal.addOrReplaceChild("cube_r6", CubeListBuilder.create()
				.texOffs(13, 27).addBox(0.0F, -2.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.4363F, 0.0F));

		PartDefinition cube_r7 = meal.addOrReplaceChild("cube_r7", CubeListBuilder.create()
				.texOffs(9, 14).addBox(-0.5F, -0.5F, 1.5F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.2618F, 0.0F));

		PartDefinition cube_r8 = meal.addOrReplaceChild("cube_r8", CubeListBuilder.create()
				.texOffs(19, 16).addBox(1.0F, -1.0F, 1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.6109F, 0.0F));

		PartDefinition cube_r9 = meal.addOrReplaceChild("cube_r9", CubeListBuilder.create()
				.texOffs(0, 20).addBox(-1.0F, -1.25F, 0.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F, 0.0F));

		PartDefinition cube_r10 = meal.addOrReplaceChild("cube_r10", CubeListBuilder.create()
				.texOffs(0, 14).addBox(-3.0F, -1.25F, 1.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.2618F, 0.0F));

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
