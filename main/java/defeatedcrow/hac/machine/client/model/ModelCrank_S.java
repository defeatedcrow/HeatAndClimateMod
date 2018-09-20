package defeatedcrow.hac.machine.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCrank_S extends DCTileModelBase {
	// fields
	ModelRenderer pad1;
	ModelRenderer shaft1;
	ModelRenderer shaftcube1;
	ModelRenderer gear1;
	ModelRenderer cover1;
	ModelRenderer cover2;
	ModelRenderer cover4;
	ModelRenderer cover3;
	ModelRenderer gear2;
	ModelRenderer crank;
	ModelRenderer piston;
	ModelRenderer lod;

	public ModelCrank_S() {
		textureWidth = 64;
		textureHeight = 64;

		pad1 = new ModelRenderer(this, 0, 0);
		pad1.addBox(-3F, -8F, -3F, 6, 1, 6);
		pad1.setRotationPoint(0F, 0F, 0F);
		pad1.setTextureSize(64, 32);
		pad1.mirror = true;
		setRotation(pad1, 0F, 0F, 0F);
		shaft1 = new ModelRenderer(this, 0, 8);
		shaft1.addBox(-0.5F, -7F, -0.5F, 1, 5, 1);
		shaft1.setRotationPoint(0F, 0F, 0F);
		shaft1.setTextureSize(64, 32);
		shaft1.mirror = true;
		setRotation(shaft1, 0F, 0F, 0F);
		shaftcube1 = new ModelRenderer(this, 6, 8);
		shaftcube1.addBox(-1.5F, -7F, -1.5F, 3, 2, 3);
		shaftcube1.setRotationPoint(0F, 0F, 0F);
		shaftcube1.setTextureSize(64, 32);
		shaftcube1.mirror = true;
		setRotation(shaftcube1, 0F, 0F, 0F);
		gear1 = new ModelRenderer(this, 20, 8);
		gear1.addBox(-1F, -1.5F, -1F, 2, 2, 2);
		gear1.setRotationPoint(0F, -3F, 0F);
		gear1.setTextureSize(64, 32);
		gear1.mirror = true;
		setRotation(gear1, 0F, 0F, 0F);
		cover1 = new ModelRenderer(this, 0, 32);
		cover1.addBox(-2F, -4F, -2F, 4, 1, 4);
		cover1.setRotationPoint(0F, 0F, 0F);
		cover1.setTextureSize(64, 32);
		cover1.mirror = true;
		setRotation(cover1, 0F, 0F, 0F);
		cover2 = new ModelRenderer(this, 0, 38);
		cover2.addBox(-3F, -4F, -3F, 1, 10, 6);
		cover2.setRotationPoint(0F, 0F, 0F);
		cover2.setTextureSize(64, 32);
		cover2.mirror = true;
		setRotation(cover2, 0F, 0F, 0F);
		cover4 = new ModelRenderer(this, 16, 38);
		cover4.addBox(-2F, -4F, 2F, 4, 10, 1);
		cover4.setRotationPoint(0F, 0F, 0F);
		cover4.setTextureSize(64, 32);
		cover4.mirror = true;
		setRotation(cover4, 0F, 0F, 0F);
		cover3 = new ModelRenderer(this, 16, 38);
		cover3.addBox(-2F, -4F, -3F, 4, 10, 1);
		cover3.setRotationPoint(0F, 0F, 0F);
		cover3.setTextureSize(64, 32);
		cover3.mirror = true;
		setRotation(cover3, 0F, 0F, 0F);
		gear2 = new ModelRenderer(this, 32, 0);
		gear2.addBox(2.1F, -2.5F, -2.5F, 1, 5, 5);
		gear2.setRotationPoint(0F, -0.5F, 0F);
		gear2.setTextureSize(64, 32);
		gear2.mirror = true;
		setRotation(gear2, 0F, 0F, 0F);
		crank = new ModelRenderer(this, 46, 0);
		crank.addBox(-1.5F, -2F, -1F, 5, 4, 2);
		crank.setRotationPoint(0F, -0.5F, 0F);
		crank.setTextureSize(64, 32);
		crank.mirror = true;
		setRotation(crank, 0F, 0F, 0F);
		piston = new ModelRenderer(this, 46, 8);
		piston.addBox(-1.5F, 4F, -1.5F, 3, 6, 3);
		piston.setRotationPoint(0F, 0F, 0F);
		piston.setTextureSize(64, 32);
		piston.mirror = true;
		setRotation(piston, 0F, 0F, 0F);
		lod = new ModelRenderer(this, 32, 12);
		lod.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
		lod.setRotationPoint(0F, 6F, 0F);
		lod.setTextureSize(64, 32);
		lod.mirror = true;
		setRotation(lod, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		pad1.render(0.0625F);
		shaft1.render(0.0625F);
		shaftcube1.render(0.0625F);
		gear1.render(0.0625F);
		cover1.render(0.0625F);
		cover2.render(0.0625F);
		cover4.render(0.0625F);
		cover3.render(0.0625F);
		gear2.render(0.0625F);
		crank.render(0.0625F);
		piston.render(0.0625F);
		lod.render(0.0625F);
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
		float f1 = (float) (rot * Math.PI / 180F);// f * 0.01745329F;

		shaftcube1.rotateAngleY = f1;
		shaft1.rotateAngleY = f1;

		gear2.rotateAngleX = f1;
		crank.rotateAngleX = f1;

		float c1 = (float) Math.cos(f1);
		float c2 = (float) Math.cos(f1 - 1.570796F);
		float f3 = c2 * 0.15F;

		piston.rotationPointY = c1 * 2.0F;
		lod.rotationPointY = 4F + c1 * 2.0F;
		lod.rotateAngleX = f3;
	}

}
