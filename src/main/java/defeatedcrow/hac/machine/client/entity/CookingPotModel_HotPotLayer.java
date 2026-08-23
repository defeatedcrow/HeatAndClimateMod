package defeatedcrow.hac.machine.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CookingPotModel_HotPotLayer extends Model {

	private final ModelPart layer;
	private final ModelPart meal;

	public CookingPotModel_HotPotLayer(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.layer = root.getChild("layer");
		this.meal = root.getChild("meal");
	}

	public static LayerDefinition createOvarlayLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition meal = partdefinition.addOrReplaceChild("meal", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r1 = meal.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(0, 28)
		    .addBox(-2.0F, -5.8F, -4.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		PartDefinition cube_r2 = meal.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(14, 21)
		    .addBox(-4.0F, -6.0F, -7.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r3 = meal.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(12, 28)
		    .addBox(-5.0F, -6.0F, -6.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r4 = meal.addOrReplaceChild("cube_r4", CubeListBuilder.create()
		    .texOffs(14, 16)
		    .addBox(-1.0F, -5.8F, 2.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r5 = meal.addOrReplaceChild("cube_r5", CubeListBuilder.create()
		    .texOffs(14, 16)
		    .addBox(-5.0F, -5.5F, 4.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, -0.3491F, 0.0F));

		PartDefinition cube_r6 = meal.addOrReplaceChild("cube_r6", CubeListBuilder.create()
		    .texOffs(0, 16)
		    .addBox(2.0F, -6.0F, -3.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.4363F, 0.0F));

		PartDefinition cube_r7 = meal.addOrReplaceChild("cube_r7", CubeListBuilder.create()
		    .texOffs(0, 16)
		    .addBox(4.0F, -6.0F, -3.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.6109F, 0.0F));

		PartDefinition layer = partdefinition.addOrReplaceChild("layer", CubeListBuilder.create()
		    .texOffs(-16, 0)
		    .addBox(-8.0F, -5.5F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		layer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		meal.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
