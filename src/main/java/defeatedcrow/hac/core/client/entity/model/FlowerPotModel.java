package defeatedcrow.hac.core.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.core.material.entity.ObjectEntityBaseDC;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class FlowerPotModel<T extends ObjectEntityBaseDC> extends EntityModel<ObjectEntityBaseDC> {

	private final ModelPart main;

	public FlowerPotModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 20)
		    .addBox(-4.0F, -5.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 7)
		    .addBox(-3.0F, -6.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 14)
		    .addBox(-2.0F, -7.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(16, 14)
		    .addBox(-3.0F, -8.0F, -3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(16, 16)
		    .addBox(-3.0F, -8.0F, 2.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(18, 0)
		    .addBox(2.0F, -8.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(18, 7)
		    .addBox(-3.0F, -8.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(ObjectEntityBaseDC entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
