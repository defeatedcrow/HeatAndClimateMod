package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHopperF extends DCTileModelBase {
	// fields
	public ModelRenderer front;
	public ModelRenderer back;
	public ModelRenderer right;
	public ModelRenderer left;
	public ModelRenderer bottom1;
	public ModelRenderer bottom2;
	public ModelRenderer bottom3;
	public ModelRenderer bottom4;

	public ModelHopperF() {
		textureWidth = 64;
		textureHeight = 64;

		this.front = new ModelRenderer(this, 0, 0);
		this.front.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.front.addBox(-8.0F, -8.0F, -8.0F, 16, 8, 1);
		this.back = new ModelRenderer(this, 0, 0);
		this.back.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.back.addBox(-8.0F, -8.0F, 7.0F, 16, 8, 1);
		this.right = new ModelRenderer(this, 0, 10);
		this.right.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right.addBox(7.0F, -8.0F, -7.0F, 1, 8, 14);
		this.left = new ModelRenderer(this, 0, 10);
		this.left.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left.addBox(-8.0F, -8.0F, -7.0F, 1, 8, 14);
		this.bottom1 = new ModelRenderer(this, 46, 48);
		this.bottom1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bottom1.addBox(-2.0F, 3.0F, -2.0F, 4, 5, 4);
		this.bottom2 = new ModelRenderer(this, 42, 38);
		this.bottom2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bottom2.addBox(-3.0F, 2.0F, -3.0F, 6, 1, 6);
		this.bottom3 = new ModelRenderer(this, 0, 34);
		this.bottom3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bottom3.addBox(-5.0F, 1.0F, -5.0F, 10, 1, 10);
		this.bottom4 = new ModelRenderer(this, 0, 46);
		this.bottom4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bottom4.addBox(-7.0F, -1.0F, -7.0F, 14, 2, 14);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		bottom1.render(0.0625F);
		bottom2.render(0.0625F);
		bottom3.render(0.0625F);
		bottom4.render(0.0625F);
		front.render(0.0625F);
		back.render(0.0625F);
		left.render(0.0625F);
		right.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {}

}
