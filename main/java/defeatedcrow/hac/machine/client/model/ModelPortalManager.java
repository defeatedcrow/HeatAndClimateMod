package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPortalManager extends DCTileModelBase {

	ModelRenderer bottom;
	ModelRenderer body;
	ModelRenderer top;
	ModelRenderer panel;

	public ModelPortalManager() {

		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-8F, -3F, -8F, 16, 3, 16);
		bottom.setRotationPoint(0F, 8F, 0F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 22);
		body.addBox(-7F, -12F, -7F, 14, 9, 14);
		body.setRotationPoint(0F, 8F, 0F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-8F, -15F, -8F, 16, 3, 16);
		top.setRotationPoint(0F, 8F, 0F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		panel = new ModelRenderer(this, 64, 0);
		panel.addBox(-3F, -16F, -3F, 6, 1, 6);
		panel.setRotationPoint(0F, 8F, 0F);
		panel.setTextureSize(128, 64);
		panel.mirror = true;
		setRotation(panel, 0F, 0F, 0F);
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
		top.render(scale);
		body.render(scale);
		bottom.render(scale);
		panel.render(scale);
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
