package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelOnigiri extends DCFoodModelBase {

	ModelRenderer shape1;
	ModelRenderer shape2;
	ModelRenderer shape3;
	ModelRenderer shape4;

	public ModelOnigiri(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 16;

		shape1 = new ModelRenderer(this, 0, 0);
		shape1.addBox(-2.5F, -0.5F, -1.5F, 5, 1, 3);
		shape1.setRotationPoint(0F, 0F, 0F);
		shape1.setTextureSize(32, 16);
		shape1.mirror = true;
		setRotation(shape1, 0F, 0F, 0F);
		shape2 = new ModelRenderer(this, 0, 6);
		shape2.addBox(-3F, -3F, -2F, 6, 3, 4);
		shape2.setRotationPoint(0F, 0F, 0F);
		shape2.setTextureSize(32, 16);
		shape2.mirror = true;
		setRotation(shape2, 0F, 0F, 0F);
		shape3 = new ModelRenderer(this, 18, 0);
		shape3.addBox(-2.5F, -4F, -1.5F, 5, 1, 3);
		shape3.setRotationPoint(0F, 0F, 0F);
		shape3.setTextureSize(32, 16);
		shape3.mirror = true;
		setRotation(shape3, 0F, 0F, 0F);
		shape4 = new ModelRenderer(this, 22, 6);
		shape4.addBox(-1.5F, -5F, -1F, 3, 1, 2);
		shape4.setRotationPoint(0F, 0F, 0F);
		shape4.setTextureSize(32, 16);
		shape4.mirror = true;
		setRotation(shape4, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		shape1.render(0.0625F);
		shape2.render(0.0625F);
		shape3.render(0.0625F);
		shape4.render(0.0625F);
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
