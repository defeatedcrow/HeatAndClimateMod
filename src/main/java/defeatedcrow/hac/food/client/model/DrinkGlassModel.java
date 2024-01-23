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

public class DrinkGlassModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart glass;
	private final ModelPart inner;

	public DrinkGlassModel(ModelPart root) {
		this.glass = root.getChild("glass");
		this.inner = root.getChild("inner");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(0, 13).addBox(2.5F, -10.0F, -3.5F, 1.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(30, 13).addBox(-3.5F, -10.0F, -3.5F, 1.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(47, 19).addBox(-2.5F, -10.0F, -3.5F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(17, 19).addBox(-2.5F, -10.0F, 2.5F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition bb_main = partdefinition.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -8.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(15, 10).addBox(-2.5F, -8.9F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		glass.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderInner(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		inner.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
