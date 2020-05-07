package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBreakfastJ2 extends DCFoodModelBase {

	public ModelRenderer obon;
	public ModelRenderer obon2;
	public ModelRenderer obon3;
	public ModelRenderer obon4;
	public ModelRenderer obon5;
	public ModelRenderer rice1;
	public ModelRenderer rice2;
	public ModelRenderer rice3;
	public ModelRenderer rice4;
	public ModelRenderer rice5;
	public ModelRenderer rice6;
	public ModelRenderer rice7;
	public ModelRenderer soup1;
	public ModelRenderer soup2;
	public ModelRenderer soup3;
	public ModelRenderer soup4;
	public ModelRenderer soup5;
	public ModelRenderer soup6;
	public ModelRenderer soup7;
	public ModelRenderer soup8;
	public ModelRenderer fish1;
	public ModelRenderer fish2;
	public ModelRenderer fish3;
	public ModelRenderer fish4;
	public ModelRenderer tukemono1;
	public ModelRenderer tukemono2;
	public ModelRenderer tukemono3;

	public ModelBreakfastJ2(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		obon = new ModelRenderer(this, 0, 0);
		obon.addBox(-8F, 0F, -6F, 16, 1, 12, 0F);
		obon.setRotationPoint(0F, 0F, 0F);
		obon.rotateAngleX = 0F;
		obon.rotateAngleY = 0F;
		obon.rotateAngleZ = 0F;
		obon.mirror = false;
		obon2 = new ModelRenderer(this, 0, 13);
		obon2.addBox(-8F, -1F, -6F, 1, 1, 12, 0F);
		obon2.setRotationPoint(0F, 0F, 0F);
		obon2.rotateAngleX = 0F;
		obon2.rotateAngleY = 0F;
		obon2.rotateAngleZ = 0F;
		obon2.mirror = false;
		obon3 = new ModelRenderer(this, 0, 13);
		obon3.addBox(-8F, -1F, -6F, 1, 1, 12, 0F);
		obon3.setRotationPoint(0F, 0F, 0F);
		obon3.rotateAngleX = 0F;
		obon3.rotateAngleY = 3.141593F;
		obon3.rotateAngleZ = 0F;
		obon3.mirror = false;
		obon4 = new ModelRenderer(this, 27, 14);
		obon4.addBox(-7F, -1F, -6F, 14, 1, 1, 0F);
		obon4.setRotationPoint(0F, 0F, 0F);
		obon4.rotateAngleX = 0F;
		obon4.rotateAngleY = 0F;
		obon4.rotateAngleZ = 0F;
		obon4.mirror = false;
		obon5 = new ModelRenderer(this, 27, 14);
		obon5.addBox(-7F, -1F, -6F, 14, 1, 1, 0F);
		obon5.setRotationPoint(0F, 0F, 0F);
		obon5.rotateAngleX = 0F;
		obon5.rotateAngleY = 3.141593F;
		obon5.rotateAngleZ = 0F;
		obon5.mirror = false;
		rice1 = new ModelRenderer(this, 0, 30);
		rice1.addBox(-5F, -1F, -4F, 2, 1, 2, 0F);
		rice1.setRotationPoint(0F, 0F, 0F);
		rice1.rotateAngleX = 0F;
		rice1.rotateAngleY = 0F;
		rice1.rotateAngleZ = 0F;
		rice1.mirror = false;
		rice2 = new ModelRenderer(this, 9, 28);
		rice2.addBox(-6F, -2F, -5F, 4, 1, 4, 0F);
		rice2.setRotationPoint(0F, 0F, 0F);
		rice2.rotateAngleX = 0F;
		rice2.rotateAngleY = 0F;
		rice2.rotateAngleZ = 0F;
		rice2.mirror = false;
		rice3 = new ModelRenderer(this, 0, 34);
		rice3.addBox(-2F, -4F, -2F, 4, 2, 1, 0F);
		rice3.setRotationPoint(-4F, 0F, -3F);
		rice3.rotateAngleX = 0.2094395F;
		rice3.rotateAngleY = 0F;
		rice3.rotateAngleZ = 0F;
		rice3.mirror = false;
		rice4 = new ModelRenderer(this, 0, 34);
		rice4.addBox(-2F, -4F, -2F, 4, 2, 1, 0F);
		rice4.setRotationPoint(-4F, 0F, -3F);
		rice4.rotateAngleX = 0.2094395F;
		rice4.rotateAngleY = 1.570796F;
		rice4.rotateAngleZ = 0F;
		rice4.mirror = false;
		rice5 = new ModelRenderer(this, 0, 34);
		rice5.addBox(-2F, -4F, -2F, 4, 2, 1, 0F);
		rice5.setRotationPoint(-4F, 0F, -3F);
		rice5.rotateAngleX = 0.2094395F;
		rice5.rotateAngleY = -1.570796F;
		rice5.rotateAngleZ = 0F;
		rice5.mirror = false;
		rice6 = new ModelRenderer(this, 0, 34);
		rice6.addBox(-2F, -4F, -2F, 4, 2, 1, 0F);
		rice6.setRotationPoint(-4F, 0F, -3F);
		rice6.rotateAngleX = 0.2094395F;
		rice6.rotateAngleY = 3.141593F;
		rice6.rotateAngleZ = 0F;
		rice6.mirror = false;
		rice7 = new ModelRenderer(this, 26, 27);
		rice7.addBox(-2F, -4.5F, -2F, 4, 2, 4, 0F);
		rice7.setRotationPoint(-4F, 0F, -3F);
		rice7.rotateAngleX = 0F;
		rice7.rotateAngleY = 0F;
		rice7.rotateAngleZ = 0F;
		rice7.mirror = false;
		soup1 = new ModelRenderer(this, 0, 40);
		soup1.addBox(3F, -1F, -4F, 2, 1, 2, 0F);
		soup1.setRotationPoint(0F, 0F, 0F);
		soup1.rotateAngleX = 0F;
		soup1.rotateAngleY = 0F;
		soup1.rotateAngleZ = 0F;
		soup1.mirror = false;
		soup2 = new ModelRenderer(this, 9, 38);
		soup2.addBox(2F, -2F, -5F, 4, 1, 4, 0F);
		soup2.setRotationPoint(0F, 0F, 0F);
		soup2.rotateAngleX = 0F;
		soup2.rotateAngleY = 0F;
		soup2.rotateAngleZ = 0F;
		soup2.mirror = false;
		soup3 = new ModelRenderer(this, 0, 44);
		soup3.addBox(-2F, -4F, -3.533333F, 4, 2, 1, 0F);
		soup3.setRotationPoint(4F, 0F, -2F);
		soup3.rotateAngleX = 0F;
		soup3.rotateAngleY = 0F;
		soup3.rotateAngleZ = 0F;
		soup3.mirror = false;
		soup4 = new ModelRenderer(this, 0, 44);
		soup4.addBox(-1F, -4F, -2.5F, 4, 2, 1, 0F);
		soup4.setRotationPoint(4F, 0F, -2F);
		soup4.rotateAngleX = 0F;
		soup4.rotateAngleY = 1.570796F;
		soup4.rotateAngleZ = 0F;
		soup4.mirror = false;
		soup5 = new ModelRenderer(this, 0, 44);
		soup5.addBox(-3F, -4F, -2.5F, 4, 2, 1, 0F);
		soup5.setRotationPoint(4F, 0F, -2F);
		soup5.rotateAngleX = 0F;
		soup5.rotateAngleY = -1.570796F;
		soup5.rotateAngleZ = 0F;
		soup5.mirror = false;
		soup6 = new ModelRenderer(this, 0, 44);
		soup6.addBox(-2F, -4F, -1.5F, 4, 2, 1, 0F);
		soup6.setRotationPoint(4F, 0F, -2F);
		soup6.rotateAngleX = 0F;
		soup6.rotateAngleY = 3.141593F;
		soup6.rotateAngleZ = 0F;
		soup6.mirror = false;
		soup7 = new ModelRenderer(this, 26, 38);
		soup7.addBox(-2F, -3.5F, -3F, 4, 0, 4, 0F);
		soup7.setRotationPoint(4F, 0F, -2F);
		soup7.rotateAngleX = 0F;
		soup7.rotateAngleY = 0F;
		soup7.rotateAngleZ = 0F;
		soup7.mirror = false;
		soup8 = new ModelRenderer(this, 26, 43);
		soup8.addBox(-2F, -2.5F, -3F, 4, 0, 4, 0F);
		soup8.setRotationPoint(4F, 0F, -2F);
		soup8.rotateAngleX = 0F;
		soup8.rotateAngleY = 0F;
		soup8.rotateAngleZ = 0F;
		soup8.mirror = false;
		fish1 = new ModelRenderer(this, 0, 53);
		fish1.addBox(-2.5F, -1.5F, 1F, 3, 1, 2, 0F);
		fish1.setRotationPoint(0F, 0F, 0F);
		fish1.rotateAngleX = 0F;
		fish1.rotateAngleY = 0.08726646F;
		fish1.rotateAngleZ = 0F;
		fish1.mirror = false;
		fish2 = new ModelRenderer(this, 0, 49);
		fish2.addBox(-3.5F, -1.8F, 1.5F, 2, 1, 2, 0F);
		fish2.setRotationPoint(0F, 0F, 0F);
		fish2.rotateAngleX = 0F;
		fish2.rotateAngleY = -0.1745329F;
		fish2.rotateAngleZ = 0F;
		fish2.mirror = false;
		fish3 = new ModelRenderer(this, 28, 49);
		fish3.addBox(-5F, -0.8F, 0F, 7, 1, 5, 0F);
		fish3.setRotationPoint(0F, 0F, 0F);
		fish3.rotateAngleX = 0F;
		fish3.rotateAngleY = -0.05235988F;
		fish3.rotateAngleZ = 0F;
		fish3.mirror = false;
		fish4 = new ModelRenderer(this, 0, 57);
		fish4.addBox(0F, -0.5F, 0F, 3, 0, 3, 0F);
		fish4.setRotationPoint(-2F, 0F, 3F);
		fish4.rotateAngleX = 0.3490658F;
		fish4.rotateAngleY = 0.3490658F;
		fish4.rotateAngleZ = 0F;
		fish4.mirror = false;
		tukemono1 = new ModelRenderer(this, 14, 49);
		tukemono1.addBox(3F, -1F, 1F, 3, 1, 3, 0F);
		tukemono1.setRotationPoint(0F, 0F, 0F);
		tukemono1.rotateAngleX = 0F;
		tukemono1.rotateAngleY = 0F;
		tukemono1.rotateAngleZ = 0F;
		tukemono1.mirror = false;
		tukemono2 = new ModelRenderer(this, 14, 54);
		tukemono2.addBox(2.5F, -2F, -2F, 2, 1, 1, 0F);
		tukemono2.setRotationPoint(2F, 0F, 0F);
		tukemono2.rotateAngleX = 0F;
		tukemono2.rotateAngleY = -1.047198F;
		tukemono2.rotateAngleZ = 0F;
		tukemono2.mirror = false;
		tukemono3 = new ModelRenderer(this, 14, 57);
		tukemono3.addBox(2.5F, -2F, -1F, 2, 1, 1, 0F);
		tukemono3.setRotationPoint(2F, 0F, 0F);
		tukemono3.rotateAngleX = 0F;
		tukemono3.rotateAngleY = -1.134464F;
		tukemono3.rotateAngleZ = 0F;
		tukemono3.mirror = false;
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
		fish1.render(scale);
		fish2.render(scale);
		fish3.render(scale);
		fish4.render(scale);
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
