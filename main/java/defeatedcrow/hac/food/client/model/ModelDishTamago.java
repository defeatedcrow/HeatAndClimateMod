package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDishTamago extends DCFoodModelBase {

	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer dish3;
	ModelRenderer egg1;
	ModelRenderer egg2;
	ModelRenderer egg3;
	ModelRenderer oroshi;
	ModelRenderer leaf;

	public ModelDishTamago(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 64;

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-10F, -2F, -6F, 20, 1, 12);
		dish1.setRotationPoint(0F, 0F, 0F);
		dish1.setTextureSize(64, 32);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 13);
		dish2.addBox(-7F, -1F, -5F, 1, 2, 10);
		dish2.setRotationPoint(0F, 0F, 0F);
		dish2.setTextureSize(64, 32);
		dish2.mirror = true;
		setRotation(dish2, 0F, 0F, 0F);
		dish3 = new ModelRenderer(this, 0, 13);
		dish3.addBox(6F, -1F, -5F, 1, 2, 10);
		dish3.setRotationPoint(0F, 0F, 0F);
		dish3.setTextureSize(64, 32);
		dish3.mirror = true;
		setRotation(dish3, 0F, 0F, 0F);
		egg1 = new ModelRenderer(this, 0, 32);
		egg1.addBox(-1.5F, -2F, -3F, 3, 4, 6);
		egg1.setRotationPoint(-5F, -4F, 0F);
		egg1.setTextureSize(64, 64);
		egg1.mirror = true;
		setRotation(egg1, 0F, -0.3141593F, 0F);
		egg2 = new ModelRenderer(this, 0, 32);
		egg2.addBox(-1.5F, -2F, -3F, 3, 4, 6);
		egg2.setRotationPoint(-1.5F, -4F, 0F);
		egg2.setTextureSize(64, 64);
		egg2.mirror = true;
		setRotation(egg2, 0F, -0.3141593F, 0F);
		egg3 = new ModelRenderer(this, 0, 32);
		egg3.addBox(-1.5F, -2F, -3F, 3, 4, 6);
		egg3.setRotationPoint(2F, -4F, 0F);
		egg3.setTextureSize(64, 64);
		egg3.mirror = true;
		setRotation(egg3, 0F, -0.3141593F, 0F);
		oroshi = new ModelRenderer(this, 20, 40);
		oroshi.addBox(0F, 0F, 0F, 3, 3, 3);
		oroshi.setRotationPoint(4.5F, -5F, -1F);
		oroshi.setTextureSize(64, 64);
		oroshi.mirror = true;
		setRotation(oroshi, 0F, -0.0523599F, 0F);
		leaf = new ModelRenderer(this, 20, 32);
		leaf.addBox(0F, 0F, 0F, 6, 0, 6);
		leaf.setRotationPoint(3F, -2F, -1F);
		leaf.setTextureSize(64, 64);
		leaf.mirror = true;
		setRotation(leaf, 0.1396263F, 0.0743572F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		dish1.render(scale);
		dish2.render(scale);
		dish3.render(scale);
		egg1.render(scale);
		egg2.render(scale);
		egg3.render(scale);
		oroshi.render(scale);
		leaf.render(scale);
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
