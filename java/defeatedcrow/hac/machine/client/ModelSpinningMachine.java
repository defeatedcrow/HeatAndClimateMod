package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSpinningMachine extends DCTileModelBase {

	ModelRenderer bottom1;
	ModelRenderer bottom2;
	ModelRenderer bottom3;
	ModelRenderer left;
	ModelRenderer right;
	ModelRenderer middle;
	ModelRenderer bar1;
	ModelRenderer bar2;
	ModelRenderer bar3;
	ModelRenderer cylinder1;
	ModelRenderer cylinder2;
	ModelRenderer cylinder3;
	ModelRenderer bobbin1;
	ModelRenderer bobbin2;
	ModelRenderer bobbin3;
	ModelRenderer lever1;
	ModelRenderer lever2;
	ModelRenderer lever3;
	ModelRenderer gear1;
	ModelRenderer gear2;
	ModelRenderer panel;

	public ModelSpinningMachine() {

		textureWidth = 64;
		textureHeight = 32;

		bottom1 = new ModelRenderer(this, 0, 0);
		bottom1.addBox(-7F, 7F, -4F, 12, 1, 8);
		bottom1.setRotationPoint(0F, 0F, 0F);
		bottom1.setTextureSize(64, 32);
		bottom1.mirror = true;
		setRotation(bottom1, 0F, 0F, 0F);
		bottom2 = new ModelRenderer(this, 0, 0);
		bottom2.addBox(-7F, 5F, -3F, 12, 1, 4);
		bottom2.setRotationPoint(0F, 0F, 0F);
		bottom2.setTextureSize(64, 32);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		bottom3 = new ModelRenderer(this, 0, 0);
		bottom3.addBox(-7F, 2F, -3F, 12, 1, 4);
		bottom3.setRotationPoint(0F, 0F, 0F);
		bottom3.setTextureSize(64, 32);
		bottom3.mirror = true;
		setRotation(bottom3, 0F, 0F, 0F);
		left = new ModelRenderer(this, 0, 8);
		left.addBox(-8F, -8F, -4F, 1, 16, 8);
		left.setRotationPoint(0F, 0F, 0F);
		left.setTextureSize(64, 32);
		left.mirror = true;
		setRotation(left, 0F, 0F, 0F);
		right = new ModelRenderer(this, 0, 8);
		right.addBox(5F, -8F, -4F, 1, 16, 8);
		right.setRotationPoint(0F, 0F, 0F);
		right.setTextureSize(64, 32);
		right.mirror = true;
		setRotation(right, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 0, 0);
		middle.addBox(-7F, -2F, 1F, 12, 2, 2);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.setTextureSize(64, 32);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		bar1 = new ModelRenderer(this, 0, 0);
		bar1.addBox(-7F, -5F, -2F, 12, 1, 1);
		bar1.setRotationPoint(0F, 0F, 0F);
		bar1.setTextureSize(64, 32);
		bar1.mirror = true;
		setRotation(bar1, 0F, 0F, 0F);
		bar2 = new ModelRenderer(this, 0, 0);
		bar2.addBox(-7F, -5F, 1F, 12, 1, 1);
		bar2.setRotationPoint(0F, 0F, 0F);
		bar2.setTextureSize(64, 32);
		bar2.mirror = true;
		setRotation(bar2, 0F, 0F, 0F);
		bar3 = new ModelRenderer(this, 38, 0);
		bar3.addBox(-7F, -0.5F, -0.5F, 12, 1, 1);
		bar3.setRotationPoint(0F, -6F, 0F);
		bar3.setTextureSize(64, 32);
		bar3.mirror = true;
		setRotation(bar3, 0F, 0F, 0F);
		cylinder1 = new ModelRenderer(this, 52, 3);
		cylinder1.addBox(-1F, 0F, -1F, 2, 5, 2);
		cylinder1.setRotationPoint(-4F, 0F, -1F);
		cylinder1.setTextureSize(64, 32);
		cylinder1.mirror = true;
		setRotation(cylinder1, 0F, 0F, 0F);
		cylinder2 = new ModelRenderer(this, 52, 3);
		cylinder2.addBox(-1F, 0F, -1F, 2, 5, 2);
		cylinder2.setRotationPoint(-1F, 0F, -1F);
		cylinder2.setTextureSize(64, 32);
		cylinder2.mirror = true;
		setRotation(cylinder2, 0F, 0F, 0F);
		cylinder3 = new ModelRenderer(this, 52, 3);
		cylinder3.addBox(-1F, 0F, -1F, 2, 5, 2);
		cylinder3.setRotationPoint(2F, 0F, -1F);
		cylinder3.setTextureSize(64, 32);
		cylinder3.mirror = true;
		setRotation(cylinder3, 0F, 0F, 0F);
		bobbin1 = new ModelRenderer(this, 42, 3);
		bobbin1.addBox(-1F, -1F, -1F, 2, 2, 2);
		bobbin1.setRotationPoint(-4F, -6F, 0F);
		bobbin1.setTextureSize(64, 32);
		bobbin1.mirror = true;
		setRotation(bobbin1, 0F, 0F, 0F);
		bobbin2 = new ModelRenderer(this, 42, 3);
		bobbin2.addBox(-1F, -1F, -1F, 2, 2, 2);
		bobbin2.setRotationPoint(-1F, -6F, 0F);
		bobbin2.setTextureSize(64, 32);
		bobbin2.mirror = true;
		setRotation(bobbin2, 0F, 0F, 0F);
		bobbin3 = new ModelRenderer(this, 42, 3);
		bobbin3.addBox(-1F, -1F, -1F, 2, 2, 2);
		bobbin3.setRotationPoint(2F, -6F, 0F);
		bobbin3.setTextureSize(64, 32);
		bobbin3.mirror = true;
		setRotation(bobbin3, 0F, 0F, 0F);
		lever1 = new ModelRenderer(this, 50, 12);
		lever1.addBox(-5F, 6F, -5F, 2, 1, 4);
		lever1.setRotationPoint(0F, 0F, 0F);
		lever1.setTextureSize(64, 32);
		lever1.mirror = true;
		setRotation(lever1, 0F, 0F, 0F);
		lever2 = new ModelRenderer(this, 50, 12);
		lever2.addBox(-2F, 6F, -5F, 2, 1, 4);
		lever2.setRotationPoint(0F, 0F, 0F);
		lever2.setTextureSize(64, 32);
		lever2.mirror = true;
		setRotation(lever2, 0F, 0F, 0F);
		lever3 = new ModelRenderer(this, 50, 12);
		lever3.addBox(1F, 6F, -5F, 2, 1, 4);
		lever3.setRotationPoint(0F, 0F, 0F);
		lever3.setTextureSize(64, 32);
		lever3.mirror = true;
		setRotation(lever3, 0F, 0F, 0F);
		gear1 = new ModelRenderer(this, 20, 10);
		gear1.addBox(6F, -2.5F, -2.5F, 1, 5, 5);
		gear1.setRotationPoint(0F, -6F, 0F);
		gear1.setTextureSize(64, 32);
		gear1.mirror = true;
		setRotation(gear1, 0F, 0F, 0F);
		gear2 = new ModelRenderer(this, 20, 10);
		gear2.addBox(6F, -2.5F, -2.5F, 1, 5, 5);
		gear2.setRotationPoint(0F, -1F, 0F);
		gear2.setTextureSize(64, 32);
		gear2.mirror = true;
		setRotation(gear2, 0F, 0F, 0F);
		panel = new ModelRenderer(this, 20, 20);
		panel.addBox(7F, -3F, -3F, 1, 6, 6);
		panel.setRotationPoint(0F, 0F, 0F);
		panel.setTextureSize(64, 32);
		panel.mirror = true;
		setRotation(panel, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		bottom1.render(0.0625F);
		bottom2.render(0.0625F);
		bottom3.render(0.0625F);
		middle.render(0.0625F);
		bar1.render(0.0625F);
		bar2.render(0.0625F);
		bar3.render(0.0625F);
		left.render(0.0625F);
		right.render(0.0625F);
		bobbin1.render(0.0625F);
		bobbin2.render(0.0625F);
		bobbin3.render(0.0625F);
		cylinder1.render(0.0625F);
		cylinder2.render(0.0625F);
		cylinder3.render(0.0625F);
		lever1.render(0.0625F);
		lever2.render(0.0625F);
		lever3.render(0.0625F);
		gear1.render(0.0625F);
		gear2.render(0.0625F);
		panel.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		float rot = f;
		float f2 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		bar3.rotateAngleX = f2;
		bobbin1.rotateAngleX = f2;
		bobbin2.rotateAngleX = f2;
		bobbin3.rotateAngleX = f2;
		cylinder1.rotateAngleY = f2;
		cylinder2.rotateAngleY = f2;
		cylinder3.rotateAngleY = f2;
		gear1.rotateAngleX = f2;
		gear2.rotateAngleX = -f2;
	}

}
