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

public class IcecreamModel<T extends Entity> extends EntityModel<T> implements TranslucentPartModel {

	protected final ModelPart glass;
	protected final ModelPart icecream;

	public IcecreamModel(ModelPart root) {
		this.glass = root.getChild("glass");
		this.icecream = root.getChild("icecream");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(9, 13)
		    .addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 8)
		    .addBox(-4.0F, -7.0F, 3.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 13)
		    .addBox(-4.0F, -7.0F, -3.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition glass6_r1 = glass.addOrReplaceChild("glass6_r1", CubeListBuilder.create()
		    .texOffs(0, 13)
		    .addBox(-4.0F, -7.0F, -3.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 8)
		    .addBox(-4.0F, -7.0F, 3.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition glass3_r1 = glass.addOrReplaceChild("glass3_r1", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, 3.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition icecream = partdefinition.addOrReplaceChild("icecream", CubeListBuilder.create()
		    .texOffs(0, 23)
		    .addBox(-2.5F, -8.2F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(25, 30)
		    .addBox(-1.0F, -9.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(21, 30)
		    .addBox(0.0F, -9.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition fruit_r1 = icecream.addOrReplaceChild("fruit_r1", CubeListBuilder.create()
		    .texOffs(16, 21)
		    .addBox(-2.0F, -11.0F, -2.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6109F, 1.0472F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
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
