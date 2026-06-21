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

public class Rice_JollofModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart rice;
	private final ModelPart meat;

	public Rice_JollofModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.rice = root.getChild("rice");
		this.meat = root.getChild("meat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-6.0F, -1.0F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(36, 8)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(36, 8)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(36, 8)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(36, 8)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition rice = partdefinition.addOrReplaceChild("rice", CubeListBuilder.create()
		    .texOffs(0, 19)
		    .addBox(-5.5F, -2.5F, -5.5F, 11.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition meat = partdefinition.addOrReplaceChild("meat", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition chili2_r1 = meat.addOrReplaceChild("chili2_r1", CubeListBuilder.create()
		    .texOffs(47, 27)
		    .addBox(-0.5F, -3.5F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 3.0543F, 0.0F));

		PartDefinition chili1_r1 = meat.addOrReplaceChild("chili1_r1", CubeListBuilder.create()
		    .texOffs(47, 27)
		    .addBox(1.5F, -4.0F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -0.9599F, 0.0F));

		PartDefinition nasu3_r1 = meat.addOrReplaceChild("nasu3_r1", CubeListBuilder.create()
		    .texOffs(46, 21)
		    .addBox(-0.5F, -3.0F, -5.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6981F, 0.0F));

		PartDefinition nasu2_r1 = meat.addOrReplaceChild("nasu2_r1", CubeListBuilder.create()
		    .texOffs(46, 21)
		    .addBox(-2.0F, -4.0F, 2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.6545F, 0.0F));

		PartDefinition nasu1_r1 = meat.addOrReplaceChild("nasu1_r1", CubeListBuilder.create()
		    .texOffs(46, 21)
		    .addBox(-3.0F, -5.0F, 1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, -0.2618F, 0.0F));

		PartDefinition meat3_r1 = meat.addOrReplaceChild("meat3_r1", CubeListBuilder.create()
		    .texOffs(45, 15)
		    .addBox(-3.0F, -5.0F, -4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.5236F, 0.0F));

		PartDefinition meat2_r1 = meat.addOrReplaceChild("meat2_r1", CubeListBuilder.create()
		    .texOffs(45, 15)
		    .addBox(-3.0F, -4.0F, -3.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -0.6981F, 0.0F));

		PartDefinition meat1_r1 = meat.addOrReplaceChild("meat1_r1", CubeListBuilder.create()
		    .texOffs(45, 15)
		    .addBox(-3.0F, -3.5F, -4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bowl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rice.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		meat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
