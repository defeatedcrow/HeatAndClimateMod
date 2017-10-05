package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelButterflyCage extends DCTileModelBase {

	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape21;
	ModelRenderer Shape22;
	ModelRenderer Shape23;
	ModelRenderer Shape24;
	ModelRenderer Shape31;
	ModelRenderer Shape32;
	ModelRenderer Shape33;
	ModelRenderer Shape34;
	ModelRenderer glass;
	ModelRenderer body;
	ModelRenderer wing1;
	ModelRenderer wing2;

	public ModelButterflyCage() {

		textureWidth = 128;
		textureHeight = 64;

		Shape11 = new ModelRenderer(this, 0, 0);
		Shape11.addBox(-6F, 4F, -6F, 12, 2, 2);
		Shape11.setRotationPoint(0F, 16F, 0F);
		Shape11.setTextureSize(128, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 0, 0);
		Shape12.addBox(-6F, 4F, 4F, 12, 2, 2);
		Shape12.setRotationPoint(0F, 16F, 0F);
		Shape12.setTextureSize(128, 64);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 0, 0);
		Shape13.addBox(-6F, -6F, -6F, 12, 2, 2);
		Shape13.setRotationPoint(0F, 16F, 0F);
		Shape13.setTextureSize(128, 64);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 0);
		Shape14.addBox(-6F, -6F, 4F, 12, 2, 2);
		Shape14.setRotationPoint(0F, 16F, 0F);
		Shape14.setTextureSize(128, 64);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape21 = new ModelRenderer(this, 0, 6);
		Shape21.addBox(-6F, 4F, -4F, 2, 2, 8);
		Shape21.setRotationPoint(0F, 16F, 0F);
		Shape21.setTextureSize(128, 64);
		Shape21.mirror = true;
		setRotation(Shape21, 0F, 0F, 0F);
		Shape22 = new ModelRenderer(this, 0, 6);
		Shape22.addBox(4F, 4F, -4F, 2, 2, 8);
		Shape22.setRotationPoint(0F, 16F, 0F);
		Shape22.setTextureSize(128, 64);
		Shape22.mirror = true;
		setRotation(Shape22, 0F, 0F, 0F);
		Shape23 = new ModelRenderer(this, 0, 6);
		Shape23.addBox(-6F, -6F, -4F, 2, 2, 8);
		Shape23.setRotationPoint(0F, 16F, 0F);
		Shape23.setTextureSize(128, 64);
		Shape23.mirror = true;
		setRotation(Shape23, 0F, 0F, 0F);
		Shape24 = new ModelRenderer(this, 0, 6);
		Shape24.addBox(4F, -6F, -4F, 2, 2, 8);
		Shape24.setRotationPoint(0F, 16F, 0F);
		Shape24.setTextureSize(128, 64);
		Shape24.mirror = true;
		setRotation(Shape24, 0F, 0F, 0F);
		Shape31 = new ModelRenderer(this, 22, 6);
		Shape31.addBox(-6F, -4F, -6F, 2, 8, 2);
		Shape31.setRotationPoint(0F, 16F, 0F);
		Shape31.setTextureSize(128, 64);
		Shape31.mirror = true;
		setRotation(Shape31, 0F, 0F, 0F);
		Shape32 = new ModelRenderer(this, 22, 6);
		Shape32.addBox(-6F, -4F, 4F, 2, 8, 2);
		Shape32.setRotationPoint(0F, 16F, 0F);
		Shape32.setTextureSize(128, 64);
		Shape32.mirror = true;
		setRotation(Shape32, 0F, 0F, 0F);
		Shape33 = new ModelRenderer(this, 22, 6);
		Shape33.addBox(4F, -4F, -6F, 2, 8, 2);
		Shape33.setRotationPoint(0F, 16F, 0F);
		Shape33.setTextureSize(128, 64);
		Shape33.mirror = true;
		setRotation(Shape33, 0F, 0F, 0F);
		Shape34 = new ModelRenderer(this, 22, 6);
		Shape34.addBox(4F, -4F, 4F, 2, 8, 2);
		Shape34.setRotationPoint(0F, 16F, 0F);
		Shape34.setTextureSize(128, 64);
		Shape34.mirror = true;
		setRotation(Shape34, 0F, 0F, 0F);
		glass = new ModelRenderer(this, 32, 0);
		glass.addBox(-5F, -5F, -5F, 10, 10, 10);
		glass.setRotationPoint(0F, 16F, 0F);
		glass.setTextureSize(128, 64);
		glass.mirror = true;
		setRotation(glass, 0F, 0F, 0F);
		body = new ModelRenderer(this, 12, 20);
		body.addBox(-0.5F, -1F, -2.5F, 1, 3, 1);
		body.setRotationPoint(0F, 16F, 0F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0523599F, 0F, 0F);
		wing1 = new ModelRenderer(this, 0, 20);
		wing1.addBox(0.5F, -3F, -0.5F, 0, 6, 5);
		wing1.setRotationPoint(0F, 16F, -1F);
		wing1.setTextureSize(128, 64);
		wing1.mirror = true;
		setRotation(wing1, 0F, 0.75F, 0F);
		wing2 = new ModelRenderer(this, 0, 20);
		wing2.addBox(-0.5F, -3F, -0.5F, 0, 6, 5);
		wing2.setRotationPoint(0F, 16F, -1F);
		wing2.setTextureSize(128, 64);
		wing2.mirror = true;
		setRotation(wing2, 0F, -0.75F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void renderGlass(float scale) {
		glass.render(scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		Shape11.render(scale);
		Shape12.render(scale);
		Shape13.render(scale);
		Shape14.render(scale);
		Shape21.render(scale);
		Shape22.render(scale);
		Shape23.render(scale);
		Shape24.render(scale);
		Shape31.render(scale);
		Shape32.render(scale);
		Shape33.render(scale);
		Shape34.render(scale);

		body.render(scale);
		wing1.render(scale);
		wing2.render(scale);
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
