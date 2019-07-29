package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDrinkKuzu extends DCFoodModelBase {

	ModelRenderer g1;
	ModelRenderer g2;
	ModelRenderer g3;
	ModelRenderer g4;
	ModelRenderer g5;
	ModelRenderer soup;

	public ModelDrinkKuzu(boolean baked) {
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
		g2.addBox(-3F, -5F, 3F, 6, 8, 1);
		g2.setRotationPoint(0F, 0F, 0F);
		g2.setTextureSize(64, 32);
		g2.mirror = true;
		setRotation(g2, 0F, 0F, 0F);
		g3 = new ModelRenderer(this, 0, 8);
		g3.addBox(-3F, -5F, 3F, 6, 8, 1);
		g3.setRotationPoint(0F, 0F, 0F);
		g3.setTextureSize(64, 32);
		g3.mirror = true;
		setRotation(g3, 0F, 3.141593F, 0F);
		g4 = new ModelRenderer(this, 0, 8);
		g4.addBox(-3F, -5F, 3F, 6, 8, 1);
		g4.setRotationPoint(0F, 0F, 0F);
		g4.setTextureSize(64, 32);
		g4.mirror = true;
		setRotation(g4, 0F, 1.570796F, 0F);
		g5 = new ModelRenderer(this, 0, 8);
		g5.addBox(-3F, -5F, 3F, 6, 8, 1);
		g5.setRotationPoint(0F, 0F, 0F);
		g5.setTextureSize(64, 32);
		g5.mirror = true;
		setRotation(g5, 0F, -1.570796F, 0F);
		soup = new ModelRenderer(this, 32, 0);
		soup.addBox(-3F, -4F, -3F, 6, 6, 6);
		soup.setRotationPoint(0F, 0F, 0F);
		soup.setTextureSize(64, 32);
		soup.mirror = true;
		setRotation(soup, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		g1.render(scale);
		g2.render(scale);
		g3.render(scale);
		g4.render(scale);
		g5.render(scale);
	}

	public void renderSoup(float scale) {
		soup.render(scale);
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
