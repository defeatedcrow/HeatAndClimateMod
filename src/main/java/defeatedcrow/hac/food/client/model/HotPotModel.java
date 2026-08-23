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

public class HotPotModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart main;
	private final ModelPart top;

	public HotPotModel(ModelPart root) {
		this.main = root.getChild("main");
		this.top = root.getChild("top");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
		    .texOffs(0, 4)
		    .addBox(-2.5F, -1.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 11)
		    .addBox(-3.5F, -3.0F, -3.5F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 1)
		    .addBox(-3.5F, -4.0F, -3.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(16, 2)
		    .addBox(2.5F, -4.0F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(16, 2)
		    .addBox(2.5F, -4.0F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 1)
		    .addBox(-3.5F, -4.0F, -3.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r2 = top.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(10, 22)
		    .addBox(-2.0F, -3.5F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r3 = top.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(5, 22)
		    .addBox(0.0F, -5.0F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.4363F, 0.0F));

		PartDefinition cube_r4 = top.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(0, 22)
		    .addBox(1.0F, -5.0F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.8727F, 0.7418F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
