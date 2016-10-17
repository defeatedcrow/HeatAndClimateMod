package defeatedcrow.hac.main.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.client.base.DCTileModelBase;

@SideOnly(Side.CLIENT)
public class ModelShitirin extends DCTileModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer back;
	ModelRenderer side;
	ModelRenderer side2;
	ModelRenderer front1;
	ModelRenderer front2;
	ModelRenderer front3;
	ModelRenderer top1;
	ModelRenderer top2;
	ModelRenderer top3;
	ModelRenderer top4;
	ModelRenderer inner;
	ModelRenderer net;
	ModelRenderer charcoal1;
	ModelRenderer charcoal2;
	ModelRenderer charcoal3;

	public ModelShitirin() {
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-5F, 6F, -5F, 10, 2, 10);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		back = new ModelRenderer(this, 0, 13);
		back.addBox(-5.5F, -5F, 5F, 11, 11, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		side = new ModelRenderer(this, 0, 13);
		side.addBox(-5.5F, -5F, 5F, 11, 11, 1);
		side.setRotationPoint(0F, 16F, 0F);
		side.setTextureSize(64, 64);
		side.mirror = true;
		setRotation(side, 0F, 1.570796F, 0F);
		side2 = new ModelRenderer(this, 0, 13);
		side2.addBox(-5.5F, -5F, 5F, 11, 11, 1);
		side2.setRotationPoint(0F, 16F, 0F);
		side2.setTextureSize(64, 64);
		side2.mirror = true;
		setRotation(side2, 0F, -1.570796F, 0F);
		front1 = new ModelRenderer(this, 25, 13);
		front1.addBox(-5.5F, -5F, -6F, 11, 8, 1);
		front1.setRotationPoint(0F, 16F, 0F);
		front1.setTextureSize(64, 64);
		front1.mirror = true;
		setRotation(front1, 0F, 0F, 0F);
		front2 = new ModelRenderer(this, 25, 23);
		front2.addBox(-5.5F, 3F, -6F, 5, 3, 1);
		front2.setRotationPoint(0F, 16F, 0F);
		front2.setTextureSize(64, 64);
		front2.mirror = true;
		setRotation(front2, 0F, 0F, 0F);
		front3 = new ModelRenderer(this, 38, 23);
		front3.addBox(2.5F, 3F, -6F, 3, 3, 1);
		front3.setRotationPoint(0F, 16F, 0F);
		front3.setTextureSize(64, 64);
		front3.mirror = true;
		setRotation(front3, 0F, 0F, 0F);
		top1 = new ModelRenderer(this, 0, 28);
		top1.addBox(-7F, -8F, -7F, 14, 3, 2);
		top1.setRotationPoint(0F, 16F, 0F);
		top1.setTextureSize(64, 64);
		top1.mirror = true;
		setRotation(top1, 0F, 0F, 0F);
		top2 = new ModelRenderer(this, 0, 28);
		top2.addBox(-7F, -8F, 5F, 14, 3, 2);
		top2.setRotationPoint(0F, 16F, 0F);
		top2.setTextureSize(64, 64);
		top2.mirror = true;
		setRotation(top2, 0F, 0F, 0F);
		top3 = new ModelRenderer(this, 33, 28);
		top3.addBox(-7F, -8F, -5F, 2, 3, 10);
		top3.setRotationPoint(0F, 16F, 0F);
		top3.setTextureSize(64, 64);
		top3.mirror = true;
		setRotation(top3, 0F, 0F, 0F);
		top4 = new ModelRenderer(this, 33, 28);
		top4.addBox(5F, -8F, -5F, 2, 3, 10);
		top4.setRotationPoint(0F, 16F, 0F);
		top4.setTextureSize(64, 64);
		top4.mirror = true;
		setRotation(top4, 0F, 0F, 0F);
		inner = new ModelRenderer(this, 12, 46);
		inner.addBox(-5F, 2F, -5F, 10, 1, 10);
		inner.setRotationPoint(0F, 16F, 0F);
		inner.setTextureSize(64, 64);
		inner.mirror = true;
		setRotation(inner, 0F, 0F, 0F);
		net = new ModelRenderer(this, 0, 43);
		net.addBox(-8F, -9F, -8F, 16, 1, 16);
		net.setRotationPoint(0F, 16F, 0F);
		net.setTextureSize(64, 64);
		net.mirror = true;
		setRotation(net, 0F, 0F, 0F);
		charcoal1 = new ModelRenderer(this, 0, 36);
		charcoal1.addBox(-1F, -1F, -1F, 4, 2, 2);
		charcoal1.setRotationPoint(0F, 17F, 0F);
		charcoal1.setTextureSize(64, 64);
		charcoal1.mirror = true;
		setRotation(charcoal1, 0F, 0.6320364F, 0F);
		charcoal2 = new ModelRenderer(this, 0, 36);
		charcoal2.addBox(-1F, -1F, -2F, 2, 2, 4);
		charcoal2.setRotationPoint(0F, 17F, 1F);
		charcoal2.setTextureSize(64, 64);
		charcoal2.mirror = true;
		setRotation(charcoal2, -0.3665191F, 0.837758F, 0F);
		charcoal3 = new ModelRenderer(this, 0, 36);
		charcoal3.addBox(-3F, -1F, -2F, 2, 2, 4);
		charcoal3.setRotationPoint(0F, 17F, 0F);
		charcoal3.setTextureSize(64, 64);
		charcoal3.mirror = true;
		setRotation(charcoal3, 0F, -0.1745329F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(0.0625F);
		base.render(0.0625F);
		back.render(0.0625F);
		side.render(0.0625F);
		side2.render(0.0625F);
		front1.render(0.0625F);
		front2.render(0.0625F);
		front3.render(0.0625F);
		top1.render(0.0625F);
		top2.render(0.0625F);
		top3.render(0.0625F);
		top4.render(0.0625F);
		inner.render(0.0625F);
		net.render(0.0625F);
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
