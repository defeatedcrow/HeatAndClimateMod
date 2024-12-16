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
public class BlockShelfIronModel extends Model {

	private final ModelPart top;
	private final ModelPart stand;

	public BlockShelfIronModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.top = root.getChild("top");
		this.stand = root.getChild("stand");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition stand = partdefinition.addOrReplaceChild("stand", CubeListBuilder.create()
				.texOffs(0, 0).addBox(7.0F, -16.0F, 0.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(-7, 19).addBox(-7.5F, -1.0F, 0.5F, 15.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(4, 0).addBox(-7.0F, -1.0F, 0.5F, 14.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(-7, 19).addBox(-7.5F, -9.0F, 0.5F, 15.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(4, 0).addBox(-7.0F, -9.0F, 0.5F, 14.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = stand.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(4, 0).addBox(-7.0F, -9.0F, -7.5F, 14.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(4, 0).addBox(-7.0F, -1.0F, -7.5F, 14.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r2 = stand.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(0, 0).addBox(7.0F, -16.0F, -8.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 8.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = stand.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(0, 0).addBox(7.0F, -16.0F, 0.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 8.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r4 = stand.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-1.0F, -16.0F, -8.0F, 1.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 8.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create()
				.texOffs(-7, 19).addBox(-7.5F, -16.0F, 0.5F, 15.0F, 0.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(4, 0).addBox(-7.0F, -16.0F, 0.5F, 14.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r5 = top.addOrReplaceChild("cube_r5", CubeListBuilder.create()
				.texOffs(4, 0).addBox(-7.0F, -16.0F, -7.5F, 14.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		stand.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderTop(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		top.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
