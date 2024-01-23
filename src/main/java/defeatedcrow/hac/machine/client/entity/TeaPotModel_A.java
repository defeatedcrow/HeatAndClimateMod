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
public class TeaPotModel_A extends Model {

	private final ModelPart main;
	private final ModelPart spout;

	public TeaPotModel_A(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.spout = root.getChild("spout");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(2, 26).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(0, 2).addBox(-2.5F, -5.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(0, 2).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 25).addBox(-3.0F, -1.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 8).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-3.5F, -3.0F, -3.5F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(20, 0).addBox(0.0F, -9.0F, -3.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(22, 0).addBox(-0.5F, -9.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition spout = partdefinition.addOrReplaceChild("spout", CubeListBuilder.create().texOffs(28, 11).addBox(-0.5F, -7.0F, -2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		spout.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
