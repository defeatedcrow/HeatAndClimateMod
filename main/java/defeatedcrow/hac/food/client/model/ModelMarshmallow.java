package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMarshmallow extends DCFoodModelBase {

	ModelRenderer stick;
	ModelRenderer block1;

	public ModelMarshmallow(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 32;

		stick = new ModelRenderer(this, 0, 0);
		stick.addBox(-0.5F, -8F, -0.5F, 1, 16, 1);
		stick.setRotationPoint(0F, -8F, 0F);
		stick.setTextureSize(32, 32);
		stick.mirror = true;
		setRotation(stick, 0F, 0F, 0F);
		block1 = new ModelRenderer(this, 6, 0);
		block1.addBox(-2F, -7F, -2F, 4, 3, 4);
		block1.setRotationPoint(0F, -8F, 0F);
		block1.setTextureSize(32, 32);
		block1.mirror = true;
		setRotation(block1, 0.0523599F, 0.2617994F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			float f1 = 0.0523599F;
			block1.rotateAngleY = f1 + (entity.getIndividual() / 33F) * (float) (Math.PI);
		}
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		stick.render(scale);
		block1.render(scale);
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
