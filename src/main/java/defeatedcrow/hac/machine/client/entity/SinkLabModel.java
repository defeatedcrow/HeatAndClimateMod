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
public class SinkLabModel extends Model {

	private final ModelPart body;

	public SinkLabModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-8.0F, -12.0F, -7.0F, 16.0F, 12.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-6.0F, -13.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 50).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(1, 54).addBox(-8.0F, -15.0F, -7.0F, 16.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 43).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(36, 43).addBox(-6.0F, -16.0F, -8.0F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(36, 43).addBox(-6.0F, -16.0F, -8.0F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
