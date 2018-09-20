package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFishAndChips extends DCFoodModelBase {

	ModelRenderer paper1;
	ModelRenderer paper2;
	ModelRenderer fish;
	ModelRenderer potato1;
	ModelRenderer potato2;
	ModelRenderer potato3;
	ModelRenderer potato4;
	ModelRenderer potato5;
	ModelRenderer potato6;
	ModelRenderer potato21;
	ModelRenderer potato22;
	ModelRenderer potato23;
	ModelRenderer potato24;
	ModelRenderer potato25;
	ModelRenderer potato26;

	public ModelFishAndChips(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		paper1 = new ModelRenderer(this, 0, 0);
		paper1.addBox(-4F, -1F, -10F, 8, 0, 10);
		paper1.setRotationPoint(0F, 0F, 5F);
		paper1.setTextureSize(64, 32);
		paper1.mirror = true;
		setRotation(paper1, -0.2F, 0F, 0F);
		paper2 = new ModelRenderer(this, 36, 0);
		paper2.addBox(-4F, -3F, -5.2F, 8, 3, 5);
		paper2.setRotationPoint(0F, 0F, 5F);
		paper2.setTextureSize(64, 32);
		paper2.mirror = true;
		setRotation(paper2, -0.2443461F, 0F, 0F);
		fish = new ModelRenderer(this, 29, 12);
		fish.addBox(-3F, -2.5F, -11F, 4, 2, 10);
		fish.setRotationPoint(0F, 0F, 5F);
		fish.setTextureSize(64, 32);
		fish.mirror = true;
		setRotation(fish, -0.1919862F, 0.0872665F, 0F);
		potato1 = new ModelRenderer(this, 0, 12);
		potato1.addBox(0F, -2F, -8F, 1, 1, 6);
		potato1.setRotationPoint(0F, 0F, 5F);
		potato1.setTextureSize(64, 32);
		potato1.mirror = true;
		setRotation(potato1, -0.3839724F, 0.1745329F, 0F);
		potato2 = new ModelRenderer(this, 0, 12);
		potato2.addBox(3F, -1.7F, -8.5F, 1, 1, 6);
		potato2.setRotationPoint(0F, 0F, 5F);
		potato2.setTextureSize(64, 32);
		potato2.mirror = true;
		setRotation(potato2, -0.1745329F, 0.3316126F, 0F);
		potato3 = new ModelRenderer(this, 0, 12);
		potato3.addBox(1F, -1.5F, -10F, 1, 1, 6);
		potato3.setRotationPoint(0F, 0F, 5F);
		potato3.setTextureSize(64, 32);
		potato3.mirror = true;
		setRotation(potato3, -0.1570796F, -0.3141593F, 0F);
		potato4 = new ModelRenderer(this, 0, 12);
		potato4.addBox(0F, -2F, -7F, 1, 1, 6);
		potato4.setRotationPoint(0F, 0F, 5F);
		potato4.setTextureSize(64, 32);
		potato4.mirror = true;
		setRotation(potato4, -0.2792527F, -0.0872665F, 0F);
		potato5 = new ModelRenderer(this, 0, 12);
		potato5.addBox(3F, -3.3F, -7F, 1, 1, 6);
		potato5.setRotationPoint(0F, 0F, 5F);
		potato5.setTextureSize(64, 32);
		potato5.mirror = true;
		setRotation(potato5, 0.0349066F, 0.1396263F, 0F);
		potato6 = new ModelRenderer(this, 0, 12);
		potato6.addBox(0F, -2F, -9F, 1, 1, 6);
		potato6.setRotationPoint(0F, 0F, 5F);
		potato6.setTextureSize(64, 32);
		potato6.mirror = true;
		setRotation(potato6, -0.3490659F, -0.3141593F, 0F);
		potato21 = new ModelRenderer(this, 0, 12);
		potato21.addBox(0F, -2F, -7F, 1, 1, 6);
		potato21.setRotationPoint(0F, 0F, 5F);
		potato21.setTextureSize(64, 32);
		potato21.mirror = true;
		setRotation(potato21, -0.1396263F, 0.0872665F, 0F);
		potato22 = new ModelRenderer(this, 0, 12);
		potato22.addBox(-3F, -2F, -8F, 1, 1, 6);
		potato22.setRotationPoint(0F, 0F, 5F);
		potato22.setTextureSize(64, 32);
		potato22.mirror = true;
		setRotation(potato22, -0.2617994F, -0.1919862F, 0F);
		potato23 = new ModelRenderer(this, 0, 12);
		potato23.addBox(-3F, -2F, -8F, 1, 1, 6);
		potato23.setRotationPoint(0F, 0F, 5F);
		potato23.setTextureSize(64, 32);
		potato23.mirror = true;
		setRotation(potato23, -0.1047198F, 0.1570796F, 0F);
		potato24 = new ModelRenderer(this, 0, 12);
		potato24.addBox(1F, -2F, -7F, 1, 1, 6);
		potato24.setRotationPoint(0F, 0F, 5F);
		potato24.setTextureSize(64, 32);
		potato24.mirror = true;
		setRotation(potato24, -0.0872665F, 0.5934119F, 0F);
		potato25 = new ModelRenderer(this, 0, 12);
		potato25.addBox(-2F, -3F, -9F, 1, 1, 6);
		potato25.setRotationPoint(0F, 0F, 5F);
		potato25.setTextureSize(64, 32);
		potato25.mirror = true;
		setRotation(potato25, -0.1047198F, 0.3665191F, 0F);
		potato26 = new ModelRenderer(this, 0, 12);
		potato26.addBox(-3F, -3F, -7F, 1, 1, 6);
		potato26.setRotationPoint(0F, 0F, 5F);
		potato26.setTextureSize(64, 32);
		potato26.mirror = true;
		setRotation(potato26, -0.1919862F, 0.0349066F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		paper1.render(scale);
		paper2.render(scale);
		potato1.render(scale);
		potato2.render(scale);
		potato3.render(scale);
		potato4.render(scale);
		potato5.render(scale);
		potato6.render(scale);

	}

	public void renderFish() {
		fish.render(0.0625F);
	}

	public void renderPotatos() {
		potato21.render(0.0625F);
		potato22.render(0.0625F);
		potato23.render(0.0625F);
		potato24.render(0.0625F);
		potato25.render(0.0625F);
		potato26.render(0.0625F);
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
