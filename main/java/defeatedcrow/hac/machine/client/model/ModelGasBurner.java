package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGasBurner extends DCTileModelBase {

	ModelRenderer can;
	ModelRenderer middle;
	ModelRenderer middle2;
	ModelRenderer part1;
	ModelRenderer part2;
	ModelRenderer part3;
	ModelRenderer lever1;
	ModelRenderer lever2;
	ModelRenderer bottom;
	ModelRenderer bottom2;
	ModelRenderer bottom3;

	public ModelGasBurner() {
		textureWidth = 64;
		textureHeight = 64;

		can = new ModelRenderer(this, 0, 17);
		can.addBox(-6F, -1F, -6F, 12, 8, 12);
		can.setRotationPoint(0F, 0F, 0F);
		can.setTextureSize(64, 64);
		can.mirror = true;
		setRotation(can, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 0, 0);
		middle.addBox(-2F, -4F, -2F, 4, 2, 4);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.setTextureSize(64, 64);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		middle2 = new ModelRenderer(this, 32, 8);
		middle2.addBox(-4F, -2F, -4F, 8, 1, 8);
		middle2.setRotationPoint(0F, 0F, 0F);
		middle2.setTextureSize(64, 64);
		middle2.mirror = true;
		setRotation(middle2, 0F, 0F, 0F);
		part1 = new ModelRenderer(this, 0, 8);
		part1.addBox(-4F, -5F, -4F, 8, 1, 8);
		part1.setRotationPoint(0F, 0F, 0F);
		part1.setTextureSize(64, 64);
		part1.mirror = true;
		setRotation(part1, 0F, 0F, 0F);
		part2 = new ModelRenderer(this, 32, 0);
		part2.addBox(-6F, -6F, -0.5F, 12, 1, 1);
		part2.setRotationPoint(0F, 0F, 0F);
		part2.setTextureSize(64, 64);
		part2.mirror = true;
		setRotation(part2, 0F, 0.7853982F, 0F);
		part3 = new ModelRenderer(this, 32, 0);
		part3.addBox(-6F, -6F, -0.5F, 12, 1, 1);
		part3.setRotationPoint(0F, 0F, 0F);
		part3.setTextureSize(64, 64);
		part3.mirror = true;
		setRotation(part3, 0F, -0.7853982F, 0F);
		lever1 = new ModelRenderer(this, 32, 3);
		lever1.addBox(2F, -3.5F, -0.5F, 3, 1, 1);
		lever1.setRotationPoint(0F, 0F, 0F);
		lever1.setTextureSize(64, 64);
		lever1.mirror = true;
		setRotation(lever1, 0F, 0F, 0F);
		lever2 = new ModelRenderer(this, 42, 3);
		lever2.addBox(5F, -4F, -1F, 2, 2, 2);
		lever2.setRotationPoint(0F, 0F, 0F);
		lever2.setTextureSize(64, 64);
		lever2.mirror = true;
		setRotation(lever2, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 0, 38);
		bottom.addBox(-7F, 6F, -7F, 14, 2, 14);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		bottom2 = new ModelRenderer(this, 57, 38);
		bottom2.addBox(-7F, -2F, 0F, 1, 8, 1);
		bottom2.setRotationPoint(0F, 0F, 0F);
		bottom2.setTextureSize(64, 64);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		bottom3 = new ModelRenderer(this, 57, 50);
		bottom3.addBox(-6F, -2F, 0F, 2, 1, 1);
		bottom3.setRotationPoint(0F, 0F, 0F);
		bottom3.setTextureSize(64, 64);
		bottom3.mirror = true;
		setRotation(bottom3, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		render(f);
	}

	@Override
	public void render(float f) {
		setRotationAngles(f, 0.0F, 0.0F);
		can.render(0.0625F);
		middle.render(0.0625F);
		middle2.render(0.0625F);
		part1.render(0.0625F);
		part2.render(0.0625F);
		part3.render(0.0625F);
		bottom.render(0.0625F);
		bottom2.render(0.0625F);
		bottom3.render(0.0625F);
	}

	public void renderLever(float f, boolean b) {
		setLeverRot(b);
		lever1.render(0.0625F);
		lever2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	private void setLeverRot(boolean b) {
		if (b) {
			lever1.rotateAngleY = 0.7853982F;
			lever2.rotateAngleY = 0.7853982F;
		} else {
			lever1.rotateAngleY = 0.0F;
			lever2.rotateAngleY = 0.0F;
		}
	}

}
