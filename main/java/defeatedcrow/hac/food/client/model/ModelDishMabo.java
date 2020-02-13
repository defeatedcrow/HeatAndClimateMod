package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishMabo extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer dish3;
	ModelRenderer dish4;
	ModelRenderer dish5;
	ModelRenderer soup;
	ModelRenderer tofu1;
	ModelRenderer tofu2;
	ModelRenderer tofu3;
	ModelRenderer tofu4;
	ModelRenderer tofu5;

	public ModelDishMabo(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-5F, -1F, -5F, 10, 1, 10);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 32);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 12);
		dish2.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 32);
		dish2.mirror = true;
		setRotation(dish2, 0.2617994F, 0F, 0F);
		dish3 = new ModelRenderer(this, 0, 12);
		dish3.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish3.setRotationPoint(0F, 0F, 0F);
		dish3.setTextureSize(64, 32);
		dish3.mirror = true;
		setRotation(dish3, 0.2617994F, 1.570796F, 0F);
		dish4 = new ModelRenderer(this, 0, 12);
		dish4.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish4.setRotationPoint(0F, 0F, 0F);
		dish4.setTextureSize(64, 32);
		dish4.mirror = true;
		setRotation(dish4, 0.2617994F, 3.141593F, 0F);
		dish5 = new ModelRenderer(this, 0, 12);
		dish5.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish5.setRotationPoint(0F, 0F, 0F);
		dish5.setTextureSize(64, 32);
		dish5.mirror = true;
		setRotation(dish5, 0.2617994F, -1.570796F, 0F);
		soup = new ModelRenderer(this, 0, 16);
		soup.addBox(-6F, -1.3F, -6F, 12, 0, 12);
		soup.setRotationPoint(0F, 0F, 0F);
		soup.setTextureSize(64, 32);
		soup.mirror = true;
		setRotation(soup, 0F, 0F, 0F);
		tofu1 = new ModelRenderer(this, 42, 0);
		tofu1.addBox(0F, -2F, 0F, 2, 1, 2);
		tofu1.setRotationPoint(0F, 0F, 0F);
		tofu1.setTextureSize(64, 32);
		tofu1.mirror = true;
		setRotation(tofu1, 0F, 0F, 0F);
		tofu2 = new ModelRenderer(this, 42, 0);
		tofu2.addBox(1F, -2F, 1F, 2, 1, 2);
		tofu2.setRotationPoint(0F, 0F, 0F);
		tofu2.setTextureSize(64, 32);
		tofu2.mirror = true;
		setRotation(tofu2, 0F, 0F, 0F);
		tofu3 = new ModelRenderer(this, 42, 0);
		tofu3.addBox(2F, -1.7F, -1F, 2, 1, 2);
		tofu3.setRotationPoint(0F, 0F, 0F);
		tofu3.setTextureSize(64, 32);
		tofu3.mirror = true;
		setRotation(tofu3, 0F, 0F, 0F);
		tofu4 = new ModelRenderer(this, 42, 0);
		tofu4.addBox(2F, -1.8F, -2F, 2, 1, 2);
		tofu4.setRotationPoint(0F, 0F, 0F);
		tofu4.setTextureSize(64, 32);
		tofu4.mirror = true;
		setRotation(tofu4, 0F, 0F, 0F);
		tofu5 = new ModelRenderer(this, 42, 0);
		tofu5.addBox(2F, -2F, 0F, 2, 1, 2);
		tofu5.setRotationPoint(0F, 0F, 0F);
		tofu5.setTextureSize(64, 32);
		tofu5.mirror = true;
		setRotation(tofu5, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		dish1.render(scale);
		dish2.render(scale);
		dish3.render(scale);
		dish4.render(scale);
		dish5.render(scale);
		soup.render(scale);
		tofu1.render(scale);
		tofu2.render(scale);
		tofu3.render(scale);
		tofu4.render(scale);
		tofu5.render(scale);
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

	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			tofu1.rotateAngleY = 0.5F + (entity.getIndividual() / 16F) * (float) (Math.PI);
			tofu2.rotateAngleY = 1.8F * (entity.getIndividual() / 16F) * (float) (Math.PI);
			tofu3.rotateAngleY = 0.2F * (entity.getIndividual() / 16F) * (float) (Math.PI);
			tofu4.rotateAngleY = 1F + (entity.getIndividual() / 16F) * (float) (Math.PI);
			tofu5.rotateAngleY = 1.25F * (entity.getIndividual() / 16F) * (float) (Math.PI);
		}
	}

}
