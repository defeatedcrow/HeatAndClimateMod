package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelUdon extends DCFoodModelBase {

	ModelRenderer bowl1;
	ModelRenderer bowl2;
	ModelRenderer bowl3;
	ModelRenderer bowl4;
	ModelRenderer bowl5;
	ModelRenderer bowl6;
	ModelRenderer soup;
	ModelRenderer n11;
	ModelRenderer n12;
	ModelRenderer n13;
	ModelRenderer n21;
	ModelRenderer n22;
	ModelRenderer n23;
	ModelRenderer n31;
	ModelRenderer n32;
	ModelRenderer n33;
	ModelRenderer soup2;
	ModelRenderer egg;

	public ModelUdon(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		bowl1 = new ModelRenderer(this, 0, 0);
		bowl1.addBox(-3F, 0F, -3F, 6, 1, 6);
		bowl1.setRotationPoint(0F, 0F, 0F);
		bowl1.setTextureSize(64, 32);
		bowl1.mirror = true;
		setRotation(bowl1, 0F, 0F, 0F);
		bowl2 = new ModelRenderer(this, 0, 10);
		bowl2.addBox(-6F, -1F, -6F, 12, 1, 12);
		bowl2.setRotationPoint(0F, 0F, 0F);
		bowl2.setTextureSize(64, 32);
		bowl2.mirror = true;
		setRotation(bowl2, 0F, 0F, 0F);
		bowl3 = new ModelRenderer(this, 25, 0);
		bowl3.addBox(-6F, -9F, 6F, 12, 8, 1);
		bowl3.setRotationPoint(0F, 0F, 0F);
		bowl3.setTextureSize(64, 32);
		bowl3.mirror = true;
		setRotation(bowl3, 0F, 3.141593F, 0F);
		bowl4 = new ModelRenderer(this, 25, 0);
		bowl4.addBox(-6F, -9F, 6F, 12, 8, 1);
		bowl4.setRotationPoint(0F, 0F, 0F);
		bowl4.setTextureSize(64, 32);
		bowl4.mirror = true;
		setRotation(bowl4, 0F, 0F, 0F);
		bowl5 = new ModelRenderer(this, 25, 0);
		bowl5.addBox(-6F, -9F, 6F, 12, 8, 1);
		bowl5.setRotationPoint(0F, 0F, 0F);
		bowl5.setTextureSize(64, 32);
		bowl5.mirror = true;
		setRotation(bowl5, 0F, 1.570796F, 0F);
		bowl6 = new ModelRenderer(this, 25, 0);
		bowl6.addBox(-6F, -9F, 6F, 12, 8, 1);
		bowl6.setRotationPoint(0F, 0F, 0F);
		bowl6.setTextureSize(64, 32);
		bowl6.mirror = true;
		setRotation(bowl6, 0F, -1.570796F, 0F);
		soup = new ModelRenderer(this, 0, 24);
		soup.addBox(-6F, -6F, -6F, 12, 0, 12);
		soup.setRotationPoint(0F, 0F, 0F);
		soup.setTextureSize(64, 64);
		soup.mirror = true;
		setRotation(soup, 0F, 0F, 0F);
		n11 = new ModelRenderer(this, 0, 37);
		n11.addBox(-1F, -6F, 1F, 6, 3, 1);
		n11.setRotationPoint(0F, 0F, 2F);
		n11.setTextureSize(64, 64);
		n11.mirror = true;
		setRotation(n11, 0.3490659F, 0.3490659F, 0F);
		n12 = new ModelRenderer(this, 0, 37);
		n12.addBox(-1F, -5.5F, 2.2F, 6, 3, 1);
		n12.setRotationPoint(0F, 0F, 2F);
		n12.setTextureSize(64, 64);
		n12.mirror = true;
		setRotation(n12, 0.3490659F, 0.3490659F, 0F);
		n13 = new ModelRenderer(this, 0, 37);
		n13.addBox(-1F, -5F, 3.4F, 6, 3, 1);
		n13.setRotationPoint(0F, 0F, 2F);
		n13.setTextureSize(64, 64);
		n13.mirror = true;
		setRotation(n13, 0.3490659F, 0.3490659F, 0F);
		n21 = new ModelRenderer(this, 0, 37);
		n21.addBox(-4F, -7F, -1F, 6, 3, 1);
		n21.setRotationPoint(0F, 0F, 0F);
		n21.setTextureSize(64, 64);
		n21.mirror = true;
		setRotation(n21, 0.5235988F, -1.047198F, 0F);
		n22 = new ModelRenderer(this, 0, 37);
		n22.addBox(-4F, -6.5F, 0.2F, 6, 3, 1);
		n22.setRotationPoint(0F, 0F, 0F);
		n22.setTextureSize(64, 64);
		n22.mirror = true;
		setRotation(n22, 0.5235988F, -1.047198F, 0F);
		n23 = new ModelRenderer(this, 0, 37);
		n23.addBox(-4F, -6F, 1.4F, 6, 3, 1);
		n23.setRotationPoint(0F, 0F, 0F);
		n23.setTextureSize(64, 64);
		n23.mirror = true;
		setRotation(n23, 0.5235988F, -1.047198F, 0F);
		n31 = new ModelRenderer(this, 15, 37);
		n31.addBox(-4F, -7F, 0F, 8, 3, 1);
		n31.setRotationPoint(0F, 0F, 0F);
		n31.setTextureSize(64, 64);
		n31.mirror = true;
		setRotation(n31, -0.2094395F, -1.500983F, 0F);
		n32 = new ModelRenderer(this, 15, 37);
		n32.addBox(-4F, -7.2F, 1.2F, 8, 3, 1);
		n32.setRotationPoint(0F, 0F, 0F);
		n32.setTextureSize(64, 64);
		n32.mirror = true;
		setRotation(n32, -0.2094395F, -1.500983F, 0F);
		n33 = new ModelRenderer(this, 15, 37);
		n33.addBox(-4F, -7.4F, 2.4F, 8, 3, 1);
		n33.setRotationPoint(0F, 0F, 0F);
		n33.setTextureSize(64, 64);
		n33.mirror = true;
		setRotation(n33, -0.2094395F, -1.500983F, 0F);
		soup2 = new ModelRenderer(this, 0, 42);
		soup2.addBox(-5F, -7F, -5F, 10, 0, 10);
		soup2.setRotationPoint(0F, 0F, 0F);
		soup2.setTextureSize(64, 64);
		soup2.mirror = true;
		setRotation(soup2, 0F, 0F, 0F);
		egg = new ModelRenderer(this, 0, 53);
		egg.addBox(-2F, -7.5F, -2F, 4, 2, 4);
		egg.setRotationPoint(0F, 0F, 0F);
		egg.setTextureSize(64, 64);
		egg.mirror = true;
		setRotation(egg, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bowl1.render(scale);
		bowl2.render(scale);
		bowl3.render(scale);
		bowl4.render(scale);
		bowl5.render(scale);
		bowl6.render(scale);

		n11.render(scale);
		n12.render(scale);
		n13.render(scale);
		n21.render(scale);
		n22.render(scale);
		n23.render(scale);
		n31.render(scale);
		n32.render(scale);
		n33.render(scale);
		soup2.render(scale);
		egg.render(scale);
	}

	public void renderSoup() {
		soup.render(0.0625F);
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
