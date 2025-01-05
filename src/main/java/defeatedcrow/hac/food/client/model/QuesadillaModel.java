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

public class QuesadillaModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart tortilla;
	private final ModelPart cheese;

	public QuesadillaModel(ModelPart root) {
		this.tortilla = root.getChild("tortilla");
		this.cheese = root.getChild("cheese");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tortilla = partdefinition.addOrReplaceChild("tortilla", CubeListBuilder.create()
				.texOffs(0, 5).addBox(-5.0F, -1.0F, -3.0F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = tortilla.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 10).addBox(-5.5F, -1.5F, 0.0F, 11.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = tortilla.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-5.0F, -2.0F, -3.0F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cheese = partdefinition.addOrReplaceChild("cheese", CubeListBuilder.create()
				.texOffs(0, 13).addBox(-4.5F, -1.5F, -2.5F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tortilla.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		cheese.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
