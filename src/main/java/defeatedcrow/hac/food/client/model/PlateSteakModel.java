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

public class PlateSteakModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart plate;
	private final ModelPart steak;

	public PlateSteakModel(ModelPart root) {
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

		PartDefinition steak = partdefinition.addOrReplaceChild("steak", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition veg2 = steak.addOrReplaceChild("veg2", CubeListBuilder.create().texOffs(22, 42).mirror().addBox(4.0F, -2.0F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4014F, 0.0F));

		PartDefinition veg1 = steak.addOrReplaceChild("veg1", CubeListBuilder.create().texOffs(12, 42).mirror().addBox(4.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition potato4 = steak.addOrReplaceChild("potato4", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(2.8F, -2.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.3491F, 0.0F));

		PartDefinition potato3 = steak.addOrReplaceChild("potato3", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(4.0F, -2.2F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.5236F, 0.0F));

		PartDefinition potato2 = steak.addOrReplaceChild("potato2", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(0.8F, -1.0F, 1.8F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(0, 42).mirror().addBox(-0.3F, -1.0F, 2.2F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition steak1 = steak.addOrReplaceChild("steak1", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-5.5F, -2.0F, -3.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1222F, 0.0F));

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
