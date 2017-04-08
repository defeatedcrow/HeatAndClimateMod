package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelStewBowl extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer dishF1;
	ModelRenderer dishB1;
	ModelRenderer dishL1;
	ModelRenderer dishR1;
	ModelRenderer dish3;
	ModelRenderer dish4;
	ModelRenderer soup1;
	ModelRenderer soup2;
	ModelRenderer in1;
	ModelRenderer in2;
	ModelRenderer in3;
	ModelRenderer in4;
	ModelRenderer in5;
	ModelRenderer in6;

	public ModelStewBowl(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		dish1 = new ModelRenderer(this, 0, 24);
		dish1.addBox(-4F, 0F, -4F, 8, 1, 8);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 32);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 0);
		dish2.addBox(-5F, -1F, -5F, 10, 1, 10);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 32);
		dish2.mirror = true;
		setRotation(dish2, 0F, 0F, 0F);
		dishF1 = new ModelRenderer(this, 0, 18);
		dishF1.addBox(-5F, -4F, -6F, 10, 4, 1);
		dishF1.setRotationPoint(0F, 0F, 0F);
		dishF1.setTextureSize(64, 32);
		dishF1.mirror = true;
		setRotation(dishF1, 0F, 0F, 0F);
		dishB1 = new ModelRenderer(this, 0, 18);
		dishB1.addBox(-5F, -4F, -6F, 10, 4, 1);
		dishB1.setRotationPoint(0F, 0F, 0F);
		dishB1.setTextureSize(64, 32);
		dishB1.mirror = true;
		setRotation(dishB1, 0F, 3.141593F, 0F);
		dishL1 = new ModelRenderer(this, 0, 12);
		dishL1.addBox(-6F, -4F, -6F, 12, 4, 1);
		dishL1.setRotationPoint(0F, 0F, 0F);
		dishL1.setTextureSize(64, 32);
		dishL1.mirror = true;
		setRotation(dishL1, 0F, 4.712389F, 0F);
		dishR1 = new ModelRenderer(this, 0, 12);
		dishR1.addBox(-6F, -4F, -6F, 12, 4, 1);
		dishR1.setRotationPoint(0F, 0F, 0F);
		dishR1.setTextureSize(64, 32);
		dishR1.mirror = true;
		setRotation(dishR1, 0F, 1.570796F, 0F);
		dish3 = new ModelRenderer(this, 30, 12);
		dish3.addBox(-7F, -4F, -2F, 1, 1, 4);
		dish3.setRotationPoint(0F, 0F, 0F);
		dish3.setTextureSize(64, 32);
		dish3.mirror = true;
		setRotation(dish3, 0F, 0F, 0F);
		dish4 = new ModelRenderer(this, 30, 12);
		dish4.addBox(6F, -4F, -2F, 1, 1, 4);
		dish4.setRotationPoint(0F, 0F, 0F);
		dish4.setTextureSize(64, 32);
		dish4.mirror = true;
		setRotation(dish4, 0F, 0F, 0F);
		soup1 = new ModelRenderer(this, 0, 12);
		soup1.addBox(-5F, -2F, -5F, 10, 0, 10);
		soup1.setRotationPoint(0F, 0F, 0F);
		soup1.setTextureSize(64, 64);
		soup1.mirror = true;
		setRotation(soup1, 0F, 0F, 0F);
		soup2 = new ModelRenderer(this, 0, 0);
		soup2.addBox(-5F, -3F, -5F, 10, 0, 10);
		soup2.setRotationPoint(0F, 0F, 0F);
		soup2.setTextureSize(64, 64);
		soup2.mirror = true;
		setRotation(soup2, 0F, 0F, 0F);
		in1 = new ModelRenderer(this, 32, 0);
		in1.addBox(1F, -3.3F, 1F, 2, 2, 2);
		in1.setRotationPoint(0F, 0F, 0F);
		in1.setTextureSize(64, 64);
		in1.mirror = true;
		setRotation(in1, 0.0523599F, 0.2617994F, 0F);
		in1.mirror = false;
		in2 = new ModelRenderer(this, 41, 0);
		in2.addBox(-0.5F, -3.2F, 0.5F, 2, 2, 2);
		in2.setRotationPoint(0F, 0F, 0F);
		in2.setTextureSize(64, 64);
		in2.mirror = true;
		setRotation(in2, -0.1919862F, -1.396263F, 0F);
		in3 = new ModelRenderer(this, 50, 0);
		in3.addBox(0F, -3F, 0F, 2, 2, 2);
		in3.setRotationPoint(1F, 0F, -2F);
		in3.setTextureSize(64, 64);
		in3.mirror = true;
		setRotation(in3, 0.2617994F, 1.047198F, 0F);
		in4 = new ModelRenderer(this, 0, 0);
		in4.addBox(-2F, -3.1F, -2F, 1, 1, 1);
		in4.setRotationPoint(-1F, 0F, -1F);
		in4.setTextureSize(64, 64);
		in4.mirror = true;
		setRotation(in4, 0.0523599F, -0.2617994F, 0F);
		in5 = new ModelRenderer(this, 0, 3);
		in5.addBox(1F, -2.5F, 1F, 1, 1, 1);
		in5.setRotationPoint(2F, 0F, 2F);
		in5.setTextureSize(64, 64);
		in5.mirror = true;
		setRotation(in5, -0.0523599F, 2.96706F, 0F);
		in6 = new ModelRenderer(this, 0, 6);
		in6.addBox(-1F, -3.1F, 1F, 1, 1, 1);
		in6.setRotationPoint(-2F, 0F, 2F);
		in6.setTextureSize(64, 64);
		in6.mirror = true;
		setRotation(in6, 0.2094395F, -0.3490659F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			float f1 = 0.2617994F;
			in1.rotateAngleY = f1 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f2 = -1.396263F;
			in2.rotateAngleY = f2 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f3 = 1.047198F;
			in3.rotateAngleY = f3 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f4 = -0.2617994F;
			in4.rotateAngleY = f4 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f5 = 2.96706F;
			in5.rotateAngleY = f5 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f6 = -0.3490659F;
			in6.rotateAngleY = f6 + (entity.getIndividual() / 16F) * (float) (Math.PI);
		}
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		dish1.render(scale);
		dish2.render(scale);
		dish3.render(scale);
		dish4.render(scale);
		dishF1.render(scale);
		dishB1.render(scale);
		dishL1.render(scale);
		dishR1.render(scale);
	}

	public void renderSoup1(float scale) {
		soup1.render(scale);
		in1.render(scale);
		in2.render(scale);
		in3.render(scale);
		in4.render(scale);
		in5.render(scale);
		in6.render(scale);
	}

	public void renderSoup2(float scale) {
		soup2.render(scale);
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
