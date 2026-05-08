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

public class DumplingModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart bowl;
	private final ModelPart meal;

	public DumplingModel(ModelPart root) {
		this.bowl = root.getChild("bowl");
		this.meal = root.getChild("meal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create()
		    .texOffs(4, 0)
		    .addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition side_r1 = bowl.addOrReplaceChild("side_r1", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-1.0F, -4.7F, 0.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -2.3562F, 0.0F));

		PartDefinition side_r2 = bowl.addOrReplaceChild("side_r2", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-1.0F, -4.7F, 0.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 2.3562F, 0.0F));

		PartDefinition side_r3 = bowl.addOrReplaceChild("side_r3", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-1.0F, -4.5F, 0.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -0.7854F, 0.0F));

		PartDefinition side_r4 = bowl.addOrReplaceChild("side_r4", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-1.0F, -4.7F, 0.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.7854F, 0.0F));

		PartDefinition side_r5 = bowl.addOrReplaceChild("side_r5", CubeListBuilder.create()
		    .texOffs(3, 7)
		    .addBox(-2.5F, -4.0F, 0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 1.5708F, 0.0F));

		PartDefinition side_r6 = bowl.addOrReplaceChild("side_r6", CubeListBuilder.create()
		    .texOffs(3, 7)
		    .addBox(-2.5F, -4.0F, 0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 3.1416F, 0.0F));

		PartDefinition side_r7 = bowl.addOrReplaceChild("side_r7", CubeListBuilder.create()
		    .texOffs(3, 7)
		    .addBox(-2.5F, -4.0F, 0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, -1.5708F, 0.0F));

		PartDefinition side_r8 = bowl.addOrReplaceChild("side_r8", CubeListBuilder.create()
		    .texOffs(3, 7)
		    .addBox(-2.5F, -4.0F, 0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create()
		    .texOffs(0, 11)
		    .addBox(-2.0F, -3.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 17)
		    .addBox(-1.5F, -3.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float yaw, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bowl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		meal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
