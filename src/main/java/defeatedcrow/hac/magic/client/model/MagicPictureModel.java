package defeatedcrow.hac.magic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.magic.material.entity.MagicPictureEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MagicPictureModel extends EntityModel<MagicPictureEntity> {

	private final ModelPart main;

	public MagicPictureModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-7.0F, -1.0F, -2.0F, 14.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(-8.0F, -16.0F, -2.0F, 1.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(7.0F, -16.0F, -2.0F, 1.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -16.0F, -2.0F, 14.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(7, 4).addBox(-7.0F, -15.0F, -1.0F, 14.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(MagicPictureEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
