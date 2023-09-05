package defeatedcrow.hac.core.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.core.material.block.building.CabinetTile;
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
public class BlockCabinetModel extends Model {

	private final ModelPart main;
	private final ModelPart door;
	private final ModelPart door2;

	public BlockCabinetModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.door = root.getChild("door");
		this.door2 = root.getChild("door2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(0, 18).addBox(-8.0F, -15.0F, 7.0F, 16.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(34, 17).addBox(7.0F, -15.0F, -7.0F, 1.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(34, 17).addBox(-8.0F, -15.0F, -7.0F, 1.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
			.texOffs(0, 51).addBox(-7.0F, -9.0F, -5.0F, 14.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(16, 34).addBox(-7.0F, -7.0F, -0.5F, 7.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(40, 19).addBox(-6.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -8.0F, -7.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition door2 = partdefinition.addOrReplaceChild("door2", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, -7.0F, -0.5F, 7.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(35, 19).addBox(5.0F, -2.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -8.0F, -7.0F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(CabinetTile tile, float swing) {
		door.yRot = -swing * Mth.DEG_TO_RAD;
		door2.yRot = swing * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		door.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		door2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
