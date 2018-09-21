package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPizza extends DCFoodModelBase {

	ModelRenderer pizza;
	ModelRenderer cheese1;
	ModelRenderer cheese2;
	ModelRenderer cheese3;

	public ModelPizza(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		pizza = new ModelRenderer(this, 0, 0);
		pizza.addBox(-6F, -1F, -6F, 12, 1, 12);
		pizza.setRotationPoint(0F, 0F, 0F);
		pizza.setTextureSize(64, 32);
		pizza.mirror = true;
		setRotation(pizza, 0F, 0F, 0F);
		cheese1 = new ModelRenderer(this, 14, 14);
		cheese1.addBox(1F, -1.3F, 0F, 3, 1, 3);
		cheese1.setRotationPoint(0F, 0F, 0F);
		cheese1.setTextureSize(64, 32);
		cheese1.mirror = true;
		setRotation(cheese1, 0F, 0.2617994F, 0F);
		cheese2 = new ModelRenderer(this, 28, 14);
		cheese2.addBox(1F, -1.4F, 0F, 3, 1, 3);
		cheese2.setRotationPoint(0F, 0F, 0F);
		cheese2.setTextureSize(64, 32);
		cheese2.mirror = true;
		setRotation(cheese2, 0F, 2.443461F, 0F);
		cheese3 = new ModelRenderer(this, 0, 14);
		cheese3.addBox(-5F, -1.5F, -2F, 3, 1, 3);
		cheese3.setRotationPoint(0F, 0F, 0F);
		cheese3.setTextureSize(64, 32);
		cheese3.mirror = true;
		setRotation(cheese3, 0F, 1.047198F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		pizza.render(0.0625F);
		cheese1.render(0.0625F);
		cheese2.render(0.0625F);
		cheese3.render(0.0625F);
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
