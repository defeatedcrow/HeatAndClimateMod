package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelGingerman extends DCFoodModelBase {

	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer handR;
	ModelRenderer handL;
	ModelRenderer legR;
	ModelRenderer legL;

	public ModelGingerman(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, 0F, 3F, 6, 1, 6);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 25, 0);
		body.addBox(-3F, 0F, -5F, 6, 1, 8);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		handR = new ModelRenderer(this, 0, 8);
		handR.addBox(2.5F, 0.1F, -2F, 6, 1, 4);
		handR.setRotationPoint(0F, 0F, 0F);
		handR.setTextureSize(64, 32);
		handR.mirror = true;
		setRotation(handR, 0F, -0.2617994F, 0F);
		handL = new ModelRenderer(this, 0, 8);
		handL.addBox(2.5F, 0.1F, -2F, 6, 1, 4);
		handL.setRotationPoint(0F, 0F, 0F);
		handL.setTextureSize(64, 32);
		handL.mirror = true;
		setRotation(handL, 0F, -2.879793F, 0F);
		legR = new ModelRenderer(this, 0, 14);
		legR.addBox(-2F, 0.1F, -2F, 8, 1, 4);
		legR.setRotationPoint(2F, 0F, -4F);
		legR.setTextureSize(64, 32);
		legR.mirror = true;
		setRotation(legR, 0F, 1.047198F, 0F);
		legL = new ModelRenderer(this, 0, 14);
		legL.addBox(-2F, 0.1F, -2F, 8, 1, 4);
		legL.setRotationPoint(-2F, 0F, -4F);
		legL.setTextureSize(64, 32);
		legL.mirror = true;
		setRotation(legL, 0F, 2.094395F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		head.render(scale);
		body.render(scale);
		handR.render(scale);
		handL.render(scale);
		legR.render(scale);
		legL.render(scale);
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
