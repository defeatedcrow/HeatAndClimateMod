package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishYakko extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer sauce;
	ModelRenderer tofu;
	ModelRenderer sauce2;
	ModelRenderer negi;

	public ModelDishYakko(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-8F, -1F, -6F, 16, 1, 12);
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
		sauce.addBox(-5F, -1.5F, -5F, 8, 1, 8);
		sauce.setRotationPoint(0F, 0F, 0F);
		sauce.setTextureSize(64, 64);
		sauce.mirror = true;
		setRotation(sauce, 0F, 0.0872665F, 0F);
		tofu = new ModelRenderer(this, 0, 40);
		tofu.addBox(-4F, -6F, -3F, 7, 5, 7);
		tofu.setRotationPoint(1F, 0F, 0F);
		tofu.setTextureSize(64, 64);
		tofu.mirror = true;
		setRotation(tofu, 0F, 0F, 0F);
		sauce2 = new ModelRenderer(this, 33, 28);
		sauce2.addBox(-2F, -6.5F, -3.5F, 3, 5, 5);
		sauce2.setRotationPoint(0F, 0F, 0F);
		sauce2.setTextureSize(64, 64);
		sauce2.mirror = true;
		setRotation(sauce2, 0F, 0F, 0F);
		negi = new ModelRenderer(this, 32, 40);
		negi.addBox(-1F, -7F, 0F, 2, 1, 2);
		negi.setRotationPoint(0F, 0F, 0F);
		negi.setTextureSize(64, 64);
		negi.mirror = true;
		setRotation(negi, 0F, 0.5235988F, 0F);
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
		tofu.render(scale);
		sauce2.render(scale);
		negi.render(scale);
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
