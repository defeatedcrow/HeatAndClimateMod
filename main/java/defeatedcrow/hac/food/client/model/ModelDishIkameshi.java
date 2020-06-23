package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishIkameshi extends DCFoodModelBase {

	public ModelRenderer dish1;
	public ModelRenderer dish2;
	public ModelRenderer dish3;
	public ModelRenderer dish4;
	public ModelRenderer dish5;
	public ModelRenderer ika1;
	public ModelRenderer ika2;
	public ModelRenderer ika3;
	public ModelRenderer ika4;
	public ModelRenderer ika5;
	public ModelRenderer ika6;
	public ModelRenderer ika7;
	public ModelRenderer ika8;

	public ModelDishIkameshi(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-5F, -1F, -5F, 10, 1, 10);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 32);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 12);
		dish2.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 32);
		dish2.mirror = true;
		setRotation(dish2, 0.2617994F, 0F, 0F);
		dish3 = new ModelRenderer(this, 0, 12);
		dish3.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish3.setRotationPoint(0F, 0F, 0F);
		dish3.setTextureSize(64, 32);
		dish3.mirror = true;
		setRotation(dish3, 0.2617994F, 1.570796F, 0F);
		dish4 = new ModelRenderer(this, 0, 12);
		dish4.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish4.setRotationPoint(0F, 0F, 0F);
		dish4.setTextureSize(64, 32);
		dish4.mirror = true;
		setRotation(dish4, 0.2617994F, 3.141593F, 0F);
		dish5 = new ModelRenderer(this, 0, 12);
		dish5.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish5.setRotationPoint(0F, 0F, 0F);
		dish5.setTextureSize(64, 32);
		dish5.mirror = true;
		setRotation(dish5, 0.2617994F, -1.570796F, 0F);
		ika1 = new ModelRenderer(this, 0, 16);
		ika1.addBox(-2F, -5F, -1F, 4, 4, 5, 0F);
		ika1.setRotationPoint(0F, 0F, 0F);
		ika1.rotateAngleX = 0F;
		ika1.rotateAngleY = 0F;
		ika1.rotateAngleZ = 0F;
		ika1.mirror = false;
		ika2 = new ModelRenderer(this, 19, 16);
		ika2.addBox(-1.5F, -4.5F, 4F, 3, 3, 1, 0F);
		ika2.setRotationPoint(0F, 0F, 0F);
		ika2.rotateAngleX = 0F;
		ika2.rotateAngleY = 0F;
		ika2.rotateAngleZ = 0F;
		ika2.mirror = false;
		ika3 = new ModelRenderer(this, 28, 16);
		ika3.addBox(-1F, -4F, 5F, 2, 2, 1, 0F);
		ika3.setRotationPoint(0F, 0F, 0F);
		ika3.rotateAngleX = 0F;
		ika3.rotateAngleY = 0F;
		ika3.rotateAngleZ = 0F;
		ika3.mirror = false;
		ika4 = new ModelRenderer(this, 35, 16);
		ika4.addBox(-0.5F, -3.5F, 6F, 1, 1, 1, 0F);
		ika4.setRotationPoint(0F, 0F, 0F);
		ika4.rotateAngleX = 0F;
		ika4.rotateAngleY = 0F;
		ika4.rotateAngleZ = 0F;
		ika4.mirror = false;
		ika5 = new ModelRenderer(this, 19, 21);
		ika5.addBox(0F, -3.5F, 0F, 3, 1, 3, 0F);
		ika5.setRotationPoint(-0.5F, 0F, 3F);
		ika5.rotateAngleX = 0F;
		ika5.rotateAngleY = 0.4363323F;
		ika5.rotateAngleZ = 0F;
		ika5.mirror = false;
		ika6 = new ModelRenderer(this, 19, 21);
		ika6.addBox(0F, -3.5F, 0F, 3, 1, 3, 0F);
		ika6.setRotationPoint(0.5F, 0F, 3F);
		ika6.rotateAngleX = 0F;
		ika6.rotateAngleY = -2.007129F;
		ika6.rotateAngleZ = 0F;
		ika6.mirror = false;
		ika7 = new ModelRenderer(this, 0, 26);
		ika7.addBox(-2F, -4.5F, -3F, 4, 4, 1, 0F);
		ika7.setRotationPoint(0F, 0F, 0F);
		ika7.rotateAngleX = -0.2617994F;
		ika7.rotateAngleY = 0F;
		ika7.rotateAngleZ = 0F;
		ika7.mirror = false;
		ika8 = new ModelRenderer(this, 0, 26);
		ika8.addBox(-2F, -2.5F, -5F, 4, 4, 1, 0F);
		ika8.setRotationPoint(0F, 0F, 0F);
		ika8.rotateAngleX = -0.5585054F;
		ika8.rotateAngleY = 0F;
		ika8.rotateAngleZ = 0F;
		ika8.mirror = false;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
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
		ika1.render(scale);
		ika2.render(scale);
		ika3.render(scale);
		ika4.render(scale);
		ika5.render(scale);
		ika6.render(scale);
		ika7.render(scale);
		ika8.render(scale);
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

	public void setIndividualRotation(FoodEntityBase entity) {

	}

}
