package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSausageBread extends DCFoodModelBase {

	public ModelRenderer bread1;
	public ModelRenderer sausage;
	public ModelRenderer bread2;

	public ModelSausageBread(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 32;

		bread1 = new ModelRenderer(this, 0, 0);
		bread1.addBox(-1F, 6F, -3F, 2, 2, 6, 0F);
		bread1.setRotationPoint(0F, -8F, 0F);
		bread1.rotateAngleX = 0F;
		bread1.rotateAngleY = 0F;
		bread1.rotateAngleZ = 0F;
		bread1.mirror = false;
		sausage = new ModelRenderer(this, 0, 18);
		sausage.addBox(-0.5F, 6.5F, -4F, 1, 1, 8, 0F);
		sausage.setRotationPoint(0F, -8F, 0F);
		sausage.rotateAngleX = 0F;
		sausage.rotateAngleY = 0F;
		sausage.rotateAngleZ = 0F;
		sausage.mirror = false;
		bread2 = new ModelRenderer(this, 0, 9);
		bread2.addBox(-1.5F, 6F, -3F, 3, 2, 6, 0F);
		bread2.setRotationPoint(0F, -8F, 0F);
		bread2.rotateAngleX = 0F;
		bread2.rotateAngleY = 0F;
		bread2.rotateAngleZ = 0F;
		bread2.mirror = false;
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
			bread2.render(scale);
		} else {
			bread1.render(scale);
		}
		sausage.render(scale);
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
