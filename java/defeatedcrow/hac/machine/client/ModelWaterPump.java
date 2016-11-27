package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelWaterPump extends DCTileModelBase {

	ModelRenderer connect;
	ModelRenderer shaft;
	ModelRenderer body;
	ModelRenderer con1;
	ModelRenderer con2;
	ModelRenderer pipe1;
	ModelRenderer pipe2;
	ModelRenderer con3;

	public ModelWaterPump() {

		textureWidth = 64;
		textureHeight = 64;

		connect = new ModelRenderer(this, 34, 0);
		connect.addBox(-3F, -3F, 7F, 6, 6, 1);
		connect.setRotationPoint(0F, 0F, 0F);
		connect.setTextureSize(64, 64);
		connect.mirror = true;
		setRotation(connect, 0F, 0F, 0F);
		shaft = new ModelRenderer(this, 0, 17);
		shaft.addBox(-2F, -2F, 4F, 4, 4, 3);
		shaft.setRotationPoint(0F, 0F, 0F);
		shaft.setTextureSize(64, 64);
		shaft.mirror = true;
		setRotation(shaft, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-5F, -5F, -3F, 10, 10, 6);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		con1 = new ModelRenderer(this, 49, 0);
		con1.addBox(-3F, -3F, 3F, 6, 6, 1);
		con1.setRotationPoint(0F, 0F, 0F);
		con1.setTextureSize(64, 64);
		con1.mirror = true;
		setRotation(con1, 0F, 0F, 0F);
		con2 = new ModelRenderer(this, 49, 0);
		con2.addBox(-3F, -3F, -4F, 6, 6, 1);
		con2.setRotationPoint(0F, 0F, 0F);
		con2.setTextureSize(64, 64);
		con2.mirror = true;
		setRotation(con2, 0F, 0F, 0F);
		pipe1 = new ModelRenderer(this, 0, 25);
		pipe1.addBox(-2F, -2F, -8F, 4, 12, 4);
		pipe1.setRotationPoint(0F, 0F, 0F);
		pipe1.setTextureSize(64, 64);
		pipe1.mirror = true;
		setRotation(pipe1, 0F, 0F, 0F);
		pipe2 = new ModelRenderer(this, 15, 17);
		pipe2.addBox(-2F, -7F, -2F, 4, 2, 4);
		pipe2.setRotationPoint(0F, 0F, 0F);
		pipe2.setTextureSize(64, 64);
		pipe2.mirror = true;
		setRotation(pipe2, 0F, 0F, 0F);
		con3 = new ModelRenderer(this, 39, 8);
		con3.addBox(-3F, -8F, -3F, 6, 1, 6);
		con3.setRotationPoint(0F, 0F, 0F);
		con3.setTextureSize(64, 64);
		con3.mirror = true;
		setRotation(con3, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		connect.render(scale);
		shaft.render(scale);
		body.render(scale);
		con1.render(scale);
		con2.render(scale);
		pipe1.render(scale);
		pipe2.render(scale);
		con3.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

}
