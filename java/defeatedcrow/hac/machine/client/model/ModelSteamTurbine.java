package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSteamTurbine extends DCTileModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer beltbox;
	ModelRenderer back;
	ModelRenderer cooler;
	ModelRenderer tank;
	ModelRenderer body;
	ModelRenderer piston11;
	ModelRenderer piston12;
	ModelRenderer piston21;
	ModelRenderer piston22;
	ModelRenderer pipe1;
	ModelRenderer pipe2;
	ModelRenderer pipe3;
	ModelRenderer pipe4;
	ModelRenderer pipe5;
	ModelRenderer pipe6;
	ModelRenderer pipe7;
	ModelRenderer panel;

	public ModelSteamTurbine() {
		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 51);
		bottom.addBox(-6F, 7F, -6F, 11, 1, 12);
		bottom.setRotationPoint(0F, 0F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		beltbox = new ModelRenderer(this, 44, 28);
		beltbox.addBox(5F, -4F, -4F, 2, 12, 8);
		beltbox.setRotationPoint(0F, 0F, 0F);
		beltbox.setTextureSize(64, 64);
		beltbox.mirror = true;
		setRotation(beltbox, 0F, 0F, 0F);
		back = new ModelRenderer(this, 46, 48);
		back.addBox(-7F, 0F, -4F, 1, 8, 8);
		back.setRotationPoint(0F, 0F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		cooler = new ModelRenderer(this, 0, 22);
		cooler.addBox(-6F, 1F, -2F, 11, 6, 8);
		cooler.setRotationPoint(0F, 0F, 0F);
		cooler.setTextureSize(64, 64);
		cooler.mirror = true;
		setRotation(cooler, 0F, 0F, 0F);
		tank = new ModelRenderer(this, 0, 9);
		tank.addBox(-6F, 0F, -8F, 8, 7, 6);
		tank.setRotationPoint(0F, 0F, 0F);
		tank.setTextureSize(64, 64);
		tank.mirror = true;
		setRotation(tank, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 36);
		body.addBox(-6F, -4F, -2.5F, 11, 5, 10);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		piston11 = new ModelRenderer(this, 32, 12);
		piston11.addBox(0F, -5F, 3F, 4, 1, 4);
		piston11.setRotationPoint(0F, 0F, 0F);
		piston11.setTextureSize(64, 64);
		piston11.mirror = true;
		setRotation(piston11, 0F, 0F, 0F);
		piston12 = new ModelRenderer(this, 32, 12);
		piston12.addBox(-5F, -5F, 3F, 4, 1, 4);
		piston12.setRotationPoint(0F, 0F, 0F);
		piston12.setTextureSize(64, 64);
		piston12.mirror = true;
		setRotation(piston12, 0F, 0F, 0F);
		piston21 = new ModelRenderer(this, 32, 18);
		piston21.addBox(0.5F, -7F, 3.5F, 3, 4, 3);
		piston21.setRotationPoint(0F, 0F, 0F);
		piston21.setTextureSize(64, 64);
		piston21.mirror = true;
		setRotation(piston21, 0F, 0F, 0F);
		piston22 = new ModelRenderer(this, 32, 18);
		piston22.addBox(-4.5F, -7F, 3.5F, 3, 4, 3);
		piston22.setRotationPoint(0F, 0F, 0F);
		piston22.setTextureSize(64, 64);
		piston22.mirror = true;
		setRotation(piston22, 0F, 0F, 0F);
		pipe1 = new ModelRenderer(this, 0, 0);
		pipe1.addBox(2F, 1F, -5F, 2, 2, 3);
		pipe1.setRotationPoint(0F, 0F, 0F);
		pipe1.setTextureSize(64, 64);
		pipe1.mirror = true;
		setRotation(pipe1, 0F, 0F, 0F);
		pipe2 = new ModelRenderer(this, 0, 0);
		pipe2.addBox(2F, 4F, -5F, 2, 2, 3);
		pipe2.setRotationPoint(0F, 0F, 0F);
		pipe2.setTextureSize(64, 64);
		pipe2.mirror = true;
		setRotation(pipe2, 0F, 0F, 0F);
		pipe3 = new ModelRenderer(this, 12, 0);
		pipe3.addBox(-5F, -3F, -6F, 2, 2, 4);
		pipe3.setRotationPoint(0F, 0F, 0F);
		pipe3.setTextureSize(64, 64);
		pipe3.mirror = true;
		setRotation(pipe3, 0F, 0F, 0F);
		pipe4 = new ModelRenderer(this, 12, 0);
		pipe4.addBox(-2F, -3F, -6F, 2, 2, 4);
		pipe4.setRotationPoint(0F, 0F, 0F);
		pipe4.setTextureSize(64, 64);
		pipe4.mirror = true;
		setRotation(pipe4, 0F, 0F, 0F);
		pipe5 = new ModelRenderer(this, 26, 0);
		pipe5.addBox(-5F, -6F, -6F, 2, 3, 2);
		pipe5.setRotationPoint(0F, 0F, 0F);
		pipe5.setTextureSize(64, 64);
		pipe5.mirror = true;
		setRotation(pipe5, 0F, 0F, 0F);
		pipe6 = new ModelRenderer(this, 26, 0);
		pipe6.addBox(-2F, -6F, -6F, 2, 3, 2);
		pipe6.setRotationPoint(0F, 0F, 0F);
		pipe6.setTextureSize(64, 64);
		pipe6.mirror = true;
		setRotation(pipe6, 0F, 0F, 0F);
		pipe7 = new ModelRenderer(this, 36, 0);
		pipe7.addBox(-5F, -2F, 6F, 2, 8, 2);
		pipe7.setRotationPoint(0F, 0F, 0F);
		pipe7.setTextureSize(64, 64);
		pipe7.mirror = true;
		setRotation(pipe7, 0F, 0F, 0F);
		panel = new ModelRenderer(this, 50, 0);
		panel.addBox(7F, -3F, -3F, 1, 6, 6);
		panel.setRotationPoint(0F, 0F, 0F);
		panel.setTextureSize(64, 64);
		panel.mirror = true;
		setRotation(panel, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		bottom.render(0.0625F);
		beltbox.render(0.0625F);
		back.render(0.0625F);
		cooler.render(0.0625F);
		tank.render(0.0625F);
		body.render(0.0625F);
		piston11.render(0.0625F);
		piston12.render(0.0625F);
		piston21.render(0.0625F);
		piston22.render(0.0625F);
		pipe1.render(0.0625F);
		pipe2.render(0.0625F);
		pipe3.render(0.0625F);
		pipe4.render(0.0625F);
		pipe5.render(0.0625F);
		pipe6.render(0.0625F);
		pipe7.render(0.0625F);
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
		double sin = Math.sin(f2) * 2.0F;

		float rot2 = f + 180F;
		float f22 = (float) (rot2 * Math.PI / 180F);// f * 0.01745329F;
		double sin2 = Math.sin(f22) * 2.0F;

		piston21.rotationPointY = (float) sin;
		piston22.rotationPointY = (float) sin2;

		back.rotateAngleX = -f2;
	}

}
