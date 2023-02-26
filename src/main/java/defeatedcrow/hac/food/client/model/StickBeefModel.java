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

public class StickBeefModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart stick;

	public StickBeefModel(ModelPart root) {
		this.stick = root.getChild("stick");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition stick1 = partdefinition.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -15.0F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offset(0.0F, 0.0F, 0.0F));

		PartDefinition meat3 = stick1.addOrReplaceChild("meat3", CubeListBuilder.create().texOffs(6, 0).mirror().addBox(-3.0F, -14.0F, -1.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0349F, 0.0524F, 0.0F));

		PartDefinition meat2 = stick1.addOrReplaceChild("meat2", CubeListBuilder.create().texOffs(6, 6).mirror().addBox(-3.0F, -10.0F, -1.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0349F, 0.0F));

		PartDefinition meat1 = stick1.addOrReplaceChild("meat1", CubeListBuilder.create().texOffs(6, 0).mirror().addBox(-3.0F, -6.0F, -1.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0349F, 0.0698F, 0.0F));

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
