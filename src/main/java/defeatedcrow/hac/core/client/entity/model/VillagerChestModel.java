package defeatedcrow.hac.core.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.core.material.block.building.VillagerChestTile;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VillagerChestModel extends Model {

	private final ModelPart main;
	private final ModelPart top;

	public VillagerChestModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.top = root.getChild("top");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-8.0F, -9.0F, -8.0F, 16.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(35, 18).addBox(-8.0F, -9.0F, 7.0F, 16.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 5).addBox(7.0F, -9.0F, -7.0F, 1.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition side2_r1 = main.addOrReplaceChild("side2_r1", CubeListBuilder.create()
			.texOffs(70, 5).addBox(7.0F, -9.0F, -7.0F, 1.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create()
			.texOffs(0, 43).addBox(-8.0F, -7.0F, -10.5F, 16.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(70, 29).addBox(6.8F, -2.0F, -14.5F, 1.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(70, 29).addBox(-7.8F, -2.0F, -14.5F, 1.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(71, 30).addBox(6.8F, -4.0F, -14.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(71, 30).addBox(-7.8F, -4.0F, -14.0F, 1.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(72, 32).addBox(6.8F, -5.0F, -13.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(72, 32).addBox(-7.8F, -5.0F, -13.5F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(75, 35).addBox(6.8F, -6.0F, -12.1F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(75, 35).addBox(-7.8F, -6.0F, -12.1F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -1.0F, -16.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 7.0F));

		PartDefinition cube_r1 = top.addOrReplaceChild("cube_r1", CubeListBuilder.create()
			.texOffs(35, 37).addBox(-8.0F, -0.5F, 6.8F, 16.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, 1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r2 = top.addOrReplaceChild("cube_r2", CubeListBuilder.create()
			.texOffs(35, 30).addBox(-8.0F, -3.0F, 7.0F, 16.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r3 = top.addOrReplaceChild("cube_r3", CubeListBuilder.create()
			.texOffs(0, 37).addBox(-8.0F, -0.5F, -7.8F, 16.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition cube_r4 = top.addOrReplaceChild("cube_r4", CubeListBuilder.create()
			.texOffs(0, 30).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, -0.2618F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	public void setupAnim(VillagerChestTile tile, float swing) {
		top.xRot = swing * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		top.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
