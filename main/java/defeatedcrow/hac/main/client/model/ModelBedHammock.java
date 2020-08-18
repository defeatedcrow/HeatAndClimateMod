package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBedHammock extends DCTileModelBase {
	// fields
	ModelRenderer matress;
	ModelRenderer pillow;
	ModelRenderer leg11;
	ModelRenderer leg12;
	ModelRenderer leg3;
	ModelRenderer leg4;

	public ModelBedHammock() {
		textureWidth = 128;
		textureHeight = 64;

		matress = new ModelRenderer(this, 0, 44);
		matress.addBox(-7F, 0F, -8F, 30, 1, 16);
		matress.setRotationPoint(0F, 0F, 0F);
		matress.setTextureSize(64, 32);
		matress.mirror = true;
		setRotation(matress, 0F, 1.570796F, 0F);
		pillow = new ModelRenderer(this, 6, 0);
		pillow.addBox(-4F, -1.2F, 1F, 8, 2, 6);
		pillow.setRotationPoint(0F, 0F, 0F);
		pillow.setTextureSize(64, 32);
		pillow.mirror = true;
		setRotation(pillow, 0.1396263F, 0F, 0F);
		leg11 = new ModelRenderer(this, 38, 0);
		leg11.addBox(0F, 0F, -10F, 1, 1, 12);
		leg11.setRotationPoint(-1.5F, -2.5F, 13.5F);
		leg11.setTextureSize(64, 32);
		leg11.mirror = true;
		setRotation(leg11, 0.2617994F, 0.7853982F, 0F);
		leg12 = new ModelRenderer(this, 38, 0);
		leg12.addBox(0F, 0F, -10F, 1, 1, 12);
		leg12.setRotationPoint(0.5F, -2.5F, 13F);
		leg12.setTextureSize(64, 32);
		leg12.mirror = true;
		setRotation(leg12, 0.2617994F, -0.7853982F, 0F);
		leg3 = new ModelRenderer(this, 66, 0);
		leg3.addBox(-0.5F, -4.5F, -0.5F, 1, 11, 1);
		leg3.setRotationPoint(0F, -5.5F, 20F);
		leg3.setTextureSize(128, 64);
		leg3.mirror = true;
		setRotation(leg3, -1.047198F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 0);
		leg4.addBox(-0.5F, -8F, -0.5F, 1, 16, 1);
		leg4.setRotationPoint(0F, 0F, 24F);
		leg4.setTextureSize(128, 64);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		matress.render(0.0625F);
		pillow.render(0.0625F);
	}

	public void renderLeg1() {
		leg11.render(0.0625F);
		leg12.render(0.0625F);
	}

	public void renderLeg2() {
		leg3.render(0.0625F);
	}

	public void renderLeg3() {
		leg4.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}