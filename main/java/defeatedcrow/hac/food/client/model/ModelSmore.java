package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSmore extends DCFoodModelBase {

	public ModelRenderer bottom;
	public ModelRenderer cream;
	public ModelRenderer top;

	public ModelSmore(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-3.5F, 7F, -2.5F, 7, 1, 5, 0F);
		bottom.setRotationPoint(0F, -2F, 0F);
		bottom.rotateAngleX = 0F;
		bottom.rotateAngleY = 0F;
		bottom.rotateAngleZ = 0F;
		bottom.mirror = false;
		cream = new ModelRenderer(this, 0, 7);
		cream.addBox(-2.5F, 5.5F, -2F, 5, 2, 4, 0F);
		cream.setRotationPoint(0F, -2F, 0F);
		cream.rotateAngleX = 0F;
		cream.rotateAngleY = 0.03490658F;
		cream.rotateAngleZ = 0F;
		cream.mirror = false;
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-3.5F, 4.5F, -2.5F, 7, 1, 5, 0F);
		top.setRotationPoint(0F, -2F, 0F);
		top.rotateAngleX = 0F;
		top.rotateAngleY = 0F;
		top.rotateAngleZ = 0F;
		top.mirror = false;
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
		cream.render(scale);
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
