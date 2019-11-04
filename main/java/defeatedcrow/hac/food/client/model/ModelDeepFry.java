package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDeepFry extends DCFoodModelBase {

	ModelRenderer plate1;
	ModelRenderer plate2;
	ModelRenderer meat1;
	ModelRenderer leaf1;
	ModelRenderer leaf2;
	ModelRenderer vegi1;
	ModelRenderer vegi2;

	ModelRenderer meat21;
	ModelRenderer meat22;
	ModelRenderer meat23;
	ModelRenderer meat24;
	ModelRenderer meat25;

	ModelRenderer meat31;
	ModelRenderer meat32;
	ModelRenderer meat33;
	ModelRenderer meat34;
	ModelRenderer meat35;

	ModelRenderer meat41;
	ModelRenderer meat42;
	ModelRenderer meat43;

	ModelRenderer meat51;
	ModelRenderer meat52;
	ModelRenderer meat53;

	public ModelRenderer meat61;
	public ModelRenderer meat62;
	public ModelRenderer meat63;
	public ModelRenderer meat64;
	public ModelRenderer meat65;
	public ModelRenderer meat66;

	public ModelDeepFry(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		plate1 = new ModelRenderer(this, 0, 0);
		plate1.addBox(-5F, -1F, -6F, 10, 1, 12);
		plate1.setRotationPoint(0F, 0F, 0F);
		plate1.setTextureSize(64, 32);
		plate1.mirror = true;
		setRotation(plate1, 0F, 0F, 0F);
		plate2 = new ModelRenderer(this, 40, 0);
		plate2.addBox(-3F, 0F, -3F, 6, 1, 6);
		plate2.setRotationPoint(0F, 0F, 0F);
		plate2.setTextureSize(64, 32);
		plate2.mirror = true;
		setRotation(plate2, 0F, 0F, 0F);
		meat1 = new ModelRenderer(this, 0, 16);
		meat1.addBox(-2F, -3F, -5F, 4, 2, 7);
		meat1.setRotationPoint(0F, 0F, 0F);
		meat1.setTextureSize(64, 32);
		meat1.mirror = true;
		setRotation(meat1, 0F, -0.3316126F, 0F);
		leaf1 = new ModelRenderer(this, 38, 16);
		leaf1.addBox(-2F, -1F, 2F, 6, 0, 4);
		leaf1.setRotationPoint(0F, 0F, 0F);
		leaf1.setTextureSize(64, 32);
		leaf1.mirror = true;
		setRotation(leaf1, 0.3141593F, -1.047198F, 0F);
		leaf2 = new ModelRenderer(this, 38, 16);
		leaf2.addBox(-3F, -1F, 1.5F, 6, 0, 4);
		leaf2.setRotationPoint(0F, 0F, 0F);
		leaf2.setTextureSize(64, 32);
		leaf2.mirror = true;
		setRotation(leaf2, 0.2268928F, -1.53589F, 0F);
		vegi1 = new ModelRenderer(this, 38, 22);
		vegi1.addBox(-3F, -2F, 3F, 2, 1, 2);
		vegi1.setRotationPoint(0F, 0F, 0F);
		vegi1.setTextureSize(64, 32);
		vegi1.mirror = true;
		setRotation(vegi1, 0F, 0.7853982F, 0F);
		vegi2 = new ModelRenderer(this, 38, 22);
		vegi2.addBox(-3F, -4F, 2F, 2, 1, 2);
		vegi2.setRotationPoint(0F, 0F, 0F);
		vegi2.setTextureSize(64, 32);
		vegi2.mirror = true;
		setRotation(vegi2, -0.4363323F, 1.047198F, 0F);

		meat21 = new ModelRenderer(this, 0, 16);
		meat21.addBox(0F, -4F, -4F, 3, 3, 3);
		meat21.setRotationPoint(0F, 0F, 0F);
		meat21.setTextureSize(64, 32);
		meat21.mirror = true;
		setRotation(meat21, 0F, 0.7853982F, 0F);
		meat22 = new ModelRenderer(this, 0, 22);
		meat22.addBox(1F, -3F, -4F, 3, 2, 3);
		meat22.setRotationPoint(0F, 0F, 0F);
		meat22.setTextureSize(64, 32);
		meat22.mirror = true;
		setRotation(meat22, 0F, 0.6108652F, 0F);
		meat23 = new ModelRenderer(this, 12, 22);
		meat23.addBox(-2F, -3F, 0F, 3, 2, 3);
		meat23.setRotationPoint(0F, 0F, 0F);
		meat23.setTextureSize(64, 32);
		meat23.mirror = true;
		setRotation(meat23, 0F, 0.2094395F, 0F);
		meat24 = new ModelRenderer(this, 24, 16);
		meat24.addBox(0.5F, -2.5F, -3F, 3, 2, 3);
		meat24.setRotationPoint(0F, 0F, 0F);
		meat24.setTextureSize(64, 32);
		meat24.mirror = true;
		setRotation(meat24, 0F, -0.3839724F, 0F);
		meat25 = new ModelRenderer(this, 12, 16);
		meat25.addBox(-2F, -4.5F, -1F, 3, 3, 3);
		meat25.setRotationPoint(0F, 0F, 0F);
		meat25.setTextureSize(64, 32);
		meat25.mirror = true;
		setRotation(meat25, 0F, -0.0523599F, 0F);

		meat31 = new ModelRenderer(this, 0, 16);
		meat31.addBox(0F, -2F, -4F, 3, 1, 6);
		meat31.setRotationPoint(0F, 0F, 0F);
		meat31.setTextureSize(64, 32);
		meat31.mirror = true;
		setRotation(meat31, 0F, -0.5759587F, 0F);
		meat32 = new ModelRenderer(this, 18, 16);
		meat32.addBox(-2F, -2F, -3.5F, 2, 1, 5);
		meat32.setRotationPoint(0F, 0F, 0F);
		meat32.setTextureSize(64, 32);
		meat32.mirror = true;
		setRotation(meat32, 0F, -0.5759587F, 0F);
		meat33 = new ModelRenderer(this, 0, 24);
		meat33.addBox(-1F, -2F, -4F, 4, 1, 2);
		meat33.setRotationPoint(0F, 0F, 0F);
		meat33.setTextureSize(64, 32);
		meat33.mirror = true;
		setRotation(meat33, -0.1745329F, 1.047198F, 0F);
		meat34 = new ModelRenderer(this, 0, 28);
		meat34.addBox(0F, -2F, -5F, 2, 1, 1);
		meat34.setRotationPoint(0F, 0F, 0F);
		meat34.setTextureSize(64, 32);
		meat34.mirror = true;
		setRotation(meat34, -0.2094395F, 1.047198F, 0F);
		meat35 = new ModelRenderer(this, 13, 24);
		meat35.addBox(-1F, -1F, -8F, 4, 0, 3);
		meat35.setRotationPoint(0F, 0F, 0F);
		meat35.setTextureSize(64, 32);
		meat35.mirror = true;
		setRotation(meat35, -0.3839724F, 1.047198F, 0F);

		meat41 = new ModelRenderer(this, 0, 16);
		meat41.addBox(-1F, -4F, -4.5F, 3, 3, 3);
		meat41.setRotationPoint(0F, 0F, 0F);
		meat41.setTextureSize(64, 32);
		meat41.mirror = true;
		setRotation(meat41, 0F, 0.4014257F, 0F);
		meat42 = new ModelRenderer(this, 24, 16);
		meat42.addBox(0.5F, -4F, -1F, 3, 3, 3);
		meat42.setRotationPoint(0F, 0F, 0F);
		meat42.setTextureSize(64, 32);
		meat42.mirror = true;
		setRotation(meat42, 0F, 0.4363323F, 0F);
		meat43 = new ModelRenderer(this, 12, 16);
		meat43.addBox(-2F, -4F, 0F, 3, 3, 3);
		meat43.setRotationPoint(0F, 0F, 0F);
		meat43.setTextureSize(64, 32);
		meat43.mirror = true;
		setRotation(meat43, 0F, -0.0523599F, 0F);

		meat51 = new ModelRenderer(this, 0, 22);
		meat51.addBox(1F, -2F, -4F, 3, 1, 3);
		meat51.setRotationPoint(0F, 0F, 0F);
		meat51.setTextureSize(64, 32);
		meat51.mirror = true;
		setRotation(meat51, 0F, 0.6108652F, 0F);
		meat52 = new ModelRenderer(this, 0, 22);
		meat52.addBox(0F, -2F, -3F, 3, 1, 3);
		meat52.setRotationPoint(0F, 0F, 0F);
		meat52.setTextureSize(64, 32);
		meat52.mirror = true;
		setRotation(meat52, -0.5235988F, 0.6108652F, 0F);
		meat53 = new ModelRenderer(this, 0, 22);
		meat53.addBox(-1F, -3F, -2F, 3, 1, 3);
		meat53.setRotationPoint(0F, 0F, 0F);
		meat53.setTextureSize(64, 32);
		meat53.mirror = true;
		setRotation(meat53, -0.7853982F, 0.6108652F, 0F);

		meat61 = new ModelRenderer(this, 0, 22);
		meat61.addBox(-1F, -3F, -4F, 2, 2, 7, 0F);
		meat61.setRotationPoint(0F, 0F, 0F);
		meat61.rotateAngleX = -0.1396263F;
		meat61.rotateAngleY = 1.308997F;
		meat61.rotateAngleZ = 0F;
		meat61.mirror = false;
		meat62 = new ModelRenderer(this, 0, 22);
		meat62.addBox(-0.5F, -2.5F, -5F, 1, 1, 1, 0F);
		meat62.setRotationPoint(0F, 0F, 0F);
		meat62.rotateAngleX = -0.2617994F;
		meat62.rotateAngleY = 1.308997F;
		meat62.rotateAngleZ = 0F;
		meat62.mirror = false;
		meat63 = new ModelRenderer(this, 19, 22);
		meat63.addBox(1.5F, -3F, -4F, 2, 2, 7, 0F);
		meat63.setRotationPoint(0F, 0F, 0F);
		meat63.rotateAngleX = -0.1487144F;
		meat63.rotateAngleY = 1.047198F;
		meat63.rotateAngleZ = 0F;
		meat63.mirror = false;
		meat64 = new ModelRenderer(this, 0, 22);
		meat64.addBox(2F, -2.5F, -5F, 1, 1, 1, 0F);
		meat64.setRotationPoint(0F, 0F, 0F);
		meat64.rotateAngleX = -0.2617994F;
		meat64.rotateAngleY = 1.047198F;
		meat64.rotateAngleZ = 0F;
		meat64.mirror = false;
		meat65 = new ModelRenderer(this, 0, 16);
		meat65.addBox(-1F, -1F, -7.5F, 2, 0, 2, 0F);
		meat65.setRotationPoint(0F, 0F, 0F);
		meat65.rotateAngleX = -0.5235988F;
		meat65.rotateAngleY = 1.308997F;
		meat65.rotateAngleZ = 0F;
		meat65.mirror = false;
		meat66 = new ModelRenderer(this, 0, 16);
		meat66.addBox(1.5F, -1F, -7.5F, 2, 0, 2, 0F);
		meat66.setRotationPoint(0F, 0F, 0F);
		meat66.rotateAngleX = -0.5235988F;
		meat66.rotateAngleY = 1.047198F;
		meat66.rotateAngleZ = 0F;
		meat66.mirror = false;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		plate1.render(scale);
		plate2.render(scale);

		leaf1.render(scale);
		leaf2.render(scale);
		vegi1.render(scale);
		vegi2.render(scale);

	}

	public void pork() {
		meat1.render(0.0625F);
	}

	public void chicken() {
		meat21.render(0.0625F);
		meat22.render(0.0625F);
		meat23.render(0.0625F);
		meat24.render(0.0625F);
		meat25.render(0.0625F);
	}

	public void fish() {
		meat31.render(0.0625F);
		meat32.render(0.0625F);
		meat33.render(0.0625F);
		meat34.render(0.0625F);
		meat35.render(0.0625F);
	}

	public void ball() {
		meat41.render(0.0625F);
		meat42.render(0.0625F);
		meat43.render(0.0625F);
	}

	public void fishcake() {
		meat51.render(0.0625F);
		meat52.render(0.0625F);
		meat53.render(0.0625F);
	}

	public void prawn() {
		meat61.render(0.0625F);
		meat62.render(0.0625F);
		meat63.render(0.0625F);
		meat64.render(0.0625F);
		meat65.render(0.0625F);
		meat66.render(0.0625F);
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
