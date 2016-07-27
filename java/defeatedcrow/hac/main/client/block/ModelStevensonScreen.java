package defeatedcrow.hac.main.client.block;

import net.minecraft.client.model.ModelRenderer;
import defeatedcrow.hac.core.client.base.DCTileModelBase;

public class ModelStevensonScreen extends DCTileModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer p1;
	ModelRenderer p2;
	ModelRenderer p3;
	ModelRenderer p4;
	ModelRenderer front;
	ModelRenderer back;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer top;
	ModelRenderer top2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer front2;

	public ModelStevensonScreen() {
		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-8F, 7F, -8F, 16, 1, 16);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		p1 = new ModelRenderer(this, 55, 18);
		p1.addBox(-8F, -9F, -8F, 2, 16, 2);
		p1.setRotationPoint(0F, 16F, 0F);
		p1.setTextureSize(64, 64);
		p1.mirror = true;
		setRotation(p1, 0F, 0F, 0F);
		p2 = new ModelRenderer(this, 55, 18);
		p2.addBox(6F, -9F, -8F, 2, 16, 2);
		p2.setRotationPoint(0F, 16F, 0F);
		p2.setTextureSize(64, 64);
		p2.mirror = true;
		setRotation(p2, 0F, 0F, 0F);
		p3 = new ModelRenderer(this, 55, 18);
		p3.addBox(-8F, -6F, 6F, 2, 13, 2);
		p3.setRotationPoint(0F, 16F, 0F);
		p3.setTextureSize(64, 64);
		p3.mirror = true;
		setRotation(p3, 0F, 0F, 0F);
		p4 = new ModelRenderer(this, 55, 18);
		p4.addBox(6F, -6F, 6F, 2, 13, 2);
		p4.setRotationPoint(0F, 16F, 0F);
		p4.setTextureSize(64, 64);
		p4.mirror = true;
		setRotation(p4, 0F, 0F, 0F);
		front = new ModelRenderer(this, 0, 18);
		front.addBox(-6F, -5F, -7F, 12, 12, 1);
		front.setRotationPoint(0F, 16F, 0F);
		front.setTextureSize(64, 64);
		front.mirror = true;
		setRotation(front, 0F, 0F, 0F);
		back = new ModelRenderer(this, 0, 18);
		back.addBox(-6F, -6F, 6F, 12, 13, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 27, 18);
		side1.addBox(-7F, -5F, -6F, 1, 12, 12);
		side1.setRotationPoint(0F, 16F, 0F);
		side1.setTextureSize(64, 64);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 27, 18);
		side2.addBox(6F, -5F, -6F, 1, 12, 12);
		side2.setRotationPoint(0F, 16F, 0F);
		side2.setTextureSize(64, 64);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		top = new ModelRenderer(this, 6, 6);
		top.addBox(-8F, -8F, -2F, 16, 1, 10);
		top.setRotationPoint(0F, 16F, 0F);
		top.setTextureSize(64, 64);
		top.mirror = true;
		setRotation(top, -0.2268928F, 0F, 0F);
		top2 = new ModelRenderer(this, 6, 6);
		top2.addBox(-8F, -8F, -12F, 16, 1, 10);
		top2.setRotationPoint(0F, 16F, 0F);
		top2.setTextureSize(64, 64);
		top2.mirror = true;
		setRotation(top2, -0.2268928F, 0F, 0F);
		side3 = new ModelRenderer(this, 0, 34);
		side3.addBox(5.8F, -7F, -8F, 1, 4, 13);
		side3.setRotationPoint(0F, 16F, 0F);
		side3.setTextureSize(64, 64);
		side3.mirror = true;
		setRotation(side3, -0.2268928F, 0F, 0F);
		side4 = new ModelRenderer(this, 0, 34);
		side4.addBox(-6.8F, -7F, -8F, 1, 4, 13);
		side4.setRotationPoint(0F, 16F, 0F);
		side4.setTextureSize(64, 64);
		side4.mirror = true;
		setRotation(side4, -0.2268928F, 0F, 0F);
		front2 = new ModelRenderer(this, 0, 52);
		front2.addBox(-6F, -9F, -7F, 12, 4, 1);
		front2.setRotationPoint(0F, 16F, 0F);
		front2.setTextureSize(64, 64);
		front2.mirror = true;
		setRotation(front2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		setRotationAngles(f);
		bottom.render(0.0625F);
		p1.render(0.0625F);
		p2.render(0.0625F);
		p3.render(0.0625F);
		p4.render(0.0625F);
		front.render(0.0625F);
		back.render(0.0625F);
		side1.render(0.0625F);
		side2.render(0.0625F);
		top.render(0.0625F);
		top2.render(0.0625F);
		side3.render(0.0625F);
		side4.render(0.0625F);
		front2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
