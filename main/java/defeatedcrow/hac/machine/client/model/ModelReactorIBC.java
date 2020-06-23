package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelReactorIBC extends DCTileModelBase {

	ModelRenderer bottom;
	ModelRenderer body;
	ModelRenderer cap;
	ModelRenderer cage;
	ModelRenderer bar1;
	ModelRenderer bar2;
	ModelRenderer bar3;
	ModelRenderer bar4;
	ModelRenderer motor1;
	ModelRenderer motor2;
	ModelRenderer motor3;
	ModelRenderer panel;

	public ModelReactorIBC() {
		textureWidth = 64;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-8F, 7F, -8F, 16, 1, 16);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-7.5F, -6.5F, -7.5F, 15, 13, 15);
		body.setRotationPoint(0F, 16F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		cap = new ModelRenderer(this, 0, 0);
		cap.addBox(-2F, -8.5F, -2F, 4, 1, 4);
		cap.setRotationPoint(0F, 16F, 0F);
		cap.setTextureSize(64, 32);
		cap.mirror = true;
		setRotation(cap, 0F, 0F, 0F);
		cage = new ModelRenderer(this, 0, 0);
		cage.addBox(-8F, -7F, -8F, 16, 14, 16);
		cage.setRotationPoint(0F, 16F, 0F);
		cage.setTextureSize(64, 32);
		cage.mirror = true;
		setRotation(cage, 0F, 0F, 0F);

		bar1 = new ModelRenderer(this, 0, 18);
		bar1.addBox(-8F, -8F, 3F, 16, 1, 1);
		bar1.setRotationPoint(0F, 16F, 0F);
		bar1.setTextureSize(64, 32);
		bar1.mirror = true;
		setRotation(bar1, 0F, 0F, 0F);
		bar2 = new ModelRenderer(this, 0, 18);
		bar2.addBox(-8F, -8F, 6F, 16, 1, 1);
		bar2.setRotationPoint(0F, 16F, 0F);
		bar2.setTextureSize(64, 32);
		bar2.mirror = true;
		setRotation(bar2, 0F, 0F, 0F);
		bar3 = new ModelRenderer(this, 0, 21);
		bar3.addBox(-8F, -8F, 4F, 1, 1, 2);
		bar3.setRotationPoint(0F, 16F, 0F);
		bar3.setTextureSize(64, 32);
		bar3.mirror = true;
		setRotation(bar3, 0F, 0F, 0F);
		bar4 = new ModelRenderer(this, 0, 21);
		bar4.addBox(7F, -8F, 4F, 1, 1, 2);
		bar4.setRotationPoint(0F, 16F, 0F);
		bar4.setTextureSize(64, 32);
		bar4.mirror = true;
		setRotation(bar4, 0F, 0F, 0F);
		motor1 = new ModelRenderer(this, 7, 21);
		motor1.addBox(3.5F, -9F, 3.5F, 3, 1, 3);
		motor1.setRotationPoint(0F, 16F, 0F);
		motor1.setTextureSize(64, 32);
		motor1.mirror = true;
		setRotation(motor1, 0F, 0F, 0F);
		motor2 = new ModelRenderer(this, 20, 21);
		motor2.addBox(3F, -13F, 3F, 4, 4, 4);
		motor2.setRotationPoint(0F, 16F, 0F);
		motor2.setTextureSize(64, 32);
		motor2.mirror = true;
		setRotation(motor2, 0F, 0F, 0F);
		motor3 = new ModelRenderer(this, 37, 18);
		motor3.addBox(4.5F, -8F, 4.5F, 1, 10, 1);
		motor3.setRotationPoint(0F, 16F, 0F);
		motor3.setTextureSize(64, 32);
		motor3.mirror = true;
		setRotation(motor3, 0F, 0F, 0F);
		panel = new ModelRenderer(this, 42, 18);
		panel.addBox(-3F, -3F, 7.1F, 6, 6, 1);
		panel.setRotationPoint(0F, 16F, 0F);
		panel.setTextureSize(64, 32);
		panel.mirror = true;
		setRotation(panel, 0F, 0F, 0F);
	}

	public void renderBottom(Entity entity, float f) {
		setRotationAngles(f);
		bottom.render(0.0625F);
		bar1.render(0.0625F);
		bar2.render(0.0625F);
		bar3.render(0.0625F);
		bar4.render(0.0625F);
		motor1.render(0.0625F);
		motor2.render(0.0625F);
		motor3.render(0.0625F);
		panel.render(0.0625F);
	}

	public void renderCage(Entity entity, float f) {
		setRotationAngles(f);
		cap.render(0.0625F);
		cage.render(0.0625F);
	}

	public void renderBody(Entity entity, float f) {
		setRotationAngles(f);
		body.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
