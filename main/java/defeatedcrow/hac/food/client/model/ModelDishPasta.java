package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishPasta extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer dish3;
	ModelRenderer dish4;
	ModelRenderer dish5;

	ModelRenderer soup;
	ModelRenderer pasta1;
	ModelRenderer pasta2;
	ModelRenderer pasta3;
	ModelRenderer pasta4;
	ModelRenderer meat1;
	ModelRenderer meat2;
	ModelRenderer meat4;
	ModelRenderer meat3;
	ModelRenderer leaf1;
	ModelRenderer leaf2;

	public ModelDishPasta(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-5F, -1F, -5F, 10, 1, 10);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 32);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 12);
		dish2.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 32);
		dish2.mirror = true;
		setRotation(dish2, 0.2617994F, 0F, 0F);
		dish3 = new ModelRenderer(this, 0, 12);
		dish3.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish3.setRotationPoint(0F, 0F, 0F);
		dish3.setTextureSize(64, 32);
		dish3.mirror = true;
		setRotation(dish3, 0.2617994F, 1.570796F, 0F);
		dish4 = new ModelRenderer(this, 0, 12);
		dish4.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish4.setRotationPoint(0F, 0F, 0F);
		dish4.setTextureSize(64, 32);
		dish4.mirror = true;
		setRotation(dish4, 0.2617994F, 3.141593F, 0F);
		dish5 = new ModelRenderer(this, 0, 12);
		dish5.addBox(-7F, 0F, 5F, 14, 1, 2);
		dish5.setRotationPoint(0F, 0F, 0F);
		dish5.setTextureSize(64, 32);
		dish5.mirror = true;
		setRotation(dish5, 0.2617994F, -1.570796F, 0F);

		soup = new ModelRenderer(this, 0, 0);
		soup.addBox(-5F, -1.2F, -5F, 10, 0, 10);
		soup.setRotationPoint(0F, 0F, 0F);
		soup.setTextureSize(64, 32);
		soup.mirror = true;
		setRotation(soup, 0F, 0F, 0F);
		pasta1 = new ModelRenderer(this, 0, 11);
		pasta1.addBox(-3.5F, -2F, -3.5F, 7, 1, 7);
		pasta1.setRotationPoint(0F, 0F, 0F);
		pasta1.setTextureSize(64, 32);
		pasta1.mirror = true;
		setRotation(pasta1, 0F, 0F, 0F);
		pasta2 = new ModelRenderer(this, 0, 21);
		pasta2.addBox(-2F, 0F, -1F, 4, 1, 5);
		pasta2.setRotationPoint(0F, -2.5F, 0F);
		pasta2.setTextureSize(64, 32);
		pasta2.mirror = true;
		setRotation(pasta2, -0.1047198F, 0.8726646F, 0F);
		pasta3 = new ModelRenderer(this, 19, 21);
		pasta3.addBox(-3F, 0F, 0F, 6, 1, 4);
		pasta3.setRotationPoint(0F, -3F, 0F);
		pasta3.setTextureSize(64, 32);
		pasta3.mirror = true;
		setRotation(pasta3, -0.2617994F, -1.396263F, 0F);
		pasta4 = new ModelRenderer(this, 40, 21);
		pasta4.addBox(-2F, 0F, 0F, 4, 1, 4);
		pasta4.setRotationPoint(0F, -3F, 0F);
		pasta4.setTextureSize(64, 32);
		pasta4.mirror = true;
		setRotation(pasta4, -0.2094395F, 2.356194F, 0F);
		meat1 = new ModelRenderer(this, 33, 12);
		meat1.addBox(-1F, -3.5F, 1F, 2, 1, 2);
		meat1.setRotationPoint(0F, 0F, 0F);
		meat1.setTextureSize(64, 32);
		meat1.mirror = true;
		setRotation(meat1, -0.2617994F, 0.5235988F, 0F);
		meat2 = new ModelRenderer(this, 33, 12);
		meat2.addBox(-1F, 0F, 1F, 2, 1, 2);
		meat2.setRotationPoint(0F, -3.3F, 0F);
		meat2.setTextureSize(64, 32);
		meat2.mirror = true;
		setRotation(meat2, -0.0872665F, -2.094395F, 0F);
		meat4 = new ModelRenderer(this, 33, 16);
		meat4.addBox(0F, 0F, 0.5F, 2, 1, 2);
		meat4.setRotationPoint(0F, -3.5F, 0F);
		meat4.setTextureSize(64, 32);
		meat4.mirror = true;
		setRotation(meat4, -0.2094395F, 1.570796F, 0F);
		meat3 = new ModelRenderer(this, 33, 16);
		meat3.addBox(0F, 0F, 0.5F, 2, 1, 2);
		meat3.setRotationPoint(0F, -3.3F, 0F);
		meat3.setTextureSize(64, 32);
		meat3.mirror = true;
		setRotation(meat3, -0.0872665F, -1.223796F, 0F);
		leaf1 = new ModelRenderer(this, 42, 12);
		leaf1.addBox(0F, 0F, 0F, 2, 0, 2);
		leaf1.setRotationPoint(0F, -3.5F, 0F);
		leaf1.setTextureSize(64, 32);
		leaf1.mirror = true;
		setRotation(leaf1, -0.3490659F, 0.5235988F, 0F);
		leaf2 = new ModelRenderer(this, 42, 12);
		leaf2.addBox(0F, 0F, 0F, 2, 0, 2);
		leaf2.setRotationPoint(0F, -3.5F, 0F);
		leaf2.setTextureSize(64, 32);
		leaf2.mirror = true;
		setRotation(leaf2, -0.2617994F, -2.094395F, 0F);
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
		soup.render(scale);
		pasta1.render(scale);
		pasta2.render(scale);
		pasta3.render(scale);
		pasta4.render(scale);
		meat1.render(scale);
		meat2.render(scale);
		meat4.render(scale);
		meat3.render(scale);
		leaf1.render(scale);
		leaf2.render(scale);
	}

	public void renderDish(float scale) {
		dish1.render(scale);
		dish2.render(scale);
		dish3.render(scale);
		dish4.render(scale);
		dish5.render(scale);
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

	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			pasta2.rotateAngleY = 0.9F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
			pasta3.rotateAngleY = -1.3F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
			pasta4.rotateAngleY = 2.3F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
			meat1.rotateAngleY = 0.5F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
			meat2.rotateAngleY = -2.0F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
			meat3.rotateAngleY = 1.5F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
			meat4.rotateAngleY = -1.2F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
			leaf1.rotateAngleY = 0.5F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
			leaf2.rotateAngleY = -2.0F + (entity.getIndividual() / 16F) * (float) (Math.PI) * 0.2F;
		}
	}

}
