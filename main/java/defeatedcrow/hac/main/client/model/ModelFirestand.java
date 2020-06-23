package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelFirestand extends DCTileModelBase {
	// fields
	public ModelRenderer mesh2;
	public ModelRenderer mesh1;
	public ModelRenderer frame1;
	public ModelRenderer frame2;
	public ModelRenderer frame3;
	public ModelRenderer frame4;
	public ModelRenderer frame5;
	public ModelRenderer frame6;
	public ModelRenderer frame7;
	public ModelRenderer wood1;
	public ModelRenderer wood2;
	public ModelRenderer wood3;
	public ModelRenderer wood4;
	public ModelRenderer wood5;
	public ModelRenderer wood6;
	public ModelRenderer wood7;

	public ModelFirestand() {
		textureWidth = 64;
		textureHeight = 64;

		mesh2 = new ModelRenderer(this, 0, 0);
		mesh2.addBox(-7F, 0F, 0F, 14, 0, 7, 0F);
		mesh2.setRotationPoint(0F, 14F, 0F);
		mesh2.rotateAngleX = 0.1396263F;
		mesh2.rotateAngleY = 3.141593F;
		mesh2.rotateAngleZ = 0F;
		mesh2.mirror = false;
		mesh1 = new ModelRenderer(this, 0, 0);
		mesh1.addBox(-7F, 0F, 0F, 14, 0, 7, 0F);
		mesh1.setRotationPoint(0F, 14F, 0F);
		mesh1.rotateAngleX = 0.1396263F;
		mesh1.rotateAngleY = 0F;
		mesh1.rotateAngleZ = 0F;
		mesh1.mirror = false;
		frame1 = new ModelRenderer(this, 0, 8);
		frame1.addBox(-8F, 0F, 7F, 16, 1, 1, 0F);
		frame1.setRotationPoint(0F, 13.5F, 0F);
		frame1.rotateAngleX = 0.1396263F;
		frame1.rotateAngleY = 0F;
		frame1.rotateAngleZ = 0F;
		frame1.mirror = false;
		frame2 = new ModelRenderer(this, 0, 8);
		frame2.addBox(-8F, 0F, 7F, 16, 1, 1, 0F);
		frame2.setRotationPoint(0F, 13.5F, 0F);
		frame2.rotateAngleX = 0.1396263F;
		frame2.rotateAngleY = 3.141593F;
		frame2.rotateAngleZ = 0F;
		frame2.mirror = false;
		frame3 = new ModelRenderer(this, 0, 11);
		frame3.addBox(-0.5F, -12F, -0.5F, 1, 22, 1, 0F);
		frame3.setRotationPoint(0F, 19F, 0F);
		frame3.rotateAngleX = 1.047198F;
		frame3.rotateAngleY = 0.7853982F;
		frame3.rotateAngleZ = 0F;
		frame3.mirror = false;
		frame4 = new ModelRenderer(this, 0, 11);
		frame4.addBox(-0.5F, -12F, -0.5F, 1, 22, 1, 0F);
		frame4.setRotationPoint(0F, 19F, 0F);
		frame4.rotateAngleX = 1.047198F;
		frame4.rotateAngleY = 2.356194F;
		frame4.rotateAngleZ = 0F;
		frame4.mirror = false;
		frame5 = new ModelRenderer(this, 0, 11);
		frame5.addBox(-0.5F, -12F, -0.5F, 1, 22, 1, 0F);
		frame5.setRotationPoint(0F, 19F, 0F);
		frame5.rotateAngleX = 1.047198F;
		frame5.rotateAngleY = -0.7853982F;
		frame5.rotateAngleZ = 0F;
		frame5.mirror = false;
		frame6 = new ModelRenderer(this, 0, 11);
		frame6.addBox(-0.5F, -12F, -0.5F, 1, 22, 1, 0F);
		frame6.setRotationPoint(0F, 19F, 0F);
		frame6.rotateAngleX = 1.047198F;
		frame6.rotateAngleY = -2.356194F;
		frame6.rotateAngleZ = 0F;
		frame6.mirror = false;
		frame7 = new ModelRenderer(this, 5, 11);
		frame7.addBox(-2F, 2F, -2F, 4, 1, 4, 0F);
		frame7.setRotationPoint(0F, 16F, 0F);
		frame7.rotateAngleX = 0F;
		frame7.rotateAngleY = 0F;
		frame7.rotateAngleZ = 0F;
		frame7.mirror = false;
		wood1 = new ModelRenderer(this, 22, 11);
		wood1.addBox(-5F, -1F, -1F, 10, 2, 2, 0F);
		wood1.setRotationPoint(0F, 12.3F, -4F);
		wood1.rotateAngleX = -0.06981317F;
		wood1.rotateAngleY = 0.08726646F;
		wood1.rotateAngleZ = 0F;
		wood1.mirror = false;
		wood2 = new ModelRenderer(this, 22, 16);
		wood2.addBox(-5F, -1F, -1F, 10, 2, 2, 0F);
		wood2.setRotationPoint(0F, 12.4F, 4F);
		wood2.rotateAngleX = 0.06981317F;
		wood2.rotateAngleY = -0.05235988F;
		wood2.rotateAngleZ = 0F;
		wood2.mirror = false;
		wood3 = new ModelRenderer(this, 22, 11);
		wood3.addBox(-5F, -1F, -1F, 10, 2, 2, 0F);
		wood3.setRotationPoint(0F, 12.8F, 0F);
		wood3.rotateAngleX = 0F;
		wood3.rotateAngleY = 0.2617994F;
		wood3.rotateAngleZ = 0F;
		wood3.mirror = false;
		wood4 = new ModelRenderer(this, 22, 16);
		wood4.addBox(-5F, -1F, -1F, 10, 2, 2, 0F);
		wood4.setRotationPoint(-3F, 10.2F, 0F);
		wood4.rotateAngleX = 0F;
		wood4.rotateAngleY = 1.605703F;
		wood4.rotateAngleZ = 0F;
		wood4.mirror = false;
		wood5 = new ModelRenderer(this, 22, 21);
		wood5.addBox(-5F, -1F, -1F, 10, 2, 2, 0F);
		wood5.setRotationPoint(3F, 10.3F, 0F);
		wood5.rotateAngleX = -0.05235988F;
		wood5.rotateAngleY = 1.710423F;
		wood5.rotateAngleZ = 0F;
		wood5.mirror = false;
		wood6 = new ModelRenderer(this, 22, 16);
		wood6.addBox(-5F, -1F, -1F, 10, 2, 2, 0F);
		wood6.setRotationPoint(0F, 8.3F, 2F);
		wood6.rotateAngleX = 0F;
		wood6.rotateAngleY = 0.1919862F;
		wood6.rotateAngleZ = 0F;
		wood6.mirror = false;
		wood7 = new ModelRenderer(this, 22, 11);
		wood7.addBox(-5F, -1F, -1F, 10, 2, 2, 0F);
		wood7.setRotationPoint(0F, 8.3F, -3F);
		wood7.rotateAngleX = -0.06981317F;
		wood7.rotateAngleY = -0.1047198F;
		wood7.rotateAngleZ = 0F;
		wood7.mirror = false;
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		mesh2.render(0.0625F);
		mesh1.render(0.0625F);
		frame1.render(0.0625F);
		frame2.render(0.0625F);
		frame3.render(0.0625F);
		frame4.render(0.0625F);
		frame5.render(0.0625F);
		frame6.render(0.0625F);
		frame7.render(0.0625F);
		wood1.render(0.0625F);
		wood2.render(0.0625F);
		wood3.render(0.0625F);
		wood4.render(0.0625F);
		wood5.render(0.0625F);
		wood6.render(0.0625F);
		wood7.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
