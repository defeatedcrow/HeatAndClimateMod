package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWalnutBread extends DCFoodModelBase {

	ModelRenderer body;
	ModelRenderer b1;
	ModelRenderer b2;
	ModelRenderer b3;
	ModelRenderer b4;
	ModelRenderer b5;

	public ModelWalnutBread(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this, 20, 0);
		body.addBox(-3F, -2F, -3F, 6, 2, 6);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		b1 = new ModelRenderer(this, 0, 0);
		b1.addBox(-2.5F, -2.8F, 1F, 5, 3, 4);
		b1.setRotationPoint(0F, 0F, 0F);
		b1.setTextureSize(64, 32);
		b1.mirror = true;
		setRotation(b1, 0F, 0F, 0F);
		b2 = new ModelRenderer(this, 0, 0);
		b2.addBox(-2.5F, -2.7F, 1F, 5, 3, 4);
		b2.setRotationPoint(0F, 0F, 0F);
		b2.setTextureSize(64, 32);
		b2.mirror = true;
		setRotation(b2, 0F, 1.256637F, 0F);
		b3 = new ModelRenderer(this, 0, 0);
		b3.addBox(-2.5F, -2.5F, 1F, 5, 3, 4);
		b3.setRotationPoint(0F, 0F, 0F);
		b3.setTextureSize(64, 32);
		b3.mirror = true;
		setRotation(b3, 0F, 2.478368F, 0F);
		b4 = new ModelRenderer(this, 0, 0);
		b4.addBox(-2.5F, -2.6F, 1F, 5, 3, 4);
		b4.setRotationPoint(0F, 0F, 0F);
		b4.setTextureSize(64, 32);
		b4.mirror = true;
		setRotation(b4, 0F, -2.478368F, 0F);
		b5 = new ModelRenderer(this, 0, 0);
		b5.addBox(-2.5F, -2.7F, 1F, 5, 3, 4);
		b5.setRotationPoint(0F, 0F, 0F);
		b5.setTextureSize(64, 32);
		b5.mirror = true;
		setRotation(b5, 0F, -1.256637F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		body.render(scale);
		b1.render(scale);
		b2.render(scale);
		b3.render(scale);
		b4.render(scale);
		b5.render(scale);
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
