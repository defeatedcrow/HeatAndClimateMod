package defeatedcrow.hac.main.client.block;

import net.minecraft.client.model.ModelRenderer;
import defeatedcrow.hac.core.client.base.DCModelBase;

public class ModelNormalChamber extends DCModelBase {
	// fields
	ModelRenderer inner;
	ModelRenderer under;
	ModelRenderer top;
	ModelRenderer sideL;
	ModelRenderer sideR;
	ModelRenderer sideL2;
	ModelRenderer sideR2;

	public ModelNormalChamber() {
		textureWidth = 128;
		textureHeight = 64;

		inner = new ModelRenderer(this, 2, 20);
		inner.addBox(-8F, -3F, -6F, 16, 10, 14);
		inner.setRotationPoint(0F, 16F, 0F);
		inner.setTextureSize(128, 64);
		inner.mirror = true;
		setRotation(inner, 0F, 0F, 0F);
		under = new ModelRenderer(this, 0, 0);
		under.addBox(-8F, 7F, -8F, 16, 1, 16);
		under.setRotationPoint(0F, 16F, 0F);
		under.setTextureSize(128, 64);
		under.mirror = true;
		setRotation(under, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-8F, -7F, -8F, 16, 4, 16);
		top.setRotationPoint(0F, 16F, 0F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 0, 0);
		sideL.addBox(-8F, -5F, -8F, 2, 12, 2);
		sideL.setRotationPoint(0F, 16F, 0F);
		sideL.setTextureSize(128, 64);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 48, 0);
		sideR.addBox(6F, -3F, -8F, 2, 10, 2);
		sideR.setRotationPoint(0F, 16F, 0F);
		sideR.setTextureSize(128, 64);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		sideL2 = new ModelRenderer(this, 8, 0);
		sideL2.addBox(-6F, -3F, -8F, 1, 2, 2);
		sideL2.setRotationPoint(0F, 16F, 0F);
		sideL2.setTextureSize(128, 64);
		sideL2.mirror = true;
		setRotation(sideL2, 0F, 0F, 0F);
		sideR2 = new ModelRenderer(this, 8, 4);
		sideR2.addBox(5F, -3F, -8F, 1, 2, 2);
		sideR2.setRotationPoint(0F, 16F, 0F);
		sideR2.setTextureSize(128, 64);
		sideR2.mirror = true;
		setRotation(sideR2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(0.0625F);
		inner.render(0.0625F);
		under.render(0.0625F);
		top.render(0.0625F);
		sideL.render(0.0625F);
		sideR.render(0.0625F);
		sideL2.render(0.0625F);
		sideR2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {
	}

}
