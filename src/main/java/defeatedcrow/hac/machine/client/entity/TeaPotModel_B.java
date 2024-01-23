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
public class TeaPotModel_B extends Model {

	private final ModelPart main;

	public TeaPotModel_B(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(16, 0).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 6).addBox(-3.5F, -4.5F, -3.5F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(16, 0).addBox(-2.0F, -8.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(28, 27).addBox(-0.5F, -7.5F, 6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 26).addBox(-2.5F, -7.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(0, 17).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(15, 27).addBox(-0.5F, -8.0F, 3.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(18, 17).addBox(-1.0F, -5.0F, -5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(26, 18).addBox(-0.5F, -7.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r2 = main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 26).addBox(-0.5F, 1.0F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(19, 27).addBox(-0.5F, -3.0F, 6.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(26, 22).addBox(-0.5F, -9.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.6981F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
