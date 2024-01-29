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

public class CasseroleModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart main;

	public CasseroleModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
			.texOffs(29, 2).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(44, 0).addBox(5.0F, -4.0F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(-8, 10).addBox(-5.0F, -3.5F, -4.0F, 10.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(0, 4).addBox(6.0F, -3.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-7.0F, -3.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-5.0F, -1.0F, -4.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube = main.addOrReplaceChild("cube", CubeListBuilder.create()
			.texOffs(44, 0).addBox(5.0F, -4.0F, -4.0F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(29, 2).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
