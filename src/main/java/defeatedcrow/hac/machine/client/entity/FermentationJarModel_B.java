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
public class FermentationJarModel_B extends Model {

	private final ModelPart body;

	public FermentationJarModel_B(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
			.texOffs(0, 54).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 43).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(0, 29).addBox(-5.0F, -6.0F, -5.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(0, 11).addBox(-5.5F, -12.0F, -5.5F, 11.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-4.5F, -13.0F, -4.5F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
			.texOffs(35, 11).addBox(-3.5F, -14.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(38, 6).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
