package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelOscillator extends DCTileModelBase {
	// fields
	public ModelRenderer bottom;
	public ModelRenderer f;
	public ModelRenderer b;
	public ModelRenderer r;
	public ModelRenderer l;
	public ModelRenderer shaft;
	public ModelRenderer gear1;
	public ModelRenderer gear2;
	public ModelRenderer gear3;
	public ModelRenderer gear41;
	public ModelRenderer gear42;
	public ModelRenderer gear43;
	public ModelRenderer gear44;
	public ModelRenderer gear45;
	public ModelRenderer gear46;
	public ModelRenderer gear47;

	public ModelOscillator() {
		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-7F, -8F, -7F, 14, 6, 14, 0F);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.rotateAngleX = 0F;
		bottom.rotateAngleY = 0F;
		bottom.rotateAngleZ = 0F;
		bottom.mirror = false;
		f = new ModelRenderer(this, 60, 0);
		f.addBox(-8F, -8F, -8F, 16, 14, 1, 0F);
		f.setRotationPoint(0F, 0F, 0F);
		f.rotateAngleX = 0F;
		f.rotateAngleY = 0F;
		f.rotateAngleZ = 0F;
		f.mirror = false;
		b = new ModelRenderer(this, 60, 0);
		b.addBox(-8F, -8F, -8F, 16, 14, 1, 0F);
		b.setRotationPoint(0F, 0F, 0F);
		b.rotateAngleX = 0F;
		b.rotateAngleY = 3.141593F;
		b.rotateAngleZ = 0F;
		b.mirror = false;
		r = new ModelRenderer(this, 96, 0);
		r.addBox(-8F, -8F, -7F, 1, 14, 14, 0F);
		r.setRotationPoint(0F, 0F, 0F);
		r.rotateAngleX = 0F;
		r.rotateAngleY = 0F;
		r.rotateAngleZ = 0F;
		r.mirror = false;
		l = new ModelRenderer(this, 96, 0);
		l.addBox(-8F, -8F, -7F, 1, 14, 14, 0F);
		l.setRotationPoint(0F, 0F, 0F);
		l.rotateAngleX = 0F;
		l.rotateAngleY = 3.141593F;
		l.rotateAngleZ = 0F;
		l.mirror = false;
		shaft = new ModelRenderer(this, 0, 24);
		shaft.addBox(-1.5F, -2F, -1.5F, 3, 8, 3, 0F);
		shaft.setRotationPoint(0F, 0F, 0F);
		shaft.rotateAngleX = 0F;
		shaft.rotateAngleY = 0F;
		shaft.rotateAngleZ = 0F;
		shaft.mirror = false;
		gear1 = new ModelRenderer(this, 15, 24);
		gear1.addBox(-4.5F, 0F, -4.5F, 9, 1, 9, 0F);
		gear1.setRotationPoint(0F, 0F, 0F);
		gear1.rotateAngleX = 0F;
		gear1.rotateAngleY = 0F;
		gear1.rotateAngleZ = 0F;
		gear1.mirror = false;
		gear2 = new ModelRenderer(this, 54, 35);
		gear2.addBox(-2.5F, 2F, -2.5F, 5, 1, 5, 0F);
		gear2.setRotationPoint(-2F, 0F, -3F);
		gear2.rotateAngleX = 0F;
		gear2.rotateAngleY = 0.7807508F;
		gear2.rotateAngleZ = 0F;
		gear2.mirror = false;
		gear3 = new ModelRenderer(this, 77, 34);
		gear3.addBox(-3.5F, 4F, -3.5F, 7, 1, 7, 0F);
		gear3.setRotationPoint(2F, 0F, 1F);
		gear3.rotateAngleX = 0F;
		gear3.rotateAngleY = 0F;
		gear3.rotateAngleZ = 0F;
		gear3.mirror = false;
		gear41 = new ModelRenderer(this, 15, 36);
		gear41.addBox(-4.5F, 6F, -4.5F, 9, 1, 9, 0F);
		gear41.setRotationPoint(0F, 0F, 0F);
		gear41.rotateAngleX = 0F;
		gear41.rotateAngleY = 0F;
		gear41.rotateAngleZ = 0F;
		gear41.mirror = false;
		gear42 = new ModelRenderer(this, 28, 49);
		gear42.addBox(-2.5F, 6F, -5.5F, 5, 1, 1, 0F);
		gear42.setRotationPoint(0F, 0F, 0F);
		gear42.rotateAngleX = 0F;
		gear42.rotateAngleY = 0F;
		gear42.rotateAngleZ = 0F;
		gear42.mirror = false;
		gear43 = new ModelRenderer(this, 14, 49);
		gear43.addBox(-2.5F, 6F, -5.5F, 5, 1, 1, 0F);
		gear43.setRotationPoint(0F, 0F, 0F);
		gear43.rotateAngleX = 0F;
		gear43.rotateAngleY = 1.570796F;
		gear43.rotateAngleZ = 0F;
		gear43.mirror = false;
		gear44 = new ModelRenderer(this, 14, 49);
		gear44.addBox(-2.5F, 6F, -5.5F, 5, 1, 1, 0F);
		gear44.setRotationPoint(0F, 0F, 0F);
		gear44.rotateAngleX = 0F;
		gear44.rotateAngleY = 3.141593F;
		gear44.rotateAngleZ = 0F;
		gear44.mirror = false;
		gear45 = new ModelRenderer(this, 14, 49);
		gear45.addBox(-2.5F, 6F, -5.5F, 5, 1, 1, 0F);
		gear45.setRotationPoint(0F, 0F, 0F);
		gear45.rotateAngleX = 0F;
		gear45.rotateAngleY = -1.570796F;
		gear45.rotateAngleZ = 0F;
		gear45.mirror = false;
		gear46 = new ModelRenderer(this, 14, 52);
		gear46.addBox(-0.5F, 6F, -6.5F, 1, 1, 1, 0F);
		gear46.setRotationPoint(0F, 0F, 0F);
		gear46.rotateAngleX = 0F;
		gear46.rotateAngleY = 0F;
		gear46.rotateAngleZ = 0F;
		gear46.mirror = false;
		gear47 = new ModelRenderer(this, 14, 52);
		gear47.addBox(-0.5F, 6F, -8F, 1, 1, 1, 0F);
		gear47.setRotationPoint(0F, 0F, 0F);
		gear47.rotateAngleX = 0F;
		gear47.rotateAngleY = 0F;
		gear47.rotateAngleZ = 0F;
		gear47.mirror = false;
	}

	@Override
	public void render(float f1, float speed, float tick) {
		setRotationAngles(f1, speed, tick);
		bottom.render(0.0625F);
		f.render(0.0625F);
		b.render(0.0625F);
		r.render(0.0625F);
		l.render(0.0625F);
		shaft.render(0.0625F);
		gear1.render(0.0625F);
		gear2.render(0.0625F);
		gear3.render(0.0625F);
		gear41.render(0.0625F);
		gear42.render(0.0625F);
		gear43.render(0.0625F);
		gear44.render(0.0625F);
		gear45.render(0.0625F);
		gear46.render(0.0625F);
		gear47.render(0.0625F);
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
		float f3 = (-speed / 16F) * 3.141593F * 2;

		shaft.rotateAngleY = f2;
		gear1.rotateAngleY = f2;
		gear2.rotateAngleY = f2 * 4;
		gear3.rotateAngleY = f2 * 2;
		gear41.rotateAngleY = f3;
		gear42.rotateAngleY = f3;
		gear43.rotateAngleY = f3 + 1.570796F;
		gear44.rotateAngleY = f3 + 3.141593F;
		gear45.rotateAngleY = f3 - 1.570796F;
		gear46.rotateAngleY = f3;

	}

}
