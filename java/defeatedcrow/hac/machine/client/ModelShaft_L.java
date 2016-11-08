package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShaft_L extends DCTileModelBase {
	// fields
	ModelRenderer pad1;
	ModelRenderer shaft1;
	ModelRenderer shaftcube1;
	ModelRenderer pad2;
	ModelRenderer shaftcube2;
	ModelRenderer shaft2;
	ModelRenderer gear1;
	ModelRenderer gear2;

	public ModelShaft_L() {
		textureWidth = 64;
		textureHeight = 64;

		pad1 = new ModelRenderer(this, 0, 0);
		pad1.addBox(-3F, -8F, -3F, 6, 1, 6);
		pad1.setRotationPoint(0F, 0F, 0F);
		pad1.setTextureSize(64, 32);
		pad1.mirror = true;
		setRotation(pad1, 0F, 0F, 0F);
		shaft1 = new ModelRenderer(this, 0, 8);
		shaft1.addBox(-0.5F, -7.5F, -0.5F, 1, 8, 1);
		shaft1.setRotationPoint(0F, 0F, 0F);
		shaft1.setTextureSize(64, 32);
		shaft1.mirror = true;
		setRotation(shaft1, 0F, 0F, 0F);
		shaftcube1 = new ModelRenderer(this, 6, 8);
		shaftcube1.addBox(-1.5F, -6.5F, -1.5F, 3, 3, 3);
		shaftcube1.setRotationPoint(0F, 0F, 0F);
		shaftcube1.setTextureSize(64, 32);
		shaftcube1.mirror = true;
		setRotation(shaftcube1, 0F, 0F, 0F);
		pad2 = new ModelRenderer(this, 32, 0);
		pad2.addBox(-8F, -3F, -3F, 1, 6, 6);
		pad2.setRotationPoint(0F, 0F, 0F);
		pad2.setTextureSize(64, 32);
		pad2.mirror = true;
		setRotation(pad2, 0F, 0F, 0F);
		shaftcube2 = new ModelRenderer(this, 47, 0);
		shaftcube2.addBox(-7F, -1.5F, -1.5F, 3, 3, 3);
		shaftcube2.setRotationPoint(0F, 0F, 0F);
		shaftcube2.setTextureSize(64, 32);
		shaftcube2.mirror = true;
		setRotation(shaftcube2, 0F, 0F, 0F);
		shaft2 = new ModelRenderer(this, 47, 7);
		shaft2.addBox(-7F, -0.5F, -0.5F, 7, 1, 1);
		shaft2.setRotationPoint(0F, 0F, 0F);
		shaft2.setTextureSize(64, 32);
		shaft2.mirror = true;
		setRotation(shaft2, 0F, 0F, 0F);
		gear1 = new ModelRenderer(this, 0, 26);
		gear1.addBox(-4.5F, -3.5F, -4.5F, 9, 1, 9);
		gear1.setRotationPoint(0F, 0F, 0F);
		gear1.setTextureSize(64, 32);
		gear1.mirror = true;
		setRotation(gear1, 0F, 0F, 0F);
		gear2 = new ModelRenderer(this, 32, 14);
		gear2.addBox(-4F, -2.5F, -2.5F, 1, 5, 5);
		gear2.setRotationPoint(0F, 0F, 0F);
		gear2.setTextureSize(64, 32);
		gear2.mirror = true;
		setRotation(gear2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		pad1.render(0.0625F);
		shaft1.render(0.0625F);
		shaftcube1.render(0.0625F);
		pad2.render(0.0625F);
		shaftcube2.render(0.0625F);
		shaft2.render(0.0625F);
		gear1.render(0.0625F);
		gear2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	private float lastR = 0.0F;

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		if (speed < 0.5F) {
			speed = 0.0F;
		}
		float f1 = speed * 0.1F;
		f1 *= 0.01745329F;
		lastR += f1;
		if (lastR > Math.PI * 2) {
			lastR -= Math.PI * 2;
		}
		if (lastR < -Math.PI * 2) {
			lastR += Math.PI * 2;
		}

		shaftcube1.rotateAngleY = lastR;
		shaft1.rotateAngleY = lastR;
		gear1.rotateAngleY = lastR;

		shaftcube2.rotateAngleX = -lastR;
		shaft2.rotateAngleX = -lastR;
		gear2.rotateAngleX = -lastR;
	}

}
