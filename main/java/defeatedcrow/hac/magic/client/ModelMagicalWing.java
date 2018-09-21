package defeatedcrow.hac.magic.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMagicalWing extends ModelBase {

	ModelRenderer left1;
	ModelRenderer left2;
	ModelRenderer left3;
	ModelRenderer left4;
	ModelRenderer right1;
	ModelRenderer right2;
	ModelRenderer right3;
	ModelRenderer right4;
	ModelRenderer tail1;
	ModelRenderer tail2;

	public ModelMagicalWing() {
		super();

		textureWidth = 64;
		textureHeight = 32;

		left1 = new ModelRenderer(this, 0, 0);
		left1.addBox(0F, -2F, 3F, 0, 6, 8);
		left1.setRotationPoint(0F, 4F, 4F);
		left1.setTextureSize(64, 32);
		left1.mirror = true;
		setRotation(left1, 0.2094395F, 1.396263F, 0F);
		left2 = new ModelRenderer(this, 17, 0);
		left2.addBox(0F, 1.5F, -1F, 0, 8, 2);
		left2.setRotationPoint(10F, -2F, 6.5F);
		left2.setTextureSize(64, 32);
		left2.mirror = true;
		setRotation(left2, 0.2094395F, 1.396263F, 0F);
		left3 = new ModelRenderer(this, 22, 0);
		left3.addBox(0F, 1.5F, -1F, 0, 10, 2);
		left3.setRotationPoint(10F, -2F, 7F);
		left3.setTextureSize(64, 32);
		left3.mirror = true;
		setRotation(left3, 0.418879F, 1.396263F, 0F);
		left4 = new ModelRenderer(this, 27, 0);
		left4.addBox(0F, 1.5F, -1F, 0, 12, 2);
		left4.setRotationPoint(10F, -2F, 7.5F);
		left4.setTextureSize(64, 32);
		left4.mirror = true;
		setRotation(left4, 0.6283185F, 1.396263F, 0F);
		right1 = new ModelRenderer(this, 0, 0);
		right1.addBox(0F, -2F, 3F, 0, 6, 8);
		right1.setRotationPoint(0F, 4F, 4F);
		right1.setTextureSize(64, 32);
		right1.mirror = true;
		setRotation(right1, 0.2094395F, -1.396263F, 0F);
		right2 = new ModelRenderer(this, 17, 0);
		right2.addBox(0F, 1.5F, -1F, 0, 8, 2);
		right2.setRotationPoint(-10F, -2F, 6.5F);
		right2.setTextureSize(64, 32);
		right2.mirror = true;
		setRotation(right2, 0.2094395F, -1.396263F, 0F);
		right3 = new ModelRenderer(this, 22, 0);
		right3.addBox(0F, 1.5F, -1F, 0, 10, 2);
		right3.setRotationPoint(-10F, -2F, 7F);
		right3.setTextureSize(64, 32);
		right3.mirror = true;
		setRotation(right3, 0.418879F, -1.396263F, 0F);
		right4 = new ModelRenderer(this, 27, 0);
		right4.addBox(0F, 1.5F, -1F, 0, 12, 2);
		right4.setRotationPoint(-10F, -2F, 7.5F);
		right4.setTextureSize(64, 32);
		right4.mirror = true;
		setRotation(right4, 0.6283185F, -1.396263F, 0F);
		tail1 = new ModelRenderer(this, 0, 16);
		tail1.addBox(-3F, 0F, 0F, 6, 7, 0);
		tail1.setRotationPoint(0F, 10F, 4F);
		tail1.setTextureSize(64, 32);
		tail1.mirror = true;
		setRotation(tail1, 0.6283185F, 0F, 0F);
		tail2 = new ModelRenderer(this, 0, 24);
		tail2.addBox(-6F, 0F, 0F, 12, 7, 0);
		tail2.setRotationPoint(0F, 15F, 8F);
		tail2.setTextureSize(64, 32);
		tail2.mirror = true;
		setRotation(tail2, 0.6283185F, 0F, 0F);
	}

	public void renderWing(float scale, boolean open, float angle) {
		renderWing(null, scale, open, angle);
	}

	public void renderWing(Entity entity, float scale, boolean open, float angle) {
		this.setRotationAngles(scale, entity, open, angle);
		left1.render(scale);
		left2.render(scale);
		left3.render(scale);
		left4.render(scale);
		right1.render(scale);
		right2.render(scale);
		right3.render(scale);
		right4.render(scale);
	}

	public void renderTail(float scale, boolean open, float angle) {
		renderTail(null, scale, open, angle);
	}

	public void renderTail(Entity entity, float scale, boolean open, float angle) {
		this.setRotationAngles(scale, entity, open, angle);
		left1.render(scale);
		right1.render(scale);
		tail1.render(scale);
		tail2.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float scale, Entity entity, boolean open, float angle) {
		super.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale, entity);

		float wingF = (angle + 10.0F) / 18.0F;
		left1.rotateAngleY = wingF * 1.396263F;
		left2.rotateAngleY = wingF * 1.396263F;
		left3.rotateAngleY = wingF * 1.396263F;
		left4.rotateAngleY = wingF * 1.396263F;
		right1.rotateAngleY = wingF * -1.396263F;
		right2.rotateAngleY = wingF * -1.396263F;
		right3.rotateAngleY = wingF * -1.396263F;
		right4.rotateAngleY = wingF * -1.396263F;

		double w1 = wingF * 0.25F * Math.PI;
		left2.rotationPointX = (float) Math.sin(w1) * 12.0F;
		left3.rotationPointX = (float) Math.sin(w1) * 12.0F;
		left4.rotationPointX = (float) Math.sin(w1) * 12.0F;
		left2.rotationPointZ = -2.0F + (float) Math.cos(w1) * 12.0F;
		left3.rotationPointZ = -1.5F + (float) Math.cos(w1) * 12.0F;
		left4.rotationPointZ = -1.0F + (float) Math.cos(w1) * 12.0F;

		right2.rotationPointX = (float) Math.sin(w1) * -12.0F;
		right3.rotationPointX = (float) Math.sin(w1) * -12.0F;
		right4.rotationPointX = (float) Math.sin(w1) * -12.0F;
		right2.rotationPointZ = -2.0F + (float) Math.cos(w1) * 12.0F;
		right3.rotationPointZ = -1.5F + (float) Math.cos(w1) * 12.0F;
		right4.rotationPointZ = -1.0F + (float) Math.cos(w1) * 12.0F;

		if (open) {
			left2.rotateAngleX = 2.0F * 0.2094395F;
			left3.rotateAngleX = 4.0F * 0.2094395F;
			left4.rotateAngleX = 6.0F * 0.2094395F;
			right2.rotateAngleX = 2.0F * 0.2094395F;
			right3.rotateAngleX = 4.0F * 0.2094395F;
			right4.rotateAngleX = 6.0F * 0.2094395F;
		} else {
			left2.rotateAngleX = 1.0F * 0.2094395F;
			left3.rotateAngleX = 2.0F * 0.2094395F;
			left4.rotateAngleX = 3.0F * 0.2094395F;
			right2.rotateAngleX = 1.0F * 0.2094395F;
			right3.rotateAngleX = 2.0F * 0.2094395F;
			right4.rotateAngleX = 3.0F * 0.2094395F;
		}

		float wingO = (angle / 8.0F);
		double w2 = (((wingO * 60.0F) + 30.0F) / 360.0D) * Math.PI;
		tail1.rotateAngleX = (float) w2;
		tail2.rotateAngleX = 1.5F * (float) w2;
		tail2.rotationPointY = 10.0F + (float) Math.cos(w2) * 7.0F;
		tail2.rotationPointZ = 4.0F + (float) Math.sin(w2) * 7.0F;

	}

}
