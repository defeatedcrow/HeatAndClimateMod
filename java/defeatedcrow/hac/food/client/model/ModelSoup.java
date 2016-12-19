package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSoup extends ModelSteakPlate {

	ModelRenderer soup;
	ModelRenderer veg1;
	ModelRenderer veg2;
	ModelRenderer veg3;
	ModelRenderer veg4;
	ModelRenderer veg5;
	ModelRenderer veg6;

	public ModelSoup(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		soup = new ModelRenderer(this, 0, 0);
		soup.addBox(-6F, -1F, -5F, 12, 0, 10);
		soup.setRotationPoint(0F, -1F, 0F);
		soup.setTextureSize(64, 32);
		soup.mirror = true;
		setRotation(soup, 0F, 0F, 0F);
		veg1 = new ModelRenderer(this, 0, 12);
		veg1.addBox(-1.5F, -2F, -1.5F, 3, 2, 3);
		veg1.setRotationPoint(-3F, -1F, -2F);
		veg1.setTextureSize(64, 32);
		veg1.mirror = true;
		setRotation(veg1, 0F, 0.2617994F, 0F);
		veg2 = new ModelRenderer(this, 13, 12);
		veg2.addBox(-1.5F, -2F, -1.5F, 3, 2, 3);
		veg2.setRotationPoint(-1F, -1F, 2F);
		veg2.setTextureSize(64, 32);
		veg2.mirror = true;
		setRotation(veg2, 0F, -0.2443461F, 0F);
		veg3 = new ModelRenderer(this, 26, 12);
		veg3.addBox(-1.5F, -2F, -1.5F, 3, 2, 3);
		veg3.setRotationPoint(3F, -1F, 0F);
		veg3.setTextureSize(64, 32);
		veg3.mirror = true;
		setRotation(veg3, 0F, 0.1745329F, 0F);
		veg4 = new ModelRenderer(this, 0, 18);
		veg4.addBox(-1F, -1.5F, -1F, 2, 2, 2);
		veg4.setRotationPoint(1F, -1F, -3F);
		veg4.setTextureSize(64, 32);
		veg4.mirror = true;
		setRotation(veg4, 0F, -0.0872665F, 0F);
		veg5 = new ModelRenderer(this, 9, 18);
		veg5.addBox(-1F, -1.5F, -1F, 2, 2, 2);
		veg5.setRotationPoint(-4F, -1F, 2F);
		veg5.setTextureSize(64, 32);
		veg5.mirror = true;
		setRotation(veg5, 0F, -0.6457718F, 0F);
		veg6 = new ModelRenderer(this, 18, 18);
		veg6.addBox(-1F, -1.5F, -1F, 2, 2, 2);
		veg6.setRotationPoint(3F, -1F, 3F);
		veg6.setTextureSize(64, 32);
		veg6.mirror = true;
		setRotation(veg6, 0F, 0.4886922F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		soup.render(scale);
		veg1.render(scale);
		veg2.render(scale);
		veg3.render(scale);
		veg4.render(scale);
		veg5.render(scale);
		veg6.render(scale);
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
