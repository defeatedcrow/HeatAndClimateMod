package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishTaco extends DCFoodModelBase {

	public ModelRenderer dish1;
	public ModelRenderer dish2;
	public ModelRenderer dish3;
	public ModelRenderer dish4;
	public ModelRenderer dish5;
	public ModelRenderer leaf1;
	public ModelRenderer leaf2;
	public ModelRenderer leaf3;
	public ModelRenderer leaf4;
	public ModelRenderer leaf5;
	public ModelRenderer leaf6;
	public ModelRenderer leaf7;
	public ModelRenderer meat;
	public ModelRenderer meat2;
	public ModelRenderer taco1;
	public ModelRenderer taco2;
	public ModelRenderer taco3;
	public ModelRenderer taco4;
	public ModelRenderer taco5;
	public ModelRenderer taco6;
	public ModelRenderer taco7;
	public ModelRenderer taco8;
	public ModelRenderer taco9;
	public ModelRenderer taco10;

	public ModelDishTaco(boolean baked) {
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
		leaf1 = new ModelRenderer(this, 33, 0);
		leaf1.addBox(0F, 0F, 1F, 4, 0, 3, 0F);
		leaf1.setRotationPoint(0F, -2.8F, 0F);
		leaf1.rotateAngleX = 0.296706F;
		leaf1.rotateAngleY = 0.418879F;
		leaf1.rotateAngleZ = 0F;
		leaf1.mirror = false;
		leaf2 = new ModelRenderer(this, 33, 0);
		leaf2.addBox(1F, 0F, 0F, 4, 0, 3, 0F);
		leaf2.setRotationPoint(0F, -3F, 0F);
		leaf2.rotateAngleX = 0.296706F;
		leaf2.rotateAngleY = 1.256637F;
		leaf2.rotateAngleZ = 0F;
		leaf2.mirror = false;
		leaf3 = new ModelRenderer(this, 33, 0);
		leaf3.addBox(0F, 0F, 0F, 4, 0, 3, 0F);
		leaf3.setRotationPoint(0F, -3F, 1F);
		leaf3.rotateAngleX = 0.3665192F;
		leaf3.rotateAngleY = -0.4128892F;
		leaf3.rotateAngleZ = 0F;
		leaf3.mirror = false;
		leaf4 = new ModelRenderer(this, 33, 0);
		leaf4.addBox(0F, 0F, 0F, 4, 0, 3, 0F);
		leaf4.setRotationPoint(0F, -3F, 1F);
		leaf4.rotateAngleX = 0.3839724F;
		leaf4.rotateAngleY = -1.919862F;
		leaf4.rotateAngleZ = 0F;
		leaf4.mirror = false;
		leaf5 = new ModelRenderer(this, 33, 0);
		leaf5.addBox(0F, -3F, 0F, 4, 0, 3, 0F);
		leaf5.setRotationPoint(0F, 0F, 0F);
		leaf5.rotateAngleX = 0.2094395F;
		leaf5.rotateAngleY = -2.617994F;
		leaf5.rotateAngleZ = 0F;
		leaf5.mirror = false;
		leaf6 = new ModelRenderer(this, 33, 0);
		leaf6.addBox(0.5F, 0F, 0F, 4, 0, 3, 0F);
		leaf6.setRotationPoint(0F, -2.8F, 0F);
		leaf6.rotateAngleX = 0.2792527F;
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
		taco1 = new ModelRenderer(this, 0, 19);
		taco1.addBox(-2F, -1F, 1F, 4, 1, 6, 0F);
		taco1.setRotationPoint(0F, 0F, 0F);
		taco1.rotateAngleX = 0.5235988F;
		taco1.rotateAngleY = 0.08726646F;
		taco1.rotateAngleZ = 0F;
		taco1.mirror = false;
		taco2 = new ModelRenderer(this, 21, 19);
		taco2.addBox(-2F, -1F, 0.5F, 5, 1, 6, 0F);
		taco2.setRotationPoint(0F, 0F, 0F);
		taco2.rotateAngleX = 0.418879F;
		taco2.rotateAngleY = 0.6283185F;
		taco2.rotateAngleZ = 0F;
		taco2.mirror = false;
		taco3 = new ModelRenderer(this, 0, 19);
		taco3.addBox(-2F, -1F, 1F, 4, 1, 6, 0F);
		taco3.setRotationPoint(0F, 0F, 0F);
		taco3.rotateAngleX = 0.5061455F;
		taco3.rotateAngleY = 1.32645F;
		taco3.rotateAngleZ = 0F;
		taco3.mirror = false;
		taco4 = new ModelRenderer(this, 21, 19);
		taco4.addBox(-1F, -1F, 0F, 5, 1, 6, 0F);
		taco4.setRotationPoint(0F, 0F, 0F);
		taco4.rotateAngleX = 0.418879F;
		taco4.rotateAngleY = 1.832596F;
		taco4.rotateAngleZ = 0F;
		taco4.mirror = false;
		taco5 = new ModelRenderer(this, 0, 19);
		taco5.addBox(-2F, -1F, 1F, 4, 1, 6, 0F);
		taco5.setRotationPoint(0F, 0F, 0F);
		taco5.rotateAngleX = 0.4712389F;
		taco5.rotateAngleY = 2.70526F;
		taco5.rotateAngleZ = 0F;
		taco5.mirror = false;
		taco6 = new ModelRenderer(this, 21, 19);
		taco6.addBox(-2F, -1F, 0.5F, 5, 1, 6, 0F);
		taco6.setRotationPoint(0F, 0F, 0F);
		taco6.rotateAngleX = 0.418879F;
		taco6.rotateAngleY = -2.984513F;
		taco6.rotateAngleZ = 0F;
		taco6.mirror = false;
		taco7 = new ModelRenderer(this, 0, 19);
		taco7.addBox(-2F, -1F, 1F, 4, 1, 6, 0F);
		taco7.setRotationPoint(0F, 0F, 0F);
		taco7.rotateAngleX = 0.4886922F;
		taco7.rotateAngleY = -2.268928F;
		taco7.rotateAngleZ = 0F;
		taco7.mirror = false;
		taco8 = new ModelRenderer(this, 21, 19);
		taco8.addBox(-2F, -1F, 0.5F, 5, 1, 6, 0F);
		taco8.setRotationPoint(0F, 0F, 0F);
		taco8.rotateAngleX = 0.418879F;
		taco8.rotateAngleY = -1.745329F;
		taco8.rotateAngleZ = 0F;
		taco8.mirror = false;
		taco9 = new ModelRenderer(this, 0, 19);
		taco9.addBox(-2F, -1F, 1F, 4, 1, 6, 0F);
		taco9.setRotationPoint(0F, 0F, 0F);
		taco9.rotateAngleX = 0.4886922F;
		taco9.rotateAngleY = -1.047198F;
		taco9.rotateAngleZ = 0F;
		taco9.mirror = false;
		taco10 = new ModelRenderer(this, 21, 19);
		taco10.addBox(-2F, -1F, 0.5F, 5, 1, 6, 0F);
		taco10.setRotationPoint(0F, 0F, 0F);
		taco10.rotateAngleX = 0.4014257F;
		taco10.rotateAngleY = -0.554581F;
		taco10.rotateAngleZ = 0F;
		taco10.mirror = false;
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
		leaf1.render(scale);
		leaf2.render(scale);
		leaf3.render(scale);
		leaf4.render(scale);
		leaf5.render(scale);
		leaf6.render(scale);
		leaf7.render(scale);
		meat.render(scale);
		meat2.render(scale);
		taco1.render(scale);
		taco2.render(scale);
		taco3.render(scale);
		taco4.render(scale);
		taco5.render(scale);
		taco6.render(scale);
		taco7.render(scale);
		taco8.render(scale);
		taco9.render(scale);
		taco10.render(scale);
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
