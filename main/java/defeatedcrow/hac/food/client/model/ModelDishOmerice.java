package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishOmerice extends DCFoodModelBase {

	public ModelRenderer dish1;
	public ModelRenderer dish2;
	public ModelRenderer dish3;
	public ModelRenderer dish4;
	public ModelRenderer dish5;
	public ModelRenderer egg1;
	public ModelRenderer egg2;
	public ModelRenderer egg3;
	public ModelRenderer egg4;
	public ModelRenderer egg5;
	public ModelRenderer sauce;

	public ModelDishOmerice(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-4F, -1F, -4F, 8, 1, 8, 0F);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.rotateAngleX = 0F;
		dish1.rotateAngleY = 0F;
		dish1.rotateAngleZ = 0F;
		dish1.mirror = false;
		dish2 = new ModelRenderer(this, 0, 12);
		dish2.addBox(-6F, 0F, 3F, 12, 1, 4, 0F);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.rotateAngleX = 0.418879F;
		dish2.rotateAngleY = 0F;
		dish2.rotateAngleZ = 0F;
		dish2.mirror = false;
		dish3 = new ModelRenderer(this, 0, 12);
		dish3.addBox(-6F, 0F, 3F, 12, 1, 4, 0F);
		dish3.setRotationPoint(0F, 0F, 0F);
		dish3.rotateAngleX = 0.418879F;
		dish3.rotateAngleY = 1.570796F;
		dish3.rotateAngleZ = 0F;
		dish3.mirror = false;
		dish4 = new ModelRenderer(this, 0, 12);
		dish4.addBox(-6F, 0F, 3F, 12, 1, 4, 0F);
		dish4.setRotationPoint(0F, 0F, 0F);
		dish4.rotateAngleX = 0.418879F;
		dish4.rotateAngleY = 3.141593F;
		dish4.rotateAngleZ = 0F;
		dish4.mirror = false;
		dish5 = new ModelRenderer(this, 0, 12);
		dish5.addBox(-6F, 0F, 3F, 12, 1, 4, 0F);
		dish5.setRotationPoint(0F, 0F, 0F);
		dish5.rotateAngleX = 0.418879F;
		dish5.rotateAngleY = -1.570796F;
		dish5.rotateAngleZ = 0F;
		dish5.mirror = false;
		egg1 = new ModelRenderer(this, 0, 18);
		egg1.addBox(-2.5F, -5F, -3F, 5, 4, 6, 0F);
		egg1.setRotationPoint(0F, 0F, 0F);
		egg1.rotateAngleX = 0F;
		egg1.rotateAngleY = -0.5585054F;
		egg1.rotateAngleZ = 0F;
		egg1.mirror = false;
		egg2 = new ModelRenderer(this, 23, 18);
		egg2.addBox(2F, -4.5F, -2.5F, 2, 3, 5, 0F);
		egg2.setRotationPoint(0F, 0F, 0F);
		egg2.rotateAngleX = 0F;
		egg2.rotateAngleY = -0.5061455F;
		egg2.rotateAngleZ = 0F;
		egg2.mirror = false;
		egg3 = new ModelRenderer(this, 23, 18);
		egg3.addBox(-4F, -4.5F, -2.5F, 2, 3, 5, 0F);
		egg3.setRotationPoint(0F, 0F, 0F);
		egg3.rotateAngleX = 0F;
		egg3.rotateAngleY = -0.6283185F;
		egg3.rotateAngleZ = 0F;
		egg3.mirror = false;
		egg4 = new ModelRenderer(this, 38, 18);
		egg4.addBox(4F, -4F, -2F, 1, 2, 4, 0F);
		egg4.setRotationPoint(0F, 0F, 0F);
		egg4.rotateAngleX = 0F;
		egg4.rotateAngleY = -0.4537856F;
		egg4.rotateAngleZ = 0F;
		egg4.mirror = false;
		egg5 = new ModelRenderer(this, 38, 18);
		egg5.addBox(-5F, -4F, -2F, 1, 2, 4, 0F);
		egg5.setRotationPoint(0F, 0F, 0F);
		egg5.rotateAngleX = 0F;
		egg5.rotateAngleY = -0.6632251F;
		egg5.rotateAngleZ = 0F;
		egg5.mirror = false;
		sauce = new ModelRenderer(this, 34, 0);
		sauce.addBox(-1F, -5.5F, -3.5F, 4, 4, 4, 0F);
		sauce.setRotationPoint(0F, 0F, 0F);
		sauce.rotateAngleX = 0F;
		sauce.rotateAngleY = -0.5585054F;
		sauce.rotateAngleZ = 0F;
		sauce.mirror = false;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		dish1.render(scale);
		dish2.render(scale);
		dish3.render(scale);
		dish4.render(scale);
		dish5.render(scale);
		egg1.render(scale);
		egg2.render(scale);
		egg3.render(scale);
		egg4.render(scale);
		egg5.render(scale);
		sauce.render(scale);
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

	public void setIndividualRotation(FoodEntityBase entity) {

	}

}
