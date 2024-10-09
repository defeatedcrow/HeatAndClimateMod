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

public class DeepfryTonkatsuModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart vegi;
	private final ModelPart fry;

	public DeepfryTonkatsuModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.vegi = root.getChild("vegi");
		this.fry = root.getChild("fry");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r1 = bowl.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 10).addBox(-5.0F, 1.0F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 1.5708F, 0.0F));
		PartDefinition cube_r2 = bowl.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 10).addBox(-5.0F, 1.0F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 3.1416F, 0.0F));
		PartDefinition cube_r3 = bowl.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 10).addBox(-5.0F, 1.0F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, -1.5708F, 0.0F));
		PartDefinition cube_r4 = bowl.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 10).addBox(-5.0F, 1.0F, 3.5F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition vegi = partdefinition.addOrReplaceChild("vegi", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition cube_r5 = vegi.addOrReplaceChild("cube_r5", CubeListBuilder.create()
				.texOffs(23, 15).addBox(-1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 1.1345F, 0.0F));
		PartDefinition cube_r6 = vegi.addOrReplaceChild("cube_r6", CubeListBuilder.create()
				.texOffs(23, 15).addBox(-1.0F, 0.0F, 3.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.6109F, 0.0F));
		PartDefinition cube_r7 = vegi.addOrReplaceChild("cube_r7", CubeListBuilder.create()
				.texOffs(19, 14).addBox(-0.5F, -4.0F, -0.5F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, -0.2618F, 0.0F));
		PartDefinition cube_r8 = vegi.addOrReplaceChild("cube_r8", CubeListBuilder.create()
				.texOffs(11, 14).addBox(-2.5F, -4.5F, 1.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, -0.5236F, 0.0F));
		PartDefinition cube_r9 = vegi.addOrReplaceChild("cube_r9", CubeListBuilder.create()
				.texOffs(11, 14).addBox(-3.5F, -4.0F, 1.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.2618F, 0.0F));
		PartDefinition cube_r10 = vegi.addOrReplaceChild("cube_r10", CubeListBuilder.create()
				.texOffs(11, 14).addBox(-3.5F, -5.0F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1345F, 0.3491F, 0.0F));
		PartDefinition cube_r11 = vegi.addOrReplaceChild("cube_r11", CubeListBuilder.create()
				.texOffs(11, 14).addBox(-2.0F, -5.5F, 1.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.9599F, -0.7854F, 0.0F));
		PartDefinition cube_r12 = vegi.addOrReplaceChild("cube_r12", CubeListBuilder.create()
				.texOffs(1, 15).addBox(-2.5F, -2.0F, 1.5F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition fry = partdefinition.addOrReplaceChild("fry", CubeListBuilder.create()
				.texOffs(0, 19).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition cube_r13 = fry.addOrReplaceChild("cube_r13", CubeListBuilder.create()
				.texOffs(17, 19).addBox(0.5F, -2.3F, -2.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));
		PartDefinition cube_r14 = fry.addOrReplaceChild("cube_r14", CubeListBuilder.create()
				.texOffs(17, 19).addBox(-4.0F, -2.3F, -2.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bowl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		vegi.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fry.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
