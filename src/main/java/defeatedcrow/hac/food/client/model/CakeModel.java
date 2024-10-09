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

public class CakeModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bottom;
	private final ModelPart jelly1;
	private final ModelPart jelly2;
	private final ModelPart jelly3;
	private final ModelPart fruits;

	public CakeModel(ModelPart root) {
		this.bottom = root.getChild("bottom");
		this.jelly1 = root.getChild("jelly1");
		this.jelly2 = root.getChild("jelly2");
		this.jelly3 = root.getChild("jelly3");
		this.fruits = root.getChild("fruits");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create()
				.texOffs(28, 0).mirror().addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(16, 18).mirror().addBox(-6.0F, -1.5F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition jelly1 = partdefinition.addOrReplaceChild("jelly1", CubeListBuilder.create()
				.texOffs(0, 9).mirror().addBox(-3.5F, -6.0F, -3.5F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.ZERO);

		PartDefinition jelly2 = partdefinition.addOrReplaceChild("jelly2", CubeListBuilder.create()
				.texOffs(0, 19).mirror().addBox(-3.5F, -4.0F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.ZERO);

		PartDefinition jelly3 = partdefinition.addOrReplaceChild("jelly3", CubeListBuilder.create()
				.texOffs(0, 0).mirror().addBox(-3.5F, -7.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.ZERO);

		PartDefinition fruits = partdefinition.addOrReplaceChild("fruits", CubeListBuilder.create()
				.texOffs(0, 0).addBox(0.0F, -8.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 2).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float yaw, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		jelly1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		jelly2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		jelly3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fruits.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderJelly1(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		jelly1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		jelly2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fruits.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderJelly2(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		jelly3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
