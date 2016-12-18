package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShaft_S extends DCTileModelBase {
	// fields
	ModelRenderer pad1;
	ModelRenderer pad2;
	ModelRenderer shaft1;
	ModelRenderer shaftcube;

	public ModelShaft_S() {
		textureWidth = 64;
		textureHeight = 64;

		pad1 = new ModelRenderer(this, 0, 0);
		pad1.addBox(-3F, -8F, -3F, 6, 1, 6);
		pad1.setRotationPoint(0F, 0F, 0F);
		pad1.setTextureSize(64, 32);
		pad1.mirror = true;
		setRotation(pad1, 0F, 0F, 0F);
		pad2 = new ModelRenderer(this, 26, 0);
		pad2.addBox(-3F, 7F, -3F, 6, 1, 6);
		pad2.setRotationPoint(0F, 0F, 0F);
		pad2.setTextureSize(64, 32);
		pad2.mirror = true;
		setRotation(pad2, 0F, 0F, 0F);
		shaft1 = new ModelRenderer(this, 0, 8);
		shaft1.addBox(-0.5F, -7F, -0.5F, 1, 14, 1);
		shaft1.setRotationPoint(0F, 0F, 0F);
		shaft1.setTextureSize(64, 32);
		shaft1.mirror = true;
		setRotation(shaft1, 0F, 0F, 0F);
		shaftcube = new ModelRenderer(this, 6, 8);
		shaftcube.addBox(-1.5F, -6.5F, -1.5F, 3, 13, 3);
		shaftcube.setRotationPoint(0F, 0F, 0F);
		shaftcube.setTextureSize(64, 32);
		shaftcube.mirror = true;
		setRotation(shaftcube, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		pad1.render(0.0625F);
		pad2.render(0.0625F);
		shaft1.render(0.0625F);
		shaftcube.render(0.0625F);
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

		shaftcube.rotateAngleY = f2;
		shaft1.rotateAngleY = f2;
	}

}
