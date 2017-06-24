package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFreezer extends DCTileModelBase {

	ModelRenderer control;
	ModelRenderer bottom;
	ModelRenderer tank1;
	ModelRenderer tank2;
	ModelRenderer body;
	ModelRenderer panel;
	ModelRenderer bar1;
	ModelRenderer bar2;

	public ModelFreezer() {

		textureWidth = 128;
		textureHeight = 64;

		control = new ModelRenderer(this, 0, 0);
		control.addBox(0.5F, -7F, -7.5F, 7, 14, 4);
		control.setRotationPoint(0F, 0F, 0F);
		control.setTextureSize(128, 64);
		control.mirror = true;
		setRotation(control, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 64, 0);
		bottom.addBox(-8F, 7F, -8F, 16, 1, 16);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		tank1 = new ModelRenderer(this, 0, 28);
		tank1.addBox(-8F, -0.5F, -8F, 8, 7, 16);
		tank1.setRotationPoint(0F, 0F, 0F);
		tank1.setTextureSize(128, 64);
		tank1.mirror = true;
		setRotation(tank1, 0F, 0F, 0F);
		tank2 = new ModelRenderer(this, 0, 28);
		tank2.addBox(-8F, -8F, -8F, 8, 7, 16);
		tank2.setRotationPoint(0F, 0F, 0F);
		tank2.setTextureSize(128, 64);
		tank2.mirror = true;
		setRotation(tank2, 0F, 0F, 0F);
		body = new ModelRenderer(this, 24, 0);
		body.addBox(0F, -7.5F, -3.5F, 7, 15, 11);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		panel = new ModelRenderer(this, 64, 20);
		panel.addBox(7F, -3F, -3F, 1, 6, 6);
		panel.setRotationPoint(0F, 0F, 0F);
		panel.setTextureSize(128, 64);
		panel.mirror = true;
		setRotation(panel, 0F, 0F, 0F);
		bar1 = new ModelRenderer(this, 80, 20);
		bar1.addBox(-7F, -1F, -6F, 6, 8, 1);
		bar1.setRotationPoint(0F, 0F, 0F);
		bar1.setTextureSize(128, 64);
		bar1.mirror = true;
		setRotation(bar1, 0F, 0F, 0F);
		bar2 = new ModelRenderer(this, 80, 20);
		bar2.addBox(-7F, -1F, 5F, 6, 8, 1);
		bar2.setRotationPoint(0F, 0F, 0F);
		bar2.setTextureSize(128, 64);
		bar2.mirror = true;
		setRotation(bar2, 0F, 0F, 0F);
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
		tank1.render(scale);
		tank2.render(scale);
		control.render(scale);
		body.render(scale);
		bottom.render(scale);
		panel.render(scale);
		bar1.render(scale);
		bar2.render(scale);
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
