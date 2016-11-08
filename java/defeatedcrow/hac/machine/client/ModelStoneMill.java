package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelStoneMill extends DCTileModelBase {
	// fields
	ModelRenderer stone1;
	ModelRenderer stone2;
	ModelRenderer pad1;
	ModelRenderer plate2;
	ModelRenderer plate3;

	public ModelStoneMill() {
		textureWidth = 64;
		textureHeight = 64;

		stone1 = new ModelRenderer(this, 0, 0);
		stone1.addBox(-8F, 1F, -8F, 16, 7, 16);
		stone1.setRotationPoint(0F, 0F, 0F);
		stone1.setTextureSize(64, 32);
		stone1.mirror = true;
		setRotation(stone1, 0F, 0F, 0F);
		stone2 = new ModelRenderer(this, 0, 0);
		stone2.addBox(-8F, -6.3F, -8F, 16, 7, 16);
		stone2.setRotationPoint(0F, 0F, 0F);
		stone2.setTextureSize(64, 32);
		stone2.mirror = true;
		setRotation(stone2, 0F, 0F, 0F);
		pad1 = new ModelRenderer(this, 0, 24);
		pad1.addBox(-3F, -8F, -3F, 6, 1, 6);
		pad1.setRotationPoint(0F, 0F, 0F);
		pad1.setTextureSize(64, 64);
		pad1.mirror = true;
		setRotation(pad1, 0F, 0F, 0F);
		plate2 = new ModelRenderer(this, 0, 32);
		plate2.addBox(-5F, -7F, -5F, 10, 1, 10);
		plate2.setRotationPoint(0F, 0F, 0F);
		plate2.setTextureSize(64, 64);
		plate2.mirror = true;
		setRotation(plate2, 0F, 0F, 0F);
		plate3 = new ModelRenderer(this, 0, 44);
		plate3.addBox(-3F, 0F, -3F, 6, 1, 6);
		plate3.setRotationPoint(0F, 0F, 0F);
		plate3.setTextureSize(64, 64);
		plate3.mirror = true;
		setRotation(plate3, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		stone1.render(0.0625F);
		stone2.render(0.0625F);
		pad1.render(0.0625F);
		plate2.render(0.0625F);
		plate2.render(0.0625F);
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

		stone2.rotateAngleY = lastR;
		plate2.rotateAngleY = lastR;
	}

}
