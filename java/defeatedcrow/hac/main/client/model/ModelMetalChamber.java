package defeatedcrow.hac.main.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;

@SideOnly(Side.CLIENT)
public class ModelMetalChamber extends DCTileModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer top;
	ModelRenderer back;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer front1;
	ModelRenderer front2;
	ModelRenderer front3;
	ModelRenderer front5;
	ModelRenderer front6;
	ModelRenderer front7;
	ModelRenderer front8;
	ModelRenderer charcoal1;
	ModelRenderer charcoal2;
	ModelRenderer charcoal3;

	public ModelMetalChamber() {
		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-8F, 7F, -8F, 16, 1, 16);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(128, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-8F, -8F, -8F, 16, 1, 16);
		top.setRotationPoint(0F, 16F, 0F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		back = new ModelRenderer(this, 66, 0);
		back.addBox(-8F, -7F, 7F, 16, 14, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(128, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 20);
		side1.addBox(-8F, -7F, -7F, 1, 14, 14);
		side1.setRotationPoint(0F, 16F, 0F);
		side1.setTextureSize(128, 64);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 32, 20);
		side2.addBox(7F, -7F, -7F, 1, 14, 14);
		side2.setRotationPoint(0F, 16F, 0F);
		side2.setTextureSize(128, 64);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		front1 = new ModelRenderer(this, 64, 20);
		front1.addBox(-8F, -7F, -8F, 16, 3, 1);
		front1.setRotationPoint(0F, 16F, 0F);
		front1.setTextureSize(128, 64);
		front1.mirror = true;
		setRotation(front1, 0F, 0F, 0F);
		front2 = new ModelRenderer(this, 64, 26);
		front2.addBox(-8F, -4F, -8F, 2, 11, 1);
		front2.setRotationPoint(0F, 16F, 0F);
		front2.setTextureSize(128, 64);
		front2.mirror = true;
		setRotation(front2, 0F, 0F, 0F);
		front3 = new ModelRenderer(this, 64, 26);
		front3.addBox(6F, -4F, -8F, 2, 11, 1);
		front3.setRotationPoint(0F, 16F, 0F);
		front3.setTextureSize(128, 64);
		front3.mirror = true;
		setRotation(front3, 0F, 0F, 0F);
		front5 = new ModelRenderer(this, 71, 26);
		front5.addBox(-6F, -4F, -8F, 1, 4, 1);
		front5.setRotationPoint(0F, 16F, 0F);
		front5.setTextureSize(128, 64);
		front5.mirror = true;
		setRotation(front5, 0F, 0F, 0F);
		front6 = new ModelRenderer(this, 71, 26);
		front6.addBox(5F, -4F, -8F, 1, 4, 1);
		front6.setRotationPoint(0F, 16F, 0F);
		front6.setTextureSize(128, 64);
		front6.mirror = true;
		setRotation(front6, 0F, 0F, 0F);
		front7 = new ModelRenderer(this, 76, 26);
		front7.addBox(-5F, -4F, -8F, 1, 1, 1);
		front7.setRotationPoint(0F, 16F, 0F);
		front7.setTextureSize(128, 64);
		front7.mirror = true;
		setRotation(front7, 0F, 0F, 0F);
		front8 = new ModelRenderer(this, 76, 26);
		front8.addBox(4F, -4F, -8F, 1, 1, 1);
		front8.setRotationPoint(0F, 16F, 0F);
		front8.setTextureSize(128, 64);
		front8.mirror = true;
		setRotation(front8, 0F, 0F, 0F);
		charcoal1 = new ModelRenderer(this, 64, 40);
		charcoal1.addBox(-1F, -1F, -3F, 2, 2, 5);
		charcoal1.setRotationPoint(-2F, 21F, 0F);
		charcoal1.setTextureSize(128, 64);
		charcoal1.mirror = true;
		setRotation(charcoal1, 0.5235988F, 0.7853982F, 0F);
		charcoal2 = new ModelRenderer(this, 80, 40);
		charcoal2.addBox(-1F, -1F, -3F, 2, 2, 6);
		charcoal2.setRotationPoint(1F, 22F, 1F);
		charcoal2.setTextureSize(128, 64);
		charcoal2.mirror = true;
		setRotation(charcoal2, -0.0349066F, -1.047198F, 0F);
		charcoal3 = new ModelRenderer(this, 98, 40);
		charcoal3.addBox(-1F, -1F, -2F, 2, 2, 4);
		charcoal3.setRotationPoint(0F, 22F, -2F);
		charcoal3.setTextureSize(128, 64);
		charcoal3.mirror = true;
		setRotation(charcoal3, 0.1396263F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(0.0625F);
		bottom.render(0.0625F);
		top.render(0.0625F);
		back.render(0.0625F);
		side1.render(0.0625F);
		side2.render(0.0625F);
		front1.render(0.0625F);
		front2.render(0.0625F);
		front3.render(0.0625F);
		front5.render(0.0625F);
		front6.render(0.0625F);
		front7.render(0.0625F);
		front8.render(0.0625F);
		charcoal1.render(0.0625F);
		charcoal2.render(0.0625F);
		charcoal3.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f) {
	}

}
