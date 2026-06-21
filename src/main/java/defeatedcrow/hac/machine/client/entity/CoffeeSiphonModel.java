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
public class CoffeeSiphonModel extends Model {

	private final ModelPart glass;
	private final ModelPart base;
	private final ModelPart water;
	private final ModelPart water_2;
	private final ModelPart water_3;
	private final ModelPart coffee_1;
	private final ModelPart coffee_2;
	private final ModelPart coffee_3;
	private final ModelPart coffee_4;
	private final ModelPart coffee_5;
	private final ModelPart coffee_6;
	private final ModelPart coffee_7;

	public CoffeeSiphonModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.glass = root.getChild("glass");
		this.base = root.getChild("base");
		this.water = root.getChild("water");
		this.water_2 = root.getChild("water_2");
		this.water_3 = root.getChild("water_3");
		this.coffee_1 = root.getChild("coffee_1");
		this.coffee_2 = root.getChild("coffee_2");
		this.coffee_3 = root.getChild("coffee_3");
		this.coffee_4 = root.getChild("coffee_4");
		this.coffee_5 = root.getChild("coffee_5");
		this.coffee_6 = root.getChild("coffee_6");
		this.coffee_7 = root.getChild("coffee_7");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create()
		    .texOffs(0, 0)
		    .addBox(-2.5F, -2.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 6)
		    .addBox(-3.0F, -5.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		    .texOffs(20, 0)
		    .addBox(-2.5F, -6.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 15)
		    .addBox(-2.5F, -9.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 21)
		    .addBox(-3.0F, -16.0F, -2.9F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = glass.addOrReplaceChild("cube_r1", CubeListBuilder.create()
		    .texOffs(14, 21)
		    .addBox(-2.0F, -16.0F, -2.9F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = glass.addOrReplaceChild("cube_r2", CubeListBuilder.create()
		    .texOffs(14, 21)
		    .addBox(-2.0F, -16.0F, -2.9F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = glass.addOrReplaceChild("cube_r3", CubeListBuilder.create()
		    .texOffs(0, 21)
		    .addBox(-3.0F, -16.0F, -2.9F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
		    .texOffs(0, 35)
		    .addBox(-1.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(8, 34)
		    .addBox(-1.5F, -7.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 38)
		    .addBox(-1.5F, -9.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 43)
		    .addBox(-0.5F, -7.0F, 1.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		    .texOffs(13, 38)
		    .addBox(-1.0F, -6.0F, 5.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(13, 46)
		    .addBox(-5.0F, -1.0F, 5.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 49)
		    .mirror()
		    .addBox(-6.0F, -1.0F, -5.0F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		    .mirror(false)
		    .texOffs(0, 49)
		    .addBox(4.0F, -1.0F, -5.0F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition water = partdefinition.addOrReplaceChild("water", CubeListBuilder.create()
		    .texOffs(27, 7)
		    .addBox(-2.0F, -2.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition water_2 = partdefinition.addOrReplaceChild("water_2", CubeListBuilder.create()
		    .texOffs(25, 12)
		    .addBox(-2.5F, -3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition water_3 = partdefinition.addOrReplaceChild("water_3", CubeListBuilder.create()
		    .texOffs(25, 12)
		    .addBox(-2.5F, -4.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition coffee_1 = partdefinition.addOrReplaceChild("coffee_1", CubeListBuilder.create()
		    .texOffs(46, 7)
		    .addBox(-2.0F, -10.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition coffee_2 = partdefinition.addOrReplaceChild("coffee_2", CubeListBuilder.create()
		    .texOffs(46, 12)
		    .addBox(-2.0F, -11.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition coffee_3 = partdefinition.addOrReplaceChild("coffee_3", CubeListBuilder.create()
		    .texOffs(46, 12)
		    .addBox(-2.0F, -12.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition coffee_4 = partdefinition.addOrReplaceChild("coffee_4", CubeListBuilder.create()
		    .texOffs(46, 12)
		    .addBox(-2.0F, -13.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition coffee_5 = partdefinition.addOrReplaceChild("coffee_5", CubeListBuilder.create()
		    .texOffs(27, 18)
		    .addBox(-2.0F, -2.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition coffee_6 = partdefinition.addOrReplaceChild("coffee_6", CubeListBuilder.create()
		    .texOffs(25, 23)
		    .addBox(-2.5F, -3.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition coffee_7 = partdefinition.addOrReplaceChild("coffee_7", CubeListBuilder.create()
		    .texOffs(25, 23)
		    .addBox(-2.5F, -4.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderGlass(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		glass.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage16(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		water.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		water_2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		water_3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage32(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		coffee_1.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage1(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		water.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		water_2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		water_3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_1.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage2(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		water.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		water_2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_1.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage3(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		water.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_1.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage4(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		coffee_1.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_4.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage5(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		coffee_1.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_5.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage6(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		coffee_1.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_5.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_6.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage7(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		coffee_1.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_5.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_6.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_7.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderStage8(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		coffee_5.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_6.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		coffee_7.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
