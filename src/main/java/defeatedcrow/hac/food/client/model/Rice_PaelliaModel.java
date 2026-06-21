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

public class Rice_PaelliaModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart rice;
	private final ModelPart meat;

	public Rice_PaelliaModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.rice = root.getChild("rice");
		this.meat = root.getChild("meat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-6.0F, -1.0F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		    .texOffs(-6, 0)
		    .addBox(-9.0F, -2.0F, -3.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(-6, 0)
		    .addBox(-9.0F, -2.0F, -3.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(36, 8)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(36, 8)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 3.1416F, 0.0F));

		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(36, 8)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -1.5708F, 0.0F));

		PartDefinition cube_r5 = bowl.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(36, 8)
		    .addBox(-6.5F, -5.0F, 4.5F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition rice = partdefinition.addOrReplaceChild("rice", CubeListBuilder.create()
		    .texOffs(0, 20)
		    .addBox(-5.5F, -2.0F, -5.5F, 11.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition meat = partdefinition.addOrReplaceChild("meat", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition squid3_r1 = meat.addOrReplaceChild("squid3_r1", CubeListBuilder.create()
		    .texOffs(44, 14)
		    .addBox(-3.0F, -2.0F, 2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3054F, -0.1745F, 0.0F));

		PartDefinition squid2_r1 = meat.addOrReplaceChild("squid2_r1", CubeListBuilder.create()
		    .texOffs(44, 14)
		    .addBox(-1.0F, -2.0F, -4.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, -0.5236F, 0.0F));

		PartDefinition squid1_r1 = meat.addOrReplaceChild("squid1_r1", CubeListBuilder.create()
		    .texOffs(44, 14)
		    .addBox(-5.0F, -3.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, -0.7854F, 0.0F));

		PartDefinition prawn8_r1 = meat.addOrReplaceChild("prawn8_r1", CubeListBuilder.create()
		    .texOffs(36, 20)
		    .addBox(-1.0F, -4.3F, 2.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 2.3562F, 0.0F));

		PartDefinition prawn7_r1 = meat.addOrReplaceChild("prawn7_r1", CubeListBuilder.create()
		    .texOffs(36, 20)
		    .addBox(-1.0F, -4.3F, 2.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.7854F, 0.0F));

		PartDefinition prawn6_r1 = meat.addOrReplaceChild("prawn6_r1", CubeListBuilder.create()
		    .texOffs(36, 20)
		    .addBox(-1.0F, -4.3F, 2.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -2.3562F, 0.0F));

		PartDefinition prawn5_r1 = meat.addOrReplaceChild("prawn5_r1", CubeListBuilder.create()
		    .texOffs(36, 20)
		    .addBox(-1.0F, -4.3F, 2.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.7854F, 0.0F));

		PartDefinition prawn4_r1 = meat.addOrReplaceChild("prawn4_r1", CubeListBuilder.create()
		    .texOffs(34, 20)
		    .addBox(-0.5F, -1.5F, 2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 2.3562F, 0.0F));

		PartDefinition prawn3_r1 = meat.addOrReplaceChild("prawn3_r1", CubeListBuilder.create()
		    .texOffs(34, 20)
		    .addBox(-0.5F, -1.5F, 2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.7854F, 0.0F));

		PartDefinition prawn2_r1 = meat.addOrReplaceChild("prawn2_r1", CubeListBuilder.create()
		    .texOffs(34, 20)
		    .addBox(-0.5F, -1.5F, 2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -2.3562F, 0.0F));

		PartDefinition prawn1_r1 = meat.addOrReplaceChild("prawn1_r1", CubeListBuilder.create()
		    .texOffs(34, 20)
		    .addBox(-0.5F, -1.5F, 2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -0.7854F, 0.0F));

		PartDefinition mussel4_r1 = meat.addOrReplaceChild("mussel4_r1", CubeListBuilder.create()
		    .texOffs(34, 14)
		    .addBox(-1.0F, -1.5F, 2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 1.5708F, 0.0F));

		PartDefinition mussel3_r1 = meat.addOrReplaceChild("mussel3_r1", CubeListBuilder.create()
		    .texOffs(34, 14)
		    .addBox(-1.0F, -1.5F, 2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 3.1416F, 0.0F));

		PartDefinition mussel2_r1 = meat.addOrReplaceChild("mussel2_r1", CubeListBuilder.create()
		    .texOffs(34, 14)
		    .addBox(-1.0F, -1.5F, 2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, -1.5708F, 0.0F));

		PartDefinition mussel1_r1 = meat.addOrReplaceChild("mussel1_r1", CubeListBuilder.create()
		    .texOffs(34, 14)
		    .addBox(-1.0F, -1.5F, 2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

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
