package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMagneticHover extends DCFoodModelBase {

	ModelRenderer magnet1;
	ModelRenderer magnet2;
	ModelRenderer bottom1;
	ModelRenderer bottom2;
	ModelRenderer bottom3;
	ModelRenderer bottom4;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer front1;
	ModelRenderer saddle1;
	ModelRenderer saddle2;
	ModelRenderer handle1;
	ModelRenderer handle2;
	ModelRenderer handle3;

	public ModelMagneticHover(boolean baked) {
		super(baked);
		textureWidth = 128;
		textureHeight = 128;

		magnet1 = new ModelRenderer(this, 0, 0);
		magnet1.addBox(-4F, 0F, -12F, 8, 1, 8);
		magnet1.setRotationPoint(0F, 16F, 0F);
		magnet1.setTextureSize(128, 128);
		magnet1.mirror = true;
		setRotation(magnet1, 0F, 0F, 0F);
		magnet2 = new ModelRenderer(this, 0, 0);
		magnet2.addBox(-4F, 0F, 4F, 8, 1, 8);
		magnet2.setRotationPoint(0F, 16F, 0F);
		magnet2.setTextureSize(128, 128);
		magnet2.mirror = true;
		setRotation(magnet2, 0F, 0F, 0F);
		bottom1 = new ModelRenderer(this, 0, 12);
		bottom1.addBox(-5F, -1F, -13F, 10, 1, 10);
		bottom1.setRotationPoint(0F, 16F, 0F);
		bottom1.setTextureSize(128, 128);
		bottom1.mirror = true;
		setRotation(bottom1, 0F, 0F, 0F);
		bottom2 = new ModelRenderer(this, 0, 12);
		bottom2.addBox(-5F, -1F, 3F, 10, 1, 10);
		bottom2.setRotationPoint(0F, 16F, 0F);
		bottom2.setTextureSize(128, 128);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		bottom3 = new ModelRenderer(this, 0, 26);
		bottom3.addBox(-4F, -1F, -3F, 8, 1, 6);
		bottom3.setRotationPoint(0F, 16F, 0F);
		bottom3.setTextureSize(128, 128);
		bottom3.mirror = true;
		setRotation(bottom3, 0F, 0F, 0F);
		bottom4 = new ModelRenderer(this, 66, 106);
		bottom4.addBox(-4F, -2F, -5F, 8, 1, 20);
		bottom4.setRotationPoint(0F, 16F, 0F);
		bottom4.setTextureSize(128, 128);
		bottom4.mirror = true;
		setRotation(bottom4, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 96);
		side1.addBox(-8F, -4F, -12F, 4, 3, 28);
		side1.setRotationPoint(0F, 16F, 0F);
		side1.setTextureSize(128, 128);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 96);
		side2.addBox(4F, -4F, -12F, 4, 3, 28);
		side2.setRotationPoint(0F, 16F, 0F);
		side2.setTextureSize(128, 128);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		side3 = new ModelRenderer(this, 0, 74);
		side3.addBox(-6F, -5F, -15F, 3, 4, 16);
		side3.setRotationPoint(0F, 16F, 0F);
		side3.setTextureSize(128, 128);
		side3.mirror = true;
		setRotation(side3, 0F, 0F, 0F);
		side4 = new ModelRenderer(this, 0, 74);
		side4.addBox(3F, -5F, -15F, 3, 4, 16);
		side4.setRotationPoint(0F, 16F, 0F);
		side4.setTextureSize(128, 128);
		side4.mirror = true;
		setRotation(side4, 0F, 0F, 0F);
		front1 = new ModelRenderer(this, 0, 64);
		front1.addBox(-3F, -4F, -18F, 6, 3, 6);
		front1.setRotationPoint(0F, 16F, 0F);
		front1.setTextureSize(128, 128);
		front1.mirror = true;
		setRotation(front1, 0F, 0F, 0F);
		saddle1 = new ModelRenderer(this, 66, 82);
		saddle1.addBox(-5F, -7F, -3F, 10, 6, 16);
		saddle1.setRotationPoint(0F, 16F, 0F);
		saddle1.setTextureSize(128, 128);
		saddle1.mirror = true;
		setRotation(saddle1, 0F, 0F, 0F);
		saddle2 = new ModelRenderer(this, 66, 66);
		saddle2.addBox(-3F, -8F, -2F, 6, 3, 11);
		saddle2.setRotationPoint(0F, 16F, 0F);
		saddle2.setTextureSize(128, 128);
		saddle2.mirror = true;
		setRotation(saddle2, 0.0523599F, 0F, 0F);
		handle1 = new ModelRenderer(this, 64, 0);
		handle1.addBox(-3F, 0F, 0F, 6, 2, 15);
		handle1.setRotationPoint(0F, 12F, -17F);
		handle1.setTextureSize(128, 128);
		handle1.mirror = true;
		setRotation(handle1, 0.6981317F, 0F, 0F);
		handle2 = new ModelRenderer(this, 98, 0);
		handle2.addBox(-6F, 0.5F, 13.5F, 12, 1, 1);
		handle2.setRotationPoint(0F, 12F, -17F);
		handle2.setTextureSize(128, 128);
		handle2.mirror = true;
		setRotation(handle2, 0.6981317F, 0F, 0F);
		handle3 = new ModelRenderer(this, 64, 20);
		handle3.addBox(-2F, -1F, -1F, 4, 4, 14);
		handle3.setRotationPoint(0F, 12F, -17F);
		handle3.setTextureSize(128, 128);
		handle3.mirror = true;
		setRotation(handle3, 0.6981317F, 0F, 0F);

	}

	@Override
	public void render(float swing, FoodEntityBase entity) {
		render(null, swing, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		magnet1.render(scale);
		magnet2.render(scale);
		bottom1.render(scale);
		bottom2.render(scale);
		bottom3.render(scale);
		bottom4.render(scale);
		side1.render(scale);
		side2.render(scale);
		side3.render(scale);
		side4.render(scale);
		front1.render(scale);
		saddle1.render(scale);
		saddle2.render(scale);
		handle1.render(scale);
		handle2.render(scale);
		handle3.render(scale);
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

		float f = limbSwing;

		handle1.rotateAngleX = (f / 180F) * (float) Math.PI;
		handle2.rotateAngleX = (f / 180F) * (float) Math.PI;
		handle3.rotateAngleX = (f / 180F) * (float) Math.PI;
	}

}
