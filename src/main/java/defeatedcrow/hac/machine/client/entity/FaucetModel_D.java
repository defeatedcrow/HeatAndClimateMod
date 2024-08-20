package defeatedcrow.hac.machine.client.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FaucetModel_D extends FaucetModel_A {

	public FaucetModel_D(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-1.5F, -10.0F, 7.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(9, 0).addBox(-0.5F, -9.0F, 4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition head = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create()
				.texOffs(9, 0).addBox(-0.5F, -1.0F, -2.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(18, 0).addBox(-1.5F, -2.0F, -4.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 6).addBox(-2.5F, -3.0F, -5.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(-3.5F, -4.0F, -6.5F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 4.5F, 0.9599F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(boolean flag) {}

}
