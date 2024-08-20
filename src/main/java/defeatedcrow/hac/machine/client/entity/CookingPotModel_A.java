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
public class CookingPotModel_A extends Model {

	private final ModelPart top;
	private final ModelPart main;

	public CookingPotModel_A(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.top = root.getChild("top");
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 19).addBox(-6.0F, -8.0F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(22, 11).addBox(-1.0F, -9.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(30, 12).addBox(-0.5F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 11).addBox(-5.0F, -7.0F, -6.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(34, 1).addBox(-7.0F, -6.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(34, 1).addBox(6.0F, -6.0F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 11).addBox(-5.0F, -7.0F, -6.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F,
						0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 11).addBox(-5.0F, -7.0F, -6.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F,
						0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 11).addBox(-5.0F, -7.0F, -6.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F,
						0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderTop(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
