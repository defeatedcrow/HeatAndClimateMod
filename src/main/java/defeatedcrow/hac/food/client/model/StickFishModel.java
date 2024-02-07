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

public class StickFishModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart stick;

	public StickFishModel(ModelPart root) {
		this.stick = root.getChild("stick");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition stick = partdefinition.addOrReplaceChild("stick", CubeListBuilder.create()
			.texOffs(0, 0).mirror().addBox(-0.5F, -15.0F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.ZERO);

		PartDefinition cube_r1 = stick.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 0).addBox(-1.0F, -13.0F, 4.25F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(5, 2).addBox(-1.6F, -12.0F, 3.75F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(17, 6).addBox(-1.5F, -6.5F, 0.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r2 = stick.addOrReplaceChild("cube_r2", CubeListBuilder.create()
			.texOffs(15, 1).addBox(-1.5F, -11.0F, 2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r3 = stick.addOrReplaceChild("cube_r3", CubeListBuilder.create()
			.texOffs(5, 6).addBox(-2.0F, -8.0F, -4.5F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r4 = stick.addOrReplaceChild("cube_r4", CubeListBuilder.create()
			.texOffs(5, 11).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r5 = stick.addOrReplaceChild("cube_r5", CubeListBuilder.create()
			.texOffs(9, 11).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		stick.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
