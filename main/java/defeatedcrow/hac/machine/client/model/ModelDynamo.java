package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDynamo extends DCTileModelBase {
	// fields
	ModelRenderer output;
	ModelRenderer shaftcube;
	ModelRenderer middle;
	ModelRenderer frame1;
	ModelRenderer frame2;
	ModelRenderer frame3;
	ModelRenderer frame4;
	ModelRenderer mag1;
	ModelRenderer mag2;
	ModelRenderer mag3;
	ModelRenderer mag4;
	ModelRenderer mag5;
	ModelRenderer mag6;
	ModelRenderer mag7;
	ModelRenderer mag8;
	ModelRenderer mag2_1;
	ModelRenderer mag2_2;
	ModelRenderer mag2_3;
	ModelRenderer mag2_4;
	ModelRenderer fil1;
	ModelRenderer fil2;

	public ModelDynamo() {
		textureWidth = 64;
		textureHeight = 64;

		output = new ModelRenderer(this, 0, 0);
		output.addBox(-3F, 7F, -3F, 6, 1, 6);
		output.setRotationPoint(0F, 0F, 0F);
		output.setTextureSize(64, 32);
		output.mirror = true;
		setRotation(output, 0F, 0F, 0F);
		shaftcube = new ModelRenderer(this, 52, 0);
		shaftcube.addBox(-1.5F, -6F, -1.5F, 3, 13, 3);
		shaftcube.setRotationPoint(0F, 0F, 0F);
		shaftcube.setTextureSize(64, 32);
		shaftcube.mirror = true;
		setRotation(shaftcube, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 24, 0);
		middle.addBox(-3F, -8F, -3F, 6, 2, 6);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.setTextureSize(64, 32);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		frame1 = new ModelRenderer(this, 0, 46);
		frame1.addBox(-8F, -8F, -8F, 16, 16, 1);
		frame1.setRotationPoint(0F, 0F, 0F);
		frame1.setTextureSize(64, 64);
		frame1.mirror = true;
		setRotation(frame1, 0F, 3.141593F, 0F);
		frame2 = new ModelRenderer(this, 0, 46);
		frame2.addBox(-8F, -8F, -8F, 16, 16, 1);
		frame2.setRotationPoint(0F, 0F, 0F);
		frame2.setTextureSize(64, 64);
		frame2.mirror = true;
		setRotation(frame2, 0F, 0F, 0F);
		frame3 = new ModelRenderer(this, 34, 46);
		frame3.addBox(-7F, -8F, -8F, 14, 16, 1);
		frame3.setRotationPoint(0F, 0F, 0F);
		frame3.setTextureSize(64, 64);
		frame3.mirror = true;
		setRotation(frame3, 0F, 1.570796F, 0F);
		frame4 = new ModelRenderer(this, 34, 46);
		frame4.addBox(-7F, -8F, -8F, 14, 16, 1);
		frame4.setRotationPoint(0F, 0F, 0F);
		frame4.setTextureSize(64, 64);
		frame4.mirror = true;
		setRotation(frame4, 0F, -1.570796F, 0F);
		mag1 = new ModelRenderer(this, 0, 12);
		mag1.addBox(-2F, -6F, -7F, 4, 12, 2);
		mag1.setRotationPoint(0F, 0F, 0F);
		mag1.setTextureSize(64, 64);
		mag1.mirror = true;
		setRotation(mag1, 0F, 0F, 0F);
		mag2 = new ModelRenderer(this, 0, 12);
		mag2.addBox(-2F, -6F, -7F, 4, 12, 2);
		mag2.setRotationPoint(0F, 0F, 0F);
		mag2.setTextureSize(64, 64);
		mag2.mirror = true;
		setRotation(mag2, 0F, 0.7853982F, 0F);
		mag3 = new ModelRenderer(this, 0, 12);
		mag3.addBox(-2F, -6F, -7F, 4, 12, 2);
		mag3.setRotationPoint(0F, 0F, 0F);
		mag3.setTextureSize(64, 64);
		mag3.mirror = true;
		setRotation(mag3, 0F, 1.570796F, 0F);
		mag4 = new ModelRenderer(this, 0, 12);
		mag4.addBox(-2F, -6F, -7F, 4, 12, 2);
		mag4.setRotationPoint(0F, 0F, 0F);
		mag4.setTextureSize(64, 64);
		mag4.mirror = true;
		setRotation(mag4, 0F, 2.356194F, 0F);
		mag5 = new ModelRenderer(this, 0, 12);
		mag5.addBox(-2F, -6F, -7F, 4, 12, 2);
		mag5.setRotationPoint(0F, 0F, 0F);
		mag5.setTextureSize(64, 64);
		mag5.mirror = true;
		setRotation(mag5, 0F, 3.141593F, 0F);
		mag6 = new ModelRenderer(this, 0, 12);
		mag6.addBox(-2F, -6F, -7F, 4, 12, 2);
		mag6.setRotationPoint(0F, 0F, 0F);
		mag6.setTextureSize(64, 64);
		mag6.mirror = true;
		setRotation(mag6, 0F, -0.7853982F, 0F);
		mag7 = new ModelRenderer(this, 0, 12);
		mag7.addBox(-2F, -6F, -7F, 4, 12, 2);
		mag7.setRotationPoint(0F, 0F, 0F);
		mag7.setTextureSize(64, 64);
		mag7.mirror = true;
		setRotation(mag7, 0F, -1.570796F, 0F);
		mag8 = new ModelRenderer(this, 0, 12);
		mag8.addBox(-2F, -6F, -7F, 4, 12, 2);
		mag8.setRotationPoint(0F, 0F, 0F);
		mag8.setTextureSize(64, 64);
		mag8.mirror = true;
		setRotation(mag8, 0F, -2.356194F, 0F);
		mag2_1 = new ModelRenderer(this, 13, 12);
		mag2_1.addBox(-2F, -5F, -4F, 4, 10, 2);
		mag2_1.setRotationPoint(0F, 0F, 0F);
		mag2_1.setTextureSize(64, 64);
		mag2_1.mirror = true;
		setRotation(mag2_1, 0F, 0F, 0F);
		mag2_2 = new ModelRenderer(this, 13, 12);
		mag2_2.addBox(-2F, -5F, -4F, 4, 10, 2);
		mag2_2.setRotationPoint(0F, 0F, 0F);
		mag2_2.setTextureSize(64, 64);
		mag2_2.mirror = true;
		setRotation(mag2_2, 0F, 1.570796F, 0F);
		mag2_3 = new ModelRenderer(this, 13, 12);
		mag2_3.addBox(-2F, -5F, -4F, 4, 10, 2);
		mag2_3.setRotationPoint(0F, 0F, 0F);
		mag2_3.setTextureSize(64, 64);
		mag2_3.mirror = true;
		setRotation(mag2_3, 0F, 3.141593F, 0F);
		mag2_4 = new ModelRenderer(this, 13, 12);
		mag2_4.addBox(-2F, -5F, -4F, 4, 10, 2);
		mag2_4.setRotationPoint(0F, 0F, 0F);
		mag2_4.setTextureSize(64, 64);
		mag2_4.mirror = true;
		setRotation(mag2_4, 0F, -1.570796F, 0F);
		fil1 = new ModelRenderer(this, 0, 32);
		fil1.addBox(-7F, -7.5F, -7F, 14, 0, 14);
		fil1.setRotationPoint(0F, 0F, 0F);
		fil1.setTextureSize(64, 64);
		fil1.mirror = true;
		setRotation(fil1, 0F, 0F, 0F);
		fil2 = new ModelRenderer(this, 0, 32);
		fil2.addBox(-7F, 7.5F, -7F, 14, 0, 14);
		fil2.setRotationPoint(0F, 0F, 0F);
		fil2.setTextureSize(64, 64);
		fil2.mirror = true;
		setRotation(fil2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		output.render(0.0625F);
		shaftcube.render(0.0625F);
		middle.render(0.0625F);
		frame1.render(0.0625F);
		frame2.render(0.0625F);
		frame3.render(0.0625F);
		frame4.render(0.0625F);
		mag1.render(0.0625F);
		mag2.render(0.0625F);
		mag3.render(0.0625F);
		mag4.render(0.0625F);
		mag5.render(0.0625F);
		mag6.render(0.0625F);
		mag7.render(0.0625F);
		mag8.render(0.0625F);
		mag2_1.render(0.0625F);
		mag2_2.render(0.0625F);
		mag2_3.render(0.0625F);
		mag2_4.render(0.0625F);
		fil1.render(0.0625F);
		fil2.render(0.0625F);
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

		mag2_1.rotateAngleY = f2;
		mag2_2.rotateAngleY = f2 + (float) Math.PI * 0.5F;
		mag2_3.rotateAngleY = f2 + (float) Math.PI * 1.0F;
		mag2_4.rotateAngleY = f2 + (float) Math.PI * 1.5F;
	}

}
