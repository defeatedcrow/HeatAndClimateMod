package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShawarma extends DCFoodModelBase {

	ModelRenderer paper1;
	ModelRenderer paper2;
	ModelRenderer pita1;
	ModelRenderer pita2;
	ModelRenderer pita3;
	ModelRenderer pita4;
	ModelRenderer meat1;
	ModelRenderer meat2;
	ModelRenderer meat3;
	ModelRenderer vegi1;
	ModelRenderer vegi2;

	public ModelShawarma(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		paper1 = new ModelRenderer(this, 0, 0);
		paper1.addBox(-4F, 0F, -10F, 8, 0, 10);
		paper1.setRotationPoint(0F, 0F, 5F);
		paper1.setTextureSize(64, 32);
		paper1.mirror = true;
		setRotation(paper1, -0.2F, 0F, 0F);
		paper2 = new ModelRenderer(this, 36, 0);
		paper2.addBox(-4F, -3F, -5.2F, 8, 3, 5);
		paper2.setRotationPoint(0F, 0F, 5F);
		paper2.setTextureSize(64, 32);
		paper2.mirror = true;
		setRotation(paper2, -0.2443461F, 0F, 0F);
		pita1 = new ModelRenderer(this, 0, 11);
		pita1.addBox(-3F, -1F, -7.8F, 6, 1, 7);
		pita1.setRotationPoint(0F, 0F, 5F);
		pita1.setTextureSize(64, 32);
		pita1.mirror = true;
		setRotation(pita1, -0.2617994F, 0F, 0F);
		pita2 = new ModelRenderer(this, 0, 11);
		pita2.addBox(-3F, -1F, -8F, 6, 1, 7);
		pita2.setRotationPoint(0F, 0F, 5F);
		pita2.setTextureSize(64, 32);
		pita2.mirror = true;
		setRotation(pita2, -0.5410521F, 0F, 0F);
		pita3 = new ModelRenderer(this, 26, 11);
		pita3.addBox(-3F, -2F, -8F, 1, 2, 5);
		pita3.setRotationPoint(0F, 0F, 5F);
		pita3.setTextureSize(64, 32);
		pita3.mirror = true;
		setRotation(pita3, -0.3316126F, 0.122173F, 0F);
		pita4 = new ModelRenderer(this, 26, 11);
		pita4.addBox(2F, -2F, -8F, 1, 2, 5);
		pita4.setRotationPoint(0F, 0F, 5F);
		pita4.setTextureSize(64, 32);
		pita4.mirror = true;
		setRotation(pita4, -0.3316126F, -0.1047198F, 0F);
		meat1 = new ModelRenderer(this, 0, 20);
		meat1.addBox(0F, -2F, -8.5F, 2, 1, 5);
		meat1.setRotationPoint(0F, 0F, 5F);
		meat1.setTextureSize(64, 32);
		meat1.mirror = true;
		setRotation(meat1, -0.2617994F, -0.0872665F, 0F);
		meat2 = new ModelRenderer(this, 15, 20);
		meat2.addBox(0.5F, -2F, -8F, 2, 1, 5);
		meat2.setRotationPoint(0F, 0F, 5F);
		meat2.setTextureSize(64, 32);
		meat2.mirror = true;
		setRotation(meat2, -0.296706F, 0.2443461F, 0F);
		meat3 = new ModelRenderer(this, 30, 20);
		meat3.addBox(-3F, -2F, -8F, 2, 1, 5);
		meat3.setRotationPoint(0F, 0F, 5F);
		meat3.setTextureSize(64, 32);
		meat3.mirror = true;
		setRotation(meat3, -0.2792527F, -0.0349066F, 0F);
		vegi1 = new ModelRenderer(this, 46, 10);
		vegi1.addBox(-1F, -2F, -8.5F, 3, 0, 5);
		vegi1.setRotationPoint(0F, 0F, 5F);
		vegi1.setTextureSize(64, 32);
		vegi1.mirror = true;
		setRotation(vegi1, -0.1396263F, -0.0872665F, 0F);
		vegi2 = new ModelRenderer(this, 46, 16);
		vegi2.addBox(-2F, -2.5F, -8.5F, 3, 0, 5);
		vegi2.setRotationPoint(0F, 0F, 5F);
		vegi2.setTextureSize(64, 32);
		vegi2.mirror = true;
		setRotation(vegi2, -0.0698132F, 0.1570796F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		paper1.render(scale);
		paper2.render(scale);
		pita1.render(scale);
		pita2.render(scale);
		pita3.render(scale);
		pita4.render(scale);
		meat1.render(scale);
		meat2.render(scale);
		meat3.render(scale);
		vegi1.render(scale);
		vegi2.render(scale);

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
