package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBellow extends DCTileModelBase {
	// fields
	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer shaft;
	ModelRenderer bellow1;
	ModelRenderer bellow2;
	ModelRenderer bellow3;

	public ModelBellow() {
		textureWidth = 64;
		textureHeight = 64;

		base1 = new ModelRenderer(this, 0, 0);
		base1.addBox(-8F, 7F, -8F, 16, 1, 16);
		base1.setRotationPoint(0F, 0F, 0F);
		base1.setTextureSize(64, 32);
		base1.mirror = true;
		setRotation(base1, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 0, 0);
		base2.addBox(-8F, -7F, -8F, 16, 1, 16);
		base2.setRotationPoint(0F, 0F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 0);
		side1.addBox(-8F, -6F, -8F, 1, 13, 1);
		side1.setRotationPoint(0F, 0F, 0F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 0);
		side2.addBox(-8F, -6F, 7F, 1, 13, 1);
		side2.setRotationPoint(0F, 0F, 0F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		side3 = new ModelRenderer(this, 0, 0);
		side3.addBox(7F, -6F, -8F, 1, 13, 1);
		side3.setRotationPoint(0F, 0F, 0F);
		side3.setTextureSize(64, 32);
		side3.mirror = true;
		setRotation(side3, 0F, 0F, 0F);
		side4 = new ModelRenderer(this, 0, 0);
		side4.addBox(7F, -6F, 7F, 1, 13, 1);
		side4.setRotationPoint(0F, 0F, 0F);
		side4.setTextureSize(64, 32);
		side4.mirror = true;
		setRotation(side4, 0F, 0F, 0F);
		shaft = new ModelRenderer(this, 0, 34);
		shaft.addBox(-3F, -8F, -3F, 6, 8, 6);
		shaft.setRotationPoint(0F, 0F, 0F);
		shaft.setTextureSize(64, 32);
		shaft.mirror = true;
		setRotation(shaft, 0F, 0F, 0F);
		bellow1 = new ModelRenderer(this, 0, 17);
		bellow1.addBox(-7F, -5F, -7F, 14, 2, 14);
		bellow1.setRotationPoint(0F, 0F, 0F);
		bellow1.setTextureSize(64, 32);
		bellow1.mirror = true;
		setRotation(bellow1, 0F, 0F, 0F);
		bellow2 = new ModelRenderer(this, 28, 34);
		bellow2.addBox(-4.5F, 2F, -4.5F, 9, 5, 9);
		bellow2.setRotationPoint(0F, 0F, 0F);
		bellow2.setTextureSize(64, 32);
		bellow2.mirror = true;
		setRotation(bellow2, 0F, 0F, 0F);
		bellow3 = new ModelRenderer(this, 24, 48);
		bellow3.addBox(-5F, -3F, -5F, 10, 5, 10);
		bellow3.setRotationPoint(0F, 0F, 0F);
		bellow3.setTextureSize(64, 32);
		bellow3.mirror = true;
		setRotation(bellow3, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		base1.render(0.0625F);
		base2.render(0.0625F);
		side1.render(0.0625F);
		side2.render(0.0625F);
		side3.render(0.0625F);
		side4.render(0.0625F);
		shaft.render(0.0625F);
		bellow1.render(0.0625F);
		bellow2.render(0.0625F);
		bellow3.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		float offset = f * 0.5F;
		if (offset < 0.0F) {
			offset = 0.0f;
		}
		if (offset > 5.0F) {
			offset = 5.0F;
		}

		bellow1.rotationPointY = offset;
		bellow3.rotationPointY = offset * 0.6F;
	}

}