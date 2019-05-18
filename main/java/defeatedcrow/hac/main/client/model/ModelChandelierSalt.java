package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelChandelierSalt extends DCTileModelBase {
	// fields
	ModelRenderer bar1;
	ModelRenderer bar2;
	ModelRenderer bar3;
	ModelRenderer lamp1;
	ModelRenderer lamp2;
	ModelRenderer lamp3;
	ModelRenderer lamp4;
	ModelRenderer bar21;
	ModelRenderer chain1;
	ModelRenderer chain2;
	ModelRenderer lamp21;

	public ModelChandelierSalt() {
		textureWidth = 64;
		textureHeight = 32;

		bar1 = new ModelRenderer(this, 0, 3);
		bar1.addBox(-0.5F, -8F, -0.5F, 1, 16, 1);
		bar1.setRotationPoint(0F, 0F, 0F);
		bar1.setTextureSize(64, 32);
		bar1.mirror = true;
		setRotation(bar1, 0F, 0F, 0F);
		bar2 = new ModelRenderer(this, 0, 0);
		bar2.addBox(-7F, 3F, -0.5F, 14, 1, 1);
		bar2.setRotationPoint(0F, 0F, 0F);
		bar2.setTextureSize(64, 32);
		bar2.mirror = true;
		setRotation(bar2, 0F, 0F, 0F);
		bar3 = new ModelRenderer(this, 0, 0);
		bar3.addBox(-7F, 3F, -0.5F, 14, 1, 1);
		bar3.setRotationPoint(0F, 0F, 0F);
		bar3.setTextureSize(64, 32);
		bar3.mirror = true;
		setRotation(bar3, 0F, 1.570796F, 0F);
		lamp1 = new ModelRenderer(this, 6, 4);
		lamp1.addBox(3F, 1F, -0.5F, 1, 2, 1);
		lamp1.setRotationPoint(0F, 0F, 0F);
		lamp1.setTextureSize(64, 32);
		lamp1.mirror = true;
		setRotation(lamp1, 0F, 0F, 0F);
		lamp2 = new ModelRenderer(this, 6, 4);
		lamp2.addBox(-4F, 1F, -0.5F, 1, 2, 1);
		lamp2.setRotationPoint(0F, 0F, 0F);
		lamp2.setTextureSize(64, 32);
		lamp2.mirror = true;
		setRotation(lamp2, 0F, 0F, 0F);
		lamp3 = new ModelRenderer(this, 6, 4);
		lamp3.addBox(-0.5F, 1F, 3F, 1, 2, 1);
		lamp3.setRotationPoint(0F, 0F, 0F);
		lamp3.setTextureSize(64, 32);
		lamp3.mirror = true;
		setRotation(lamp3, 0F, 0F, 0F);
		lamp4 = new ModelRenderer(this, 6, 4);
		lamp4.addBox(-0.5F, 1F, -4F, 1, 2, 1);
		lamp4.setRotationPoint(0F, 0F, 0F);
		lamp4.setTextureSize(64, 32);
		lamp4.mirror = true;
		setRotation(lamp4, 0F, 0F, 0F);
		bar21 = new ModelRenderer(this, 14, 3);
		bar21.addBox(7F, 4F, -2F, 1, 1, 4);
		bar21.setRotationPoint(0F, -1F, 0F);
		bar21.setTextureSize(64, 32);
		bar21.mirror = true;
		setRotation(bar21, 0F, 0F, 0F);
		chain1 = new ModelRenderer(this, 6, 10);
		chain1.addBox(0.5F, -7F, 0F, 7, 10, 0);
		chain1.setRotationPoint(0F, 0F, 0F);
		chain1.setTextureSize(64, 32);
		chain1.mirror = true;
		setRotation(chain1, 0F, 0F, 0F);
		chain2 = new ModelRenderer(this, 22, 10);
		chain2.addBox(0F, 4F, 0F, 8, 6, 0);
		chain2.setRotationPoint(0F, 0F, 0F);
		chain2.setTextureSize(64, 32);
		chain2.mirror = true;
		setRotation(chain2, 0F, 0F, 0F);
		lamp21 = new ModelRenderer(this, 6, 4);
		lamp21.addBox(7F, 1F, -0.5F, 1, 2, 1);
		lamp21.setRotationPoint(0F, 0F, 0F);
		lamp21.setTextureSize(64, 32);
		lamp21.mirror = true;
		setRotation(lamp21, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		bar1.render(0.0625F);
		bar2.render(0.0625F);
		bar3.render(0.0625F);
	}

	public void renderBar() {
		bar21.render(0.0625F);
	}

	public void renderLamp1() {
		lamp1.render(0.0625F);
		lamp2.render(0.0625F);
		lamp3.render(0.0625F);
		lamp4.render(0.0625F);
	}

	public void renderLamp2() {
		lamp21.render(0.0625F);
		chain1.render(0.0625F);
		chain2.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
	}

}