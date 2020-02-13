package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishSashimi extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer dish3;
	ModelRenderer leaves1;
	ModelRenderer leaves2;
	ModelRenderer sashimi1;
	ModelRenderer sashimi2;
	ModelRenderer sashimi3;
	ModelRenderer sashimi4;
	ModelRenderer sashimi5;
	ModelRenderer sashim6;
	ModelRenderer kiku;
	ModelRenderer wasabi;

	public ModelDishSashimi(boolean baked) {
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
		leaves1 = new ModelRenderer(this, 24, 15);
		leaves1.addBox(-1F, -1.5F, 2F, 9, 0, 8);
		leaves1.setRotationPoint(0F, 0F, 0F);
		leaves1.setTextureSize(64, 32);
		leaves1.mirror = true;
		setRotation(leaves1, 0.1745329F, 0.2617994F, 0F);
		leaves2 = new ModelRenderer(this, 24, 15);
		leaves2.addBox(-8F, -1.8F, 1F, 9, 0, 8);
		leaves2.setRotationPoint(0F, 0F, 0F);
		leaves2.setTextureSize(64, 32);
		leaves2.mirror = true;
		setRotation(leaves2, 0.1745329F, -0.1396263F, 0F);
		sashimi1 = new ModelRenderer(this, 0, 32);
		sashimi1.addBox(-4F, 0F, 0F, 7, 1, 4);
		sashimi1.setRotationPoint(-5.5F, -3F, -4F);
		sashimi1.setTextureSize(64, 32);
		sashimi1.mirror = true;
		setRotation(sashimi1, 0.6283185F, 0.5235988F, 0F);
		sashimi2 = new ModelRenderer(this, 0, 32);
		sashimi2.addBox(-4F, 0F, 0F, 7, 1, 4);
		sashimi2.setRotationPoint(-4.5F, -3F, -3F);
		sashimi2.setTextureSize(64, 32);
		sashimi2.mirror = true;
		setRotation(sashimi2, 0.418879F, 0.6283185F, 0F);
		sashimi3 = new ModelRenderer(this, 0, 38);
		sashimi3.addBox(-4F, 0F, 0F, 7, 1, 4);
		sashimi3.setRotationPoint(-0.5F, -3F, -1F);
		sashimi3.setTextureSize(64, 32);
		sashimi3.mirror = true;
		setRotation(sashimi3, 0.418879F, 0.2268928F, 0F);
		sashimi4 = new ModelRenderer(this, 0, 38);
		sashimi4.addBox(-4F, 0F, 0F, 7, 1, 4);
		sashimi4.setRotationPoint(0F, -2.5F, 1F);
		sashimi4.setTextureSize(64, 32);
		sashimi4.mirror = true;
		setRotation(sashimi4, 0.3141593F, 0.3141593F, 0F);
		sashimi5 = new ModelRenderer(this, 0, 44);
		sashimi5.addBox(-4F, 0F, 0F, 7, 1, 4);
		sashimi5.setRotationPoint(4F, -3F, -4F);
		sashimi5.setTextureSize(64, 32);
		sashimi5.mirror = true;
		setRotation(sashimi5, 0.6283185F, 0.5235988F, 0F);
		sashim6 = new ModelRenderer(this, 0, 44);
		sashim6.addBox(-4F, 0F, 0F, 7, 1, 4);
		sashim6.setRotationPoint(5F, -3F, -3F);
		sashim6.setTextureSize(64, 32);
		sashim6.mirror = true;
		setRotation(sashim6, 0.3490659F, 0.6981317F, 0F);
		kiku = new ModelRenderer(this, 24, 32);
		kiku.addBox(-1F, -1F, -1F, 3, 1, 3);
		kiku.setRotationPoint(3F, -3F, -4F);
		kiku.setTextureSize(64, 64);
		kiku.mirror = true;
		setRotation(kiku, 0.3490659F, 0.5061455F, 0F);
		wasabi = new ModelRenderer(this, 24, 38);
		wasabi.addBox(-1F, -1F, -1F, 2, 2, 2);
		wasabi.setRotationPoint(1F, -3F, -3F);
		wasabi.setTextureSize(64, 64);
		wasabi.mirror = true;
		setRotation(wasabi, 0F, 0.2094395F, 0F);
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
		leaves1.render(scale);
		leaves2.render(scale);
		sashimi1.render(scale);
		sashimi2.render(scale);
		sashimi3.render(scale);
		sashimi4.render(scale);
		sashimi5.render(scale);
		sashim6.render(scale);
		kiku.render(scale);
		wasabi.render(scale);
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
