package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSaladNut extends DCFoodModelBase {

	ModelRenderer glass1;
	ModelRenderer glass2;
	ModelRenderer glass3;
	ModelRenderer glass4;
	ModelRenderer glass5;
	ModelRenderer glass6;
	ModelRenderer glass7;
	ModelRenderer glass8;
	ModelRenderer glassbottom;
	ModelRenderer leaf1;
	ModelRenderer leaf2;
	ModelRenderer leaf3;
	ModelRenderer lea4;
	ModelRenderer leaf5;
	ModelRenderer veg1;
	ModelRenderer veg2;
	ModelRenderer veg3;
	ModelRenderer veg4;
	ModelRenderer veg5;
	ModelRenderer veg6;
	ModelRenderer veg7;
	ModelRenderer veg8;
	ModelRenderer veg9;
	ModelRenderer veg10;
	ModelRenderer veg11;
	ModelRenderer veg12;
	ModelRenderer veg13;
	ModelRenderer veg14;

	public ModelSaladNut(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		glass1 = new ModelRenderer(this, 0, 0);
		glass1.addBox(-3F, -5F, 6F, 6, 6, 1);
		glass1.setRotationPoint(0F, 0F, 0F);
		glass1.setTextureSize(64, 32);
		glass1.mirror = true;
		setRotation(glass1, -0.7853982F, 0F, 0F);
		glass2 = new ModelRenderer(this, 0, 0);
		glass2.addBox(-3F, -5F, 6F, 6, 6, 1);
		glass2.setRotationPoint(0F, 0F, 0F);
		glass2.setTextureSize(64, 32);
		glass2.mirror = true;
		setRotation(glass2, -0.7853982F, 1.570796F, 0F);
		glass3 = new ModelRenderer(this, 0, 0);
		glass3.addBox(-3F, -5F, 6F, 6, 6, 1);
		glass3.setRotationPoint(0F, 0F, 0F);
		glass3.setTextureSize(64, 32);
		glass3.mirror = true;
		setRotation(glass3, -0.7853982F, 3.141593F, 0F);
		glass4 = new ModelRenderer(this, 0, 0);
		glass4.addBox(-3F, -5F, 6F, 6, 6, 1);
		glass4.setRotationPoint(0F, 0F, 0F);
		glass4.setTextureSize(64, 32);
		glass4.mirror = true;
		setRotation(glass4, -0.7853982F, -1.570796F, 0F);
		glass5 = new ModelRenderer(this, 15, 0);
		glass5.addBox(-3.5F, -5F, 6F, 7, 6, 1);
		glass5.setRotationPoint(0F, 0F, 0F);
		glass5.setTextureSize(64, 32);
		glass5.mirror = true;
		setRotation(glass5, -0.7853982F, 0.7853982F, 0F);
		glass6 = new ModelRenderer(this, 15, 0);
		glass6.addBox(-3.5F, -5F, 6F, 7, 6, 1);
		glass6.setRotationPoint(0F, 0F, 0F);
		glass6.setTextureSize(64, 32);
		glass6.mirror = true;
		setRotation(glass6, -0.7853982F, 2.356194F, 0F);
		glass7 = new ModelRenderer(this, 15, 0);
		glass7.addBox(-3.5F, -5F, 6F, 7, 6, 1);
		glass7.setRotationPoint(0F, 0F, 0F);
		glass7.setTextureSize(64, 32);
		glass7.mirror = true;
		setRotation(glass7, -0.7853982F, -2.356194F, 0F);
		glass8 = new ModelRenderer(this, 15, 0);
		glass8.addBox(-3.5F, -5F, 6F, 7, 6, 1);
		glass8.setRotationPoint(0F, 0F, 0F);
		glass8.setTextureSize(64, 32);
		glass8.mirror = true;
		setRotation(glass8, -0.7853982F, -0.7853982F, 0F);
		glassbottom = new ModelRenderer(this, 32, 0);
		glassbottom.addBox(-4F, 5F, -4F, 8, 2, 8);
		glassbottom.setRotationPoint(0F, 0F, 0F);
		glassbottom.setTextureSize(64, 32);
		glassbottom.mirror = true;
		setRotation(glassbottom, 0F, 0F, 0F);
		leaf1 = new ModelRenderer(this, 0, 16);
		leaf1.addBox(-3F, -8F, -4F, 6, 8, 0);
		leaf1.setRotationPoint(0F, 0F, 0F);
		leaf1.setTextureSize(64, 32);
		leaf1.mirror = true;
		setRotation(leaf1, 1.047198F, 0F, 0F);
		leaf2 = new ModelRenderer(this, 13, 16);
		leaf2.addBox(-4F, -8F, -4F, 8, 8, 0);
		leaf2.setRotationPoint(0F, 0F, 0F);
		leaf2.setTextureSize(64, 32);
		leaf2.mirror = true;
		setRotation(leaf2, 1.047198F, 1.047198F, 0F);
		leaf3 = new ModelRenderer(this, 30, 16);
		leaf3.addBox(-4F, -8F, -4F, 8, 8, 0);
		leaf3.setRotationPoint(0F, 0F, 0F);
		leaf3.setTextureSize(64, 32);
		leaf3.mirror = true;
		setRotation(leaf3, 0.9599311F, 2.617994F, 0F);
		lea4 = new ModelRenderer(this, 47, 16);
		lea4.addBox(-4F, -7F, -4F, 8, 8, 0);
		lea4.setRotationPoint(0F, 0F, 0F);
		lea4.setTextureSize(64, 32);
		lea4.mirror = true;
		setRotation(lea4, 0.7853982F, -1.308997F, 0F);
		leaf5 = new ModelRenderer(this, 0, 16);
		leaf5.addBox(-3F, -8F, -4F, 6, 8, 0);
		leaf5.setRotationPoint(0F, 0F, 0F);
		leaf5.setTextureSize(64, 32);
		leaf5.mirror = true;
		setRotation(leaf5, 1.047198F, -2.094395F, 0F);
		veg1 = new ModelRenderer(this, 0, 26);
		veg1.addBox(0F, -5F, -4F, 4, 4, 1);
		veg1.setRotationPoint(0F, 0F, 0F);
		veg1.setTextureSize(64, 32);
		veg1.mirror = true;
		setRotation(veg1, 0.9599311F, 1.047198F, 0F);
		veg2 = new ModelRenderer(this, 0, 26);
		veg2.addBox(-2F, -5F, -4F, 4, 4, 1);
		veg2.setRotationPoint(0F, 0F, 0F);
		veg2.setTextureSize(64, 32);
		veg2.mirror = true;
		setRotation(veg2, 0.9599311F, 2.125377F, 0F);
		veg3 = new ModelRenderer(this, 0, 26);
		veg3.addBox(-3F, -4F, -4F, 4, 4, 1);
		veg3.setRotationPoint(0F, 0F, 0F);
		veg3.setTextureSize(64, 32);
		veg3.mirror = true;
		setRotation(veg3, 1.047198F, -2.879793F, 0F);
		veg4 = new ModelRenderer(this, 0, 26);
		veg4.addBox(-2F, -5F, -4F, 4, 4, 1);
		veg4.setRotationPoint(0F, 0F, 0F);
		veg4.setTextureSize(64, 32);
		veg4.mirror = true;
		setRotation(veg4, 0.9599311F, -0.8726646F, 0F);
		veg5 = new ModelRenderer(this, 11, 26);
		veg5.addBox(-1F, -4F, -3F, 2, 5, 1);
		veg5.setRotationPoint(0F, 0F, 0F);
		veg5.setTextureSize(64, 32);
		veg5.mirror = true;
		setRotation(veg5, 0.9599311F, 0F, 0F);
		veg6 = new ModelRenderer(this, 11, 26);
		veg6.addBox(-2F, -4F, -2F, 2, 5, 1);
		veg6.setRotationPoint(0F, 0F, 0F);
		veg6.setTextureSize(64, 32);
		veg6.mirror = true;
		setRotation(veg6, 1.308997F, 1.047198F, 0F);
		veg7 = new ModelRenderer(this, 11, 26);
		veg7.addBox(-1F, -4F, -3F, 2, 5, 1);
		veg7.setRotationPoint(0F, 0F, 0F);
		veg7.setTextureSize(64, 32);
		veg7.mirror = true;
		setRotation(veg7, 0.8726646F, 2.879793F, 0F);
		veg8 = new ModelRenderer(this, 11, 26);
		veg8.addBox(0F, -4F, -3F, 2, 5, 1);
		veg8.setRotationPoint(0F, 0F, 0F);
		veg8.setTextureSize(64, 32);
		veg8.mirror = true;
		setRotation(veg8, 0.9599311F, -1.396263F, 0F);
		veg9 = new ModelRenderer(this, 0, 33);
		veg9.addBox(-2F, 1F, -2F, 4, 4, 4);
		veg9.setRotationPoint(0F, 0F, 0F);
		veg9.setTextureSize(64, 32);
		veg9.mirror = true;
		setRotation(veg9, 0F, 0.3490659F, 0F);
		veg10 = new ModelRenderer(this, 18, 29);
		veg10.addBox(-1F, 0F, 1F, 1, 1, 1);
		veg10.setRotationPoint(0F, 0F, 0F);
		veg10.setTextureSize(64, 64);
		veg10.mirror = true;
		setRotation(veg10, -0.1396263F, 0F, 0F);
		veg10.mirror = false;
		veg11 = new ModelRenderer(this, 18, 26);
		veg11.addBox(1F, 0F, 0F, 1, 1, 1);
		veg11.setRotationPoint(0F, 0F, 0F);
		veg11.setTextureSize(64, 64);
		veg11.mirror = true;
		setRotation(veg11, -0.2617994F, 0F, 0F);
		veg12 = new ModelRenderer(this, 18, 29);
		veg12.addBox(-1F, 0F, 1F, 1, 1, 1);
		veg12.setRotationPoint(0F, 0F, 0F);
		veg12.setTextureSize(64, 64);
		veg12.mirror = true;
		setRotation(veg12, -0.1745329F, -1.570796F, 0F);
		veg13 = new ModelRenderer(this, 18, 29);
		veg13.addBox(-1F, 0F, -1F, 1, 1, 1);
		veg13.setRotationPoint(0F, 0F, 0F);
		veg13.setTextureSize(64, 64);
		veg13.mirror = true;
		setRotation(veg13, 0.3141593F, -0.5235988F, 0F);
		veg14 = new ModelRenderer(this, 18, 26);
		veg14.addBox(1F, 0F, 1F, 1, 1, 1);
		veg14.setRotationPoint(0F, 0F, 0F);
		veg14.setTextureSize(64, 64);
		veg14.mirror = true;
		setRotation(veg14, -0.1396263F, 2.044824F, 0F);
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
		glass1.render(scale);
		glass2.render(scale);
		glass3.render(scale);
		glass4.render(scale);
		glass5.render(scale);
		glass6.render(scale);
		glass7.render(scale);
		glass8.render(scale);
		glassbottom.render(scale);

		leaf1.render(scale);
		leaf2.render(scale);
		leaf3.render(scale);
		lea4.render(scale);
		leaf5.render(scale);

		veg1.render(scale);
		veg2.render(scale);
		veg3.render(scale);
		veg4.render(scale);
		veg5.render(scale);
		veg6.render(scale);
		veg7.render(scale);
		veg8.render(scale);
		veg9.render(scale);
		veg10.render(scale);
		veg11.render(scale);
		veg12.render(scale);
		veg13.render(scale);
		veg14.render(scale);
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
			float v1 = 1.047198F;
			veg1.rotateAngleY = v1 + 0.5F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v2 = 2.125377F;
			veg2.rotateAngleY = v2 - 0.2F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v3 = -2.879793F;
			veg3.rotateAngleY = v3 - 0.3F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v4 = -0.8726646F;
			veg4.rotateAngleY = v4 + 0.5F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v5 = 0F;
			veg5.rotateAngleY = v5 + 0.2F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v6 = 1.047198F;
			veg6.rotateAngleY = v6 - 0.5F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v7 = 2.879793F;
			veg7.rotateAngleY = v7 + 0.3F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v8 = -2.094395F;
			veg8.rotateAngleY = v8 + 0.2F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v9 = 0.3490659F;
			veg9.rotateAngleY = v9 + 0.5F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v10 = 0.0F;
			veg9.rotateAngleY = v10 - 0.5F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v11 = 0.0F;
			veg9.rotateAngleY = v11 + 0.1F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v12 = -1.570796F;
			veg9.rotateAngleY = v12 - 0.2F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v13 = -0.5235988F;
			veg9.rotateAngleY = v13 + 0.2F * (entity.getIndividual() / 64F) * (float) (Math.PI);
			float v14 = 2.044824F;
			veg9.rotateAngleY = v14 - 0.5F * (entity.getIndividual() / 64F) * (float) (Math.PI);
		}
	}

}
