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

public class ChazukeModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart rice;
	private final ModelPart soup;
	private final ModelPart mochi;

	public ChazukeModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.rice = root.getChild("rice");
		this.soup = root.getChild("soup");
		this.mochi = root.getChild("mochi");
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

		PartDefinition rice = partdefinition.addOrReplaceChild("rice", CubeListBuilder.create()
			.texOffs(23, 10).addBox(-3.5F, -6.0F, -3.5F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(25, 22).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(45, 11).addBox(-2.0F, -8.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r4 = rice.addOrReplaceChild("cube_r4", CubeListBuilder.create()
			.texOffs(48, 26).addBox(-2.0F, -9.1F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r5 = rice.addOrReplaceChild("cube_r5", CubeListBuilder.create()
			.texOffs(41, 22).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(50, 22).addBox(-2.0F, -9.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.618F, 0.0F));

		PartDefinition soup = partdefinition.addOrReplaceChild("soup", CubeListBuilder.create()
			.texOffs(0, 24).addBox(-4.0F, -6.5F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition mochi = partdefinition.addOrReplaceChild("mochi", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r6 = mochi.addOrReplaceChild("cube_r6", CubeListBuilder.create()
			.texOffs(52, 17).addBox(-2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.1745F, 1.0472F, 0.0F));

		PartDefinition cube_r7 = mochi.addOrReplaceChild("cube_r7", CubeListBuilder.create()
			.texOffs(52, 17).addBox(-2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.5236F, -2.618F, 0.0F));

		PartDefinition cube_r8 = mochi.addOrReplaceChild("cube_r8", CubeListBuilder.create()
			.texOffs(52, 17).addBox(-3.0F, 0.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bowl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rice.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		mochi.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSoup(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		soup.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
