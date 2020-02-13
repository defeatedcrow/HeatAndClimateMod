package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPorkGinger extends DCFoodModelBase {

	ModelRenderer plate1;
	ModelRenderer plate2;
	ModelRenderer meat1;
	ModelRenderer meat2;
	ModelRenderer leaf1;
	ModelRenderer leaf2;
	ModelRenderer vegi1;
	ModelRenderer vegi2;

	public ModelPorkGinger(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		plate1 = new ModelRenderer(this, 0, 0);
		plate1.addBox(-5F, -1F, -6F, 10, 1, 12);
		plate1.setRotationPoint(0F, 0F, 0F);
		plate1.setTextureSize(64, 32);
		plate1.mirror = true;
		setRotation(plate1, 0F, 0F, 0F);
		plate2 = new ModelRenderer(this, 40, 0);
		plate2.addBox(-3F, 0F, -3F, 6, 1, 6);
		plate2.setRotationPoint(0F, 0F, 0F);
		plate2.setTextureSize(64, 32);
		plate2.mirror = true;
		setRotation(plate2, 0F, 0F, 0F);
		meat1 = new ModelRenderer(this, 0, 19);
		meat1.addBox(-4F, -3F, -3F, 6, 1, 3);
		meat1.setRotationPoint(0F, 0F, 0F);
		meat1.setTextureSize(64, 32);
		meat1.mirror = true;
		setRotation(meat1, 0.3141593F, -1.396263F, 0F);
		meat2 = new ModelRenderer(this, 0, 24);
		meat2.addBox(-1F, -2F, -1F, 3, 1, 6);
		meat2.setRotationPoint(0F, 0F, 0F);
		meat2.setTextureSize(64, 32);
		meat2.mirror = true;
		setRotation(meat2, 0F, 0.3490659F, 0F);
		leaf1 = new ModelRenderer(this, 38, 16);
		leaf1.addBox(-2F, -0.5F, 2F, 6, 0, 4);
		leaf1.setRotationPoint(0F, 0F, 0F);
		leaf1.setTextureSize(64, 32);
		leaf1.mirror = true;
		setRotation(leaf1, 0.3141593F, -1.117011F, 0F);
		leaf2 = new ModelRenderer(this, 38, 16);
		leaf2.addBox(-3F, -1F, 1.5F, 6, 0, 4);
		leaf2.setRotationPoint(0F, 0F, 0F);
		leaf2.setTextureSize(64, 32);
		leaf2.mirror = true;
		setRotation(leaf2, 0.2268928F, -1.53589F, 0F);
		vegi1 = new ModelRenderer(this, 38, 22);
		vegi1.addBox(-1F, -1F, -1F, 2, 1, 2);
		vegi1.setRotationPoint(-2F, -1F, -4F);
		vegi1.setTextureSize(64, 32);
		vegi1.mirror = true;
		setRotation(vegi1, 0F, 1.134464F, 0F);
		vegi2 = new ModelRenderer(this, 38, 22);
		vegi2.addBox(-1F, -1F, -1F, 2, 1, 2);
		vegi2.setRotationPoint(-2F, -1.5F, -3F);
		vegi2.setTextureSize(64, 32);
		vegi2.mirror = true;
		setRotation(vegi2, -0.5235988F, 0.5235988F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		plate1.render(scale);
		plate2.render(scale);
		meat1.render(scale);
		meat2.render(scale);
		leaf1.render(scale);
		leaf2.render(scale);
		vegi1.render(scale);
		vegi2.render(scale);
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
