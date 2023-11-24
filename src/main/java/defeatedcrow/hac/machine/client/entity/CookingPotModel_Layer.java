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
public class CookingPotModel_Layer extends Model {

	private final ModelPart layer;

	public CookingPotModel_Layer(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.layer = root.getChild("layer");
	}

	public static LayerDefinition createOvarlayLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition layer = partdefinition.addOrReplaceChild("layer", CubeListBuilder.create().texOffs(-16, 0)
			.addBox(-8.0F, -5.5F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		layer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
