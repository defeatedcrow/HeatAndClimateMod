package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBreakfastJ extends DCFoodModelBase {

	ModelRenderer obon;
	ModelRenderer obon2;
	ModelRenderer obon3;
	ModelRenderer obon4;
	ModelRenderer obon5;
	ModelRenderer rice1;
	ModelRenderer rice2;
	ModelRenderer rice3;
	ModelRenderer rice4;
	ModelRenderer rice5;
	ModelRenderer rice6;
	ModelRenderer rice7;
	ModelRenderer soup1;
	ModelRenderer soup2;
	ModelRenderer soup3;
	ModelRenderer soup4;
	ModelRenderer soup5;
	ModelRenderer soup6;
	ModelRenderer soup7;
	ModelRenderer soup8;
	ModelRenderer natto1;
	ModelRenderer natto2;
	ModelRenderer tukemono1;
	ModelRenderer tukemono2;
	ModelRenderer tukemono3;

	public ModelBreakfastJ(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		obon = new ModelRenderer(this, 0, 0);
		obon.addBox(-8F, 0F, -6F, 16, 1, 12);
		obon.setRotationPoint(0F, 0F, 0F);
		obon.setTextureSize(64, 64);
		obon.mirror = true;
		setRotation(obon, 0F, 0F, 0F);
		obon2 = new ModelRenderer(this, 0, 13);
		obon2.addBox(-8F, -1F, -6F, 1, 1, 12);
		obon2.setRotationPoint(0F, 0F, 0F);
		obon2.setTextureSize(64, 64);
		obon2.mirror = true;
		setRotation(obon2, 0F, 0F, 0F);
		obon3 = new ModelRenderer(this, 0, 13);
		obon3.addBox(-8F, -1F, -6F, 1, 1, 12);
		obon3.setRotationPoint(0F, 0F, 0F);
		obon3.setTextureSize(64, 64);
		obon3.mirror = true;
		setRotation(obon3, 0F, 3.141593F, 0F);
		obon4 = new ModelRenderer(this, 27, 14);
		obon4.addBox(-7F, -1F, -6F, 14, 1, 1);
		obon4.setRotationPoint(0F, 0F, 0F);
		obon4.setTextureSize(64, 64);
		obon4.mirror = true;
		setRotation(obon4, 0F, 0F, 0F);
		obon5 = new ModelRenderer(this, 27, 14);
		obon5.addBox(-7F, -1F, -6F, 14, 1, 1);
		obon5.setRotationPoint(0F, 0F, 0F);
		obon5.setTextureSize(64, 64);
		obon5.mirror = true;
		setRotation(obon5, 0F, 3.141593F, 0F);
		rice1 = new ModelRenderer(this, 0, 30);
		rice1.addBox(-5F, -1F, -3F, 2, 1, 2);
		rice1.setRotationPoint(0F, 0F, 0F);
		rice1.setTextureSize(64, 64);
		rice1.mirror = true;
		setRotation(rice1, 0F, 0F, 0F);
		rice2 = new ModelRenderer(this, 9, 28);
		rice2.addBox(-6F, -2F, -4F, 4, 1, 4);
		rice2.setRotationPoint(0F, 0F, 0F);
		rice2.setTextureSize(64, 64);
		rice2.mirror = true;
		setRotation(rice2, 0F, 0F, 0F);
		rice3 = new ModelRenderer(this, 0, 34);
		rice3.addBox(-2F, -4F, -2F, 4, 2, 1);
		rice3.setRotationPoint(-4F, 0F, -2F);
		rice3.setTextureSize(64, 64);
		rice3.mirror = true;
		setRotation(rice3, 0.2094395F, 0F, 0F);
		rice4 = new ModelRenderer(this, 0, 34);
		rice4.addBox(-2F, -4F, -2F, 4, 2, 1);
		rice4.setRotationPoint(-4F, 0F, -2F);
		rice4.setTextureSize(64, 64);
		rice4.mirror = true;
		setRotation(rice4, 0.2094395F, 1.570796F, 0F);
		rice5 = new ModelRenderer(this, 0, 34);
		rice5.addBox(-2F, -4F, -2F, 4, 2, 1);
		rice5.setRotationPoint(-4F, 0F, -2F);
		rice5.setTextureSize(64, 64);
		rice5.mirror = true;
		setRotation(rice5, 0.2094395F, -1.570796F, 0F);
		rice6 = new ModelRenderer(this, 0, 34);
		rice6.addBox(-2F, -4F, -2F, 4, 2, 1);
		rice6.setRotationPoint(-4F, 0F, -2F);
		rice6.setTextureSize(64, 64);
		rice6.mirror = true;
		setRotation(rice6, 0.2094395F, 3.141593F, 0F);
		rice7 = new ModelRenderer(this, 26, 27);
		rice7.addBox(-2F, -4.5F, -2F, 4, 2, 4);
		rice7.setRotationPoint(-4F, 0F, -2F);
		rice7.setTextureSize(64, 64);
		rice7.mirror = true;
		setRotation(rice7, 0F, 0F, 0F);
		soup1 = new ModelRenderer(this, 0, 40);
		soup1.addBox(3F, -1F, -3F, 2, 1, 2);
		soup1.setRotationPoint(0F, 0F, 0F);
		soup1.setTextureSize(64, 64);
		soup1.mirror = true;
		setRotation(soup1, 0F, 0F, 0F);
		soup2 = new ModelRenderer(this, 9, 38);
		soup2.addBox(2F, -2F, -4F, 4, 1, 4);
		soup2.setRotationPoint(0F, 0F, 0F);
		soup2.setTextureSize(64, 64);
		soup2.mirror = true;
		setRotation(soup2, 0F, 0F, 0F);
		soup3 = new ModelRenderer(this, 0, 44);
		soup3.addBox(-2F, -4F, -2.533333F, 4, 2, 1);
		soup3.setRotationPoint(4F, 0F, -2F);
		soup3.setTextureSize(64, 64);
		soup3.mirror = true;
		setRotation(soup3, 0F, 0F, 0F);
		soup4 = new ModelRenderer(this, 0, 44);
		soup4.addBox(-2F, -4F, -2.5F, 4, 2, 1);
		soup4.setRotationPoint(4F, 0F, -2F);
		soup4.setTextureSize(64, 64);
		soup4.mirror = true;
		setRotation(soup4, 0F, 1.570796F, 0F);
		soup5 = new ModelRenderer(this, 0, 44);
		soup5.addBox(-2F, -4F, -2.5F, 4, 2, 1);
		soup5.setRotationPoint(4F, 0F, -2F);
		soup5.setTextureSize(64, 64);
		soup5.mirror = true;
		setRotation(soup5, 0F, -1.570796F, 0F);
		soup6 = new ModelRenderer(this, 0, 44);
		soup6.addBox(-2F, -4F, -2.5F, 4, 2, 1);
		soup6.setRotationPoint(4F, 0F, -2F);
		soup6.setTextureSize(64, 64);
		soup6.mirror = true;
		setRotation(soup6, 0F, 3.141593F, 0F);
		soup7 = new ModelRenderer(this, 26, 38);
		soup7.addBox(-2F, -3.5F, -2F, 4, 0, 4);
		soup7.setRotationPoint(4F, 0F, -2F);
		soup7.setTextureSize(64, 64);
		soup7.mirror = true;
		setRotation(soup7, 0F, 0F, 0F);
		soup8 = new ModelRenderer(this, 26, 43);
		soup8.addBox(-2F, -2.5F, -2F, 4, 0, 4);
		soup8.setRotationPoint(4F, 0F, -2F);
		soup8.setTextureSize(64, 64);
		soup8.mirror = true;
		setRotation(soup8, 0F, 0F, 0F);
		natto1 = new ModelRenderer(this, 0, 49);
		natto1.addBox(-3F, -2F, 1F, 3, 2, 3);
		natto1.setRotationPoint(0F, 0F, 0F);
		natto1.setTextureSize(64, 64);
		natto1.mirror = true;
		setRotation(natto1, 0F, 0F, 0F);
		natto2 = new ModelRenderer(this, 0, 55);
		natto2.addBox(-2.5F, -2.5F, 1.5F, 2, 1, 2);
		natto2.setRotationPoint(0F, 0F, 0F);
		natto2.setTextureSize(64, 64);
		natto2.mirror = true;
		setRotation(natto2, 0F, 0F, 0F);
		tukemono1 = new ModelRenderer(this, 14, 49);
		tukemono1.addBox(1F, -1F, 1F, 3, 1, 3);
		tukemono1.setRotationPoint(0F, 0F, 0F);
		tukemono1.setTextureSize(64, 64);
		tukemono1.mirror = true;
		setRotation(tukemono1, 0F, 0F, 0F);
		tukemono2 = new ModelRenderer(this, 14, 54);
		tukemono2.addBox(2.5F, -2F, -2F, 2, 1, 1);
		tukemono2.setRotationPoint(0F, 0F, 0F);
		tukemono2.setTextureSize(64, 64);
		tukemono2.mirror = true;
		setRotation(tukemono2, 0F, -1.047198F, 0F);
		tukemono3 = new ModelRenderer(this, 14, 57);
		tukemono3.addBox(2.5F, -2F, -1F, 2, 1, 1);
		tukemono3.setRotationPoint(0F, 0F, 0F);
		tukemono3.setTextureSize(64, 64);
		tukemono3.mirror = true;
		setRotation(tukemono3, 0F, -1.134464F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		obon.render(scale);
		obon2.render(scale);
		obon3.render(scale);
		obon4.render(scale);
		obon5.render(scale);
		rice1.render(scale);
		rice2.render(scale);
		rice3.render(scale);
		rice4.render(scale);
		rice5.render(scale);
		rice6.render(scale);
		rice7.render(scale);
		soup1.render(scale);
		soup2.render(scale);
		soup3.render(scale);
		soup4.render(scale);
		soup5.render(scale);
		soup6.render(scale);
		soup8.render(scale);
		natto1.render(scale);
		natto2.render(scale);
		tukemono1.render(scale);
		tukemono2.render(scale);
		tukemono3.render(scale);

	}

	public void renderSoup() {
		soup7.render(0.0625F);
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
