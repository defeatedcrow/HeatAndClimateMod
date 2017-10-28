package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelOilEngine extends DCTileModelBase {

	ModelRenderer bottom;
	ModelRenderer back;
	ModelRenderer beltcase;
	ModelRenderer output;
	ModelRenderer shaft;
	ModelRenderer gear11;
	ModelRenderer gear12;
	ModelRenderer gear21;
	ModelRenderer gear22;
	ModelRenderer gear23;
	ModelRenderer gear24;
	ModelRenderer cylinder1;
	ModelRenderer cylinder2;
	ModelRenderer ctop1;
	ModelRenderer ctop2;
	ModelRenderer ctop3;
	ModelRenderer ctop4;
	ModelRenderer tank;
	ModelRenderer tankcap;
	ModelRenderer pipe1;
	ModelRenderer pipe2;
	ModelRenderer pipe3;
	ModelRenderer pipe4;
	ModelRenderer pipe5;
	ModelRenderer pipe6;
	ModelRenderer pipe7;

	public ModelOilEngine() {

		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 51);
		bottom.addBox(-7F, 7F, -6F, 14, 1, 12);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		back = new ModelRenderer(this, 50, 51);
		back.addBox(-7F, 1F, -3F, 1, 6, 6);
		back.setRotationPoint(0F, 0F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		beltcase = new ModelRenderer(this, 44, 13);
		beltcase.addBox(5F, -8F, -4F, 2, 11, 8);
		beltcase.setRotationPoint(0F, 4F, 0F);
		beltcase.setTextureSize(64, 64);
		beltcase.mirror = true;
		setRotation(beltcase, 0F, 0F, 0F);
		output = new ModelRenderer(this, 50, 0);
		output.addBox(7F, -3F, -3F, 1, 6, 6);
		output.setRotationPoint(0F, 0F, 0F);
		output.setTextureSize(64, 64);
		output.mirror = true;
		setRotation(output, 0F, 0F, 0F);
		shaft = new ModelRenderer(this, 0, 14);
		shaft.addBox(-6F, -1.5F, -1.5F, 12, 3, 3);
		shaft.setRotationPoint(0F, 4F, 0F);
		shaft.setTextureSize(64, 64);
		shaft.mirror = true;
		setRotation(shaft, 0F, 0F, 0F);
		gear11 = new ModelRenderer(this, 35, 0);
		gear11.addBox(2F, -3F, -3F, 1, 6, 6);
		gear11.setRotationPoint(0F, 4F, 0F);
		gear11.setTextureSize(64, 64);
		gear11.mirror = true;
		setRotation(gear11, 0F, 0F, 0F);
		gear12 = new ModelRenderer(this, 35, 0);
		gear12.addBox(-3F, -3F, -3F, 1, 6, 6);
		gear12.setRotationPoint(0F, 4F, 0F);
		gear12.setTextureSize(64, 64);
		gear12.mirror = true;
		setRotation(gear12, 0F, 0F, 0F);
		gear21 = new ModelRenderer(this, 30, 12);
		gear21.addBox(1F, -2F, -2F, 1, 4, 4);
		gear21.setRotationPoint(0F, 1F, 3.5F);
		gear21.setTextureSize(64, 64);
		gear21.mirror = true;
		setRotation(gear21, 0F, 0F, 0F);
		gear22 = new ModelRenderer(this, 30, 12);
		gear22.addBox(-4F, -2F, -2F, 1, 4, 4);
		gear22.setRotationPoint(0F, 1F, 3.5F);
		gear22.setTextureSize(64, 64);
		gear22.mirror = true;
		setRotation(gear22, 0F, 0F, 0F);
		gear23 = new ModelRenderer(this, 30, 12);
		gear23.addBox(1F, -2F, -2F, 1, 4, 4);
		gear23.setRotationPoint(0F, 1F, -3.5F);
		gear23.setTextureSize(64, 64);
		gear23.mirror = true;
		setRotation(gear23, 0F, 0F, 0F);
		gear24 = new ModelRenderer(this, 30, 12);
		gear24.addBox(0F, -2F, -2F, 1, 4, 4);
		gear24.setRotationPoint(-4F, 1F, -3.5F);
		gear24.setTextureSize(64, 64);
		gear24.mirror = true;
		setRotation(gear24, 0F, 0F, 0F);
		cylinder1 = new ModelRenderer(this, 0, 0);
		cylinder1.addBox(-5F, -3F, 3F, 10, 8, 5);
		cylinder1.setRotationPoint(0F, 0F, 0F);
		cylinder1.setTextureSize(64, 64);
		cylinder1.mirror = true;
		setRotation(cylinder1, 0F, 0F, 0F);
		cylinder2 = new ModelRenderer(this, 0, 0);
		cylinder2.addBox(-5F, -3F, 3F, 10, 8, 5);
		cylinder2.setRotationPoint(0F, 0F, 0F);
		cylinder2.setTextureSize(64, 64);
		cylinder2.mirror = true;
		setRotation(cylinder2, 0F, 3.141593F, 0F);
		ctop1 = new ModelRenderer(this, 30, 22);
		ctop1.addBox(1F, -4F, 4F, 3, 1, 3);
		ctop1.setRotationPoint(0F, 0F, 0F);
		ctop1.setTextureSize(64, 64);
		ctop1.mirror = true;
		setRotation(ctop1, 0F, 0F, 0F);
		ctop2 = new ModelRenderer(this, 30, 22);
		ctop2.addBox(-4F, -4F, 4F, 3, 1, 3);
		ctop2.setRotationPoint(0F, 0F, 0F);
		ctop2.setTextureSize(64, 64);
		ctop2.mirror = true;
		setRotation(ctop2, 0F, 0F, 0F);
		ctop3 = new ModelRenderer(this, 30, 22);
		ctop3.addBox(1F, -4F, -7F, 3, 1, 3);
		ctop3.setRotationPoint(0F, 0F, 0F);
		ctop3.setTextureSize(64, 64);
		ctop3.mirror = true;
		setRotation(ctop3, 0F, 0F, 0F);
		ctop4 = new ModelRenderer(this, 30, 22);
		ctop4.addBox(-4F, -4F, -7F, 3, 1, 3);
		ctop4.setRotationPoint(0F, 0F, 0F);
		ctop4.setTextureSize(64, 64);
		ctop4.mirror = true;
		setRotation(ctop4, 0F, 0F, 0F);
		tank = new ModelRenderer(this, 0, 22);
		tank.addBox(-5F, -6F, -4F, 10, 3, 8);
		tank.setRotationPoint(0F, 0F, 0F);
		tank.setTextureSize(64, 64);
		tank.mirror = true;
		setRotation(tank, 0F, 0F, 0F);
		tankcap = new ModelRenderer(this, 23, 33);
		tankcap.addBox(-2F, -7F, -2F, 4, 1, 4);
		tankcap.setRotationPoint(0F, 0F, 0F);
		tankcap.setTextureSize(64, 64);
		tankcap.mirror = true;
		setRotation(tankcap, 0F, 0F, 0F);
		pipe1 = new ModelRenderer(this, 0, 46);
		pipe1.addBox(-8F, 4F, -7F, 12, 2, 2);
		pipe1.setRotationPoint(0F, 0F, 0F);
		pipe1.setTextureSize(64, 64);
		pipe1.mirror = true;
		setRotation(pipe1, 0F, 0F, 0F);
		pipe2 = new ModelRenderer(this, 0, 46);
		pipe2.addBox(-8F, 4F, 4F, 12, 2, 2);
		pipe2.setRotationPoint(0F, 0F, 0F);
		pipe2.setTextureSize(64, 64);
		pipe2.mirror = true;
		setRotation(pipe2, 0F, 0F, 0F);
		pipe3 = new ModelRenderer(this, 43, 42);
		pipe3.addBox(-8F, -2F, -7F, 2, 6, 2);
		pipe3.setRotationPoint(0F, 0F, 0F);
		pipe3.setTextureSize(64, 64);
		pipe3.mirror = true;
		setRotation(pipe3, 0F, 0F, 0F);
		pipe4 = new ModelRenderer(this, 29, 45);
		pipe4.addBox(-8F, 1F, 4F, 2, 3, 2);
		pipe4.setRotationPoint(0F, 0F, 0F);
		pipe4.setTextureSize(64, 64);
		pipe4.mirror = true;
		setRotation(pipe4, 0F, 0F, 0F);
		pipe5 = new ModelRenderer(this, 0, 34);
		pipe5.addBox(-8F, -2F, -5F, 2, 2, 9);
		pipe5.setRotationPoint(0F, 0F, 0F);
		pipe5.setTextureSize(64, 64);
		pipe5.mirror = true;
		setRotation(pipe5, 0F, 0F, 0F);
		pipe6 = new ModelRenderer(this, 52, 41);
		pipe6.addBox(-8.5F, -5F, 3.5F, 3, 6, 3);
		pipe6.setRotationPoint(0F, 0F, 0F);
		pipe6.setTextureSize(64, 64);
		pipe6.mirror = true;
		setRotation(pipe6, 0F, 0F, 0F);
		pipe7 = new ModelRenderer(this, 23, 39);
		pipe7.addBox(-9F, -6F, 3F, 4, 1, 4);
		pipe7.setRotationPoint(0F, 0F, 0F);
		pipe7.setTextureSize(64, 64);
		pipe7.mirror = true;
		setRotation(pipe7, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bottom.render(scale);
		back.render(scale);
		beltcase.render(scale);
		output.render(scale);
		shaft.render(scale);
		gear11.render(scale);
		gear12.render(scale);
		gear21.render(scale);
		gear22.render(scale);
		gear23.render(scale);
		gear24.render(scale);
		cylinder1.render(scale);
		cylinder2.render(scale);
		ctop1.render(scale);
		ctop2.render(scale);
		ctop3.render(scale);
		ctop4.render(scale);
		tank.render(scale);
		tankcap.render(scale);
		pipe1.render(scale);
		pipe2.render(scale);
		pipe3.render(scale);
		pipe4.render(scale);
		pipe5.render(scale);
		pipe6.render(scale);
		pipe7.render(scale);
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

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		float rot = f;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		shaft.rotateAngleX = -f2;
		gear11.rotateAngleX = -f2;
		gear12.rotateAngleX = -f2;

		gear21.rotateAngleX = f2;
		gear22.rotateAngleX = f2;
		gear23.rotateAngleX = f2;
		gear24.rotateAngleX = f2;
	}

}
