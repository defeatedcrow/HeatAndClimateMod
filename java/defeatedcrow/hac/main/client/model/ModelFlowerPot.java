package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFlowerPot extends DCFoodModelBase {

	ModelRenderer bottom;
	ModelRenderer middle;
	ModelRenderer neck;
	ModelRenderer top;

	public ModelFlowerPot(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-3F, 0F, -3F, 6, 1, 6);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 0, 9);
		middle.addBox(-4F, -4F, -4F, 8, 4, 8);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.setTextureSize(64, 32);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		neck = new ModelRenderer(this, 0, 22);
		neck.addBox(-3F, -6F, -3F, 6, 2, 6);
		neck.setRotationPoint(0F, 0F, 0F);
		neck.setTextureSize(64, 32);
		neck.mirror = true;
		setRotation(neck, 0F, 0F, 0F);
		top = new ModelRenderer(this, 26, 0);
		top.addBox(-3.5F, -7F, -3.5F, 7, 1, 7);
		top.setRotationPoint(0F, 0F, 0F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bottom.render(scale);
		middle.render(scale);
		neck.render(scale);
		top.render(scale);
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
