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

public class StickVegiModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart stick;

	public StickVegiModel(ModelPart root) {
		this.stick = root.getChild("stick");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition stick = partdefinition.addOrReplaceChild("stick", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-0.5F, -15.0F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition vegi5_r1 = stick.addOrReplaceChild("vegi5_r1", CubeListBuilder.create()
				.texOffs(16, 0).addBox(-1.5F, -4.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		PartDefinition vegi4_r1 = stick.addOrReplaceChild("vegi4_r1", CubeListBuilder.create()
				.texOffs(5, 0).addBox(-1.5F, -14.5F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 5).addBox(-2.0F, -6.5F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition vegi3_r1 = stick.addOrReplaceChild("vegi3_r1", CubeListBuilder.create()
				.texOffs(4, 5).addBox(-2.0F, -11.5F, -1.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2094F, 0.0F));

		PartDefinition vegi2_r1 = stick.addOrReplaceChild("vegi2_r1", CubeListBuilder.create()
				.texOffs(5, 10).addBox(-2.0F, -9.5F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0175F, -0.0524F,
						0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		stick.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
