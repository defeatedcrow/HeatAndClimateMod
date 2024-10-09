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

public class PlateStuffedVegiModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart plate;
	private final ModelPart steak;

	public PlateStuffedVegiModel(ModelPart root) {
		this.plate = root.getChild("plate");
		this.steak = root.getChild("steak");
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

		PartDefinition Steak = partdefinition.addOrReplaceChild("steak", CubeListBuilder.create()
				.texOffs(17, 32).addBox(-4.5F, -4.0F, -4.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(0.5F, -4.0F, -4.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(-4.5F, -4.0F, 0.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(17, 32).addBox(0.5F, -4.0F, 0.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

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
