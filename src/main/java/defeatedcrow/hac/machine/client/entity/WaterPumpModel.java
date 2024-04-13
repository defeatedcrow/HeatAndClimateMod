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
public class WaterPumpModel extends Model {

	private final ModelPart main;

	public WaterPumpModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
			.texOffs(0, 21).addBox(-6.0F, -4.0F, -6.0F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-5.0F, -14.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(32, 1).addBox(-2.0F, -16.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(43, 13).addBox(-4.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(48, 13).addBox(3.0F, -15.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(43, 10).addBox(-4.0F, -16.0F, 3.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
