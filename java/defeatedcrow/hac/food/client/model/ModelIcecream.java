package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelIcecream extends DCFoodModelBase {

	ModelRenderer g1;
	ModelRenderer g2;
	ModelRenderer g31;
	ModelRenderer g32;
	ModelRenderer g33;
	ModelRenderer g34;
	ModelRenderer g35;
	ModelRenderer g36;
	ModelRenderer g37;
	ModelRenderer g38;
	ModelRenderer icecream;
	ModelRenderer sauce;
	ModelRenderer berry1;
	ModelRenderer berry2;
	ModelRenderer biscui1;
	ModelRenderer biscui2;

	public ModelIcecream(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		g1 = new ModelRenderer(this, 0, 0);
		g1.addBox(-3F, 2F, -3F, 6, 1, 6);
		g1.setRotationPoint(0F, 0F, 0F);
		g1.setTextureSize(64, 32);
		g1.mirror = true;
		setRotation(g1, 0F, 0F, 0F);
		g2 = new ModelRenderer(this, 0, 8);
		g2.addBox(-2F, 0F, -2F, 4, 2, 4);
		g2.setRotationPoint(0F, 0F, 0F);
		g2.setTextureSize(64, 32);
		g2.mirror = true;
		setRotation(g2, 0F, 0F, 0F);
		g31 = new ModelRenderer(this, 0, 15);
		g31.addBox(-2F, 1F, 1F, 4, 1, 4);
		g31.setRotationPoint(0F, 0F, 0F);
		g31.setTextureSize(64, 32);
		g31.mirror = true;
		setRotation(g31, 0.6981317F, 0F, 0F);
		g32 = new ModelRenderer(this, 0, 15);
		g32.addBox(-2F, 1F, 1F, 4, 1, 4);
		g32.setRotationPoint(0F, 0F, 0F);
		g32.setTextureSize(64, 32);
		g32.mirror = true;
		setRotation(g32, 0.6981317F, 1.570796F, 0F);
		g33 = new ModelRenderer(this, 0, 15);
		g33.addBox(-2F, 1F, 1F, 4, 1, 4);
		g33.setRotationPoint(0F, 0F, 0F);
		g33.setTextureSize(64, 32);
		g33.mirror = true;
		setRotation(g33, 0.6981317F, 3.141593F, 0F);
		g34 = new ModelRenderer(this, 0, 15);
		g34.addBox(-2F, 1F, 1F, 4, 1, 4);
		g34.setRotationPoint(0F, 0F, 0F);
		g34.setTextureSize(64, 32);
		g34.mirror = true;
		setRotation(g34, 0.6981317F, -1.570796F, 0F);
		g35 = new ModelRenderer(this, 0, 15);
		g35.addBox(-2F, 1F, 1F, 4, 1, 4);
		g35.setRotationPoint(0F, 0F, 0F);
		g35.setTextureSize(64, 32);
		g35.mirror = true;
		setRotation(g35, 0.6981317F, 0.7853982F, 0F);
		g36 = new ModelRenderer(this, 0, 15);
		g36.addBox(-2F, 1F, 1F, 4, 1, 4);
		g36.setRotationPoint(0F, 0F, 0F);
		g36.setTextureSize(64, 32);
		g36.mirror = true;
		setRotation(g36, 0.6981317F, 2.356194F, 0F);
		g37 = new ModelRenderer(this, 0, 15);
		g37.addBox(-2F, 1F, 1F, 4, 1, 4);
		g37.setRotationPoint(0F, 0F, 0F);
		g37.setTextureSize(64, 32);
		g37.mirror = true;
		setRotation(g37, 0.6981317F, -2.356194F, 0F);
		g38 = new ModelRenderer(this, 0, 15);
		g38.addBox(-2F, 1F, 1F, 4, 1, 4);
		g38.setRotationPoint(0F, 0F, 0F);
		g38.setTextureSize(64, 32);
		g38.mirror = true;
		setRotation(g38, 0.6981317F, -0.7853982F, 0F);
		icecream = new ModelRenderer(this, 32, 0);
		icecream.addBox(-2.5F, -5F, -2.5F, 5, 3, 5);
		icecream.setRotationPoint(0F, 1F, 0F);
		icecream.setTextureSize(64, 32);
		icecream.mirror = true;
		setRotation(icecream, 0F, 0.1396263F, 0F);
		sauce = new ModelRenderer(this, 32, 9);
		sauce.addBox(-2F, -4.3F, -1.3F, 3, 4, 4);
		sauce.setRotationPoint(0F, 0F, 0F);
		sauce.setTextureSize(64, 32);
		sauce.mirror = true;
		setRotation(sauce, 0F, 0.1396263F, 0F);
		berry1 = new ModelRenderer(this, 32, 18);
		berry1.addBox(-1F, -5F, -1F, 1, 1, 1);
		berry1.setRotationPoint(0F, 0F, 0F);
		berry1.setTextureSize(64, 32);
		berry1.mirror = true;
		setRotation(berry1, 0F, 0F, 0F);
		berry2 = new ModelRenderer(this, 37, 18);
		berry2.addBox(0F, -5F, 0F, 1, 1, 1);
		berry2.setRotationPoint(0F, 0F, 0F);
		berry2.setTextureSize(64, 32);
		berry2.mirror = true;
		setRotation(berry2, 0F, 0.1396263F, 0F);
		biscui1 = new ModelRenderer(this, 32, 21);
		biscui1.addBox(-1F, -8F, -1F, 3, 6, 1);
		biscui1.setRotationPoint(0F, 0F, 0F);
		biscui1.setTextureSize(64, 32);
		biscui1.mirror = true;
		setRotation(biscui1, 0.8203047F, 0F, 0F);
		biscui2 = new ModelRenderer(this, 41, 21);
		biscui2.addBox(-2F, -8F, -0.8F, 3, 6, 1);
		biscui2.setRotationPoint(0F, 0F, 0F);
		biscui2.setTextureSize(64, 32);
		biscui2.mirror = true;
		setRotation(biscui2, 0.8203047F, 0.6108652F, 0F);
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
		icecream.render(0.0625F);
		berry1.render(0.0625F);
		berry2.render(0.0625F);
		biscui1.render(0.0625F);
		biscui2.render(0.0625F);
	}

	public void renderGlass() {
		g1.render(0.0625F);
		g2.render(0.0625F);
		g31.render(0.0625F);
		g32.render(0.0625F);
		g33.render(0.0625F);
		g34.render(0.0625F);
		g35.render(0.0625F);
		g36.render(0.0625F);
		g37.render(0.0625F);
		g38.render(0.0625F);
	}

	public void renderSauce() {
		sauce.render(0.0625F);
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
			float f1 = 0.2617994F;
			berry1.rotateAngleY = f1 + (entity.getIndividual() / 16F) * (float) (Math.PI);
			float f2 = -0.2443461F;
			berry2.rotateAngleY = f2 + (entity.getIndividual() / 16F) * (float) (Math.PI);
		}
	}

}
