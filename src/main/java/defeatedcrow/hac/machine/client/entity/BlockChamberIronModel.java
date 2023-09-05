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
public class BlockChamberIronModel extends Model {

	private final ModelPart chamber;
	private final ModelPart fuel;

	public BlockChamberIronModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.chamber = root.getChild("chamber");
		this.fuel = root.getChild("fuel");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition chamber = partdefinition.addOrReplaceChild("chamber", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(0, 19).addBox(-8.0F, -15.0F, 7.0F, 16.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 35).addBox(-8.0F, -15.0F, -8.0F, 16.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 51).addBox(-6.0F, -13.0F, -9.0F, 12.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = chamber.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(3, 19).addBox(-7.0F, -15.0F, 7.0F, 14.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = chamber.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(3, 19).addBox(-7.0F, -15.0F, 7.0F, 14.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition fuel = partdefinition.addOrReplaceChild("fuel", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition cube_r3 = fuel.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(38, 30).addBox(-1.0F, -7.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.6545F, -2.618F, 0.0F));

		PartDefinition cube_r4 = fuel.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(38, 20).addBox(-2.0F, -6.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.4363F, 1.2217F, 0.0F));

		PartDefinition cube_r5 = fuel.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(37, 40).addBox(-1.0F, -5.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.0873F, -0.2618F, 0.0F));

		PartDefinition cube_r6 = fuel.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(38, 20).addBox(0.0F, -5.5F, -5.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.0873F, -1.1345F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		chamber.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		fuel.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
