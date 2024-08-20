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
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FaucetModel_A extends Model {

	protected final ModelPart handle;
	protected final ModelPart main;

	public FaucetModel_A(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.handle = root.getChild("handle");
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create()
				.texOffs(9, 23).addBox(-2.5F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 3.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-2.0F, -10.0F, 7.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 19).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(15, 8).addBox(-1.5F, -9.5F, 1.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(9, 19).addBox(-1.0F, -10.5F, 2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 19).addBox(-0.5F, -11.5F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public void setupAnim(boolean flag) {
		handle.yRot = flag ? -90F * Mth.DEG_TO_RAD : 0F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		handle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
