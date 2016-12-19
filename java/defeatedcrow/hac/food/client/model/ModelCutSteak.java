package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCutSteak extends ModelSteakPlate {

	ModelRenderer potato1;
	ModelRenderer potato2;
	ModelRenderer potato3;
	ModelRenderer potato4;
	ModelRenderer veg1;
	ModelRenderer veg2;
	ModelRenderer steak1;
	ModelRenderer steak2;
	ModelRenderer steak3;

	public ModelCutSteak(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		potato1 = new ModelRenderer(this, 0, 10);
		potato1.addBox(-0.3F, -1F, 2.2F, 1, 1, 4);
		potato1.setRotationPoint(0F, -1F, 0F);
		potato1.setTextureSize(64, 32);
		potato1.mirror = true;
		setRotation(potato1, 0F, 0.7853982F, 0F);
		potato2 = new ModelRenderer(this, 0, 10);
		potato2.addBox(0.8F, -1F, 1.8F, 1, 1, 4);
		potato2.setRotationPoint(0F, -1F, 0F);
		potato2.setTextureSize(64, 32);
		potato2.mirror = true;
		setRotation(potato2, 0F, 0.7853982F, 0F);
		potato3 = new ModelRenderer(this, 0, 10);
		potato3.addBox(4F, -2.2F, -2F, 1, 1, 4);
		potato3.setRotationPoint(0F, -1F, 0F);
		potato3.setTextureSize(64, 32);
		potato3.mirror = true;
		setRotation(potato3, 0.4363323F, -0.5235988F, 0F);
		potato4 = new ModelRenderer(this, 0, 10);
		potato4.addBox(2.8F, -2F, -2F, 1, 1, 4);
		potato4.setRotationPoint(0F, -1F, 0F);
		potato4.setTextureSize(64, 32);
		potato4.mirror = true;
		setRotation(potato4, 0.4363323F, -0.3490659F, 0F);
		veg1 = new ModelRenderer(this, 12, 10);
		veg1.addBox(4F, -2F, -1F, 2, 2, 2);
		veg1.setRotationPoint(0F, -1F, 0F);
		veg1.setTextureSize(64, 32);
		veg1.mirror = true;
		setRotation(veg1, 0F, 0.2617994F, 0F);
		veg2 = new ModelRenderer(this, 22, 10);
		veg2.addBox(4F, -2F, -2.5F, 2, 2, 2);
		veg2.setRotationPoint(0F, -1F, 0F);
		veg2.setTextureSize(64, 32);
		veg2.mirror = true;
		setRotation(veg2, 0F, 0.4014257F, 0F);
		steak1 = new ModelRenderer(this, 0, 0);
		steak1.addBox(-3F, -1F, 0F, 6, 2, 3);
		steak1.setRotationPoint(0F, -1F, 0F);
		steak1.setTextureSize(64, 32);
		steak1.mirror = true;
		setRotation(steak1, 0.7853982F, 1.256637F, 0F);
		steak2 = new ModelRenderer(this, 0, 0);
		steak2.addBox(-3F, -3F, -1.3F, 6, 2, 3);
		steak2.setRotationPoint(0F, -1F, 0F);
		steak2.setTextureSize(64, 32);
		steak2.mirror = true;
		setRotation(steak2, 0.7853982F, 1.256637F, 0F);
		steak3 = new ModelRenderer(this, 0, 0);
		steak3.addBox(-3F, -4.5F, -3F, 6, 2, 3);
		steak3.setRotationPoint(0F, -1F, 0F);
		steak3.setTextureSize(64, 32);
		steak3.mirror = true;
		setRotation(steak3, 0.7853982F, 1.256637F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		potato1.render(scale);
		potato2.render(scale);
		potato3.render(scale);
		potato4.render(scale);
		veg1.render(scale);
		veg2.render(scale);
		steak1.render(scale);
		steak2.render(scale);
		steak3.render(scale);
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
