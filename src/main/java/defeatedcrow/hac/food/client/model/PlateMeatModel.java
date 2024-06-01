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

public class PlateMeatModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart plate;
	private final ModelPart steak1;

	public PlateMeatModel(ModelPart root) {
		this.plate = root.getChild("plate");
		this.steak1 = root.getChild("steak1");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6.0F, -1.0F, -5.0F, 12.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).mirror().addBox(-7.0F, -3.0F, -6.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).mirror().addBox(-7.0F, -3.0F, 5.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).mirror().addBox(-7.0F, -3.0F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).mirror().addBox(6.0F, -3.0F, -5.0F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(36, 0).mirror().addBox(-8.0F, -3.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(36, 0).mirror().addBox(7.0F, -3.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Steak = partdefinition.addOrReplaceChild("steak1", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition veg2 = Steak.addOrReplaceChild("veg2", CubeListBuilder.create().texOffs(22, 42).mirror().addBox(4.0F, -2.0F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4014F, 0.0F));

		PartDefinition veg1 = Steak.addOrReplaceChild("veg1", CubeListBuilder.create().texOffs(12, 42).mirror().addBox(4.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition potato4 = Steak.addOrReplaceChild("potato4", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(2.8F, -2.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.3491F, 0.0F));

		PartDefinition potato3 = Steak.addOrReplaceChild("potato3", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(4.0F, -2.2F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, -0.5236F, 0.0F));

		PartDefinition potato2 = Steak.addOrReplaceChild("potato2", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(0.8F, -1.0F, 1.8F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 42).mirror().addBox(-0.3F, -1.0F, 2.2F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition steak3 = Steak.addOrReplaceChild("steak3", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(-3.0F, -6.0F, -2.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).mirror().addBox(-3.0F, -4.0F, -0.3F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).mirror().addBox(-3.0F, -2.0F, 1.0F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.7854F, 1.2566F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		plate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		steak1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
