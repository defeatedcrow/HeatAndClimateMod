package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelReactor extends DCTileModelBase {

	ModelRenderer enbox;
	ModelRenderer tank11;
	ModelRenderer tank12;
	ModelRenderer bottom;
	ModelRenderer tank21;
	ModelRenderer tank31;
	ModelRenderer tank22;
	ModelRenderer tank32;
	ModelRenderer top11;
	ModelRenderer shaft;
	ModelRenderer shaft2;
	ModelRenderer panel;

	public ModelReactor() {

		textureWidth = 128;
		textureHeight = 64;

		enbox = new ModelRenderer(this, 0, 0);
		enbox.addBox(-7F, -8F, -4F, 3, 12, 12);
		enbox.setRotationPoint(0F, 0F, 0F);
		enbox.setTextureSize(64, 32);
		enbox.mirror = true;
		setRotation(enbox, 0F, 0F, 0F);
		tank11 = new ModelRenderer(this, 32, 0);
		tank11.addBox(-4F, -6F, -2F, 12, 12, 10);
		tank11.setRotationPoint(0F, 0F, 0F);
		tank11.setTextureSize(64, 32);
		tank11.mirror = true;
		setRotation(tank11, 0F, 0F, 0F);
		tank12 = new ModelRenderer(this, 78, 0);
		tank12.addBox(-3F, -6.5F, -1F, 10, 12, 8);
		tank12.setRotationPoint(0F, 0F, 0F);
		tank12.setTextureSize(64, 32);
		tank12.mirror = true;
		setRotation(tank12, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 0, 26);
		bottom.addBox(-6F, 6F, -2F, 14, 2, 10);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		tank21 = new ModelRenderer(this, 0, 40);
		tank21.addBox(-4.1F, -4F, -8F, 6, 12, 6);
		tank21.setRotationPoint(0F, 0F, 0F);
		tank21.setTextureSize(64, 32);
		tank21.mirror = true;
		setRotation(tank21, 0F, 0F, 0F);
		tank31 = new ModelRenderer(this, 0, 40);
		tank31.addBox(2F, -4F, -8F, 6, 12, 6);
		tank31.setRotationPoint(0F, 0F, 0F);
		tank31.setTextureSize(64, 32);
		tank31.mirror = true;
		setRotation(tank31, 0F, 0F, 0F);
		tank22 = new ModelRenderer(this, 28, 40);
		tank22.addBox(-3F, -5F, -7F, 4, 2, 4);
		tank22.setRotationPoint(0F, 0F, 0F);
		tank22.setTextureSize(64, 32);
		tank22.mirror = true;
		setRotation(tank22, 0F, 0F, 0F);
		tank32 = new ModelRenderer(this, 28, 40);
		tank32.addBox(3F, -5F, -7F, 4, 2, 4);
		tank32.setRotationPoint(0F, 0F, 0F);
		tank32.setTextureSize(64, 32);
		tank32.mirror = true;
		setRotation(tank32, 0F, 0F, 0F);
		top11 = new ModelRenderer(this, 52, 24);
		top11.addBox(-4F, -7F, 2F, 12, 1, 2);
		top11.setRotationPoint(0F, 0F, 0F);
		top11.setTextureSize(64, 32);
		top11.mirror = true;
		setRotation(top11, 0F, 0F, 0F);
		shaft = new ModelRenderer(this, 52, 29);
		shaft.addBox(-0.5F, -1F, -0.5F, 1, 12, 1);
		shaft.setRotationPoint(2F, 10F, 3F);
		shaft.setTextureSize(64, 32);
		shaft.mirror = true;
		setRotation(shaft, 0F, 0F, 0F);
		shaft2 = new ModelRenderer(this, 58, 28);
		shaft2.addBox(-0.5F, 0F, -2F, 1, 3, 4);
		shaft2.setRotationPoint(2F, 16F, 3F);
		shaft2.setTextureSize(64, 32);
		shaft2.mirror = true;
		setRotation(shaft2, 0F, 0F, 0F);
		panel = new ModelRenderer(this, 82, 24);
		panel.addBox(-8F, -3F, -3F, 1, 6, 6);
		panel.setRotationPoint(0F, 0F, 0F);
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
		tank11.render(scale);
		tank12.render(scale);
		tank21.render(scale);
		tank22.render(scale);
		tank31.render(scale);
		tank32.render(scale);
		enbox.render(scale);
		bottom.render(scale);
		top11.render(scale);
		panel.render(scale);
	}

	public void renderReverce(float scale) {
		tank12.render(scale);
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
