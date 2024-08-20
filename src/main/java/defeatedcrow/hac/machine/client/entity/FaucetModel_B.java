package defeatedcrow.hac.machine.client.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FaucetModel_B extends FaucetModel_A {

	public FaucetModel_B(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 7.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition fauset = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
				.texOffs(19, 0).addBox(-1.0F, -7.0F, 6.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -1.0F, 5.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r1 = fauset.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 9).addBox(-1.0F, -3.75F, -9.3F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 7.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r2 = fauset.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 5).addBox(-1.0F, -3.0F, -10.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose
				.offsetAndRotation(0.0F, 0.0F, 7.0F, -0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(boolean flag) {
		handle.xRot = flag ? -60F * Mth.DEG_TO_RAD : -10F * Mth.DEG_TO_RAD;
	}

}
