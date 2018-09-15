package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSquareBread extends DCFoodModelBase {

	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer base3;
	ModelRenderer base4;
	ModelRenderer base5;
	ModelRenderer bread1;
	ModelRenderer bread2;

	public ModelSquareBread(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		base1 = new ModelRenderer(this, 0, 0);
		base1.addBox(-3F, 7F, -4F, 6, 1, 8);
		base1.setRotationPoint(0F, -8F, 0F);
		base1.setTextureSize(64, 32);
		base1.mirror = true;
		setRotation(base1, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 0, 10);
		base2.addBox(-3F, 4F, -4F, 1, 3, 8);
		base2.setRotationPoint(0F, -8F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
		base3 = new ModelRenderer(this, 0, 10);
		base3.addBox(2F, 4F, -4F, 1, 3, 8);
		base3.setRotationPoint(0F, -8F, 0F);
		base3.setTextureSize(64, 32);
		base3.mirror = true;
		setRotation(base3, 0F, 0F, 0F);
		base4 = new ModelRenderer(this, 18, 10);
		base4.addBox(-2F, 4F, -4F, 4, 3, 1);
		base4.setRotationPoint(0F, -8F, 0F);
		base4.setTextureSize(64, 32);
		base4.mirror = true;
		setRotation(base4, 0F, 0F, 0F);
		base5 = new ModelRenderer(this, 18, 10);
		base5.addBox(-2F, 4F, 3F, 4, 3, 1);
		base5.setRotationPoint(0F, -8F, 0F);
		base5.setTextureSize(64, 32);
		base5.mirror = true;
		setRotation(base5, 0F, 0F, 0F);
		bread1 = new ModelRenderer(this, 32, 0);
		bread1.addBox(-2F, 3.9F, -3.5F, 4, 4, 7);
		bread1.setRotationPoint(0F, -8F, 0F);
		bread1.setTextureSize(64, 32);
		bread1.mirror = true;
		setRotation(bread1, 0F, 0F, 0F);
		bread2 = new ModelRenderer(this, 32, 12);
		bread2.addBox(-2.5F, 3F, -3.5F, 5, 1, 7);
		bread2.setRotationPoint(0F, -8F, 0F);
		bread2.setTextureSize(64, 32);
		bread2.mirror = true;
		setRotation(bread2, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale * 1.5F);
	}

	public void renderMold(float scale) {
		renderMold(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale * 1.5F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bread1.render(scale);
		if (isBaked()) {
			bread2.render(scale);
		}
	}

	public void renderMold(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		base1.render(scale);
		base2.render(scale);
		base3.render(scale);
		base4.render(scale);
		base5.render(scale);
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
