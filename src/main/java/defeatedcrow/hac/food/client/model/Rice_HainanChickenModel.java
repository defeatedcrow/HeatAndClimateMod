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

public class Rice_HainanChickenModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart rice;
	private final ModelPart meat;

	public Rice_HainanChickenModel(ModelPart root) {
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
		    .texOffs(0, 15)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(0, 15)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(0, 15)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(0, 15)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition rice = partdefinition.addOrReplaceChild("rice", CubeListBuilder.create()
		    .texOffs(0, 19)
		    .addBox(-5.0F, -3.0F, -1.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition meat = partdefinition.addOrReplaceChild("meat", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r5 = meat.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(30, 23)
		    .addBox(-2.0F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -1.0F, 0.0F, -0.4363F, -0.3491F, 0.8727F));

		PartDefinition cube_r6 = meat.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(30, 23)
		    .addBox(-1.0F, -4.0F, 0.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -1.0F, 0.0F, -0.7854F, -0.4363F, 0.9599F));

		PartDefinition tomato_r1 = meat.addOrReplaceChild("tomato_r1", CubeListBuilder.create()
		    .texOffs(37, 25)
		    .addBox(4.0F, -4.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition meat4_r1 = meat.addOrReplaceChild("meat4_r1", CubeListBuilder.create()
		    .texOffs(47, 23)
		    .addBox(1.0F, -2.0F, -17.5F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 10.0F, 0.3491F, -0.3491F, 0.3491F));

		PartDefinition meat3_r1 = meat.addOrReplaceChild("meat3_r1", CubeListBuilder.create()
		    .texOffs(30, 15)
		    .addBox(0.0F, -1.5F, -17.3F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 10.0F, 0.3927F, -0.2618F, 0.3491F));

		PartDefinition meat2_r1 = meat.addOrReplaceChild("meat2_r1", CubeListBuilder.create()
		    .texOffs(30, 15)
		    .addBox(0.0F, -1.0F, -17.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 10.0F, 0.4363F, -0.0873F, 0.3491F));

		PartDefinition meat1_r1 = meat.addOrReplaceChild("meat1_r1", CubeListBuilder.create()
		    .texOffs(47, 15)
		    .addBox(0.0F, -1.5F, -17.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 10.0F, 0.5236F, 0.0873F, 0.3491F));

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
