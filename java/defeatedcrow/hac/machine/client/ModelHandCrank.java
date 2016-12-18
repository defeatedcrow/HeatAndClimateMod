package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelHandCrank extends DCTileModelBase {
	// fields
	ModelRenderer pad1;
	ModelRenderer shaft1;
	ModelRenderer shaftcube1;
	ModelRenderer gear1;
	ModelRenderer crank;
	ModelRenderer handle;

	public ModelHandCrank() {
		textureWidth = 64;
		textureHeight = 64;

		pad1 = new ModelRenderer(this, 0, 0);
		pad1.addBox(-3F, 7F, -3F, 6, 1, 6);
		pad1.setRotationPoint(0F, 0F, 0F);
		pad1.setTextureSize(64, 32);
		pad1.mirror = true;
		setRotation(pad1, 0F, 0F, 0F);
		shaft1 = new ModelRenderer(this, 0, 8);
		shaft1.addBox(-0.5F, 3F, -0.5F, 1, 4, 1);
		shaft1.setRotationPoint(0F, 0F, 0F);
		shaft1.setTextureSize(64, 32);
		shaft1.mirror = true;
		setRotation(shaft1, 0F, 0F, 0F);
		shaftcube1 = new ModelRenderer(this, 6, 8);
		shaftcube1.addBox(-1.5F, 5F, -1.5F, 3, 2, 3);
		shaftcube1.setRotationPoint(0F, 0F, 0F);
		shaftcube1.setTextureSize(64, 32);
		shaftcube1.mirror = true;
		setRotation(shaftcube1, 0F, 0F, 0F);
		gear1 = new ModelRenderer(this, 20, 8);
		gear1.addBox(-1F, 3.5F, -1F, 2, 2, 2);
		gear1.setRotationPoint(0F, -1F, 0F);
		gear1.setTextureSize(64, 32);
		gear1.mirror = true;
		setRotation(gear1, 0F, 0F, 0F);
		crank = new ModelRenderer(this, 0, 15);
		crank.addBox(0F, 3F, -0.5F, 6, 1, 1);
		crank.setRotationPoint(0F, 0F, 0F);
		crank.setTextureSize(64, 64);
		crank.mirror = true;
		setRotation(crank, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 0, 19);
		handle.addBox(4.5F, -1F, -1F, 2, 4, 2);
		handle.setRotationPoint(0F, 0F, 0F);
		handle.setTextureSize(64, 64);
		handle.mirror = true;
		setRotation(handle, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		pad1.render(0.0625F);
		shaft1.render(0.0625F);
		shaftcube1.render(0.0625F);
		gear1.render(0.0625F);
		crank.render(0.0625F);
		handle.render(0.0625F);
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

		gear1.rotateAngleY = f2;
		crank.rotateAngleY = f2;
		handle.rotateAngleY = f2;
	}

}
