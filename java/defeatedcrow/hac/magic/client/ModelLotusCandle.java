package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLotusCandle extends DCTileModelBase {

	ModelRenderer bottom;
	ModelRenderer middle;
	ModelRenderer petal1;
	ModelRenderer petal2;

	public ModelLotusCandle() {

		textureWidth = 64;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-4F, 0F, 0F, 8, 0, 10);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 0, 0);
		middle.addBox(-1F, -3F, -1F, 2, 2, 2);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.setTextureSize(64, 32);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		petal1 = new ModelRenderer(this, 0, 12);
		petal1.addBox(-3F, 0F, 0F, 6, 0, 8);
		petal1.setRotationPoint(0F, 0F, 0F);
		petal1.setTextureSize(64, 32);
		petal1.mirror = true;
		setRotation(petal1, 0.3490556F, 0F, 0F);
		petal2 = new ModelRenderer(this, 30, 12);
		petal2.addBox(-3F, 0F, 0F, 6, 0, 8);
		petal2.setRotationPoint(0F, 0F, 0F);
		petal2.setTextureSize(64, 32);
		petal2.mirror = true;
		setRotation(petal2, 0.785375F, 0.4537856F, 0F);
	}

	@Override
	public void render(float scale) {
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
	}

	public void renderMiddle(float scale) {
		middle.render(scale);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bottom.render(scale);
		petal1.render(scale);
		petal2.render(scale);
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
