package defeatedcrow.hac.core.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.core.material.block.building.LockerTile;
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
public class BlockLockerModel extends Model {

	private final ModelPart main;
	private final ModelPart door;

	public BlockLockerModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.door = root.getChild("door");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
			.texOffs(0, 18).addBox(-8.0F, -15.0F, 7.0F, 16.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(25, 19).addBox(7.0F, -15.0F, -8.0F, 1.0F, 14.0F, 15.0F, new CubeDeformation(0.0F))
			.texOffs(25, 19).addBox(-8.0F, -15.0F, -8.0F, 1.0F, 14.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition door = partdefinition.addOrReplaceChild("door", CubeListBuilder.create().texOffs(1, 49).addBox(-14.0F, -7.0F, -0.5F, 14.0F, 14.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(32, 49).addBox(-13.0F, -2.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(LockerTile tile, float swing) {
		door.yRot = -swing * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		door.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
