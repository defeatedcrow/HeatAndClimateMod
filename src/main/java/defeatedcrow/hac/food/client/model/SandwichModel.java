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

public class SandwichModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart main;

	public SandwichModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -1.2F, -1.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F,
			0.0F));

		PartDefinition pin = bb_main.addOrReplaceChild("pin", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F,
			0.0F, 0.5236F, 0.2182F, 0.0F));

		PartDefinition leaves2 = bb_main.addOrReplaceChild("leaves2", CubeListBuilder.create().texOffs(7, 6).addBox(-6.5F, -1.0F, 1.5F, 9.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, -4.0F, 0.5411F, 0.2182F, -0.0436F));

		PartDefinition leaves1 = bb_main.addOrReplaceChild("leaves1", CubeListBuilder.create().texOffs(7, 0).addBox(-3.5F, -0.2F, 0.5F, 9.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-1.0F, 0.0F, 0.0087F, 0.0F, 0.0F));

		PartDefinition meat1 = bb_main.addOrReplaceChild("meat1", CubeListBuilder.create().texOffs(0, 22).addBox(-2.5F, -0.8F, 0.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-1.0F, 0.0F, 0.096F, 0.0F, 0.0F));

		PartDefinition meat2 = bb_main.addOrReplaceChild("meat2", CubeListBuilder.create().texOffs(0, 22).addBox(-5.5F, -1.3F, 1.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, -4.0F, 0.6545F, 0.2182F, -0.0436F));

		PartDefinition bread4 = bb_main.addOrReplaceChild("bread4", CubeListBuilder.create().texOffs(0, 13).addBox(-6.0F, -2.0F, 0.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, -4.0F, 0.6981F, 0.2182F, -0.0436F));

		PartDefinition bread2 = bb_main.addOrReplaceChild("bread2", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -1.5F, -1.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-1.0F, 0.0F, 0.1396F, 0.0F, 0.0F));

		PartDefinition bread3 = bb_main.addOrReplaceChild("bread3", CubeListBuilder.create().texOffs(0, 13).addBox(-6.0F, -1.0F, 0.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, -4.0F, 0.5236F, 0.2182F, -0.0436F));

		PartDefinition paper4 = bb_main.addOrReplaceChild("paper4", CubeListBuilder.create().texOffs(-6, 0).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0436F, 0.7854F, 0.0436F));

		PartDefinition paper3 = bb_main.addOrReplaceChild("paper3", CubeListBuilder.create().texOffs(-6, 0).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, 2.3562F, -0.0436F));

		PartDefinition paper2 = bb_main.addOrReplaceChild("paper2", CubeListBuilder.create().texOffs(-6, 0).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0436F, -2.3562F, -0.0436F));

		PartDefinition paper1 = bb_main.addOrReplaceChild("paper1", CubeListBuilder.create().texOffs(-6, 0).addBox(-6.0F, 0.0F, 0.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, -0.7854F, 0.0436F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
