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

public class LargeBowlModel_Rice<T extends Entity> extends EntityModel<T> {

	private final ModelPart soup;
	private final ModelPart gu;
	private final ModelPart gu2;

	public LargeBowlModel_Rice(ModelPart root) {
		this.soup = root.getChild("soup");
		this.gu = root.getChild("gu");
		this.gu2 = root.getChild("gu2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition soup = partdefinition.addOrReplaceChild("soup", CubeListBuilder.create().texOffs(0, 0)
			.addBox(-6.0F, -3.0F, -6.0F, 12.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition gu = partdefinition.addOrReplaceChild("gu", CubeListBuilder.create()
			.texOffs(0, 20).addBox(0.0F, -3.5F, -5.0F, 6.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition gu2 = partdefinition.addOrReplaceChild("gu2", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition cube_r1 = gu.addOrReplaceChild("cube_r1", CubeListBuilder.create()
			.texOffs(30, 21).addBox(-6.0F, -3.5F, -5.0F, 7.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r2 = gu2.addOrReplaceChild("cube_r2", CubeListBuilder.create()
			.texOffs(52, 12).addBox(-1.0F, -4.0F, -0.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, -2.3562F, 0.1745F));

		PartDefinition cube_r3 = gu2.addOrReplaceChild("cube_r3", CubeListBuilder.create()
			.texOffs(52, 8).addBox(-6.0F, -3.5F, -0.25F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, -1.0472F, 0.0F));

		PartDefinition cube_r4 = gu2.addOrReplaceChild("cube_r4", CubeListBuilder.create()
			.texOffs(52, 4).addBox(-3.5F, -3.5F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 1.309F, 0.0F));

		PartDefinition cube_r5 = gu2.addOrReplaceChild("cube_r5", CubeListBuilder.create()
			.texOffs(52, 0).addBox(-1.0F, -4.25F, 3.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.7854F, 0.0F));

		PartDefinition cube_r6 = gu2.addOrReplaceChild("cube_r6", CubeListBuilder.create()
			.texOffs(38, 4).addBox(-2.5F, -3.75F, 2.25F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, -1.3963F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		soup.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		gu.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		gu2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
