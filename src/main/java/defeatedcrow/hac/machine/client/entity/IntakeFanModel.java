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
public class IntakeFanModel extends Model {

	private final ModelPart main;
	private final ModelPart fan;
	private final ModelPart power_on;
	private final ModelPart power_off;
	private final ModelPart error_on;
	private final ModelPart error_off;

	public IntakeFanModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.fan = root.getChild("fan");
		this.power_on = root.getChild("power_on");
		this.power_off = root.getChild("power_off");
		this.error_on = root.getChild("error_on");
		this.error_off = root.getChild("error_off");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-6.0F, -2.0F, -6.0F, 12.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(16, 8).addBox(-8.0F, -16.0F, -6.0F, 2.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(16, 8).addBox(6.0F, -16.0F, -6.0F, 2.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(2, 11).addBox(-2.0F, -10.0F, 0.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(2, 11).addBox(-2.0F, -10.0F, -8.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(37, 24).addBox(-3.0F, -11.0F, -7.5F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 0).addBox(-6.0F, -14.0F, -5.5F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(40, 12).addBox(-6.0F, -14.0F, 1.5F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition fan = partdefinition.addOrReplaceChild("fan", CubeListBuilder.create()
				.texOffs(1, 17).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -4.0F));

		PartDefinition cube_r1 = fan.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(1, 25).addBox(-5.0F, -4.0F, 2.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0873F, -1.5708F));

		PartDefinition cube_r2 = fan.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(1, 25).addBox(-5.0F, -4.0F, 2.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0873F, -3.1416F));

		PartDefinition cube_r3 = fan.addOrReplaceChild("cube_r3", CubeListBuilder.create()
				.texOffs(1, 25).addBox(-5.0F, -4.0F, 2.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0873F, 1.5708F));

		PartDefinition cube_r4 = fan.addOrReplaceChild("cube_r4", CubeListBuilder.create()
				.texOffs(1, 25).addBox(-5.0F, -4.0F, 2.0F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0873F, 0.0F));

		PartDefinition power_on = partdefinition.addOrReplaceChild("power_on", CubeListBuilder.create()
				.texOffs(0, 2).addBox(-4.5F, -1.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition power_off = partdefinition.addOrReplaceChild("power_off", CubeListBuilder.create()
				.texOffs(4, 2).addBox(-4.5F, -1.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition error_on = partdefinition.addOrReplaceChild("error_on", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-6.0F, -1.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition error_off = partdefinition.addOrReplaceChild("error_off", CubeListBuilder.create()
				.texOffs(4, 0).addBox(-6.0F, -1.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public void setupAnim(float swing) {
		fan.zRot = -swing * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		fan.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderButton(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha, boolean flag, boolean power) {
		if (flag) {
			error_on.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		} else {
			error_off.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
		if (power) {
			power_on.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		} else {
			power_off.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}

}
