package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCocotte extends DCFoodModelBase {

	public ModelRenderer bottom;
	public ModelRenderer side1;
	public ModelRenderer side2;
	public ModelRenderer side3;
	public ModelRenderer side4;
	public ModelRenderer top;

	public ModelCocotte(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-2F, 7F, -2F, 4, 1, 4, 0F);
		bottom.setRotationPoint(0F, -2F, 0F);
		bottom.rotateAngleX = 0F;
		bottom.rotateAngleY = 0F;
		bottom.rotateAngleZ = 0F;
		bottom.mirror = false;
		side1 = new ModelRenderer(this, 0, 8);
		side1.addBox(-3F, 4F, -3F, 6, 4, 1, 0F);
		side1.setRotationPoint(0F, -2F, 0F);
		side1.rotateAngleX = 0F;
		side1.rotateAngleY = 0F;
		side1.rotateAngleZ = 0F;
		side1.mirror = false;
		side2 = new ModelRenderer(this, 0, 8);
		side2.addBox(-3F, 4F, 2F, 6, 4, 1, 0F);
		side2.setRotationPoint(0F, -2F, 0F);
		side2.rotateAngleX = 0F;
		side2.rotateAngleY = 0F;
		side2.rotateAngleZ = 0F;
		side2.mirror = false;
		side3 = new ModelRenderer(this, 16, 8);
		side3.addBox(-2F, 4F, 2F, 4, 4, 1, 0F);
		side3.setRotationPoint(0F, -2F, 0F);
		side3.rotateAngleX = 0F;
		side3.rotateAngleY = 1.570796F;
		side3.rotateAngleZ = 0F;
		side3.mirror = false;
		side4 = new ModelRenderer(this, 16, 8);
		side4.addBox(-2F, 4F, -3F, 4, 4, 1, 0F);
		side4.setRotationPoint(0F, -2F, 0F);
		side4.rotateAngleX = 0F;
		side4.rotateAngleY = 1.570796F;
		side4.rotateAngleZ = 0F;
		side4.mirror = false;
		top = new ModelRenderer(this, 0, 16);
		top.addBox(-2F, 4.2F, -2F, 4, 3, 4, 0F);
		top.setRotationPoint(0F, -2F, 0F);
		top.rotateAngleX = 0F;
		top.rotateAngleY = 0F;
		top.rotateAngleZ = 0F;
		top.mirror = false;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bottom.render(scale);
		side1.render(scale);
		side2.render(scale);
		side3.render(scale);
		side4.render(scale);
		top.render(scale);

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
