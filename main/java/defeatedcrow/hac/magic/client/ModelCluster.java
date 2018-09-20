package defeatedcrow.hac.magic.client;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;

@SideOnly(Side.CLIENT)
public class ModelCluster extends DCTileModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;

	public ModelCluster() {

		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-2F, -13F, -2F, 4, 13, 4);
		Shape1.setRotationPoint(0F, 16F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.2792527F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(-2F, -10F, -1F, 4, 10, 4);
		Shape2.setRotationPoint(0F, 16F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, -0.3490659F, 1.047198F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(-1F, -6F, -2F, 4, 6, 4);
		Shape3.setRotationPoint(0F, 16F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, -0.5235988F, -0.8726646F, 0F);
		Shape4 = new ModelRenderer(this, 24, 11);
		Shape4.addBox(-5F, -1F, -5F, 5, 5, 5);
		Shape4.setRotationPoint(0F, 16F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, -0.6108652F, -1.047198F, 0F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(0F, 0F, 0F, 4, 4, 4);
		Shape5.setRotationPoint(0F, 16F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.7853982F, -0.6457718F, 0F);
		Shape6 = new ModelRenderer(this, 0, 20);
		Shape6.addBox(-1F, -6F, 0F, 3, 5, 3);
		Shape6.setRotationPoint(0F, 16F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, -1.256637F, 0.8203047F, 0F);
		Shape7 = new ModelRenderer(this, 24, 0);
		Shape7.addBox(-7F, -3F, -3F, 6, 5, 5);
		Shape7.setRotationPoint(0F, 16F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.7853982F, 0F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		Shape1.render(scale);
		Shape2.render(scale);
		Shape3.render(scale);
		Shape4.render(scale);
		Shape5.render(scale);
		Shape6.render(scale);
		Shape7.render(scale);
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

}
