package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelParfait extends DCFoodModelBase {

	public ModelRenderer glass1;
	public ModelRenderer glass2;
	public ModelRenderer glass3;
	public ModelRenderer glass4;
	public ModelRenderer glass5;
	public ModelRenderer biscuit;
	public ModelRenderer cream1;
	public ModelRenderer cream2;
	public ModelRenderer cream3;
	public ModelRenderer cream4;
	public ModelRenderer cream5;
	public ModelRenderer berry1;
	public ModelRenderer berry2;
	public ModelRenderer fruit1;
	public ModelRenderer fruit2;
	public ModelRenderer fluit3;
	public ModelRenderer fluit4;

	public ModelParfait(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		glass1 = new ModelRenderer(this, 0, 0);
		glass1.addBox(-2.5F, 7F, -2.5F, 5, 1, 5, 0F);
		glass1.setRotationPoint(0F, 0F, 0F);
		glass1.rotateAngleX = 0F;
		glass1.rotateAngleY = 0F;
		glass1.rotateAngleZ = 0F;
		glass1.mirror = false;
		glass2 = new ModelRenderer(this, 0, 0);
		glass2.addBox(-0.5F, 5F, -0.5F, 1, 2, 1, 0F);
		glass2.setRotationPoint(0F, 0F, 0F);
		glass2.rotateAngleX = 0F;
		glass2.rotateAngleY = 0F;
		glass2.rotateAngleZ = 0F;
		glass2.mirror = false;
		glass3 = new ModelRenderer(this, 0, 6);
		glass3.addBox(-2F, 1F, -2F, 4, 4, 4, 0F);
		glass3.setRotationPoint(0F, 0F, 0F);
		glass3.rotateAngleX = 0F;
		glass3.rotateAngleY = 0F;
		glass3.rotateAngleZ = 0F;
		glass3.mirror = false;
		glass4 = new ModelRenderer(this, 0, 15);
		glass4.addBox(-2.5F, -3F, -2.5F, 5, 4, 5, 0F);
		glass4.setRotationPoint(0F, 0F, 0F);
		glass4.rotateAngleX = 0F;
		glass4.rotateAngleY = 0F;
		glass4.rotateAngleZ = 0F;
		glass4.mirror = false;
		glass5 = new ModelRenderer(this, 0, 25);
		glass5.addBox(-3F, -4F, -3F, 6, 1, 6, 0F);
		glass5.setRotationPoint(0F, 0F, 0F);
		glass5.rotateAngleX = 0F;
		glass5.rotateAngleY = 0F;
		glass5.rotateAngleZ = 0F;
		glass5.mirror = false;
		biscuit = new ModelRenderer(this, 32, 0);
		biscuit.addBox(-1.5F, 0.5F, -1.5F, 3, 4, 3, 0F);
		biscuit.setRotationPoint(0F, 0F, 0F);
		biscuit.rotateAngleX = 0F;
		biscuit.rotateAngleY = 0F;
		biscuit.rotateAngleZ = 0F;
		biscuit.mirror = false;
		cream1 = new ModelRenderer(this, 46, 0);
		cream1.addBox(-2F, -0.5F, -2F, 4, 1, 4, 0F);
		cream1.setRotationPoint(0F, 0F, 0F);
		cream1.rotateAngleX = 0F;
		cream1.rotateAngleY = 0F;
		cream1.rotateAngleZ = 0F;
		cream1.mirror = false;
		cream2 = new ModelRenderer(this, 46, 5);
		cream2.addBox(-2F, -1.5F, -2F, 4, 1, 4, 0F);
		cream2.setRotationPoint(0F, 0F, 0F);
		cream2.rotateAngleX = 0F;
		cream2.rotateAngleY = 0F;
		cream2.rotateAngleZ = 0F;
		cream2.mirror = false;
		cream3 = new ModelRenderer(this, 46, 10);
		cream3.addBox(-2F, -2.5F, -2F, 4, 1, 4, 0F);
		cream3.setRotationPoint(0F, 0F, 0F);
		cream3.rotateAngleX = 0F;
		cream3.rotateAngleY = 0F;
		cream3.rotateAngleZ = 0F;
		cream3.mirror = false;
		cream4 = new ModelRenderer(this, 46, 15);
		cream4.addBox(-2F, -4.5F, -2F, 4, 2, 4, 0F);
		cream4.setRotationPoint(0F, 0F, 0F);
		cream4.rotateAngleX = 0F;
		cream4.rotateAngleY = 0F;
		cream4.rotateAngleZ = 0F;
		cream4.mirror = false;
		cream5 = new ModelRenderer(this, 46, 22);
		cream5.addBox(-1.5F, -6.5F, -1.5F, 3, 2, 3, 0F);
		cream5.setRotationPoint(0F, 0F, 0F);
		cream5.rotateAngleX = 0F;
		cream5.rotateAngleY = 0F;
		cream5.rotateAngleZ = 0F;
		cream5.mirror = false;
		berry1 = new ModelRenderer(this, 32, 8);
		berry1.addBox(0F, -7.5F, 0F, 1, 1, 1, 0F);
		berry1.setRotationPoint(0F, 0F, 0F);
		berry1.rotateAngleX = 0F;
		berry1.rotateAngleY = -0.1745329F;
		berry1.rotateAngleZ = 0F;
		berry1.mirror = false;
		berry2 = new ModelRenderer(this, 32, 8);
		berry2.addBox(0F, -7.5F, 0F, 1, 1, 1, 0F);
		berry2.setRotationPoint(0F, 0F, 0F);
		berry2.rotateAngleX = 0F;
		berry2.rotateAngleY = 2.617994F;
		berry2.rotateAngleZ = 0F;
		berry2.mirror = false;
		fruit1 = new ModelRenderer(this, 32, 11);
		fruit1.addBox(-1F, 0F, 1F, 2, 1, 3, 0F);
		fruit1.setRotationPoint(0F, -4F, 0F);
		fruit1.rotateAngleX = 0.4712389F;
		fruit1.rotateAngleY = 0.5235988F;
		fruit1.rotateAngleZ = 0F;
		fruit1.mirror = false;
		fruit2 = new ModelRenderer(this, 32, 11);
		fruit2.addBox(-1F, 0F, 1F, 2, 1, 3, 0F);
		fruit2.setRotationPoint(0F, -4F, 0F);
		fruit2.rotateAngleX = 0.4712389F;
		fruit2.rotateAngleY = 1.396263F;
		fruit2.rotateAngleZ = 0F;
		fruit2.mirror = false;
		fluit3 = new ModelRenderer(this, 32, 16);
		fluit3.addBox(-1F, -0.5F, 1.2F, 2, 2, 2, 0F);
		fluit3.setRotationPoint(0F, -5F, 0F);
		fluit3.rotateAngleX = 0.1745329F;
		fluit3.rotateAngleY = -1.117011F;
		fluit3.rotateAngleZ = 0F;
		fluit3.mirror = false;
		fluit4 = new ModelRenderer(this, 32, 16);
		fluit4.addBox(-1F, -1F, 1F, 2, 2, 2, 0F);
		fluit4.setRotationPoint(0F, -4F, 0F);
		fluit4.rotateAngleX = 0.2792527F;
		fluit4.rotateAngleY = -2.094395F;
		fluit4.rotateAngleZ = 0F;
		fluit4.mirror = false;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		biscuit.render(0.0625F);
	}

	public void renderGlass() {
		glass1.render(0.0625F);
		glass2.render(0.0625F);
		glass3.render(0.0625F);
		glass4.render(0.0625F);
		glass5.render(0.0625F);

	}

	public void renderSauce() {
		cream2.render(0.0625F);
		cream4.render(0.0625F);
	}

	public void renderSauce2() {
		cream1.render(0.0625F);
		cream3.render(0.0625F);
		cream5.render(0.0625F);
	}

	public void renderTop() {
		berry1.render(0.0625F);
		berry2.render(0.0625F);
	}

	public void renderFruit() {
		fruit1.render(0.0625F);
		fruit2.render(0.0625F);
		fluit3.render(0.0625F);
		fluit4.render(0.0625F);
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

	public void setIndividualRotation(FoodEntityBase entity) {}

}
