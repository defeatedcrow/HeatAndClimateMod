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

public class StickPotatoModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart stick;

	public StickPotatoModel(ModelPart root) {
		this.stick = root.getChild("stick");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition stick
		    = partdefinition.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -15.0F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(4, 0).mirror().addBox(-2.0F, -6.5F,
		        -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.ZERO);

		PartDefinition vegi2 = stick.addOrReplaceChild("vegi2", CubeListBuilder.create().texOffs(4, 8).mirror().addBox(-2.0F, -10.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
		    PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		PartDefinition vegi1 = stick.addOrReplaceChild("vegi1", CubeListBuilder.create().texOffs(4, 0).mirror().addBox(-2.0F, -14.5F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
		    PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		stick.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
