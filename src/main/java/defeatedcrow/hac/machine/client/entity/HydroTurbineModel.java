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
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HydroTurbineModel extends Model {

	private final ModelPart turbine;

	public HydroTurbineModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.turbine = root.getChild("turbine");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition turbine = partdefinition.addOrReplaceChild("turbine", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -1.5F, -1.5F, 8.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 7).addBox(-4.0F, -0.5F, 1.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 6.0F));

		PartDefinition cube_r1 = turbine.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, -0.5F, 1.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = turbine.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, -0.5F, 1.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r3 = turbine.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, -0.5F, 1.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r4 = turbine.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, -0.5F, 1.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition cube_r5 = turbine.addOrReplaceChild("cube_r5", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, -0.5F, 1.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r6 = turbine.addOrReplaceChild("cube_r6", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, -0.5F, 1.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r7 = turbine.addOrReplaceChild("cube_r7", CubeListBuilder.create()
				.texOffs(0, 7).addBox(-4.0F, -0.5F, 1.5F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	public void setupAnim(float swing) {
		turbine.xRot = -swing * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		turbine.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
