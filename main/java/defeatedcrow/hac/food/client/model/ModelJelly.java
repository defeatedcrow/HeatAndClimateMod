package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelJelly extends DCFoodModelBase {

	ModelRenderer glass1;
	ModelRenderer glass2;
	ModelRenderer glass3;
	ModelRenderer glass4;
	ModelRenderer glass5;
	ModelRenderer glass6;
	ModelRenderer glass7;
	ModelRenderer glass8;
	ModelRenderer glassbottom;

	ModelRenderer jelly1;
	ModelRenderer jelly2;
	ModelRenderer jelly3;

	public ModelJelly(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		glass1 = new ModelRenderer(this, 0, 0);
		glass1.addBox(-3F, -6F, 6F, 6, 5, 1);
		glass1.setRotationPoint(0F, 0F, 0F);
		glass1.setTextureSize(64, 32);
		glass1.mirror = true;
		setRotation(glass1, -1.308997F, 0F, 0F);
		glass2 = new ModelRenderer(this, 0, 0);
		glass2.addBox(-3F, -6F, 6F, 6, 5, 1);
		glass2.setRotationPoint(0F, 0F, 0F);
		glass2.setTextureSize(64, 32);
		glass2.mirror = true;
		setRotation(glass2, -1.308997F, 1.570796F, 0F);
		glass3 = new ModelRenderer(this, 0, 0);
		glass3.addBox(-3F, -6F, 6F, 6, 5, 1);
		glass3.setRotationPoint(0F, 0F, 0F);
		glass3.setTextureSize(64, 32);
		glass3.mirror = true;
		setRotation(glass3, -1.308997F, 3.141593F, 0F);
		glass4 = new ModelRenderer(this, 0, 0);
		glass4.addBox(-3F, -6F, 6F, 6, 5, 1);
		glass4.setRotationPoint(0F, 0F, 0F);
		glass4.setTextureSize(64, 32);
		glass4.mirror = true;
		setRotation(glass4, -1.308997F, -1.570796F, 0F);
		glass5 = new ModelRenderer(this, 15, 0);
		glass5.addBox(-3.5F, -6F, 6F, 7, 5, 1);
		glass5.setRotationPoint(0F, 0F, 0F);
		glass5.setTextureSize(64, 32);
		glass5.mirror = true;
		setRotation(glass5, -1.308997F, 0.7853982F, 0F);
		glass6 = new ModelRenderer(this, 15, 0);
		glass6.addBox(-3.5F, -6F, 6F, 7, 5, 1);
		glass6.setRotationPoint(0F, 0F, 0F);
		glass6.setTextureSize(64, 32);
		glass6.mirror = true;
		setRotation(glass6, -1.308997F, 2.356194F, 0F);
		glass7 = new ModelRenderer(this, 15, 0);
		glass7.addBox(-3.5F, -6F, 6F, 7, 5, 1);
		glass7.setRotationPoint(0F, 0F, 0F);
		glass7.setTextureSize(64, 32);
		glass7.mirror = true;
		setRotation(glass7, -1.308997F, -2.356194F, 0F);
		glass8 = new ModelRenderer(this, 15, 0);
		glass8.addBox(-3.5F, -6F, 6F, 7, 5, 1);
		glass8.setRotationPoint(0F, 0F, 0F);
		glass8.setTextureSize(64, 32);
		glass8.mirror = true;
		setRotation(glass8, -1.308997F, -0.7853982F, 0F);
		glassbottom = new ModelRenderer(this, 32, 0);
		glassbottom.addBox(-4F, 5F, -4F, 8, 2, 8);
		glassbottom.setRotationPoint(0F, 0F, 0F);
		glassbottom.setTextureSize(64, 32);
		glassbottom.mirror = true;
		setRotation(glassbottom, 0F, 0F, 0F);

		jelly1 = new ModelRenderer(this, 0, 16);
		jelly1.addBox(-3.5F, 1F, -3.5F, 7, 2, 7);
		jelly1.setRotationPoint(0F, 0F, 0F);
		jelly1.setTextureSize(64, 64);
		jelly1.mirror = true;
		setRotation(jelly1, 0F, 0F, 0F);
		jelly2 = new ModelRenderer(this, 0, 26);
		jelly2.addBox(-3.5F, 3F, -3.5F, 7, 2, 7);
		jelly2.setRotationPoint(0F, 0F, 0F);
		jelly2.setTextureSize(64, 64);
		jelly2.mirror = true;
		setRotation(jelly2, 0F, 0F, 0F);
		jelly3 = new ModelRenderer(this, 32, 16);
		jelly3.addBox(-3.5F, 0F, -3.5F, 7, 1, 7);
		jelly3.setRotationPoint(0F, 0F, 0F);
		jelly3.setTextureSize(64, 64);
		jelly3.mirror = true;
		setRotation(jelly3, 0F, 0F, 0F);
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
	}

	public void renderJelly() {
		jelly1.render(0.0625F);
	}

	public void renderJelly2() {
		jelly2.render(0.0625F);
	}

	public void renderJelly3() {
		jelly3.render(0.0625F);
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

	}

}
