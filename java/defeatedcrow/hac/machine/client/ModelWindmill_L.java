package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWindmill_L extends DCTileModelBase {
	// fields
	ModelRenderer output;
	ModelRenderer shaftcube;
	ModelRenderer middle;
	ModelRenderer winglod1;
	ModelRenderer winglod2;
	ModelRenderer winglod3;
	ModelRenderer winglod4;
	ModelRenderer wingcloth1;
	ModelRenderer wingcloth2;
	ModelRenderer wingcloth3;
	ModelRenderer wingcloth4;

	public ModelWindmill_L() {
		textureWidth = 128;
		textureHeight = 32;

		output = new ModelRenderer(this, 0, 0);
		output.addBox(-4F, 6F, -4F, 8, 2, 8);
		output.setRotationPoint(0F, 0F, 0F);
		output.setTextureSize(128, 32);
		output.mirror = true;
		setRotation(output, 0F, 0F, 0F);
		shaftcube = new ModelRenderer(this, 34, 0);
		shaftcube.addBox(-2F, 0F, -2F, 4, 6, 4);
		shaftcube.setRotationPoint(0F, 0F, 0F);
		shaftcube.setTextureSize(128, 32);
		shaftcube.mirror = true;
		setRotation(shaftcube, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 52, 0);
		middle.addBox(-3F, -4F, -3F, 6, 4, 6);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.setTextureSize(128, 32);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		winglod1 = new ModelRenderer(this, 0, 12);
		winglod1.addBox(3F, -3F, -1.5F, 45, 3, 3);
		winglod1.setRotationPoint(0F, 0F, 0F);
		winglod1.setTextureSize(128, 32);
		winglod1.mirror = true;
		setRotation(winglod1, 0F, 0F, 0F);
		winglod2 = new ModelRenderer(this, 0, 12);
		winglod2.addBox(3F, -3F, -1.5F, 45, 3, 3);
		winglod2.setRotationPoint(0F, 0F, 0F);
		winglod2.setTextureSize(128, 32);
		winglod2.mirror = true;
		setRotation(winglod2, 0F, 1.570796F, 0F);
		winglod3 = new ModelRenderer(this, 0, 12);
		winglod3.addBox(3F, -3F, -1.5F, 45, 3, 3);
		winglod3.setRotationPoint(0F, 0F, 0F);
		winglod3.setTextureSize(128, 32);
		winglod3.mirror = true;
		setRotation(winglod3, 0F, 3.141593F, 0F);
		winglod4 = new ModelRenderer(this, 0, 12);
		winglod4.addBox(3F, -3F, -1.5F, 45, 3, 3);
		winglod4.setRotationPoint(0F, 0F, 0F);
		winglod4.setTextureSize(128, 32);
		winglod4.mirror = true;
		setRotation(winglod4, 0F, -1.570796F, 0F);
		wingcloth1 = new ModelRenderer(this, 0, 20);
		wingcloth1.addBox(5F, -2F, 1F, 43, 1, 8);
		wingcloth1.setRotationPoint(0F, 0F, 0F);
		wingcloth1.setTextureSize(128, 32);
		wingcloth1.mirror = true;
		setRotation(wingcloth1, -0.2617994F, 0F, 0F);
		wingcloth2 = new ModelRenderer(this, 0, 20);
		wingcloth2.addBox(5F, -2F, 1F, 43, 1, 8);
		wingcloth2.setRotationPoint(0F, 0F, 0F);
		wingcloth2.setTextureSize(128, 32);
		wingcloth2.mirror = true;
		setRotation(wingcloth2, -0.2617994F, 1.570796F, 0F);
		wingcloth3 = new ModelRenderer(this, 0, 20);
		wingcloth3.addBox(5F, -2F, 1F, 43, 1, 8);
		wingcloth3.setRotationPoint(0F, 0F, 0F);
		wingcloth3.setTextureSize(128, 32);
		wingcloth3.mirror = true;
		setRotation(wingcloth3, -0.2617994F, 3.141593F, 0F);
		wingcloth4 = new ModelRenderer(this, 0, 20);
		wingcloth4.addBox(5F, -2F, 1F, 43, 1, 8);
		wingcloth4.setRotationPoint(0F, 0F, 0F);
		wingcloth4.setTextureSize(128, 32);
		wingcloth4.mirror = true;
		setRotation(wingcloth4, -0.2617994F, -1.570796F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		output.render(0.0625F);
		shaftcube.render(0.0625F);
		middle.render(0.0625F);
		winglod1.render(0.0625F);
		winglod2.render(0.0625F);
		winglod3.render(0.0625F);
		winglod4.render(0.0625F);
		wingcloth1.render(0.0625F);
		wingcloth2.render(0.0625F);
		wingcloth3.render(0.0625F);
		wingcloth4.render(0.0625F);
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
		winglod1.rotateAngleY = f2;
		winglod2.rotateAngleY = f2 + (float) Math.PI * 0.5F;
		winglod3.rotateAngleY = f2 + (float) Math.PI;
		winglod4.rotateAngleY = f2 + (float) Math.PI * 1.5F;
		wingcloth1.rotateAngleY = f2;
		wingcloth2.rotateAngleY = f2 + (float) Math.PI * 0.5F;
		wingcloth3.rotateAngleY = f2 + (float) Math.PI;
		wingcloth4.rotateAngleY = f2 + (float) Math.PI * 1.5F;
	}

}
