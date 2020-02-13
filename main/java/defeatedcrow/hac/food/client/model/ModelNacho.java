package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelNacho extends DCFoodModelBase {

	public ModelRenderer dish1;
	public ModelRenderer dish2;
	public ModelRenderer dish3;
	public ModelRenderer dish4;
	public ModelRenderer dish5;
	public ModelRenderer chips1;
	public ModelRenderer chips2;
	public ModelRenderer chips3;
	public ModelRenderer chips4;
	public ModelRenderer chips5;
	public ModelRenderer chips6;
	public ModelRenderer chips7;

	public ModelNacho(boolean baked) {
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
		chips1 = new ModelRenderer(this, 0, 19);
		chips1.addBox(1F, 0F, 1F, 4, 1, 4, 0F);
		chips1.setRotationPoint(0F, -2F, 0F);
		chips1.rotateAngleX = 0.6108652F;
		chips1.rotateAngleY = 0.6108652F;
		chips1.rotateAngleZ = 0F;
		chips1.mirror = false;
		chips2 = new ModelRenderer(this, 0, 19);
		chips2.addBox(1F, 0F, 1F, 4, 1, 4, 0F);
		chips2.setRotationPoint(0F, -1F, 0F);
		chips2.rotateAngleX = 0.7853982F;
		chips2.rotateAngleY = 2.181662F;
		chips2.rotateAngleZ = 0F;
		chips2.mirror = false;
		chips3 = new ModelRenderer(this, 0, 19);
		chips3.addBox(-1F, 0F, -6F, 4, 1, 4, 0F);
		chips3.setRotationPoint(0F, -1F, 0F);
		chips3.rotateAngleX = -0.4537856F;
		chips3.rotateAngleY = 2.094395F;
		chips3.rotateAngleZ = 0F;
		chips3.mirror = false;
		chips4 = new ModelRenderer(this, 17, 19);
		chips4.addBox(-2F, 0F, 1.5F, 4, 1, 4, 0F);
		chips4.setRotationPoint(0F, -1F, 0F);
		chips4.rotateAngleX = 0.5235988F;
		chips4.rotateAngleY = 0.3490658F;
		chips4.rotateAngleZ = 0F;
		chips4.mirror = false;
		chips5 = new ModelRenderer(this, 17, 19);
		chips5.addBox(-2F, 0F, 1F, 4, 1, 4, 0F);
		chips5.setRotationPoint(0F, -1F, 0F);
		chips5.rotateAngleX = 0.7330383F;
		chips5.rotateAngleY = -2.094395F;
		chips5.rotateAngleZ = 0F;
		chips5.mirror = false;
		chips6 = new ModelRenderer(this, 17, 19);
		chips6.addBox(-2F, 0F, 2F, 4, 1, 4, 0F);
		chips6.setRotationPoint(0F, -1F, 0F);
		chips6.rotateAngleX = 0.418879F;
		chips6.rotateAngleY = 2.319532F;
		chips6.rotateAngleZ = 0F;
		chips6.mirror = false;
		chips7 = new ModelRenderer(this, 0, 19);
		chips7.addBox(-3F, 0F, -1F, 4, 1, 4, 0F);
		chips7.setRotationPoint(0F, -3F, 0F);
		chips7.rotateAngleX = 0.5585054F;
		chips7.rotateAngleY = 0.7853982F;
		chips7.rotateAngleZ = 0F;
		chips7.mirror = false;
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void setIndividualRotation(FoodEntityBase entity) {}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		dish1.render(scale);
		dish2.render(scale);
		dish3.render(scale);
		dish4.render(scale);
		dish5.render(scale);

	}

	public void renderChips(float scale) {
		chips1.render(scale);
		chips2.render(scale);
		chips3.render(scale);
		chips4.render(scale);
		chips5.render(scale);
		chips6.render(scale);
		chips7.render(scale);
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
