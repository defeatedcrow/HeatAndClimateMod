package defeatedcrow.hac.machine.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelFuelStove extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer chestR;
	ModelRenderer chestL;
	ModelRenderer chestB;
	ModelRenderer chestF;
	ModelRenderer chestT;
	ModelRenderer chestI;
	ModelRenderer chestHandle;
	ModelRenderer stoveBase;
	ModelRenderer stove1;
	ModelRenderer stove2;
	ModelRenderer stove3;
	ModelRenderer stove4;
	ModelRenderer stove5;
	ModelRenderer stove6;
	ModelRenderer stove7;
	ModelRenderer stove8;
	ModelRenderer button;
	ModelRenderer window;
	ModelRenderer gasline;

	public ModelFuelStove() {
		textureWidth = 128;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-8F, 7F, -7F, 16, 1, 15);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(128, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		chestR = new ModelRenderer(this, 0, 20);
		chestR.addBox(-8F, -1F, -7F, 1, 8, 15);
		chestR.setRotationPoint(0F, 16F, 0F);
		chestR.setTextureSize(128, 64);
		chestR.mirror = true;
		setRotation(chestR, 0F, 0F, 0F);
		chestL = new ModelRenderer(this, 0, 20);
		chestL.addBox(7F, -1F, -7F, 1, 8, 15);
		chestL.setRotationPoint(0F, 16F, 0F);
		chestL.setTextureSize(128, 64);
		chestL.mirror = true;
		setRotation(chestL, 0F, 0F, 0F);
		chestB = new ModelRenderer(this, 20, 20);
		chestB.addBox(-7F, -1F, 7F, 14, 8, 1);
		chestB.setRotationPoint(0F, 16F, 0F);
		chestB.setTextureSize(128, 64);
		chestB.mirror = true;
		setRotation(chestB, 0F, 0F, 0F);
		chestF = new ModelRenderer(this, 20, 20);
		chestF.addBox(-14F, -4F, 0F, 14, 8, 1);
		chestF.setRotationPoint(7F, 19F, -6F);
		chestF.setTextureSize(128, 64);
		chestF.mirror = true;
		setRotation(chestF, 0F, 0F, 0F);
		chestT = new ModelRenderer(this, 0, 0);
		chestT.addBox(-8F, -2F, -7F, 16, 1, 15);
		chestT.setRotationPoint(0F, 16F, 0F);
		chestT.setTextureSize(128, 64);
		chestT.mirror = true;
		setRotation(chestT, 0F, 0F, 0F);
		chestI = new ModelRenderer(this, 0, 46);
		chestI.addBox(-7F, 3F, -1F, 14, 1, 8);
		chestI.setRotationPoint(0F, 16F, 0F);
		chestI.setTextureSize(128, 64);
		chestI.mirror = true;
		setRotation(chestI, 0F, 0F, 0F);
		chestHandle = new ModelRenderer(this, 32, 30);
		chestHandle.addBox(-13F, -1F, -1F, 1, 2, 1);
		chestHandle.setRotationPoint(7F, 19F, -6F);
		chestHandle.setTextureSize(128, 64);
		chestHandle.mirror = true;
		setRotation(chestHandle, 0F, 0F, 0F);
		stoveBase = new ModelRenderer(this, 64, 0);
		stoveBase.addBox(-8F, -6F, -6F, 16, 4, 13);
		stoveBase.setRotationPoint(0F, 16F, 0F);
		stoveBase.setTextureSize(128, 64);
		stoveBase.mirror = true;
		setRotation(stoveBase, 0F, 0F, 0F);
		stove1 = new ModelRenderer(this, 64, 20);
		stove1.addBox(-4F, -7F, -3F, 1, 1, 7);
		stove1.setRotationPoint(0F, 16F, 0F);
		stove1.setTextureSize(128, 64);
		stove1.mirror = true;
		setRotation(stove1, 0F, 0F, 0F);
		stove2 = new ModelRenderer(this, 64, 20);
		stove2.addBox(3F, -7F, -3F, 1, 1, 7);
		stove2.setRotationPoint(0F, 16F, 0F);
		stove2.setTextureSize(128, 64);
		stove2.mirror = true;
		setRotation(stove2, 0F, 0F, 0F);
		stove3 = new ModelRenderer(this, 80, 20);
		stove3.addBox(-3F, -7F, 3F, 6, 1, 1);
		stove3.setRotationPoint(0F, 16F, 0F);
		stove3.setTextureSize(128, 64);
		stove3.mirror = true;
		setRotation(stove3, 0F, 0F, 0F);
		stove4 = new ModelRenderer(this, 80, 20);
		stove4.addBox(-3F, -7F, -3F, 6, 1, 1);
		stove4.setRotationPoint(0F, 16F, 0F);
		stove4.setTextureSize(128, 64);
		stove4.mirror = true;
		setRotation(stove4, 0F, 0F, 0F);
		stove5 = new ModelRenderer(this, 84, 24);
		stove5.addBox(-0.5F, -8F, 2F, 1, 1, 3);
		stove5.setRotationPoint(0F, 16F, 0F);
		stove5.setTextureSize(128, 64);
		stove5.mirror = true;
		setRotation(stove5, 0F, 0F, 0F);
		stove6 = new ModelRenderer(this, 84, 24);
		stove6.addBox(-0.5F, -8F, -4F, 1, 1, 3);
		stove6.setRotationPoint(0F, 16F, 0F);
		stove6.setTextureSize(128, 64);
		stove6.mirror = true;
		setRotation(stove6, 0F, 0F, 0F);
		stove7 = new ModelRenderer(this, 84, 30);
		stove7.addBox(-5F, -8F, 0F, 3, 1, 1);
		stove7.setRotationPoint(0F, 16F, 0F);
		stove7.setTextureSize(128, 64);
		stove7.mirror = true;
		setRotation(stove7, 0F, 0F, 0F);
		stove8 = new ModelRenderer(this, 84, 30);
		stove8.addBox(2F, -8F, 0F, 3, 1, 1);
		stove8.setRotationPoint(0F, 16F, 0F);
		stove8.setTextureSize(128, 64);
		stove8.mirror = true;
		setRotation(stove8, 0F, 0F, 0F);
		button = new ModelRenderer(this, 64, 30);
		button.addBox(-6F, -5F, -7F, 2, 2, 1);
		button.setRotationPoint(0F, 16F, 0F);
		button.setTextureSize(128, 64);
		button.mirror = true;
		setRotation(button, 0F, 0F, 0F);
		window = new ModelRenderer(this, 64, 36);
		window.addBox(0F, -5F, -7F, 6, 2, 1);
		window.setRotationPoint(0F, 16F, 0F);
		window.setTextureSize(128, 64);
		window.mirror = true;
		setRotation(window, 0F, 0F, 0F);
		gasline = new ModelRenderer(this, 64, 42);
		gasline.addBox(-6F, -5F, 7F, 1, 3, 1);
		gasline.setRotationPoint(0F, 16F, 0F);
		gasline.setTextureSize(128, 64);
		gasline.mirror = true;
		setRotation(gasline, 0F, 0F, 0F);
	}

	public void render(float f) {
		setRotationAngles(f);
		base.render(0.0625F);
		chestR.render(0.0625F);
		chestL.render(0.0625F);
		chestB.render(0.0625F);
		chestF.render(0.0625F);
		chestT.render(0.0625F);
		chestI.render(0.0625F);
		chestHandle.render(0.0625F);
		stoveBase.render(0.0625F);
		stove1.render(0.0625F);
		stove2.render(0.0625F);
		stove3.render(0.0625F);
		stove4.render(0.0625F);
		stove5.render(0.0625F);
		stove6.render(0.0625F);
		stove7.render(0.0625F);
		stove8.render(0.0625F);
		button.render(0.0625F);
		window.render(0.0625F);
		gasline.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f) {
	}

}
