package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishMisoni extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer sauce;
	ModelRenderer fish1;
	ModelRenderer fish2;
	ModelRenderer fish3;

	public ModelDishMisoni(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-10F, -1F, -6F, 20, 1, 12);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 32);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 13);
		dish2.addBox(-6F, 0F, -4F, 12, 1, 8);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 32);
		dish2.mirror = true;
		setRotation(dish2, 0F, 0F, 0F);
		sauce = new ModelRenderer(this, 0, 28);
		sauce.addBox(-5F, -1.5F, -5F, 10, 1, 10);
		sauce.setRotationPoint(0F, 0F, 0F);
		sauce.setTextureSize(64, 64);
		sauce.mirror = true;
		setRotation(sauce, 0F, 0.0872665F, 0F);
		fish1 = new ModelRenderer(this, 0, 40);
		fish1.addBox(-7F, -4F, -4F, 5, 3, 8);
		fish1.setRotationPoint(1F, 0F, 0F);
		fish1.setTextureSize(64, 64);
		fish1.mirror = true;
		setRotation(fish1, 0.0872665F, 0F, 0F);
		fish2 = new ModelRenderer(this, 28, 40);
		fish2.addBox(-2F, -3.8F, -3.5F, 4, 3, 7);
		fish2.setRotationPoint(1F, 0F, 0F);
		fish2.setTextureSize(64, 64);
		fish2.mirror = true;
		setRotation(fish2, 0.0698132F, 0F, 0F);
		fish3 = new ModelRenderer(this, 28, 52);
		fish3.addBox(0F, -3.5F, -3F, 3, 3, 6);
		fish3.setRotationPoint(3F, 0F, 0F);
		fish3.setTextureSize(64, 64);
		fish3.mirror = true;
		setRotation(fish3, 0.0698132F, 0F, 0F);
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
		sauce.render(scale);
		fish1.render(scale);
		fish2.render(scale);
		fish3.render(scale);
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
