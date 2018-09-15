package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFan extends DCTileModelBase {
	// fields
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer side5;
	ModelRenderer side6;
	ModelRenderer side7;
	ModelRenderer side8;
	ModelRenderer box;
	ModelRenderer pad1;
	ModelRenderer shaft;
	ModelRenderer wing1;
	ModelRenderer wing2;
	ModelRenderer wing3;
	ModelRenderer net1;
	ModelRenderer net2;

	public ModelFan() {
		textureWidth = 128;
		textureHeight = 64;

		side1 = new ModelRenderer(this, 0, 0);
		side1.addBox(-6F, -8F, -8F, 12, 16, 1);
		side1.setRotationPoint(0F, 0F, 0F);
		side1.setTextureSize(128, 64);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 0);
		side2.addBox(-6F, -8F, 7F, 12, 16, 1);
		side2.setRotationPoint(0F, 0F, 0F);
		side2.setTextureSize(128, 64);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		side3 = new ModelRenderer(this, 28, 0);
		side3.addBox(-8F, -8F, -6F, 1, 16, 12);
		side3.setRotationPoint(0F, 0F, 0F);
		side3.setTextureSize(128, 64);
		side3.mirror = true;
		setRotation(side3, 0F, 0F, 0F);
		side4 = new ModelRenderer(this, 28, 0);
		side4.addBox(7F, -8F, -6F, 1, 16, 12);
		side4.setRotationPoint(0F, 0F, 0F);
		side4.setTextureSize(128, 64);
		side4.mirror = true;
		setRotation(side4, 0F, 0F, 0F);
		side5 = new ModelRenderer(this, 56, 0);
		side5.addBox(-7F, -7F, -7F, 1, 14, 1);
		side5.setRotationPoint(0F, 0F, 0F);
		side5.setTextureSize(128, 64);
		side5.mirror = true;
		setRotation(side5, 0F, 0F, 0F);
		side6 = new ModelRenderer(this, 56, 0);
		side6.addBox(6F, -7F, -7F, 1, 14, 1);
		side6.setRotationPoint(0F, 0F, 0F);
		side6.setTextureSize(128, 64);
		side6.mirror = true;
		setRotation(side6, 0F, 0F, 0F);
		side7 = new ModelRenderer(this, 56, 0);
		side7.addBox(-7F, -7F, 6F, 1, 14, 1);
		side7.setRotationPoint(0F, 0F, 0F);
		side7.setTextureSize(128, 64);
		side7.mirror = true;
		setRotation(side7, 0F, 0F, 0F);
		side8 = new ModelRenderer(this, 56, 0);
		side8.addBox(6F, -7F, 6F, 1, 14, 1);
		side8.setRotationPoint(0F, 0F, 0F);
		side8.setTextureSize(128, 64);
		side8.mirror = true;
		setRotation(side8, 0F, 0F, 0F);
		box = new ModelRenderer(this, 0, 32);
		box.addBox(-5F, 0F, -5F, 10, 6, 10);
		box.setRotationPoint(0F, 0F, 0F);
		box.setTextureSize(128, 64);
		box.mirror = true;
		setRotation(box, 0F, 0F, 0F);
		pad1 = new ModelRenderer(this, 0, 50);
		pad1.addBox(-3F, -3F, 5F, 6, 6, 1);
		pad1.setRotationPoint(0F, 0F, 0F);
		pad1.setTextureSize(128, 64);
		pad1.mirror = true;
		setRotation(pad1, 0F, 0F, 0F);
		shaft = new ModelRenderer(this, 18, 50);
		shaft.addBox(-1F, -5F, -1F, 2, 5, 2);
		shaft.setRotationPoint(0F, 0F, 0F);
		shaft.setTextureSize(128, 64);
		shaft.mirror = true;
		setRotation(shaft, 0F, 0F, 0F);
		wing1 = new ModelRenderer(this, 30, 50);
		wing1.addBox(0F, 0F, 0F, 6, 1, 4);
		wing1.setRotationPoint(0F, -4F, 0F);
		wing1.setTextureSize(128, 64);
		wing1.mirror = true;
		setRotation(wing1, 0.5235988F, 0F, 0F);
		wing2 = new ModelRenderer(this, 30, 50);
		wing2.addBox(0F, 0F, 0F, 6, 1, 4);
		wing2.setRotationPoint(0F, -4F, 0F);
		wing2.setTextureSize(128, 64);
		wing2.mirror = true;
		setRotation(wing2, 0.5235988F, 2.094395F, 0F);
		wing3 = new ModelRenderer(this, 30, 50);
		wing3.addBox(0F, 0F, 0F, 6, 1, 4);
		wing3.setRotationPoint(0F, -4F, 0F);
		wing3.setTextureSize(128, 64);
		wing3.mirror = true;
		setRotation(wing3, 0.5235988F, -2.094395F, 0F);
		net1 = new ModelRenderer(this, 64, 0);
		net1.addBox(-7F, -8F, -7F, 14, 1, 14);
		net1.setRotationPoint(0F, 0F, 0F);
		net1.setTextureSize(128, 64);
		net1.mirror = true;
		setRotation(net1, 0F, 0F, 0F);
		net2 = new ModelRenderer(this, 64, 0);
		net2.addBox(-7F, 7F, -7F, 14, 1, 14);
		net2.setRotationPoint(0F, 0F, 0F);
		net2.setTextureSize(128, 64);
		net2.mirror = true;
		setRotation(net2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		side1.render(0.0625F);
		side2.render(0.0625F);
		side3.render(0.0625F);
		side4.render(0.0625F);
		side5.render(0.0625F);
		side6.render(0.0625F);
		side7.render(0.0625F);
		side8.render(0.0625F);
		box.render(0.0625F);
		pad1.render(0.0625F);
		shaft.render(0.0625F);
		wing1.render(0.0625F);
		wing2.render(0.0625F);
		wing3.render(0.0625F);
		net1.render(0.0625F);
		net2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		float rot = f * 12.0F;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		wing1.rotateAngleY = f2;
		wing2.rotateAngleY = f2 + 2.094395F;
		wing3.rotateAngleY = f2 - 2.094395F;
	}

}
