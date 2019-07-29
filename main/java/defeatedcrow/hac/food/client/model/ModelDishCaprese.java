package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishCaprese extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer tomato1;
	ModelRenderer cheese1;
	ModelRenderer tomato2;
	ModelRenderer cheese2;
	ModelRenderer tomato3;
	ModelRenderer cheese3;

	public ModelDishCaprese(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-10F, -2F, -6F, 20, 1, 12);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 64);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 13);
		dish2.addBox(-6F, -1F, -4F, 12, 1, 8);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 64);
		dish2.mirror = true;
		setRotation(dish2, 0F, 0F, 0F);
		tomato1 = new ModelRenderer(this, 0, 32);
		tomato1.addBox(-4F, -5F, -1F, 8, 5, 1);
		tomato1.setRotationPoint(3F, -3F, 0F);
		tomato1.setTextureSize(64, 64);
		tomato1.mirror = true;
		setRotation(tomato1, 1.570796F, -1.570796F, 0F);
		cheese1 = new ModelRenderer(this, 20, 32);
		cheese1.addBox(-4F, -5F, -1F, 8, 5, 1);
		cheese1.setRotationPoint(1F, -3F, 0F);
		cheese1.setTextureSize(64, 64);
		cheese1.mirror = true;
		setRotation(cheese1, 1.256637F, -1.570796F, 0F);
		tomato2 = new ModelRenderer(this, 0, 32);
		tomato2.addBox(-4F, -5F, -1F, 8, 5, 1);
		tomato2.setRotationPoint(-1F, -3F, 0F);
		tomato2.setTextureSize(64, 64);
		tomato2.mirror = true;
		setRotation(tomato2, 1.117011F, -1.570796F, 0F);
		cheese2 = new ModelRenderer(this, 20, 32);
		cheese2.addBox(-4F, -5F, -1F, 8, 5, 1);
		cheese2.setRotationPoint(-3F, -3F, 0F);
		cheese2.setTextureSize(64, 64);
		cheese2.mirror = true;
		setRotation(cheese2, 1.047198F, -1.570796F, 0F);
		tomato3 = new ModelRenderer(this, 0, 32);
		tomato3.addBox(-4F, -5F, -1F, 8, 5, 1);
		tomato3.setRotationPoint(-5.5F, -3F, 0F);
		tomato3.setTextureSize(64, 64);
		tomato3.mirror = true;
		setRotation(tomato3, 1.047198F, -1.570796F, 0F);
		cheese3 = new ModelRenderer(this, 20, 32);
		cheese3.addBox(-4F, -5F, -1F, 8, 5, 1);
		cheese3.setRotationPoint(-8F, -3F, 0F);
		cheese3.setTextureSize(64, 64);
		cheese3.mirror = true;
		setRotation(cheese3, 1.047198F, -1.570796F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		dish1.render(scale);
		dish2.render(scale);
		tomato1.render(scale);
		cheese1.render(scale);
		tomato2.render(scale);
		cheese2.render(scale);
		tomato3.render(scale);
		cheese3.render(scale);
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
