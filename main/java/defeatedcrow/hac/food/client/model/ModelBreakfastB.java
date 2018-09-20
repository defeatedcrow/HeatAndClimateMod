package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBreakfastB extends DCFoodModelBase {

	ModelRenderer plate1;
	ModelRenderer plate2;
	ModelRenderer toast1;
	ModelRenderer toast2;
	ModelRenderer egg1;
	ModelRenderer egg2;
	ModelRenderer egg3;
	ModelRenderer sausage;
	ModelRenderer beans1;
	ModelRenderer beans2;
	ModelRenderer tomato1;
	ModelRenderer tomato2;
	ModelRenderer mushroom1;
	ModelRenderer mushroom2;
	ModelRenderer mushroom3;

	public ModelBreakfastB(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		plate1 = new ModelRenderer(this, 0, 0);
		plate1.addBox(-7F, -1F, -7F, 14, 1, 14);
		plate1.setRotationPoint(0F, 0F, 0F);
		plate1.setTextureSize(64, 32);
		plate1.mirror = true;
		setRotation(plate1, 0F, 0F, 0F);
		plate2 = new ModelRenderer(this, 40, 0);
		plate2.addBox(-3F, 0F, -3F, 6, 1, 6);
		plate2.setRotationPoint(0F, 0F, 0F);
		plate2.setTextureSize(64, 32);
		plate2.mirror = true;
		setRotation(plate2, 0F, 0F, 0F);
		toast1 = new ModelRenderer(this, 0, 15);
		toast1.addBox(-7F, -3F, -4F, 6, 1, 5);
		toast1.setRotationPoint(0F, 0F, 0F);
		toast1.setTextureSize(64, 32);
		toast1.mirror = true;
		setRotation(toast1, 0.2617994F, 1.047198F, 0F);
		toast2 = new ModelRenderer(this, 0, 15);
		toast2.addBox(-7F, -2F, -4F, 6, 1, 5);
		toast2.setRotationPoint(0F, 0F, 0F);
		toast2.setTextureSize(64, 32);
		toast2.mirror = true;
		setRotation(toast2, 0F, 1.308997F, 0F);
		egg1 = new ModelRenderer(this, 0, 21);
		egg1.addBox(1F, -2F, -1F, 5, 1, 5);
		egg1.setRotationPoint(0F, 0F, 0F);
		egg1.setTextureSize(64, 32);
		egg1.mirror = true;
		setRotation(egg1, 0F, 0.4363323F, 0F);
		egg2 = new ModelRenderer(this, 0, 27);
		egg2.addBox(2F, -1.9F, 2F, 4, 1, 3);
		egg2.setRotationPoint(0F, 0F, 0F);
		egg2.setTextureSize(64, 32);
		egg2.mirror = true;
		setRotation(egg2, 0F, 0.1396263F, 0F);
		egg3 = new ModelRenderer(this, 14, 27);
		egg3.addBox(3F, -3F, 0F, 2, 1, 2);
		egg3.setRotationPoint(0F, 0F, 0F);
		egg3.setTextureSize(64, 32);
		egg3.mirror = true;
		setRotation(egg3, 0F, 0.1396263F, 0F);
		sausage = new ModelRenderer(this, 24, 15);
		sausage.addBox(-1F, -3F, -1F, 2, 2, 8);
		sausage.setRotationPoint(0F, 0F, 0F);
		sausage.setTextureSize(64, 32);
		sausage.mirror = true;
		setRotation(sausage, 0F, 0.3490659F, 0F);
		beans1 = new ModelRenderer(this, 46, 16);
		beans1.addBox(3F, -2F, -2F, 4, 1, 3);
		beans1.setRotationPoint(0F, 0F, 0F);
		beans1.setTextureSize(64, 32);
		beans1.mirror = true;
		setRotation(beans1, 0F, 0.9599311F, 0F);
		beans2 = new ModelRenderer(this, 46, 20);
		beans2.addBox(3F, -3F, -1.5F, 3, 2, 3);
		beans2.setRotationPoint(0F, 0F, 0F);
		beans2.setTextureSize(64, 32);
		beans2.mirror = true;
		setRotation(beans2, 0F, 0.8726646F, 0F);
		tomato1 = new ModelRenderer(this, 24, 26);
		tomato1.addBox(-2F, -3F, -1F, 3, 1, 3);
		tomato1.setRotationPoint(0F, 0F, 0F);
		tomato1.setTextureSize(64, 32);
		tomato1.mirror = true;
		setRotation(tomato1, 0.6981317F, 0.2617994F, 0F);
		tomato2 = new ModelRenderer(this, 24, 26);
		tomato2.addBox(2F, -3F, -2F, 3, 1, 3);
		tomato2.setRotationPoint(0F, 0F, 0F);
		tomato2.setTextureSize(64, 32);
		tomato2.mirror = true;
		setRotation(tomato2, 0.5235988F, 1.305899F, 0F);
		mushroom1 = new ModelRenderer(this, 42, 26);
		mushroom1.addBox(-6F, -2F, -1F, 2, 1, 2);
		mushroom1.setRotationPoint(0F, 0F, 0F);
		mushroom1.setTextureSize(64, 32);
		mushroom1.mirror = true;
		setRotation(mushroom1, 0F, -0.4089647F, 0F);
		mushroom2 = new ModelRenderer(this, 42, 26);
		mushroom2.addBox(-8F, -2F, -2F, 2, 1, 2);
		mushroom2.setRotationPoint(0F, 0F, 0F);
		mushroom2.setTextureSize(64, 32);
		mushroom2.mirror = true;
		setRotation(mushroom2, 0F, -0.6108652F, 0F);
		mushroom3 = new ModelRenderer(this, 42, 26);
		mushroom3.addBox(-6F, -2F, 0F, 2, 1, 2);
		mushroom3.setRotationPoint(0F, 0F, 0F);
		mushroom3.setTextureSize(64, 32);
		mushroom3.mirror = true;
		setRotation(mushroom3, 0F, -1.047198F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		plate1.render(scale);
		plate2.render(scale);
		toast1.render(scale);
		toast2.render(scale);
		egg1.render(scale);
		egg2.render(scale);
		egg3.render(scale);
		sausage.render(scale);
		beans1.render(scale);
		beans2.render(scale);
		tomato1.render(scale);
		tomato2.render(scale);
		mushroom1.render(scale);
		mushroom2.render(scale);
		mushroom3.render(scale);

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
