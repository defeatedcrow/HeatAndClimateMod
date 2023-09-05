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
public class BlockChamberFuelModel extends Model {

	private final ModelPart fuel;

	public BlockChamberFuelModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.fuel = root.getChild("fuel");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition fuel = partdefinition.addOrReplaceChild("fuel", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition cube_r1 = fuel.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(1, 11).addBox(-1.0F, -7.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.6545F, -2.618F, 0.0F));

		PartDefinition cube_r2 = fuel.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, 1).addBox(-2.0F, -6.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.4363F, 1.2217F, 0.0F));

		PartDefinition cube_r3 = fuel.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -5.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.0873F, -0.2618F, 0.0F));

		PartDefinition cube_r4 = fuel.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 1).addBox(0.0F, -5.5F, -5.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, 0.0873F, -1.1345F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		fuel.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
