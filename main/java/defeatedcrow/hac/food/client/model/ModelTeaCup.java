package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.client.base.DCFoodModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTeaCup extends DCFoodModelBase {

	ModelRenderer base;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer hand1;
	ModelRenderer hand2;
	ModelRenderer hand3;
	ModelRenderer saucer;
	ModelRenderer saucer2;

	public ModelTeaCup(boolean baked) {
		super(baked);

		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-3F, 6F, -3F, 6, 1, 6);
		base.setRotationPoint(0F, -8F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 8);
		side1.addBox(-3F, 0F, -3.8F, 6, 6, 1);
		side1.setRotationPoint(0F, -8F, 0F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0.0349066F, 3.141593F, 0F);
		side2 = new ModelRenderer(this, 0, 8);
		side2.addBox(-3F, 0F, -3.8F, 6, 6, 1);
		side2.setRotationPoint(0F, -8F, 0F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0.0349066F, 0F, 0F);
		side3 = new ModelRenderer(this, 0, 8);
		side3.addBox(-3F, 0F, -3.8F, 6, 6, 1);
		side3.setRotationPoint(0F, -8F, 0F);
		side3.setTextureSize(64, 32);
		side3.mirror = true;
		setRotation(side3, 0.0349066F, 1.570796F, 0F);
		side4 = new ModelRenderer(this, 0, 8);
		side4.addBox(-3F, 0F, -3.8F, 6, 6, 1);
		side4.setRotationPoint(0F, -8F, 0F);
		side4.setTextureSize(64, 32);
		side4.mirror = true;
		setRotation(side4, 0.0349066F, -1.570796F, 0F);
		hand1 = new ModelRenderer(this, 0, 16);
		hand1.addBox(-0.5F, 1F, -6F, 1, 1, 3);
		hand1.setRotationPoint(0F, -8F, 0F);
		hand1.setTextureSize(64, 32);
		hand1.mirror = true;
		setRotation(hand1, 0F, 0F, 0F);
		hand2 = new ModelRenderer(this, 10, 16);
		hand2.addBox(-0.5F, 0.4F, -7F, 1, 4, 1);
		hand2.setRotationPoint(0F, -8F, 0F);
		hand2.setTextureSize(64, 32);
		hand2.mirror = true;
		setRotation(hand2, 0.0872665F, 0F, 0F);
		hand3 = new ModelRenderer(this, 0, 21);
		hand3.addBox(-0.5F, -4F, -6F, 1, 1, 3);
		hand3.setRotationPoint(0F, 0F, 0F);
		hand3.setTextureSize(64, 32);
		hand3.mirror = true;
		setRotation(hand3, 0F, 0F, 0F);
		saucer = new ModelRenderer(this, 16, 8);
		saucer.addBox(-6F, 7F, -6F, 12, 1, 12);
		saucer.setRotationPoint(0F, -8F, 0F);
		saucer.setTextureSize(64, 32);
		saucer.mirror = true;
		setRotation(saucer, 0F, 0F, 0F);
		saucer2 = new ModelRenderer(this, 0, 0);
		saucer2.addBox(-3F, 7.5F, -3F, 6, 1, 6);
		saucer2.setRotationPoint(0F, -8F, 0F);
		saucer2.setTextureSize(64, 32);
		saucer2.mirror = true;
		setRotation(saucer2, 0F, 0F, 0F);
	}

	@Override
	public void render(float scale, FoodEntityBase entity) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		base.render(0.0625F);
		side1.render(0.0625F);
		side2.render(0.0625F);
		side3.render(0.0625F);
		side4.render(0.0625F);
		hand1.render(0.0625F);
		hand2.render(0.0625F);
		hand3.render(0.0625F);
		saucer.render(0.0625F);
		saucer2.render(0.0625F);
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
