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

public class CrapeModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart main;

	public CrapeModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
		    .texOffs(0, 29)
		    .addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 25)
		    .addBox(-1.5F, -3.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 19)
		    .addBox(-2.0F, -6.0F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 12)
		    .addBox(-2.5F, -9.0F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 6)
		    .addBox(-3.0F, -11.0F, -2.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(19, 3)
		    .addBox(-1.0F, -12.5F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(12, 0)
		    .addBox(-0.5F, -6.0F, 2.0F, 1.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
		    .texOffs(-6, 0)
		    .addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0F, -0.05F, 0F));

		PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(20, 20)
		    .addBox(-2.8F, -12.0F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r2 = main.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(15, 0)
		    .addBox(-3.0F, -13.0F, 0.0F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r3 = main.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(21, 12)
		    .addBox(-2.0F, -13.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, -0.4363F, 0.0F));

		PartDefinition cube_r4 = main.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(21, 8)
		    .addBox(-2.5F, -12.3F, -1.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, -0.2618F, 0.0F));

		PartDefinition cube_r5 = main.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(20, 16)
		    .addBox(0.8F, -12.0F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

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
