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

public class SoupModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;

	public SoupModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
			.texOffs(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(-9, 23).addBox(-4.5F, -1.6F, -4.5F, 9.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(19, 27).addBox(-2.0F, -3.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.6981F, -2.0944F, 0.5236F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(19, 24).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.3491F, -1.5708F, 0.2618F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(19, 21).addBox(0.0F, -3.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.2618F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(19, 16).addBox(-2.0F, -1.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.3491F, -0.3491F, 0.7854F));

		PartDefinition cube_r5 = bowl.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(19, 18).addBox(0.0F, -3.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.2618F, 2.3562F, 0.1745F));

		PartDefinition cube_r6 = bowl.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(19, 14).addBox(0.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.3491F, -0.3491F, 0.1745F));

		PartDefinition side_r1 = bowl.addOrReplaceChild("side_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.6F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.5236F, -2.3562F, 0.0F));

		PartDefinition side_r2 = bowl.addOrReplaceChild("side_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.6F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.5236F, 2.3562F, 0.0F));

		PartDefinition side_r3 = bowl.addOrReplaceChild("side_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.6F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.5236F, -0.7854F, 0.0F));

		PartDefinition side_r4 = bowl.addOrReplaceChild("side_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.6F, 4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.5236F, 0.7854F, 0.0F));

		PartDefinition side_r5 = bowl.addOrReplaceChild("side_r5", CubeListBuilder.create().texOffs(0, 9).addBox(-4.0F, -5.0F, 2.8F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.5236F, 1.5708F, 0.0F));

		PartDefinition side_r6 = bowl.addOrReplaceChild("side_r6", CubeListBuilder.create().texOffs(0, 9).addBox(-4.0F, -5.0F, 2.8F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.5236F, 3.1416F, 0.0F));

		PartDefinition side_r7 = bowl.addOrReplaceChild("side_r7", CubeListBuilder.create().texOffs(0, 9).addBox(-4.0F, -5.0F, 2.8F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.5236F, -1.5708F, 0.0F));

		PartDefinition side_r8 = bowl.addOrReplaceChild("side_r8", CubeListBuilder.create().texOffs(0, 9).addBox(-4.0F, -5.0F, 2.8F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public static LayerDefinition createOuterLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition translucent = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create().texOffs(-9, 14).addBox(-4.5F, -2.0F, -4.5F, 9.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

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
