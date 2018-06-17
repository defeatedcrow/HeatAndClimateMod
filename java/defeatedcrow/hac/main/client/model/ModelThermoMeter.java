package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelThermoMeter extends DCTileModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer glass;
	ModelRenderer water;
	ModelRenderer cap;
	ModelRenderer cry;
	ModelRenderer fro;
	ModelRenderer cld;
	ModelRenderer coo;
	ModelRenderer nor;
	ModelRenderer wam;
	ModelRenderer hot;
	ModelRenderer boi;
	ModelRenderer ovn;
	ModelRenderer kln;
	ModelRenderer smt;
	ModelRenderer uht;

	public ModelThermoMeter() {
		textureWidth = 128;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-7F, 7F, -7F, 14, 1, 14);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(128, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		glass = new ModelRenderer(this, 0, 32);
		glass.addBox(-6F, -13F, -6F, 12, 20, 12);
		glass.setRotationPoint(0F, 16F, 0F);
		glass.setTextureSize(128, 64);
		glass.mirror = true;
		setRotation(glass, 0F, 0F, 0F);
		water = new ModelRenderer(this, 50, 32);
		water.addBox(-5.5F, -11.5F, -5.5F, 11, 18, 11);
		water.setRotationPoint(0F, 16F, 0F);
		water.setTextureSize(128, 64);
		water.mirror = true;
		setRotation(water, 0F, 0F, 0F);
		cap = new ModelRenderer(this, 0, 18);
		cap.addBox(-4F, -14F, -4F, 8, 1, 8);
		cap.setRotationPoint(0F, 16F, 0F);
		cap.setTextureSize(128, 64);
		cap.mirror = true;
		setRotation(cap, 0F, 0F, 0F);
		cry = new ModelRenderer(this, 60, 0);
		cry.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		cry.setRotationPoint(-3F, 8F, -3F);
		cry.setTextureSize(128, 64);
		cry.mirror = true;
		setRotation(cry, 0F, 0.1745329F, 0F);
		fro = new ModelRenderer(this, 74, 0);
		fro.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		fro.setRotationPoint(0F, 8F, -2F);
		fro.setTextureSize(128, 64);
		fro.mirror = true;
		setRotation(fro, 0F, -0.08725F, 0F);
		cld = new ModelRenderer(this, 88, 0);
		cld.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		cld.setRotationPoint(0F, 8F, 2F);
		cld.setTextureSize(128, 64);
		cld.mirror = true;
		setRotation(cld, 0F, -0.0523599F, 0F);
		coo = new ModelRenderer(this, 102, 0);
		coo.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		coo.setRotationPoint(3.5F, 8F, -3F);
		coo.setTextureSize(128, 64);
		coo.mirror = true;
		setRotation(coo, 0F, -0.0523599F, 0F);
		nor = new ModelRenderer(this, 116, 0);
		nor.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		nor.setRotationPoint(-3F, 8F, 3F);
		nor.setTextureSize(128, 64);
		nor.mirror = true;
		setRotation(nor, 0F, -0.12215F, 0F);
		wam = new ModelRenderer(this, 60, 7);
		wam.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		wam.setRotationPoint(3F, 8F, 3F);
		wam.setTextureSize(128, 64);
		wam.mirror = true;
		setRotation(wam, 0F, -0.26175F, 0F);
		hot = new ModelRenderer(this, 74, 7);
		hot.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		hot.setRotationPoint(3F, 6F, 3F);
		hot.setTextureSize(128, 64);
		hot.mirror = true;
		setRotation(hot, 0F, 0.0523599F, 0F);
		boi = new ModelRenderer(this, 88, 7);
		boi.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		boi.setRotationPoint(-1.5F, 6F, -3F);
		boi.setTextureSize(128, 64);
		boi.mirror = true;
		setRotation(boi, 0F, 0.1745F, 0F);
		ovn = new ModelRenderer(this, 102, 7);
		ovn.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		ovn.setRotationPoint(0F, 6F, 0F);
		ovn.setTextureSize(128, 64);
		ovn.mirror = true;
		setRotation(ovn, 0F, 0.2268928F, 0F);
		kln = new ModelRenderer(this, 116, 7);
		kln.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		kln.setRotationPoint(-0.5F, 6F, 3F);
		kln.setTextureSize(128, 64);
		kln.mirror = true;
		setRotation(kln, 0F, -0.52350F, 0F);
		smt = new ModelRenderer(this, 60, 14);
		smt.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		smt.setRotationPoint(-3F, 6F, 0F);
		smt.setTextureSize(128, 64);
		smt.mirror = true;
		setRotation(smt, 0F, -0.10470F, 0F);
		uht = new ModelRenderer(this, 74, 14);
		uht.addBox(-1.5F, -1F, -1.5F, 3, 2, 3);
		uht.setRotationPoint(2.5F, 6F, -2F);
		uht.setTextureSize(128, 64);
		uht.mirror = true;
		setRotation(uht, 0F, -0.24430F, 0F);
	}

	public void renderGlass(float f) {
		glass.render(0.0625F);
	}

	public void renderWater(float f) {
		water.render(0.0625F);
	}

	public void renderBase(float f, float[] points) {
		setRotationAngles(f);
		setFloatPoint(points);
		base.render(0.0625F);
		cap.render(0.0625F);
		cry.render(0.0625F);
		fro.render(0.0625F);
		cld.render(0.0625F);
		coo.render(0.0625F);
		nor.render(0.0625F);
		wam.render(0.0625F);
		hot.render(0.0625F);
		boi.render(0.0625F);
		ovn.render(0.0625F);
		kln.render(0.0625F);
		smt.render(0.0625F);
		uht.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	private void setFloatPoint(float[] points) {
		if (points != null) {
			cry.rotationPointY = points[0] + 8.0F;
			fro.rotationPointY = points[1] + 8.0F;
			cld.rotationPointY = points[2] + 8.0F;
			coo.rotationPointY = points[3] + 8.0F;
			nor.rotationPointY = points[4] + 8.0F;
			wam.rotationPointY = points[5] + 8.0F;
			hot.rotationPointY = points[6] + 6.0F;
			boi.rotationPointY = points[7] + 6.0F;
			ovn.rotationPointY = points[8] + 6.0F;
			kln.rotationPointY = points[9] + 6.0F;
			smt.rotationPointY = points[10] + 6.0F;
			uht.rotationPointY = points[11] + 6.0F;
		}
	}

}
