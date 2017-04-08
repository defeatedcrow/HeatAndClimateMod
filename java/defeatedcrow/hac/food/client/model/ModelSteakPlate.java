package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSteakPlate extends DCFoodModelBase {

	ModelRenderer bottom;
	ModelRenderer sideF;
	ModelRenderer sideB;
	ModelRenderer sideR;
	ModelRenderer sideL;
	ModelRenderer sideR2;
	ModelRenderer sideL2;

	public ModelSteakPlate(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-6F, 0F, -5F, 12, 1, 10);
		bottom.setRotationPoint(0F, -1F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		sideF = new ModelRenderer(this, 0, 12);
		sideF.addBox(-7F, -2F, -6F, 14, 3, 1);
		sideF.setRotationPoint(0F, -1F, 0F);
		sideF.setTextureSize(64, 32);
		sideF.mirror = true;
		setRotation(sideF, 0F, 0F, 0F);
		sideB = new ModelRenderer(this, 0, 12);
		sideB.addBox(-7F, -2F, 5F, 14, 3, 1);
		sideB.setRotationPoint(0F, -1F, 0F);
		sideB.setTextureSize(64, 32);
		sideB.mirror = true;
		setRotation(sideB, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 0, 17);
		sideR.addBox(-7F, -2F, -5F, 1, 3, 10);
		sideR.setRotationPoint(0F, -1F, 0F);
		sideR.setTextureSize(64, 32);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 0, 17);
		sideL.addBox(6F, -2F, -5F, 1, 3, 10);
		sideL.setRotationPoint(0F, -1F, 0F);
		sideL.setTextureSize(64, 32);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
		sideR2 = new ModelRenderer(this, 36, 0);
		sideR2.addBox(-8F, -2F, -2F, 1, 1, 4);
		sideR2.setRotationPoint(0F, -1F, 0F);
		sideR2.setTextureSize(64, 32);
		sideR2.mirror = true;
		setRotation(sideR2, 0F, 0F, 0F);
		sideL2 = new ModelRenderer(this, 36, 0);
		sideL2.addBox(7F, -2F, -2F, 1, 1, 4);
		sideL2.setRotationPoint(0F, -1F, 0F);
		sideL2.setTextureSize(64, 32);
		sideL2.mirror = true;
		setRotation(sideL2, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void setIndividualRotation(FoodEntityBase entity) {}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bottom.render(scale);
		sideF.render(scale);
		sideB.render(scale);
		sideR.render(scale);
		sideL.render(scale);
		sideR2.render(scale);
		sideL2.render(scale);
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
