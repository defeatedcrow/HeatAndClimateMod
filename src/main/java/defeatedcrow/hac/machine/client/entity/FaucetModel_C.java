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
public class FaucetModel_C extends FaucetModel_A {

	public FaucetModel_C(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create()
				.texOffs(21, 6).addBox(-0.5F, -1.5F, -4.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -1.0F, 7.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition fauset = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
				.texOffs(21, 0).addBox(-0.5F, -9.0F, 1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(-0.5F, -10.0F, 1.5F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(16, 0).addBox(-0.5F, -9.0F, 6.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -1.0F, 6.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(2.0F, -3.0F, 6.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(boolean flag) {
		handle.yRot = flag ? 0F : -90F * Mth.DEG_TO_RAD;
	}

}
