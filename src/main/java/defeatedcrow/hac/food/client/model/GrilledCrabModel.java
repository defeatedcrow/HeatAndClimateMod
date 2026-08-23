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

public class GrilledCrabModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart main;

	public GrilledCrabModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -2.0F, -2.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(14, 7)
		    .mirror()
		    .addBox(3.0F, -0.5F, -3.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		    .mirror(false)
		    .texOffs(14, 7)
		    .addBox(-6.0F, -0.5F, -3.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(9, 12)
		    .mirror()
		    .addBox(0.0F, -1.0F, 3.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		    .mirror(false)
		    .texOffs(0, 12)
		    .mirror()
		    .addBox(1.0F, -1.5F, 2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		    .mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		PartDefinition cube_r2 = main.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(9, 12)
		    .addBox(-2.0F, -1.0F, 3.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 12)
		    .addBox(-4.0F, -1.5F, 2.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r3 = main.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(9, 7)
		    .mirror()
		    .addBox(-0.5F, -1.5F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		    .mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 2.0071F, 0.0F));

		PartDefinition cube_r4 = main.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .mirror()
		    .addBox(-0.5F, -1.5F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		    .mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 1.6581F, 0.0F));

		PartDefinition cube_r5 = main.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .mirror()
		    .addBox(-1.0F, -1.5F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		    .mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 1.3963F, 0.0F));

		PartDefinition cube_r6 = main.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(9, 7)
		    .addBox(-0.5F, -1.5F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, -2.0071F, 0.0F));

		PartDefinition cube_r7 = main.addOrReplaceChild("cube_r7", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(-0.5F, -1.5F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, -1.6581F, 0.0F));

		PartDefinition cube_r8 = main.addOrReplaceChild("cube_r8", CubeListBuilder.create()
		    .texOffs(0, 7)
		    .addBox(0.0F, -1.5F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, -1.3963F, 0.0F));

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
