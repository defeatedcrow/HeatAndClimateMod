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

public class DrinkDemitasseModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart cup;
	private final ModelPart coffee;

	public DrinkDemitasseModel(ModelPart root) {
		this.cup = root.getChild("cup");
		this.coffee = root.getChild("coffee");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glass = partdefinition.addOrReplaceChild("cup", CubeListBuilder.create()
		    .texOffs(3, 0)
		    .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 5)
		    .addBox(1.5F, -6.5F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 5)
		    .addBox(-2.5F, -6.5F, -2.5F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(20, 0)
		    .addBox(-1.5F, -6.5F, -2.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(20, 0)
		    .addBox(-1.5F, -6.5F, 1.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 16)
		    .addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(10, 7)
		    .addBox(-3.0F, -1.25F, -4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = glass.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(8, 7)
		    .addBox(-4.0F, -1.25F, -4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(18, 11)
		    .addBox(-0.5F, -5.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(23, 10)
		    .addBox(-0.5F, -3.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(23, 10)
		    .addBox(-0.5F, -6.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = glass.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(10, 7)
		    .addBox(-3.0F, -1.25F, -4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = glass.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(8, 7)
		    .addBox(-4.0F, -1.25F, -4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("coffee", CubeListBuilder.create()
		    .texOffs(-3, 0)
		    .addBox(-1.5F, -6.0F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cup.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		coffee.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
