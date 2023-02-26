package defeatedcrow.hac.food.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class BreadSquareModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart base;
	private final ModelPart top;

	public BreadSquareModel(ModelPart root) {
		this.base = root.getChild("bread1");
		this.top = root.getChild("bread2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bread1 = partdefinition.addOrReplaceChild("bread1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -6.1F, -5.0F, 6.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bread2 = partdefinition.addOrReplaceChild("bread2", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-2.5F, -7.0F, -4.5F, 5.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
