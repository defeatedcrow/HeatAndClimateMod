package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShaft_Switch extends DCTileModelBase {
	// fields
	public ModelRenderer pad1;
	public ModelRenderer pad2;
	public ModelRenderer shaft1;
	public ModelRenderer shaftcube;
	public ModelRenderer shaftcube2;
	public ModelRenderer shaft2;
	public ModelRenderer middle;
	public ModelRenderer leverbox;
	public ModelRenderer leverbox2;
	public ModelRenderer leverbox3;

	public ModelShaft_Switch() {
		textureWidth = 64;
		textureHeight = 32;

		pad1 = new ModelRenderer(this, 0, 0);
		pad1.addBox(-3F, -8F, -3F, 6, 1, 6, 0F);
		pad1.setRotationPoint(0F, 0F, 0F);
		pad1.rotateAngleX = 0F;
		pad1.rotateAngleY = 0F;
		pad1.rotateAngleZ = 0F;
		pad1.mirror = false;
		pad2 = new ModelRenderer(this, 26, 0);
		pad2.addBox(-3F, 7F, -3F, 6, 1, 6, 0F);
		pad2.setRotationPoint(0F, 0F, 0F);
		pad2.rotateAngleX = 0F;
		pad2.rotateAngleY = 0F;
		pad2.rotateAngleZ = 0F;
		pad2.mirror = false;
		shaft1 = new ModelRenderer(this, 0, 8);
		shaft1.addBox(-0.5F, -7F, -0.5F, 1, 5, 1, 0F);
		shaft1.setRotationPoint(0F, 0F, 0F);
		shaft1.rotateAngleX = 0F;
		shaft1.rotateAngleY = 0F;
		shaft1.rotateAngleZ = 0F;
		shaft1.mirror = false;
		shaftcube = new ModelRenderer(this, 6, 8);
		shaftcube.addBox(-1.5F, -6.5F, -1.5F, 3, 5, 3, 0F);
		shaftcube.setRotationPoint(0F, 0F, 0F);
		shaftcube.rotateAngleX = 0F;
		shaftcube.rotateAngleY = 0F;
		shaftcube.rotateAngleZ = 0F;
		shaftcube.mirror = false;
		shaftcube2 = new ModelRenderer(this, 6, 8);
		shaftcube2.addBox(-1.5F, 1.0F, -1.5F, 3, 5, 3, 0F);
		shaftcube2.setRotationPoint(0F, 0F, 0F);
		shaftcube2.rotateAngleX = 0F;
		shaftcube2.rotateAngleY = 0F;
		shaftcube2.rotateAngleZ = 0F;
		shaftcube2.mirror = false;
		shaft2 = new ModelRenderer(this, 0, 8);
		shaft2.addBox(-0.5F, 2F, -0.5F, 1, 5, 1, 0F);
		shaft2.setRotationPoint(0F, 0F, 0F);
		shaft2.rotateAngleX = 0F;
		shaft2.rotateAngleY = 0F;
		shaft2.rotateAngleZ = 0F;
		shaft2.mirror = false;
		middle = new ModelRenderer(this, 20, 8);
		middle.addBox(-1F, -2F, -1F, 2, 4, 2, 0F);
		middle.setRotationPoint(0F, 0F, 0F);
		middle.rotateAngleX = 0F;
		middle.rotateAngleY = 0F;
		middle.rotateAngleZ = 0F;
		middle.mirror = false;
		leverbox = new ModelRenderer(this, 0, 18);
		leverbox.addBox(-3F, -6.1F, -0.5F, 1, 9, 1, 0F);
		leverbox.setRotationPoint(0F, 0F, 0F);
		leverbox.rotateAngleX = 0F;
		leverbox.rotateAngleY = 0F;
		leverbox.rotateAngleZ = 0F;
		leverbox.mirror = false;
		leverbox2 = new ModelRenderer(this, 6, 18);
		leverbox2.addBox(-2.5F, -6F, -2.5F, 5, 1, 5, 0F);
		leverbox2.setRotationPoint(0F, 0F, 0F);
		leverbox2.rotateAngleX = 0F;
		leverbox2.rotateAngleY = 0F;
		leverbox2.rotateAngleZ = 0F;
		leverbox2.mirror = false;
		leverbox3 = new ModelRenderer(this, 6, 18);
		leverbox3.addBox(-2.5F, 2F, -2.5F, 5, 1, 5, 0F);
		leverbox3.setRotationPoint(0F, 0F, 0F);
		leverbox3.rotateAngleX = 0F;
		leverbox3.rotateAngleY = 0F;
		leverbox3.rotateAngleZ = 0F;
		leverbox3.mirror = false;
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		pad1.render(0.0625F);
		pad2.render(0.0625F);
		shaft1.render(0.0625F);
		shaftcube.render(0.0625F);
		shaftcube2.render(0.0625F);
		shaft2.render(0.0625F);
		middle.render(0.0625F);
		leverbox.render(0.0625F);
		leverbox2.render(0.0625F);
		leverbox3.render(0.0625F);
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
		shaftcube2.rotateAngleY = f2;
		middle.rotateAngleY = f2;
		shaft2.rotateAngleY = f2;

		if (speed > 0) {
			shaftcube2.offsetY = 0.5F / 16F;
		} else {
			shaftcube2.offsetY = -2.0F / 16F;
		}
	}

}
