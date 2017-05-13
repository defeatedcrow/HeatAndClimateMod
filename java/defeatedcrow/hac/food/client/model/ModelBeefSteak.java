package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBeefSteak extends ModelSteakPlate {

	ModelRenderer steak;
	ModelRenderer potato1;
	ModelRenderer potato2;
	ModelRenderer potato3;
	ModelRenderer potato4;
	ModelRenderer veg1;
	ModelRenderer veg2;

	public ModelBeefSteak(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		steak = new ModelRenderer(this, 0, 0);
		steak.addBox(-5.5F, -2F, -3F, 8, 2, 6);
		steak.setRotationPoint(0F, -1F, 0F);
		steak.setTextureSize(64, 32);
		steak.mirror = true;
		setRotation(steak, 0F, -0.122173F, 0F);
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
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void setIndividualRotation(FoodEntityBase entity) {}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		steak.render(scale);
		potato1.render(scale);
		potato2.render(scale);
		potato3.render(scale);
		potato4.render(scale);
		veg1.render(scale);
		veg2.render(scale);
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
