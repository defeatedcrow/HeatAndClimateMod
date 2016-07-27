package defeatedcrow.hac.machine.client;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;

@SideOnly(Side.CLIENT)
public class ModelWindmill extends DCTileModelBase {
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

	public ModelWindmill() {
		textureWidth = 64;
		textureHeight = 64;

		output = new ModelRenderer(this, 0, 0);
		output.addBox(-3F, 7F, -3F, 6, 1, 6);
		output.setRotationPoint(0F, 0F, 0F);
		output.setTextureSize(64, 64);
		output.mirror = true;
		setRotation(output, 0F, 0F, 0F);
		shaftcube = new ModelRenderer(this, 26, 0);
		shaftcube.addBox(-1.5F, 2F, -1.5F, 3, 5, 3);
		shaftcube.setRotationPoint(0F, 0F, 0F);
		shaftcube.setTextureSize(64, 64);
		shaftcube.mirror = true;
		setRotation(shaftcube, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 40, 0);
		middle.addBox(-2F, -2F, -2F, 4, 4, 4);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.setTextureSize(64, 64);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		winglod1 = new ModelRenderer(this, 0, 10);
		winglod1.addBox(0F, -1F, -1F, 20, 2, 2);
		winglod1.setRotationPoint(0F, 0F, 0F);
		winglod1.setTextureSize(64, 64);
		winglod1.mirror = true;
		setRotation(winglod1, 0F, 0F, 0F);
		winglod2 = new ModelRenderer(this, 0, 10);
		winglod2.addBox(0F, -1F, -1F, 20, 2, 2);
		winglod2.setRotationPoint(0F, 0F, 0F);
		winglod2.setTextureSize(64, 64);
		winglod2.mirror = true;
		setRotation(winglod2, 0F, 1.570796F, 0F);
		winglod3 = new ModelRenderer(this, 0, 10);
		winglod3.addBox(0F, -1F, -1F, 20, 2, 2);
		winglod3.setRotationPoint(0F, 0F, 0F);
		winglod3.setTextureSize(64, 64);
		winglod3.mirror = true;
		setRotation(winglod3, 0F, 3.141593F, 0F);
		winglod4 = new ModelRenderer(this, 0, 10);
		winglod4.addBox(0F, -1F, -1F, 20, 2, 2);
		winglod4.setRotationPoint(0F, 0F, 0F);
		winglod4.setTextureSize(64, 64);
		winglod4.mirror = true;
		setRotation(winglod4, 0F, -1.570796F, 0F);
		wingcloth1 = new ModelRenderer(this, 0, 16);
		wingcloth1.addBox(3F, -1F, 0F, 17, 1, 5);
		wingcloth1.setRotationPoint(0F, 0F, 0F);
		wingcloth1.setTextureSize(64, 64);
		wingcloth1.mirror = true;
		setRotation(wingcloth1, -0.2094395F, 0F, 0F);
		wingcloth2 = new ModelRenderer(this, 0, 16);
		wingcloth2.addBox(3F, -1F, 0F, 17, 1, 5);
		wingcloth2.setRotationPoint(0F, 0F, 0F);
		wingcloth2.setTextureSize(64, 64);
		wingcloth2.mirror = true;
		setRotation(wingcloth2, -0.2094395F, 1.570796F, 0F);
		wingcloth3 = new ModelRenderer(this, 0, 16);
		wingcloth3.addBox(3F, -1F, 0F, 17, 1, 5);
		wingcloth3.setRotationPoint(0F, 0F, 0F);
		wingcloth3.setTextureSize(64, 64);
		wingcloth3.mirror = true;
		setRotation(wingcloth3, -0.2094395F, 3.141593F, 0F);
		wingcloth4 = new ModelRenderer(this, 0, 16);
		wingcloth4.addBox(3F, -1F, 0F, 17, 1, 5);
		wingcloth4.setRotationPoint(0F, 0F, 0F);
		wingcloth4.setTextureSize(64, 64);
		wingcloth4.mirror = true;
		setRotation(wingcloth4, -0.2094395F, -1.570796F, 0F);
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
		if (speed < 0.5F) {
			speed = 0.0F;
		}
		float f1 = speed * 0.1F;
		f1 *= 0.01745329F;

		shaftcube.rotateAngleY += f1;
		middle.rotateAngleY += f1;
		winglod1.rotateAngleY += f1;
		winglod2.rotateAngleY += f1;
		winglod3.rotateAngleY += f1;
		winglod4.rotateAngleY += f1;
		wingcloth1.rotateAngleY += f1;
		wingcloth2.rotateAngleY += f1;
		wingcloth3.rotateAngleY += f1;
		wingcloth4.rotateAngleY += f1;
	}

}
