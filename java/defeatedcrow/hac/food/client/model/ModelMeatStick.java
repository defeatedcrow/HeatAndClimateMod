package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMeatStick extends DCFoodModelBase {

	ModelRenderer stick;
	ModelRenderer meat1;
	ModelRenderer meat2;
	ModelRenderer meat3;

	public ModelMeatStick(boolean baked) {
		super(baked);

		textureWidth = 32;
		textureHeight = 32;

		stick = new ModelRenderer(this, 0, 0);
		stick.addBox(-0.5F, -8F, -0.5F, 1, 16, 1);
		stick.setRotationPoint(0F, -8F, 0F);
		stick.setTextureSize(32, 32);
		stick.mirror = true;
		setRotation(stick, 0F, 0F, 0F);
		meat1 = new ModelRenderer(this, 6, 0);
		meat1.addBox(-3F, 1F, -1F, 6, 4, 2);
		meat1.setRotationPoint(0F, -8F, 0F);
		meat1.setTextureSize(32, 32);
		meat1.mirror = true;
		setRotation(meat1, -0.0349066F, 0.0698132F, 0F);
		meat2 = new ModelRenderer(this, 6, 0);
		meat2.addBox(-3F, -3F, -1F, 6, 4, 2);
		meat2.setRotationPoint(0F, -8F, 0F);
		meat2.setTextureSize(32, 32);
		meat2.mirror = true;
		setRotation(meat2, 0.0174533F, -0.0349066F, 0F);
		meat3 = new ModelRenderer(this, 6, 0);
		meat3.addBox(-3F, -7F, -1F, 6, 4, 2);
		meat3.setRotationPoint(0F, -8F, 0F);
		meat3.setTextureSize(32, 32);
		meat3.mirror = true;
		setRotation(meat3, -0.0349066F, 0.0523599F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	private void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			float f1 = 0.0698132F;
			meat1.rotateAngleY = f1 + ((entity.getIndividual() - 16) / 64F) * (float) (Math.PI);
			float f2 = -0.0349066F;
			meat2.rotateAngleY = f2 + ((entity.getIndividual() - 12) / 64F) * (float) (Math.PI);
			float f3 = 0.0523599F;
			meat3.rotateAngleY = f3 + ((entity.getIndividual() - 14) / 64F) * (float) (Math.PI);
		}
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		stick.render(scale);
		meat1.render(scale);
		meat2.render(scale);
		meat3.render(scale);
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
