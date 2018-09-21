package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCartMotor extends DCTileModelBase {

	ModelRenderer tank;
	ModelRenderer motor;
	ModelRenderer pulley;
	ModelRenderer pipe1;
	ModelRenderer pipe2;

	public ModelCartMotor() {
		textureWidth = 64;
		textureHeight = 32;

		tank = new ModelRenderer(this, 0, 10);
		tank.addBox(-5F, -6F, 9.5F, 8, 2, 4);
		tank.setRotationPoint(0F, 4F, 0F);
		tank.setTextureSize(64, 32);
		tank.mirror = true;
		setRotation(tank, 0F, 0F, 0F);
		motor = new ModelRenderer(this, 0, 0);
		motor.addBox(-5F, -4F, 10F, 8, 4, 4);
		motor.setRotationPoint(0F, 4F, 0F);
		motor.setTextureSize(64, 32);
		motor.mirror = true;
		setRotation(motor, 0F, 0F, 0F);
		pulley = new ModelRenderer(this, 26, 0);
		pulley.addBox(3F, -5F, 10F, 4, 5, 5);
		pulley.setRotationPoint(0F, 4F, 0F);
		pulley.setTextureSize(64, 32);
		pulley.mirror = true;
		setRotation(pulley, 0F, 0F, 0F);
		pipe1 = new ModelRenderer(this, 0, 18);
		pipe1.addBox(-9F, -3F, 11F, 4, 2, 2);
		pipe1.setRotationPoint(0F, 4F, 0F);
		pipe1.setTextureSize(64, 32);
		pipe1.mirror = true;
		setRotation(pipe1, 0F, 0F, 0F);
		pipe2 = new ModelRenderer(this, 14, 18);
		pipe2.addBox(-9F, -5F, 11F, 2, 2, 2);
		pipe2.setRotationPoint(0F, 4F, 0F);
		pipe2.setTextureSize(64, 32);
		pipe2.mirror = true;
		setRotation(pipe2, 0F, 0F, 0F);
		tank.rotateAngleY = ((float) Math.PI / 2F);
		motor.rotateAngleY = ((float) Math.PI / 2F);
		pulley.rotateAngleY = ((float) Math.PI / 2F);
		pipe1.rotateAngleY = ((float) Math.PI / 2F);
		pipe2.rotateAngleY = ((float) Math.PI / 2F);
	}

	public void render(Entity entity, float f) {
		setRotationAngles(f);
		tank.render(0.0625F);
		motor.render(0.0625F);
		pulley.render(0.0625F);
		pipe1.render(0.0625F);
		pipe2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
