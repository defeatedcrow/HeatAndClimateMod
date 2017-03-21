package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWatermill extends DCTileModelBase {
	// fields
	ModelRenderer output;
	ModelRenderer shaftcube;
	ModelRenderer middle;
	ModelRenderer panel1_1;
	ModelRenderer panel1_2;
	ModelRenderer panel1_3;
	ModelRenderer panel2_1;
	ModelRenderer panel2_2;
	ModelRenderer panel2_3;
	ModelRenderer frame1_1;
	ModelRenderer frame1_2;
	ModelRenderer frame1_3;
	ModelRenderer frame2_1;
	ModelRenderer frame2_2;
	ModelRenderer frame2_3;
	ModelRenderer wing1;
	ModelRenderer wing2;
	ModelRenderer wing3;
	ModelRenderer panel3_1;
	ModelRenderer panel3_2;
	ModelRenderer panel3_3;

	public ModelWatermill() {
		textureWidth = 64;
		textureHeight = 64;

		output = new ModelRenderer(this, 0, 0);
		output.addBox(-3F, 7F, -3F, 6, 1, 6);
		output.setRotationPoint(0F, 0F, 0F);
		output.setTextureSize(64, 32);
		output.mirror = true;
		setRotation(output, 0F, 0F, 0F);
		shaftcube = new ModelRenderer(this, 52, 0);
		shaftcube.addBox(-1.5F, -7F, -1.5F, 3, 14, 3);
		shaftcube.setRotationPoint(0F, 0F, 0F);
		shaftcube.setTextureSize(64, 32);
		shaftcube.mirror = true;
		setRotation(shaftcube, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 24, 0);
		middle.addBox(-3F, -8F, -3F, 6, 1, 6);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.setTextureSize(64, 32);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		panel1_1 = new ModelRenderer(this, 0, 12);
		panel1_1.addBox(-8F, -7.95F, 16F, 16, 1, 8);
		panel1_1.setRotationPoint(0F, 0F, 0F);
		panel1_1.setTextureSize(64, 64);
		panel1_1.mirror = true;
		setRotation(panel1_1, 0F, 0F, 0F);
		panel1_2 = new ModelRenderer(this, 0, 12);
		panel1_2.addBox(-8F, -7.9F, 16F, 16, 1, 8);
		panel1_2.setRotationPoint(0F, 0F, 0F);
		panel1_2.setTextureSize(64, 64);
		panel1_2.mirror = true;
		setRotation(panel1_2, 0F, 0.5235988F, 0F);
		panel1_3 = new ModelRenderer(this, 0, 12);
		panel1_3.addBox(-8F, -7.85F, 16F, 16, 1, 8);
		panel1_3.setRotationPoint(0F, 0F, 0F);
		panel1_3.setTextureSize(64, 64);
		panel1_3.mirror = true;
		setRotation(panel1_3, 0F, 1.047198F, 0F);
		panel2_1 = new ModelRenderer(this, 0, 12);
		panel2_1.addBox(-8F, 6.95F, 16F, 16, 1, 8);
		panel2_1.setRotationPoint(0F, 0F, 0F);
		panel2_1.setTextureSize(64, 64);
		panel2_1.mirror = true;
		setRotation(panel2_1, 0F, 0F, 0F);
		panel2_2 = new ModelRenderer(this, 0, 12);
		panel2_2.addBox(-8F, 6.9F, 16F, 16, 1, 8);
		panel2_2.setRotationPoint(0F, 0F, 0F);
		panel2_2.setTextureSize(64, 64);
		panel2_2.mirror = true;
		setRotation(panel2_2, 0F, 0.5235988F, 0F);
		panel2_3 = new ModelRenderer(this, 0, 12);
		panel2_3.addBox(-8F, 6.85F, 16F, 16, 1, 8);
		panel2_3.setRotationPoint(0F, 0F, 0F);
		panel2_3.setTextureSize(64, 64);
		panel2_3.mirror = true;
		setRotation(panel2_3, 0F, 1.047198F, 0F);
		frame1_1 = new ModelRenderer(this, 0, 8);
		frame1_1.addBox(2F, -7F, -0.5F, 18, 1, 1);
		frame1_1.setRotationPoint(0F, 0F, 0F);
		frame1_1.setTextureSize(64, 64);
		frame1_1.mirror = true;
		setRotation(frame1_1, 0F, -1.832596F, 0F);
		frame1_2 = new ModelRenderer(this, 0, 8);
		frame1_2.addBox(2F, -7F, -0.5F, 18, 1, 1);
		frame1_2.setRotationPoint(0F, 0F, 0F);
		frame1_2.setTextureSize(64, 64);
		frame1_2.mirror = true;
		setRotation(frame1_2, 0F, -1.308997F, 0F);
		frame1_3 = new ModelRenderer(this, 0, 8);
		frame1_3.addBox(2F, -7F, -0.5F, 18, 1, 1);
		frame1_3.setRotationPoint(0F, 0F, 0F);
		frame1_3.setTextureSize(64, 64);
		frame1_3.mirror = true;
		setRotation(frame1_3, 0F, -0.7853982F, 0F);
		frame2_1 = new ModelRenderer(this, 0, 8);
		frame2_1.addBox(2F, 6F, -0.5F, 18, 1, 1);
		frame2_1.setRotationPoint(0F, 0F, 0F);
		frame2_1.setTextureSize(64, 64);
		frame2_1.mirror = true;
		setRotation(frame2_1, 0F, -1.832596F, 0F);
		frame2_2 = new ModelRenderer(this, 0, 8);
		frame2_2.addBox(2F, 6F, -0.5F, 18, 1, 1);
		frame2_2.setRotationPoint(0F, 0F, 0F);
		frame2_2.setTextureSize(64, 64);
		frame2_2.mirror = true;
		setRotation(frame2_2, 0F, -1.308997F, 0F);
		frame2_3 = new ModelRenderer(this, 0, 8);
		frame2_3.addBox(2F, 6F, -0.5F, 18, 1, 1);
		frame2_3.setRotationPoint(0F, 0F, 0F);
		frame2_3.setTextureSize(64, 64);
		frame2_3.mirror = true;
		setRotation(frame2_3, 0F, -0.7853982F, 0F);
		wing1 = new ModelRenderer(this, 0, 22);
		wing1.addBox(16F, -7F, -0.6F, 8, 14, 1);
		wing1.setRotationPoint(0F, 0F, 0F);
		wing1.setTextureSize(64, 64);
		wing1.mirror = true;
		setRotation(wing1, 0F, -1.832596F, 0F);
		wing2 = new ModelRenderer(this, 0, 22);
		wing2.addBox(16F, -7F, -0.6F, 8, 14, 1);
		wing2.setRotationPoint(0F, 0F, 0F);
		wing2.setTextureSize(64, 64);
		wing2.mirror = true;
		setRotation(wing2, 0F, -1.308997F, 0F);
		wing3 = new ModelRenderer(this, 0, 22);
		wing3.addBox(16F, -7F, -0.6F, 8, 14, 1);
		wing3.setRotationPoint(0F, 0F, 0F);
		wing3.setTextureSize(64, 64);
		wing3.mirror = true;
		setRotation(wing3, 0F, -0.7853982F, 0F);
		panel3_1 = new ModelRenderer(this, 0, 22);
		panel3_1.addBox(-4F, -7F, 16F, 8, 14, 1);
		panel3_1.setRotationPoint(0F, 0F, 0F);
		panel3_1.setTextureSize(64, 64);
		panel3_1.mirror = true;
		setRotation(panel3_1, 0F, 0F, 0F);
		panel3_2 = new ModelRenderer(this, 0, 22);
		panel3_2.addBox(-4F, -7F, 16F, 8, 14, 1);
		panel3_2.setRotationPoint(0F, 0F, 0F);
		panel3_2.setTextureSize(64, 64);
		panel3_2.mirror = true;
		setRotation(panel3_2, 0F, 0.5235988F, 0F);
		panel3_3 = new ModelRenderer(this, 0, 22);
		panel3_3.addBox(-4F, -7F, 16F, 8, 14, 1);
		panel3_3.setRotationPoint(0F, 0F, 0F);
		panel3_3.setTextureSize(64, 64);
		panel3_3.mirror = true;
		setRotation(panel3_3, 0F, 1.047198F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		output.render(0.0625F);
		shaftcube.render(0.0625F);
		middle.render(0.0625F);
	}

	public void renderWing(float f, float speed, float tick) {
		setRotationWingAngles(f, speed, tick);
		panel1_1.render(0.0625F);
		panel1_2.render(0.0625F);
		panel1_3.render(0.0625F);
		panel2_1.render(0.0625F);
		panel2_2.render(0.0625F);
		panel2_3.render(0.0625F);
		panel3_1.render(0.0625F);
		panel3_2.render(0.0625F);
		panel3_3.render(0.0625F);
		frame1_1.render(0.0625F);
		frame1_2.render(0.0625F);
		frame1_3.render(0.0625F);
		frame2_1.render(0.0625F);
		frame2_2.render(0.0625F);
		frame2_3.render(0.0625F);
		wing1.render(0.0625F);
		wing2.render(0.0625F);
		wing3.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		float rot = f;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		shaftcube.rotateAngleY = f2;
		middle.rotateAngleY = f2;
	}

	public void setRotationWingAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		float rot = f;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		panel1_1.rotateAngleY = f2;
		panel2_1.rotateAngleY = f2;
		panel3_1.rotateAngleY = f2 + (float) Math.PI * 15.0F / 180.0F;
		frame1_1.rotateAngleY = f2;
		frame2_1.rotateAngleY = f2;
		wing1.rotateAngleY = f2;

		panel1_2.rotateAngleY = f2 + (float) Math.PI * 30.0F / 180.0F;
		panel2_2.rotateAngleY = f2 + (float) Math.PI * 30.0F / 180.0F;
		panel3_2.rotateAngleY = f2 + (float) Math.PI * 45.0F / 180.0F;
		frame1_2.rotateAngleY = f2 + (float) Math.PI * 30.0F / 180.0F;
		frame2_2.rotateAngleY = f2 + (float) Math.PI * 30.0F / 180.0F;
		wing2.rotateAngleY = f2 + (float) Math.PI * 30.0F / 180.0F;

		panel1_3.rotateAngleY = f2 + (float) Math.PI * 60.0F / 180.0F;
		panel2_3.rotateAngleY = f2 + (float) Math.PI * 60.0F / 180.0F;
		panel3_3.rotateAngleY = f2 + (float) Math.PI * 75.0F / 180.0F;
		frame1_3.rotateAngleY = f2 + (float) Math.PI * 60.0F / 180.0F;
		frame2_3.rotateAngleY = f2 + (float) Math.PI * 60.0F / 180.0F;
		wing3.rotateAngleY = f2 + (float) Math.PI * 60.0F / 180.0F;
	}

}
