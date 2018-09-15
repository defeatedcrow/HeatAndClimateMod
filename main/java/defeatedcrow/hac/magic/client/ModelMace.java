package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMace extends DCTileModelBase {

	ModelRenderer bottom;
	ModelRenderer handle;
	ModelRenderer cap;
	ModelRenderer frame1;
	ModelRenderer frame2;
	ModelRenderer frame3;
	ModelRenderer frame4;
	ModelRenderer stone1;
	ModelRenderer stone2;
	ModelRenderer stone3;
	ModelRenderer wingL;
	ModelRenderer wingR;

	public ModelMace() {

		textureWidth = 64;
		textureHeight = 64;

		this.bottom = new ModelRenderer(this, 6, 0);
		this.bottom.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.bottom.addBox(-1.0F, 6.0F, -1.0F, 2, 2, 2);
		this.handle = new ModelRenderer(this, 0, 0);
		this.handle.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.handle.addBox(-0.5F, -14.0F, -0.5F, 1, 20, 1);
		this.cap = new ModelRenderer(this, 15, 0);
		this.cap.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.cap.addBox(-1.0F, -17.9F, -1.0F, 2, 4, 2);
		this.frame1 = new ModelRenderer(this, 6, 7);
		this.frame1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.frame1.addBox(-3.0F, -18.0F, -0.5F, 6, 1, 1);
		this.frame2 = new ModelRenderer(this, 6, 16);
		this.frame2.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.frame2.addBox(-3.0F, -23.0F, -0.5F, 6, 1, 1);
		this.frame3 = new ModelRenderer(this, 16, 10);
		this.frame3.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.frame3.addBox(2.0F, -22.0F, -0.5F, 1, 4, 1);
		this.frame4 = new ModelRenderer(this, 6, 10);
		this.frame4.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.frame4.addBox(-3.0F, -22.0F, -0.5F, 1, 4, 1);
		this.stone1 = new ModelRenderer(this, 0, 24);
		this.stone1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.stone1.addBox(-2.0F, -22.0F, -2.0F, 4, 4, 4);
		this.stone2 = new ModelRenderer(this, 0, 34);
		this.stone2.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.stone2.addBox(-1.5F, -21.5F, -1.5F, 3, 3, 3);
		this.setRotation(this.stone2, 0.0F, 0.20943951023931953F, 0.0F);
		this.stone3 = new ModelRenderer(this, 14, 34);
		this.stone3.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.stone3.addBox(-1.5F, -21.5F, -1.5F, 3, 3, 3);
		this.setRotation(this.stone3, 0.0F, -0.17453292519943295F, 0.0F);
		this.wingL = new ModelRenderer(this, 32, 0);
		this.wingL.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.wingL.addBox(0.0F, -9.0F, 0.0F, 12, 14, 0);
		this.wingR = new ModelRenderer(this, 32, 14);
		this.wingR.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.wingR.addBox(0.0F, -9.0F, 0.0F, 12, 14, 0);
		this.setRotation(this.wingR, 0.0F, 3.141592653589793F, 0.0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bottom.render(scale);
		handle.render(scale);
		cap.render(scale);
	}

	public void renderFrame(float scale) {
		frame1.render(scale);
		frame2.render(scale);
		frame3.render(scale);
		frame4.render(scale);
	}

	public void renderClear(float scale) {
		stone1.render(scale);
		stone2.render(scale);
		stone3.render(scale);
		wingL.render(scale);
		wingR.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

}
