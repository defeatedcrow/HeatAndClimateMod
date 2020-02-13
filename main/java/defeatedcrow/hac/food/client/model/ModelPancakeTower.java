package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPancakeTower extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer dish3;
	ModelRenderer dish4;
	ModelRenderer dish5;
	ModelRenderer cake1;
	ModelRenderer cake2;
	ModelRenderer cake3;
	ModelRenderer cake4;
	ModelRenderer honey1;
	ModelRenderer honey2;
	ModelRenderer butter;
	ModelRenderer cream1;
	ModelRenderer cream2;
	ModelRenderer cream3;
	ModelRenderer cream4;
	ModelRenderer cream5;
	ModelRenderer cream6;

	public ModelPancakeTower(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-5F, -1F, -5F, 10, 1, 10);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 64);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 12);
		dish2.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 64);
		dish2.mirror = true;
		setRotation(dish2, 0.2617994F, 0F, 0F);
		dish3 = new ModelRenderer(this, 0, 12);
		dish3.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish3.setRotationPoint(0F, 0F, 0F);
		dish3.setTextureSize(64, 64);
		dish3.mirror = true;
		setRotation(dish3, 0.2617994F, 1.570796F, 0F);
		dish4 = new ModelRenderer(this, 0, 12);
		dish4.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish4.setRotationPoint(0F, 0F, 0F);
		dish4.setTextureSize(64, 64);
		dish4.mirror = true;
		setRotation(dish4, 0.2617994F, 3.141593F, 0F);
		dish5 = new ModelRenderer(this, 0, 12);
		dish5.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish5.setRotationPoint(0F, 0F, 0F);
		dish5.setTextureSize(64, 64);
		dish5.mirror = true;
		setRotation(dish5, 0.2617994F, -1.570796F, 0F);
		cake1 = new ModelRenderer(this, 0, 17);
		cake1.addBox(-4.5F, -3F, -4.5F, 9, 2, 9);
		cake1.setRotationPoint(0F, 0F, 0F);
		cake1.setTextureSize(64, 64);
		cake1.mirror = true;
		setRotation(cake1, 0F, 0.1115358F, 0F);
		cake2 = new ModelRenderer(this, 0, 17);
		cake2.addBox(-4.7F, -5F, -4.5F, 9, 2, 9);
		cake2.setRotationPoint(0F, 0F, 0F);
		cake2.setTextureSize(64, 64);
		cake2.mirror = true;
		setRotation(cake2, 0F, -0.0743572F, 0F);
		cake3 = new ModelRenderer(this, 0, 17);
		cake3.addBox(-4.5F, -7F, -4.6F, 9, 2, 9);
		cake3.setRotationPoint(0F, 0F, 0F);
		cake3.setTextureSize(64, 64);
		cake3.mirror = true;
		setRotation(cake3, 0F, 0.1115358F, 0F);
		cake4 = new ModelRenderer(this, 0, 17);
		cake4.addBox(-4.5F, -9F, -4.5F, 9, 2, 9);
		cake4.setRotationPoint(0F, 0F, 0F);
		cake4.setTextureSize(64, 64);
		cake4.mirror = true;
		setRotation(cake4, 0F, 0F, 0F);
		honey1 = new ModelRenderer(this, 0, 30);
		honey1.addBox(-2F, -9.5F, -2F, 6, 1, 6);
		honey1.setRotationPoint(0F, 0F, 0F);
		honey1.setTextureSize(64, 64);
		honey1.mirror = true;
		setRotation(honey1, 0F, 0F, 0F);
		honey2 = new ModelRenderer(this, 0, 39);
		honey2.addBox(1F, -9.2F, 3.8F, 2, 8, 1);
		honey2.setRotationPoint(0F, 0F, 0F);
		honey2.setTextureSize(64, 64);
		honey2.mirror = true;
		setRotation(honey2, 0F, 0F, 0F);
		butter = new ModelRenderer(this, 7, 39);
		butter.addBox(-1F, -10F, -1F, 2, 1, 2);
		butter.setRotationPoint(0F, 0F, 0F);
		butter.setTextureSize(64, 64);
		butter.mirror = true;
		setRotation(butter, 0F, 0F, 0F);
		cream1 = new ModelRenderer(this, 26, 30);
		cream1.addBox(-5.5F, -3F, -6F, 2, 2, 2);
		cream1.setRotationPoint(0F, 0F, 0F);
		cream1.setTextureSize(64, 64);
		cream1.mirror = true;
		setRotation(cream1, 0F, 0F, 0F);
		cream2 = new ModelRenderer(this, 26, 30);
		cream2.addBox(3.5F, -3F, 4F, 2, 2, 2);
		cream2.setRotationPoint(0F, 0F, 0F);
		cream2.setTextureSize(64, 64);
		cream2.mirror = true;
		setRotation(cream2, 0F, 0F, 0F);
		cream3 = new ModelRenderer(this, 26, 36);
		cream3.addBox(-2F, -2F, -6F, 1, 1, 1);
		cream3.setRotationPoint(0F, 0F, 0F);
		cream3.setTextureSize(64, 64);
		cream3.mirror = true;
		setRotation(cream3, 0F, 0.0743572F, 0F);
		cream4 = new ModelRenderer(this, 26, 36);
		cream4.addBox(1F, -2F, 5F, 1, 1, 1);
		cream4.setRotationPoint(0F, 0F, 0F);
		cream4.setTextureSize(64, 64);
		cream4.mirror = true;
		setRotation(cream4, 0F, 0.0743572F, 0F);
		cream5 = new ModelRenderer(this, 26, 36);
		cream5.addBox(0F, -2F, -6F, 1, 1, 1);
		cream5.setRotationPoint(0F, 0F, 0F);
		cream5.setTextureSize(64, 64);
		cream5.mirror = true;
		setRotation(cream5, 0F, 0.0743572F, 0F);
		cream6 = new ModelRenderer(this, 26, 36);
		cream6.addBox(-1F, -2F, 5F, 1, 1, 1);
		cream6.setRotationPoint(0F, 0F, 0F);
		cream6.setTextureSize(64, 64);
		cream6.mirror = true;
		setRotation(cream6, 0F, 0.0743572F, 0F);
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
		dish3.render(scale);
		dish4.render(scale);
		dish5.render(scale);
		cake1.render(scale);
		cake2.render(scale);
		cake3.render(scale);
		cake4.render(scale);
		butter.render(scale);
		cream1.render(scale);
		cream2.render(scale);
		cream3.render(scale);
		cream4.render(scale);
		cream5.render(scale);
		cream6.render(scale);
	}

	public void renderSauce() {
		honey1.render(0.0625F);
		honey2.render(0.0625F);
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
