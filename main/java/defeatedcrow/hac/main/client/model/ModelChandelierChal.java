package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelChandelierChal extends DCTileModelBase {
	// fields
	ModelRenderer bar1;
	ModelRenderer bar2;
	ModelRenderer bar3;
	ModelRenderer bar4;
	ModelRenderer lamp1;
	ModelRenderer lamp2;
	ModelRenderer lamp3;
	ModelRenderer chain1;
	ModelRenderer crystal1;
	ModelRenderer crystal2;

	public ModelChandelierChal() {
		textureWidth = 64;
		textureHeight = 32;

		bar1 = new ModelRenderer(this, 24, 0);
		bar1.addBox(-1F, -8F, -1F, 2, 1, 2);
		bar1.setRotationPoint(0F, 0F, 0F);
		bar1.setTextureSize(64, 32);
		bar1.mirror = true;
		setRotation(bar1, 0F, 0F, 0F);
		bar2 = new ModelRenderer(this, 0, 0);
		bar2.addBox(-1F, -7F, 0F, 2, 8, 0);
		bar2.setRotationPoint(0F, 0F, 0F);
		bar2.setTextureSize(64, 32);
		bar2.mirror = true;
		setRotation(bar2, 0F, 0F, 0F);
		bar3 = new ModelRenderer(this, 0, 0);
		bar3.addBox(-1F, -7F, 0F, 2, 8, 0);
		bar3.setRotationPoint(0F, 0F, 0F);
		bar3.setTextureSize(64, 32);
		bar3.mirror = true;
		setRotation(bar3, 0F, 1.570796F, 0F);
		bar4 = new ModelRenderer(this, 6, 0);
		bar4.addBox(-2F, 1F, -2F, 4, 1, 4);
		bar4.setRotationPoint(0F, 0F, 0F);
		bar4.setTextureSize(64, 32);
		bar4.mirror = true;
		setRotation(bar4, 0F, 0F, 0F);
		lamp1 = new ModelRenderer(this, 0, 16);
		lamp1.addBox(6F, 2F, -1.5F, 3, 1, 3);
		lamp1.setRotationPoint(0F, -1F, 0F);
		lamp1.setTextureSize(64, 32);
		lamp1.mirror = true;
		setRotation(lamp1, 0F, 0F, 0F);
		lamp2 = new ModelRenderer(this, 14, 16);
		lamp2.addBox(7F, -1F, -0.5F, 1, 2, 1);
		lamp2.setRotationPoint(0F, 0F, 0F);
		lamp2.setTextureSize(64, 32);
		lamp2.mirror = true;
		setRotation(lamp2, 0F, 0F, 0F);
		lamp3 = new ModelRenderer(this, 0, 22);
		lamp3.addBox(6.5F, -3F, -1F, 2, 3, 2);
		lamp3.setRotationPoint(0F, 0F, 0F);
		lamp3.setTextureSize(64, 32);
		lamp3.mirror = true;
		setRotation(lamp3, 0F, 0F, 0F);
		chain1 = new ModelRenderer(this, 6, 8);
		chain1.addBox(0F, 2F, 0F, 8, 4, 0);
		chain1.setRotationPoint(0F, 0F, 0F);
		chain1.setTextureSize(64, 32);
		chain1.mirror = true;
		setRotation(chain1, 0F, 0F, 0F);
		crystal1 = new ModelRenderer(this, 10, 22);
		crystal1.addBox(5F, 4.75F, -0.5F, 1, 1, 1);
		crystal1.setRotationPoint(0F, 0F, 0F);
		crystal1.setTextureSize(64, 32);
		crystal1.mirror = true;
		setRotation(crystal1, 0F, 0F, 0F);
		crystal2 = new ModelRenderer(this, 16, 22);
		crystal2.addBox(5F, 6F, -0.5F, 1, 2, 1);
		crystal2.setRotationPoint(0F, 0F, 0F);
		crystal2.setTextureSize(64, 32);
		crystal2.mirror = true;
		setRotation(crystal2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		bar1.render(0.0625F);
		bar2.render(0.0625F);
		bar3.render(0.0625F);
		bar4.render(0.0625F);
	}

	public void renderLamp() {
		lamp1.render(0.0625F);
		lamp2.render(0.0625F);
		lamp3.render(0.0625F);
		chain1.render(0.0625F);
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