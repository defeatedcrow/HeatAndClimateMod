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

public class BreadCreamModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart body;
	private final ModelPart b2;
	private final ModelPart b3;
	private final ModelPart b4;
	private final ModelPart b5;

	public BreadCreamModel(ModelPart root) {
		this.body = root.getChild("body");
		this.b2 = root.getChild("b2");
		this.b3 = root.getChild("b3");
		this.b4 = root.getChild("b4");
		this.b5 = root.getChild("b5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-2.5F, -2.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
			PartPose.ZERO);

		PartDefinition b2 = partdefinition.addOrReplaceChild("b2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, -1.7F, 1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.8326F, 0.0F));

		PartDefinition b3 = partdefinition.addOrReplaceChild("b3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -1.5F, 1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.618F, 0.0F));

		PartDefinition b4 = partdefinition.addOrReplaceChild("b4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -1.6F, 1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.618F, 0.0F));

		PartDefinition b5 = partdefinition.addOrReplaceChild("b5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, -1.7F, 1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.8326F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float yaw, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		b2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		b3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		b4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		b5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
