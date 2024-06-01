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

public class SobaModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart meal;
	private final ModelPart tenpura;
	private final ModelPart soup;

	public SobaModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.meal = root.getChild("meal");
		this.tenpura = root.getChild("tenpura");
		this.soup = root.getChild("soup");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(25, 0).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 8).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(17, 10).addBox(-4.0F, 1.2F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(19, 19).addBox(-3.0F, 0.9F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(55, 11).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition na_r1 = meal.addOrReplaceChild("na_r1", CubeListBuilder.create()
				.texOffs(42, 15).addBox(1.75F, 0.5F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, -0.5F, 0.0F, 0.7854F, -0.0873F));

		PartDefinition egg1_r1 = meal.addOrReplaceChild("egg1_r1", CubeListBuilder.create()
				.texOffs(42, 10).addBox(0.75F, 0.5F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition meat2_r1 = meal.addOrReplaceChild("meat2_r1", CubeListBuilder.create()
				.texOffs(53, 16).addBox(1.0F, 0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, -2.3562F, -0.1309F));

		PartDefinition meat1_r1 = meal.addOrReplaceChild("meat1_r1", CubeListBuilder.create()
				.texOffs(53, 16).addBox(1.0F, 0.5F, 0.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.618F, -0.1309F));

		PartDefinition tenpura = partdefinition.addOrReplaceChild("tenpura", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r4 = tenpura.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(54, 27).addBox(-6.0F, 1.0F, -2.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(50, 28).addBox(-4.5F, 0.7F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4363F, 0.5236F));

		PartDefinition cube_r5 = tenpura.addOrReplaceChild("cube_r5", CubeListBuilder.create()
				.texOffs(36, 27).addBox(-3.0F, 0.5F, -2.0F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(24, 26).addBox(-3.0F, 0.0F, -1.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4363F, 0.2618F));

		PartDefinition soup = partdefinition.addOrReplaceChild("soup", CubeListBuilder.create()
				.texOffs(-7, 24).addBox(-4.0F, 1.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

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

	public void renderTenpura(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tenpura.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSoup(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		soup.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
