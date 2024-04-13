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
public class CableModel extends Model {

	private final ModelPart main;
	private final ModelPart N;
	private final ModelPart S;
	private final ModelPart W;
	private final ModelPart E;
	private final ModelPart D;
	private final ModelPart U;

	public CableModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.N = root.getChild("side_n");
		this.S = root.getChild("side_s");
		this.W = root.getChild("side_w");
		this.E = root.getChild("side_e");
		this.D = root.getChild("side_d");
		this.U = root.getChild("side_u");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0)
			.addBox(-2.0F, -10.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition S = partdefinition.addOrReplaceChild("side_s", CubeListBuilder.create().texOffs(0, 8)
			.addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition N = partdefinition.addOrReplaceChild("side_n", CubeListBuilder.create().texOffs(0, 8)
			.addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition E = partdefinition.addOrReplaceChild("side_e", CubeListBuilder.create().texOffs(0, 8)
			.addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition W = partdefinition.addOrReplaceChild("side_w", CubeListBuilder.create().texOffs(0, 8)
			.addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition D = partdefinition.addOrReplaceChild("side_d", CubeListBuilder.create().texOffs(0, 18)
			.addBox(-2.0F, -8.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition U = partdefinition.addOrReplaceChild("side_u", CubeListBuilder.create().texOffs(0, 18)
			.addBox(-2.0F, -8.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSideN(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		N.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSideS(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		S.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSideW(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		W.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSideE(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		E.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSideD(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		D.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSideU(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		U.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
