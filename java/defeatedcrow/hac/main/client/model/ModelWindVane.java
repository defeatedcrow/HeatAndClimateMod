package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWindVane extends DCTileModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer shaft;
	ModelRenderer bar;
	ModelRenderer bar2;
	ModelRenderer clow;
	ModelRenderer cup1;
	ModelRenderer cup2;
	ModelRenderer cup3;
	ModelRenderer cup4;

	public ModelWindVane() {
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-2F, 7F, -2F, 4, 1, 4);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		shaft = new ModelRenderer(this, 18, 0);
		shaft.addBox(-0.5F, 0F, -0.5F, 1, 7, 1);
		shaft.setRotationPoint(0F, 16F, 0F);
		shaft.setTextureSize(64, 64);
		shaft.mirror = true;
		setRotation(shaft, 0F, 0F, 0F);
		bar = new ModelRenderer(this, 0, 12);
		bar.addBox(-7F, 2F, -0.5F, 14, 1, 1);
		bar.setRotationPoint(0F, 16F, 0F);
		bar.setTextureSize(64, 64);
		bar.mirror = true;
		setRotation(bar, 0F, 1.570796F, 0F);
		bar2 = new ModelRenderer(this, 0, 12);
		bar2.addBox(-7F, 2F, -0.5F, 14, 1, 1);
		bar2.setRotationPoint(0F, 16F, 0F);
		bar2.setTextureSize(64, 64);
		bar2.mirror = true;
		setRotation(bar2, 0F, 0F, 0F);
		clow = new ModelRenderer(this, 0, 32);
		clow.addBox(-6F, -12F, 0F, 12, 12, 0);
		clow.setRotationPoint(0F, 16F, 0F);
		clow.setTextureSize(64, 64);
		clow.mirror = true;
		setRotation(clow, 0F, 0F, 0F);
		cup1 = new ModelRenderer(this, 0, 6);
		cup1.addBox(-8F, 1.5F, 0F, 2, 2, 2);
		cup1.setRotationPoint(0F, 16F, 0F);
		cup1.setTextureSize(64, 64);
		cup1.mirror = true;
		setRotation(cup1, 0F, 0F, 0F);
		cup2 = new ModelRenderer(this, 0, 6);
		cup2.addBox(6F, 1.5F, -2F, 2, 2, 2);
		cup2.setRotationPoint(0F, 16F, 0F);
		cup2.setTextureSize(64, 64);
		cup2.mirror = true;
		setRotation(cup2, 0F, 0F, 0F);
		cup3 = new ModelRenderer(this, 0, 6);
		cup3.addBox(-2F, 1.5F, -8F, 2, 2, 2);
		cup3.setRotationPoint(0F, 16F, 0F);
		cup3.setTextureSize(64, 64);
		cup3.mirror = true;
		setRotation(cup3, 0F, 0F, 0F);
		cup4 = new ModelRenderer(this, 0, 6);
		cup4.addBox(0F, 1.5F, 6F, 2, 2, 2);
		cup4.setRotationPoint(0F, 16F, 0F);
		cup4.setTextureSize(64, 64);
		cup4.mirror = true;
		setRotation(cup4, 0F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		base.render(0.0625F);
		shaft.render(0.0625F);
		clow.render(0.0625F);
	}

	public void renderScrew(float f) {
		setRotationScrew(f);
		bar.render(0.0625F);
		bar2.render(0.0625F);
		cup1.render(0.0625F);
		cup2.render(0.0625F);
		cup3.render(0.0625F);
		cup4.render(0.0625F);
	}

	private void setRotationScrew(float f) {
		float rot = f;
		float f2 = (float) (rot * Math.PI / 180F);

		bar.rotateAngleY = f2;
		bar2.rotateAngleY = f2 + (float) Math.PI * 0.5F;
		cup1.rotateAngleY = f2;
		cup2.rotateAngleY = f2;
		cup3.rotateAngleY = f2;
		cup4.rotateAngleY = f2;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
