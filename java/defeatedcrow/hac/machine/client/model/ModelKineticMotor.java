package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelKineticMotor extends DCTileModelBase {

	ModelRenderer MotorBack;
	ModelRenderer MotorBack2;
	ModelRenderer MotorBody1;
	ModelRenderer MotorBody2;
	ModelRenderer MotorBody3;
	ModelRenderer MotorBody4;
	ModelRenderer MotorBody5;
	ModelRenderer MotorBody6;
	ModelRenderer MotorBody7;
	ModelRenderer MotorBody8;
	ModelRenderer MotorBody9;
	ModelRenderer MotorBody10;
	ModelRenderer Panel;
	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer Shaft;
	ModelRenderer GearBox1;
	ModelRenderer GearBox2;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer ENBox;

	public ModelKineticMotor() {

		textureWidth = 64;
		textureHeight = 32;

		MotorBack = new ModelRenderer(this, 0, 0);
		MotorBack.addBox(7F, -3F, -3F, 1, 6, 6);
		MotorBack.setRotationPoint(0F, 0F, 0F);
		MotorBack.setTextureSize(64, 32);
		MotorBack.mirror = true;
		setRotation(MotorBack, 0F, 0F, 0F);
		MotorBack2 = new ModelRenderer(this, 19, 0);
		MotorBack2.addBox(6F, -3.5F, -3.5F, 1, 7, 7);
		MotorBack2.setRotationPoint(0F, 0F, 0F);
		MotorBack2.setTextureSize(64, 32);
		MotorBack2.mirror = true;
		setRotation(MotorBack2, 0F, 0F, 0F);
		MotorBody1 = new ModelRenderer(this, 0, 12);
		MotorBody1.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody1.setRotationPoint(0F, 0F, 0F);
		MotorBody1.setTextureSize(64, 32);
		MotorBody1.mirror = true;
		setRotation(MotorBody1, 0F, 0F, 0F);
		MotorBody2 = new ModelRenderer(this, 0, 12);
		MotorBody2.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody2.setRotationPoint(0F, 0F, 0F);
		MotorBody2.setTextureSize(64, 32);
		MotorBody2.mirror = true;
		setRotation(MotorBody2, 0.6283185F, 0F, 0F);
		MotorBody3 = new ModelRenderer(this, 0, 12);
		MotorBody3.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody3.setRotationPoint(0F, 0F, 0F);
		MotorBody3.setTextureSize(64, 32);
		MotorBody3.mirror = true;
		setRotation(MotorBody3, 1.256637F, 0F, 0F);
		MotorBody4 = new ModelRenderer(this, 0, 12);
		MotorBody4.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody4.setRotationPoint(0F, 0F, 0F);
		MotorBody4.setTextureSize(64, 32);
		MotorBody4.mirror = true;
		setRotation(MotorBody4, 1.884956F, 0F, 0F);
		MotorBody5 = new ModelRenderer(this, 0, 12);
		MotorBody5.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody5.setRotationPoint(0F, 0F, 0F);
		MotorBody5.setTextureSize(64, 32);
		MotorBody5.mirror = true;
		setRotation(MotorBody5, 2.513274F, 0F, 0F);
		MotorBody6 = new ModelRenderer(this, 0, 12);
		MotorBody6.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody6.setRotationPoint(0F, 0F, 0F);
		MotorBody6.setTextureSize(64, 32);
		MotorBody6.mirror = true;
		setRotation(MotorBody6, 3.141593F, 0F, 0F);
		MotorBody7 = new ModelRenderer(this, 0, 12);
		MotorBody7.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody7.setRotationPoint(0F, 0F, 0F);
		MotorBody7.setTextureSize(64, 32);
		MotorBody7.mirror = true;
		setRotation(MotorBody7, -0.6283185F, 0F, 0F);
		MotorBody8 = new ModelRenderer(this, 0, 12);
		MotorBody8.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody8.setRotationPoint(0F, 0F, 0F);
		MotorBody8.setTextureSize(64, 32);
		MotorBody8.mirror = true;
		setRotation(MotorBody8, -1.256637F, 0F, 0F);
		MotorBody9 = new ModelRenderer(this, 0, 12);
		MotorBody9.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody9.setRotationPoint(0F, 0F, 0F);
		MotorBody9.setTextureSize(64, 32);
		MotorBody9.mirror = true;
		setRotation(MotorBody9, -1.884956F, 0F, 0F);
		MotorBody10 = new ModelRenderer(this, 0, 12);
		MotorBody10.addBox(-1F, -4F, -1F, 7, 1, 2);
		MotorBody10.setRotationPoint(0F, 0F, 0F);
		MotorBody10.setTextureSize(64, 32);
		MotorBody10.mirror = true;
		setRotation(MotorBody10, -2.513274F, 0F, 0F);
		Panel = new ModelRenderer(this, 8, 20);
		Panel.addBox(-8F, -3F, -3F, 1, 6, 6);
		Panel.setRotationPoint(0F, 0F, 0F);
		Panel.setTextureSize(64, 32);
		Panel.mirror = true;
		setRotation(Panel, 0F, 0F, 0F);
		Base1 = new ModelRenderer(this, 36, 0);
		Base1.addBox(-5F, 7F, -5F, 12, 1, 2);
		Base1.setRotationPoint(0F, 0F, 0F);
		Base1.setTextureSize(64, 32);
		Base1.mirror = true;
		setRotation(Base1, 0F, 0F, 0F);
		Base2 = new ModelRenderer(this, 36, 0);
		Base2.addBox(-5F, 7F, 3F, 12, 1, 2);
		Base2.setRotationPoint(0F, 0F, 0F);
		Base2.setTextureSize(64, 32);
		Base2.mirror = true;
		setRotation(Base2, 0F, 0F, 0F);
		Shaft = new ModelRenderer(this, 0, 15);
		Shaft.addBox(-7.9F, -0.5F, -0.5F, 10, 1, 1);
		Shaft.setRotationPoint(0F, 0F, 0F);
		Shaft.setTextureSize(64, 32);
		Shaft.mirror = true;
		setRotation(Shaft, 0F, 0F, 0F);
		GearBox1 = new ModelRenderer(this, 40, 14);
		GearBox1.addBox(-4F, -4.5F, -4.5F, 3, 9, 9);
		GearBox1.setRotationPoint(0F, 0F, 0F);
		GearBox1.setTextureSize(64, 32);
		GearBox1.mirror = true;
		setRotation(GearBox1, 0F, 0F, 0F);
		GearBox2 = new ModelRenderer(this, 22, 18);
		GearBox2.addBox(-6F, -3.5F, -3.5F, 2, 7, 7);
		GearBox2.setRotationPoint(0F, 0F, 0F);
		GearBox2.setTextureSize(64, 32);
		GearBox2.mirror = true;
		setRotation(GearBox2, 0F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 0, 18);
		Leg1.addBox(-4F, 4.5F, -4.5F, 3, 3, 1);
		Leg1.setRotationPoint(0F, 0F, 0F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 0, 18);
		Leg2.addBox(-4F, 4.5F, 3.5F, 3, 3, 1);
		Leg2.setRotationPoint(0F, 0F, 0F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		ENBox = new ModelRenderer(this, 36, 4);
		ENBox.addBox(1F, 4F, -3.5F, 5, 3, 7);
		ENBox.setRotationPoint(0F, 0F, 0F);
		ENBox.setTextureSize(64, 32);
		ENBox.mirror = true;
		setRotation(ENBox, 0F, 0F, 0F);
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
		MotorBack.render(scale);
		MotorBack2.render(scale);
		MotorBody1.render(scale);
		MotorBody2.render(scale);
		MotorBody3.render(scale);
		MotorBody4.render(scale);
		MotorBody5.render(scale);
		MotorBody6.render(scale);
		MotorBody7.render(scale);
		MotorBody8.render(scale);
		MotorBody9.render(scale);
		MotorBody10.render(scale);
		Panel.render(scale);
		Base1.render(scale);
		Base2.render(scale);
		Shaft.render(scale);
		GearBox1.render(scale);
		GearBox2.render(scale);
		Leg1.render(scale);
		Leg2.render(scale);
		ENBox.render(scale);
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

		Shaft.rotateAngleX = f2;
	}

}
