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

public class DrinkCupModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart cup;
	private final ModelPart tea;
	private final ModelPart tea_2;

	public DrinkCupModel(ModelPart root) {
		this.cup = root.getChild("cup");
		this.tea = root.getChild("tea");
		this.tea_2 = root.getChild("tea_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition cup = partdefinition.addOrReplaceChild("cup", CubeListBuilder.create()
		    .texOffs(6, 0)
		    .addBox(-3.0F, -3.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(8, 2)
		    .addBox(-2.5F, -2.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 9)
		    .addBox(2.5F, -8.0F, -3.5F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 9)
		    .addBox(-3.5F, -8.0F, -3.5F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		    .texOffs(17, 15)
		    .addBox(-2.5F, -8.0F, -3.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(17, 15)
		    .addBox(-2.5F, -8.0F, 2.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(-2, 23)
		    .addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		    .texOffs(10, 10)
		    .addBox(-4.0F, -1.25F, -6.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(25, 28)
		    .addBox(4.0F, -1.25F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(25, 28)
		    .addBox(4.0F, -1.25F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(25, 28)
		    .addBox(-5.0F, -1.25F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(25, 28)
		    .addBox(-5.0F, -1.25F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = cup.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(10, 10)
		    .addBox(-4.0F, -1.25F, -6.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(1, 23)
		    .addBox(-0.5F, -7.0F, 6.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(23, 23)
		    .addBox(-0.5F, -3.5F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(23, 23)
		    .addBox(-0.5F, -7.5F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = cup.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(10, 10)
		    .addBox(-4.0F, -1.25F, -6.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = cup.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(10, 10)
		    .addBox(-4.0F, -1.25F, -6.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition tea = partdefinition.addOrReplaceChild("tea", CubeListBuilder.create()
		    .texOffs(-5, 0)
		    .addBox(-2.5F, -7.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition tea_2 = partdefinition.addOrReplaceChild("tea_2", CubeListBuilder.create()
		    .texOffs(-5, 0)
		    .addBox(-2.5F, -7.8F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cup.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tea.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderTop(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cup.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tea_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
