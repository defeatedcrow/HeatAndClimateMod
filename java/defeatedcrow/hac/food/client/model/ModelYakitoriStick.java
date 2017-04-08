package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelYakitoriStick extends DCFoodModelBase {

	ModelRenderer stick;
	ModelRenderer chicken1;
	ModelRenderer chicken2;
	ModelRenderer chicken3;
	ModelRenderer chicken4;

	public ModelYakitoriStick(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		stick = new ModelRenderer(this, 0, 0);
		stick.addBox(-0.5F, -8F, -0.5F, 1, 16, 1);
		stick.setRotationPoint(0F, -8F, 0F);
		stick.setTextureSize(64, 32);
		stick.mirror = true;
		setRotation(stick, 0F, 0F, 0F);
		chicken1 = new ModelRenderer(this, 6, 0);
		chicken1.addBox(-2F, 2F, -2F, 4, 3, 4);
		chicken1.setRotationPoint(0F, -8F, 0F);
		chicken1.setTextureSize(64, 32);
		chicken1.mirror = true;
		setRotation(chicken1, 0.0523599F, 0.2617994F, 0F);
		chicken2 = new ModelRenderer(this, 6, 0);
		chicken2.addBox(-2F, -1F, -2F, 4, 3, 4);
		chicken2.setRotationPoint(0F, -8F, 0F);
		chicken2.setTextureSize(64, 32);
		chicken2.mirror = true;
		setRotation(chicken2, -0.0698132F, -0.0523599F, 0F);
		chicken3 = new ModelRenderer(this, 6, 0);
		chicken3.addBox(-2F, -4F, -2F, 4, 3, 4);
		chicken3.setRotationPoint(0F, -8F, 0F);
		chicken3.setTextureSize(64, 32);
		chicken3.mirror = true;
		setRotation(chicken3, 0F, 0.2094395F, 0F);
		chicken4 = new ModelRenderer(this, 6, 0);
		chicken4.addBox(-2F, -7F, -2F, 4, 3, 4);
		chicken4.setRotationPoint(0F, -8F, 0F);
		chicken4.setTextureSize(64, 32);
		chicken4.mirror = true;
		setRotation(chicken4, 0F, -0.1745329F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			float f1 = 0.0523599F;
			chicken1.rotateAngleY = f1 + (entity.getIndividual() / 33F) * (float) (Math.PI);
			float f2 = -0.0523599F;
			chicken2.rotateAngleY = f2 + (entity.getIndividual() / 30F) * (float) (Math.PI);
			float f3 = 0.2094395F;
			chicken3.rotateAngleY = f3 + (entity.getIndividual() / 32F) * (float) (Math.PI);
			float f4 = -0.1745329F;
			chicken4.rotateAngleY = f4 + (entity.getIndividual() / 36F) * (float) (Math.PI);
		}
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		stick.render(scale);
		chicken1.render(scale);
		chicken2.render(scale);
		chicken3.render(scale);
		chicken4.render(scale);
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
