package defeatedcrow.hac.core.client.entity.model;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.core.event.ClientTickEventDC;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class ModelMagicWing<T extends LivingEntity> extends AgeableListModel<T> {

	public final ModelPart right_wing;
	public final ModelPart left_wing;
	public boolean crouching;

	public ModelMagicWing(ModelPart part) {
		this.right_wing = part.getChild("right_wing");
		this.left_wing = part.getChild("left_wing");
	}

	public static LayerDefinition createWingMesh() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		PartDefinition right_wing = part.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition wing_r2 = right_wing.addOrReplaceChild("wing_r2", CubeListBuilder.create().texOffs(16, 0).addBox(-18.0F, 1.0F, 6.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, -4.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition wing_r1 = right_wing.addOrReplaceChild("wing_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, 2.0F, 4.0F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, -4.0F, 0.0F, 0.1745F, 0.0873F));

		PartDefinition left_wing = part.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition wing_l2 = left_wing.addOrReplaceChild("wing_l2", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(10.0F, 1.0F, 6.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition wing_l1 = left_wing.addOrReplaceChild("wing_l1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3.0F, 2.0F, 4.0F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose
			.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.0F, -0.1745F, -0.0873F));

		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public void setupAnim(T living, float f1, float f2, float part, float headYaw, float headPitch) {
		boolean b = !living.isOnGround() && living.getDeltaMovement().length() > 0.05D;
		if (b) {
			int i1 = ClientTickEventDC.i;
			int i2 = i1 - 1;
			if (i1 == 0) {
				i2 = 1;
			} else if (i1 > 5) {
				i1 = 11 - i1;
				i2 = 11 - i2;
			}
			float r = Mth.rotLerp(0F, i2 * 10F, i1 * 10F);
			this.right_wing.yRot = r * Mth.DEG_TO_RAD;
			this.left_wing.yRot = -r * Mth.DEG_TO_RAD;
		} else {
			this.right_wing.yRot = 45F * Mth.DEG_TO_RAD;
			this.left_wing.yRot = -45F * Mth.DEG_TO_RAD;
		}
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.left_wing, this.right_wing);
	}

}
