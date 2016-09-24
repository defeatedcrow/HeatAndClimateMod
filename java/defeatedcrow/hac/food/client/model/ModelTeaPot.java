package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTeaPot extends DCTileModelBase {

	// fields
	ModelRenderer shape1;
	ModelRenderer shape2;
	ModelRenderer shape4;
	ModelRenderer shape5;
	ModelRenderer shape6;
	ModelRenderer shape7;
	ModelRenderer shape8;
	ModelRenderer shape9;
	ModelRenderer shape10;
	ModelRenderer shape11;
	ModelRenderer shape12;

	public ModelTeaPot() {
		textureWidth = 64;
		textureHeight = 64;

		shape1 = new ModelRenderer(this, 4, 14);
		shape1.addBox(-4F, 0F, -4F, 8, 1, 8);
		shape1.setRotationPoint(0F, 22F, 0F);
		shape1.setTextureSize(64, 64);
		shape1.mirror = true;
		setRotation(shape1, 0F, 0F, 0F);
		shape2 = new ModelRenderer(this, 0, 12);
		shape2.addBox(-5F, -6F, -5F, 10, 6, 10);
		shape2.setRotationPoint(0F, 22F, 0F);
		shape2.setTextureSize(64, 64);
		shape2.mirror = true;
		setRotation(shape2, 0F, 0F, 0F);
		shape4 = new ModelRenderer(this, 4, 14);
		shape4.addBox(-4F, -8F, -4F, 8, 2, 8);
		shape4.setRotationPoint(0F, 22F, 0F);
		shape4.setTextureSize(64, 64);
		shape4.mirror = true;
		setRotation(shape4, 0F, 0F, 0F);
		shape5 = new ModelRenderer(this, 6, 15);
		shape5.addBox(-3.5F, -9F, -3.5F, 7, 1, 7);
		shape5.setRotationPoint(0F, 22F, 0F);
		shape5.setTextureSize(64, 64);
		shape5.mirror = true;
		setRotation(shape5, 0F, 0F, 0F);
		shape6 = new ModelRenderer(this, 4, 14);
		shape6.addBox(-4F, -10F, -4F, 8, 1, 8);
		shape6.setRotationPoint(0F, 22F, 0F);
		shape6.setTextureSize(64, 64);
		shape6.mirror = true;
		setRotation(shape6, 0F, 0F, 0F);
		shape7 = new ModelRenderer(this, 10, 0);
		shape7.addBox(-1F, -11F, -1F, 2, 1, 2);
		shape7.setRotationPoint(0F, 22F, 0F);
		shape7.setTextureSize(64, 64);
		shape7.mirror = true;
		setRotation(shape7, 0F, 0F, 0F);
		shape8 = new ModelRenderer(this, 0, 0);
		shape8.addBox(-1F, -7F, -1F, 2, 8, 2);
		shape8.setRotationPoint(0F, 20F, -4F);
		shape8.setTextureSize(64, 64);
		shape8.mirror = true;
		setRotation(shape8, 0.5235988F, 0F, 0F);
		shape9 = new ModelRenderer(this, 10, 4);
		shape9.addBox(-1F, -6.6F, -10.6F, 2, 2, 2);
		shape9.setRotationPoint(0F, 22F, 0F);
		shape9.setTextureSize(64, 64);
		shape9.mirror = true;
		setRotation(shape9, -0.2617994F, 0F, 0F);
		shape10 = new ModelRenderer(this, 0, 30);
		shape10.addBox(-1F, -8F, 4F, 2, 1, 4);
		shape10.setRotationPoint(0F, 22F, 0F);
		shape10.setTextureSize(64, 64);
		shape10.mirror = true;
		setRotation(shape10, 0F, 0F, 0F);
		shape11 = new ModelRenderer(this, 0, 36);
		shape11.addBox(-1F, -7F, 7F, 2, 5, 1);
		shape11.setRotationPoint(0F, 22F, 0F);
		shape11.setTextureSize(64, 64);
		shape11.mirror = true;
		setRotation(shape11, 0F, 0F, 0F);
		shape12 = new ModelRenderer(this, 0, 43);
		shape12.addBox(-1F, -2F, 5F, 2, 1, 3);
		shape12.setRotationPoint(0F, 22F, 0F);
		shape12.setTextureSize(64, 64);
		shape12.mirror = true;
		setRotation(shape12, 0F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		shape1.render(0.0625F);
		shape2.render(0.0625F);
		shape4.render(0.0625F);
		shape5.render(0.0625F);
		shape6.render(0.0625F);
		shape7.render(0.0625F);
		shape8.render(0.0625F);
		shape9.render(0.0625F);
		shape10.render(0.0625F);
		shape11.render(0.0625F);
		shape12.render(0.0625F);
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
