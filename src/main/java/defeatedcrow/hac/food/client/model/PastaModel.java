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

public class PastaModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart dish;
	private final ModelPart meal;

	public PastaModel(ModelPart root) {
		this.dish = root.getChild("dish");
		this.meal = root.getChild("meal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dish = partdefinition.addOrReplaceChild("dish", CubeListBuilder.create()
				.texOffs(0, 0).mirror().addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition dish5_r1 = dish.addOrReplaceChild("dish5_r1", CubeListBuilder.create()
				.texOffs(31, 1).mirror().addBox(-7.0F, 0.0F, 5.0F, 14.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 3.1416F, 0.0F));

		PartDefinition dish4_r1 = dish.addOrReplaceChild("dish4_r1", CubeListBuilder.create()
				.texOffs(31, 1).mirror().addBox(-7.0F, 0.0F, 5.0F, 14.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 1.5708F, 0.0F));

		PartDefinition dish3_r1 = dish.addOrReplaceChild("dish3_r1", CubeListBuilder.create()
				.texOffs(31, 1).mirror().addBox(-7.0F, 0.0F, 5.0F, 14.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -1.5708F, 0.0F));

		PartDefinition dish2_r1 = dish.addOrReplaceChild("dish2_r1", CubeListBuilder.create()
				.texOffs(31, 1).mirror().addBox(-7.0F, 0.0F, 5.0F, 14.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(0, 12).mirror().addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition leaf3_r1 = meal.addOrReplaceChild("leaf3_r1", CubeListBuilder.create()
				.texOffs(39, 15).addBox(-5.0F, 0.0F, 4.0F, 10.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition leaf2_r1 = meal.addOrReplaceChild("leaf2_r1", CubeListBuilder.create()
				.texOffs(42, 12).mirror().addBox(0.0F, -3.7F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.2618F, -2.0944F, 0.0F));

		PartDefinition leaf1_r1 = meal.addOrReplaceChild("leaf1_r1", CubeListBuilder.create()
				.texOffs(42, 12).mirror().addBox(0.0F, -3.5F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, -0.3491F, 0.5236F,
						0.0F));

		PartDefinition meat3_r1 = meal.addOrReplaceChild("meat3_r1", CubeListBuilder.create()
				.texOffs(33, 16).mirror().addBox(0.0F, -3.3F, 0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, -1.2217F, 0.0F));

		PartDefinition meat4_r1 = meal.addOrReplaceChild("meat4_r1", CubeListBuilder.create()
				.texOffs(33, 16).mirror().addBox(0.0F, -3.5F, 0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 1.5708F, 0.0F));

		PartDefinition meat2_r1 = meal.addOrReplaceChild("meat2_r1", CubeListBuilder.create()
				.texOffs(33, 12).mirror().addBox(-1.0F, -3.3F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, -2.0944F,
						0.0F));

		PartDefinition meat1_r1 = meal.addOrReplaceChild("meat1_r1", CubeListBuilder.create()
				.texOffs(33, 12).mirror().addBox(-1.0F, -3.5F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.5236F, 0.0F));

		PartDefinition pasta4_r1 = meal.addOrReplaceChild("pasta4_r1", CubeListBuilder.create()
				.texOffs(39, 22).mirror().addBox(-2.0F, -3.0F, 0.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.5F, -0.1745F, 2.3562F,
						0.0F));

		PartDefinition pasta3_r1 = meal.addOrReplaceChild("pasta3_r1", CubeListBuilder.create()
				.texOffs(19, 25).mirror().addBox(-4.0F, -3.0F, 0.0F, 7.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, -0.2618F, -1.3963F,
						0.0F));

		PartDefinition pasta2_r1 = meal.addOrReplaceChild("pasta2_r1", CubeListBuilder.create()
				.texOffs(0, 24).mirror().addBox(-2.0F, -2.5F, 0.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.5F, -0.0873F, 0.8727F,
						0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
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
