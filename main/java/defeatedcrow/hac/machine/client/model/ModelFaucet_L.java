package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFaucet_L extends DCTileModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer pipe1;
	ModelRenderer pipe2;
	ModelRenderer vulve1;
	ModelRenderer vulve2;

	public ModelFaucet_L() {
		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-2F, -2F, 7F, 4, 4, 1);
		base.setRotationPoint(0F, 0F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		pipe1 = new ModelRenderer(this, 0, 6);
		pipe1.addBox(-1F, -1F, -1F, 2, 9, 2);
		pipe1.setRotationPoint(0F, 0F, 0F);
		pipe1.setTextureSize(64, 32);
		pipe1.mirror = true;
		setRotation(pipe1, 0F, 0F, 0F);
		pipe2 = new ModelRenderer(this, 9, 7);
		pipe2.addBox(-1F, -1F, 1F, 2, 2, 6);
		pipe2.setRotationPoint(0F, 0F, 0F);
		pipe2.setTextureSize(64, 32);
		pipe2.mirror = true;
		setRotation(pipe2, 0F, 0F, 0F);
		vulve1 = new ModelRenderer(this, 18, 0);
		vulve1.addBox(-1.5F, -1.5F, 2F, 3, 3, 3);
		vulve1.setRotationPoint(0F, 0F, 0F);
		vulve1.setTextureSize(64, 32);
		vulve1.mirror = true;
		setRotation(vulve1, 0F, 0F, 0F);
		vulve2 = new ModelRenderer(this, 32, 0);
		vulve2.addBox(-0.5F, -1.5F, -5F, 1, 2, 6);
		vulve2.setRotationPoint(0F, -2F, 3.5F);
		vulve2.setTextureSize(64, 32);
		vulve2.mirror = true;
		setRotation(vulve2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		base.render(0.0625F);
		pipe1.render(0.0625F);
		pipe2.render(0.0625F);
		vulve1.render(0.0625F);
		vulve2.render(0.0625F);
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

		vulve2.rotateAngleY = f2;
	}

}
