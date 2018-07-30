package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelToast extends DCFoodModelBase {

	ModelRenderer bread;
	ModelRenderer butter1;
	ModelRenderer butter2;
	ModelRenderer butter3;
	ModelRenderer butter4;

	public ModelToast(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		bread = new ModelRenderer(this, 0, 0);
		bread.addBox(-4F, 0F, -5F, 8, 1, 10);
		bread.setRotationPoint(0F, -1F, 0F);
		bread.setTextureSize(64, 32);
		bread.mirror = true;
		setRotation(bread, 0F, 0F, 0F);
		butter1 = new ModelRenderer(this, 0, 12);
		butter1.addBox(-1F, -0.2F, 0F, 4, 1, 3);
		butter1.setRotationPoint(0F, -1F, 0F);
		butter1.setTextureSize(64, 32);
		butter1.mirror = true;
		setRotation(butter1, 0F, 0F, 0F);
		butter2 = new ModelRenderer(this, 0, 17);
		butter2.addBox(-1F, -0.2F, -3F, 3, 1, 3);
		butter2.setRotationPoint(0F, -1F, 0F);
		butter2.setTextureSize(64, 32);
		butter2.mirror = true;
		setRotation(butter2, 0F, 0F, 0F);
		butter3 = new ModelRenderer(this, 15, 12);
		butter3.addBox(-3F, -0.2F, -3F, 2, 1, 5);
		butter3.setRotationPoint(0F, -1F, 0F);
		butter3.setTextureSize(64, 32);
		butter3.mirror = true;
		setRotation(butter3, 0F, 0F, 0F);
		butter4 = new ModelRenderer(this, 30, 12);
		butter4.addBox(-1F, -1F, -1F, 2, 1, 2);
		butter4.setRotationPoint(0F, -1F, 0F);
		butter4.setTextureSize(64, 32);
		butter4.mirror = true;
		setRotation(butter4, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bread.render(0.0625F);
		if (!isBaked()) {
			butter4.render(0.0625F);
		}
	}

	public void renderButter(float scale) {
		if (isBaked()) {
			butter1.render(0.0625F);
			butter2.render(0.0625F);
			butter3.render(0.0625F);
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
