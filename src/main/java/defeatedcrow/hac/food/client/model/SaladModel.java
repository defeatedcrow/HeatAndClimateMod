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

public class SaladModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart glass1;
	private final ModelPart veggie;

	public SaladModel(ModelPart root) {
		this.glass1 = root.getChild("glass1");
		this.veggie = root.getChild("veggie");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glass1 = partdefinition.addOrReplaceChild("glass1", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false),
			PartPose.ZERO);

		PartDefinition glass8_r1 = glass1.addOrReplaceChild("glass8_r1", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(-3.5F, -5.0F, 6.0F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7854F, -0.7854F, 0.0F));

		PartDefinition glass7_r1 = glass1.addOrReplaceChild("glass7_r1", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(-3.5F, -5.0F, 6.0F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7854F, -2.3562F, 0.0F));

		PartDefinition glass6_r1 = glass1.addOrReplaceChild("glass6_r1", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(-3.5F, -5.0F, 6.0F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7854F, 2.3562F, 0.0F));

		PartDefinition glass5_r1 = glass1.addOrReplaceChild("glass5_r1", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(-3.5F, -5.0F, 6.0F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7854F, 0.7854F, 0.0F));

		PartDefinition glass4_r1 = glass1.addOrReplaceChild("glass4_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -5.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition glass3_r1 = glass1.addOrReplaceChild("glass3_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -5.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7854F, 3.1416F, 0.0F));

		PartDefinition glass2_r1 = glass1.addOrReplaceChild("glass2_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -5.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7854F, 1.5708F, 0.0F));

		PartDefinition glass1_r1 = glass1.addOrReplaceChild("glass1_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -5.0F, 6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition veggie = partdefinition.addOrReplaceChild("veggie", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition veg9_r1 = veggie.addOrReplaceChild("veg9_r1", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition veg8_r1 = veggie.addOrReplaceChild("veg8_r1", CubeListBuilder.create().texOffs(11, 26).mirror().addBox(0.0F, -5.0F, -3.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.9599F, -1.3963F, 0.0F));

		PartDefinition veg7_r1 = veggie.addOrReplaceChild("veg7_r1", CubeListBuilder.create().texOffs(11, 26).mirror().addBox(-1.0F, -6.0F, -3.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 1.1345F, 2.8798F, 0.0F));

		PartDefinition veg6_r1 = veggie.addOrReplaceChild("veg6_r1", CubeListBuilder.create().texOffs(11, 26).mirror().addBox(-2.0F, -5.0F, -2.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 1.309F, 1.0472F, 0.0F));

		PartDefinition veg5_r1 = veggie.addOrReplaceChild("veg5_r1", CubeListBuilder.create().texOffs(11, 26).mirror().addBox(-1.0F, -5.0F, -3.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 16).mirror().addBox(-3.0F, -8.0F, -4.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition veg4_r1 = veggie.addOrReplaceChild("veg4_r1", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-2.0F, -5.0F, -4.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.9599F, -0.8727F, 0.0F));

		PartDefinition veg3_r1 = veggie.addOrReplaceChild("veg3_r1", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-3.0F, -4.0F, -3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 1.0472F, -2.8798F, 0.0F));

		PartDefinition veg2_r1 = veggie.addOrReplaceChild("veg2_r1", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(-2.0F, -5.0F, -4.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.9599F, 2.0944F, 0.0F));

		PartDefinition veg1_r1 = veggie.addOrReplaceChild("veg1_r1", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(0.0F, -5.0F, -4.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.9599F, 1.0472F, 0.0F));

		PartDefinition leaf6_r1 = veggie.addOrReplaceChild("leaf6_r1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-3.0F, -8.0F, -4.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 1.0472F, 2.0944F, 0.0F));

		PartDefinition leaf5_r1 = veggie.addOrReplaceChild("leaf5_r1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-3.0F, -8.0F, -4.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 1.0472F, -2.0944F, 0.0F));

		PartDefinition lea4_r1 = veggie.addOrReplaceChild("lea4_r1", CubeListBuilder.create().texOffs(47, 16).mirror().addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.9599F, -1.309F, 0.0F));

		PartDefinition leaf3_r1 = veggie.addOrReplaceChild("leaf3_r1", CubeListBuilder.create().texOffs(30, 16).mirror().addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 1.0472F, 2.9671F, 0.0F));

		PartDefinition leaf2_r1 = veggie.addOrReplaceChild("leaf2_r1", CubeListBuilder.create().texOffs(13, 16).mirror().addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, -6.0F, 0.0F, 1.0472F, 1.0472F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		glass1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		veggie.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
