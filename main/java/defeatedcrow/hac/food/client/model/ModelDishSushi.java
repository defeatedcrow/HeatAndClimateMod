package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishSushi extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer dish3;
	ModelRenderer syari1;
	ModelRenderer syari2;
	ModelRenderer syari3;
	ModelRenderer syari4;
	ModelRenderer sushi11;
	ModelRenderer sushi12;
	ModelRenderer sushi13;
	ModelRenderer sushi21;
	ModelRenderer sushi22;
	ModelRenderer sushi23;
	ModelRenderer sushi31;
	ModelRenderer sushi32;
	ModelRenderer sushi33;
	ModelRenderer sushi41;
	ModelRenderer sushi42;
	ModelRenderer sushi43;

	public ModelDishSushi(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-10F, -2F, -6F, 20, 1, 12);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 32);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 13);
		dish2.addBox(-7F, -1F, -5F, 1, 2, 10);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 32);
		dish2.mirror = true;
		setRotation(dish2, 0F, 0F, 0F);
		dish3 = new ModelRenderer(this, 0, 13);
		dish3.addBox(6F, -1F, -5F, 1, 2, 10);
		dish3.setRotationPoint(0F, 0F, 0F);
		dish3.setTextureSize(64, 32);
		dish3.mirror = true;
		setRotation(dish3, 0F, 0F, 0F);
		syari1 = new ModelRenderer(this, 0, 32);
		syari1.addBox(0F, -4F, 0F, 3, 2, 5);
		syari1.setRotationPoint(-7.8F, 0F, -3F);
		syari1.setTextureSize(64, 64);
		syari1.mirror = true;
		setRotation(syari1, 0F, -0.2094395F, 0F);
		syari2 = new ModelRenderer(this, 0, 32);
		syari2.addBox(0F, -4F, 0F, 3, 2, 5);
		syari2.setRotationPoint(-3.333333F, 0F, -3F);
		syari2.setTextureSize(64, 64);
		syari2.mirror = true;
		setRotation(syari2, 0F, -0.2094395F, 0F);
		syari3 = new ModelRenderer(this, 0, 32);
		syari3.addBox(0F, -4F, 0F, 3, 2, 5);
		syari3.setRotationPoint(1.3F, 0F, -3F);
		syari3.setTextureSize(64, 64);
		syari3.mirror = true;
		setRotation(syari3, 0F, -0.2094395F, 0F);
		syari4 = new ModelRenderer(this, 0, 32);
		syari4.addBox(1F, -4F, 0F, 3, 2, 5);
		syari4.setRotationPoint(4.8F, 0F, -3F);
		syari4.setTextureSize(64, 64);
		syari4.mirror = true;
		setRotation(syari4, 0F, -0.2094395F, 0F);
		sushi11 = new ModelRenderer(this, 18, 32);
		sushi11.addBox(0F, -5.5F, 1.5F, 4, 1, 3);
		sushi11.setRotationPoint(-8.5F, 0F, -2F);
		sushi11.setTextureSize(64, 64);
		sushi11.mirror = true;
		setRotation(sushi11, -0.2443461F, -0.2094395F, 0F);
		sushi12 = new ModelRenderer(this, 18, 36);
		sushi12.addBox(0F, -5F, 0F, 4, 1, 3);
		sushi12.setRotationPoint(-8.5F, 0F, -2F);
		sushi12.setTextureSize(64, 64);
		sushi12.mirror = true;
		setRotation(sushi12, 0F, -0.2094395F, 0F);
		sushi13 = new ModelRenderer(this, 18, 40);
		sushi13.addBox(0F, -4.8F, -1.8F, 4, 1, 3);
		sushi13.setRotationPoint(-8.5F, 0F, -2F);
		sushi13.setTextureSize(64, 64);
		sushi13.mirror = true;
		setRotation(sushi13, 0.2443461F, -0.2094395F, 0F);
		sushi21 = new ModelRenderer(this, 33, 32);
		sushi21.addBox(0F, -5.5F, 1.5F, 4, 1, 3);
		sushi21.setRotationPoint(-4F, 0F, -2F);
		sushi21.setTextureSize(64, 64);
		sushi21.mirror = true;
		setRotation(sushi21, -0.2792527F, -0.2094395F, 0F);
		sushi22 = new ModelRenderer(this, 33, 36);
		sushi22.addBox(0F, -5F, 0F, 4, 1, 3);
		sushi22.setRotationPoint(-4F, 0F, -2F);
		sushi22.setTextureSize(64, 64);
		sushi22.mirror = true;
		setRotation(sushi22, 0F, -0.2094395F, 0F);
		sushi23 = new ModelRenderer(this, 33, 40);
		sushi23.addBox(0F, -4.8F, -1.7F, 4, 1, 3);
		sushi23.setRotationPoint(-4F, 0F, -2F);
		sushi23.setTextureSize(64, 64);
		sushi23.mirror = true;
		setRotation(sushi23, 0.2792527F, -0.2094395F, 0F);
		sushi31 = new ModelRenderer(this, 18, 46);
		sushi31.addBox(0F, -5.5F, 1.8F, 4, 1, 3);
		sushi31.setRotationPoint(0.5F, 0F, -2F);
		sushi31.setTextureSize(64, 64);
		sushi31.mirror = true;
		setRotation(sushi31, -0.1745329F, -0.2094395F, 0F);
		sushi32 = new ModelRenderer(this, 18, 50);
		sushi32.addBox(0F, -5F, 0F, 4, 1, 3);
		sushi32.setRotationPoint(0.5F, 0F, -2F);
		sushi32.setTextureSize(64, 64);
		sushi32.mirror = true;
		setRotation(sushi32, 0F, -0.2094395F, 0F);
		sushi33 = new ModelRenderer(this, 18, 54);
		sushi33.addBox(0F, -4.8F, -2F, 4, 1, 3);
		sushi33.setRotationPoint(0.5F, 0F, -2F);
		sushi33.setTextureSize(64, 64);
		sushi33.mirror = true;
		setRotation(sushi33, 0.1570796F, -0.2094395F, 0F);
		sushi41 = new ModelRenderer(this, 33, 46);
		sushi41.addBox(0F, -5.5F, 1.5F, 4, 1, 3);
		sushi41.setRotationPoint(5F, 0F, -2F);
		sushi41.setTextureSize(64, 64);
		sushi41.mirror = true;
		setRotation(sushi41, -0.2443461F, -0.2094395F, 0F);
		sushi42 = new ModelRenderer(this, 33, 50);
		sushi42.addBox(0F, -5F, 0F, 4, 1, 3);
		sushi42.setRotationPoint(5F, 0F, -2F);
		sushi42.setTextureSize(64, 64);
		sushi42.mirror = true;
		setRotation(sushi42, 0F, -0.2094395F, 0F);
		sushi43 = new ModelRenderer(this, 33, 54);
		sushi43.addBox(0F, -4.8F, -1.8F, 4, 1, 3);
		sushi43.setRotationPoint(5F, 0F, -2F);
		sushi43.setTextureSize(64, 64);
		sushi43.mirror = true;
		setRotation(sushi43, 0.2443461F, -0.2094395F, 0F);
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
		syari1.render(scale);
		syari2.render(scale);
		syari3.render(scale);
		syari4.render(scale);
		sushi11.render(scale);
		sushi12.render(scale);
		sushi13.render(scale);
		sushi21.render(scale);
		sushi22.render(scale);
		sushi23.render(scale);
		sushi31.render(scale);
		sushi32.render(scale);
		sushi33.render(scale);
		sushi41.render(scale);
		sushi42.render(scale);
		sushi43.render(scale);
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
