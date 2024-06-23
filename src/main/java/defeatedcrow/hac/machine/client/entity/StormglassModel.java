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
public class StormglassModel extends Model {

	private final ModelPart base;
	private final ModelPart glass;
	private final ModelPart warm;
	private final ModelPart cold;
	private final ModelPart sun;
	private final ModelPart rainy;
	private final ModelPart bottom;
	private final ModelPart snow;
	private final ModelPart storm;

	public StormglassModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.base = root.getChild("base");
		this.glass = root.getChild("glass");
		this.warm = root.getChild("warm");
		this.cold = root.getChild("cold");
		this.sun = root.getChild("sun");
		this.rainy = root.getChild("rainy");
		this.bottom = root.getChild("bottom");
		this.snow = root.getChild("snow");
		this.storm = root.getChild("storm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-3.0F, -15.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create()
				.texOffs(0, 17).addBox(-2.5F, -13.0F, -2.5F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition warm = partdefinition.addOrReplaceChild("warm", CubeListBuilder.create()
				.texOffs(32, 6).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.ZERO);

		PartDefinition cold = partdefinition.addOrReplaceChild("cold", CubeListBuilder.create()
				.texOffs(32, 19).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.ZERO);

		PartDefinition sun = partdefinition.addOrReplaceChild("sun", CubeListBuilder.create()
				.texOffs(48, 6).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition rainy = partdefinition.addOrReplaceChild("rainy", CubeListBuilder.create()
				.texOffs(48, 19).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r1 = bottom.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(24, 10).addBox(0.0F, -7.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r2 = bottom.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(24, 6).addBox(0.0F, -7.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition snow = partdefinition.addOrReplaceChild("snow", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r3 = snow.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(24, 18)
				.addBox(1.0F, -9.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.7453F, 0.0F));

		PartDefinition cube_r4 = snow.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 16)
				.addBox(1.0F, -11.0F, -1.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r5 = snow.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(24, 18)
				.addBox(0.0F, -6.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r6 = snow.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(24, 16)
				.addBox(1.0F, -7.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition storm = partdefinition.addOrReplaceChild("storm", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r7 = storm.addOrReplaceChild("cube_r7", CubeListBuilder.create()
				.texOffs(24, 22).addBox(0.0F, -10.0F, -2.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.7453F, 0.0F));

		PartDefinition cube_r8 = storm.addOrReplaceChild("cube_r8", CubeListBuilder.create()
				.texOffs(24, 25).addBox(0.0F, -12.0F, -2.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition cube_r9 = storm.addOrReplaceChild("cube_r9", CubeListBuilder.create()
				.texOffs(24, 22).addBox(0.5F, -6.0F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r10 = storm.addOrReplaceChild("cube_r10", CubeListBuilder.create()
				.texOffs(24, 19).addBox(-0.5F, -8.0F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderGlass(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		glass.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderWater(boolean isCold, PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (isCold) {
			cold.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		} else {
			warm.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}

	public void renderSunnyLayer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		sun.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderRainyLayer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rainy.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderBottomCrystal(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bottom.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderSnowyCrystal(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		snow.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStormyCrystal(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		storm.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
