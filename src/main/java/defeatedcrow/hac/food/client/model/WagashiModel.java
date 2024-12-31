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

public class WagashiModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart dish;
	private final ModelPart mochi;

	public WagashiModel(ModelPart root) {
		this.dish = root.getChild("dish");
		this.mochi = root.getChild("mochi");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dish = partdefinition.addOrReplaceChild("dish", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-6.0F, -1.0F, -4.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 0).addBox(-3.0F, -1.1F, -3.5F, 6.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition mochi = partdefinition.addOrReplaceChild("mochi", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition mochi3_2_r1 = mochi.addOrReplaceChild("mochi3_2_r1", CubeListBuilder.create()
				.texOffs(16, 21).addBox(-2.5F, -4.0F, -3.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(1, 21).addBox(-0.5F, -4.0F, -0.5F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

		PartDefinition mochi2_2_r1 = mochi.addOrReplaceChild("mochi2_2_r1", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-1.0F, -2.5F, -2.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.1745F));

		PartDefinition mochi2_1_r1 = mochi.addOrReplaceChild("mochi2_1_r1", CubeListBuilder.create()
				.texOffs(15, 16).addBox(-3.5F, -2.0F, -3.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition mochi1_2_r1 = mochi.addOrReplaceChild("mochi1_2_r1", CubeListBuilder.create()
				.texOffs(15, 10).addBox(-4.0F, -3.0F, -2.6F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(-1.0F, -3.0F, 0.5F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		dish.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		mochi.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
