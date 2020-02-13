package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDrinkTomato extends DCFoodModelBase {

	ModelRenderer g1;
	ModelRenderer g2;
	ModelRenderer g3;
	ModelRenderer g31;
	ModelRenderer g32;
	ModelRenderer g33;
	ModelRenderer g34;
	ModelRenderer soup;
	ModelRenderer berry1;
	ModelRenderer berry2;

	public ModelDrinkTomato(boolean baked) {
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
		g2.addBox(-1F, 0F, -1F, 2, 2, 2);
		g2.setRotationPoint(0F, 0F, 0F);
		g2.setTextureSize(64, 32);
		g2.mirror = true;
		setRotation(g2, 0F, 0F, 0F);
		g3 = new ModelRenderer(this, 0, 0);
		g3.addBox(-3F, -1F, -3F, 6, 1, 6);
		g3.setRotationPoint(0F, 0F, 0F);
		g3.setTextureSize(64, 32);
		g3.mirror = true;
		setRotation(g3, 0F, 0F, 0F);
		g31 = new ModelRenderer(this, 0, 15);
		g31.addBox(-3F, 2F, 1F, 6, 1, 4);
		g31.setRotationPoint(0F, 0F, 0F);
		g31.setTextureSize(64, 32);
		g31.mirror = true;
		setRotation(g31, 1.361357F, 0F, 0F);
		g32 = new ModelRenderer(this, 0, 15);
		g32.addBox(-3F, 2F, 1F, 6, 1, 4);
		g32.setRotationPoint(0F, 0F, 0F);
		g32.setTextureSize(64, 32);
		g32.mirror = true;
		setRotation(g32, 1.361357F, 1.570796F, 0F);
		g33 = new ModelRenderer(this, 0, 15);
		g33.addBox(-3F, 2F, 1F, 6, 1, 4);
		g33.setRotationPoint(0F, 0F, 0F);
		g33.setTextureSize(64, 32);
		g33.mirror = true;
		setRotation(g33, 1.361357F, 3.141593F, 0F);
		g34 = new ModelRenderer(this, 0, 15);
		g34.addBox(-3F, 2F, 1F, 6, 1, 4);
		g34.setRotationPoint(0F, 0F, 0F);
		g34.setTextureSize(64, 32);
		g34.mirror = true;
		setRotation(g34, 1.361357F, -1.570796F, 0F);
		soup = new ModelRenderer(this, 32, 0);
		soup.addBox(-3F, -5F, -3F, 6, 2, 6);
		soup.setRotationPoint(0F, 1.5F, 0F);
		soup.setTextureSize(64, 32);
		soup.mirror = true;
		setRotation(soup, 0F, 0F, 0F);
		berry1 = new ModelRenderer(this, 32, 18);
		berry1.addBox(-1F, -5F, -1F, 1, 1, 1);
		berry1.setRotationPoint(0F, 1F, 0F);
		berry1.setTextureSize(64, 32);
		berry1.mirror = true;
		setRotation(berry1, 0F, 0F, 0F);
		berry2 = new ModelRenderer(this, 37, 18);
		berry2.addBox(0F, -5F, 0F, 1, 1, 1);
		berry2.setRotationPoint(0F, 1F, 0F);
		berry2.setTextureSize(64, 32);
		berry2.mirror = true;
		setRotation(berry2, 0F, 0.1396263F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		soup.render(scale);
		berry1.render(scale);
		berry2.render(scale);
	}

	public void renderGlass(float scale) {
		g1.render(scale);
		g2.render(scale);
		g3.render(scale);
		g31.render(scale);
		g32.render(scale);
		g33.render(scale);
		g34.render(scale);
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
