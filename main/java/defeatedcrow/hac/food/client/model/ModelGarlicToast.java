package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelGarlicToast extends DCFoodModelBase {

	ModelRenderer bread1;
	ModelRenderer bread2;

	public ModelGarlicToast(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 16;

		bread1 = new ModelRenderer(this, 0, 0);
		bread1.addBox(-3F, -1.5F, -3F, 6, 1, 4);
		bread1.setRotationPoint(0F, -1F, 0F);
		bread1.setTextureSize(32, 16);
		bread1.mirror = true;
		setRotation(bread1, 0.5235988F, 0.3490659F, 0F);
		bread2 = new ModelRenderer(this, 0, 0);
		bread2.addBox(-3F, 0F, -1F, 6, 1, 4);
		bread2.setRotationPoint(0F, -1F, 0F);
		bread2.setTextureSize(32, 16);
		bread2.mirror = true;
		setRotation(bread2, 0F, 0.5235988F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bread1.render(scale);
		bread2.render(scale);
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
