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
public class KichenBenchSUSModel extends Model {

	private final ModelPart body;
	private final ModelPart stand;

	public KichenBenchSUSModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.body = root.getChild("body");
		this.stand = root.getChild("stand");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 46).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 23).addBox(-7.5F, -3.0F, -7.5F, 15.0F, 0.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition stand = partdefinition.addOrReplaceChild("stand", CubeListBuilder.create()
				.texOffs(0, 23).addBox(7.0F, -14.0F, -8.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = stand.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(0, 23).addBox(7.0F, -14.0F, -8.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = stand.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 23).addBox(7.0F, -14.0F, -8.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r3 = stand.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 23).addBox(7.0F, -14.0F, -8.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		stand.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
