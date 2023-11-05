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
public class CookingPotModel_B extends Model {

	private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart bone4;
	private final ModelPart top;
	private final ModelPart main;

	public CookingPotModel_B(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.bone = root.getChild("bone");
		this.bone2 = root.getChild("bone2");
		this.bone3 = root.getChild("bone3");
		this.bone4 = root.getChild("bone4");
		this.top = root.getChild("top");
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 20).addBox(-7.0F, -6.0F, -7.0F, 14.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 15).addBox(-8.0F, -7.0F, -8.0F, 16.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(2, 20).addBox(-6.0F, -6.0F, -7.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(2, 15).addBox(-7.0F, -7.0F, -8.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 20).addBox(-7.0F, -6.0F, -7.0F, 14.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 15).addBox(-8.0F, -7.0F, -8.0F, 16.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(2, 20).addBox(-6.0F, -6.0F, -7.0F, 12.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(2, 15).addBox(-7.0F, -7.0F, -8.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(23, 21).addBox(-5.0F, -8.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(36, 15).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(0, 28).addBox(-6.0F, -7.0F, -6.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = top.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(2, 28).addBox(-5.0F, -7.0F, -6.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = top.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(2, 28).addBox(-5.0F, -7.0F, -6.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = top.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 28).addBox(-6.0F, -7.0F, -6.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -1.0F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-9.0F, -7.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(8.0F, -7.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderTop(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
