package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;

@SideOnly(Side.CLIENT)
public class ModelSteelPot extends DCTileModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer front;
	ModelRenderer back;
	ModelRenderer right;
	ModelRenderer left;
	ModelRenderer handle1;
	ModelRenderer handle2;
	ModelRenderer handle3;
	ModelRenderer handle4;
	ModelRenderer handle5;
	ModelRenderer handle6;
	ModelRenderer cap1;
	ModelRenderer cap2;

	public ModelSteelPot() {
		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-6F, 7F, -6F, 12, 1, 12);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		front = new ModelRenderer(this, 0, 14);
		front.addBox(-7F, -6F, -7F, 14, 14, 1);
		front.setRotationPoint(0F, 16F, 0F);
		front.setTextureSize(64, 32);
		front.mirror = true;
		setRotation(front, 0F, 0F, 0F);
		back = new ModelRenderer(this, 0, 14);
		back.addBox(-7F, -6F, 6F, 14, 14, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 32);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		right = new ModelRenderer(this, 32, 14);
		right.addBox(-7F, -6F, -6F, 1, 14, 12);
		right.setRotationPoint(0F, 16F, 0F);
		right.setTextureSize(64, 32);
		right.mirror = true;
		setRotation(right, 0F, 0F, 0F);
		left = new ModelRenderer(this, 32, 14);
		left.addBox(6F, -6F, -6F, 1, 14, 12);
		left.setRotationPoint(0F, 16F, 0F);
		left.setTextureSize(64, 32);
		left.mirror = true;
		setRotation(left, 0F, 0F, 0F);
		handle1 = new ModelRenderer(this, 0, 30);
		handle1.addBox(-3F, -5F, -9F, 6, 1, 1);
		handle1.setRotationPoint(0F, 16F, 0F);
		handle1.setTextureSize(64, 32);
		handle1.mirror = true;
		setRotation(handle1, 0F, 0F, 0F);
		handle2 = new ModelRenderer(this, 0, 33);
		handle2.addBox(-3F, -5F, -8F, 1, 1, 1);
		handle2.setRotationPoint(0F, 16F, 0F);
		handle2.setTextureSize(64, 32);
		handle2.mirror = true;
		setRotation(handle2, 0F, 0F, 0F);
		handle3 = new ModelRenderer(this, 0, 33);
		handle3.addBox(2F, -5F, -8F, 1, 1, 1);
		handle3.setRotationPoint(0F, 16F, 0F);
		handle3.setTextureSize(64, 32);
		handle3.mirror = true;
		setRotation(handle3, 0F, 0F, 0F);
		handle4 = new ModelRenderer(this, 0, 30);
		handle4.addBox(-3F, -5F, 8F, 6, 1, 1);
		handle4.setRotationPoint(0F, 16F, 0F);
		handle4.setTextureSize(64, 32);
		handle4.mirror = true;
		setRotation(handle4, 0F, 0F, 0F);
		handle5 = new ModelRenderer(this, 0, 33);
		handle5.addBox(-3F, -5F, 7F, 1, 1, 1);
		handle5.setRotationPoint(0F, 16F, 0F);
		handle5.setTextureSize(64, 32);
		handle5.mirror = true;
		setRotation(handle5, 0F, 0F, 0F);
		handle6 = new ModelRenderer(this, 0, 33);
		handle6.addBox(2F, -5F, 7F, 1, 1, 1);
		handle6.setRotationPoint(0F, 16F, 0F);
		handle6.setTextureSize(64, 32);
		handle6.mirror = true;
		setRotation(handle6, 0F, 0F, 0F);
		cap1 = new ModelRenderer(this, 0, 42);
		cap1.addBox(-7F, -7.1F, -7F, 14, 1, 14);
		cap1.setRotationPoint(0F, 16F, 0F);
		cap1.setTextureSize(64, 64);
		cap1.mirror = true;
		setRotation(cap1, 0F, 0F, 0F);
		cap2 = new ModelRenderer(this, 0, 38);
		cap2.addBox(-1F, -8F, -1F, 2, 1, 2);
		cap2.setRotationPoint(0F, 16F, 0F);
		cap2.setTextureSize(64, 64);
		cap2.mirror = true;
		setRotation(cap2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bottom.render(0.0625F);
		front.render(0.0625F);
		back.render(0.0625F);
		right.render(0.0625F);
		left.render(0.0625F);
		handle1.render(0.0625F);
		handle2.render(0.0625F);
		handle3.render(0.0625F);
		handle4.render(0.0625F);
		handle5.render(0.0625F);
		handle6.render(0.0625F);
	}

	public void renderCap(float f) {
		cap1.render(0.0625F);
		cap2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
