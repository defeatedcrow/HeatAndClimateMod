package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSquarePie extends DCFoodModelBase {

	ModelRenderer raw;
	ModelRenderer bake;

	public ModelSquarePie(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 16;

		raw = new ModelRenderer(this, 0, 0);
		raw.addBox(-4F, 7F, -3F, 8, 1, 6);
		raw.setRotationPoint(0F, -8F, 0F);
		raw.setTextureSize(32, 16);
		raw.mirror = true;
		setRotation(raw, 0F, 0F, 0F);
		bake = new ModelRenderer(this, 0, 0);
		bake.addBox(-4F, 4F, -3F, 8, 4, 6);
		bake.setRotationPoint(0F, -8F, 0F);
		bake.setTextureSize(32, 16);
		bake.mirror = true;
		setRotation(bake, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale * 0.75F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		if (isBaked()) {
			bake.render(scale);
		} else {
			raw.render(scale);
		}
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
