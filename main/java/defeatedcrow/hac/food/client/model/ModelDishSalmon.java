package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishSalmon extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer bread1;
	ModelRenderer bread2;
	ModelRenderer bread3;

	public ModelDishSalmon(boolean baked) {
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
		bread1.addBox(-2.5F, -5F, -2.5F, 5, 4, 5);
		bread1.setRotationPoint(-5.5F, -1F, 0F);
		bread1.setTextureSize(64, 64);
		bread1.mirror = true;
		setRotation(bread1, 0F, 0.2617994F, 0F);
		bread2 = new ModelRenderer(this, 0, 32);
		bread2.addBox(-2.5F, -3F, -2.5F, 5, 4, 5);
		bread2.setRotationPoint(0F, -3F, 0F);
		bread2.setTextureSize(64, 64);
		bread2.mirror = true;
		setRotation(bread2, 0F, 0.2617994F, 0F);
		bread3 = new ModelRenderer(this, 0, 32);
		bread3.addBox(-2.5F, -3F, -2.5F, 5, 4, 5);
		bread3.setRotationPoint(5.5F, -3F, 0F);
		bread3.setTextureSize(64, 64);
		bread3.mirror = true;
		setRotation(bread3, 0F, 0.2617994F, 0F);
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
