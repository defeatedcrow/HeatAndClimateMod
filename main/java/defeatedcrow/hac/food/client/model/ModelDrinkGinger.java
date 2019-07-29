package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDrinkGinger extends DCFoodModelBase {

	ModelRenderer g1;
	ModelRenderer g21;
	ModelRenderer g22;
	ModelRenderer g23;
	ModelRenderer g24;

	ModelRenderer soup;
	ModelRenderer leaf;

	public ModelDrinkGinger(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		g1 = new ModelRenderer(this, 0, 0);
		g1.addBox(-3F, -1F, -3F, 6, 1, 6);
		g1.setRotationPoint(0F, 0F, 0F);
		g1.setTextureSize(64, 32);
		g1.mirror = true;
		setRotation(g1, 0F, 0F, 0F);
		soup = new ModelRenderer(this, 36, 0);
		soup.addBox(-3F, -9F, -3F, 6, 8, 6);
		soup.setRotationPoint(0F, 0F, 0F);
		soup.setTextureSize(64, 32);
		soup.mirror = true;
		setRotation(soup, 0F, 0F, 0F);
		g21 = new ModelRenderer(this, 0, 8);
		g21.addBox(-3F, -11F, 3F, 6, 12, 1);
		g21.setRotationPoint(0F, 0F, 0F);
		g21.setTextureSize(64, 32);
		g21.mirror = true;
		setRotation(g21, 0F, 0F, 0F);
		g22 = new ModelRenderer(this, 0, 8);
		g22.addBox(-3F, -11F, 3F, 6, 12, 1);
		g22.setRotationPoint(0F, 0F, 0F);
		g22.setTextureSize(64, 32);
		g22.mirror = true;
		setRotation(g22, 0F, 3.141593F, 0F);
		g23 = new ModelRenderer(this, 16, 8);
		g23.addBox(-4F, -11F, 3F, 8, 12, 1);
		g23.setRotationPoint(0F, 0F, 0F);
		g23.setTextureSize(64, 32);
		g23.mirror = true;
		setRotation(g23, 0F, 1.570796F, 0F);
		g24 = new ModelRenderer(this, 16, 8);
		g24.addBox(-4F, -11F, 3F, 8, 12, 1);
		g24.setRotationPoint(0F, 0F, 0F);
		g24.setTextureSize(64, 32);
		g24.mirror = true;
		setRotation(g24, 0F, -1.570796F, 0F);
		leaf = new ModelRenderer(this, 36, 16);
		leaf.addBox(0F, 0F, 0F, 3, 0, 3);
		leaf.setRotationPoint(0F, -9.2F, 0F);
		leaf.setTextureSize(64, 32);
		leaf.mirror = true;
		setRotation(leaf, 0.5220497F, 0.1745329F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		leaf.render(scale);
	}

	public void renderSoup(float scale) {
		soup.render(scale);
	}

	public void renderGlass(float scale) {
		g1.render(scale);
		g21.render(scale);
		g22.render(scale);
		g23.render(scale);
		g24.render(scale);
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
