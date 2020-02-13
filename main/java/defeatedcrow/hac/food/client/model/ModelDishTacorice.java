package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishTacorice extends DCFoodModelBase {

	public ModelRenderer dish1;
	public ModelRenderer dish2;
	public ModelRenderer dish3;
	public ModelRenderer dish4;
	public ModelRenderer dish5;
	public ModelRenderer rice;
	public ModelRenderer leaf1;
	public ModelRenderer leaf2;
	public ModelRenderer leaf3;
	public ModelRenderer leaf4;
	public ModelRenderer leaf5;
	public ModelRenderer leaf6;
	public ModelRenderer leaf7;
	public ModelRenderer meat;
	public ModelRenderer meat2;

	public ModelDishTacorice(boolean baked) {
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
		rice = new ModelRenderer(this, 0, 19);
		rice.addBox(-4F, -3F, -4F, 8, 2, 8, 0F);
		rice.setRotationPoint(0F, 0F, 0F);
		rice.rotateAngleX = 0F;
		rice.rotateAngleY = 0F;
		rice.rotateAngleZ = 0F;
		rice.mirror = false;
		leaf1 = new ModelRenderer(this, 33, 0);
		leaf1.addBox(0F, 0F, 1F, 4, 0, 3, 0F);
		leaf1.setRotationPoint(0F, -2.8F, 0F);
		leaf1.rotateAngleX = 0.2094395F;
		leaf1.rotateAngleY = 0.418879F;
		leaf1.rotateAngleZ = 0F;
		leaf1.mirror = false;
		leaf2 = new ModelRenderer(this, 33, 0);
		leaf2.addBox(1F, 0F, 0F, 4, 0, 3, 0F);
		leaf2.setRotationPoint(0F, -3F, 0F);
		leaf2.rotateAngleX = 0.08726646F;
		leaf2.rotateAngleY = 1.256637F;
		leaf2.rotateAngleZ = 0F;
		leaf2.mirror = false;
		leaf3 = new ModelRenderer(this, 33, 0);
		leaf3.addBox(0F, 0F, 0F, 4, 0, 3, 0F);
		leaf3.setRotationPoint(0F, -3F, 1F);
		leaf3.rotateAngleX = 0.1745329F;
		leaf3.rotateAngleY = -0.4128892F;
		leaf3.rotateAngleZ = 0F;
		leaf3.mirror = false;
		leaf4 = new ModelRenderer(this, 33, 0);
		leaf4.addBox(0F, 0F, 0F, 4, 0, 3, 0F);
		leaf4.setRotationPoint(0F, -3F, 1F);
		leaf4.rotateAngleX = 0.03490658F;
		leaf4.rotateAngleY = -1.919862F;
		leaf4.rotateAngleZ = 0F;
		leaf4.mirror = false;
		leaf5 = new ModelRenderer(this, 33, 0);
		leaf5.addBox(0F, -3F, 0F, 4, 0, 3, 0F);
		leaf5.setRotationPoint(0F, 0F, 0F);
		leaf5.rotateAngleX = 0.1570796F;
		leaf5.rotateAngleY = -2.617994F;
		leaf5.rotateAngleZ = 0F;
		leaf5.mirror = false;
		leaf6 = new ModelRenderer(this, 33, 0);
		leaf6.addBox(0.5F, 0F, 0F, 4, 0, 3, 0F);
		leaf6.setRotationPoint(0F, -2.8F, 0F);
		leaf6.rotateAngleX = 0.2617994F;
		leaf6.rotateAngleY = 2.932153F;
		leaf6.rotateAngleZ = 0F;
		leaf6.mirror = false;
		leaf7 = new ModelRenderer(this, 33, 0);
		leaf7.addBox(0F, 0F, 0F, 4, 0, 3, 0F);
		leaf7.setRotationPoint(0F, -3F, 0F);
		leaf7.rotateAngleX = 0.06981317F;
		leaf7.rotateAngleY = 2.268928F;
		leaf7.rotateAngleZ = 0F;
		leaf7.mirror = false;
		meat = new ModelRenderer(this, 33, 4);
		meat.addBox(-2.5F, -4F, -2.5F, 5, 1, 5, 0F);
		meat.setRotationPoint(0F, 0F, 0F);
		meat.rotateAngleX = 0F;
		meat.rotateAngleY = 0F;
		meat.rotateAngleZ = 0F;
		meat.mirror = false;
		meat2 = new ModelRenderer(this, 33, 11);
		meat2.addBox(-2F, -4.1F, -2F, 4, 0, 4, 0F);
		meat2.setRotationPoint(0F, 0F, 0F);
		meat2.rotateAngleX = 0F;
		meat2.rotateAngleY = 0F;
		meat2.rotateAngleZ = 0F;
		meat2.mirror = false;
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
		rice.render(scale);
		leaf1.render(scale);
		leaf2.render(scale);
		leaf3.render(scale);
		leaf4.render(scale);
		leaf5.render(scale);
		leaf6.render(scale);
		leaf7.render(scale);
		meat.render(scale);
		meat2.render(scale);
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
