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

public class TartModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart main;

	public TartModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
			.texOffs(0, 15).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 10).addBox(-3.0F, -2.5F, -4.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(15, 15).addBox(3.0F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_1 = main.addOrReplaceChild("cube_1", CubeListBuilder.create()
			.texOffs(15, 15).addBox(3.0F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 10).addBox(-3.0F, -2.5F, -4.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 15).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_2 = main.addOrReplaceChild("cube_2", CubeListBuilder.create()
			.texOffs(15, 15).addBox(3.0F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 10).addBox(-3.0F, -2.5F, -4.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 15).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_3 = main.addOrReplaceChild("cube_3", CubeListBuilder.create()
			.texOffs(15, 15).addBox(3.0F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 10).addBox(-3.0F, -2.5F, -4.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 15).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

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
