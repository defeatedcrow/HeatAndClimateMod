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

public class IcecreamSandaeModel<T extends Entity> extends EntityModel<T> implements TranslucentPartModel {

	protected final ModelPart glass;
	protected final ModelPart icecream;

	public IcecreamSandaeModel(ModelPart root) {
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
		    .texOffs(0, 24)
		    .addBox(-3.0F, -6.2F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(16, 17)
		    .addBox(-2.0F, -8.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(19, 25)
		    .addBox(-1.0F, -9.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = icecream.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(27, 26)
		    .addBox(-0.5F, -10.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

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
