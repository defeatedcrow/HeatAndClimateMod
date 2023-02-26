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

public class PlateGarlicModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart plate;
	private final ModelPart steak;

	public PlateGarlicModel(ModelPart root) {
		this.plate = root.getChild("plate");
		this.steak = root.getChild("steak");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6.0F, -1.0F, -5.0F, 12.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 12).mirror().addBox(-7.0F, -3.0F, -6.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 12).mirror().addBox(-7.0F, -3.0F, 5.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 17).mirror().addBox(-7.0F, -3.0F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 17).mirror().addBox(6.0F, -3.0F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(36, 0).mirror().addBox(-8.0F, -3.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(36, 0).mirror().addBox(7.0F, -3.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition steak = partdefinition.addOrReplaceChild("steak", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-5.0F, -5.0F, -3.5F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 46).mirror().addBox(0.0F, -6.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.0F, -0.0175F, 0.0F));

		PartDefinition butter = steak.addOrReplaceChild("butter", CubeListBuilder.create().texOffs(10, 46).mirror().addBox(-1.0F, -7.5F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		PartDefinition garlic3 = steak.addOrReplaceChild("garlic3", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(0.0F, -7.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition garlic1 = steak.addOrReplaceChild("garlic1", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-1.0F, -7.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		plate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		steak.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
