package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;

@SideOnly(Side.CLIENT)
public class ModelTart extends DCFoodModelBase {

	ModelRenderer base;
	ModelRenderer inner;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer top;

	public ModelTart(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-6F, 7F, -6F, 12, 1, 12);
		base.setRotationPoint(0F, -8F, 0F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		inner = new ModelRenderer(this, 0, 14);
		inner.addBox(-4F, 3.5F, -4F, 8, 3, 8);
		inner.setRotationPoint(0F, -8F, 0F);
		inner.setTextureSize(64, 64);
		inner.mirror = true;
		setRotation(inner, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 26);
		side1.addBox(-5F, 3F, -5F, 10, 4, 1);
		side1.setRotationPoint(0F, -8F, 0F);
		side1.setTextureSize(64, 64);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 26);
		side2.addBox(-5F, 3F, -5F, 10, 4, 1);
		side2.setRotationPoint(0F, -8F, 0F);
		side2.setTextureSize(64, 64);
		side2.mirror = true;
		setRotation(side2, 0F, 3.141593F, 0F);
		side3 = new ModelRenderer(this, 0, 32);
		side3.addBox(-4F, 3F, -5F, 8, 4, 1);
		side3.setRotationPoint(0F, -8F, 0F);
		side3.setTextureSize(64, 64);
		side3.mirror = true;
		setRotation(side3, 0F, 1.570796F, 0F);
		side4 = new ModelRenderer(this, 0, 32);
		side4.addBox(-4F, 3F, -5F, 8, 4, 1);
		side4.setRotationPoint(0F, -8F, 0F);
		side4.setTextureSize(64, 64);
		side4.mirror = true;
		setRotation(side4, 0F, -1.570796F, 0F);
		top = new ModelRenderer(this, 24, 26);
		top.addBox(-4F, 3.2F, -4F, 8, 0, 8);
		top.setRotationPoint(0F, -8F, 0F);
		top.setTextureSize(64, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		base.render(0.0625F);
		inner.render(0.0625F);
		side1.render(0.0625F);
		side2.render(0.0625F);
		side3.render(0.0625F);
		side4.render(0.0625F);
		top.render(0.0625F);
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
