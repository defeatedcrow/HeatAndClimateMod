package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCrusher extends DCTileModelBase {

	ModelRenderer body1;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer bottom;
	ModelRenderer hopper1;
	ModelRenderer hopper2;
	ModelRenderer shaft;
	ModelRenderer gear;

	public ModelCrusher() {

		textureWidth = 64;
		textureHeight = 64;

		body1 = new ModelRenderer(this, 0, 0);
		body1.addBox(-5F, -3F, -6F, 10, 10, 5);
		body1.setRotationPoint(0F, 0F, 0F);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 0, 0);
		body2.addBox(-5F, -3F, -6F, 10, 10, 5);
		body2.setRotationPoint(0F, 0F, 0F);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0F, 3.141593F, 0F);
		body3 = new ModelRenderer(this, 32, 0);
		body3.addBox(-6F, -4F, -1F, 12, 12, 2);
		body3.setRotationPoint(0F, 0F, 0F);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 0, 16);
		bottom.addBox(-4F, 7F, -4F, 8, 1, 8);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		hopper1 = new ModelRenderer(this, 0, 26);
		hopper1.addBox(-3F, -5F, -3F, 6, 2, 6);
		hopper1.setRotationPoint(0F, 0F, 0F);
		hopper1.setTextureSize(64, 64);
		hopper1.mirror = true;
		setRotation(hopper1, 0F, 0F, 0F);
		hopper2 = new ModelRenderer(this, 25, 26);
		hopper2.addBox(-4F, -8F, -4F, 8, 3, 8);
		hopper2.setRotationPoint(0F, 0F, 0F);
		hopper2.setTextureSize(64, 64);
		hopper2.mirror = true;
		setRotation(hopper2, 0F, 0F, 0F);
		shaft = new ModelRenderer(this, 0, 38);
		shaft.addBox(-2F, -2F, 6F, 4, 4, 1);
		shaft.setRotationPoint(0F, 0F, 0F);
		shaft.setTextureSize(64, 64);
		shaft.mirror = true;
		setRotation(shaft, 0F, 0F, 0F);
		gear = new ModelRenderer(this, 0, 44);
		gear.addBox(-3F, -3F, 7F, 6, 6, 1);
		gear.setRotationPoint(0F, 0F, 0F);
		gear.setTextureSize(64, 64);
		gear.mirror = true;
		setRotation(gear, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		body1.render(scale);
		body2.render(scale);
		body3.render(scale);
		bottom.render(scale);
		hopper1.render(scale);
		hopper2.render(scale);
		shaft.render(scale);
		gear.render(scale);
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
