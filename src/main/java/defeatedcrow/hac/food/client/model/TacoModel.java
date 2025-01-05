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

public class TacoModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart tortilla;
	private final ModelPart meat;

	public TacoModel(ModelPart root) {
		this.tortilla = root.getChild("tortilla");
		this.meat = root.getChild("meat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tortilla = partdefinition.addOrReplaceChild("tortilla", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r1 = tortilla.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 13).addBox(-5.5F, 0.0F, 1.0F, 11.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = tortilla.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 10).addBox(-5.5F, -3.5F, -1.5F, 11.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = tortilla.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-5.0F, -4.0F, -4.0F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r4 = tortilla.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 5).addBox(-5.0F, -1.0F, -3.0F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition meat = partdefinition.addOrReplaceChild("meat", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r5 = meat.addOrReplaceChild("cube_r5", CubeListBuilder.create()
				.texOffs(0, 22).addBox(-4.5F, -3.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.5236F, 0.0F));

		PartDefinition cube_r6 = meat.addOrReplaceChild("cube_r6", CubeListBuilder.create()
				.texOffs(0, 24).addBox(-2.5F, -3.0F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 22).addBox(-2.0F, -3.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.2618F, 0.0F));

		PartDefinition cube_r7 = meat.addOrReplaceChild("cube_r7", CubeListBuilder.create()
				.texOffs(4, 24).addBox(-2.0F, -2.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, -0.3491F, 0.0F));

		PartDefinition cube_r8 = meat.addOrReplaceChild("cube_r8", CubeListBuilder.create()
				.texOffs(8, 24).addBox(1.5F, -2.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.1745F, 0.0F));

		PartDefinition cube_r9 = meat.addOrReplaceChild("cube_r9", CubeListBuilder.create()
				.texOffs(12, 24).addBox(3.5F, -2.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -0.3491F, 0.0F));

		PartDefinition cube_r10 = meat.addOrReplaceChild("cube_r10", CubeListBuilder.create()
				.texOffs(12, 22).addBox(3.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6981F, -0.5236F, 0.0F));

		PartDefinition cube_r11 = meat.addOrReplaceChild("cube_r11", CubeListBuilder.create()
				.texOffs(8, 22).addBox(0.0F, -4.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -0.4363F, 0.0F));

		PartDefinition cube_r12 = meat.addOrReplaceChild("cube_r12", CubeListBuilder.create()
				.texOffs(0, 17).addBox(-4.0F, -2.5F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.6109F, 0.0F));

		PartDefinition cube_r13 = meat.addOrReplaceChild("cube_r13", CubeListBuilder.create()
				.texOffs(16, 17).addBox(1.0F, -3.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, -0.1745F));

		PartDefinition cube_r14 = meat.addOrReplaceChild("cube_r14", CubeListBuilder.create()
				.texOffs(8, 17).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r15 = meat.addOrReplaceChild("cube_r15", CubeListBuilder.create()
				.texOffs(0, 27).addBox(-4.0F, -2.5F, -2.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tortilla.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		meat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
