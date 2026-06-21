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

public class IcecreamFloatModel<T extends Entity> extends EntityModel<T> implements TranslucentPartModel {

	protected final ModelPart glass;
	protected final ModelPart icecream;

	public IcecreamFloatModel(ModelPart root) {
		this.glass = root.getChild("glass");
		this.icecream = root.getChild("icecream");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(12, 7)
		    .addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 7)
		    .addBox(-2.0F, -4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 12)
		    .addBox(-3.5F, -14.0F, -3.5F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition icecream = partdefinition.addOrReplaceChild("icecream", CubeListBuilder.create()
		    .texOffs(29, 0)
		    .addBox(-3.0F, -14.0F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(29, 15)
		    .addBox(-2.8F, -16.0F, -2.2F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(50, 22)
		    .addBox(3.0F, -16.0F, -0.5F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = icecream.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(50, 18)
		    .addBox(3.0F, -16.0F, -1.5F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		    .texOffs(50, 16)
		    .addBox(2.6F, -14.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r2 = icecream.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(50, 20)
		    .addBox(2.2F, -14.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

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
