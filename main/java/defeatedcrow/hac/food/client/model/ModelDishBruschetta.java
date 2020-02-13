package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishBruschetta extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer bread1;
	ModelRenderer bread2;
	ModelRenderer bread3;
	ModelRenderer up1;
	ModelRenderer up2;
	ModelRenderer up3;
	ModelRenderer up21;
	ModelRenderer up22;
	ModelRenderer up23;
	ModelRenderer up24;
	ModelRenderer up25;
	ModelRenderer up26;

	public ModelDishBruschetta(boolean baked) {
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
		bread1 = new ModelRenderer(this, 0, 32);
		bread1.addBox(-2.5F, -3F, -3.5F, 5, 2, 7);
		bread1.setRotationPoint(-5.5F, -1F, 0F);
		bread1.setTextureSize(64, 64);
		bread1.mirror = true;
		setRotation(bread1, 0F, 0.2617994F, 0F);
		bread2 = new ModelRenderer(this, 0, 32);
		bread2.addBox(-2.5F, -1F, -3.5F, 5, 2, 7);
		bread2.setRotationPoint(0F, -3F, 0F);
		bread2.setTextureSize(64, 64);
		bread2.mirror = true;
		setRotation(bread2, 0F, 0.2617994F, 0F);
		bread3 = new ModelRenderer(this, 0, 32);
		bread3.addBox(-2.5F, -1F, -3.5F, 5, 2, 7);
		bread3.setRotationPoint(5.5F, -3F, 0F);
		bread3.setTextureSize(64, 64);
		bread3.mirror = true;
		setRotation(bread3, 0F, 0.2617994F, 0F);
		up1 = new ModelRenderer(this, 26, 32);
		up1.addBox(-2F, -5F, -3F, 4, 1, 6);
		up1.setRotationPoint(-5.5F, 0F, 0F);
		up1.setTextureSize(64, 64);
		up1.mirror = true;
		setRotation(up1, 0F, 0.2617994F, 0F);
		up2 = new ModelRenderer(this, 26, 40);
		up2.addBox(-2F, -5F, -3F, 4, 1, 6);
		up2.setRotationPoint(0F, 0F, 0F);
		up2.setTextureSize(64, 64);
		up2.mirror = true;
		setRotation(up2, 0F, 0.2617994F, 0F);
		up3 = new ModelRenderer(this, 26, 48);
		up3.addBox(-2F, -5F, -3F, 4, 1, 6);
		up3.setRotationPoint(5.5F, 0F, 0F);
		up3.setTextureSize(64, 64);
		up3.mirror = true;
		setRotation(up3, 0F, 0.2617994F, 0F);
		up21 = new ModelRenderer(this, 48, 32);
		up21.addBox(-1F, -1F, -1F, 1, 1, 1);
		up21.setRotationPoint(-5.5F, -5F, 0F);
		up21.setTextureSize(64, 64);
		up21.mirror = true;
		setRotation(up21, 0F, 0F, 0F);
		up22 = new ModelRenderer(this, 48, 35);
		up22.addBox(0F, -1F, 0F, 1, 1, 1);
		up22.setRotationPoint(-5.5F, -5F, 0F);
		up22.setTextureSize(64, 64);
		up22.mirror = true;
		setRotation(up22, 0F, 0F, 0F);
		up23 = new ModelRenderer(this, 48, 40);
		up23.addBox(0F, -1F, 0F, 1, 1, 1);
		up23.setRotationPoint(0F, -5F, 0F);
		up23.setTextureSize(64, 64);
		up23.mirror = true;
		setRotation(up23, 0F, 0F, 0F);
		up24 = new ModelRenderer(this, 48, 43);
		up24.addBox(-1F, -1F, -1F, 1, 1, 1);
		up24.setRotationPoint(0F, -5F, 0F);
		up24.setTextureSize(64, 64);
		up24.mirror = true;
		setRotation(up24, 0F, 0F, 0F);
		up25 = new ModelRenderer(this, 48, 48);
		up25.addBox(0F, -1F, 0F, 1, 1, 1);
		up25.setRotationPoint(5.5F, -5F, 0F);
		up25.setTextureSize(64, 64);
		up25.mirror = true;
		setRotation(up25, 0F, 0F, 0F);
		up26 = new ModelRenderer(this, 48, 51);
		up26.addBox(-1F, -1F, -1F, 1, 1, 1);
		up26.setRotationPoint(5.5F, -5F, 0F);
		up26.setTextureSize(64, 64);
		up26.mirror = true;
		setRotation(up26, 0F, 0F, 0F);
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
		bread1.render(scale);
		bread2.render(scale);
		bread3.render(scale);
		up1.render(scale);
		up2.render(scale);
		up3.render(scale);
		up21.render(scale);
		up22.render(scale);
		up23.render(scale);
		up24.render(scale);
		up25.render(scale);
		up26.render(scale);
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
