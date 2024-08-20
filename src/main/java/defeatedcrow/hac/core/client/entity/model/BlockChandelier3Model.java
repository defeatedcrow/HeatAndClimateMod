package defeatedcrow.hac.core.client.entity.model;

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
public class BlockChandelier3Model extends Model {

	private final ModelPart main;
	private final ModelPart part;
	private final ModelPart part2;
	private final ModelPart part3;
	private final ModelPart part4;
	private final ModelPart part5;
	private final ModelPart part6;

	public BlockChandelier3Model(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.part = root.getChild("part");
		this.part2 = root.getChild("part2");
		this.part3 = root.getChild("part3");
		this.part4 = root.getChild("part4");
		this.part5 = root.getChild("part5");
		this.part6 = root.getChild("part6");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 1).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(59, 3).addBox(-1.0F, -14.0F, 0.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(59, 1).addBox(0.0F, -14.0F, -1.0F, 0.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition part = partdefinition.addOrReplaceChild("part", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(53, 19).addBox(-11.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = part.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r2 = part.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part2 = partdefinition.addOrReplaceChild("part2", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(53, 19).addBox(-11.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

		PartDefinition cube_r3 = part2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r4 = part2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part3 = partdefinition.addOrReplaceChild("part3", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(53, 19).addBox(-11.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition cube_r5 = part3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r6 = part3.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part4 = partdefinition.addOrReplaceChild("part4", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(53, 19).addBox(-11.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r7 = part4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r8 = part4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part5 = partdefinition.addOrReplaceChild("part5", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(53, 19).addBox(-11.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

		PartDefinition cube_r9 = part5.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r10 = part5.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part6 = partdefinition.addOrReplaceChild("part6", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(53, 19).addBox(-11.5F, -11.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

		PartDefinition cube_r11 = part6.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r12 = part6.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part4.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part5.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part6.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderFlowers(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

	}

}
