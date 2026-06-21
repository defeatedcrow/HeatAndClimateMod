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
public class CoffeeMacchinettaModel extends Model {

	private final ModelPart base;

	public CoffeeMacchinettaModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.base = root.getChild("base");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 11)
		    .addBox(-2.0F, -6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(24, 0)
		    .addBox(-3.0F, -11.5F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 16)
		    .addBox(-2.5F, -6.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(16, 12)
		    .addBox(-2.5F, -12.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(16, 28)
		    .addBox(-0.5F, -13.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(16, 23)
		    .addBox(-1.0F, -11.5F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(9, 23)
		    .addBox(-1.0F, -11.5F, -6.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(0, 23)
		    .addBox(-1.0F, -11.5F, -3.3F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
