package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSimmered extends DCFoodModelBase {

	ModelRenderer glass1;
	ModelRenderer glass2;
	ModelRenderer glass3;
	ModelRenderer glass4;
	ModelRenderer glass5;
	ModelRenderer glass6;
	ModelRenderer glass7;
	ModelRenderer glass8;
	ModelRenderer glassbottom;

	ModelRenderer vegi11;
	ModelRenderer vegi12;
	ModelRenderer vegi21;
	ModelRenderer vegi22;
	ModelRenderer vegi31;
	ModelRenderer vegi32;
	ModelRenderer vegi33;
	ModelRenderer vegi34;
	ModelRenderer vegi41;
	ModelRenderer vegi42;
	ModelRenderer vegi43;

	public ModelSimmered(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		glass1 = new ModelRenderer(this, 0, 0);
		glass1.addBox(-3F, -5F, 6F, 6, 6, 1);
		glass1.setRotationPoint(0F, 8F, 0F);
		glass1.setTextureSize(64, 32);
		glass1.mirror = true;
		setRotation(glass1, -0.7853982F, 0F, 0F);
		glass2 = new ModelRenderer(this, 0, 0);
		glass2.addBox(-3F, -5F, 6F, 6, 6, 1);
		glass2.setRotationPoint(0F, 8F, 0F);
		glass2.setTextureSize(64, 32);
		glass2.mirror = true;
		setRotation(glass2, -0.7853982F, 1.570796F, 0F);
		glass3 = new ModelRenderer(this, 0, 0);
		glass3.addBox(-3F, -5F, 6F, 6, 6, 1);
		glass3.setRotationPoint(0F, 8F, 0F);
		glass3.setTextureSize(64, 32);
		glass3.mirror = true;
		setRotation(glass3, -0.7853982F, 3.141593F, 0F);
		glass4 = new ModelRenderer(this, 0, 0);
		glass4.addBox(-3F, -5F, 6F, 6, 6, 1);
		glass4.setRotationPoint(0F, 8F, 0F);
		glass4.setTextureSize(64, 32);
		glass4.mirror = true;
		setRotation(glass4, -0.7853982F, -1.570796F, 0F);
		glass5 = new ModelRenderer(this, 15, 0);
		glass5.addBox(-3.5F, -5F, 6F, 7, 6, 1);
		glass5.setRotationPoint(0F, 8F, 0F);
		glass5.setTextureSize(64, 32);
		glass5.mirror = true;
		setRotation(glass5, -0.7853982F, 0.7853982F, 0F);
		glass6 = new ModelRenderer(this, 15, 0);
		glass6.addBox(-3.5F, -5F, 6F, 7, 6, 1);
		glass6.setRotationPoint(0F, 8F, 0F);
		glass6.setTextureSize(64, 32);
		glass6.mirror = true;
		setRotation(glass6, -0.7853982F, 2.356194F, 0F);
		glass7 = new ModelRenderer(this, 15, 0);
		glass7.addBox(-3.5F, -5F, 6F, 7, 6, 1);
		glass7.setRotationPoint(0F, 8F, 0F);
		glass7.setTextureSize(64, 32);
		glass7.mirror = true;
		setRotation(glass7, -0.7853982F, -2.356194F, 0F);
		glass8 = new ModelRenderer(this, 15, 0);
		glass8.addBox(-3.5F, -5F, 6F, 7, 6, 1);
		glass8.setRotationPoint(0F, 8F, 0F);
		glass8.setTextureSize(64, 32);
		glass8.mirror = true;
		setRotation(glass8, -0.7853982F, -0.7853982F, 0F);
		glassbottom = new ModelRenderer(this, 32, 0);
		glassbottom.addBox(-4F, 5F, -4F, 8, 2, 8);
		glassbottom.setRotationPoint(0F, 8F, 0F);
		glassbottom.setTextureSize(64, 32);
		glassbottom.mirror = true;
		setRotation(glassbottom, 0F, 0F, 0F);

		vegi11 = new ModelRenderer(this, 0, 16);
		vegi11.addBox(-2F, 0.5F, 1F, 4, 1, 5);
		vegi11.setRotationPoint(0F, 8F, 0F);
		vegi11.setTextureSize(64, 32);
		vegi11.mirror = true;
		setRotation(vegi11, -0.3490659F, 0.6806784F, 0F);
		vegi12 = new ModelRenderer(this, 0, 16);
		vegi12.addBox(-2F, 0.5F, 1F, 4, 1, 5);
		vegi12.setRotationPoint(0F, 8F, 0F);
		vegi12.setTextureSize(64, 32);
		vegi12.mirror = true;
		setRotation(vegi12, -0.4363323F, -2.094395F, 0F);
		vegi21 = new ModelRenderer(this, 19, 16);
		vegi21.addBox(-2F, 0F, 1F, 4, 1, 5);
		vegi21.setRotationPoint(0F, 8F, 0F);
		vegi21.setTextureSize(64, 32);
		vegi21.mirror = true;
		setRotation(vegi21, -0.3665191F, -0.1745329F, 0F);
		vegi22 = new ModelRenderer(this, 19, 16);
		vegi22.addBox(-2F, 0F, 1F, 4, 1, 5);
		vegi22.setRotationPoint(0F, 8F, 0F);
		vegi22.setTextureSize(64, 32);
		vegi22.mirror = true;
		setRotation(vegi22, -0.3665191F, 2.617994F, 0F);
		vegi31 = new ModelRenderer(this, 38, 16);
		vegi31.addBox(-2F, 0.5F, 2F, 4, 1, 4);
		vegi31.setRotationPoint(0F, 8F, 0F);
		vegi31.setTextureSize(64, 32);
		vegi31.mirror = true;
		setRotation(vegi31, -0.4363323F, -1.047198F, 0F);
		vegi32 = new ModelRenderer(this, 38, 16);
		vegi32.addBox(-2F, 0.5F, 2F, 4, 1, 4);
		vegi32.setRotationPoint(0F, 8F, 0F);
		vegi32.setTextureSize(64, 32);
		vegi32.mirror = true;
		setRotation(vegi32, -0.4363323F, 1.623156F, 0F);
		vegi33 = new ModelRenderer(this, 38, 16);
		vegi33.addBox(-2F, 1F, 2F, 4, 1, 4);
		vegi33.setRotationPoint(0F, 8F, 0F);
		vegi33.setTextureSize(64, 32);
		vegi33.mirror = true;
		setRotation(vegi33, -0.4363323F, -2.792527F, 0F);
		vegi34 = new ModelRenderer(this, 38, 16);
		vegi34.addBox(-2F, 2F, -2F, 4, 1, 4);
		vegi34.setRotationPoint(0F, 8F, 0F);
		vegi34.setTextureSize(64, 32);
		vegi34.mirror = true;
		setRotation(vegi34, 0F, 0.4363323F, 0F);
		vegi41 = new ModelRenderer(this, 0, 24);
		vegi41.addBox(-1F, -0.5F, 2F, 2, 1, 4);
		vegi41.setRotationPoint(0F, 8F, 0F);
		vegi41.setTextureSize(64, 32);
		vegi41.mirror = true;
		setRotation(vegi41, -0.2617994F, 1.745329F, 0F);
		vegi42 = new ModelRenderer(this, 0, 24);
		vegi42.addBox(-1F, -0.5F, 1F, 2, 1, 4);
		vegi42.setRotationPoint(0F, 8F, 0F);
		vegi42.setTextureSize(64, 32);
		vegi42.mirror = true;
		setRotation(vegi42, -0.2617994F, -2.443461F, 0F);
		vegi43 = new ModelRenderer(this, 0, 24);
		vegi43.addBox(-1F, -0.5F, 1F, 2, 1, 4);
		vegi43.setRotationPoint(0F, 8F, 0F);
		vegi43.setTextureSize(64, 32);
		vegi43.mirror = true;
		setRotation(vegi43, -0.2617994F, 0F, 0F);
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
		glass1.render(0.0625F);
		glass2.render(0.0625F);
		glass3.render(0.0625F);
		glass4.render(0.0625F);
		glass5.render(0.0625F);
		glass6.render(0.0625F);
		glass7.render(0.0625F);
		glass8.render(0.0625F);
		glassbottom.render(0.0625F);

		vegi11.render(0.0625F);
		vegi12.render(0.0625F);
		vegi21.render(0.0625F);
		vegi22.render(0.0625F);
		vegi31.render(0.0625F);
		vegi32.render(0.0625F);
		vegi33.render(0.0625F);
		vegi34.render(0.0625F);
		vegi41.render(0.0625F);
		vegi42.render(0.0625F);
		vegi43.render(0.0625F);
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
