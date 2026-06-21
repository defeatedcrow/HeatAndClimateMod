package defeatedcrow.hac.food.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.food.client.TranslucentPartModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class IcecreamPuddingModel<T extends Entity> extends EntityModel<T> implements TranslucentPartModel {

	protected final ModelPart glass;
	protected final ModelPart icecream;

	public IcecreamPuddingModel(ModelPart root) {
		this.glass = root.getChild("glass");
		this.icecream = root.getChild("icecream");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(18, 0)
		    .addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 7)
		    .addBox(-3.0F, -4.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 14)
		    .addBox(-5.0F, -4.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 20)
		    .addBox(-3.0F, -6.0F, -4.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(9, 15)
		    .addBox(3.0F, -6.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(9, 15)
		    .addBox(-5.0F, -6.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(9, 15)
		    .addBox(3.0F, -6.0F, 2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(9, 15)
		    .addBox(-5.0F, -6.0F, 2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(13, 14)
		    .addBox(5.0F, -6.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(13, 14)
		    .addBox(-6.0F, -6.0F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = glass.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(0, 20)
		    .addBox(-3.0F, -6.0F, -4.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 14)
		    .addBox(-5.0F, -4.0F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition icecream = partdefinition.addOrReplaceChild("icecream", CubeListBuilder.create()
		    .texOffs(32, 0)
		    .addBox(-3.5F, -7.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(44, 0)
		    .addBox(0.5F, -7.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(45, 7)
		    .addBox(-1.0F, -6.0F, -2.8F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(45, 7)
		    .addBox(-1.0F, -6.0F, 0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(53, 7)
		    .addBox(2.8F, -6.0F, -0.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(53, 7)
		    .addBox(-4.8F, -6.0F, -0.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r2 = icecream.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(41, 18)
		    .addBox(0.0F, -8.0F, -2.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		    .texOffs(41, 15)
		    .addBox(0.0F, -7.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r3 = icecream.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(45, 12)
		    .addBox(-2.0F, -9.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -1.9199F, 0.0F));

		PartDefinition cube_r4 = icecream.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(32, 18)
		    .addBox(-2.0F, -10.0F, 1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, -1.1345F, 0.0F));

		PartDefinition cube_r5 = icecream.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(32, 13)
		    .addBox(-3.0F, -9.5F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, -0.2618F, 0.0F));

		PartDefinition cube_r6 = icecream.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(38, 7)
		    .addBox(-1.0F, -9.5F, -2.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.4363F, 0.0F));

		PartDefinition cube_r7 = icecream.addOrReplaceChild("cube_r7", CubeListBuilder.create()
		    .texOffs(32, 7)
		    .addBox(-1.0F, -10.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 0.8727F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float yaw, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		icecream.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void renderTranslucent(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		glass.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
