package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCroquette extends DCFoodModelBase {

	ModelRenderer paper1;
	ModelRenderer paper2;
	ModelRenderer croquette;

	public ModelCroquette(boolean baked) {
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
		croquette = new ModelRenderer(this, 0, 12);
		croquette.addBox(-3F, -2F, -9F, 6, 2, 8);
		croquette.setRotationPoint(0F, 0F, 5F);
		croquette.setTextureSize(64, 32);
		croquette.mirror = true;
		setRotation(croquette, -0.2617994F, 0F, 0F);
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
		croquette.render(scale);

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
