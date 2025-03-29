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

public class ZarusobaModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart zaru;
	private final ModelPart meal;

	public ZarusobaModel(ModelPart root) {
		this.zaru = root.getChild("zaru");
		this.meal = root.getChild("meal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition zaru = partdefinition.addOrReplaceChild("zaru", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-5.0F, -2.0F, -3.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 1).addBox(-5.0F, -2.0F, -2.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(-8, 3).addBox(-4.0F, -1.5F, -2.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(28, 0).addBox(2.5F, -3.0F, -7.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(41, 1).addBox(2.0F, -3.5F, -8.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(52, 0).addBox(2.0F, -3.5F, -7.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(37, 6).addBox(-6.0F, -1.0F, -8.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = zaru.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(52, 0).addBox(-6.0F, -3.5F, 5.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(41, 1).addBox(-6.0F, -3.5F, 4.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 1).addBox(-5.0F, -2.0F, -6.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.0F, -2.0F, -7.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-3.0F, -2.5F, -1.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r2 = meal.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(34, 20).addBox(0.0F, -4.0F, -3.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, -2.0944F, 0.0F));

		PartDefinition cube_r3 = meal.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(17, 20).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.1745F, 0.0F));

		PartDefinition cube_r4 = meal.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 20).addBox(-2.0F, -3.5F, 1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.3491F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		zaru.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		meal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
