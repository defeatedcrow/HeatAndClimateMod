package defeatedcrow.hac.food.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;

@SideOnly(Side.CLIENT)
public class ModelStickBase extends DCFoodModelBase {

	ModelRenderer stick;

	public ModelStickBase(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		stick = new ModelRenderer(this, 0, 0);
		stick.addBox(-0.5F, -8F, -0.5F, 1, 16, 1);
		stick.setRotationPoint(0F, -8F, 0F);
		stick.setTextureSize(64, 32);
		stick.mirror = true;
		setRotation(stick, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		stick.render(scale);
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
