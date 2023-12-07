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
public class StoneMillModel extends Model {

	private final ModelPart base;
	private final ModelPart rot;

	public StoneMillModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.base = root.getChild("base");
		this.rot = root.getChild("rot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
			.texOffs(0, 0).addBox(-8.0F, -7.0F, -8.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(6.0F, -7.0F, -8.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-8.0F, -7.0F, 6.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(6.0F, -7.0F, 6.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-6.0F, -7.0F, -6.0F, 12.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 9).addBox(-2.0F, -5.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 19).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(0, 20).addBox(7.0F, -16.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 20).addBox(-8.0F, -16.0F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(7, 20).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(32, 37).addBox(-7.0F, -16.0F, -1.0F, 14.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 37).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition rot = partdefinition.addOrReplaceChild("rot", CubeListBuilder.create()
			.texOffs(0, 49).addBox(-5.0F, -14.1F, -5.0F, 10.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(float swing) {
		rot.yRot = swing * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderTop(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
