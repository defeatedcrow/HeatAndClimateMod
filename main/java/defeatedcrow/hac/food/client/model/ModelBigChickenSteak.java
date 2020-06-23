package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBigChickenSteak extends ModelSteakPlate {

	ModelRenderer steak1;
	ModelRenderer steak2;
	ModelRenderer steak3;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer wing1;
	ModelRenderer wing2;

	public ModelBigChickenSteak(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		steak1 = new ModelRenderer(this, 0, 0);
		steak1.addBox(-5F, -3F, -3F, 8, 3, 6);
		steak1.setRotationPoint(0F, 0F, 0F);
		steak1.setTextureSize(64, 32);
		steak1.mirror = true;
		setRotation(steak1, 0F, 0F, 0F);
		steak2 = new ModelRenderer(this, 26, 0);
		steak2.addBox(-3.1F, -3.5F, -2F, 6, 1, 4);
		steak2.setRotationPoint(0F, 0F, 0F);
		steak2.setTextureSize(64, 32);
		steak2.mirror = true;
		setRotation(steak2, 0F, 0F, 0F);
		steak3 = new ModelRenderer(this, 48, 0);
		steak3.addBox(-1.5F, -4F, -1.5F, 4, 1, 3);
		steak3.setRotationPoint(0F, 0F, 0F);
		steak3.setTextureSize(64, 32);
		steak3.mirror = true;
		setRotation(steak3, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 10, 16);
		leg1.addBox(-1F, -4F, 2F, 5, 3, 2);
		leg1.setRotationPoint(0F, 0F, 0F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, -0.2617994F, 0.2617994F, 0F);
		leg2 = new ModelRenderer(this, 10, 10);
		leg2.addBox(-1F, -4F, -4F, 5, 3, 2);
		leg2.setRotationPoint(0F, 0F, 0F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.2617994F, -0.2792527F, 0F);
		leg3 = new ModelRenderer(this, 25, 13);
		leg3.addBox(3F, -3F, 2F, 4, 1, 1);
		leg3.setRotationPoint(0F, 0F, 0F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0.1396263F, 0F);
		leg4 = new ModelRenderer(this, 25, 10);
		leg4.addBox(3F, -3F, -3F, 4, 1, 1);
		leg4.setRotationPoint(0F, 0F, 0F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, -0.1396263F, 0F);
		wing1 = new ModelRenderer(this, 0, 10);
		wing1.addBox(-4F, -2F, -4F, 3, 2, 1);
		wing1.setRotationPoint(0F, 0F, 0F);
		wing1.setTextureSize(64, 32);
		wing1.mirror = true;
		setRotation(wing1, 0F, 0.1047198F, 0F);
		wing2 = new ModelRenderer(this, 0, 10);
		wing2.addBox(-4F, -2F, 3F, 3, 2, 1);
		wing2.setRotationPoint(0F, 0F, 0F);
		wing2.setTextureSize(64, 32);
		wing2.mirror = true;
		setRotation(wing2, 0F, -0.1396263F, 0F);
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
		steak1.render(scale);
		steak2.render(scale);
		steak3.render(scale);
		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
		leg4.render(scale);
		wing1.render(scale);
		wing2.render(scale);
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
