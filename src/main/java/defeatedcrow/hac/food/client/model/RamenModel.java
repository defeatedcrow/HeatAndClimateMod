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

public class RamenModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart meal;
	private final ModelPart soup;

	public RamenModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.meal = root.getChild("meal");
		this.soup = root.getChild("soup");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(25, 0).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-5.0F, -6.0F, -5.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 8).addBox(-5.0F, -6.0F, -5.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(17, 10).addBox(-4.0F, 3.2F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(19, 19).addBox(-3.0F, 2.9F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(52, 10).addBox(-1.5F, 2.8F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition na2_r1 = meal.addOrReplaceChild("na2", CubeListBuilder.create()
				.texOffs(38, 20).addBox(-2.0F, 4.2F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.6109F, 0.0F));

		PartDefinition na_r1 = meal.addOrReplaceChild("na", CubeListBuilder.create()
				.texOffs(42, 15).addBox(1.75F, 2.5F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, -0.5F, 0.0F, 0.9599F, -0.0873F));

		PartDefinition egg_r1 = meal.addOrReplaceChild("egg", CubeListBuilder.create()
				.texOffs(42, 11).addBox(0.75F, 2.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, -0.6109F, -0.1745F));

		PartDefinition meat2_r1 = meal.addOrReplaceChild("meat2", CubeListBuilder.create()
				.texOffs(53, 16).addBox(1.0F, 2.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, -2.5307F, -0.0873F));

		PartDefinition meat1_r1 = meal.addOrReplaceChild("meat1", CubeListBuilder.create()
				.texOffs(53, 16).addBox(1.0F, 2.5F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7925F, -0.0873F));

		PartDefinition soup = partdefinition.addOrReplaceChild("soup", CubeListBuilder.create()
				.texOffs(-7, 24).addBox(-4.0F, 3.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bowl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		meal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSoup(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		soup.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
