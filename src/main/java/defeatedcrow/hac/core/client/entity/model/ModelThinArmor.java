package defeatedcrow.hac.core.client.entity.model;

import java.util.function.Function;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class ModelThinArmor extends HumanoidModel {

	public ModelThinArmor(ModelPart part) {
		this(part, RenderType::entityCutoutNoCull);
	}

	public ModelThinArmor(ModelPart part, Function<ResourceLocation, RenderType> renderTypeIn) {
		super(part, renderTypeIn);
	}

	public static LayerDefinition createArmorMesh(CubeDeformation def) {
		return LayerDefinition.create(HumanoidModel.createMesh(def, 0.0F), 64, 32);
	}

	public static LayerDefinition createHatMesh() {
		MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0F), 0.0F);
		PartDefinition part = mesh.getRoot();
		PartDefinition head = part.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -10.0F, -4.5F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.55F)),
			PartPose.ZERO);
		head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 17).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0F)), PartPose.ZERO);
		return LayerDefinition.create(mesh, 64, 32);
	}

	public static LayerDefinition createLongMesh() {
		MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.4F), 0.0F);
		PartDefinition part = mesh.getRoot();
		PartDefinition body = part.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
			.texOffs(29, 33).addBox(-3.0F, 11.8F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.6F))
			.texOffs(29, 46).addBox(-3.5F, 17.0F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.6F))
			.texOffs(18, 6).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.6F))
			.texOffs(21, 3).addBox(-1.0F, 8.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.4F))
			.texOffs(28, 2).mirror().addBox(1.0F, 7.5F, 1.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.4F)).mirror(false)
			.texOffs(28, 2).addBox(-3.0F, 7.5F, 1.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.4F)), PartPose.ZERO);

		PartDefinition left_shoe = part.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false)
			.texOffs(0, 33).addBox(-2.9F, -0.2F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.4F))
			.texOffs(0, 46).addBox(-3.4F, 5.0F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(1.9F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition right_shoe = part.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
			.texOffs(0, 33).mirror().addBox(-3.6F, -0.2F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.4F)).mirror(false)
			.texOffs(0, 46).mirror().addBox(-4.1F, 5.0F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(-1.9F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = part.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false)
			.texOffs(40, 7).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_arm = part.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
			.texOffs(40, 7).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		return LayerDefinition.create(mesh, 64, 64);
	}

}
