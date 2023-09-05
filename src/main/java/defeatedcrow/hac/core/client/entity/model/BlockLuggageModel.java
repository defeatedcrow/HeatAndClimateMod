package defeatedcrow.hac.core.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import defeatedcrow.hac.core.material.block.building.LuggageTile;
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
public class BlockLuggageModel extends Model {

	private final ModelPart main;
	private final ModelPart huta;
	private final ModelPart band;

	public BlockLuggageModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.huta = root.getChild("huta");
		this.band = root.getChild("band");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -1.0F, -4.0F, 14.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
			.texOffs(35, 12).addBox(7.0F, -3.0F, -5.0F, 1.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
			.texOffs(2, 12).addBox(-8.0F, -3.0F, -5.0F, 1.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
			.texOffs(17, 18).addBox(-7.0F, -4.0F, 6.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(17, 13).addBox(-7.0F, -2.0F, -5.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(17, 52).addBox(-7.0F, -4.0F, 7.0F, 14.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(3, 30).addBox(-6.0F, 0.1F, -5.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(3, 35).addBox(4.0F, 0.1F, -5.0F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition huta = partdefinition.addOrReplaceChild("huta", CubeListBuilder.create().texOffs(0, 30).addBox(-8.0F, -1.0F, -13.0F, 16.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
			.texOffs(0, 46).addBox(-8.0F, 0.0F, -13.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
			.texOffs(36, 46).addBox(7.0F, 0.0F, -13.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
			.texOffs(17, 46).addBox(-7.0F, 0.0F, -13.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(40, 1).addBox(-4.0F, 0.5F, -16.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(2, 6).addBox(-3.0F, 1.0F, -15.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(2, 1).addBox(2.0F, 1.0F, -15.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 8.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition band = partdefinition.addOrReplaceChild("band", CubeListBuilder.create().texOffs(1, 31).addBox(-6.0F, 0.1F, -2.9F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(1, 36).addBox(4.0F, 0.1F, -2.9F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.1F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(LuggageTile tile, float swing) {
		huta.xRot = swing * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		huta.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		band.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
