package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelClubSand extends DCFoodModelBase {

	ModelRenderer bread1;
	ModelRenderer beef1;
	ModelRenderer beef2;
	ModelRenderer lettuce1;
	ModelRenderer lettuce2;
	ModelRenderer lettuce3;
	ModelRenderer tomato1;
	ModelRenderer tomato2;
	ModelRenderer tomato3;
	ModelRenderer bread2;
	ModelRenderer beef3;
	ModelRenderer beef4;
	ModelRenderer lettuce4;
	ModelRenderer lettuce5;
	ModelRenderer lettuce6;
	ModelRenderer tomato4;
	ModelRenderer tomato5;
	ModelRenderer tomato6;
	ModelRenderer bread3;
	ModelRenderer pin;

	public ModelClubSand(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		bread1 = new ModelRenderer(this, 0, 0);
		bread1.addBox(-5F, 0F, -5F, 10, 1, 10);
		bread1.setRotationPoint(0F, 0F, 0F);
		bread1.setTextureSize(64, 32);
		bread1.mirror = true;
		setRotation(bread1, 0F, 0F, 0F);
		beef1 = new ModelRenderer(this, 0, 12);
		beef1.addBox(-4.5F, -1F, -4.5F, 9, 1, 5);
		beef1.setRotationPoint(0F, 0F, 0F);
		beef1.setTextureSize(64, 32);
		beef1.mirror = true;
		setRotation(beef1, -0.0174533F, 0.122173F, 0F);
		beef2 = new ModelRenderer(this, 0, 12);
		beef2.addBox(-5F, -1F, -0.5F, 9, 1, 5);
		beef2.setRotationPoint(0F, 0F, 0F);
		beef2.setTextureSize(64, 32);
		beef2.mirror = true;
		setRotation(beef2, 0.0349066F, 0.0174533F, 0F);
		lettuce1 = new ModelRenderer(this, 0, 22);
		lettuce1.addBox(-5F, -1.3F, -4F, 10, 0, 9);
		lettuce1.setRotationPoint(0F, 0F, 0F);
		lettuce1.setTextureSize(64, 32);
		lettuce1.mirror = true;
		setRotation(lettuce1, 0.0872665F, 0.1745329F, 0F);
		lettuce2 = new ModelRenderer(this, 0, 22);
		lettuce2.addBox(-5F, -1.2F, -5F, 10, 0, 9);
		lettuce2.setRotationPoint(0F, 0F, 0F);
		lettuce2.setTextureSize(64, 32);
		lettuce2.mirror = true;
		setRotation(lettuce2, -0.0523599F, 1.047198F, 0F);
		lettuce3 = new ModelRenderer(this, 0, 22);
		lettuce3.addBox(-5F, -1.5F, -5F, 10, 0, 9);
		lettuce3.setRotationPoint(0F, 0F, 0F);
		lettuce3.setTextureSize(64, 32);
		lettuce3.mirror = true;
		setRotation(lettuce3, -0.0872665F, 1.343077F, 0F);
		tomato1 = new ModelRenderer(this, 30, 12);
		tomato1.addBox(0F, -2.5F, 0F, 4, 1, 4);
		tomato1.setRotationPoint(0F, 0F, 0F);
		tomato1.setTextureSize(64, 32);
		tomato1.mirror = true;
		setRotation(tomato1, 0F, 0.3490659F, 0F);
		tomato2 = new ModelRenderer(this, 30, 12);
		tomato2.addBox(0F, -2.5F, 0F, 4, 1, 4);
		tomato2.setRotationPoint(0F, 0F, 0F);
		tomato2.setTextureSize(64, 32);
		tomato2.mirror = true;
		setRotation(tomato2, 0F, 2.268928F, 0F);
		tomato3 = new ModelRenderer(this, 30, 12);
		tomato3.addBox(0F, -2.5F, 0F, 4, 1, 4);
		tomato3.setRotationPoint(0F, 0F, 0F);
		tomato3.setTextureSize(64, 32);
		tomato3.mirror = true;
		setRotation(tomato3, 0F, -1.570796F, 0F);
		bread2 = new ModelRenderer(this, 0, 0);
		bread2.addBox(-5F, -4.5F, -5F, 10, 1, 10);
		bread2.setRotationPoint(0F, 1F, 0F);
		bread2.setTextureSize(64, 32);
		bread2.mirror = true;
		setRotation(bread2, 0F, 0.0523599F, 0F);
		beef3 = new ModelRenderer(this, 0, 12);
		beef3.addBox(-4.5F, -4.5F, -0.5F, 9, 1, 5);
		beef3.setRotationPoint(0F, 0F, 0F);
		beef3.setTextureSize(64, 32);
		beef3.mirror = true;
		setRotation(beef3, -0.0174533F, 0.122173F, 0F);
		beef4 = new ModelRenderer(this, 0, 12);
		beef4.addBox(-4.5F, -4.2F, -5F, 9, 1, 5);
		beef4.setRotationPoint(0F, 0F, 0F);
		beef4.setTextureSize(64, 32);
		beef4.mirror = true;
		setRotation(beef4, -0.0698132F, 0.2268928F, 0F);
		lettuce4 = new ModelRenderer(this, 0, 22);
		lettuce4.addBox(-5F, -4.8F, -5F, 10, 0, 9);
		lettuce4.setRotationPoint(0F, 0F, 0F);
		lettuce4.setTextureSize(64, 32);
		lettuce4.mirror = true;
		setRotation(lettuce4, 0.0872665F, 0.3490659F, 0F);
		lettuce5 = new ModelRenderer(this, 0, 22);
		lettuce5.addBox(-5F, -4.8F, -5F, 10, 0, 9);
		lettuce5.setRotationPoint(0F, 0F, 0F);
		lettuce5.setTextureSize(64, 32);
		lettuce5.mirror = true;
		setRotation(lettuce5, -0.0523599F, 1.047198F, 0F);
		lettuce6 = new ModelRenderer(this, 0, 22);
		lettuce6.addBox(-4.5F, -4.8F, -4.5F, 10, 0, 9);
		lettuce6.setRotationPoint(0F, 0F, 0F);
		lettuce6.setTextureSize(64, 32);
		lettuce6.mirror = true;
		setRotation(lettuce6, -0.0872665F, 1.343077F, 0F);
		tomato4 = new ModelRenderer(this, 30, 12);
		tomato4.addBox(-1F, -6F, -1F, 4, 1, 4);
		tomato4.setRotationPoint(0F, 0F, 0F);
		tomato4.setTextureSize(64, 32);
		tomato4.mirror = true;
		setRotation(tomato4, 0F, 0.7853982F, 0F);
		tomato5 = new ModelRenderer(this, 30, 12);
		tomato5.addBox(0F, -6F, 0F, 4, 1, 4);
		tomato5.setRotationPoint(0F, 0F, 0F);
		tomato5.setTextureSize(64, 32);
		tomato5.mirror = true;
		setRotation(tomato5, 0F, 3.141593F, 0F);
		tomato6 = new ModelRenderer(this, 30, 12);
		tomato6.addBox(0F, -6F, 0F, 4, 1, 4);
		tomato6.setRotationPoint(0F, 0F, 0F);
		tomato6.setTextureSize(64, 32);
		tomato6.mirror = true;
		setRotation(tomato6, 0F, -1.308997F, 0F);
		bread3 = new ModelRenderer(this, 0, 0);
		bread3.addBox(-5F, -7F, -5F, 10, 1, 10);
		bread3.setRotationPoint(0F, 0F, 0F);
		bread3.setTextureSize(64, 32);
		bread3.mirror = true;
		setRotation(bread3, 0F, -0.0174533F, 0F);
		pin = new ModelRenderer(this, 0, 0);
		pin.addBox(0F, -10F, 0F, 1, 9, 1);
		pin.setRotationPoint(0F, 0F, 0F);
		pin.setTextureSize(64, 32);
		pin.mirror = true;
		setRotation(pin, -0.1396263F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		setIndividualRotation(entity);
		render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bread1.render(scale);
		beef1.render(scale);
		beef2.render(scale);
		lettuce1.render(scale);
		lettuce2.render(scale);
		lettuce3.render(scale);
		tomato1.render(scale);
		tomato2.render(scale);
		tomato3.render(scale);
		bread2.render(scale);
		beef3.render(scale);
		beef4.render(scale);
		lettuce4.render(scale);
		lettuce5.render(scale);
		lettuce6.render(scale);
		tomato4.render(scale);
		tomato5.render(scale);
		tomato6.render(scale);
		bread3.render(scale);
		pin.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
	}

	public void setIndividualRotation(FoodEntityBase entity) {
		if (entity != null) {
			float f1 = 0.1745329F;
			lettuce1.rotateAngleY = f1 + (entity.getIndividual() / 64F) * (float) (Math.PI);
			float f2 = 1.047198F;
			lettuce2.rotateAngleY = f2 + (entity.getIndividual() / 64F) * (float) (Math.PI);
			float f3 = 1.343077F;
			lettuce3.rotateAngleY = f3 + (entity.getIndividual() / 64F) * (float) (Math.PI);
			float f4 = 0.3490659F;
			lettuce4.rotateAngleY = f4 + (entity.getIndividual() / 64F) * (float) (Math.PI);
			float f5 = 1.343077F;
			lettuce5.rotateAngleY = f5 + (entity.getIndividual() / 64F) * (float) (Math.PI);
			float f6 = 1.343077F;
			lettuce6.rotateAngleY = f6 + (entity.getIndividual() / 64F) * (float) (Math.PI);
		}
	}

}
