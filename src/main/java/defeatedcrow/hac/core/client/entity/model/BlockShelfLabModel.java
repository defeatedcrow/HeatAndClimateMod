package defeatedcrow.hac.core.client.entity.model;

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
public class BlockShelfLabModel extends Model {

	private final ModelPart top;
	private final ModelPart stand;

	public BlockShelfLabModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.top = root.getChild("top");
		this.stand = root.getChild("stand");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition stand = partdefinition.addOrReplaceChild("stand", CubeListBuilder.create()
				.texOffs(0, 0).addBox(7.0F, -16.0F, 0.0F, 1.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-8.0F, -16.0F, 0.0F, 1.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(13, 0).addBox(-7.0F, -1.0F, 1.0F, 14.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(13, 0).addBox(-7.0F, -9.0F, 1.0F, 14.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(19, 8).addBox(0.0F, -16.0F, 7.0F, 7.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(19, 8).addBox(-7.0F, -16.0F, 7.0F, 7.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 8).addBox(-7.0F, -16.0F, 0.0F, 7.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(36, 8).mirror().addBox(0.0F, -16.0F, 0.0F, 7.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.ZERO);

		PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create()
				.texOffs(13, 0).addBox(-7.0F, -16.0F, 1.0F, 14.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		stand.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderTop(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		top.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
