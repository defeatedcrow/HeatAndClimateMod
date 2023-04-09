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

public class ModelMagicFin<T extends LivingEntity> extends AgeableListModel<T> {

	public final ModelPart fin;
	public final ModelPart tail;
	public boolean crouching;

	public ModelMagicFin(ModelPart part) {
		this.tail = part.getChild("tail");
		this.fin = part.getChild("fin");
	}

	public static LayerDefinition createFinMesh() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();

		PartDefinition tail = part.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, 2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition tail5 = tail.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(12, 7).addBox(0.0F, -4.0F, 4.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,
			-0.1745F, 0.0F, 0.0F));

		PartDefinition tail4 = tail.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(10, 0).addBox(-5.0F, -1.0F, 9.0F, 10.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition tail3 = tail.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(12, 7).addBox(-0.5F, -0.5F, 8.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,
			-0.2618F, 0.0F, 0.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, 0.0F, 5.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,
			-0.0873F, 0.0F, 0.0F));

		PartDefinition fin = part.addOrReplaceChild("fin", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition side1 = fin.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(37, -8).addBox(3.0F, -4.0F, 3.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,
			-0.5236F, 0.7854F, 0.0F));

		PartDefinition side2 = fin.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(37, -8).addBox(-3.0F, -4.0F, 3.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F,
			-0.5236F, -0.7854F, 0.0F));

		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public void setupAnim(T living, float f1, float f2, float part, float headYaw, float headPitch) {
		boolean b = !living.isOnGround() && living.isInFluidType() && living.getDeltaMovement().length() > 0.05D;
		if (b) {
			int i1 = ClientTickEventDC.i;
			int i2 = i1 - 1;
			if (i1 == 0) {
				i2 = 1;
			} else if (i1 > 5) {
				i1 = 11 - i1;
				i2 = 11 - i2;
			}
			float r = Mth.rotLerp(0F, i2 * 10F, i1 * 10F) - 20F;
			this.tail.xRot = r * Mth.DEG_TO_RAD;
			this.fin.xRot = r * Mth.DEG_TO_RAD;
		} else {
			this.tail.xRot = 0F * Mth.DEG_TO_RAD;
			this.fin.xRot = 0F * Mth.DEG_TO_RAD;
		}
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.tail, this.fin);
	}

}
