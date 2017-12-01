package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCrowBalloon extends DCFoodModelBase {

	ModelRenderer body1;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer string1;
	ModelRenderer string2;
	ModelRenderer bottom;

	public ModelCrowBalloon(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		body1 = new ModelRenderer(this, 0, 0);
		body1.addBox(-5F, -5F, -5F, 10, 8, 10);
		body1.setRotationPoint(0F, 0F, 0F);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 0, 18);
		body2.addBox(-4F, -6F, -4F, 8, 1, 8);
		body2.setRotationPoint(0F, 0F, 0F);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 0, 18);
		body3.addBox(-4F, 3F, -4F, 8, 1, 8);
		body3.setRotationPoint(0F, 0F, 0F);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
		string1 = new ModelRenderer(this, 57, 1);
		string1.addBox(-0.25F, 6F, 0F, 1, 16, 0);
		string1.setRotationPoint(0F, -2F, 0F);
		string1.setTextureSize(64, 64);
		string1.mirror = true;
		setRotation(string1, 0F, 0F, 0F);
		string2 = new ModelRenderer(this, 60, 0);
		string2.addBox(0F, 4F, -0.25F, 0, 16, 1);
		string2.setRotationPoint(0F, 0F, 0F);
		string2.setTextureSize(64, 64);
		string2.mirror = true;
		setRotation(string2, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 40, 0);
		bottom.addBox(-2F, 20F, -2F, 4, 2, 4);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		body1.render(scale);
		body2.render(scale);
		body3.render(scale);
		string1.render(scale);
		string2.render(scale);
		bottom.render(scale);
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
