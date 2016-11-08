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

		bottom = new ModelRenderer(this, 6, 0);
		bottom.addBox(-1F, 6F, -1F, 2, 2, 2);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 0, 0);
		handle.addBox(-0.5F, -14F, -0.5F, 1, 20, 1);
		handle.setRotationPoint(0F, 16F, 0F);
		handle.setTextureSize(64, 64);
		handle.mirror = true;
		setRotation(handle, 0F, 0F, 0F);
		cap = new ModelRenderer(this, 15, 0);
		cap.addBox(-1F, -18F, -1F, 2, 4, 2);
		cap.setRotationPoint(0F, 16F, 0F);
		cap.setTextureSize(64, 64);
		cap.mirror = true;
		setRotation(cap, 0F, 0F, 0F);
		frame1 = new ModelRenderer(this, 6, 7);
		frame1.addBox(-3F, -18.01F, -0.5F, 6, 1, 1);
		frame1.setRotationPoint(0F, 16F, 0F);
		frame1.setTextureSize(64, 64);
		frame1.mirror = true;
		setRotation(frame1, 0F, 0F, 0F);
		frame2 = new ModelRenderer(this, 6, 16);
		frame2.addBox(-3F, -23F, -0.5F, 6, 1, 1);
		frame2.setRotationPoint(0F, 16F, 0F);
		frame2.setTextureSize(64, 64);
		frame2.mirror = true;
		setRotation(frame2, 0F, 0F, 0F);
		frame3 = new ModelRenderer(this, 16, 10);
		frame3.addBox(2F, -22F, -0.5F, 1, 4, 1);
		frame3.setRotationPoint(0F, 16F, 0F);
		frame3.setTextureSize(64, 64);
		frame3.mirror = true;
		setRotation(frame3, 0F, 0F, 0F);
		frame4 = new ModelRenderer(this, 6, 10);
		frame4.addBox(-3F, -22F, -0.5F, 1, 4, 1);
		frame4.setRotationPoint(0F, 16F, 0F);
		frame4.setTextureSize(64, 64);
		frame4.mirror = true;
		setRotation(frame4, 0F, 0F, 0F);
		stone1 = new ModelRenderer(this, 0, 24);
		stone1.addBox(-2F, -23F, -2F, 4, 4, 4);
		stone1.setRotationPoint(0F, 17F, 0F);
		stone1.setTextureSize(64, 64);
		stone1.mirror = true;
		setRotation(stone1, 0F, 0F, 0F);
		stone2 = new ModelRenderer(this, 0, 34);
		stone2.addBox(-1.5F, -21.5F, -1.5F, 3, 3, 3);
		stone2.setRotationPoint(0F, 16F, 0F);
		stone2.setTextureSize(64, 64);
		stone2.mirror = true;
		setRotation(stone2, 0F, -0.3141593F, 0F);
		stone3 = new ModelRenderer(this, 14, 34);
		stone3.addBox(-1.5F, -21.5F, -1.5F, 3, 3, 3);
		stone3.setRotationPoint(0F, 16F, 0F);
		stone3.setTextureSize(64, 64);
		stone3.mirror = true;
		setRotation(stone3, 0F, 0.2617994F, 0F);
		wingL = new ModelRenderer(this, 32, 0);
		wingL.addBox(2F, -5F, -1F, 10, 10, 0);
		wingL.setRotationPoint(0F, -4F, 0F);
		wingL.setTextureSize(64, 64);
		wingL.mirror = true;
		setRotation(wingL, 0F, -0.2094395F, 0F);
		wingR = new ModelRenderer(this, 32, 12);
		wingR.addBox(2F, -5F, 1F, 10, 10, 0);
		wingR.setRotationPoint(0F, -4F, 0F);
		wingR.setTextureSize(64, 64);
		wingR.mirror = true;
		setRotation(wingR, 0F, -2.932153F, 0F);
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
