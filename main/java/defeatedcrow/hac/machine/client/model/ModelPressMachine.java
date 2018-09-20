package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPressMachine extends DCTileModelBase {

	ModelRenderer bottom;
	ModelRenderer bottom1;
	ModelRenderer bottom2;
	ModelRenderer bottom3;
	ModelRenderer bottom4;
	ModelRenderer mould1;
	ModelRenderer mould2;
	ModelRenderer mould3;
	ModelRenderer mould4;
	ModelRenderer mould5;
	ModelRenderer mould6;
	ModelRenderer hummer1;
	ModelRenderer hummer2;
	ModelRenderer hummer3;
	ModelRenderer hummer4;
	ModelRenderer hummer5;
	ModelRenderer bar1;
	ModelRenderer bar2;
	ModelRenderer bar3;
	ModelRenderer bar4;
	ModelRenderer connect;
	ModelRenderer top;

	public ModelPressMachine() {

		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-8F, 6F, -8F, 16, 2, 16);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		bottom1 = new ModelRenderer(this, 26, 42);
		bottom1.addBox(-8F, 4F, -8F, 16, 2, 3);
		bottom1.setRotationPoint(0F, 0F, 0F);
		bottom1.setTextureSize(64, 32);
		bottom1.mirror = true;
		setRotation(bottom1, 0F, 0F, 0F);
		bottom2 = new ModelRenderer(this, 26, 42);
		bottom2.addBox(-8F, 4F, 5F, 16, 2, 3);
		bottom2.setRotationPoint(0F, 0F, 0F);
		bottom2.setTextureSize(64, 32);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		bottom3 = new ModelRenderer(this, 26, 48);
		bottom3.addBox(5F, 4F, -5F, 3, 2, 10);
		bottom3.setRotationPoint(0F, 0F, 0F);
		bottom3.setTextureSize(64, 32);
		bottom3.mirror = true;
		setRotation(bottom3, 0F, 0F, 0F);
		bottom4 = new ModelRenderer(this, 26, 48);
		bottom4.addBox(-8F, 4F, -5F, 3, 2, 10);
		bottom4.setRotationPoint(0F, 0F, 0F);
		bottom4.setTextureSize(64, 32);
		bottom4.mirror = true;
		setRotation(bottom4, 0F, 0F, 0F);
		mould1 = new ModelRenderer(this, 66, 24);
		mould1.addBox(-5F, 5F, -5F, 10, 1, 1);
		mould1.setRotationPoint(0F, 0F, 0F);
		mould1.setTextureSize(64, 32);
		mould1.mirror = true;
		setRotation(mould1, 0F, 0F, 0F);
		mould2 = new ModelRenderer(this, 66, 24);
		mould2.addBox(-5F, 5F, 4F, 10, 1, 1);
		mould2.setRotationPoint(0F, 0F, 0F);
		mould2.setTextureSize(64, 32);
		mould2.mirror = true;
		setRotation(mould2, 0F, 0F, 0F);
		mould3 = new ModelRenderer(this, 66, 24);
		mould3.addBox(-5F, 5F, -0.5F, 10, 1, 1);
		mould3.setRotationPoint(0F, 0F, 0F);
		mould3.setTextureSize(64, 32);
		mould3.mirror = true;
		setRotation(mould3, 0F, 0F, 0F);
		mould4 = new ModelRenderer(this, 89, 24);
		mould4.addBox(4F, 5F, -4F, 1, 1, 8);
		mould4.setRotationPoint(0F, 0F, 0F);
		mould4.setTextureSize(64, 32);
		mould4.mirror = true;
		setRotation(mould4, 0F, 0F, 0F);
		mould5 = new ModelRenderer(this, 89, 24);
		mould5.addBox(-5F, 5F, -4F, 1, 1, 8);
		mould5.setRotationPoint(0F, 0F, 0F);
		mould5.setTextureSize(64, 32);
		mould5.mirror = true;
		setRotation(mould5, 0F, 0F, 0F);
		mould6 = new ModelRenderer(this, 89, 24);
		mould6.addBox(-0.5F, 5F, -4F, 1, 1, 8);
		mould6.setRotationPoint(0F, 0F, 0F);
		mould6.setTextureSize(64, 32);
		mould6.mirror = true;
		setRotation(mould6, 0F, 0F, 0F);
		hummer1 = new ModelRenderer(this, 66, 16);
		hummer1.addBox(0.75F, 4F, 0.75F, 3, 2, 3);
		hummer1.setRotationPoint(0F, 0F, 0F);
		hummer1.setTextureSize(64, 32);
		hummer1.mirror = true;
		setRotation(hummer1, 0F, 0F, 0F);
		hummer2 = new ModelRenderer(this, 66, 16);
		hummer2.addBox(-3.75F, 4F, 0.75F, 3, 2, 3);
		hummer2.setRotationPoint(0F, 0F, 0F);
		hummer2.setTextureSize(64, 32);
		hummer2.mirror = true;
		setRotation(hummer2, 0F, 0F, 0F);
		hummer3 = new ModelRenderer(this, 66, 16);
		hummer3.addBox(0.75F, 4F, -3.75F, 3, 2, 3);
		hummer3.setRotationPoint(0F, 0F, 0F);
		hummer3.setTextureSize(64, 32);
		hummer3.mirror = true;
		setRotation(hummer3, 0F, 0F, 0F);
		hummer4 = new ModelRenderer(this, 66, 16);
		hummer4.addBox(-3.75F, 4F, -3.75F, 3, 2, 3);
		hummer4.setRotationPoint(0F, 0F, 0F);
		hummer4.setTextureSize(64, 32);
		hummer4.mirror = true;
		setRotation(hummer4, 0F, 0F, 0F);
		hummer5 = new ModelRenderer(this, 66, 0);
		hummer5.addBox(-5F, -1F, -5F, 10, 5, 10);
		hummer5.setRotationPoint(0F, 0F, 0F);
		hummer5.setTextureSize(128, 64);
		hummer5.mirror = true;
		setRotation(hummer5, 0F, 0F, 0F);
		bar1 = new ModelRenderer(this, 0, 51);
		bar1.addBox(-8F, -1F, -8F, 2, 5, 2);
		bar1.setRotationPoint(0F, 0F, 0F);
		bar1.setTextureSize(128, 64);
		bar1.mirror = true;
		setRotation(bar1, 0F, 0F, 0F);
		bar2 = new ModelRenderer(this, 0, 51);
		bar2.addBox(6F, -1F, -8F, 2, 5, 2);
		bar2.setRotationPoint(0F, 0F, 0F);
		bar2.setTextureSize(128, 64);
		bar2.mirror = true;
		setRotation(bar2, 0F, 0F, 0F);
		bar3 = new ModelRenderer(this, 0, 50);
		bar3.addBox(-8F, -1F, 6F, 2, 5, 2);
		bar3.setRotationPoint(0F, 0F, 0F);
		bar3.setTextureSize(128, 64);
		bar3.mirror = true;
		setRotation(bar3, 0F, 0F, 0F);
		bar4 = new ModelRenderer(this, 0, 50);
		bar4.addBox(6F, -1F, 6F, 2, 5, 2);
		bar4.setRotationPoint(0F, 0F, 0F);
		bar4.setTextureSize(128, 64);
		bar4.mirror = true;
		setRotation(bar4, 0F, 0F, 0F);
		connect = new ModelRenderer(this, 0, 42);
		connect.addBox(-3F, -8F, -3F, 6, 1, 6);
		connect.setRotationPoint(0F, 0F, 0F);
		connect.setTextureSize(128, 64);
		connect.mirror = true;
		setRotation(connect, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 18);
		top.addBox(-8F, -7F, -8F, 16, 6, 16);
		top.setRotationPoint(0F, 0F, 0F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		render(null, f, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		bottom.render(scale);
		bottom1.render(scale);
		bottom2.render(scale);
		bottom3.render(scale);
		bottom4.render(scale);
		mould1.render(scale);
		mould2.render(scale);
		mould3.render(scale);
		mould4.render(scale);
		mould5.render(scale);
		mould6.render(scale);
		hummer1.render(scale);
		hummer2.render(scale);
		hummer3.render(scale);
		hummer4.render(scale);
		hummer5.render(scale);
		bar1.render(scale);
		bar2.render(scale);
		bar3.render(scale);
		bar4.render(scale);
		connect.render(scale);
		top.render(scale);
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
		float c1 = (float) Math.sin(f2);
		float f3 = c1 * 2.4F;
		hummer1.rotationPointY = -2.4F + f3;
		hummer2.rotationPointY = -2.0F + f3;
		hummer3.rotationPointY = -2.0F + f3;
		hummer4.rotationPointY = -2.0F + f3;
		hummer5.rotationPointY = -2.0F + f3;
	}

}
