package defeatedcrow.hac.food.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBlockPotteryPot extends DCTileModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer front;
	ModelRenderer back;
	ModelRenderer right;
	ModelRenderer left;
	ModelRenderer top1;
	ModelRenderer top2;
	ModelRenderer top3;
	ModelRenderer top4;
	ModelRenderer cap;

	public ModelBlockPotteryPot() {
		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-6F, 7F, -6F, 12, 1, 12);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		front = new ModelRenderer(this, 0, 14);
		front.addBox(-7F, -5F, -7F, 14, 12, 1);
		front.setRotationPoint(0F, 0F, 0F);
		front.setTextureSize(64, 64);
		front.mirror = true;
		setRotation(front, 0F, 0F, 0F);
		back = new ModelRenderer(this, 0, 14);
		back.addBox(-7F, -5F, 6F, 14, 12, 1);
		back.setRotationPoint(0F, 0F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		right = new ModelRenderer(this, 32, 14);
		right.addBox(-7F, -5F, -6F, 1, 12, 12);
		right.setRotationPoint(0F, 0F, 0F);
		right.setTextureSize(64, 64);
		right.mirror = true;
		setRotation(right, 0F, 0F, 0F);
		left = new ModelRenderer(this, 32, 14);
		left.addBox(6F, -5F, -6F, 1, 12, 12);
		left.setRotationPoint(0F, 0F, 0F);
		left.setTextureSize(64, 64);
		left.mirror = true;
		setRotation(left, 0F, 0F, 0F);
		top1 = new ModelRenderer(this, 0, 28);
		top1.addBox(-6F, -7F, -6F, 12, 2, 2);
		top1.setRotationPoint(0F, 0F, 0F);
		top1.setTextureSize(64, 64);
		top1.mirror = true;
		setRotation(top1, 0F, 0F, 0F);
		top2 = new ModelRenderer(this, 0, 28);
		top2.addBox(-6F, -7F, 4F, 12, 2, 2);
		top2.setRotationPoint(0F, 0F, 0F);
		top2.setTextureSize(64, 64);
		top2.mirror = true;
		setRotation(top2, 0F, 0F, 0F);
		top3 = new ModelRenderer(this, 0, 34);
		top3.addBox(-4F, -7F, -6F, 8, 2, 2);
		top3.setRotationPoint(0F, 0F, 0F);
		top3.setTextureSize(64, 64);
		top3.mirror = true;
		setRotation(top3, 0F, 1.570796F, 0F);
		top4 = new ModelRenderer(this, 0, 34);
		top4.addBox(-4F, -7F, 4F, 8, 2, 2);
		top4.setRotationPoint(0F, 0F, 0F);
		top4.setTextureSize(64, 64);
		top4.mirror = true;
		setRotation(top4, 0F, 1.570796F, 0F);
		cap = new ModelRenderer(this, 0, 40);
		cap.addBox(-5F, -8F, -5F, 10, 3, 10);
		cap.setRotationPoint(0F, 0F, 0F);
		cap.setTextureSize(64, 64);
		cap.mirror = true;
		setRotation(cap, 0F, 0F, 0F);
	}

	@Override
	public void render(float f) {
		setRotationAngles(f);
		bottom.render(0.0625F);
		front.render(0.0625F);
		back.render(0.0625F);
		right.render(0.0625F);
		left.render(0.0625F);
		top1.render(0.0625F);
		top2.render(0.0625F);
		top3.render(0.0625F);
		top4.render(0.0625F);
	}

	public void renderCap(float f) {
		cap.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
