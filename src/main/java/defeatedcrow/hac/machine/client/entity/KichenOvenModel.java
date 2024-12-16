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
public class KichenOvenModel extends Model {

	private final ModelPart body;
	private final ModelPart stand;
	private final ModelPart off;
	private final ModelPart on;

	public KichenOvenModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.body = root.getChild("body");
		this.stand = root.getChild("stand");
		this.off = root.getChild("off");
		this.on = root.getChild("on");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 47).addBox(-8.0F, -15.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.0F, -14.0F, -7.0F, 16.0F, 14.0F, 15.0F, new CubeDeformation(0.0F))
				.texOffs(0, 35).addBox(-6.0F, -15.5F, -5.0F, 12.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(41, 34).addBox(1.5F, -16.0F, 2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(41, 34).addBox(-2.5F, -16.0F, 2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(41, 34).addBox(1.5F, -16.0F, -6.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(41, 34).addBox(-2.5F, -16.0F, -6.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(41, 41).addBox(3.0F, -16.0F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(41, 41).addBox(-7.0F, -16.0F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition stand = partdefinition.addOrReplaceChild("stand", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition off = partdefinition.addOrReplaceChild("off", CubeListBuilder.create()
				.texOffs(53, 34).addBox(5.0F, -13.5F, -8.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition on = partdefinition.addOrReplaceChild("on", CubeListBuilder.create()
				.texOffs(53, 38).addBox(4.5F, -13.0F, -8.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		stand.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderButton(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha, boolean power) {
		if (power) {
			on.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		} else {
			off.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}

}
