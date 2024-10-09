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
public class RollCrusherModel extends Model {

	private final ModelPart base;
	private final ModelPart rot;
	private final ModelPart rot2;

	public RollCrusherModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.base = root.getChild("base");
		this.rot = root.getChild("rot");
		this.rot2 = root.getChild("rot2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-8.0F, -5.0F, -8.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.0F, -5.0F, -8.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.0F, -5.0F, 6.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.0F, -5.0F, 6.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-2.0F, -3.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-8.0F, -6.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 34).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(43, 34).addBox(-4.0F, -16.0F, -4.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(37, 34).addBox(-3.0F, -16.0F, 3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(27, 53).addBox(6.0F, -11.0F, 0.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(27, 53).addBox(6.0F, -11.0F, -3.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(27, 53).addBox(-7.0F, -11.0F, -3.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(36, 1).addBox(-4.0F, -16.0F, 4.0F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(48, 9).addBox(-7.0F, -15.5F, 4.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(36, 53).addBox(-7.0F, -11.0F, 0.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition motor3_r1 = base.addOrReplaceChild("motor3_r1", CubeListBuilder.create()
				.texOffs(47, 49).addBox(-7.0F, -7.0F, 8.5F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8378F, 0.0F, 0.0F));

		PartDefinition box6_r1 = base.addOrReplaceChild("box6_r1", CubeListBuilder.create()
				.texOffs(37, 34).addBox(-3.0F, -16.0F, 3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(43, 34).addBox(-4.0F, -16.0F, -4.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition box3_r1 = base.addOrReplaceChild("box3_r1", CubeListBuilder.create()
				.texOffs(37, 34).addBox(-3.0F, -16.0F, 0.2F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 3.1416F, 0.0F));

		PartDefinition box2_r1 = base.addOrReplaceChild("box2_r1", CubeListBuilder.create()
				.texOffs(37, 34).addBox(-3.0F, -16.0F, 0.2F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition rot = partdefinition.addOrReplaceChild("rot", CubeListBuilder.create()
				.texOffs(0, 53).addBox(-5.0F, -1.5F, -1.5F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5F, 1.5F, 0.2618F, 0.0F, 0.0F));

		PartDefinition rot2 = partdefinition.addOrReplaceChild("rot2", CubeListBuilder.create()
				.texOffs(0, 53).addBox(-5.0F, -1.5F, -1.5F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5F, -1.5F, 0.2618F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(float swing) {
		rot.xRot = swing * Mth.DEG_TO_RAD;
		rot2.xRot = swing * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rot2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderRot1(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderRot2(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rot2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
