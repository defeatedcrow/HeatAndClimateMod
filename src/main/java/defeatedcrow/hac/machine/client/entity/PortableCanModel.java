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
public class PortableCanModel extends Model {

	private final ModelPart cap;
	private final ModelPart main;

	public PortableCanModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.cap = root.getChild("cap");
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition cap = partdefinition.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(30, 25).addBox(0.0F, -15.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
			PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(4, 1).addBox(-6.0F, -1.0F, -4.0F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-7.0F, -12.0F, -5.0F, 14.0F, 11.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(0, 21).addBox(2.0F, -14.0F, -4.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(5, 23).addBox(2.0F, -15.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 22).addBox(-6.0F, -13.0F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(2, 21).addBox(-4.0F, -14.0F, -4.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(5, 23).addBox(-3.0F, -15.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(30, 25).addBox(3.2F, -15.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(17, 25).addBox(-2.0F, -15.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cap.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
