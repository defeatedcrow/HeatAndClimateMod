package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRiceBowl extends DCFoodModelBase {

	ModelRenderer bowl1;
	ModelRenderer bowl2;
	ModelRenderer bowl3;
	ModelRenderer rice1;
	ModelRenderer rice2;

	public ModelRiceBowl(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		bowl1 = new ModelRenderer(this, 0, 0);
		bowl1.addBox(-2F, 7F, -2F, 4, 1, 4);
		bowl1.setRotationPoint(0F, -8F, 0F);
		bowl1.setTextureSize(64, 32);
		bowl1.mirror = true;
		setRotation(bowl1, 0F, 0F, 0F);
		bowl2 = new ModelRenderer(this, 0, 6);
		bowl2.addBox(-3.5F, 6F, -3.5F, 7, 1, 7);
		bowl2.setRotationPoint(0F, -8F, 0F);
		bowl2.setTextureSize(64, 32);
		bowl2.mirror = true;
		setRotation(bowl2, 0F, 0F, 0F);
		bowl3 = new ModelRenderer(this, 32, 0);
		bowl3.addBox(-4F, 3F, -4F, 8, 3, 8);
		bowl3.setRotationPoint(0F, -8F, 0F);
		bowl3.setTextureSize(64, 32);
		bowl3.mirror = true;
		setRotation(bowl3, 0F, 0F, 0F);
		rice1 = new ModelRenderer(this, 0, 16);
		rice1.addBox(-3F, 1.5F, -3F, 6, 2, 6);
		rice1.setRotationPoint(0F, -8F, 0F);
		rice1.setTextureSize(64, 32);
		rice1.mirror = true;
		setRotation(rice1, 0F, 0F, 0F);
		rice2 = new ModelRenderer(this, 0, 25);
		rice2.addBox(-2F, 0.5F, -2F, 4, 1, 4);
		rice2.setRotationPoint(0F, -8F, 0F);
		rice2.setTextureSize(64, 32);
		rice2.mirror = true;
		setRotation(rice2, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bowl1.render(scale);
		bowl2.render(scale);
		bowl3.render(scale);
		rice1.render(scale);
		rice2.render(scale);
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
