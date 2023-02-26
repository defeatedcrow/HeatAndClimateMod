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

public class BreadSausageModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bread1;
	private final ModelPart sausage;
	private final ModelPart bread2;

	public BreadSausageModel(ModelPart root) {
		this.bread1 = root.getChild("bread1");
		this.sausage = root.getChild("sausage");
		this.bread2 = root.getChild("bread2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bread1 = partdefinition.addOrReplaceChild("bread1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.1F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F,
			0.0F));

		PartDefinition sausage = partdefinition.addOrReplaceChild("sausage", CubeListBuilder.create().texOffs(13, 0).addBox(-0.5F, -1.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F,
			0.0F));

		PartDefinition bread2 = partdefinition.addOrReplaceChild("bread2", CubeListBuilder.create().texOffs(0, 8).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F,
			0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bread1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		sausage.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bread2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
