package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShaft_TB extends DCTileModelBase {
	// fields
	ModelRenderer pad1;
	ModelRenderer pad2;
	ModelRenderer pad3;
	ModelRenderer shaft1;
	ModelRenderer shaft2;
	ModelRenderer shaftcube1;
	ModelRenderer shaftcube2;
	ModelRenderer shaftcube3;
	ModelRenderer joint;
	ModelRenderer gear1;
	ModelRenderer gear2;
	ModelRenderer gear3;

	public ModelShaft_TB() {
		textureWidth = 64;
		textureHeight = 64;

		pad1 = new ModelRenderer(this, 0, 0);
		pad1.addBox(-3F, -8F, -3F, 6, 1, 6);
		pad1.setRotationPoint(0F, 0F, 0F);
		pad1.setTextureSize(64, 32);
		pad1.mirror = true;
		setRotation(pad1, 0F, 0F, 0F);
		pad2 = new ModelRenderer(this, 32, 0);
		pad2.addBox(-8F, -3F, -3F, 1, 6, 6);
		pad2.setRotationPoint(0F, 0F, 0F);
		pad2.setTextureSize(64, 32);
		pad2.mirror = true;
		setRotation(pad2, 0F, 0F, 0F);
		pad3 = new ModelRenderer(this, 32, 0);
		pad3.addBox(7F, -3F, -3F, 1, 6, 6);
		pad3.setRotationPoint(0F, 0F, 0F);
		pad3.setTextureSize(64, 32);
		pad3.mirror = true;
		setRotation(pad3, 0F, 0F, 0F);
		shaft1 = new ModelRenderer(this, 0, 8);
		shaft1.addBox(-0.5F, -7F, -0.5F, 1, 7, 1);
		shaft1.setRotationPoint(0F, 0F, 0F);
		shaft1.setTextureSize(64, 32);
		shaft1.mirror = true;
		setRotation(shaft1, 0F, 0F, 0F);
		shaft2 = new ModelRenderer(this, 32, 14);
		shaft2.addBox(-7F, -0.5F, -0.5F, 14, 1, 1);
		shaft2.setRotationPoint(0F, 0F, 0F);
		shaft2.setTextureSize(64, 32);
		shaft2.mirror = true;
		setRotation(shaft2, 0F, 0F, 0F);
		shaftcube1 = new ModelRenderer(this, 6, 8);
		shaftcube1.addBox(-1.5F, -7F, -1.5F, 3, 4, 3);
		shaftcube1.setRotationPoint(0F, 0F, 0F);
		shaftcube1.setTextureSize(64, 32);
		shaftcube1.mirror = true;
		setRotation(shaftcube1, 0F, 0F, 0F);
		shaftcube2 = new ModelRenderer(this, 47, 0);
		shaftcube2.addBox(-7F, -1.5F, -1.5F, 3, 3, 3);
		shaftcube2.setRotationPoint(0F, 0F, 0F);
		shaftcube2.setTextureSize(64, 32);
		shaftcube2.mirror = true;
		setRotation(shaftcube2, 0F, 0F, 0F);
		shaftcube3 = new ModelRenderer(this, 46, 18);
		shaftcube3.addBox(4F, -1.5F, -1.5F, 3, 3, 3);
		shaftcube3.setRotationPoint(0F, 0F, 0F);
		shaftcube3.setTextureSize(64, 32);
		shaftcube3.mirror = true;
		setRotation(shaftcube3, 0F, 0F, 0F);
		joint = new ModelRenderer(this, 47, 8);
		joint.addBox(-0.5F, -1F, -1F, 1, 2, 2);
		joint.setRotationPoint(0F, 0F, 0F);
		joint.setTextureSize(64, 32);
		joint.mirror = true;
		setRotation(joint, 0F, 0F, 0F);
		gear1 = new ModelRenderer(this, 0, 18);
		gear1.addBox(-2.5F, -3F, -2.5F, 5, 1, 5);
		gear1.setRotationPoint(0F, 0F, 0F);
		gear1.setTextureSize(64, 32);
		gear1.mirror = true;
		setRotation(gear1, 0F, 0F, 0F);
		gear2 = new ModelRenderer(this, 32, 18);
		gear2.addBox(-4F, -2.5F, -2.5F, 1, 5, 5);
		gear2.setRotationPoint(0F, 0F, 0F);
		gear2.setTextureSize(64, 32);
		gear2.mirror = true;
		setRotation(gear2, 0F, 0F, 0F);
		gear3 = new ModelRenderer(this, 0, 26);
		gear3.addBox(2.5F, -4.5F, -4.5F, 1, 9, 9);
		gear3.setRotationPoint(0F, 0F, 0F);
		gear3.setTextureSize(64, 32);
		gear3.mirror = true;
		setRotation(gear3, 0F, -1.570796F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		pad1.render(0.0625F);
		pad2.render(0.0625F);
		pad3.render(0.0625F);
		shaft1.render(0.0625F);
		shaft2.render(0.0625F);
		shaftcube1.render(0.0625F);
		shaftcube2.render(0.0625F);
		shaftcube3.render(0.0625F);
		joint.render(0.0625F);
		gear1.render(0.0625F);
		gear2.render(0.0625F);
		gear3.render(0.0625F);
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
		float f3 = f2 * 0.5F;

		shaftcube1.rotateAngleY = f2;
		shaft1.rotateAngleY = f2;
		gear1.rotateAngleY = f2;

		shaftcube2.rotateAngleX = -f3;
		shaft2.rotateAngleX = -f3;
		gear2.rotateAngleX = -f3;
		shaftcube3.rotateAngleX = f3;

		gear3.rotateAngleX = -f3;
	}

}
