package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBigGarlicSteak extends ModelSteakPlate {

	ModelRenderer steak;
	ModelRenderer garlic1;
	ModelRenderer garlic2;
	ModelRenderer garlic3;
	ModelRenderer butter;

	public ModelBigGarlicSteak(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		steak = new ModelRenderer(this, 0, 0);
		steak.addBox(-5F, -5F, -3.5F, 10, 5, 8);
		steak.setRotationPoint(0F, 0F, 0F);
		steak.setTextureSize(64, 32);
		steak.mirror = true;
		setRotation(steak, 0F, -0.0174533F, 0F);
		garlic1 = new ModelRenderer(this, 0, 14);
		garlic1.addBox(-1F, -6F, 2F, 2, 1, 2);
		garlic1.setRotationPoint(0F, 0F, 0F);
		garlic1.setTextureSize(64, 32);
		garlic1.mirror = true;
		setRotation(garlic1, 0F, 1.047198F, 0F);
		garlic2 = new ModelRenderer(this, 0, 14);
		garlic2.addBox(0F, -6F, 1F, 2, 1, 2);
		garlic2.setRotationPoint(0F, 0F, 0F);
		garlic2.setTextureSize(64, 32);
		garlic2.mirror = true;
		setRotation(garlic2, 0F, -2.094395F, 0F);
		garlic3 = new ModelRenderer(this, 0, 14);
		garlic3.addBox(0F, -6F, 0F, 2, 1, 2);
		garlic3.setRotationPoint(0F, 0F, 0F);
		garlic3.setTextureSize(64, 32);
		garlic3.mirror = true;
		setRotation(garlic3, 0F, 1.396263F, 0F);
		butter = new ModelRenderer(this, 10, 14);
		butter.addBox(-1F, -6.5F, -0.5F, 2, 2, 2);
		butter.setRotationPoint(0F, 0F, 0F);
		butter.setTextureSize(64, 32);
		butter.mirror = true;
		setRotation(butter, 0F, 0.0872665F, 0F);
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
		steak.render(scale);
		garlic1.render(scale);
		garlic2.render(scale);
		garlic3.render(scale);
		butter.render(scale);
	}

	@Override
	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			float f1 = 1.047198F;
			garlic1.rotateAngleY = f1 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f2 = -2.094395F;
			garlic2.rotateAngleY = f2 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f3 = 1.396263F;
			garlic3.rotateAngleY = f3 + (entity.getIndividual() / 16F) * (float) (Math.PI);
		}
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
