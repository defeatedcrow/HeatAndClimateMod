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

public class SweetPotatoModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart main;

	public SweetPotatoModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
			.texOffs(26, 0).addBox(-5.0F, -2.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(16, 0).addBox(-4.0F, -3.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(16, 6).addBox(2.0F, -3.0F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(26, 4).addBox(4.0F, -2.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
