package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelScooter extends DCFoodModelBase {

	ModelRenderer bottom;
	ModelRenderer bottom2;
	ModelRenderer carriar1;
	ModelRenderer carriar2;
	ModelRenderer saddle;
	ModelRenderer shafR;
	ModelRenderer shaftF;
	ModelRenderer front1;
	ModelRenderer front2;
	ModelRenderer cover1;
	ModelRenderer cover2;
	ModelRenderer shaft3;
	ModelRenderer shaft4;
	ModelRenderer neck;
	ModelRenderer head;
	ModelRenderer lamp1;
	ModelRenderer lamp2;
	ModelRenderer handle;
	ModelRenderer motor;
	ModelRenderer exhaust1;
	ModelRenderer exhaust2;
	ModelRenderer lampT;

	ModelRenderer tireF1;
	ModelRenderer tireF2;
	ModelRenderer tireF3;
	ModelRenderer tireF4;
	ModelRenderer tireF5;
	ModelRenderer tireF6;
	ModelRenderer tireF7;
	ModelRenderer tireF8;

	ModelRenderer tireR1;
	ModelRenderer tireR2;
	ModelRenderer tireR3;
	ModelRenderer tireR4;
	ModelRenderer tireR5;
	ModelRenderer tireR6;
	ModelRenderer tireR7;
	ModelRenderer tireR8;

	ModelRenderer tireshaftF1;
	ModelRenderer tireshaftF2;
	ModelRenderer tireshaftF3;
	ModelRenderer tireshaftF4;
	ModelRenderer tireshaftF5;
	ModelRenderer tireshaftF6;
	ModelRenderer tireshaftF7;
	ModelRenderer tireshaftF8;

	ModelRenderer tireshaftR1;
	ModelRenderer tireshaftR2;
	ModelRenderer tireshaftR3;
	ModelRenderer tireshaftR4;
	ModelRenderer tireshaftR5;
	ModelRenderer tireshaftR6;
	ModelRenderer tireshaftR7;
	ModelRenderer tireshaftR8;

	public ModelScooter(boolean baked) {
		super(baked);
		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-5F, 1F, -6F, 10, 2, 12);
		bottom.setRotationPoint(0F, 16F, -4F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		bottom2 = new ModelRenderer(this, 45, 0);
		bottom2.addBox(-5F, -8F, 0F, 10, 10, 2);
		bottom2.setRotationPoint(0F, 16F, -11F);
		bottom2.setTextureSize(128, 64);
		bottom2.mirror = true;
		setRotation(bottom2, 0.5235988F, 0F, 0F);
		carriar1 = new ModelRenderer(this, 0, 15);
		carriar1.addBox(-5F, -7F, 4F, 10, 8, 12);
		carriar1.setRotationPoint(0F, 16F, -4F);
		carriar1.setTextureSize(128, 64);
		carriar1.mirror = true;
		setRotation(carriar1, 0F, 0F, 0F);
		carriar2 = new ModelRenderer(this, 0, 36);
		carriar2.addBox(-6F, -4F, 6F, 12, 6, 12);
		carriar2.setRotationPoint(0F, 16F, -4F);
		carriar2.setTextureSize(128, 64);
		carriar2.mirror = true;
		setRotation(carriar2, 0F, 0F, 0F);
		saddle = new ModelRenderer(this, 50, 45);
		saddle.addBox(-4F, -2F, -2F, 8, 4, 12);
		saddle.setRotationPoint(0F, 9F, 1F);
		saddle.setTextureSize(128, 64);
		saddle.mirror = true;
		setRotation(saddle, 0F, 0F, 0F);
		shafR = new ModelRenderer(this, 0, 15);
		shafR.addBox(-2F, -1F, -1F, 4, 2, 2);
		shafR.setRotationPoint(0F, 18F, 7F);
		shafR.setTextureSize(128, 64);
		shafR.mirror = true;
		setRotation(shafR, 0F, 0F, 0F);
		shaftF = new ModelRenderer(this, 0, 15);
		shaftF.addBox(-2F, -1F, -1F, 4, 2, 2);
		shaftF.setRotationPoint(0F, 18F, -18F);
		shaftF.setTextureSize(128, 64);
		shaftF.mirror = true;
		setRotation(shaftF, 0F, 0F, 0F);
		front1 = new ModelRenderer(this, 72, 0);
		front1.addBox(-3F, -15F, -8F, 6, 10, 2);
		front1.setRotationPoint(0F, 16F, -11F);
		front1.setTextureSize(128, 64);
		front1.mirror = true;
		setRotation(front1, -0.2617994F, 0F, 0F);
		front2 = new ModelRenderer(this, 88, 0);
		front2.addBox(-5F, -15F, -6F, 10, 10, 2);
		front2.setRotationPoint(0F, 16F, -11F);
		front2.setTextureSize(128, 64);
		front2.mirror = true;
		setRotation(front2, -0.2617994F, 0F, 0F);
		cover1 = new ModelRenderer(this, 46, 15);
		cover1.addBox(-4F, -6F, -6F, 8, 2, 12);
		cover1.setRotationPoint(0F, 18F, -18F);
		cover1.setTextureSize(128, 64);
		cover1.mirror = true;
		setRotation(cover1, 0F, 0F, 0F);
		cover2 = new ModelRenderer(this, 86, 15);
		cover2.addBox(-3F, -8F, -5F, 6, 2, 10);
		cover2.setRotationPoint(0F, 18F, -18F);
		cover2.setTextureSize(128, 64);
		cover2.mirror = true;
		setRotation(cover2, 0F, 0F, 0F);
		shaft3 = new ModelRenderer(this, 114, 0);
		shaft3.addBox(2F, -5F, -0.5F, 1, 6, 1);
		shaft3.setRotationPoint(0F, 18F, -18F);
		shaft3.setTextureSize(128, 64);
		shaft3.mirror = true;
		setRotation(shaft3, -0.2617994F, 0F, 0F);
		shaft4 = new ModelRenderer(this, 114, 0);
		shaft4.addBox(-3F, -5F, -0.5F, 1, 6, 1);
		shaft4.setRotationPoint(0F, 18F, -18F);
		shaft4.setTextureSize(128, 64);
		shaft4.mirror = true;
		setRotation(shaft4, -0.2617994F, 0F, 0F);
		neck = new ModelRenderer(this, 116, 38);
		neck.addBox(-2F, 0F, -1F, 4, 2, 2);
		neck.setRotationPoint(0F, -1F, -12F);
		neck.setTextureSize(128, 64);
		neck.mirror = true;
		setRotation(neck, -0.2617994F, 0F, 0F);
		head = new ModelRenderer(this, 110, 30);
		head.addBox(-3F, -2F, -1F, 6, 3, 3);
		head.setRotationPoint(0F, -2F, -12F);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, -0.2617994F, 0F, 0F);
		lamp1 = new ModelRenderer(this, 99, 32);
		lamp1.addBox(-1F, -15F, -6F, 2, 2, 2);
		lamp1.setRotationPoint(0F, 16F, -11F);
		lamp1.setTextureSize(128, 64);
		lamp1.mirror = true;
		setRotation(lamp1, 0F, 0F, 0F);
		lamp2 = new ModelRenderer(this, 98, 38);
		lamp2.addBox(-2F, -16F, -7F, 4, 4, 2);
		lamp2.setRotationPoint(0F, 16F, -11F);
		lamp2.setTextureSize(128, 64);
		lamp2.mirror = true;
		setRotation(lamp2, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 46, 31);
		handle.addBox(-8F, -1F, 0F, 16, 1, 1);
		handle.setRotationPoint(0F, -2F, -12F);
		handle.setTextureSize(128, 64);
		handle.mirror = true;
		setRotation(handle, -0.2617994F, 0F, 0F);

		motor = new ModelRenderer(this, 50, 35);
		motor.addBox(-4F, 2F, 0F, 8, 2, 6);
		motor.setRotationPoint(0F, 16F, -4F);
		motor.setTextureSize(128, 64);
		motor.mirror = true;
		setRotation(motor, 0F, 0F, 0F);
		exhaust1 = new ModelRenderer(this, 108, 53);
		exhaust1.addBox(-4F, 3F, 10F, 2, 2, 6);
		exhaust1.setRotationPoint(0F, 16F, -4F);
		exhaust1.setTextureSize(128, 64);
		exhaust1.mirror = true;
		setRotation(exhaust1, 0F, 0F, 0F);
		exhaust2 = new ModelRenderer(this, 92, 53);
		exhaust2.addBox(-3.5F, 3.5F, 4F, 1, 1, 6);
		exhaust2.setRotationPoint(0F, 16F, -4F);
		exhaust2.setTextureSize(128, 64);
		exhaust2.mirror = true;
		setRotation(exhaust2, 0F, 0F, 0F);
		lampT = new ModelRenderer(this, 92, 48);
		lampT.addBox(-2F, -3F, 18F, 4, 2, 1);
		lampT.setRotationPoint(0F, 16F, -4F);
		lampT.setTextureSize(128, 64);
		lampT.mirror = true;
		setRotation(lampT, 0F, 0F, 0F);

		tireF1 = new ModelRenderer(this, 80, 35);
		tireF1.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireF1.setRotationPoint(0F, 18F, -18F);
		tireF1.setTextureSize(128, 64);
		tireF1.mirror = true;
		setRotation(tireF1, 0F, 0F, 0F);
		tireF2 = new ModelRenderer(this, 80, 35);
		tireF2.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireF2.setRotationPoint(0F, 18F, -18F);
		tireF2.setTextureSize(128, 64);
		tireF2.mirror = true;
		setRotation(tireF2, 0F, 0F, 0F);
		tireF3 = new ModelRenderer(this, 80, 35);
		tireF3.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireF3.setRotationPoint(0F, 18F, -18F);
		tireF3.setTextureSize(128, 64);
		tireF3.mirror = true;
		setRotation(tireF3, 0F, 0F, 0F);
		tireF4 = new ModelRenderer(this, 80, 35);
		tireF4.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireF4.setRotationPoint(0F, 18F, -18F);
		tireF4.setTextureSize(128, 64);
		tireF4.mirror = true;
		setRotation(tireF4, 0F, 0F, 0F);
		tireF5 = new ModelRenderer(this, 80, 35);
		tireF5.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireF5.setRotationPoint(0F, 18F, -18F);
		tireF5.setTextureSize(128, 64);
		tireF5.mirror = true;
		setRotation(tireF5, 0F, 0F, 0F);
		tireF6 = new ModelRenderer(this, 80, 35);
		tireF6.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireF6.setRotationPoint(0F, 18F, -18F);
		tireF6.setTextureSize(128, 64);
		tireF6.mirror = true;
		setRotation(tireF6, 0F, 0F, 0F);
		tireF7 = new ModelRenderer(this, 80, 35);
		tireF7.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireF7.setRotationPoint(0F, 18F, -18F);
		tireF7.setTextureSize(128, 64);
		tireF7.mirror = true;
		setRotation(tireF7, 0F, 0F, 0F);
		tireF8 = new ModelRenderer(this, 80, 35);
		tireF8.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireF8.setRotationPoint(0F, 18F, -18F);
		tireF8.setTextureSize(128, 64);
		tireF8.mirror = true;
		setRotation(tireF8, 0F, 0F, 0F);

		tireR1 = new ModelRenderer(this, 80, 35);
		tireR1.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireR1.setRotationPoint(0F, 18F, 7F);
		tireR1.setTextureSize(128, 64);
		tireR1.mirror = true;
		setRotation(tireR1, 0F, 0F, 0F);
		tireR2 = new ModelRenderer(this, 80, 35);
		tireR2.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireR2.setRotationPoint(0F, 18F, 7F);
		tireR2.setTextureSize(128, 64);
		tireR2.mirror = true;
		setRotation(tireR2, 0F, 0F, 0F);
		tireR3 = new ModelRenderer(this, 80, 35);
		tireR3.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireR3.setRotationPoint(0F, 18F, 7F);
		tireR3.setTextureSize(128, 64);
		tireR3.mirror = true;
		setRotation(tireR3, 0F, 0F, 0F);
		tireR4 = new ModelRenderer(this, 80, 35);
		tireR4.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireR4.setRotationPoint(0F, 18F, 7F);
		tireR4.setTextureSize(128, 64);
		tireR4.mirror = true;
		setRotation(tireR4, 0F, 0F, 0F);
		tireR5 = new ModelRenderer(this, 80, 35);
		tireR5.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireR5.setRotationPoint(0F, 18F, 7F);
		tireR5.setTextureSize(128, 64);
		tireR5.mirror = true;
		setRotation(tireR5, 0F, 0F, 0F);
		tireR6 = new ModelRenderer(this, 80, 35);
		tireR6.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireR6.setRotationPoint(0F, 18F, 7F);
		tireR6.setTextureSize(128, 64);
		tireR6.mirror = true;
		setRotation(tireR6, 0F, 0F, 0F);
		tireR7 = new ModelRenderer(this, 80, 35);
		tireR7.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireR7.setRotationPoint(0F, 18F, 7F);
		tireR7.setTextureSize(128, 64);
		tireR7.mirror = true;
		setRotation(tireR7, 0F, 0F, 0F);
		tireR8 = new ModelRenderer(this, 80, 35);
		tireR8.addBox(-2F, -5F, -2F, 4, 2, 4);
		tireR8.setRotationPoint(0F, 18F, 7F);
		tireR8.setTextureSize(128, 64);
		tireR8.mirror = true;
		setRotation(tireR8, 0F, 0F, 0F);

		tireshaftF1 = new ModelRenderer(this, 90, 30);
		tireshaftF1.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftF1.setRotationPoint(0F, 18F, -18F);
		tireshaftF1.setTextureSize(128, 64);
		tireshaftF1.mirror = true;
		setRotation(tireshaftF1, 0F, 0F, 0F);
		tireshaftF2 = new ModelRenderer(this, 90, 30);
		tireshaftF2.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftF2.setRotationPoint(0F, 18F, -18F);
		tireshaftF2.setTextureSize(128, 64);
		tireshaftF2.mirror = true;
		setRotation(tireshaftF2, 0F, 0F, 0F);
		tireshaftF3 = new ModelRenderer(this, 90, 30);
		tireshaftF3.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftF3.setRotationPoint(0F, 18F, -18F);
		tireshaftF3.setTextureSize(128, 64);
		tireshaftF3.mirror = true;
		setRotation(tireshaftF3, 0F, 0F, 0F);
		tireshaftF4 = new ModelRenderer(this, 90, 30);
		tireshaftF4.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftF4.setRotationPoint(0F, 18F, -18F);
		tireshaftF4.setTextureSize(128, 64);
		tireshaftF4.mirror = true;
		setRotation(tireshaftF4, 0F, 0F, 0F);
		tireshaftF5 = new ModelRenderer(this, 90, 30);
		tireshaftF5.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftF5.setRotationPoint(0F, 18F, -18F);
		tireshaftF5.setTextureSize(128, 64);
		tireshaftF5.mirror = true;
		setRotation(tireshaftF5, 0F, 0F, 0F);
		tireshaftF6 = new ModelRenderer(this, 90, 30);
		tireshaftF6.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftF6.setRotationPoint(0F, 18F, -18F);
		tireshaftF6.setTextureSize(128, 64);
		tireshaftF6.mirror = true;
		setRotation(tireshaftF6, 0F, 0F, 0F);
		tireshaftF7 = new ModelRenderer(this, 90, 30);
		tireshaftF7.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftF7.setRotationPoint(0F, 18F, -18F);
		tireshaftF7.setTextureSize(128, 64);
		tireshaftF7.mirror = true;
		setRotation(tireshaftF7, 0F, 0F, 0F);
		tireshaftF8 = new ModelRenderer(this, 90, 30);
		tireshaftF8.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftF8.setRotationPoint(0F, 18F, -18F);
		tireshaftF8.setTextureSize(128, 64);
		tireshaftF8.mirror = true;
		setRotation(tireshaftF8, 0F, 0F, 0F);

		tireshaftR1 = new ModelRenderer(this, 90, 30);
		tireshaftR1.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftR1.setRotationPoint(0F, 18F, 7F);
		tireshaftR1.setTextureSize(128, 64);
		tireshaftR1.mirror = true;
		setRotation(tireshaftR1, 0F, 0F, 0F);
		tireshaftR2 = new ModelRenderer(this, 90, 30);
		tireshaftR2.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftR2.setRotationPoint(0F, 18F, 7F);
		tireshaftR2.setTextureSize(128, 64);
		tireshaftR2.mirror = true;
		setRotation(tireshaftR2, 0F, 0F, 0F);
		tireshaftR3 = new ModelRenderer(this, 90, 30);
		tireshaftR3.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftR3.setRotationPoint(0F, 18F, 7F);
		tireshaftR3.setTextureSize(128, 64);
		tireshaftR3.mirror = true;
		setRotation(tireshaftR3, 0F, 0F, 0F);
		tireshaftR4 = new ModelRenderer(this, 90, 30);
		tireshaftR4.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftR4.setRotationPoint(0F, 18F, 7F);
		tireshaftR4.setTextureSize(128, 64);
		tireshaftR4.mirror = true;
		setRotation(tireshaftR4, 0F, 0F, 0F);
		tireshaftR5 = new ModelRenderer(this, 90, 30);
		tireshaftR5.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftR5.setRotationPoint(0F, 18F, 7F);
		tireshaftR5.setTextureSize(128, 64);
		tireshaftR5.mirror = true;
		setRotation(tireshaftR5, 0F, 0F, 0F);
		tireshaftR6 = new ModelRenderer(this, 90, 30);
		tireshaftR6.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftR6.setRotationPoint(0F, 18F, 7F);
		tireshaftR6.setTextureSize(128, 64);
		tireshaftR6.mirror = true;
		setRotation(tireshaftR6, 0F, 0F, 0F);
		tireshaftR7 = new ModelRenderer(this, 90, 30);
		tireshaftR7.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftR7.setRotationPoint(0F, 18F, 7F);
		tireshaftR7.setTextureSize(128, 64);
		tireshaftR7.mirror = true;
		setRotation(tireshaftR7, 0F, 0F, 0F);
		tireshaftR8 = new ModelRenderer(this, 90, 30);
		tireshaftR8.addBox(-1F, -3F, -0.5F, 2, 2, 1);
		tireshaftR8.setRotationPoint(0F, 18F, 7F);
		tireshaftR8.setTextureSize(128, 64);
		tireshaftR8.mirror = true;
		setRotation(tireshaftR8, 0F, 0F, 0F);

	}

	@Override
	public void render(float swing, FoodEntityBase entity) {
		render(null, swing, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bottom.render(scale);
		bottom2.render(scale);
		carriar1.render(scale);
		carriar2.render(scale);
		saddle.render(scale);
		shafR.render(scale);
		shaftF.render(scale);
		front1.render(scale);
		front2.render(scale);
		cover1.render(scale);
		cover2.render(scale);
		shaft3.render(scale);
		shaft4.render(scale);
		neck.render(scale);
		head.render(scale);
		lamp1.render(scale);
		lamp2.render(scale);
		handle.render(scale);

		motor.render(scale);
		exhaust1.render(scale);
		exhaust2.render(scale);
		lampT.render(scale);
	}

	public void renderTire(float swing, float wheel, float scale) {
		setTireAngles(wheel, swing);
		tireF1.render(scale);
		tireF2.render(scale);
		tireF3.render(scale);
		tireF4.render(scale);
		tireF5.render(scale);
		tireF6.render(scale);
		tireF7.render(scale);
		tireF8.render(scale);

		tireR1.render(scale);
		tireR2.render(scale);
		tireR3.render(scale);
		tireR4.render(scale);
		tireR5.render(scale);
		tireR6.render(scale);
		tireR7.render(scale);
		tireR8.render(scale);

		tireshaftF1.render(scale);
		tireshaftF2.render(scale);
		tireshaftF3.render(scale);
		tireshaftF4.render(scale);
		tireshaftF5.render(scale);
		tireshaftF6.render(scale);
		tireshaftF7.render(scale);
		tireshaftF8.render(scale);

		tireshaftR1.render(scale);
		tireshaftR2.render(scale);
		tireshaftR3.render(scale);
		tireshaftR4.render(scale);
		tireshaftR5.render(scale);
		tireshaftR6.render(scale);
		tireshaftR7.render(scale);
		tireshaftR8.render(scale);
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

		head.rotateAngleY = (f / 180F) * (float) Math.PI;
		handle.rotateAngleY = (f / 180F) * (float) Math.PI;
		shaft3.rotateAngleY = (f / 180F) * (float) Math.PI;
		shaft4.rotateAngleY = (f / 180F) * (float) Math.PI;
	}

	public void setTireAngles(float swing, float rot) {
		tireF1.rotateAngleX = (swing / 180F) * (float) Math.PI;
		tireF2.rotateAngleX = ((swing + 45F) / 180F) * (float) Math.PI;
		tireF3.rotateAngleX = ((swing + 90F) / 180F) * (float) Math.PI;
		tireF4.rotateAngleX = ((swing + 135F) / 180F) * (float) Math.PI;
		tireF5.rotateAngleX = ((swing + 180F) / 180F) * (float) Math.PI;
		tireF6.rotateAngleX = ((swing + 225F) / 180F) * (float) Math.PI;
		tireF7.rotateAngleX = ((swing + 270F) / 180F) * (float) Math.PI;
		tireF8.rotateAngleX = ((swing + 315F) / 180F) * (float) Math.PI;

		tireR1.rotateAngleX = (swing / 180F) * (float) Math.PI;
		tireR2.rotateAngleX = ((swing + 45F) / 180F) * (float) Math.PI;
		tireR3.rotateAngleX = ((swing + 90F) / 180F) * (float) Math.PI;
		tireR4.rotateAngleX = ((swing + 135F) / 180F) * (float) Math.PI;
		tireR5.rotateAngleX = ((swing + 180F) / 180F) * (float) Math.PI;
		tireR6.rotateAngleX = ((swing + 225F) / 180F) * (float) Math.PI;
		tireR7.rotateAngleX = ((swing + 270F) / 180F) * (float) Math.PI;
		tireR8.rotateAngleX = ((swing + 315F) / 180F) * (float) Math.PI;

		tireshaftF1.rotateAngleX = (swing / 180F) * (float) Math.PI;
		tireshaftF2.rotateAngleX = ((swing + 45F) / 180F) * (float) Math.PI;
		tireshaftF3.rotateAngleX = ((swing + 90F) / 180F) * (float) Math.PI;
		tireshaftF4.rotateAngleX = ((swing + 135F) / 180F) * (float) Math.PI;
		tireshaftF5.rotateAngleX = ((swing + 180F) / 180F) * (float) Math.PI;
		tireshaftF6.rotateAngleX = ((swing + 225F) / 180F) * (float) Math.PI;
		tireshaftF7.rotateAngleX = ((swing + 270F) / 180F) * (float) Math.PI;
		tireshaftF8.rotateAngleX = ((swing + 315F) / 180F) * (float) Math.PI;

		tireshaftR1.rotateAngleX = (swing / 180F) * (float) Math.PI;
		tireshaftR2.rotateAngleX = ((swing + 45F) / 180F) * (float) Math.PI;
		tireshaftR3.rotateAngleX = ((swing + 90F) / 180F) * (float) Math.PI;
		tireshaftR4.rotateAngleX = ((swing + 135F) / 180F) * (float) Math.PI;
		tireshaftR5.rotateAngleX = ((swing + 180F) / 180F) * (float) Math.PI;
		tireshaftR6.rotateAngleX = ((swing + 225F) / 180F) * (float) Math.PI;
		tireshaftR7.rotateAngleX = ((swing + 270F) / 180F) * (float) Math.PI;
		tireshaftR8.rotateAngleX = ((swing + 315F) / 180F) * (float) Math.PI;

		tireF1.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireF2.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireF3.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireF4.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireF5.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireF6.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireF7.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireF8.rotateAngleY = (rot / 180F) * (float) Math.PI;

		tireshaftF1.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireshaftF2.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireshaftF3.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireshaftF4.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireshaftF5.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireshaftF6.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireshaftF7.rotateAngleY = (rot / 180F) * (float) Math.PI;
		tireshaftF8.rotateAngleY = (rot / 180F) * (float) Math.PI;
	}

}
