package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMochi extends DCFoodModelBase {

	ModelRenderer b1;
	ModelRenderer b2;
	ModelRenderer b3;
	ModelRenderer m1;
	ModelRenderer m2;
	ModelRenderer s1;
	ModelRenderer s2;

	public ModelMochi(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		b1 = new ModelRenderer(this, 0, 0);
		b1.addBox(-7F, -1F, -5F, 14, 1, 10);
		b1.setRotationPoint(0F, 2F, 0F);
		b1.setTextureSize(64, 32);
		b1.mirror = true;
		setRotation(b1, 0F, 0F, 0F);
		b2 = new ModelRenderer(this, 40, 2);
		b2.addBox(-6F, 0F, -5F, 1, 1, 10);
		b2.setRotationPoint(0F, 2F, 0F);
		b2.setTextureSize(64, 32);
		b2.mirror = true;
		setRotation(b2, 0F, 0F, 0F);
		b3 = new ModelRenderer(this, 40, 2);
		b3.addBox(5F, 0F, -5F, 1, 1, 10);
		b3.setRotationPoint(0F, 2F, 0F);
		b3.setTextureSize(64, 32);
		b3.mirror = true;
		setRotation(b3, 0F, 0F, 0F);
		m1 = new ModelRenderer(this, 0, 16);
		m1.addBox(-4.2F, -3F, -2.5F, 4, 2, 4);
		m1.setRotationPoint(0F, 2F, 0F);
		m1.setTextureSize(64, 32);
		m1.mirror = true;
		setRotation(m1, 0F, 0.3490659F, 0F);
		m2 = new ModelRenderer(this, 0, 23);
		m2.addBox(0.3F, -3F, -1F, 4, 2, 4);
		m2.setRotationPoint(0F, 2F, 0F);
		m2.setTextureSize(64, 32);
		m2.mirror = true;
		setRotation(m2, 0F, 0.3490659F, 0F);
		s1 = new ModelRenderer(this, 18, 16);
		s1.addBox(0.3F, -3F, -1F, 3, 2, 4);
		s1.setRotationPoint(0F, 2F, 0F);
		s1.setTextureSize(64, 32);
		s1.mirror = true;
		setRotation(s1, 0F, 0.3490659F, 0F);
		s2 = new ModelRenderer(this, 18, 16);
		s2.addBox(-3.2F, -3F, -2.5F, 3, 2, 4);
		s2.setRotationPoint(0F, 2F, 0F);
		s2.setTextureSize(64, 32);
		s2.mirror = true;
		setRotation(s2, 0F, 0.3490659F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		b1.render(0.0625F);
		b2.render(0.0625F);
		b3.render(0.0625F);
	}

	public void rendetMochi1() {
		m1.render(0.0625F);
		m2.render(0.0625F);
	}

	public void rendetMochi2() {
		s1.render(0.0625F);
		s2.render(0.0625F);
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
