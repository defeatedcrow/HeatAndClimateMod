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
public class CookingPotModel_FishLayer extends Model {

	private final ModelPart layer;
	private final ModelPart fish;

	public CookingPotModel_FishLayer(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.layer = root.getChild("layer");
		this.fish = root.getChild("fish");
	}

	public static LayerDefinition createOvarlayLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition layer = partdefinition.addOrReplaceChild("layer", CubeListBuilder.create()
		    .texOffs(-16, 0)
		    .addBox(-8.0F, -5.5F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition fish = partdefinition.addOrReplaceChild("fish", CubeListBuilder.create()
		    .texOffs(0, 16)
		    .addBox(-3.0F, -6.5F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(26, 20)
		    .addBox(5.0F, -6.0F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(20, 17)
		    .addBox(-7.0F, -6.2F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(23, 23)
		    .addBox(6.0F, -5.8F, -3.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(9, 24)
		    .addBox(3.0F, -6.2F, -1.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(14, 17)
		    .addBox(-6.0F, -6.3F, -2.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(14, 23)
		    .addBox(1.0F, -6.4F, -2.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 23)
		    .addBox(-5.0F, -6.4F, -2.5F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		layer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fish.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
