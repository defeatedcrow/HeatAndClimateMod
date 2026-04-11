package defeatedcrow.hac.core.client.entity.model;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//@formatter:off
@OnlyIn(Dist.CLIENT)
public class CanoeModel extends BoatModel {

	public CanoeModel(ModelPart root) {
		super(root, false);
	}

	public static LayerDefinition createCanoeLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition front = partdefinition.addOrReplaceChild("front",CubeListBuilder.create().texOffs(0, 25)
		    .addBox(5.0F, -7.0F, -1.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 25)
		    .addBox(-7.0F, -7.0F, -1.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(12, 25)
		    .addBox(3.0F,-7.0F, 3.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(12, 25)
		    .addBox(-5.0F, -7.0F, 3.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(32, 17)
		    .addBox(-3.0F, -9.0F, 5.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 17)
		    .addBox(-5.0F, -1.0F, -1.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
		    PartPose.offsetAndRotation(15.0F, 3.0F, 0.0F, 0.0F, (float)Math.PI / 2F, 0.0F));

		PartDefinition back = partdefinition.addOrReplaceChild("back",CubeListBuilder.create().texOffs(0, 25)
		    .addBox(1.0F, -7.0F, -1.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 25)
		    .addBox(-11.0F, -7.0F, -1.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(12, 25)
		    .addBox(-9.0F, -7.0F, 3.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(12, 25)
		    .addBox(-1.0F, -7.0F, 3.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(32, 17)
		    .addBox(-7.0F, -9.0F, 5.0F, 6.0F, 8.0F, 4.0F,new CubeDeformation(0.0F)).texOffs(0, 17)
		    .addBox(-9.0F, -1.0F, -1.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
		    PartPose.offsetAndRotation(-15.0F, 3.0F, 4.0F, 0.0F, (float)Math.PI * 1.5F, 0.0F));
		
		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create().texOffs(0, 43)
		    .addBox(-14.0F, -7.0F, -2.0F, 28.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
		    PartPose.offset(0.0F, 3.0F, 9.0F));
		
		PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create().texOffs(0, 35)
			.addBox(-14.0F, -7.0F, -2.0F, 28.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
		    PartPose.offsetAndRotation(0.0F, 3.0F, -9.0F, 0.0F, (float)Math.PI, 0.0F));
		
		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom",CubeListBuilder.create().texOffs(0, 0)
		    .addBox(-14.0F, -8.0F, -3.0F, 28.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 51)
		    .addBox(-14.0F, 19.0F, 1.0F, 28.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(64, 46)
		    .addBox(11.0F, 7.0F, 5.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(64, 46)
		    .addBox(-13.0F, 7.0F, 5.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)),
		    PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, (float)Math.PI / 2F, 0.0F, 0.0F));

		PartDefinition paddle_left = partdefinition.addOrReplaceChild("left_paddle", CubeListBuilder.create().texOffs(62, 0)
		    .addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(62, 0)
		    .addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)),
		    PartPose.offsetAndRotation(3.0F, -5.0F, 9.0F, 0.0F, 0.0F, (float)Math.PI/16F));

		PartDefinition paddle_right = partdefinition.addOrReplaceChild("right_paddle",CubeListBuilder.create().texOffs(62, 20)
		    .addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(62, 20)
		    .addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)),
		    PartPose.offsetAndRotation(3.0F, -5.0F, -9.0F, 0.0F, (float)Math.PI, (float)Math.PI/16F));

		partdefinition.addOrReplaceChild("water_patch", CubeListBuilder.create().texOffs(0, 0)
			.addBox(-18.0F, -8.0F, -3.0F, 36.0F, 14.0F, 3.0F),
			PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, (float) Math.PI / 2F, 0.0F, 0.0F));
		
		return LayerDefinition.create(meshdefinition, 128, 64);
	}
}
